import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameField extends JPanel implements ActionListener {

	private final int SIZE = 320;
	private final int DOT_SIZE = 16;
	private final int ALL_DOTS = 400;
	private Image dot;
	private Image apple;
	private int appleX;
	private int appleY;
	private int [] x = new int [ALL_DOTS];
	private int [] y = new int [ALL_DOTS];
	private int dots;
	private Timer timer;
	private boolean left = false;
	private boolean right = true;
	private boolean up = false;
	private boolean down = false;
	private boolean GameTry = true;
	
	
	public GameField() {
		setBackground(Color.white);
		LoadImages();
		InItGame();
		addKeyListener(new KeyListener());
		setFocusable(true);
	}
	
	public void InItGame() {
		dots = 3;
		for (int i = 0; i<dots; i++) {
			x[i] = 48 - i*DOT_SIZE;
			y[i] = 48;		
		}
		timer = new Timer(250,this);
		timer.start();
		createApple();
	}
	
	public void createApple() {
		appleX = new Random().nextInt(20)*DOT_SIZE;
		appleY = new Random().nextInt(20)*DOT_SIZE;
	}
	
	public void LoadImages() {
		ImageIcon iia = new ImageIcon("Apple.png");
		apple = iia.getImage();
		ImageIcon iid = new ImageIcon("Snake.png");
		dot = iid.getImage();
	}
	
	public void move() {
		for(int i = dots; i>0; i--)	{
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		if (left) {
			x[0]-=DOT_SIZE;
		}
		if (right) {
			x[0]+=DOT_SIZE;
		}
		if (up) {
			y[0]-=DOT_SIZE;
		}
		if (down) {
			y[0]+=DOT_SIZE;
		}
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if (GameTry) {
			g.drawImage(apple,appleX,appleY,this);
			for (int i=0; i<dots; i++) {
				g.drawImage(dot,x[i],y[i],this);
			}
		}else {
			String Over = "Game Over";
			g.setColor(Color.BLACK);
			g.drawString(Over, 125, SIZE/2);
		}
	}
	
	public void checkApple() {
		if(x[0]==appleX && y[0]==appleY) {
			dots++;
			createApple();
		}
	}
	
	public void checkCollisions() {
		for(int i = dots; i>0; i--) {
			if(i>4 && x[0]==x[i] && y[0]==y[i]) {
				GameTry = false;
			}
		}
		if (x[0]>SIZE || x[0]<0) {
			GameTry = false;
		}
		if (y[0]>SIZE || y[0]<0) {
			GameTry = false;
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(GameTry){
			checkApple();
			move();
			checkCollisions();
		}
		repaint();
	}
	class KeyListener extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyPressed(e);
			int key = e.getExtendedKeyCode();
			if (key == KeyEvent.VK_LEFT && !right) {
				left = true;
				up = false;
				down = false;
			}
			if (key == KeyEvent.VK_RIGHT && !left) {
				right = true;
				up = false;
				down = false;
			}
			if (key == KeyEvent.VK_UP && !down) {
				up = true;
				right = false;
				left = false;
			}
			if (key == KeyEvent.VK_DOWN && !up) {
				down = true;
				left = false;
				right = false;
			}
			if (key == KeyEvent.VK_A && !right) {
				left = true;
				up = false;
				down = false;
			}
			if (key == KeyEvent.VK_D && !left) {
				right = true;
				up = false;
				down = false;
			}
			if (key == KeyEvent.VK_W && !down) {
				up = true;
				right = false;
				left = false;
			}
			if (key == KeyEvent.VK_S && !up) {
				down = true;
				left = false;
				right = false;
			}			
		}	
	}
}
