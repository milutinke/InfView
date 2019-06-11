package rs.raf.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import rs.raf.view.frames.MainFrame;

public class Entity extends Observable implements InfViewNode, Cloneable {
	private String name;
	private String location;
	private String file;
	private ArrayList<Attribute> children;
	private ArrayList<Observer> observers;
	private InfViewNode parent = null;

	public Entity(String name, String location, String file) {
		this.name = name;
		this.location = location;
		this.file = file;

		this.children = new ArrayList<Attribute>();
		this.observers = new ArrayList<Observer>();
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		child.setParent(this);
		children.add(index, (Attribute) child);

		notifyAllObservers();
	}

	@Override
	public void remove(int index) {
		children.get(index).setParent(null);
		children.remove(index);

		notifyAllObservers();
	}

	@Override
	public void remove(MutableTreeNode node) {
		node.setParent(null);
		children.remove((Attribute) node);

		notifyAllObservers();
	}

	@Override
	public void removeFromParent() {
		if (parent != null) {
			parent.remove(this);
			notifyObservers();
		}
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		this.parent = (InfViewNode) newParent;
	}

	@Override
	public void setUserObject(Object object) {
	}

	@Override
	public Enumeration<Attribute> children() {
		return Collections.enumeration(children);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return children.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return children.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		for (int index = 0; index < children.size(); index++) {
			if (children.get(index).equals((InfViewNode) node))
				return index;
		}

		return -1;
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public void notifyAllObservers() {
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());

		for (Observer observer : observers)
			observer.update(this, null);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		notifyAllObservers();
	}

	@Override
	public void addChild(InfViewNode child) {
		children.add((Attribute) child);
		child.setParent(this);

		notifyAllObservers();
	}

	public void addObserver(Observer observer) {
		if (observers.contains(observer))
			return;

		observers.add(observer);
	}

	public ArrayList<Observer> getObservers() {
		return observers;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getLocation() {
		return location;
	}

	public String getFile() {
		return file;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public boolean hasFile() {
		return file.length() != 0;
	}

	public boolean hasLocation() {
		return location.length() != 0;
	}
}