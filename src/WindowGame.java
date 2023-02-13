import javax.swing.*;

public class WindowGame extends JFrame {

	public WindowGame () {
		setTitle("Snake");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(320,345);
		setLocation(400,400);
		add(new GameField());
		setVisible(true);
	}
	
	public static void main(String[] args) {
		WindowGame wg = new WindowGame();
	}
}
