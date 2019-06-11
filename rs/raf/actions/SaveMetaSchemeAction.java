package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import rs.raf.settings.Settings;
import rs.raf.utilities.AcceleratorHelper;
import rs.raf.utilities.FileUtilities;
import rs.raf.utilities.MetaSchemeUtils;
import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class SaveMetaSchemeAction extends InfViewAbstractAction {
	public SaveMetaSchemeAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('s'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_SAVE));
		putValue(NAME, "Save metascheme");
		putValue(SHORT_DESCRIPTION, "Save metascheme");
	}

	public void actionPerformed(ActionEvent arg0) {
		String tmpFilePath = System.getenv("LOCALAPPDATA") + "/_inf_view_tmp.json";
		clearTmpFile(tmpFilePath);

		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(new File(tmpFilePath));
			printWriter.println(MetaSchemeUtils.serialize());
			printWriter.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			clearTmpFile(tmpFilePath);
		}

		if (MainFrame.getInstance().getMetaMetaSchemeLocation().length() == 0
				|| !(new File(MainFrame.getInstance().getMetaMetaSchemeLocation()).exists())) {
			MainFrame.getInstance().getExceptionsManager().errorNoMetascheme();
			clearTmpFile(tmpFilePath);
			return;
		}

		if (!MetaSchemeUtils.validate(MainFrame.getInstance().getMetaMetaSchemeLocation(), tmpFilePath)) {
			clearTmpFile(tmpFilePath);
			return;
		}

		JFileChooser folder = new FileUtilities.FileChooserBuilder().withTitle("Select Folder to save the Meta Scheme")
				.withCurrentDirectory(null).withFilter("JSON File (*.json)", "json").withNoAcceptAllFileFilterUsed()
				.withNoMultiSelectFiles().withOnlySelectDirectories().build();

		if (folder.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String path = folder.getSelectedFile().getPath();
			String fileName = JOptionPane.showInputDialog("Input file name");

			if (fileName.length() == 0) {
				MainFrame.getInstance().getExceptionsManager().errorValidFileName();
				clearTmpFile(tmpFilePath);
				return;
			}

			if (!fileName.endsWith(".json")) {
				MainFrame.getInstance().getExceptionsManager().errorOnlyAddJsonFiles();
				clearTmpFile(tmpFilePath);
				return;
			}

			try {
				MetaSchemeUtils.serialize(path + "/" + fileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			clearTmpFile(tmpFilePath);
		}
	}

	private void clearTmpFile(String tmpFilePath) {
		File tmpFile = new File(tmpFilePath);

		if (tmpFile != null) {
			if (tmpFile.exists())
				tmpFile.delete();
		}
	}
}