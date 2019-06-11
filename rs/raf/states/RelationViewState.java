package rs.raf.states;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import rs.raf.model.DBFile;
import rs.raf.model.Entity;
import rs.raf.model.INDFile;
import rs.raf.model.Packet;
import rs.raf.model.Resource;
import rs.raf.model.SEKFile;
import rs.raf.model.SERFile;
import rs.raf.utilities.MetaSchemeUtils;
import rs.raf.view.panels.ClosableTabbedPane;
import rs.raf.view.panels.FileView;
import rs.raf.view.panels.StatePanel;

public class RelationViewState extends State {
	private StatePanel mediator;
	private Entity entity = null;

	public RelationViewState(StatePanel mediator, Entity entity) {
		this.mediator = mediator;
		this.entity = entity;
	}

	@Override
	public void show() {
		if (entity == null)
			return;

		Resource resource = (Resource) entity.getParent().getParent().getParent();

		// Skip non relational bases
		if (resource == null || !(resource.getType().equals("file-relational") || resource.getType().equals("sql")))
			return;

		mediator.setMainPanel(generateView(MetaSchemeUtils.getEntityRelations(entity), entity));
	}

	private JPanel generateView(ArrayList<Entity> relatedEntites, Entity entity) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		ClosableTabbedPane tabbedPane = new ClosableTabbedPane();

		relatedEntites.forEach(relatedEntity -> addTabs(tabbedPane, relatedEntity));

		panel.add(tabbedPane);
		return panel;
	}

	private void addTabs(ClosableTabbedPane tabbedPane, Entity entity) {
		String name = entity.getName();
		String file = entity.getFile();
		String location = entity.getLocation();
		FileView view = null;

		if (file != null) {
			if (file.contains(".ser")) {
				view = new FileView(new SERFile(name, location, file), entity);
			} else if (file.contains(".sek")) {
				view = new FileView(new SEKFile(name, location, file), entity);
			} else if (file.contains(".ind")) {
				view = new FileView(new INDFile(name, location, file), entity);
			}
		} else {
			DBFile db = new DBFile(name);
			Packet packet = (Packet) entity.getParent().getParent();
			Resource resource = (Resource) packet.getParent();

			db.setConnector(resource.getSqlConnection());
			view = new FileView(db, entity);
		}

		if (view == null)
			return;

		tabbedPane.addTab(entity.getName(), entity, view);
	}
}
