
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.awt.Rectangle;

public class Brick {

	private int x, y;
	private boolean cracked;
	private boolean active;
	private int type;
	private int numHits;
	private Image img;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	public Brick(String fileName) {
		x = 0;
		y = 0;
		cracked = false;
		active = true;
		numHits = 0;

		img = getImage(fileName);
		updateBrick();
	}

	public Brick(String newFileName, int newX, int newY, int newType) {
		x = newX;
		y = newY;
		type = newType;
		numHits = 0;
		active = true;
		img = getImage(newFileName);
		updateBrick();
	}
	public void changeImage(String newFileName) {
		img = getImage(newFileName);
	}

	

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		
		updateBrick();
	}

	private void updateBrick() {
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
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public boolean isCracked() {
		return cracked;
	}

	public void setCracked(boolean cracked) {
		this.cracked = cracked;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNumHits() {
		return numHits;
	}

	public void setNumHits(int numHits) {
		this.numHits = numHits;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
}
