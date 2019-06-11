package rs.raf.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Workspace extends Observable implements InfViewNode {
	private String name;
	private ArrayList<Resource> children;
	private ArrayList<Observer> observers;

	public Workspace(String name) {
		this.name = name;
		this.children = new ArrayList<Resource>();
		this.observers = new ArrayList<Observer>();
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		child.setParent(this);
		children.add(index, (Resource) child);
		notifyAllObservers();
	}

	@Override
	public void remove(int index) {
		children.remove(index);
		notifyAllObservers();
	}

	@Override
	public void remove(MutableTreeNode node) {
		children.remove((Resource) node);
		notifyAllObservers();
	}

	@Override
	public void removeFromParent() {
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
	}

	@Override
	public void setUserObject(Object object) {
	}

	@Override
	public Enumeration<Resource> children() {
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
		return null;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public void notifyAllObservers() {
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
		child.setParent(this);
		children.add((Resource) child);
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
}