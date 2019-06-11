package rs.raf.view.frames;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import rs.raf.model.Attribute;
import rs.raf.model.Entities;
import rs.raf.model.Entity;
import rs.raf.model.INDFile;
import rs.raf.model.InfViewNode;
import rs.raf.model.Packet;
import rs.raf.model.SEKFile;
import rs.raf.model.SERFile;
import rs.raf.model.UIFileField;
import rs.raf.utilities.NodeHelper;

@SuppressWarnings("serial")
public class AddEntityView extends JDialog {
	private JTextField tf; // za ime
	private JTextField Zafile;
	private String location;
	private JLabel label;
	private File file;

	public AddEntityView(InfViewNode parent) {
		Packet packet = (Packet) parent.getParent();
		location = (packet.getLocation());
		tf = new JTextField();
		Zafile = new JTextField();
		label = new JLabel("Name");
		label.setBounds(10, 20, 100, 20);
		tf.setBounds(120, 20, 100, 20);
		Zafile.setBounds(120, 60, 100, 20);
		JButton browse = new JButton("File");
		browse.setBounds(10, 60, 80, 20);

		if (!((InfViewNode) parent.getParent()).getName().equals("Servisna Stanica")) {
			add(browse);
			add(Zafile);
		}

		setLayout(null);
		setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
		setTitle(parent.getName());

		setSize(400, 400);
		setResizable(false);
		JButton add = new JButton("ADD");

		add.setBounds(50, 160, 100, 40);
		add(tf);
		add(label);
		add(add);

		browse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				jfc.showOpenDialog(MainFrame.getInstance());
				if (jfc.getSelectedFile() != null) {
					Zafile.setText(jfc.getSelectedFile().getName());
					file = jfc.getSelectedFile();
					location = file.getParentFile().getAbsolutePath();
				}

			}
		});

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (parent instanceof Entities) {

					Entity child;
					if (((InfViewNode) parent.getParent()).getName().equals("Servisna stanica")) {
						child = new Entity(tf.getText(), "", "");
					} else {
						child = new Entity(tf.getText(), location, Zafile.getText());
					}

					if (packet.getName().equals("Indeks - sekvencijalne datoteke")) {
						if (!child.getFile().contains(".ind")) {
							JOptionPane.showMessageDialog(null, "Datoteka mora biti indeks - sekvencijalna");
							return;
						}

						String file = child.getFile();// ok
						String location = child.getLocation();
						INDFile ind = new INDFile(file, location, file);

						try {
							ind.readHeader();
						} catch (IOException | SQLException e1) {
							e1.printStackTrace();
						}
						for (UIFileField field : ind.getFields()) {
							Attribute a = new Attribute(field.getFieldName());
							a.setDataType(field.getFieldType());
							a.setLength(field.getFieldLength());
							a.setPrimaryKey(field.isFieldPK());
							child.addChild(a);

						}

					} else if (packet.getName().equals("Sekvencijalne Datoteke")) {
						if (!child.getFile().contains(".sek")) {
							JOptionPane.showMessageDialog(null, "Datoteka mora biti sekvencijalna");
							return;
						}

						String file = child.getFile();// ok
						String location = child.getLocation();

						SEKFile sek = new SEKFile(file, location, file);
						try {
							sek.readHeader();
						} catch (IOException | SQLException e1) {
							e1.printStackTrace();
						}

						for (UIFileField field : sek.getFields()) {
							Attribute a = new Attribute(field.getFieldName());
							a.setDataType(field.getFieldType());
							a.setLength(field.getFieldLength());
							a.setPrimaryKey(field.isFieldPK());
							child.addChild(a);
						}

					} else if (packet.getName().equals("Serijske datoteke")) {
						if (!child.getFile().contains(".ser")) {
							JOptionPane.showMessageDialog(null, "Datoteka mora biti serijska");
							return;
						}

						String file = child.getFile();// ok
						String location = child.getLocation();

						SERFile ser = new SERFile(file, location, file);

						try {
							ser.readHeader();
						} catch (IOException | SQLException e1) {
							e1.printStackTrace();
						}

						for (UIFileField field : ser.getFields()) {
							Attribute a = new Attribute(field.getFieldName());
							a.setDataType(field.getFieldType());
							a.setLength(field.getFieldLength());
							a.setPrimaryKey(field.isFieldPK());
							child.addChild(a);
						}
					}

					if (packet.getName().equals("Servisna Stanica")) {
						if (!tf.getText().isEmpty() && !NodeHelper.isDuplicate((InfViewNode) parent, child)) {
							((Entities) parent).addChild(child);
							EditorFrame.getListOfentities().addItem(child.getName());
							EditorFrame.getListOfentities().setSelectedItem(child.getName());
						} else
							JOptionPane.showMessageDialog(null, "Ime nije validno");
					}

					else {
						if (!tf.getText().isEmpty() && !Zafile.getText().isEmpty()
								&& !NodeHelper.isDuplicate((InfViewNode) parent, child)) {
							((Entities) parent).addChild(child);
							EditorFrame.getListOfentities().addItem(child.getName());
							EditorFrame.getListOfentities().setSelectedItem(child.getName());
						} else
							JOptionPane.showMessageDialog(null, "Ime nije validno");
					}
				}
				dispose();
			}

		});

	}

}
