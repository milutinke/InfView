package rs.raf.view.frames;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rs.raf.model.DBFile;
import rs.raf.model.UIAbstractFile;
import rs.raf.model.UIFileField;
import rs.raf.view.panels.FileView;

@SuppressWarnings("serial")
public class DefineSortDB extends JPanel {
	private HashMap<String, JCheckBox> sortFields = new HashMap<String, JCheckBox>();
	@SuppressWarnings("rawtypes")
	private HashMap<String, JComboBox> typeSort = new HashMap<String, JComboBox>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DefineSortDB(String title, final ArrayList<UIFileField> fields) {
		super();
		// super.setTitle(title);

		setLayout(new GridLayout(fields.size() + 1, 1));
		ArrayList<JPanel> boxes = new ArrayList<JPanel>();

		for (int i = 0; i < fields.size(); i++) {
			String[] types = { "Ascending", "Descending" };

			sortFields.put(fields.get(i).getFieldName(), new JCheckBox());
			sortFields.get(fields.get(i).getFieldName()).setActionCommand(fields.get(i).getFieldName());
			sortFields.get(fields.get(i).getFieldName()).setSelected(fields.get(i).isSort());
			sortFields.get(fields.get(i).getFieldName()).addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					typeSort.get(arg0.getActionCommand())
							.setVisible(sortFields.get(arg0.getActionCommand()).isSelected());
				}
			});

			typeSort.put(fields.get(i).getFieldName(), new JComboBox(types));
			typeSort.get(fields.get(i).getFieldName()).setVisible(fields.get(i).isSort());
			typeSort.get(fields.get(i).getFieldName())
					.setSelectedItem(fields.get(i).isAscending() ? "Ascending" : "Descending");

			boxes.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));
			boxes.get(i).add(new JLabel(" " + fields.get(i).getFieldName()));
			boxes.get(i).add(sortFields.get(fields.get(i).getFieldName()));
			boxes.get(i).add(typeSort.get(fields.get(i).getFieldName()));

			add(boxes.get(i));

		}

		Box boxC = new Box(BoxLayout.X_AXIS);
		JButton btnOK = new JButton("start");
		btnOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < fields.size(); i++) {
					fields.get(i).setSort(sortFields.get(fields.get(i).getFieldName()).isSelected());
					fields.get(i).setAscending(typeSort.get(fields.get(i).getFieldName()).getSelectedIndex() == 0);
				}

				try {
					FileView fv = ((FileView) MainFrame.getInstance().getRightPanel().getTopPanel()
							.getSelectedComponent());
					UIAbstractFile ui = fv.getFile();
					((DBFile) ui).sortMDI();
					JOptionPane.showMessageDialog(MainFrame.getInstance(), ui.getFileName() + " is now sorted");

				} catch (IOException | SQLException e) {
					e.printStackTrace();
				}

				setVisible(false);
			}

		});

		add(btnOK);
		boxC.add(btnOK);
		add(boxC);
	}
}
