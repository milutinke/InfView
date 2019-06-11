package rs.raf.view.frames;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import rs.raf.model.Attribute;
import rs.raf.model.Entity;
import rs.raf.model.Relation;
import rs.raf.utilities.NodeHelper;

@SuppressWarnings("serial")
public class EditRelationView extends JDialog {

	private Relation rel;
	private JComboBox<String> combo1;
	private JComboBox<String> combo2;

	private JLabel label1;
	private JLabel label2;

	public EditRelationView(Relation r) {

		this.rel = r;
		combo1 = new JComboBox<>();
		combo2 = new JComboBox<>();

		Entity e1 = rel.getEntity1();
		Entity e2 = rel.getEntity2();

		int i;

		for (i = 0; i < e1.getChildCount(); i++) {
			combo1.addItem(((Attribute) e1.getChildAt(i)).getName());

		}
		for (i = 0; i < e2.getChildCount(); i++) {
			combo2.addItem(((Attribute) e2.getChildAt(i)).getName());

		}

		combo1.setSelectedItem(rel.getKey1().toString());
		combo2.setSelectedItem(rel.getKey2().toString());

		label1 = new JLabel(r.getEntity1().getName());
		label1.setBounds(30, 20, 100, 20);
		combo1.setBounds(150, 20, 100, 40);

		label2 = new JLabel(r.getEntity2().getName());

		label2.setBounds(30, 60, 100, 20);
		combo2.setBounds(150, 60, 100, 40);

		setSize(300, 300);
		setLayout(null);
		setVisible(true);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
		setTitle("Edit relation");

		JButton add = new JButton("SAVE");

		add.setBounds(50, 100, 100, 50);
		add(combo1);
		add(label1);
		add(combo2);
		add(label2);
		add(add);

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Attribute key1 = (Attribute) NodeHelper.findNode(e1, Attribute.class,
							combo1.getSelectedItem().toString());
					Attribute key2 = (Attribute) NodeHelper.findNode(e2, Attribute.class,
							combo2.getSelectedItem().toString());

					rel.setKey1(key1);
					rel.setKey2(key2);

					dispose();
				} catch (Exception e2) {
					MainFrame.getInstance().getExceptionsManager().errorCannotAddRelation();
				}
			}

		});
	}

}
