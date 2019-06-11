package rs.raf.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import rs.raf.utilities.SQLUtilities;
import rs.raf.view.frames.MainFrame;
import rs.raf.view.panels.FileView;

public class DBFile extends UIAbstractFile {
	protected String TABLE_NAME;
	private Connection sqlConnection;

	public DBFile(String tableName) {
		super(tableName, "", tableName + " .db");
		this.TABLE_NAME = tableName;
	}

	public void readHeader() throws IOException, SQLException {
		DatabaseMetaData dbms = sqlConnection.getMetaData();
		ResultSet columnNames = dbms.getColumns(null, null, TABLE_NAME, null);
		ResultSet pkeys = dbms.getPrimaryKeys(null, null, TABLE_NAME);
		while (columnNames.next()) {

			UIFileField field = new UIFileField(columnNames.getString("COLUMN_NAME"),
					columnNames.getString("TYPE_NAME").toUpperCase(), columnNames.getInt("COLUMN_SIZE"), false);
			if (field.getFieldType().equals("IMAGE"))
				continue;

			String mandatory = columnNames.getString("IS_NULLABLE").toUpperCase();

			if (mandatory.equals("YES"))
				field.setNullable(true);
			fields.add(field);

			while (pkeys.next()) {
				if (pkeys.getString("COLUMN_NAME").equals(field.getFieldName())) {
					field.setFieldPK(true);
					field.setSort(true);
					break;
				}
			}
		}
		pkeys.close();
		columnNames.close();
	}

	@Override
	public boolean fetchNextBlock() throws IOException, SQLException {
		// kreiram kolone
		String collumns = null;
		for (UIFileField field : fields) {
			if (collumns != null) {
				collumns += "," + field.getFieldName();
			} else
				collumns = field.getFieldName();
		}
		// broj redova za data
		Statement st = sqlConnection.createStatement();
		ResultSet set = st.executeQuery("select count(*) from " + TABLE_NAME + ";");
		if (set.next()) {
			RECORD_NUM = set.getInt(1);
		}

		st.close();
		set.close();
		data = new String[(int) RECORD_NUM][];
		Statement st1 = sqlConnection.createStatement();
		ResultSet set1 = st1.executeQuery("select " + collumns + " from " + TABLE_NAME + ";");
		int i = 0;
		while (set1.next()) {
			data[i] = new String[fields.size()];
			for (int j = 0; j < fields.size(); j++) {
				data[i][j] = set1.getString(j + 1);
			}
			i++;
		}
		notifyAllObservers();
		return true;
	}

	@SuppressWarnings("deprecation")
	public boolean addRecord(ArrayList<String> record) throws SQLException {
		String collumns = null;
		for (UIFileField field : fields) {
			if (collumns != null) {
				collumns += "," + field.getFieldName();
			} else
				collumns = field.getFieldName();
		}
		String q = null;
		for (int i = 0; i < fields.size(); i++) {
			if (q == null)
				q = "?";
			else
				q += ",?";
		}
		String insertSql = "insert into " + TABLE_NAME + " (" + collumns + ") values(" + q + ");";
		PreparedStatement st = sqlConnection.prepareStatement(insertSql);

		int i = 0;
		for (UIFileField field : fields) {

			if (record.get(i).equals("")) {
				st.setObject(i + 1, null);
			}

			else if (field.isDate()) {
				String[] tok1 = record.get(i).split(" ");
				String[] tok = tok1[0].split("-");
				int d = Integer.parseInt(tok[2]);
				int m = Integer.parseInt(tok[1]);
				int y = Integer.parseInt(tok[0]);
				// st.setObject(i+1, new Date(d,m,y));
				st.setDate(i + 1, (new Date(y, m, d)));
				// st.setString(i+1,record.get(i));
			}

			else if (field.isNumeric()) {
				if (field.getFieldType().equals("INT"))
					st.setInt(i + 1, Integer.parseInt(record.get(i)));
				else if (field.getFieldType().contains("DOUBLE")) {
					st.setDouble(i + 1, Double.parseDouble(record.get(i)));
				} else {
					BigDecimal b = new BigDecimal(Double.parseDouble(record.get(i)));
					st.setBigDecimal(i + 1, b);
				}

			}

			else if (field.isStringType()) {
				st.setString(i + 1, record.get(i));

			} else if (field.getFieldType().equals("IMAGE")) {
				File img = new File(record.get(i));
				FileInputStream fin;
				try {
					fin = new FileInputStream(img);
					st.setBinaryStream(1, fin, fin.available());
				} catch (IOException e) {
					MainFrame.getInstance().getExceptionsManager().errorImage();
				}

			}

			// st.setString(i+1,record.get(i));
			i++;
		}

		System.out.println(st.toString());

		st.execute();
		try {
			fetchNextBlock();
		} catch (IOException e) {
			e.printStackTrace();
			MainFrame.getInstance().getExceptionsManager().errorFailedToFetchBlock();
		}
		return true;

	}

