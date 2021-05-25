package estructuraTP;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class App extends JFrame {

	private JPanel contentPane;


	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setTitle("Chequeos");
		frame.setSize(920, 640);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Principal PanelInicio = new Principal(frame);
		frame.setContentPane(PanelInicio);
		frame.setVisible(true);
		
		
	}


}