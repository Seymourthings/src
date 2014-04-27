package code;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Driver {

	public static void main(String[] args) {
		final JFrame frame = new JFrame("Welcome!"
				+ "");
		JPanel panel = new JPanel();
		JButton button = new JButton("Enter # of Players");
		JLabel label = new JLabel(new ImageIcon("Description/Capture.png"));
		final JTextField text = new JTextField();
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Integer.parseInt(text.getText())>1 && Integer.parseInt(text.getText())<4){
					Game game = new Game();	
					game.start();
					
				}else{
					throw new NumberFormatException();
				}
				
			}
			
		});
		panel.setLayout(new BorderLayout());
		panel.add(label,"North");
		panel.add(text,"Center");
		panel.add(button,"West");
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		
	}
		}
		
	

