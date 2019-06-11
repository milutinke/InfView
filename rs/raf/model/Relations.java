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

public class Relations extends Observable implements InfViewNode, Cloneable {
	private String name;
	private ArrayList<Relation> children;
	private ArrayList<Observer> observers;
	private InfViewNode parent = null;

	public Relations(String name) {
		this.name = name;

		this.children = new ArrayList<Relation>();
		this.observers = new ArrayList<Observer>();
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		child.setParent(this);
		children.add(index, (Relation) child);

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
		children.remove((Relation) node);
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
	public Enumeration<Relation> children() {
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
	public void addChild(InfViewNode child) {
		child.setParent(this);
		children.add((Relation) child);

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

	public void setName(String name) {
		this.name = name;
		notifyAllObservers();
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}