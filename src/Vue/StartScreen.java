package Vue;

import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.EventQueue;
import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class StartScreen {

	private JFrame frame;
	private JPanel contentPane;  

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreen window = new StartScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel() { 
			Image img = Toolkit.getDefaultToolkit().getImage(StartScreen.class.getResource("/Game Pictures/bg.jpg"));
            public void paintComponent(Graphics g) {
                 g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
             }  
        };
        contentPane.setBounds(0, 0, 1366, 746);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.frame.setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnNewGame = new JButton("New game");
        btnNewGame.setBounds(685, 538, 117, 29);
        contentPane.add(btnNewGame);
        
        JButton btnRules = new JButton("Rules");
        btnRules.setBounds(685, 579, 117, 29);
        contentPane.add(btnRules);
        
	}
}
