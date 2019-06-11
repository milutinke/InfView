package rs.raf.view.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import rs.raf.actions.BinarySearchAction;
import rs.raf.model.Attribute;
import rs.raf.model.Entity;
import rs.raf.model.UIAbstractFile;
import rs.raf.view.frames.MainFrame;
import rs.raf.view.panels.FileView;

@SuppressWarnings("serial")
public class DefineBinarySearchView extends JPanel {
	private ArrayList<TextField> textFields;
	private ArrayList<JCheckBox> checkBoxes;
	private ArrayList<Integer> lengths;
	private JButton searchButton;
	private Entity entity;

	public DefineBinarySearchView() {
		textFields = new ArrayList<TextField>();
		checkBoxes = new ArrayList<JCheckBox>();
		lengths = new ArrayList<>();
		searchButton = new JButton("Search");

		JPanel panel = new JPanel();

		entity = ((FileView) MainFrame.getInstance().getRightPanel().getTopPanel().getSelectedComponent()).getEntity();

		int number = 0;
		ArrayList<Attribute> attributes = Collections.list((Enumeration<Attribute>) entity.children());

		for (Attribute attribute : attributes) {
			if (attribute.isPrimaryKey() || attribute.isForeignKey())
				number++;
		}

		panel.setLayout(new GridLayout(number, 1));

		for (Attribute attribute : attributes) {
			if (attribute.isPrimaryKey() || attribute.isForeignKey()) {
				JCheckBox checkBox = new JCheckBox(attribute.getName());
				TextField textField = new TextField(attribute.getName());

				lengths.add((Integer) attribute.getLength());

				panel.add(checkBox);
				panel.add(textField);

				checkBoxes.add(checkBox);
				textFields.add(textField);
			}
		}

		add(panel, BorderLayout.NORTH);
		FileView fv = ((FileView) MainFrame.getInstance().getRightPanel().getTopPanel().getSelectedComponent());
		UIAbstractFile ui=fv.getFile();
		searchButton.addActionListener(new BinarySearchAction(this,ui));
		add(searchButton);
	}

	public ArrayList<TextField> getTextFields() {
		return textFields;
	}

	public ArrayList<JCheckBox> getCheckBoxes() {
		return checkBoxes;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public ArrayList<Integer> getLengths() {
		return lengths;
	}

	public Entity getEntity() {
		return entity;
	}
}
