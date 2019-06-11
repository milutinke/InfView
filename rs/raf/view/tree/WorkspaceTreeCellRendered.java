package rs.raf.view.tree;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import rs.raf.actions.InfViewAbstractAction;
import rs.raf.model.Entities;
import rs.raf.model.Entity;
import rs.raf.model.Packet;
import rs.raf.model.Relation;
import rs.raf.model.Relations;
import rs.raf.model.Resource;
import rs.raf.model.Workspace;
import rs.raf.settings.Settings;

@SuppressWarnings("serial")
public class WorkspaceTreeCellRendered extends DefaultTreeCellRenderer {
	public WorkspaceTreeCellRendered() {

	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		if (value instanceof Workspace) {
			setIcon(InfViewAbstractAction.getIcon(Settings.ICON_WORKSPACE));
		} else if (value instanceof Resource) {
			setIcon(InfViewAbstractAction.getIcon(Settings.ICON_RESOURCE));
		} else if (value instanceof Packet) {
			setIcon(InfViewAbstractAction.getIcon(Settings.ICON_PACKET));
		} else if (value instanceof Entities || value instanceof Entity) {
			setIcon(InfViewAbstractAction.getIcon(Settings.ICON_ENTITY));
		} else if (value instanceof Relations || value instanceof Relation) {
			setIcon(InfViewAbstractAction.getIcon(Settings.ICON_RELATION));
		}

		return this;
	}
}