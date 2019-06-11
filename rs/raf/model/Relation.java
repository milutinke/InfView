package rs.raf.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Relation extends Observable implements InfViewNode, Cloneable {
	private String name;
	private Entity entity1;
	private Entity entity2;
	private Attribute key1;
	private Attribute key2;

	private ArrayList<Observer> observers;
	private InfViewNode parent = null;

	public Relation(Entity entity1, Entity entity2, Attribute key1, Attribute key2) {
		this.entity1 = entity1;
		this.entity2 = entity2;
		this.key1 = key1;
		this.key2 = key2;

		this.name = entity1.getName() + " - " + entity2.getName();
		this.observers = new ArrayList<Observer>();
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
	}

	@Override
	public void remove(int index) {
	}

	@Override
	public void remove(MutableTreeNode node) {
	}

	@Override
	public void removeFromParent() {
		if (parent != null)
			parent.remove(this);
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		this.parent = (InfViewNode) newParent;
	}

	@Override
	public void setUserObject(Object object) {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return null;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public int getIndex(TreeNode node) {
		return 0;
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		return true;
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
	}

	public void addObserver(Observer observer) {
		if (observers.contains(observer))
			return;

		observers.add(observer);
	}

	public ArrayList<Observer> getObservers() {
		return observers;
	}

	public Entity getEntity1() {
		return entity1;
	}

	public Entity getEntity2() {
		return entity2;
	}

	public Attribute getKey1() {
		return key1;
	}

	public Attribute getKey2() {
		return key2;
	}

	public void setEntity1(Entity entity1) {
		this.entity1 = entity1;
	}

	public void setEntity2(Entity entity2) {
		this.entity2 = entity2;
	}

	public void setKey1(Attribute key1) {
		this.key1 = key1;
	}

	public void setKey2(Attribute key2) {
		this.key2 = key2;
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