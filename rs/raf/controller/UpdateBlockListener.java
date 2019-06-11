package rs.raf.controller;

import java.util.EventListener;


public interface UpdateBlockListener extends EventListener{
	public void updateBlockPerformed(UpdateBlockEvent e);
}
