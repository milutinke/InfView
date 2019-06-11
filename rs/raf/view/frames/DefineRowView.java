package rs.raf.view.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rs.raf.model.UIAbstractFile;
import rs.raf.model.UIFileField;
import rs.raf.view.panels.FileView;

@SuppressWarnings("serial")
public class DefineRowView extends JPanel {

	@SuppressWarnings("unused")
	private ArrayList<UIFileField> fields = new ArrayList<>();
	private ArrayList<JTextField> inputs = new ArrayList<>();
	private ArrayList<String> resultRecord = new ArrayList<>();
	private JPanel panel1 = new JPanel();
	private JCheckBox traziSve;
	private JCheckBox odPocetka;

	public DefineRowView(String action, ArrayList<UIFileField> fields) {
		super();

		
		this.fields = fields;
		panel1.setLayout(new GridLayout(fields.size(), 1));

		for (int i = 0; i < fields.size(); i++) {
			JPanel filedsPanel = new JPanel();
			JTextField tf = new JTextField(10);

			inputs.add(tf);
			filedsPanel.add(new JLabel(fields.get(i).getFieldName()));
			filedsPanel.add(inputs.get(i));

			panel1.add(filedsPanel);
		}

		this.add(panel1, BorderLayout.NORTH);
		JPanel buttonsPanel = new JPanel();

		JButton btn = new JButton("Search");
		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < fields.size(); i++) {
					resultRecord.add(inputs.get(i).getText());
				}

				// krecemo
				startSearch();
				setVisible(false);
			}

		});
		traziSve = new JCheckBox("Search for all");
		odPocetka = new JCheckBox("From start");
		buttonsPanel.add(traziSve);
		buttonsPanel.add(odPocetka);
		buttonsPanel.add(btn);

		this.add(buttonsPanel, BorderLayout.SOUTH);
		//setSize(600, 300);
	}

	public void startSearch() {
		boolean nadjen = false;
	//	int positions[] = FileView.getUIFile().findRecord(this.getResultRecord(), this.SearchForAll(),
	//			this.FromStart());
		
		FileView fv = ((FileView) MainFrame.getInstance().getRightPanel().getTopPanel().getSelectedComponent());
		UIAbstractFile ui=fv.getFile();
		
		int positions[] = ui.findRecord(this.getResultRecord(), this.SearchForAll(),
							this.FromStart());
		

		for (int position : positions) {
			if (position != -1) {

				if (!this.SearchForAll())
					fv.getTable().getSelectionModel().setSelectionInterval(position, position);
				nadjen = true;
			}
		}

		if (!nadjen)
			JOptionPane.showMessageDialog(this, "Item <not found", "Error!", JOptionPane.ERROR_MESSAGE);

	}

	public ArrayList<String> getResultRecord() {

		return resultRecord;
	}

	public boolean SearchForAll() {
		if (traziSve.isSelected())
			return true;

		return false;
	}

	public boolean FromStart() {
		if (odPocetka.isSelected())
			return true;
		return false;

	}

}
