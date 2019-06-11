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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rs.raf.model.DBFile;
import rs.raf.model.UIFileField;

@SuppressWarnings("serial")
public class CreateRecordView extends JPanel {
	@SuppressWarnings("unused")
	private ArrayList<UIFileField> fields = new ArrayList<>();
	private ArrayList<JTextField> inputs = new ArrayList<>();
	private ArrayList<String> resultRecord;
	private JPanel panel1 = new JPanel();
	@SuppressWarnings("unused")
	private JCheckBox traziSve;
	@SuppressWarnings("unused")
	private JCheckBox odPocetka;
	@SuppressWarnings("unused")
	private DBFile file;

	public CreateRecordView(DBFile file, ArrayList<UIFileField> fields) {
		super();
		this.file = file;

		resultRecord = new ArrayList<>();
		this.fields = fields;
		panel1.setLayout(new GridLayout(fields.size(), 1));

		int i = 0;
		for (UIFileField field : fields) {
			JPanel filedsPanel = new JPanel();
			JTextField tf = new JTextField(field.getFieldLength());
			inputs.add(tf);
			filedsPanel.add(new JLabel(field.getFieldName()));
			filedsPanel.add(inputs.get(i));
			filedsPanel.add(new JLabel("tip:" + field.getFieldType()));
			filedsPanel.add(new JLabel("duzina<=" + field.getFieldLength()));
			i++;
			panel1.add(filedsPanel);
		}

		this.add(panel1, BorderLayout.NORTH);
		JPanel buttonsPanel = new JPanel();

		JButton btn = new JButton("add");

		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				resultRecord.clear();
				for (int i = 0; i < fields.size(); i++) {
					if (validInput(inputs.get(i).getText(), fields.get(i)))
						resultRecord.add(inputs.get(i).getText());
					else
						return;
				}

				try {
					file.addRecord(resultRecord);
				} catch (SQLException e) {
					MainFrame.getInstance().getExceptionsManager().errorNotDone();
				}
				setVisible(false);
			}

		});

		buttonsPanel.add(btn);

		this.add(buttonsPanel, BorderLayout.SOUTH);
		// setSize(600, 300);
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
				df.parse(string);
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
