package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;
import code.Game;
import code.Player;

public class ButtonActionListener implements ActionListener {
	private Game _game;
	public ButtonActionListener(Game game) {
		_game = game;
	}
		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (_game.getTextField().getText() != "") {
				if(_game.getPlayers().size()<=4){
					_game.register(new Player(_game.getTextField().getText()));
					new Board();

				}
			}
		}

	}
	

