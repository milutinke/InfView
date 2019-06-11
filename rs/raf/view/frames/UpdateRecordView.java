package rs.raf.view.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rs.raf.model.DBFile;
import rs.raf.model.UIFileField;
import rs.raf.view.panels.FileView;

@SuppressWarnings("serial")
public class UpdateRecordView extends JPanel {

	@SuppressWarnings("unused")
	private ArrayList<UIFileField> fields = new ArrayList<>();
	private ArrayList<JTextField> inputs = new ArrayList<>();
	private ArrayList<String> resultRecord;
	private ArrayList<String> where;
	private JPanel panel1 = new JPanel();

	public UpdateRecordView(DBFile file, ArrayList<UIFileField> fields) {
		super();

		resultRecord = new ArrayList<>();
		where = new ArrayList<>();
		this.fields = fields;
		panel1.setLayout(new GridLayout(fields.size(), 1));

		FileView fv = ((FileView) MainFrame.getInstance().getRightPanel().getTopPanel().getSelectedComponent());

		for (int i = 0; i < fields.size(); i++) {

			JPanel filedsPanel = new JPanel();
			JTextField tf = new JTextField(fields.get(i).getFieldLength());
			tf.setText(fv.getTable().getModel().getValueAt(fv.getTable().getSelectedRow(), i).toString());
			inputs.add(tf);
			filedsPanel.add(new JLabel(fields.get(i).getFieldName()));
			filedsPanel.add(inputs.get(i));
			filedsPanel.add(new JLabel("tip:" + fields.get(i).getFieldType()));
			filedsPanel.add(new JLabel("duzina<=" + fields.get(i).getFieldLength()));

			panel1.add(filedsPanel);

		}

		this.add(panel1, BorderLayout.NORTH);
		JPanel buttonsPanel = new JPanel();

		JButton btn = new JButton("update");

		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				resultRecord.clear();
				where.clear();

				String whereStmt = " WHERE ";
				for (int i = 0; i < fields.size(); i++) {
					if (fields.get(i).isFieldPK()) {
						String pkOriginal = fv.getTable().getModel().getValueAt(fv.getTable().getSelectedRow(), i)
								.toString();
						if (i == 0) {
							whereStmt += fields.get(i).getFieldName() + "=   "
									+ ((fields.get(i).isStringType()) ? "'" + pkOriginal + "'" : pkOriginal);
						} else {
							whereStmt += " AND " + fields.get(i).getFieldName() + "= "
									+ ((fields.get(i).isStringType()) ? "'" + pkOriginal + "'" : pkOriginal);
						}
					}
				}

				// unos korisnika
				for (int i = 0; i < fields.size(); i++) {
					if (validInput(inputs.get(i).getText(), fields.get(i))) {
						if (fields.get(i).isStringType())
							resultRecord.add("'" + inputs.get(i).getText() + "'");
						else {
							if (fields.get(i).isDate()) {
								String[] tok = inputs.get(i).getText().split(" ");
								resultRecord.add(tok[0]);
							} else
								resultRecord.add(inputs.get(i).getText());

						}

					} else
						return;
				}
				try {
					file.updateRecord(resultRecord, whereStmt);
				} catch (SQLException | IOException e) {
					MainFrame.getInstance().getExceptionsManager().errorNotDone();
				}

				setVisible(false);
			}
		});

		buttonsPanel.add(btn);

		this.add(buttonsPanel, BorderLayout.SOUTH);
	}

	private boolean validInput(String string, UIFileField field) {

		if (field.isNullable())
			System.out.println(field.getFieldName());

		if (!field.isNullable() && string.equals("")) {
			MainFrame.getInstance().getExceptionsManager().errorNotNullable(field.getFieldName());
			return false;
		}

		if (field.isNumeric()) {
			if (field.getFieldType().equals("INT"))
				try {
					int br = Integer.parseInt(string);

					if (br < 0) {
						MainFrame.getInstance().getExceptionsManager().invalidValue();
						return false;
					}

					return true;
				} catch (NumberFormatException e) {

					MainFrame.getInstance().getExceptionsManager().errorNumFormat(field.getFieldType(),
							field.getFieldName());
					return false;
				}

			else {
				try {
					double br = Double.parseDouble(string);
					if (br < 0) {
						MainFrame.getInstance().getExceptionsManager().invalidValue();
						return false;
					}
					return true;
				} catch (NumberFormatException e) {

					MainFrame.getInstance().getExceptionsManager().errorNumFormat(field.getFieldType(),
							field.getFieldName());
					return false;
				}
			}

		}

		if (field.isDate()) {
			try {
				DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
				df.setLenient(false);

				String[] tok = string.split(" ");
				System.out.println(tok[0]);
				df.parse(tok[0]);
				return true;
			} catch (Exception e) {
				MainFrame.getInstance().getExceptionsManager().errorDateFormat("yyyy-mm-dd");
				return false;
			}

		}

		if (field.getFieldName().equals("IMAGE")) {
			Image image;
			
			try {
				image = ImageIO.read(new File(string));
				if (image != null)
					return true;
			} catch (IOException e) {
				MainFrame.getInstance().getExceptionsManager().errorPathFormat();
			}

		}

		return true;
	}

}
