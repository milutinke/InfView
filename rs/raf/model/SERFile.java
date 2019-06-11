package rs.raf.model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.JOptionPane;

import rs.raf.actions.SaveFoundedRecInFile;
import rs.raf.utilities.FileUtilities;

public class SERFile extends UIAbstractFile {
	public SERFile(String name, String location, String file) {
		super(name, location, file);
	}

	@Override
	public boolean fetchNextBlock() throws IOException {
		RandomAccessFile afile = new RandomAccessFile(path + File.separator + fileName, "r");
		FILE_SIZE = afile.length();
		RECORD_NUM = (long) Math.ceil((FILE_SIZE * 1.0000) / (RECORD_SIZE * 1.0000));
		BLOCK_NUM = (int) (RECORD_NUM / BLOCK_FACTOR) + 1;

		if (FILE_POINTER / RECORD_SIZE + BLOCK_FACTOR > RECORD_NUM)
			BUFFER_SIZE = (int) (RECORD_NUM - FILE_POINTER / RECORD_SIZE) * RECORD_SIZE;
		else
			BUFFER_SIZE = (int) (RECORD_SIZE * BLOCK_FACTOR);

		buffer = new byte[BUFFER_SIZE];
		data = new String[(int) BUFFER_SIZE / RECORD_SIZE][];

		afile.seek(FILE_POINTER);
		afile.read(buffer);
		String contentS = new String(buffer);

		ArrayList<String> lines = FileUtilities.loadFile(path + fileName + ".del");

		for (int i = 0; i < BUFFER_SIZE / RECORD_SIZE; i++) {
			String line = contentS.substring(i * RECORD_SIZE, i * RECORD_SIZE + RECORD_SIZE);

			// Skip deleted lines
			if (lines != null) {
				boolean skip = false;

				for (String deletedLines : lines) {
					if (line.contains(deletedLines)) {
						skip = true;
						break;
					}
				}

				if (skip) {
					continue;
				}
			}

			data[i] = new String[fields.size()];
			int k = 0;
			for (int j = 0; j < fields.size(); j++) {
				String field = null;
				field = line.substring(k, k + fields.get(j).getFieldLength());
				data[i][j] = field;
				k = k + fields.get(j).getFieldLength();
			}

		}

		FILE_POINTER = afile.getFilePointer();
		afile.close();

		notifyAllObservers();
		return true;
	}

	public boolean fetchNextBlockSearch() throws IOException {

		RandomAccessFile afile = new RandomAccessFile(path + File.separator + fileName, "r");
		FILE_SIZE = afile.length();
		RECORD_NUM = (long) Math.ceil((FILE_SIZE * 1.0000) / (RECORD_SIZE * 1.0000));
		BLOCK_NUM = (int) (RECORD_NUM / BLOCK_FACTOR) + 1;

		if (FILE_POINTER / RECORD_SIZE + BLOCK_FACTOR > RECORD_NUM) {
			BUFFER_SIZE = (int) (RECORD_NUM - FILE_POINTER / RECORD_SIZE) * RECORD_SIZE;

		} else
			BUFFER_SIZE = (int) (RECORD_SIZE * BLOCK_FACTOR);

		buffer = new byte[BUFFER_SIZE];
		data = new String[(int) BUFFER_SIZE / RECORD_SIZE][];

		afile.seek(FILE_POINTER);
		afile.read(buffer);
		String contentS = new String(buffer);

		for (int i = 0; i < BUFFER_SIZE / RECORD_SIZE; i++) {

			String line = contentS.substring(i * RECORD_SIZE, i * RECORD_SIZE + RECORD_SIZE);
			data[i] = new String[fields.size()];

			int k = 0;
			for (int j = 0; j < fields.size(); j++) {
				String field = null;
				field = line.substring(k, k + fields.get(j).getFieldLength());
				data[i][j] = field;
				k = k + fields.get(j).getFieldLength();
			}

		}

		FILE_POINTER = afile.getFilePointer();

		afile.close();

		notifyAllObservers();
		return true;
	}

	@Override
	public int[] findRecord(ArrayList<String> searchRec, Boolean all, Boolean fromStart) {
		if (fromStart)
			FILE_POINTER = 0;

		boolean found = false;
		int position = -1;
		int[] positions = new int[(int) (FILE_SIZE / 94)];
		String[][] dataFound = new String[(int) (FILE_SIZE / 94)][fields.size()];

		int i = 0;
		for (i = 0; i < positions.length; i++)
			positions[i] = position;

		i = 0;
		int k = 0;

		while (FILE_POINTER < FILE_SIZE) {

			try {
				fetchNextBlockSearch();
			} catch (IOException e) {
				e.printStackTrace();
			}

			for (int row = 0; row < data.length; row++) {
				if (isRowEqual(data[row], searchRec)) {
					found = true;
					position = row;
					positions[i] = position;

					for (int j = 0; j < fields.size(); j++)
						dataFound[k][j] = data[row][j];

					k++;
					i++;

					if (!all)
						return positions;
				}

			}

		}

		data = dataFound;
		notifyAllObservers();

		int answer = 0;
		if (found) {
			answer = JOptionPane.showConfirmDialog(null, "Would you like to save results?", "Optional",
					JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				SaveFoundedRecInFile saveRecord = new SaveFoundedRecInFile(data);
				saveRecord.perform();
			}
		}
		return positions;

	}

	private boolean isRowEqual(String[] aData, ArrayList<String> searchRec) {
		boolean result = true;

		for (int column = 0; column < fields.size(); column++) {
			if (!searchRec.get(column).trim().equals("")) {
				if (!aData[column].trim().equals(searchRec.get(column).trim())) {
					result = false;
					return result;
				}
			}

		}

		return result;

	}

	public static String[][] loadRecycleBin(Entity entity) throws IOException {
		ArrayList<String> lines = FileUtilities
				.loadFile(entity.getLocation() + entity.getFile().replaceAll(".ser", ".txt").trim() + ".del");
		ArrayList<Attribute> attributes = Collections.list((Enumeration<Attribute>) entity.children());

		if (lines == null) {
			System.out.println("Null recycle bin");
			return null;
		}

		String[][] recycleBinTableData = null;
		recycleBinTableData = new String[lines.size()][];

		int i = 0;
		for (String line : lines) {
			recycleBinTableData[i] = new String[attributes.size()];

			int k = 0;
			for (int j = 0; j < attributes.size(); j++) {
				String field = line.substring(k, k + (Integer) attributes.get(j).getLength());
				recycleBinTableData[i][j] = field;
				k = k + (Integer) attributes.get(j).getLength();
			}

			i++;
		}

		return recycleBinTableData;
	}
}
