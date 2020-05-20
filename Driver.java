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
	
	boolean menu = true;
	
	
	Background b = new Background("background.PNG");
	Paddle p = new Paddle("paddle.PNG");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		b.paint(g);
		
		
		if(menu) {
			g.setColor(Color.GRAY);
			g.fillRect(660, 120, 580, 100);
			g.setColor(Color.BLUE);
			g.setFont(new Font("Impact", 0, 70));
			g.drawString("Hit 'Enter' to Start", 700,200);
		}else {
			p.paint(g);
		}

	}

	public void update() {

		p.move();
		
		if(p.getX() <= -25) {
			p.setX(-24);
		}
		if(p.getX() >= 1920-150+20) {
			p.setX(1920-151+20);
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

		f.add(this);
		t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		if(e.getKeyCode() == 10) {
			menu = false;
		}
		if(e.getKeyCode() == 39 && !menu) {
			p.setVx(17);
		}
		if(e.getKeyCode() == 37 && !menu) {
			p.setVx(-17);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 39) {
			p.setVx(0);
		}
		if(e.getKeyCode() == 37) {
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
