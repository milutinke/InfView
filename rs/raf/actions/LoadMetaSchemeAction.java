package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;

import rs.raf.settings.Settings;
import rs.raf.utilities.AcceleratorHelper;
import rs.raf.utilities.FileUtilities;
import rs.raf.utilities.MetaSchemeUtils;
import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class LoadMetaSchemeAction extends InfViewAbstractAction {
	public LoadMetaSchemeAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('l'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_OPEN));
		putValue(NAME, "Load metascheme");
		putValue(SHORT_DESCRIPTION, "Load metascheme");
	}

	public void actionPerformed(ActionEvent arg0) {
		JFileChooser metaMetaSchemeFileChooser = new FileUtilities.FileChooserBuilder()
				.withTitle("Select Meta Meta Scheme").withCurrentDirectory(null)
				.withFilter("JSON File (*.json)", "json").withNoAcceptAllFileFilterUsed().withNoMultiSelectFiles()
				.build();

		if (metaMetaSchemeFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String metaMetaSchemePath = metaMetaSchemeFileChooser.getSelectedFile().getPath();
			MainFrame.getInstance().setMetaMetaSchemeLocation(metaMetaSchemePath);

			JFileChooser metaSchemeFileChooser = new FileUtilities.FileChooserBuilder().withTitle("Select Meta Scheme")
					.withCurrentDirectory(new File(metaMetaSchemePath).getAbsoluteFile().getParent())
					.withFilter("JSON File (*.json)", "json").withNoAcceptAllFileFilterUsed().withNoMultiSelectFiles()
					.build();

			if (metaSchemeFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				String metaSchemePath = metaSchemeFileChooser.getSelectedFile().getPath();

				if (!MetaSchemeUtils.validate(metaMetaSchemePath, metaSchemePath))
					return;

				MetaSchemeUtils.deSerialize(metaSchemePath);
			}
		}
	}
}