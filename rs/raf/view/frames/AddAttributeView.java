package rs.raf.view.frames;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import rs.raf.controller.CustomExceptionHandler;
import rs.raf.model.Attribute;
import rs.raf.model.Entity;
import rs.raf.utilities.NodeHelper;

@SuppressWarnings("serial")
public class AddAttributeView extends JDialog {
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;

	private JLabel label4;

	private JTextField name;
	private JTextField length;
	private JTextField value;
	private JCheckBox cb1;
	private JCheckBox cb2;
	private JCheckBox cb3;
	private JTextField data;

	@SuppressWarnings("unused")
	private Entity parent;

	public AddAttributeView(Entity parent) {

		this.parent = parent;
		name = new JTextField();
		label1 = new JLabel("Name");
		label1.setBounds(30, 20, 100, 20);
		name.setBounds(150, 20, 100, 20);

		label2 = new JLabel("Length");
		label2.setBounds(30, 60, 100, 20);
		length = new JTextField();
		length.setBounds(150, 60, 100, 20);

		label3 = new JLabel("Default value");
		label3.setBounds(30, 100, 100, 20);
		value = new JTextField();
		value.setBounds(150, 100, 100, 20);

		label4 = new JLabel("Data type");
		label4.setBounds(30, 140, 100, 20);
		data = new JTextField();
		data.setBounds(150, 140, 100, 20);

		cb1 = new JCheckBox("Primary key");
		cb2 = new JCheckBox("Foreign key");
		cb3 = new JCheckBox("Mandatory key");

		cb1.setBounds(30, 180, 150, 20);
		cb2.setBounds(30, 220, 150, 20);
		cb3.setBounds(30, 260, 150, 20);

		setResizable(false);
		setLayout(null);
		setVisible(true);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

		setSize(300, 500);
		setTitle("Attribute editor");

		JButton add = new JButton("SAVE");

		add.setBounds(50, 400, 100, 50);

		add(label1);
		add(label2);
		add(label3);
		add(cb1);
		add(cb2);
		add(cb3);
		add(length);
		add(value);
		add(name);
		add(data);
		add(label4);
		add(add);

		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Attribute a = new Attribute(name.getText());
				if (!name.getText().isEmpty() && (NodeHelper.isNameReserved(name.getText(), a, parent) == false)) {
					a.setName(name.getText());

				} else {
					JOptionPane.showMessageDialog(null, "Ime nije validno");
					return;
				}

				if (!length.getText().isEmpty())
					try {
						@SuppressWarnings("unused")
						int i = Integer.parseInt(length.getText());
						a.setLength(Integer.parseInt(length.getText()));
					} catch (Exception e2) {
						CustomExceptionHandler.createMessage(null, "Duzina ne moze biti:" + length.getText());
						return;
					}
				else {
					JOptionPane.showMessageDialog(null, "Polje length nije validno");
					return;
				}

				if (!data.getText().contains(" ") && !data.getText().isEmpty())
					a.setDataType(data.getText());
				else {
					JOptionPane.showMessageDialog(null, "Polje data type nije validno");
					return;
				}

				if (!value.getText().contains(" ") && !value.getText().isEmpty())
					a.setDefaultValue(value.getText());
				else {
					JOptionPane.showMessageDialog(null, "Polje default value nije validno");
					return;
				}

				a.setPrimaryKey(cb1.isSelected());
				a.setForeignKey(cb2.isSelected());
				a.setMandatory(cb3.isSelected());

				parent.addChild(a);
				EditorFrame.getListOfattributes().addItem(a.getName());
				dispose();
			}

		});
	}
}
