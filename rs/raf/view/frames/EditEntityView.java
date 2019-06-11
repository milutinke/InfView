package rs.raf.view.frames;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import rs.raf.model.Entity;
import rs.raf.model.InfViewNode;
import rs.raf.model.Relation;
import rs.raf.model.Relations;
import rs.raf.utilities.NodeHelper;

@SuppressWarnings("serial")
public class EditEntityView extends JDialog {
	private JTextField nameField = new JTextField();
	private Entity entity;

	public EditEntityView(Entity entity) {
		this.entity = entity;
		initGui();
	}

	private void initGui() {

		setSize(500, 200);
		setTitle("Edit entity");
		setModal(true);
		JPanel panel = new JPanel();

		panel.add(new JLabel("Name: "));

		nameField.setMinimumSize(new Dimension(300, 40));
		nameField.setPreferredSize(new Dimension(300, 40));
		nameField.setText(entity.getName());
		panel.add(nameField);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

		JButton add = new JButton("Save");

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Entity forDuplicatiCheck = new Entity(nameField.getText(), entity.getLocation(), entity.getFile());
				if (!nameField.getText().isEmpty()
						&& (!NodeHelper.isDuplicate((InfViewNode) entity.getParent(), forDuplicatiCheck))) {
				} else {
					JOptionPane.showMessageDialog(null, "Ime nije validno");
					return;
				}

				updateRelations(entity, nameField.getText());

				EditorFrame.getListOfentities().removeItem(entity.getName());

				EditorFrame.getListOfentities().addItem(nameField.getText());
				entity.setName(nameField.getText());

				SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
				closeWindow();
			}

		});

		panel.add(add);
		this.add(panel);

	}

	private void updateRelations(Entity entity, String newName) {
		if (entity == null)
			return;

		Relations relations = (Relations) NodeHelper.findNode(EditorFrame.getClone(), Relations.class, "Relations");

		@SuppressWarnings("unchecked")
		Enumeration<InfViewNode> children = (Enumeration<InfViewNode>) ((InfViewNode) relations).children();

		if (children != null) {
			while (children.hasMoreElements()) {
				Relation relation = (Relation) children.nextElement();

				if (relation == null || relation.getEntity1() == null || relation.getEntity2() == null)
					continue;

				if (relation.getEntity1().getName().equals(entity.getName())) {

					EditorFrame.getListOfrelations().removeItem(relation.getName());
					relation.getEntity1().setName(newName);
					relation.setName(relation.getEntity1() + " - " + relation.getEntity2());
					SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
					EditorFrame.getListOfrelations().addItem(relation.getName());
				}
				if (relation.getEntity2().getName().equals(entity.getName())) {

					EditorFrame.getListOfrelations().removeItem(relation.getName());
					relation.getEntity2().setName(newName);
					relation.setName(relation.getEntity1() + " - " + relation.getEntity2());
					SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
					EditorFrame.getListOfrelations().addItem(relation.getName());

				}
			}
		}

	}

	private void closeWindow() {
		this.setVisible(false);
		this.setModal(false);
	}

}
