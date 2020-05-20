
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.awt.Rectangle;

public class Paddle {

	private int x, y;
	private int vx;
	private Image img;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	public Paddle(String fileName) {
		x = 885;
		y = 800;
		vx = 0;

		img = getImage(fileName);
		updateProjectile();
	}

	public Paddle(String newFileName, int newX, int newY) {
		x = newX;
		y = newY;
		updateProjectile();
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		
		updateProjectile();
	}

	private void updateProjectile() {
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
		updateProjectile();
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		updateProjectile();
	}

	public void setY(int y) {
		this.y = y;
		updateProjectile();
	}

	public int getY() {
		return y;
	}

}
