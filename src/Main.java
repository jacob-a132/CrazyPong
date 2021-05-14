import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	static int p = 720;
	static int screenSize = p*16/9;
	public Main(){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Loop());
		frame.setPreferredSize(new Dimension(screenSize, p));
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String args[]){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				@SuppressWarnings("unused")
				Main game = new Main();
			}
		});
	}
}
