package Vue;

import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.EventQueue;
import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ConfigGame {

	private JFrame frame;
	private JPanel contentPane; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigGame window = new ConfigGame();
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
	public ConfigGame() {
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
			Image img = Toolkit.getDefaultToolkit().getImage(ConfigGame.class.getResource("/Game Pictures/bg2.jpg"));
            public void paintComponent(Graphics g) {
                 g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
             }  
        };
        contentPane.setBounds(0, 0, 1366, 746);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.frame.setContentPane(contentPane);
	}

}
