package rs.raf.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileUtilities {
	@SuppressWarnings("serial")
	public static class FileChooserBuilder extends JFileChooser {
		public FileChooserBuilder() {
			super();
		}

		public FileChooserBuilder withTitle(String title) {
			this.setDialogTitle(title);
			return this;
		}

		public FileChooserBuilder withCurrentDirectory(String workingDirectory) {
			this.setCurrentDirectory(workingDirectory != null ? new File(workingDirectory)
					: this.getFileSystemView().getDefaultDirectory());
			return this;
		}

		public FileChooserBuilder withFilter(String text, String extension) {
			this.addChoosableFileFilter(new FileNameExtensionFilter(text, extension));
			return this;
		}

		public FileChooserBuilder withNoAcceptAllFileFilterUsed() {
			this.setAcceptAllFileFilterUsed(false);
			return this;
		}

		public FileChooserBuilder withNoMultiSelectFiles() {
			this.setMultiSelectionEnabled(false);
			return this;
		}

		public FileChooserBuilder withOnlySelectDirectories() {
			this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			return this;
		}

		public FileChooserBuilder build() {
			return this;
		}
	}
	
	@SuppressWarnings("resource")
	public static ArrayList<String> loadFile(String path) throws IOException {
		if (!(new File(path).exists()))
			return null;

		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
		String line = "";

		while ((line = bufferedReader.readLine()) != null)
			lines.add(line);

		return lines;

	}
}
