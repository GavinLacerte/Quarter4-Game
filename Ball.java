
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.awt.Rectangle;

public class Ball {

	private int x, y;
	private int vx, vy;
	private Image img;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	public Ball(String fileName) {
		x = 885;
		y = 750;
		vx = -5;
		vy = -10;

		img = getImage(fileName);
		updateBall();
	}

	public Ball(String newFileName, int newX, int newY) {
		x = newX;
		y = newY;
		updateBall();
	}


	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		
		updateBall();
	}

	private void updateBall() {
		tx.setToTranslation(x, y);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Driver.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	public void move() {
		x+=vx;
		y+=vy;
		updateBall();
	}
	
	public void collide(Paddle p) {
		if(x < 0) {
			x=1;
			vx = vx*-1;
		}
		if(x > 1920-38) {
			x = 1920-39;
			vx = vx*-1;
		}
		if(y < 0) {
			y = 1;
			vy = vy*-1;
		}
		if(y+38 > p.getY() + 70 && x+19 >= p.getX() + 12 && x+19 <= p.getX() + 150 - 12 && y+38 < p.getY() + 100 ) {
			y = p.getY() - 1;
			vy = vy * -1;
		}
		updateBall();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}
	
	
}
