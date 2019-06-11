package rs.raf.actions;

import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;

import rs.raf.view.frames.MainFrame;

public class SaveFoundedRecInFile {
	private String[][] data;

	public SaveFoundedRecInFile(String[][] data) {
		this.data = data;
	}

	public void perform() {
		JFileChooser jfc = new JFileChooser();
		jfc.showOpenDialog(MainFrame.getInstance());

		File f = jfc.getSelectedFile();
		try {
			FileWriter fw = new FileWriter(f);
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					if (data[i][j].isEmpty()) {
						fw.flush();
						fw.close();
						fw.close();

						break;
					}
					fw.write(data[i][j]);
				}
				fw.write(System.getProperty("line.separator"));
			}

		} catch (Exception e) {

		}

	}

}