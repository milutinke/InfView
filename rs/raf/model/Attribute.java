package rs.raf.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import rs.raf.view.frames.MainFrame;

public class Attribute extends Observable implements InfViewNode, Cloneable {
	private String name;
	private String dataType;
	private boolean primaryKey = false;
	private boolean foreignKey = false;
	private boolean mandatory = false;
	private Object defaultValue = null;
	private Object length = 0;

	private ArrayList<Observer> observers;
	private InfViewNode parent = null;

	public Attribute(String name) {
		this.name = name;
		this.observers = new ArrayList<Observer>();
	}

	public Attribute withDataType(String dataType) {
		this.dataType = dataType;
		return this;
	}

	public Attribute withPrimaryKey(boolean isPrimaryKey) {
		this.primaryKey = isPrimaryKey;
		return this;
	}

	public Attribute withForeignKey(boolean isForeignKey) {
		this.foreignKey = isForeignKey;
		return this;
	}

	public Attribute withMandatory(boolean isMandatory) {
		this.mandatory = isMandatory;
		return this;
	}

	public Attribute withDefaultValue(Object value) {
		this.defaultValue = value;
		return this;
	}

	public Attribute withLength(Object length) {
		this.length = length;
		return this;
	}

	public Attribute build() {
		return this;
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
	}

	public void addObserver(Observer observer) {
		if (observers.contains(observer))
			return;

		observers.add(observer);
	}

	public ArrayList<Observer> getObservers() {
		return observers;
	}

	public String getDataType() {
		return dataType;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public boolean isForeignKey() {
		return foreignKey;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public Object getLength() {
		return length;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public void setForeignKey(boolean foreignKey) {
		this.foreignKey = foreignKey;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setLength(Object length) {
		this.length = length;
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