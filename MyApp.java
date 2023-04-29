import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class MyApp{
	
	public Color color;
	ArrayList<Circle> circles = new ArrayList<Circle>();
	private JTextArea displayCircles = new JTextArea("Count: 0");
	private JScrollPane scroll = new JScrollPane(displayCircles);

	public MyApp() {
		JFrame f = new JFrame("Assignment 1");
		f.setBounds(100, 100, 1000 , 500 );
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setLayout(null);
		
		final JInternalFrame internalFrame = new JInternalFrame("Ball Bounce");
		internalFrame.setResizable(true);
		internalFrame.getContentPane().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					System.out.printf("Left Clicked: x=%d y=%d\n", arg0.getX(), arg0.getY());
					Circle cir = new Circle(arg0.getX() - 10, arg0.getY() - 10);
					while(cir.speedX == 0 && cir.speedY == 0) {
						cir.speedX = Circle.randomSpeed();
						cir.speedY = Circle.randomSpeed();
					}
					cir.setBounds(cir.getX(), cir.getY(), cir.getDiameter(), cir.getDiameter());
					internalFrame.add(cir);
					circles.add(cir);
				}
				
				if(arg0.getButton() == MouseEvent.BUTTON2) {
					System.out.printf("Middle Clicked\n");
					for(Circle cir:circles){
						cir.setVisible(false);
						internalFrame.remove(cir);
					}
					circles.removeAll(circles);
				}

				if (arg0.getButton() == MouseEvent.BUTTON3) {
					System.out.printf("Right Clicked\n");
					if (circles.size() >= 1) {
						Circle firstCircle = circles.remove(0);
						firstCircle.setVisible(false);
						internalFrame.remove(firstCircle);
					}
				}

				updateDisplay();
			}
		});
		
		displayCircles.setEditable(false);
		
		String LeftClickText = "<html><span style=\"font-family:Monaco;font-size:12px;font-weight:bold;\">Left Click: </span> <span style=\"font-family:Monaco;font-size:12px;font-weight:normal;\">Add a ball</span></html>";
		String MiddleClickText = "<html><span style=\"font-family:Monaco;font-size:12px;font-weight:bold;\">Middle Click: </span> <span style=\"font-family:Monaco;font-size:12px;font-weight:normal;\">Remove all ball</span></html>";
		String RightClickText = "<html><span style=\"font-family:Monaco;font-size:12px;font-weight:bold;\">Right Click: </span> <span style=\"font-family:Monaco;font-size:12px;font-weight:normal;\">Remove first ball</span></html>";

		JLabel LeftClick = new JLabel(LeftClickText);
		JLabel MiddleClick = new JLabel(MiddleClickText);
		JLabel RightClick = new JLabel(RightClickText);
		
		f.add(LeftClick);
		LeftClick.setBounds(10, 10, 300, 25);
		f.add(MiddleClick);
		MiddleClick.setBounds(10, 30, 300, 25);
		f.add(RightClick);
		RightClick.setBounds(10, 50, 300, 25);
		
		f.add(scroll);
		scroll.setBounds(10, 80, 300, 300);

		internalFrame.setBounds(350, 10, 600, 400);
		f.add(internalFrame);
		internalFrame.setLayout(null);
		internalFrame.setVisible(true);

		f.setLayout(null);
		f.setVisible(true);
		
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dimension size = internalFrame.getContentPane().getSize();

				int w = size.width;
				int h = size.height;

				for (Circle cir : circles) {
					
					if (cir.getX() <= 0 || cir.getX() >= w - cir.getDiameter()) {
						if(cir.getX() >= w - cir.getDiameter())
							cir.setLocation(w - (cir.getDiameter()), cir.getY());
						else if (cir.getX() <= 0)
							cir.setLocation(0, cir.getY());
						cir.speedX = -cir.speedX;
						updateDisplay();
					}

					if (cir.getY() <= 0 || cir.getY() >= h - cir.getDiameter()) {
						if(cir.getY() >= h - cir.getDiameter()) 
							cir.setLocation(cir.getX(), h - (cir.getDiameter()));
						else if (cir.getY() <= 0) 
							cir.setLocation(cir.getX(), 0);
						cir.speedY = -cir.speedY;
						updateDisplay(); 
					}

					cir.setLocation((cir.getX() + cir.speedX), (cir.getY() + cir.speedY));
				}
			}
		};

		Timer displayTimer = new Timer(1, listener);
		displayTimer.start();
	}

	public static void main(String args[]) {
		new MyApp();
	}

	private void updateDisplay() {
		displayCircles.setText("Count: "+String.valueOf(circles.size())+"\n\n");
		for (int i = circles.size()-1; i >= 0; i--) {
			Circle cir = circles.get(i);
			String text = String.format(" %s\tspeed: %d %d \n", cir.getColor(), cir.speedX, cir.speedY);
			displayCircles.append(text);
		}
	}
}
