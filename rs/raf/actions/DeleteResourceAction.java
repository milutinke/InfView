package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

import rs.raf.model.InfViewNode;
import rs.raf.model.Resource;
import rs.raf.settings.Settings;
import rs.raf.utilities.NodeHelper;
import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class DeleteResourceAction extends InfViewAbstractAction {
	public DeleteResourceAction() {
		putValue(SMALL_ICON, getIcon(Settings.ICON_DELETE));
		putValue(NAME, "Close Resource");
		putValue(SHORT_DESCRIPTION, "Close Resource");
	}

	public void actionPerformed(ActionEvent arg0) {
		InfViewNode selectedNode = NodeHelper.getLastSelectedComponentSafe();

		if (selectedNode == null)
			return;

		if (selectedNode != null && selectedNode instanceof Resource) {
			if (((Resource) selectedNode).getSqlConnection() != null) {
				try {
					((Resource) selectedNode).getSqlConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			MainFrame.getInstance().getRightPanel().getTopPanel().removeAll();
			MainFrame.getInstance().getRightPanel().getStatePanel().setDefaultState();

			((Resource) selectedNode).removeFromParent();

			MainFrame.getInstance().setCurrentResource(null);
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
			return;
		}
	}
}