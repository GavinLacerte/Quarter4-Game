
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Column {

	private int length;
	private int num;
	private ArrayList<Brick> brickList;
	
	public Column(int n) {
		length = (int) (Math.random() * (8 - 2 + 1)) + (2);
		num = n;
		brickList = new ArrayList<Brick>();
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public ArrayList<Brick> getBrickList() {
		return brickList;
	}

	public void setBrickList(ArrayList<Brick> brickList) {
		this.brickList = brickList;
	}
	
	
}
	