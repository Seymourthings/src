package gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import code.Board;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI {

	public static void main(String[] args) {
		Board b = new Board();
		JPanel panel = new JPanel();
		for (int j = 0; j<20;j++){
					ImageIcon image = new ImageIcon("Description/TilePlace.png");
					JLabel jLabel = new JLabel(image);
					panel.add(jLabel);
			
			}
		
		JFrame _frame = new JFrame("Scrabble");
		JButton button = new JButton();
		panel.add(button);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBackground(Color.white);
		_frame.add(panel);
		_frame.setSize(1520, 800);
		_frame.setVisible(true);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}