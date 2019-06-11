package rs.raf.states;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import rs.raf.model.Attribute;
import rs.raf.model.DBFile;
import rs.raf.model.Entity;
import rs.raf.model.UIAbstractFile;
import rs.raf.utilities.MetaSchemeUtils;
import rs.raf.view.frames.MainFrame;
import rs.raf.view.panels.StatePanel;

public class FilterState extends State {
	private StatePanel mediator;
	private Entity entity = null;
	private UIAbstractFile uiFile = null;
	private HashMap<ReferencedComponents, String> referenced;

	private class ReferencedComponents {
		private JComboBox<Attribute> fields;
		private JComboBox<String> operations;
		private JTextField value;

		public ReferencedComponents(JComboBox<Attribute> fields, JComboBox<String> operations, JTextField value) {
			this.fields = fields;
			this.operations = operations;
			this.value = value;
		}

		public JComboBox<Attribute> getFields() {
			return fields;
		}

		public JComboBox<String> getOperations() {
			return operations;
		}

		public JTextField getValue() {
			return value;
		}
	}

	public FilterState(StatePanel mediator, UIAbstractFile uiFile, Entity entity) {
		this.mediator = mediator;
		this.entity = entity;
		this.uiFile = uiFile;
		this.referenced = new HashMap<ReferencedComponents, String>();
	}

	@Override
	public void show() {
		if (entity == null || uiFile == null)
			return;

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel controlsPanel = new JPanel();
		JComboBox<String> binaryOperationsComboBox = new JComboBox<>();
		generateBinaryOperations().forEach(operation -> binaryOperationsComboBox.addItem(operation));
		binaryOperationsComboBox.setSelectedItem(0);
		controlsPanel.add(binaryOperationsComboBox);

		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.add(addFilterPanel(entity, (String) binaryOperationsComboBox.getSelectedItem()));
			}
		});

		controlsPanel.add(addButton);

		JButton filterButton = new JButton("Filter");
		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (referenced == null)
					return;

				if (referenced.size() <= 0)
					return;

				ArrayList<String> queryParts = new ArrayList<String>();
				for (Map.Entry<ReferencedComponents, String> entry : referenced.entrySet()) {
					ReferencedComponents currentReference = entry.getKey();

					if (currentReference.getFields() == null || currentReference.getOperations() == null
							|| currentReference.getValue() == null)
						continue;

					String binaryOperation = entry.getValue();

					Attribute attribute = (Attribute) currentReference.getFields().getSelectedItem();
					String operation = (String) currentReference.getOperations().getSelectedItem();
					String value = currentReference.getValue().getText();

					if (value.length() <= 0) {
						currentReference.getValue().setBorder(new LineBorder(Color.red, 2));
						currentReference.getValue().grabFocus();
						MainFrame.getInstance().getExceptionsManager().errorEmptyFields();
						return;
					}
					queryParts.add(new StringBuilder().append(attribute).append("###").append(operation).append("###")
							.append(value).append("###").append(binaryOperation).toString());
				}

				try {
					((DBFile) uiFile).filterFind(queryParts);
				} catch (SQLException e1) {
					e1.printStackTrace();
					MainFrame.getInstance().getExceptionsManager().errorFailedToFilter();
				}
			}
		});

		controlsPanel.add(filterButton);

		panel.add(controlsPanel);
		panel.add(addFilterPanel(entity, ""));

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

		mediator.setMainPanel(mainPanel);
	}

	private JPanel addFilterPanel(Entity entity, String binaryOperator) {
		JPanel panel = new JPanel();

		JComboBox<Attribute> fieldComboBox = new JComboBox<>();
		generateFields(entity).forEach(field -> fieldComboBox.addItem(field));
		fieldComboBox.setSelectedItem(0);

		JComboBox<String> operationsComboBox = new JComboBox<>();
		generateOperations((Attribute) fieldComboBox.getSelectedItem())
				.forEach(operation -> operationsComboBox.addItem(operation));

		fieldComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				operationsComboBox.removeAllItems();
				generateOperations((Attribute) fieldComboBox.getSelectedItem())
						.forEach(operation -> operationsComboBox.addItem(operation));
			}
		});

		JTextField valueField = new JTextField(30);
		// Listen for changes in the text
		valueField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateColor();
			}

			public void removeUpdate(DocumentEvent e) {
				updateColor();
			}

			public void insertUpdate(DocumentEvent e) {
				updateColor();
			}

			public void updateColor() {
				valueField.setBorder(new LineBorder(Color.gray, 1));
			}
		});

		panel.add(fieldComboBox);
		panel.add(operationsComboBox);
		panel.add(valueField);

		referenced.put(new ReferencedComponents(fieldComboBox, operationsComboBox, valueField), binaryOperator);

		return panel;
	}

	@SuppressWarnings("serial")
	public ArrayList<Attribute> generateFields(Entity entity) {
		return MetaSchemeUtils.getEntityAttributes(entity, new ArrayList<String>() {
			{
				add("int");
				add("numeric");
				add("float");
				add("double");
				add("char");
				add("varchar");
				add("string");
			}
		});
	}

	private ArrayList<String> generateOperations(Attribute field) {
		ArrayList<String> operations = new ArrayList<String>();

		if (field.getDataType().equalsIgnoreCase("char") || field.getDataType().equalsIgnoreCase("varchar")
				|| field.getDataType().equalsIgnoreCase("string")) {
			operations.add("LIKE");
			operations.add("NOT LIKE");
		} else if (field.getDataType().equalsIgnoreCase("int") || field.getDataType().equalsIgnoreCase("double")
				|| field.getDataType().equalsIgnoreCase("numeric") || field.getDataType().equalsIgnoreCase("float")) {
			operations.add("=");
			operations.add(">");
			operations.add("<");
			operations.add("<>");
			operations.add(">=");
			operations.add("<=");
		}

		return operations;
	}

	private ArrayList<String> generateBinaryOperations() {
		ArrayList<String> operations = new ArrayList<String>();

		operations.add("AND");
		operations.add("OR");

		return operations;
	}
}
