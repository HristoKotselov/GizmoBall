package view;

import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class KeyBindPopup {
	private JFrame popup;

	public KeyBindPopup(KeyListener listener) {
		popup = new JFrame("Bind Key");
		popup.setType(Type.POPUP);
		popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popup.setLocationRelativeTo(null);

		JLabel l = new JLabel("Press key to bind");
		l.setFont(new Font("Arial", 1, 15));
		l.setFocusable(true);
		l.addKeyListener(listener);

		popup.add(l);

		popup.pack();
		popup.setVisible(true);
	}
	
	public void dispose(){
		popup.dispose();
	}
}