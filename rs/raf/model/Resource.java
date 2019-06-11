package rs.raf.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import rs.raf.view.frames.MainFrame;

public class Resource extends Observable implements InfViewNode, Cloneable {
	private String name;
	private String location;
	private String type;
	private String sqlHost;
	private String sqlDatabase;
	private String sqlUsername;
	private String sqlPassword;
	private int sqlPort;
	private Connection sqlConnection = null;

	private ArrayList<Packet> children;
	private ArrayList<Observer> observers;
	private Workspace parent = null;

	public Resource(String name, String location, String type, String sqlHost, String sqlDatabase, String sqlUsername,
			String sqlPassword, int sqlPort) {
		this.name = name;
		this.location = location;
		this.type = type;
		this.sqlHost = sqlHost;
		this.sqlDatabase = sqlDatabase;
		this.sqlUsername = sqlUsername;
		this.sqlPassword = sqlPassword;
		this.sqlPort = sqlPort;

		this.children = new ArrayList<Packet>();
		this.observers = new ArrayList<Observer>();
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		child.setParent(this);
		children.add(index, (Packet) child);
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
		children.remove((Packet) node);
		notifyAllObservers();
	}

	@Override
	public void removeFromParent() {
		if (parent != null)
			parent.remove(this);
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		this.parent = (Workspace) newParent;
		notifyObservers();
	}

	@Override
	public void setUserObject(Object object) {
	}

	@Override
	public Enumeration<Packet> children() {
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
		child.setParent(this);
		children.add((Packet) child);
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSqlHost() {
		return sqlHost;
	}

	public String getSqlDatabase() {
		return sqlDatabase;
	}

	public String getSqlUsername() {
		return sqlUsername;
	}

	public String getSqlPassword() {
		return sqlPassword;
	}

	public int getSqlPort() {
		return sqlPort;
	}

	public void setSqlHost(String sqlHost) {
		this.sqlHost = sqlHost;
	}

	public void setSqlDatabase(String sqlDatabase) {
		this.sqlDatabase = sqlDatabase;
	}

	public void setSqlUsername(String sqlUsername) {
		this.sqlUsername = sqlUsername;
	}

	public void setSqlPassword(String sqlPassword) {
		this.sqlPassword = sqlPassword;
	}

	public void setSqlPort(int sqlPort) {
		this.sqlPort = sqlPort;
	}

	public void setSqlConnection(Connection connection) {
		this.sqlConnection = connection;
	}

	public Connection getSqlConnection() {
		return sqlConnection;
	}

	public void closeConnection() {
		if (sqlConnection != null)
			try {
				sqlConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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