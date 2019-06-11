package rs.raf.view.frames;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import rs.raf.actions.ActionManager;
import rs.raf.model.Resource;
import rs.raf.settings.Settings;
import rs.raf.utilities.ExceptionsManager;
import rs.raf.view.panels.LeftPanel;
import rs.raf.view.panels.MenuBar;
import rs.raf.view.panels.RightPanel;
import rs.raf.view.panels.ToolBar;
import rs.raf.view.tree.InfViewTree;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private static MainFrame instance = null;

	// Components
	public LeftPanel leftPanel;
	public RightPanel rightPanel;
	public MenuBar menuBar;
	public ToolBar toolBar;
	public InfViewTree tree;

	// Managers
	private ActionManager actionManager;
	private ExceptionsManager exceptionsManager;

	// Meta Meta Scheme Info
	private String metaMetaSchemeLocation;

	// Current Selected Resource
	private Resource currentResource = null;

	// Saved
	private boolean saved = true;

	private MainFrame() {
		super();
	}

	public void init() throws FileNotFoundException {
		// Initialise Managers
		actionManager = new ActionManager();
		exceptionsManager = new ExceptionsManager(this);

		// Intialise Tree
		tree = new InfViewTree();

		// Initialise GUI
		InitGui();
	}

	public void InitGui() throws FileNotFoundException {
		// Initialize Window
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMaximizedBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(Settings.APP_NAME);

		// Icon
		setIconImage(new ImageIcon(Settings.ICON_FOLDER_PATH + Settings.APP_ICON).getImage());

		// Menu Bar
		setJMenuBar(new MenuBar());

		// Toolbar
		toolBar = new ToolBar();
		add(toolBar, BorderLayout.NORTH);

		// Left panel
		leftPanel = new LeftPanel();
		add(leftPanel, BorderLayout.WEST);

		// Right panel
		rightPanel = new RightPanel();
		add(rightPanel, BorderLayout.CENTER);

		// Set look and feel
		try {
			UIManager.setLookAndFeel(
					/* UIManager.getCrossPlatformLookAndFeelClassName() */ getLookAndFeelClassName("Nimbus"));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		setVisible(true);
	}

	public static String getLookAndFeelClassName(String snippetName) {
		LookAndFeelInfo[] lookandFeelInfos = UIManager.getInstalledLookAndFeels();

		for (LookAndFeelInfo info : lookandFeelInfos) {
			if (info.getName().contains(snippetName)) {
				return info.getClassName();
			}
		}
		return null;
	}

	public static MainFrame getInstance() {
		if (instance == null)
			instance = new MainFrame();

		return instance;
	}

	public ActionManager getActionManager() {
		return actionManager;
	}

	public ExceptionsManager getExceptionsManager() {
		return exceptionsManager;
	}

	public LeftPanel getLeftPanel() {
		return leftPanel;
	}

	public RightPanel getRightPanel() {
		return rightPanel;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public InfViewTree getTree() {
		return tree;
	}

	public String getMetaMetaSchemeLocation() {
		return metaMetaSchemeLocation;
	}

	public void setMetaMetaSchemeLocation(String metaMetaSchemeLocation) {
		this.metaMetaSchemeLocation = metaMetaSchemeLocation;
	}

	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	public Resource getCurrentResource() {
		return currentResource;
	}

	public void setCurrentResource(Resource currentResource) {
		this.currentResource = currentResource;
	}
}