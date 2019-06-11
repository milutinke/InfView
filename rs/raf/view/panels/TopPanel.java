package rs.raf.view.panels;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rs.raf.model.Entities;
import rs.raf.model.Entity;
import rs.raf.model.Packet;
import rs.raf.model.Resource;
import rs.raf.utilities.NodeHelper;
import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class TopPanel extends ClosableTabbedPane {
	public TopPanel() {
		this.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				if (MainFrame.getInstance().getRightPanel().getTopPanel().getSelectedIndex() < 0) {
					MainFrame.getInstance().getRightPanel().getStatePanel().setDefaultState();
					return;
				}

				String name = MainFrame.getInstance().getRightPanel().getTopPanel()
						.getTabTitleAt(MainFrame.getInstance().getRightPanel().getTopPanel().getSelectedIndex());
				Resource currentResource = MainFrame.getInstance().getCurrentResource();

				if (currentResource == null)
					return;

				Packet packet = (Packet) currentResource.getChildAt(0);

				if (packet == null)
					return;

				Entities entities = (Entities) packet.getChildAt(0);

				if (entities == null)
					return;

				Entity entity = (Entity) NodeHelper.findNode(entities, Entity.class, name);

				if (entity == null)
					return;

				MainFrame.getInstance().getRightPanel().getStatePanel().setRelationViewState(entity);
			}
		});
	}
}