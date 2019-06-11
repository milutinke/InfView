package rs.raf.view.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rs.raf.settings.Settings;
import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class AboutPanel extends JPanel {
	public AboutPanel() {
		initGui();
	}

	private void initGui() {

		add(Box.createRigidArea(new Dimension(30, 30)));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(createOnePersonPanel(Settings.AUTHOR_DUSAN, "Dusan Milutinovic"));
		add(Box.createRigidArea(new Dimension(30, 30)));
		add(createOnePersonPanel(Settings.AUTHOR_MAJA, "Maja Jugo"));
		add(Box.createRigidArea(new Dimension(30, 30)));
		add(createOnePersonPanel(Settings.AUTHOR_STRAJINJA, "Strahinja Obradovic"));

	}

	private JPanel createOnePersonPanel(String imagePath, String text) {
		JPanel panel = createRow();
		BufferedImage img;
		
		try {
			img = ImageIO.read(new File(Settings.ICON_FOLDER_PATH + imagePath));
			Image tmp = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			BufferedImage dimg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2d = dimg.createGraphics();
			g2d.drawImage(tmp, 0, 0, null);
			g2d.dispose();

			JLabel image = new JLabel(new ImageIcon(dimg));

			JLabel label = new JLabel(text);

			addToRow(panel, image, 10);
			addToRow(panel, label, 10);

			panel.setAlignmentX(Component.LEFT_ALIGNMENT);

		} catch (IOException e) {
			MainFrame.getInstance().getExceptionsManager().errorPictureCantBeLoaded();
		}

		return panel;

	}

	private JPanel createRow() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		return panel;
	}

	private void addToRow(JPanel row, JComponent c, int margin) {
		row.add(Box.createRigidArea(new Dimension(margin, margin)));
		row.add(c);
		row.add(Box.createRigidArea(new Dimension(margin, margin)));
	}

}
