package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.SwingUtilities;

import rs.raf.model.Packet;
import rs.raf.model.Resource;
import rs.raf.model.Workspace;
import rs.raf.utilities.MetaSchemeUtils;
import rs.raf.utilities.NodeHelper;
import rs.raf.view.frames.EditorFrame;
import rs.raf.view.frames.MainFrame;
import rs.raf.view.tree.WorkspaceModel;

@SuppressWarnings("serial")
public class SaveAndValidateAction extends InfViewAbstractAction {
	public SaveAndValidateAction() {
		putValue(NAME, "SAVE");
		putValue(SHORT_DESCRIPTION, "SAVE");
	}

	public void actionPerformed(ActionEvent arg0) {
		String tmpFilePath = System.getenv("LOCALAPPDATA") + "/_inf_view_tmp.json";
		clearTmpFile(tmpFilePath);

		if (MainFrame.getInstance().getMetaMetaSchemeLocation().length() == 0
				|| !(new File(MainFrame.getInstance().getMetaMetaSchemeLocation()).exists())) {
			MainFrame.getInstance().getExceptionsManager().errorNoMetascheme();
			clearTmpFile(tmpFilePath);
			return;
		}

		Packet cloned = EditorFrame.getClone();
		Packet clonedOriginal = EditorFrame.getClonedOriginal();

		if (cloned == null || clonedOriginal == null) {
			MainFrame.getInstance().getExceptionsManager().errorClonedPackets();
			return;
		}

		// Get resource from the packet
		Workspace workspace = ((Workspace) ((WorkspaceModel) MainFrame.getInstance().getTree().getModel()).getRoot());
		Resource resource = (Resource) workspace.getChildAt(0);

		// Check if the requested resource is present in the tree
		if (resource == null) {
			MainFrame.getInstance().getExceptionsManager().errorResourceNotFound();
			return;
		}

		// Find the packet in tree via name
		Packet foundPacket = (Packet) NodeHelper.findNode(resource, Packet.class, clonedOriginal.getName());

		// Check if the requested packet is present in the tree
		if (foundPacket == null) {
			MainFrame.getInstance().getExceptionsManager().errorPacketNotPresent();
			return;
		}

		// Remove requested packet
		foundPacket.removeFromParent();

		// Insert cloned packet
		resource.addChild(cloned);

		// Validate

		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(new File(tmpFilePath));
			printWriter.println(MetaSchemeUtils.serialize());
			printWriter.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			clearTmpFile(tmpFilePath);
			MainFrame.getInstance().getExceptionsManager().errorTmpFileCreation();
		}

		if (!MetaSchemeUtils.validate(MainFrame.getInstance().getMetaMetaSchemeLocation(), tmpFilePath)) {
			clearTmpFile(tmpFilePath);

			// Find the packet in tree via name
			Packet foundPacketAfterFail = (Packet) NodeHelper.findNode(resource, Packet.class, cloned.getName());

			// Check if the requested packet is present in the tree
			if (foundPacketAfterFail == null) {
				MainFrame.getInstance().getExceptionsManager().errorClonedPacketNotPresent();
				return;
			}

			// Remove requested packet
			foundPacketAfterFail.removeFromParent();

			// Insert the original back
			resource.addChild(clonedOriginal);

			System.out.println("Fail!");
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
			return;
		}

		clearTmpFile(tmpFilePath);
		MainFrame.getInstance().getExceptionsManager().successMetaschemeSave();
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
	}

	private void clearTmpFile(String tmpFilePath) {
		File tmpFile = new File(tmpFilePath);

		if (tmpFile != null) {
			if (tmpFile.exists())
				tmpFile.delete();
		}
	}
}
