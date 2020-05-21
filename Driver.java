import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.image.*;
import java.awt.geom.AffineTransform;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

	// variables
	ArrayList<Column> columns = new ArrayList<Column>();
	boolean menu = true;

	Background b = new Background("background.PNG");
	Paddle p = new Paddle("paddle.PNG");
	Ball ball = new Ball("ball.PNG");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
		super.paintComponent(g);

		b.paint(g);

		if (menu) {
			g.setColor(Color.GRAY);
			g.fillRect(660, 120, 580, 100);
			g.setColor(Color.BLUE);
			g.setFont(new Font("Impact", 0, 70));
			g.drawString("Hit 'Enter' to Start", 700, 200);
		} else {
			p.paint(g);
			ball.paint(g);
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < columns.get(i).getLength(); j++) {
					columns.get(i).getBrickList().get(j).paint(g);
				}
			}
		}

	}

	public void update() {

		p.move();

		if (!menu) {
			ball.move();
			ball.collide(p);
			brickCollision(ball);
		}

		if (p.getX() <= -25) {
			p.setX(-24);
		}
		if (p.getX() >= 1920 - 150 + 20) {
			p.setX(1920 - 151 + 20);
		}

		/*
		 * for(int i = 0; i < 12; i++) { for(int j = 0; j < columns.get(i).getLength();
		 * j++) { System.out.println(columns.get(i).getBrickList().get(j).getX() + "," +
		 * columns.get(i).getBrickList().get(j).getY()); } }
		 */

	}

	public void makeBricks() {
		for (int i = 0; i < 12; i++) {
			int length = columns.get(i).getLength();
			for (int j = 0; j < length; j++) {
				int rand = (int) (Math.random() * (99 - 0 + 1)) + (0);
				if (rand <= 45) {
					Brick b = new Brick("brick1.PNG", i * 160, j * 54, 1);
					columns.get(i).getBrickList().add(b);
				}
				if (rand > 45 && rand <= 75) {
					Brick b = new Brick("brick2.PNG", i * 160, j * 54, 2);
					columns.get(i).getBrickList().add(b);
				}
				if (rand > 75 && rand <= 90) {
					Brick b = new Brick("brick3.PNG", i * 160, j * 54, 3);
					columns.get(i).getBrickList().add(b);
				}
				if (rand > 90 && rand <= 100) {
					Brick b = new Brick("brick4.PNG", i * 160, j * 54, 4);
					columns.get(i).getBrickList().add(b);
				}

			}
		}
	}

	public void brickCollision(Ball b) {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < columns.get(i).getBrickList().size(); j++) {
				if (b.getY() < columns.get(i).getBrickList().get(j).getY() + 55
						&& b.getX() + 19 > columns.get(i).getBrickList().get(j).getX()
						&& b.getX() + 19 < columns.get(i).getBrickList().get(j).getX() + 160) {
					b.setY(b.getY() + 6);
					b.setVy(b.getVy() * -1);
				}
				if (b.getY() < columns.get(i).getBrickList().get(j).getY() + 55
						&& b.getY() + 38 > columns.get(i).getBrickList().get(j).getY()
						&& b.getX() + 38 > columns.get(i).getBrickList().get(j).getX()
						&& b.getX() + 38 < columns.get(i).getBrickList().get(j).getX() + 38) {
					b.setX(b.getX() - 6);
					b.setVx(b.getVx() * -1);
				}
				if (b.getY() < columns.get(i).getBrickList().get(j).getY() + 55
						&& b.getY() + 38 > columns.get(i).getBrickList().get(j).getY()
						&& b.getX() < columns.get(i).getBrickList().get(j).getX() + 160
						&& b.getX() > columns.get(i).getBrickList().get(j).getX() + 160 - 38) {
					b.setX(b.getX() + 6);
					b.setVx(b.getVx() * -1);
				}

			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}

	public Driver() {
		JFrame f = new JFrame();
		f.setTitle("Brick Breaker");
		f.setSize(1920, 1080);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseListener(this);
		f.addMouseMotionListener(this);

		// initilialize
		for (int i = 0; i < 12; i++) {
			Column c = new Column(i);
			columns.add(c);
		}
		makeBricks();

		f.add(this);
		t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 10) {
			menu = false;
		}
		if (e.getKeyCode() == 39 && !menu) {
			p.setVx(17);
		}
		if (e.getKeyCode() == 37 && !menu) {
			p.setVx(-17);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 39) {
			p.setVx(0);
		}
		if (e.getKeyCode() == 37) {
			p.setVx(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		// System.out.println(e.getKeyCode());

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public void reset() {
	}

	boolean on = false;

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent m) {

	}

	@Override
	public void mouseMoved(MouseEvent m) {
		// TODO Auto-generated method stub
		// this method is triggered anytime the mouse moves on the screen

	}
}
