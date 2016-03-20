package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import view.BuildMenu;
import view.IBuildMenu;

public class BuildModeFunctionChangeListener implements ItemListener {
	private IBuildMenu bm;

	public BuildModeFunctionChangeListener(IBuildMenu bm) {
		this.bm = bm;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		bm.setCard();
	}
}