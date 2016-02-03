package controller;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.event.MouseInputListener;

public interface IMainListener extends ActionListener {

	public void setKeyBoardListener(KeyListener kl);

	public void setMouseListener(MouseInputListener ml);

}
