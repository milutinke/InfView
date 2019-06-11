package rs.raf.view.frames;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import rs.raf.model.Attribute;
import rs.raf.model.Entities;
import rs.raf.model.Entity;
import rs.raf.model.InfViewNode;
import rs.raf.model.Packet;
import rs.raf.model.Relation;
import rs.raf.model.Relations;
import rs.raf.settings.Settings;
import rs.raf.utilities.CloneUtilities;
import rs.raf.utilities.NodeHelper;
import rs.raf.view.panels.EditorToolbar;
import rs.raf.view.tree.InfViewTree;

@SuppressWarnings("serial")
public class EditorFrame extends JFrame {

	private static JComboBox<String> listOfentities;
	private static JComboBox<String> listOfattributes;
	private static JComboBox<String> listOfrelations;
	private Entities entities;
	private Relations relations;
	@SuppressWarnings("unused")
	private Packet packet;
	private static Packet clone;
	private static Packet clonedOriginal;
	private static InfViewTree tree;

	@SuppressWarnings("unchecked")
	public EditorFrame(Packet packet) {
		setTitle("Meta Scheme Editor");
		setIconImage(new ImageIcon(Settings.ICON_FOLDER_PATH + Settings.APP_ICON).getImage());
		setResizable(false);
		clone = null;
		clonedOriginal = null;
		listOfentities = null;
		listOfattributes = null;
		listOfrelations = null;

		try {
			if (clone == null) {
				clonedOriginal = CloneUtilities.get(packet);
				clone = CloneUtilities.get(packet);
				SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
			}
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}

		setSize(500, 500);
		setVisible(true);
		setLayout(null);
		this.packet = packet;

		EditorToolbar toolBar = new EditorToolbar();
		toolBar.setBounds(0, 0, 500, 60);
		add(toolBar);

		listOfentities = new JComboBox<>();
		listOfattributes = new JComboBox<>();
		listOfrelations = new JComboBox<>();

		listOfentities.setBounds(20, 100, 100, 50);
		listOfattributes.setBounds(150, 100, 100, 50);
		listOfrelations.setBounds(20, 200, 200, 50);

		add(listOfentities);
		add(listOfattributes);
		add(listOfrelations);

		JButton save = new JButton(MainFrame.getInstance().getActionManager().getSaveAndValidateAction());

		save.setBounds(50, 300, 100, 50);
		add(save);
		entities = (Entities) NodeHelper.findNode(clone, Entities.class, "Entities");
		Enumeration<Entity> children = (Enumeration<Entity>) entities.children();

		if (children != null) {
			while (children.hasMoreElements()) {
				InfViewNode node = (InfViewNode) children.nextElement();
				listOfentities.addItem(node.getName());
			}
		}

		Enumeration<Attribute> children1 = (Enumeration<Attribute>) entities.getChildAt(0).children();
		if (children1 != null) {
			while (children1.hasMoreElements()) {
				InfViewNode node = (InfViewNode) children1.nextElement();
				listOfattributes.addItem(node.getName());
			}

		}

		relations = (Relations) NodeHelper.findNode(clone, Relations.class, "Relations");
		Enumeration<Relation> children2 = (Enumeration<Relation>) relations.children();

		if (children2 != null) {
			while (children2.hasMoreElements()) {
				InfViewNode node = (InfViewNode) children2.nextElement();
				listOfrelations.addItem(node.getName());
			}
		}

		listOfentities.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				listOfattributes.removeAllItems();
				Entity entity = (Entity) NodeHelper.findNode(entities, Entity.class,
						listOfentities.getSelectedItem().toString());
				if (entity.children() == null)
					return;
				Enumeration<Attribute> children = (Enumeration<Attribute>) entity.children();
				if (children != null) {
					while (children.hasMoreElements()) {
						InfViewNode node = (InfViewNode) children.nextElement();
						listOfattributes.addItem(node.getName());
					}
				}
			}
		});
	}

	public static JComboBox<String> getListOfentities() {
		return listOfentities;
	}

	public static JComboBox<String> getListOfattributes() {
		return listOfattributes;
	}

	public static JComboBox<String> getListOfrelations() {
		return listOfrelations;
	}

	public static InfViewTree getTree() {
		return tree;
	}

	public static Packet getClone() {
		return clone;
	}

	public static Packet getClonedOriginal() {
		return clonedOriginal;
	}
}