	public boolean updateRecord(ArrayList<String> record, String where) throws IOException, SQLException {

		int j = 0;
		String sql = "UPDATE " + TABLE_NAME + " SET ";
		String sql2 = null;
		for (UIFileField field : fields) {
			if (sql2 == null)
				sql2 = field.getFieldName() + " = " + record.get(j);
			else
				sql2 += "," + field.getFieldName() + " = " + record.get(j);
			j++;
		}

		sql += sql2;
		sql += where + ";";

		Statement st = sqlConnection.createStatement();
		System.out.println(sql);
		st.execute(sql);
		fetchNextBlock();

		return true;
	}

	public void sortMDI() throws IOException, SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ";
		String sql2 = null;
		String order = null;
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).isSort()) {
				if (fields.get(i).isAscending())
					order = "ASC";
				else
					order = "DESC";

				if (sql2 == null)
					sql2 = fields.get(i).getFieldName() + " " + order;
				else
					sql2 += "," + fields.get(i).getFieldName() + " " + order;

			}
		}
		sql2 += ";";
		sql += sql2;
		Statement st = sqlConnection.createStatement();
		System.out.println(sql);
		System.out.println(sql2);
		// st.executeQuery(sql);
		// fetchNextBlock();

		ResultSet set1 = st.executeQuery(sql);
		int i = 0;
		while (set1.next()) {
			data[i] = new String[fields.size()];
			for (int j = 0; j < fields.size(); j++) {
				data[i][j] = set1.getString(j + 1);
			}
			i++;
		}
		notifyAllObservers();
	}

	public void avg(String arg1, ArrayList<String> orderBy) throws SQLException, IOException {

		String sql = "SELECT AVG(" + arg1 + ") FROM " + TABLE_NAME;
		String sql2 = null;
		for (int i = 0; i < orderBy.size(); i++) {

			if (sql2 == null)
				sql2 = " GROUP BY " + orderBy.get(i).toString();
			else
				sql2 += ", " + orderBy.get(i).toString();

		}

		if (sql2 != null)
			sql += sql2 + ";";
		else
			sql += ";";

		System.out.println(sql);
		Statement st = sqlConnection.createStatement();
		ResultSet set1 = st.executeQuery(sql);
		int kolona = set1.getMetaData().getColumnCount();

		ArrayList<String> list = new ArrayList<>();
		RECORD_NUM = 0;
		while (set1.next()) {
			RECORD_NUM++;
			list.add(set1.getString(1));
			System.out.println(set1.getString(1));
		}
		data = new String[(int) RECORD_NUM][];

		for (int i = 0; i < RECORD_NUM; i++) {
			data[i] = new String[kolona];
			data[i][0] = list.get(i);
		}
		System.out.println(list.size() + "-" + RECORD_NUM);

		FileView fv = ((FileView) MainFrame.getInstance().getRightPanel().getTopPanel().getSelectedComponent());
		fv.setAvgView();

	}

	public void setConnector(Connection sqlConnection) {
		this.sqlConnection = sqlConnection;
	}
	
	/*
	public boolean filterFind(ArrayList<String> searchRec) throws SQLException {
		System.out.println("Received data: " + searchRec);

		ArrayList<String> dataToReplace = new ArrayList<>(searchRec.size());
		StringBuilder queryString = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE ");

		boolean firstTime = true;
		for (String currentQueryPart : searchRec) {
			String[] currentPart = currentQueryPart.split("###");
			dataToReplace.add(currentPart[2]);

			if (currentPart.length == 4) {
				if (firstTime) {
					queryString.append(currentPart[0]).append(" ").append(currentPart[1]).append(" ").append("?")
							.append(" ").append(currentPart[3]).append(" ");
				} else
					queryString.append(currentPart[3]).append(" ").append(currentPart[0]).append(" ")
							.append(currentPart[1]).append(" ").append("?").append(" ");
			} else {
				queryString.append(currentPart[0]).append(" ").append(currentPart[1]).append(" ").append("?")
						.append(" ");
			}

			firstTime = false;
		}

		queryString.append(";");

		System.out.println("Query select: " + queryString.toString());
		System.out.println("Data to replace: " + dataToReplace);
		int numForAllocation = getRowCount(searchRec);

		System.out.println("Query - Before the preparation: " + queryString.toString());
		PreparedStatement statement = sqlConnection.prepareStatement(queryString.toString());

		for (int index = 0; index < dataToReplace.size(); index++) {
			System.out.println("Setting data to preprared - " + (index + 1) + " = " + dataToReplace.get(index));
			statement.setString(index + 1, dataToReplace.get(index));
		}

		System.out.println("Query - After the preparation: " + statement.toString());

		ResultSet resultSet = statement.executeQuery();
		// ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

		data = new String[numForAllocation][];

		int i = 0;
		while (resultSet.next()) {
			//for(int x = 1; x < resultSetMetaData.getColumnCount(); x ++)
			//	System.out.println("Column: " + x + " = " + resultSet.getObject(x));

			data[i] = new String[fields.size()];
			for (int j = 0; j < fields.size(); j++) {
				data[i][j] = resultSet.getString(j + 1);
			}

			i++;
			// System.out.println("---------------------------");
		}

		resultSet.close();

		notifyAllObservers();
		return true;
	}

	private int getRowCount(ArrayList<String> searchRec) throws SQLException {
		ArrayList<String> dataToReplace = new ArrayList<>(searchRec.size());
		StringBuilder queryString = new StringBuilder("SELECT COUNT(*) AS \"count\" FROM ").append(TABLE_NAME)
				.append(" WHERE ");

		boolean firstTime = true;
		for (String currentQueryPart : searchRec) {
			String[] currentPart = currentQueryPart.split("###");
			dataToReplace.add(currentPart[2]);

			if (currentPart.length == 4) {
				if (firstTime) {
					queryString.append(currentPart[0]).append(" ").append(currentPart[1]).append(" ").append("?")
							.append(" ").append(currentPart[3]).append(" ");
				} else
					queryString.append(currentPart[3]).append(" ").append(currentPart[0]).append(" ")
							.append(currentPart[1]).append(" ").append("?").append(" ");
			} else {
				queryString.append(currentPart[0]).append(" ").append(currentPart[1]).append(" ").append("?")
						.append(" ");
			}

			firstTime = false;
		}

		queryString.append(";");
		System.out.println("Query - Count: " + queryString.toString());
		PreparedStatement statement = sqlConnection.prepareStatement(queryString.toString());

		for (int index = 0; index < dataToReplace.size(); index++)
			statement.setString(index + 1, dataToReplace.get(index));

		ResultSet resultSet = statement.executeQuery();
		resultSet.next();

		int count = resultSet.getInt("count");
		resultSet.close();

		return count;
	}
	*/
	
	// >>>>>>> ATTENTION <<<<<<<
	
	// I'VE USED THE PREPARED STATEMENT, BUT FOR SOME REASON IT DOES NOT WORK
	// SO I HAD TO MANUALLY MAKE FUNCTION TO PROTECT AGAINST THE SQL INJECTION
	// THE PREPARED STATEMENT WAY IS CURRENTLY DISABLED (The commented functions above)

	public boolean filterFind(ArrayList<String> searchRec) throws SQLException {
		System.out.println("Received data: " + searchRec);

		StringBuilder queryString = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE ");

		boolean firstTime = true;
		for (String currentQueryPart : searchRec) {
			String[] currentPart = currentQueryPart.split("###");

			if (currentPart.length == 4) {
				if (firstTime) {
					queryString.append(currentPart[0]).append(" ").append(currentPart[1]).append(" '")
							.append(SQLUtilities.escapeString(currentPart[2], true)).append("' ").append(currentPart[3])
							.append(" ");
				} else
					queryString.append(currentPart[3]).append(" ").append(currentPart[0]).append(" ")
							.append(currentPart[1]).append(" '").append(SQLUtilities.escapeString(currentPart[2], true))
							.append("' ");
			} else {
				queryString.append(currentPart[0]).append(" ").append(currentPart[1]).append(" '")
						.append(SQLUtilities.escapeString(currentPart[2], true)).append("' ");
			}

			firstTime = false;
		}

		queryString.append(";");

		System.out.println("Query select: " + queryString.toString());
		int numForAllocation = getRowCount(searchRec);

		Statement statement = sqlConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(queryString.toString());

		data = new String[numForAllocation][];

		int i = 0;
		while (resultSet.next()) {
			data[i] = new String[fields.size()];
			for (int j = 0; j < fields.size(); j++) {
				data[i][j] = resultSet.getString(j + 1);
			}

			i++;
		}

		resultSet.close();

		notifyAllObservers();
		return true;
	}

	private int getRowCount(ArrayList<String> searchRec) throws SQLException {
		StringBuilder queryString = new StringBuilder("SELECT COUNT(*) AS \"count\" FROM ").append(TABLE_NAME)
				.append(" WHERE ");

		boolean firstTime = true;
		for (String currentQueryPart : searchRec) {
			String[] currentPart = currentQueryPart.split("###");

			if (currentPart.length == 4) {
				if (firstTime) {
					queryString.append(currentPart[0]).append(" ").append(currentPart[1]).append(" ")
							.append(SQLUtilities.escapeString(currentPart[2], true)).append(" ").append(currentPart[3])
							.append(" ");
					firstTime = false;
				} else
					queryString.append(currentPart[3]).append(" ").append(currentPart[0]).append(" ")
							.append(currentPart[1]).append(" '").append(SQLUtilities.escapeString(currentPart[2], true))
							.append("' ");
			} else {
				queryString.append(currentPart[0]).append(" ").append(currentPart[1]).append(" '")
						.append(SQLUtilities.escapeString(currentPart[2], true)).append("' ");
			}

			firstTime = false;
		}

		queryString.append(";");
		System.out.println("Query - Count: " + queryString.toString());

		Statement statement = sqlConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(queryString.toString());
		resultSet.next();

		int count = resultSet.getInt("count");
		resultSet.close();

		return count;
	}
}