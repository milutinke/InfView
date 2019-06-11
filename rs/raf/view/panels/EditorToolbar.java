package rs.raf.view.panels;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import rs.raf.actions.InfViewAbstractAction;
import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class EditorToolbar extends JToolBar {
	
	public EditorToolbar() {
		super(SwingConstants.HORIZONTAL);
		setFloatable(true);

	

		add(createNewStylisedButton(MainFrame.getInstance().getActionManager().getNewEntityAction()));
		addSeparator();

		add(createNewStylisedButton(MainFrame.getInstance().getActionManager().getNewAttributeAction()));
		addSeparator();

		add(createNewStylisedButton(MainFrame.getInstance().getActionManager().getNewRelationAction()));
		addSeparator();
		
		add(createNewStylisedButton(MainFrame.getInstance().getActionManager().getEditEntityAction()));
		addSeparator();
		
		add(createNewStylisedButton(MainFrame.getInstance().getActionManager().getEditAttributeAction()));
		addSeparator();
		
		add(createNewStylisedButton(MainFrame.getInstance().getActionManager().getEditRelationAction()));
		addSeparator();
		
		add(createNewStylisedButton(MainFrame.getInstance().getActionManager().getDeleteEntityAction()));
		addSeparator();
		
		add(createNewStylisedButton(MainFrame.getInstance().getActionManager().getDeleteAttributeAction()));
		addSeparator();
		
		add(createNewStylisedButton(MainFrame.getInstance().getActionManager().getDeleteRelationAction()));
		addSeparator();
		
	}

	@SuppressWarnings("static-access")
	public JButton createNewStylisedButton(InfViewAbstractAction abstractAction) {
		JButton button = new JButton(abstractAction);
		button.setText("");
		button.setToolTipText((String) abstractAction.getValue(abstractAction.NAME));
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);

		return button;
	}
}


