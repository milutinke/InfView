package rs.raf.view.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import rs.raf.model.Attribute;
import rs.raf.model.Entity;
import rs.raf.model.RecycleBinTableModel;
import rs.raf.model.SERFile;
import rs.raf.utilities.FileUtilities;
import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class RecycleBinView extends JPanel {
	private JTable table;

	public RecycleBinView() {
		super(new BorderLayout());
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		Entity entity = ((FileView) MainFrame.getInstance().getRightPanel().getTopPanel().getSelectedComponent())
				.getEntity();
		ArrayList<Attribute> attributes = Collections.list((Enumeration<Attribute>) entity.children());

		try {
			table.setModel(new RecycleBinTableModel(attributes, SERFile.loadRecycleBin(entity)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JScrollPane scr = new JScrollPane(table);
		add(scr, BorderLayout.CENTER);

		JButton buttonUndelte = new JButton("Restore");

		buttonUndelte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();

				if (row == -1) {
					MainFrame.getInstance().getExceptionsManager().errorYouMustSelectElementToRestore();
					return;
				}

				int numberOfColumns = attributes.size();
				String deletedLine = "";

				for (int i = 0; i < numberOfColumns; i++) {
					Attribute currentAttribute = attributes.get(i);
					int length = (Integer) currentAttribute.getLength();
					String currentString = new String(table.getModel().getValueAt(row, i).toString());

					if (currentString.length() < length) {
						for (int j = length - currentString.length(); j > 0; j--)
							currentString += " ";
					}

					deletedLine += currentString;
				}

				String path = entity.getLocation() + entity.getFile().replaceAll(".ser", ".txt").trim() + ".del";
				ArrayList<String> deletedLines = null;

				try {
					deletedLines = FileUtilities.loadFile(path);
				} catch (IOException e1) {
					e1.printStackTrace();
					MainFrame.getInstance().getExceptionsManager().errorFailedToDelete();
				}

				if (deletedLines != null) {
					int current = 0;
					for (String currentLine : deletedLines) {
						if (deletedLine.contains(currentLine)) {
							deletedLines.remove(current);
							break;
						}

						current++;
					}
				}

				try {
					File file = new File(path);

					if (file.exists())
						file.delete();

					file.createNewFile();

					PrintWriter printWriter = new PrintWriter(new FileWriter(path));

					for (String currentLine : deletedLines)
						printWriter.write(currentLine + System.getProperty("line.separator"));

					printWriter.close();

					((DefaultTableModel) table.getModel()).removeRow(row);
					MainFrame.getInstance().getExceptionsManager().successfullyDeletedRow();
				} catch (IOException exce) {
					exce.printStackTrace();
					MainFrame.getInstance().getExceptionsManager().errorFailedToDelete();
				}
			}
		});

		add(buttonUndelte, BorderLayout.SOUTH);
	}
}
