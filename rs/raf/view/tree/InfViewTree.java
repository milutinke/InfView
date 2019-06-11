package rs.raf.view.tree;

import javax.swing.JTree;

import rs.raf.controller.MouseListener;
import rs.raf.controller.PopUpListener;
import rs.raf.model.Workspace;

@SuppressWarnings("serial")
public class InfViewTree extends JTree {
	public InfViewTree() {
		setModel(new WorkspaceModel(new Workspace("Workspace")));
		setCellRenderer(new WorkspaceTreeCellRendered());
		setEditable(true);
		addMouseListener(new MouseListener());
		addMouseListener(new PopUpListener());
	}

}