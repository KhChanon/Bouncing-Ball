import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Circle extends JPanel {
	private Color color;
	private int diameter;
	public int speedX;
	public int speedY;

	public Circle() {
		this(0, 0, 10, randomColor(), randomSpeed(), randomSpeed());
	}

	public Circle(int x, int y, int d, Color c, int speedX, int speedY) {
		setLocation(x, y);
		this.diameter = d;
		this.color = c;
		this.speedX = speedX;
		this.speedY = speedY;
	}

	public Circle(int x, int y, int d, Color c) {
		this(x, y, d, c, randomSpeed(), randomSpeed());
	}

	public Circle(int x, int y, int d, int speedX, int speedY) {
		this(x, y, d, randomColor(), speedX, speedY);
	}

	public Circle(int x, int y, int d) {
		this(x, y, d, randomColor(), randomSpeed(), randomSpeed());
	}

	public Circle(int x, int y) {
		this(x, y, randomDiameter(), randomColor(), randomSpeed(), randomSpeed());
	}

	public void setColor(Color c) {
		color = c;
	}

	public String getColor() {
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();

		String color = String.format("RGB = %d : %d : %d",red,green,blue);
		return color;
	}

	public int getDiameter() {
		return diameter;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(this.color);
		g2d.fillOval(0, 0, diameter, diameter);
	}

	private static Color randomColor() {
		int R = (int) (Math.random() * 256);
		int G = (int) (Math.random() * 256);
		int B = (int) (Math.random() * 256);
		Color color = new Color(R, G, B);
		return color;
	}

	public static int randomSpeed() {
		int randomSpeed = (int) (Math.random() * 30);
		randomSpeed = randomSpeed - 15;
		return randomSpeed;
	}

	public static int randomDiameter() {
		int randomDiameter = (int) (Math.random() * 40);
		randomDiameter = randomDiameter +5;
		return randomDiameter;
	}

}