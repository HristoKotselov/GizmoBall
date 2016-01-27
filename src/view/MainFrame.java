package view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import controller.FrameListener;
import controller.KeyListenerImpl;
import controller.MagicKeyListenerWrapper;
import controller.MouseListenerImpl;
import controller.MouseMotionListenerImpl;
import model.MainEngine;

public class MainFrame extends JFrame {
	private MainEngine model;

	/* other GUI components */
	private GameBoard gameBoard;
	private BuildBoard buildBoard;

	/* Listeners */
	private FrameListener windowHandler;
	private KeyListener keyboardListener;
	private MouseListener mouseBasicHandler;
	private MouseMotionListener mouseMovingHandler;

	public MainFrame() {
		// TODO

		keyboardListener = new MagicKeyListenerWrapper(keyboardListener);
		mouseBasicHandler = new MouseListenerImpl();
		mouseMovingHandler = new MouseMotionListenerImpl();

		// TODO
	}
}
