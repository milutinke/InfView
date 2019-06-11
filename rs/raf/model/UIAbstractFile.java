package rs.raf.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.event.EventListenerList;

import rs.raf.controller.UpdateBlockEvent;
import rs.raf.controller.UpdateBlockListener;

public class UIAbstractFile extends Entity implements UIFileInterface {
	public UIAbstractFile(String name, String location, String file) {
		super(name, location, file);
		this.path = location;
		this.headerName = file;
		this.fileName = file;
	}

	static public final int BROWSE_MODE = 1;
	static public final int ADD_MODE = 2;
	static public final int UPDATE_MODE = 3;
	static public final int DELETE_MODE = 4;
	static public final int FIND_MODE = 5;

	protected long BLOCK_FACTOR = 20;
	protected int RECORD_SIZE = 0;
	protected int BUFFER_SIZE = 0;
	protected int BLOCK_NUM = 0;
	protected long RECORD_NUM = 0;
	protected long FILE_POINTER = 0;
	protected long FILE_SIZE = 0;
	protected int MODE = UIAbstractFile.BROWSE_MODE;

	protected String path;
	protected String headerName;
	protected String fileName;
	protected boolean directory;

	ArrayList<UIFileField> fields = new ArrayList<UIFileField>();
	protected byte[] buffer;
	protected String[][] data = null;

	EventListenerList listenerBlockList = new EventListenerList();
	UpdateBlockEvent updateBlockEvent = null;

	public void readHeader() throws IOException, SQLException {
		String delimiter = "\\/";
		ArrayList<String> headRecord = new ArrayList<String>();
		RandomAccessFile headerFile = null;
		Object data[] = null;

		headerFile = new RandomAccessFile(path + File.separator + headerName, "r");
		while (headerFile.getFilePointer() < headerFile.length())
			headRecord.add(headerFile.readLine());

		headerFile.close();

		@SuppressWarnings("unused")
		int row = 0;

		for (String record : headRecord) {
			StringTokenizer tokens = new StringTokenizer(record, delimiter);

			int columns = tokens.countTokens();
			data = new String[columns];

			int column = 0;
			while (tokens.hasMoreTokens()) {
				data[column] = tokens.nextToken();

				if (data[column].equals("field")) {
					String fieldName = tokens.nextToken();
					String fieldType = tokens.nextToken();
					int fieldLenght = Integer.parseInt(tokens.nextToken());

					RECORD_SIZE = RECORD_SIZE + fieldLenght;
					boolean fieldPK = new Boolean(tokens.nextToken());
					UIFileField field = new UIFileField(fieldName, fieldType, fieldLenght, fieldPK);

					fields.add(field);
				} else if (data[column].equals("path")) {
					fileName = tokens.nextToken();
				}
			}

			row++;

		}

		RECORD_SIZE = RECORD_SIZE + 2;

		RandomAccessFile afile = new RandomAccessFile(path + File.separator + fileName, "r");
		FILE_SIZE = afile.length();
		RECORD_NUM = (long) Math.ceil((FILE_SIZE * 1.0000) / (RECORD_SIZE * 1.0000));
		BLOCK_NUM = (int) (RECORD_NUM / BLOCK_FACTOR) + 1;
		afile.close();
	}

