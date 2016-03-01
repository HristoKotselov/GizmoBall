package absorber;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConnectKeyListener implements KeyListener {
	private Model model;
	
	private Absorber abs;		// abs SHOULD NOT BE HERE for final release
	
	public ConnectKeyListener(Model m, Absorber a){
		model = m;
		abs = a;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(model.isPlaying()){
			if(e.getKeyCode() == KeyEvent.VK_L){		// SHOULD HAVE BEEN check for a key is within Key-Connection List (Connections), but for the demo it is ok
				abs.triggerAction();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
