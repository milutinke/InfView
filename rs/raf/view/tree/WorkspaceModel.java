package rs.raf.view.tree;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import rs.raf.model.InfViewNode;
import rs.raf.model.Resource;
import rs.raf.model.Workspace;

@SuppressWarnings("serial")
public class WorkspaceModel extends DefaultTreeModel {
	private Resource resource;

	public WorkspaceModel(Workspace node) {
		super((Workspace) node);
	}

	public void addResource(Resource resource) {
		this.resource = resource;
		((Workspace) getRoot()).addChild(resource);
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		InfViewNode node = (InfViewNode) ((InfViewNode) path.getLastPathComponent());

		if (node != null && newValue != null) {
			if (newValue.toString().length() == 0)
				return;

			node.setName(newValue.toString());
			super.valueForPathChanged(path, node);
		}
	}

	public Resource getResource() {
		return resource;
	}

}