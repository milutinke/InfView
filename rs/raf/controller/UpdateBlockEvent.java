package rs.raf.controller;


import java.util.EventObject;

@SuppressWarnings("serial")
public class UpdateBlockEvent extends EventObject {
	public UpdateBlockEvent(Object source) {
		super(source);
	}
}
