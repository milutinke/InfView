package rs.raf.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UIFileInterface {
	public void readHeader() throws IOException, SQLException;

	public boolean fetchNextBlock() throws IOException, SQLException;

	public boolean addRecord(ArrayList<String> record) throws IOException, SQLException;

	public boolean updateRecord(ArrayList<String> record) throws IOException, SQLException;

	public int[] findRecord(ArrayList<String> searchRec, Boolean all, Boolean fromStart);

	public boolean deleteRecord(ArrayList<String> searchRec);
}
