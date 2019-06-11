package rs.raf.utilities;

import java.util.Enumeration;

import rs.raf.model.InfViewNode;
import rs.raf.view.frames.MainFrame;
import rs.raf.view.tree.InfViewTree;

public class NodeHelper {
	public static InfViewNode getLastSelectedComponentSafe() {
		InfViewTree tree = MainFrame.getInstance().getTree();

		if (tree == null) {
			MainFrame.getInstance().getExceptionsManager().errorThereIsNoTree();
			return null;
		}

		if (tree.getLastSelectedPathComponent() == null) {
			MainFrame.getInstance().getExceptionsManager().errorNoSelectedComponent();
			return null;
		}

		return (InfViewNode) tree.getLastSelectedPathComponent();
	}

	public static Boolean isDuplicate(InfViewNode root, InfViewNode newNode) {
		@SuppressWarnings("unchecked")
		Enumeration<InfViewNode> children = (Enumeration<InfViewNode>) root.children();

		if (children != null) {
			while (children.hasMoreElements()) {
				InfViewNode node = (InfViewNode) children.nextElement();

				if (node.getName().equals(newNode.getName()))
					return true;
			}
		}

		return false;
	}

	public static InfViewNode findNode(InfViewNode root, Class<?> _class_, String name) {
		if (root == null)
			return null;

		InfViewNode found = null;

		@SuppressWarnings("unchecked")
		Enumeration<InfViewNode> children = (Enumeration<InfViewNode>) root.children();

		if (children != null) {
			while (children.hasMoreElements()) {
				InfViewNode node = (InfViewNode) children.nextElement();

				if (name.length() != 0) {
					if (node.getName().equalsIgnoreCase(name)) {
						if (_class_ != null) {
							if (node.getClass().equals(_class_)) {
								found = node;
								return found;
							}
						} else {
							found = node;
							return found;
						}
					}
				} else {
					if (_class_ != null) {
						if (node.getClass().equals(_class_)) {
							found = node;
							return found;
						}
					}
				}

				found = findNode(node, _class_, name);
			}
		}

		return found;
	}

	public static String getAllRemovedChlindren(InfViewNode root, String text) {
		String finalText = root + "~";

		@SuppressWarnings("unchecked")
		Enumeration<InfViewNode> children = (Enumeration<InfViewNode>) root.children();

		if (children != null) {
			while (children.hasMoreElements()) {
				InfViewNode node = (InfViewNode) children.nextElement();
				finalText += getAllRemovedChlindren(node, text + "~" + node);
			}
		}

		return finalText;
	}

	public static void closeDeletedNodesTabs(String removedNodes) {
		if (removedNodes.length() > 0) {
			String[] removedNodesList = removedNodes.split("~");

			for (String removedNode : removedNodesList) {
				for (int openedTabs = 0; openedTabs < MainFrame.getInstance().getRightPanel().getTopPanel()
						.getTabCount(); openedTabs++) {
					String title = MainFrame.getInstance().getRightPanel().getTopPanel().getTitleAt(openedTabs).trim();

					if (title == null)
						continue;

					if (removedNode.trim().equalsIgnoreCase(title))
						MainFrame.getInstance().getRightPanel().getTopPanel().removeTabAt(openedTabs);
				}
			}
		}
	}

	public static boolean isNameReserved(String name, InfViewNode tmp, InfViewNode root) {
		@SuppressWarnings("unchecked")
		Enumeration<InfViewNode> children = (Enumeration<InfViewNode>) root.children();
		if (children != null) {
			while (children.hasMoreElements()) {
				InfViewNode child = (InfViewNode) children.nextElement();
				if (!tmp.equals(child))
					if (child.getName().equals(name))
						return true;
			}
		}

		return false;
	}
}
