package rs.raf.model;

import javax.swing.tree.MutableTreeNode;

public interface InfViewNode extends MutableTreeNode {
	public void notifyAllObservers();

	public String getName();

	public void setName(String name);

	public void addChild(InfViewNode child);
	
	
}