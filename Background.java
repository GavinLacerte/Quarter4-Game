import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Background {
	private int bx, by;
	private Image back_img;
	private AffineTransform tx = AffineTransform.getTranslateInstance(bx, by);

	public Background(String backFileName) {
		bx = 0;
		by = 0;
		back_img = getImage(backFileName);

	}

	public Background(String backFileName, int newX) {
		this(backFileName);
		bx = newX;
		updateBackground();
	}

	private void updateBackground() {
		tx.setToTranslation(bx, by);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(back_img, tx, null);
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

	

	public int getBx() {
		return bx;
	}

	public void setBx(int bx) {
		this.bx = bx;
	}

	public int getBy() {
		return by;
	}

	public void setBy(int by) {
		this.by = by;
	}

	
	public Image getBack_img() {
		return back_img;
	}

	public void setBack_img(Image back_img) {
		this.back_img = back_img;
	}

	public AffineTransform getTx() {
		return tx;
	}

	public void setTx(AffineTransform tx) {
		this.tx = tx;
	}

}
