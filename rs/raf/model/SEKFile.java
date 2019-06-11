package rs.raf.model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import rs.raf.actions.SaveFoundedRecInFile;

public class SEKFile extends UIAbstractFile {

	public SEKFile(String name, String location, String file) {
		super(name, location, file);
	}

	/*
	 * public SEKFile(String path, String headerName, boolean directory) {
	 * super(path, headerName, directory);
	 * 
	 * }
	 * 
	 * public SEKFile() { super(); }
	 */

	public boolean fetchNextBlock() throws IOException {

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

		for (int i = 0; i < BUFFER_SIZE / RECORD_SIZE; i++) { // OD 0 DO BROJA UCITANIH REDOVA

			String line = contentS.substring(i * RECORD_SIZE, i * RECORD_SIZE + RECORD_SIZE); // NOVI RED
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

		// fireUpdateBlockPerformed();

		notifyAllObservers();
		System.out.println(this.getObservers());

		return true;

	}

	@Override
	public int[] findRecord(ArrayList<String> searchRec, Boolean all, Boolean fromStart) {
		if (fromStart)
			FILE_POINTER = 0;
		boolean founded = false;
		boolean stopSearch = false;
		int position = -1;
		int[] positions = new int[(int) (FILE_SIZE / RECORD_SIZE)];
		String[][] dataFounded = new String[(int) (FILE_SIZE / RECORD_SIZE)][fields.size()];

		int i = 0;
		for (i = 0; i < positions.length; i++) {
			positions[i] = position;
		}
		i = 0;
		int k = 0;

		while (FILE_POINTER < FILE_SIZE) {

			try {
				fetchNextBlock();
			} catch (IOException e) {
				e.printStackTrace();
			}

			for (int red = 0; red < data.length; red++) {

				if (isRowEqual(data[red], searchRec)) {
					founded = true;
					position = red;
					positions[i] = position;

					for (int j = 0; j < fields.size(); j++) {
						dataFounded[k][j] = data[red][j];
					}

					k++;
					i++;
					if (!all)
						return positions;

				}

				if (isRowGreater(data[red], searchRec)) {
					stopSearch = true;
					break;

				}

			}

			if (stopSearch)
				break;
		}

		data = dataFounded;
		// fireUpdateBlockPerformed();
		notifyAllObservers();
		int n = 0;
		if (founded) {
			n = JOptionPane.showConfirmDialog(null, "Would you like to save results?", "Optional",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				SaveFoundedRecInFile saveRec = new SaveFoundedRecInFile(data);
				saveRec.perform();
			}
		}
		return positions;

	}

	private boolean isRowGreater(String[] aData, ArrayList<String> searchRec) {
		boolean result = true;
		int noPK = 0;
		boolean prev = true;

		for (int i = 0; i < fields.size(); i++) {
			if (!searchRec.get(i).trim().equals("") && !fields.get(i).isFieldPK()) {
				return false;
			}
			if (fields.get(i).isFieldPK())
				noPK++;

		}

		for (int col = 0; col < fields.size(); col++) {

			if (!searchRec.get(col).trim().equals("")) {

				if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim()) > 0 && col < noPK - 1) {
					return true;

				} else if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim()) != 0 && col < noPK - 1) {
					result = false;
					prev = false;
				} else if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim()) <= 0) {
					result = false;

				} else if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim()) > 0 && prev
						&& col == (noPK - 1)) {
					result = true;
				}
			}
		}

		return result;
	}

	private boolean isRowEqual(String[] aData, ArrayList<String> searchRec) {
		boolean result = true;

		for (int col = 0; col < fields.size(); col++) {
			if (!searchRec.get(col).trim().equals("")) {
				if (!aData[col].trim().equals(searchRec.get(col).trim())) {
					result = false;
					return result;
				}
			}

		}

		return result;

	}
}