	public void sort() throws IOException {
		byte[] sort_buffer = new byte[(int) (RECORD_SIZE * (RECORD_NUM + 1))];
		String[][] sort_data = new String[(int) RECORD_NUM + 1][];

		RandomAccessFile afile = null;
		try {
			afile = new RandomAccessFile(path + File.separator + fileName, "r");
			afile.seek(0);
			afile.read(sort_buffer);
			afile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String buffer = new String(sort_buffer);

		for (int row = 0; row < RECORD_NUM; row++) {

			String line = buffer.substring(row * RECORD_SIZE, row * RECORD_SIZE + RECORD_SIZE);

			sort_data[row] = new String[fields.size()];
			int k = 0;
			for (int b = 0; b < fields.size(); b++) {
				String field = null;
				field = line.substring(k, k + fields.get(b).getFieldLength());

				sort_data[row][b] = field;
				k = k + fields.get(b).getFieldLength();
			}

		}

		String first = "";

		for (int c = 0; c < fields.size(); c++) {
			if (fields.get(c).isSort()) {

				for (int d = 0; d < RECORD_NUM - 1; d++) {
					first = sort_data[d][c];
					int indexOfFirst = d;
					for (int j = d + 1; j < RECORD_NUM; j++) {

						boolean equal = true;
						for (int k = 0; k < c; k++) {
							if (fields.get(k).isSort()) {
								if (sort_data[d][k].compareToIgnoreCase(sort_data[j][k]) != 0) {
									equal = false;

								}
							}
						}

						if (equal) {

							if (first.compareToIgnoreCase(sort_data[j][c]) > 0 && fields.get(c).isAscending()) {

								first = sort_data[j][c];
								indexOfFirst = j;
							}

							else if (first.compareToIgnoreCase(sort_data[j][c]) < 0
									&& fields.get(c).isAscending() == false) {
								first = sort_data[j][c];
								indexOfFirst = j;
							}
						}
					}

					sort_data[(int) (RECORD_NUM)] = sort_data[d];
					sort_data[d] = sort_data[indexOfFirst];
					sort_data[indexOfFirst] = sort_data[(int) (RECORD_NUM)];
				}
			}

		}

		data = sort_data;
		notifyAllObservers();
	}

	public void addUpdateBlockListener(UpdateBlockListener l) {
		listenerBlockList.add(UpdateBlockListener.class, l);
	}

	public void removeUpdateBlockListener(UpdateBlockListener l) {
		listenerBlockList.remove(UpdateBlockListener.class, l);
	}

	public void setBLOCK_SIZE(long block_size) {
		BLOCK_FACTOR = block_size;
		BLOCK_NUM = (int) (RECORD_NUM / BLOCK_FACTOR) + 1;
	}

	public static int getADD_MODE() {
		return ADD_MODE;
	}

	public static int getBROWSE_MODE() {
		return BROWSE_MODE;
	}

	public static int getDELETE_MODE() {
		return DELETE_MODE;
	}

	public static int getFIND_MODE() {
		return FIND_MODE;
	}

	public static int getUPDATE_MODE() {
		return UPDATE_MODE;
	}

	public int getBLOCK_NUM() {
		return BLOCK_NUM;
	}

	public long getBLOCK_FACTOR() {
		return BLOCK_FACTOR;
	}

	public byte[] getBlockContent() {
		return buffer;
	}

	public String[][] getData() {
		return data;
	}

	public boolean isDirectory() {
		return directory;
	}

	public ArrayList<UIFileField> getFields() {
		return fields;
	}

	public long getFILE_POINTER() {
		return FILE_POINTER;
	}

	public long getFILE_SIZE() {
		return FILE_SIZE;
	}

	public String getFileName() {
		return fileName;
	}

	public String getHeaderName() {
		return headerName;
	}

	public int getMODE() {
		return MODE;
	}

	public String getPath() {
		return path;
	}

	public long getRECORD_NUM() {
		return RECORD_NUM;
	}

	public int getRECORD_SIZE() {
		return RECORD_SIZE;
	}

	public void setMODE(int mode) {
		MODE = mode;
	}

	public String toString() {
		return fileName;
	}

	@Override
	public boolean fetchNextBlock() throws IOException, SQLException {
		return false;
	}

	@Override
	public boolean addRecord(ArrayList<String> record) throws IOException, SQLException {
		return false;
	}

	@Override
	public boolean updateRecord(ArrayList<String> record) throws IOException, SQLException {
		return false;
	}

	@Override
	public int[] findRecord(ArrayList<String> searchRec, Boolean all, Boolean fromStart) {
		return null;
	}

	@Override
	public boolean deleteRecord(ArrayList<String> searchRec) {
		return false;
	}
}
