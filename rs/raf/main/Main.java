package rs.raf.main;

import java.awt.EventQueue;
import java.io.FileNotFoundException;

import rs.raf.view.frames.LoadingFrame;
import rs.raf.view.frames.MainFrame;

public class Main {
	public static final void main(String[] args) throws InterruptedException {
		LoadingFrame loadingFrame = new LoadingFrame();

		Thread.sleep(500);

		loadingFrame.close();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame.getInstance().init();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}
}