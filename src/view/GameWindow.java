package view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import controller.FrameListener;
import controller.PlayGameListener;
import controller.MagicKeyListenerWrapper;
import controller.MouseListenerImpl;
import controller.MouseMotionListenerImpl;
import model.MainEngine;

public class GameWindow {
	private MainEngine model;
	private JFrame frame;

	/* other GUI components */
	private PlayBoard gameBoard;
	private BuildBoard buildBoard;

	/* Listeners */
	private FrameListener windowHandler;
	private KeyListener keyboardListener;
	private MouseListener mouseBasicHandler;
	private MouseMotionListener mouseMovingHandler;

	public GameWindow() {
		// TODO

		keyboardListener = new MagicKeyListenerWrapper(keyboardListener);
		mouseBasicHandler = new MouseListenerImpl();
		mouseMovingHandler = new MouseMotionListenerImpl();

		// TODO
	}
}
