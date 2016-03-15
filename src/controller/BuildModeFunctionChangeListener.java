package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import view.BuildMenu;

public class BuildModeFunctionChangeListener implements ItemListener {
	private BuildMenu bm;

	public BuildModeFunctionChangeListener(BuildMenu bm) {
		this.bm = bm;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		bm.setCard();
	}
}