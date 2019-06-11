package rs.raf.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import rs.raf.model.Attribute;
import rs.raf.model.DBFile;
import rs.raf.model.Entity;
import rs.raf.model.InfViewNode;
import rs.raf.model.SEKFile;
import rs.raf.model.SERFile;
import rs.raf.model.TableDataModel;
import rs.raf.model.UIAbstractFile;
import rs.raf.model.UIFileField;
import rs.raf.utilities.FileUtilities;
import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class FileView extends JPanel implements Observer {
	private UIAbstractFile uiFile;
	private JTable table;
	private JPanel panTop;
	private JTextField txtBlockSize;
	private JTextField txtFileSize;
	private JTextField txtRecordSize;
	private JTextField txtRecordNum;
	private JTextField txtBlockNum;
	private Entity selectedNode;

	public FileView(final UIAbstractFile uiFile, InfViewNode selectedNode) {
		super();
		setLayout(new BorderLayout());
		uiFile.addObserver(this);
		this.uiFile = uiFile;
		try {
			uiFile.readHeader();
		} catch (IOException | SQLException e) {
		}
		// uiFile.addUpdateBlockListener(this);
		panTop = new JPanel(new BorderLayout());
		initPanParams();
		initPanToolbar();
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new TableDataModel(uiFile.getFields(), uiFile.getData()));
		JScrollPane scr = new JScrollPane(table);
		add(scr, BorderLayout.CENTER);
		this.selectedNode = (Entity) selectedNode;
	}

	private void initPanParams() {
		JPanel panParams = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// velicina bloka - moze da se menja
		panParams.add(new JLabel("f (block factor):"));
		txtBlockSize = new JTextField();
		txtBlockSize.setColumns(5);
		txtBlockSize.setText(String.valueOf(uiFile.getBLOCK_FACTOR()));
		panParams.add(txtBlockSize);
		JButton btnChangeBS = new JButton("Set block size");
		btnChangeBS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiFile.setBLOCK_SIZE(Integer.valueOf(txtBlockSize.getText()).longValue());
				txtBlockNum.setText(String.valueOf(uiFile.getBLOCK_NUM()));
			}
		});
		panParams.add(btnChangeBS);
		// velicina datoteke
		panParams.add(new JLabel("File size:"));
		txtFileSize = new JTextField();
		txtFileSize.setColumns(7);
		txtFileSize.setEnabled(false);
		// txtFileSize.setText(String.valueOf(Math.ceil(uiFile.getFILE_SIZE() /
		// 1024.0000)) + "KB");
		txtFileSize.setText(String.valueOf(Math.ceil(uiFile.getFILE_SIZE() / 1024.0000)) + "KB");
		panParams.add(txtFileSize);
		// velicina linije u datoteci
		panParams.add(new JLabel("Record size(B):"));
		txtRecordSize = new JTextField();
		txtRecordSize.setColumns(7);
		txtRecordSize.setEnabled(false);
		// txtRecordSize.setText(String.valueOf(uiFile.getRECORD_SIZE()));
		txtRecordSize.setText(String.valueOf(uiFile.getRECORD_SIZE()));
		panParams.add(txtRecordSize);
		// broj recorda u datoteci
		panParams.add(new JLabel("Record num:"));
		txtRecordNum = new JTextField();
		txtRecordNum.setColumns(7);
		txtRecordNum.setEnabled(false);
		txtRecordNum.setText(String.valueOf(uiFile.getRECORD_NUM()));
		panParams.add(txtRecordNum);
		// broj blokova u datoteci
		panParams.add(new JLabel("Block num:"));
		txtBlockNum = new JTextField();
		txtBlockNum.setColumns(7);
		txtBlockNum.setEnabled(false);
		txtBlockNum.setText(String.valueOf(uiFile.getBLOCK_NUM()));
		panParams.add(txtBlockNum);
		panParams.setBackground(new Color(153, 204, 255));
		panTop.add(panParams, BorderLayout.NORTH);
	}

	private void initPanToolbar() {
		JPanel panToolbar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton btnFetch = new JButton("Fetch next block");
		btnFetch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiFile.setMODE(SERFile.BROWSE_MODE);
				try {
					uiFile.fetchNextBlock();
					// Remove empty rows
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					ArrayList<Attribute> attributes = Collections
							.list((Enumeration<Attribute>) selectedNode.children());
					int numberOfColumns = attributes.size();
					for (int rows = 0; rows < model.getRowCount(); rows++) {
						int numberOfNulls = 0;
						for (int columns = 0; columns < model.getColumnCount(); columns++) {
							if (model.getValueAt(rows, columns) == null)
								numberOfNulls++;
						}
						if (numberOfNulls == numberOfColumns) {
							model.removeRow(rows);
						}
					}
				} catch (IOException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		panToolbar.add(btnFetch);
		JButton btnAdd = new JButton("Add Record");
		panToolbar.add(btnAdd);
		JButton btnDelete = new JButton("Delete Record");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (uiFile instanceof SERFile) {
					int row = table.getSelectedRow();
					if (row == -1) {
						MainFrame.getInstance().getExceptionsManager().errorYouMustSelectElementToDelete();
						return;
					}
					ArrayList<Attribute> attributes = Collections
							.list((Enumeration<Attribute>) selectedNode.children());
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
					String path = selectedNode.getLocation() + selectedNode.getFile().replaceAll(".ser", ".txt").trim()
							+ ".del";
					ArrayList<String> deletedLines = null;
					try {
						deletedLines = FileUtilities.loadFile(path);
					} catch (IOException e1) {
						e1.printStackTrace();
						MainFrame.getInstance().getExceptionsManager().errorFailedToDelete();
					}
					if (deletedLines != null) {
						boolean alreadyDeleted = false;
						for (String currentLine : deletedLines) {
							if (deletedLine.contains(currentLine)) {
								alreadyDeleted = true;
								break;
							}
						}
						if (alreadyDeleted) {
							MainFrame.getInstance().getExceptionsManager().errorAlreadyDeleted();
							return;
						}
					}
					try {
						File file = new File(path);
						if (!file.exists())
							file.createNewFile();
						Files.write(Paths.get(path), (deletedLine + System.getProperty("line.separator")).getBytes(),
								StandardOpenOption.APPEND);
						((DefaultTableModel) table.getModel()).removeRow(row);
						MainFrame.getInstance().getExceptionsManager().successfullyDeletedRow();
					} catch (IOException exce) {
						exce.printStackTrace();
						MainFrame.getInstance().getExceptionsManager().errorFailedToDelete();
					}
				}
			}
		});
		panToolbar.add(btnDelete);
		JButton btnRead = new JButton("Find Record");
		btnRead.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.getInstance().getRightPanel().getStatePanel().setSearchState();
			}
		});
		panToolbar.add(btnRead);
		if (uiFile instanceof SEKFile) {
			JButton btnBinary = new JButton("Find Record (Binary Search)");
			btnBinary.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					MainFrame.getInstance().getRightPanel().getStatePanel().setBinarySearchState();
				}
			});
			panToolbar.add(btnBinary);
		}
		panTop.add(panToolbar, BorderLayout.CENTER);
		add(panTop, BorderLayout.NORTH);
		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				MainFrame.getInstance().getRightPanel().getStatePanel().setSortState();
			}
		});
		panToolbar.add(btnSort);
		if (uiFile instanceof SERFile) {
			JButton btnRecycleBin = new JButton("Recycle bin");
			btnRecycleBin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainFrame.getInstance().getRightPanel().getStatePanel().setRecyleBinState();
				}
			});
			panToolbar.add(btnRecycleBin);
		}

		if (uiFile instanceof DBFile) {
			JButton btnFilter = new JButton("Filter");

			btnFilter.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					MainFrame.getInstance().getRightPanel().getStatePanel().setFilterState(uiFile, selectedNode);
				}
			});

			panToolbar.add(btnFilter);
		}

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (uiFile instanceof DBFile)
					MainFrame.getInstance().getRightPanel().getStatePanel().setAddRecordState();

			}
		});

		if (uiFile instanceof DBFile) {
			JButton avg = new JButton("AVG");
			avg.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainFrame.getInstance().getRightPanel().getStatePanel().setAvgState();
				}
			});
			panToolbar.add(avg);
		}

		if (uiFile instanceof DBFile) {
			JButton update = new JButton("Update");
			update.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainFrame.getInstance().getRightPanel().getStatePanel().setUpdateRecordState();
				}
			});
			panToolbar.add(update);
		}

	}

	public JTable getTable() {
		return table;
	}

	public static void setRecordForSearch(ArrayList<String> resultRecord) {
	}

	public UIAbstractFile getUIFile() {
		return uiFile;
	}

	public Entity getEntity() {
		return this.selectedNode;
	}

	public UIAbstractFile getFile() {
		return uiFile;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		table.setModel(new TableDataModel(uiFile.getFields(), uiFile.getData()));
	}

	public void setAvgView() {
		ArrayList<UIFileField> fields = new ArrayList<>();
		fields.add(new UIFileField("AVG", "", 0, false));
		table.setModel(new TableDataModel(fields, uiFile.getData()));
	}
}