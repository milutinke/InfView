package rs.raf.view.frames;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import rs.raf.model.Attribute;
import rs.raf.model.Entities;
import rs.raf.model.Entity;
import rs.raf.model.InfViewNode;
import rs.raf.model.Packet;
import rs.raf.model.Relation;
import rs.raf.model.Relations;
import rs.raf.utilities.NodeHelper;

@SuppressWarnings("serial")
public class AddRelationView extends JDialog {
	private JComboBox<String> e1;
	private JLabel label1;
	private JComboBox<String> e2;
	private JLabel label2;

	@SuppressWarnings("unused")
	private InfViewNode inf;
	private Entities entities;
	private JComboBox<String> key1;
	private JComboBox<String> key2;
	@SuppressWarnings("unused")
	private InfViewNode infViewNode;
	@SuppressWarnings("unused")
	private JLabel klabel1;
	@SuppressWarnings("unused")
	private JLabel klabel2;

	private Packet base;

	public AddRelationView(InfViewNode base) {
		this.base = (Packet) base;
		InfViewNode relations = NodeHelper.findNode(base, Relations.class, "Relations");
		entities = (Entities) NodeHelper.findNode(base, Entities.class, "Entities");

		e1 = new JComboBox<>();
		e2 = new JComboBox<>();
		key1 = new JComboBox<>();
		key2 = new JComboBox<>();
		setListeners(e1, key1);
		setListeners(e2, key2);

		int i;
		for (i = 0; i < entities.getChildCount(); i++) {
			e1.addItem(((Entity) entities.getChildAt(i)).getName());
			e2.addItem(((Entity) entities.getChildAt(i)).getName());
		}

		fillKeysAtStart(e1, key1);
		fillKeysAtStart(e2, key2);

		label1 = new JLabel("Entity1");
		label1.setBounds(30, 20, 100, 20);
		e1.setBounds(80, 20, 100, 20);

		key1.setBounds(200, 20, 100, 20);

		label2 = new JLabel("Entity2");
		label2.setBounds(30, 60, 100, 20);
		e2.setBounds(80, 60, 100, 20);

		key2.setBounds(200, 60, 100, 20);

		setSize(500, 300);
		setResizable(false);
		setLayout(null);
		setVisible(true);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

		setTitle("New relation");

		JButton add = new JButton("CREATE");

		add.setBounds(50, 100, 100, 50);
		add(e1);
		add(label1);
		add(key1);
		add(key2);
		add(e2);
		add(label2);
		add(add);

		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Entity entitet1 = null;
				Entity entitet2 = null;
				try {
					entitet1 = (Entity) NodeHelper.findNode(entities, Entity.class, e1.getSelectedItem().toString());
					entitet2 = (Entity) NodeHelper.findNode(entities, Entity.class, e2.getSelectedItem().toString());
				} catch (Exception e2) {
				}

				Relation rel = new Relation(entitet1, entitet2, null, null);
				if (rel != null)
					if (NodeHelper.isDuplicate(relations, rel)) {
						JOptionPane.showMessageDialog(null, "Relacija vec postoji");
						return;
					}

				setKeys(rel);
				relations.addChild(rel);
				EditorFrame.getListOfrelations().addItem(rel.getName());
				dispose();
				// @SuppressWarnings("unused")
				// KeySetView view = new KeySetView(inf, entitet1, entitet2, combo1, combo2);

			}
		});

	}

	public void setKeys(Relation relation) {
		Attribute attribute1 = null;
		Attribute attribute2 = null;
		try {
			attribute1 = (Attribute) NodeHelper.findNode(relation.getEntity1(), Attribute.class,
					key1.getSelectedItem().toString());
			attribute2 = (Attribute) NodeHelper.findNode(relation.getEntity2(), Attribute.class,
					key2.getSelectedItem().toString());
		} catch (Exception e2) {
		}

		relation.setKey1(attribute1);
		relation.setKey2(attribute2);

	}

	public void setListeners(JComboBox<String> listOfEntities, JComboBox<String> keys) {
		listOfEntities.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				keys.removeAllItems();

				InfViewNode entities = NodeHelper.findNode(base, Entities.class, "Entities");
				Entity entity = (Entity) NodeHelper.findNode(entities, Entity.class,
						listOfEntities.getSelectedItem().toString());
				if (entity.children() == null)
					return;
				Enumeration<Attribute> children = (Enumeration<Attribute>) entity.children();
				if (children != null) {
					while (children.hasMoreElements()) {
						InfViewNode node = (InfViewNode) children.nextElement();
						keys.addItem(node.getName());
					}
				}
			}
		});
	}

	public void fillKeysAtStart(JComboBox<String> listOfEntities, JComboBox<String> keys) {
		keys.removeAllItems();
		InfViewNode entities = NodeHelper.findNode(base, Entities.class, "Entities");
		Entity entity = (Entity) NodeHelper.findNode(entities, Entity.class,
				listOfEntities.getSelectedItem().toString());
		if (entity.children() == null)
			return;
		Enumeration<Attribute> children = (Enumeration<Attribute>) entity.children();
		if (children != null) {
			while (children.hasMoreElements()) {
				InfViewNode node = (InfViewNode) children.nextElement();
				keys.addItem(node.getName());

			}
		}

	}

}
