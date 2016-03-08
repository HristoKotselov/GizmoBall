package escape.view;

import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import escape.controller.RunListener;
import escape.model.Ball;
import escape.model.Model;

/**
 * part of the code is from Murray's Demonstration of MVC and MIT Physics
 * Collisions 2014
 */

public class RunGui {

	private Model model;
	private JFrame frame;
	private ActionListener listener;
	private Board board;
	private JPanel buttons;

	public RunGui(Model m) {
		model = m;

		// RunListener catches all GUI events. In reality might have many
		// listeners.
		listener = new RunListener(m);
	}

	public void createAndShowGUI() {
		frame = new JFrame("Collision Prototype");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Board is passed the Model so it can act as Observer
		board = new Board(400, 100, model);

		Container cp = frame.getContentPane();

		Font gf = new Font("Arial", Font.BOLD, 12);

		buttons = new JPanel();
		buttons.setLayout(new GridLayout(4, 1));

		JButton button1 = new JButton("Start");
		button1.setFont(gf);
		button1.addActionListener(listener);
		button1.setMaximumSize(new Dimension(100, 100));
		buttons.add(button1);

		JButton button2 = new JButton("Stop");
		button2.setFont(gf);
		button2.addActionListener(listener);
		button2.setMaximumSize(new Dimension(100, 100));
		buttons.add(button2);

		JButton button4 = new JButton("Tick");
		button4.setFont(gf);
		button4.addActionListener(listener);
		button4.setMaximumSize(new Dimension(100, 100));
		buttons.add(button4);

		JButton button5 = new JButton("Escape");
		button5.setFont(gf);
		button5.addActionListener(new EscapeListener());
		button5.setMaximumSize(new Dimension(100, 100));
		buttons.add(button5);

		JButton button3 = new JButton("Quit");
		button3.setFont(gf);
		button3.addActionListener(listener);
		button3.setMaximumSize(new Dimension(100, 100));
		buttons.add(button3);

		cp.add(buttons, BorderLayout.LINE_START);
		cp.add(board, BorderLayout.CENTER);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	class EscapeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evt) {
//			BufferedImage capture = null;
//			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//
//
//			try {
//				capture = new Robot().createScreenCapture(screenRect);
//				ImageIO.write(capture, "PNG", new File("java-screen-shot-1.png"));
//				
//				Rectangle rec = frame.getBounds();
//				capture = new BufferedImage(rec.width, rec.height, BufferedImage.TYPE_INT_ARGB);;
//				
//				frame.paint(capture.getGraphics());
//				ImageIO.write(capture, "PNG", new File("java-screen-shot-2.png"));
//			} catch (AWTException e) {
//			} catch (IOException e) {
//			}
			JFrame f2 = new JFrame();

			
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//			GraphicsDevice gd = ge.getDefaultScreenDevice();
			f2.setBounds(0, 0, ge.getMaximumWindowBounds().width, ge.getMaximumWindowBounds().height);

//			f2.setType(JFrame.Type.POPUP);		// Hides window from task bar
			f2.setUndecorated(true);

//			f2.setSize(50, 50);
//			f2.setLocationRelativeTo(null);

			f2.setBackground(new Color(0, 0, 0, 50));

//			f2.setContentPane(new MyPanel());
//			f2.getContentPane().setBackground(Color.BLACK);
//			f2.setLayout(new BorderLayout());
//			f2.setExtendedState(JFrame.MAXIMIZED_BOTH);
//			f2.seto

//			frame.remove(buttons);
//			frame.remove(board);

//			frame.setVisible(false);
//			frame.setUndecorated(true);

//
//
//
			MyPanel2 p = new MyPanel2(model);
			f2.setGlassPane(p);
			
//			p.setOpaque(true);
			p.setVisible(true);
			
			p.repaint();
			

//			p.add(board);
//

			f2.setVisible(true);
		}
	}
}

class MyPanel extends JPanel {
	public MyPanel() {
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g2.setColor(getBackground());
		g2.fill(getBounds());
		g2.dispose();
	}
}

class MyPanel2 extends JPanel {
	Model m;

	public MyPanel2(Model m) {
		super();
		this.m = m;
	}

	@Override
	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);

//		Graphics2D g2 = (Graphics2D) g;

//		Ball b = m.getBall();
		
		g.setColor(Color.red);
		g.fillRect(50, 50, 100, 100);

//		if (b != null) {
//			g2.setColor(b.getColour());
//			int x = (int) (b.getExactX() - b.getRadius());
//			int y = (int) (b.getExactY() - b.getRadius());
//			int width = (int) (2 * b.getRadius());
//			g2.fillOval(x, y, width, width);
//		}
	}
}
