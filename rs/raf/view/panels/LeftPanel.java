package rs.raf.view.panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class LeftPanel extends JScrollPane {
	public LeftPanel() {
		super(MainFrame.getInstance().getTree());

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(300, 100));
		setBorder(BorderFactory.createLineBorder(Color.gray));
	}
}
