package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import code.Board;

public class GUI {

	public GUI(){
		JPanel panel = new JPanel();
		JFrame _frame = new JFrame();
		Board _board = new Board();
		 JTextArea _box = new JTextArea("Player 1 :"+"   Score: "+"              Player 2: "+"         Score: ");
		JPanel board = new JPanel();
		board.setLayout(new GridLayout(20,20));
		panel.setLayout(new BorderLayout());
		_frame.getContentPane().setLayout(new BorderLayout());
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (! ((i < _board.getNullColms() && j > (19 - _board.getNullRows())) || (i > (19 - _board.getNullColms()) && j < _board.getNullRows()))) {
					ImageIcon image = new ImageIcon("Description/Tile.png");
					JLabel jLabel = new JLabel(image);
					board.add(jLabel);
				} else {
					
					board.add(new JLabel(" _"));
				}
			}
		}
		panel.add(_box,"Center");
		_frame.add(panel,"North");
		_frame.add(board,"Center");
		_frame.setVisible(true);
		_frame.setTitle("Scrabble");
		_frame.pack();
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setLocation(_board.randomLocations(),_board.randomLocations());
		_frame.setResizable(false);
		

	
	}
}