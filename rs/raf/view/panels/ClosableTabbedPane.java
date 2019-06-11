package rs.raf.view.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JTabbedPane;

import rs.raf.model.InfViewNode;

@SuppressWarnings({ "serial" })
public class ClosableTabbedPane extends JTabbedPane {
	private TabCloseUI closeUI = new TabCloseUI(this);
	private InfViewNode tabNode = null;

	public void paint(Graphics g) {
		super.paint(g);
		closeUI.paint(g);
	}

	public void addTab(String title, InfViewNode node, Component component) {
		String indentation = "";

		for (int numberOfCharacter = 0; numberOfCharacter < title.length(); numberOfCharacter++)
			indentation += " ";

		super.addTab(title + indentation, component);
		tabNode = node;
	}

	public String getTabTitleAt(int index) {
		if (index < 0) {
			System.out.println("getTabTitleAt - Invalid index provided!");
			return "Invalid Index";
		}

		return super.getTitleAt(index).trim();
	}

	public void closeTabWithIndex(int index) {
		if (index < 0 || index > super.getTabCount())
			return;

		this.removeTabAt(index);
	}

	private class TabCloseUI implements MouseListener, MouseMotionListener {
		private ClosableTabbedPane tabbedPane;
		private int closeX = 0, closeY = 0, mouseEventX = 0, mouseEventY = 0;
		private int selectedTab;
		private final int width = 8, height = 8;

		private Rectangle rectangle = new Rectangle(0, 0, width, height);

		@SuppressWarnings("unused")
		private TabCloseUI() {
		}

		public TabCloseUI(ClosableTabbedPane pane) {
			tabbedPane = pane;
			tabbedPane.addMouseMotionListener(this);
			tabbedPane.addMouseListener(this);
		}

		public void mouseEntered(MouseEvent me) {
		}

		public void mouseExited(MouseEvent me) {
		}

		public void mousePressed(MouseEvent me) {
		}

		public void mouseClicked(MouseEvent me) {
		}

		public void mouseDragged(MouseEvent me) {
		}

		public void mouseReleased(MouseEvent mouseEvent) {
			if (closeUnderMouse(mouseEvent.getX(), mouseEvent.getY())) {
				boolean isToCloseTab = tabAboutToClose(selectedTab);
				if (isToCloseTab && selectedTab > -1) {
					/*
					 * if (selectedTab == 0 && tabbedPane.getTabCount() <= 1) { while
					 * (MainFrame.getInstance().getRightPanel().getTabsPanel().getTabCount() > 0) {
					 * MainFrame.getInstance().getRightPanel().remove(1); }
					 * 
					 * return; }
					 */

					tabbedPane.removeTabAt(selectedTab);
				}

				selectedTab = tabbedPane.getSelectedIndex();
			}
		}

		public void mouseMoved(MouseEvent mouseEvent) {
			mouseEventX = mouseEvent.getX();
			mouseEventY = mouseEvent.getY();

			if (mouseOverTab(mouseEventX, mouseEventY)) {
				controlCursor();
				tabbedPane.repaint();
			}
		}

		private void controlCursor() {
			if (tabbedPane.getTabCount() > 0)
				if (closeUnderMouse(mouseEventX, mouseEventY)) {
					tabbedPane.setCursor(new Cursor(Cursor.HAND_CURSOR));

					if (selectedTab > -1)
						tabbedPane.setToolTipTextAt(selectedTab, "Close " + tabbedPane.getTitleAt(selectedTab));
				} else {
					tabbedPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

					if (selectedTab > -1)
						tabbedPane.setToolTipTextAt(selectedTab, tabbedPane.getTitleAt(selectedTab));
				}
		}

		private boolean closeUnderMouse(int x, int y) {
			rectangle.x = closeX;
			rectangle.y = closeY;

			return rectangle.contains(x, y);
		}

		public void paint(Graphics g) {
			for (int index = 0; index < tabbedPane.getTabCount(); index++) {
				if (tabbedPane.getComponent(index).isShowing()) {
					int x = tabbedPane.getBoundsAt(index).x + tabbedPane.getBoundsAt(index).width - width - 5;
					int y = tabbedPane.getBoundsAt(index).y + 5;
					drawClose(g, x, y);
					break;
				}
			}

			if (mouseOverTab(mouseEventX, mouseEventY))
				drawClose(g, closeX, closeY);
		}

		private void drawClose(Graphics g, int x, int y) {
			if (tabbedPane != null && tabbedPane.getTabCount() > 0) {
				Graphics2D g2 = (Graphics2D) g;
				drawColored(g2, isUnderMouse(x, y) ? Color.RED : Color.WHITE, x, y);
			}
		}

		private void drawColored(Graphics2D graphic2, Color color, int x, int y) {
			graphic2.setStroke(new BasicStroke(5, BasicStroke.JOIN_ROUND, BasicStroke.CAP_ROUND));
			graphic2.setColor(Color.BLACK);
			graphic2.drawLine(x, y, x + width, y + height);
			graphic2.drawLine(x + width, y, x, y + height);
			graphic2.setColor(color);
			graphic2.setStroke(new BasicStroke(3, BasicStroke.JOIN_ROUND, BasicStroke.CAP_ROUND));
			graphic2.drawLine(x, y, x + width, y + height);
			graphic2.drawLine(x + width, y, x, y + height);
		}

		private boolean isUnderMouse(int x, int y) {
			return Math.abs(x - mouseEventX) < width && Math.abs(y - mouseEventY) < height;
		}

		private boolean mouseOverTab(int x, int y) {
			int tabCount = tabbedPane.getTabCount();

			for (int index = 0; index < tabCount; index++) {
				if (tabbedPane.getBoundsAt(index).contains(mouseEventX, mouseEventY)) {
					selectedTab = index;
					closeX = tabbedPane.getBoundsAt(index).x + tabbedPane.getBoundsAt(index).width - width - 5;
					closeY = tabbedPane.getBoundsAt(index).y + 5;
					return true;
				}
			}

			return false;
		}
	}

	public boolean tabAboutToClose(int tabIndex) {
		return true;
	}

	public InfViewNode getTabNode() {
		return tabNode;
	}

	public void setTabNode(InfViewNode tabNode) {
		this.tabNode = tabNode;
	}
}
