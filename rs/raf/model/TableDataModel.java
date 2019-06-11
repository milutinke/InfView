package rs.raf.model;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class TableDataModel extends DefaultTableModel {
	public TableDataModel(ArrayList<UIFileField> fields, Object[][] data) {
		try {
			setDataVector(data, fields.toArray());
		} catch (OutOfMemoryError e) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), e.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
}
