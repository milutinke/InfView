package rs.raf.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import rs.raf.model.DBFile;
import rs.raf.model.Entities;
import rs.raf.model.Entity;
import rs.raf.model.INDFile;
import rs.raf.model.InfViewNode;
import rs.raf.model.Packet;
import rs.raf.model.Relation;
import rs.raf.model.Relations;
import rs.raf.model.Resource;
import rs.raf.model.SEKFile;
import rs.raf.model.SERFile;
import rs.raf.view.frames.MainFrame;
import rs.raf.view.panels.FileView;
import rs.raf.view.panels.RightPanel;
import rs.raf.view.tree.InfViewTree;

public class MouseListener extends MouseAdapter {
	public void mousePressed(MouseEvent event) {
		InfViewTree tree = MainFrame.getInstance().getTree();
		InfViewNode selectedNode = (InfViewNode) tree.getLastSelectedPathComponent();

		if (selectedNode != null) {
			// Update current selected Resource
			if (selectedNode instanceof Entity) {
				MainFrame.getInstance().setCurrentResource((Resource) selectedNode.getParent().getParent().getParent());
			} else if (selectedNode instanceof Relation) {
				MainFrame.getInstance().setCurrentResource((Resource) selectedNode.getParent().getParent().getParent());
			} else if (selectedNode instanceof Entities) {
				MainFrame.getInstance().setCurrentResource((Resource) selectedNode.getParent().getParent());
			} else if (selectedNode instanceof Relations) {
				MainFrame.getInstance().setCurrentResource((Resource) selectedNode.getParent().getParent());
			} else if (selectedNode instanceof Packet) {
				MainFrame.getInstance().setCurrentResource((Resource) selectedNode.getParent());
			}

			// Open entity tab
			if (event.getClickCount() == 2) {
				((InfViewNode) selectedNode).notifyAllObservers();

				if (!(selectedNode instanceof Entity))
					return;

				int tabIndex = findTabByName(selectedNode.getName(), MainFrame.getInstance().getRightPanel());

				if (tabIndex != -1) {
					MainFrame.getInstance().getRightPanel().getTopPanel().setSelectedIndex(tabIndex);
					return;
				}

				MainFrame.getInstance().getRightPanel().getStatePanel().setLoadingState();

				String name = ((Entity) selectedNode).getName();
				String file = ((Entity) selectedNode).getFile();
				String location = ((Entity) selectedNode).getLocation();
				FileView view = null;

				if (file != null) {
					if (file.contains(".ser")) {
						view = new FileView(new SERFile(name, location, file), selectedNode);
					} else if (file.contains(".sek")) {
						view = new FileView(new SEKFile(name, location, file), selectedNode);
					} else if (file.contains(".ind")) {
						view = new FileView(new INDFile(name, location, file), selectedNode);
					}
				} else {
					DBFile db = new DBFile(name);
					Packet packet = (Packet) selectedNode.getParent().getParent();
					Resource resource = (Resource) packet.getParent();

					db.setConnector(resource.getSqlConnection());
					view = new FileView(db, selectedNode);
				}

				if (view == null) {
					MainFrame.getInstance().getRightPanel().getStatePanel().setDefaultState();
					return;
				}

				MainFrame.getInstance().getRightPanel().getTopPanel().addTab(selectedNode.getName(), selectedNode,
						view);

				if (MainFrame.getInstance().getCurrentResource().getType().equals("sql")
						|| MainFrame.getInstance().getCurrentResource().getType().equals("file-relational"))
					MainFrame.getInstance().getRightPanel().getStatePanel().setRelationViewState((Entity) selectedNode);

				return;
			}
		}
	}

	public int findTabByName(String title, RightPanel rightPanel) {
		int tabCount = rightPanel.getTopPanel().getTabCount();

		for (int index = 0; index < tabCount; index++) {
			String tabTitle = rightPanel.getTopPanel().getTitleAt(index);

			if (tabTitle.trim().equalsIgnoreCase(title))
				return index;
		}

		return -1;
	}
}