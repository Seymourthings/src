package code;

import java.io.IOException;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import code.interfaces.IGame;

public class Game implements IGame {
	/**
	 * Handles set up for the game and includes rules during gameplay
	 * 
	 * @author Desean Abraham, Haider Atta Tung. Chris Nohilly
	 * @version 1.2 April 2014
	 */
	private List<SimpleImmutableEntry<String, Integer>> _simpleList;
	private Dictionary _dictionary;
	private int _currentTurn;
	private int _turnsPassed;
	private char[] _vowels;
	private ArrayList<Player> _list;
	private ArrayList<Tile> _bag;
	private String _wordFormed;
	private BagOfTiles _bg;
	private Board _b;
	private boolean _started;
	private int _turn;

	/**
	 * Constructor for the game class creates Arrays, ArrayLists, an Iterator,
	 * Integer variables shuffles the bag of tiles
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 */

	public Game() {
		_dictionary = new Dictionary();
		_vowels = new char[] { 'A', 'E', 'I', 'O', 'U', 'Y' };
		_bg = new BagOfTiles();
		_b = new Board();
		Collections.shuffle(_bg._tileBag);
		_currentTurn = 0;
		_turnsPassed = 0;
		_wordFormed = "";
		_list = new ArrayList<Player>();
		_started = false;
		_simpleList = new LinkedList<SimpleImmutableEntry<String, Integer>>();
	}
	public void tileMultiplier(){
	
	}

	/**
	 * should return true or false depending on whether or not a person has
	 * passed their turn could have an integer represent a turn
	 * 
	 * @return
	 */
	/**
	 * Determines the validity of a word
	 * 
	 * @param s
	 *            the string to be passed in, with it's validity being tested
	 * @return true returns true if the word has more than one character and at
	 *         least one vowel and one consonant
	 */

	public boolean isWord(String s) {
		for (int i = 0; i < _dictionary.getDictionary().size(); i++) {
			if (s.equals(_dictionary.getDictionary().get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Compares each character in a string to each character in the array of
	 * vowels
	 * 
	 * @param s
	 *            string passed in which is being compared
	 * @return true if string has at least one vowel as compared to the array of
	 *         vowels
	 */
	public boolean hasVowel(String s) {
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < _vowels.length; j++) {
				if (s.charAt(i) == _vowels[j]) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Compares each character in a string to each character in the array of
	 * vowels and counts the number of vowels that have appeared in the string.
	 * If the count is not equal to the length of the string then there is at
	 * least one character that is not a vowel in the string.
	 * 
	 * @param s
	 *            the string to be compared
	 * @return true if count is less than the string's length.
	 */
	public boolean hasNotVowel(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < _vowels.length; j++) {
				if (s.charAt(i) == _vowels[j]) {
					count++;

					if (count < s.length()) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Iterates over string and sums the point values associated with each
	 * character defined
	 * 
	 * @param s
	 *            the string iterated throughout to sum and return the scores of
	 *            each character
	 * @return
	 * @see getScore()
	 */
	public int wordScore(String s) {
		Tile tempTile;
		int score = 0;
		for (int i = 0; i < s.length(); i++) {
			tempTile = new Tile(s.charAt(i));
			score = score + tempTile.getScore();
		}
		return score;
	}

	/**
	 * Populates the player's rack with Tiles until the inventory of tiles is
	 * empty or player's rack is full.
	 * 
	 * @param Player
	 */

	public void giveTiles(Player player) {
		while (!_bg.getTileBag().isEmpty()) {
			while (player.getRack().size() < 12) {
				Tile t = _bg.getTileBag().get(0);
				player.getRack().add(t);
				_bg.getTileBag().remove(0);
				_bg.getTileBag().remove(t);
			}
			break;
		}
	}

	public int turnsPassedCount() {
		return _turnsPassed;
	}

	/**
	 * Accessor method that returns the current Player
	 * 
	 * @return Player
	 */
	public Board getBoard() {
		return _b;
	}

	public Player getPlayer() {
		return _list.get(_currentTurn);
	}

	public ArrayList<Tile> getTileBag() {
		return _bg.getTileBag();
	}

	/**
	 * Accessor method that returns the order of Players registered to the game.
	 * 
	 * @return List<Player>
	 */
	@Override
	public List<Player> orderOfPlay() {
		return _list;
	}

	/**
	 * Accessor method that returns the score of the given player
	 * 
	 * @param Player
	 * @return int
	 */

	@Override
	public int score(Player player) {
		return player.getScore();
	}

	/**
	 * Attempts to register the given Player to the game. Returns true if the
	 * player is successfully registered, false otherwise.
	 * 
	 * @param Player
	 * @return boolean
	 */
	@Override
	public boolean register(Player player) {
		if (_started == false && player.getName() != "") {
			_list.add(player);
			return true;
		}
		if (_started == true || player.getName() == "") {
			return false;
		}
		return false;

	}

	/**
	 * Starts the game if more than 1 Players are registered and randomizes
	 * their order. Returns true if the game successfully starts, false
	 * otherwise.
	 * 
	 * @return boolean
	 * 
	 * 
	 */
	@Override
	public boolean start() {
		if (_list.size() > 1) {
			Collections.shuffle(_list);
			for (int i = 0; i < _list.size(); i++) {
				giveTiles(_list.get(i));
			}
			_b.setHomeSquare();
			_turn = 1;
			_started = true;
		}
		return _started;
	}

	/**
	 * Will only end a players turn if they have played a word or passed their
	 * turn
	 * 
	 * _currentTurn delegates a player's turn. Resets when this integer is
	 * larger than the amount of people in the ArrayList.
	 * 
	 * @return false if string played does not qualify as a word
	 */
	/**
	 * 
	 * @return false returns if player has played a valid word then goes to next
	 *         turn
	 */
	@Override
	public boolean endTurn() {
		if (_wordFormed == "") {
			_turnsPassed++;
			endGame();
			return resetTurn();
		}

		else if (isWord(_wordFormed) && getPlayer().getRack().size() < 11) {
			if (wordPlaced()) {
				_turnsPassed = 0;
				endGame();
				_wordFormed = "";
				giveTiles(getPlayer());
				return resetTurn();
			}
		}
		return false;
	}

	/**
	 * If the last Player has finished their turn, it loops to the first one.
	 * 
	 * @return boolean
	 */

	public boolean resetTurn() {
		_currentTurn++;
		if (_currentTurn >= _list.size()) {
			_currentTurn = 0;
		}
		return true;
	}

	/**
	 * Attempts to play the Tile selected by current Player on to the board.
	 * Adds character of successfully played characters to a wordFormed String
	 * 
	 * @param Tile
	 *            , int, int
	 * @return boolean
	 */

	public boolean playTile(Tile t, int row, int col) {
		System.out.println(getPlayer().getRack().remove(t));
		if (_b.place(t, row, col)) {
			_wordFormed += t.getChar();
			System.out.println(getPlayer().getRack().remove(t));
			return getPlayer().getRack().remove(t);
		}
		return false;
	}

	/**
	 * Determines whether a word is vertically or horizontally oriented and
	 * calls the appropiate methods accordingly.
	 * 
	 * @return boolean
	 * 
	 */

	public boolean wordPlaced() {
		int orientation = _b.wordOrientation();
		int row = _b.getTurnLocations().indexOf(0);
		int col = _b.getTurnLocations().indexOf(1);
		if (orientation == 1) {
			return verticleWord(row, col);
		}
		if (orientation == 2) {
			return horizontalWord(row, col);
		}
		return false;
	}

	/**
	 * Returns a list of past words played with their respective scores
	 * 
	 * @see code.interfaces.IGame#lastTurnWordScores()
	 */

	@Override
	public List<SimpleImmutableEntry<String, Integer>> lastTurnWordScores() {
		return _simpleList;
	}

	public ArrayList<Player> getPlayers() {
		return _list;
	}

	/**
	 * Determines whether the conditions for ending the game have been met.
	 * Returns true if so, false otherwise.
	 * 
	 * @return boolean
	 */

	public boolean endGame() {
		if (getPlayer().getRack().size() == 0 && _bg.getTileBag().size() == 0) {

			return true;

		} else if (_turnsPassed == _list.size()) {
			return true;
		}
		return false;
	}

	/**
	 * Displays in the format of a String, the winning Player and their score. @
	 * return String
	 */
	public String winner() {
		int highest = _list.get(0).getScore();
		int winner = 0;
		for (int i = 1; i < _list.size(); i++) {
			if (_list.get(i).getScore() > highest) {
				highest = _list.get(i).getScore();
				winner = i;
			}
		}
		String win = "Winner: " + _list.get(winner).getName() + "  Score: "
				+ highest;
		return win;
	}

	/**
	 * Adds the Tile retracted from the board back to the Player's rack
	 */

	public void retractedTile(Tile t) {
		getPlayer().getRack().add(t);
	}

	/**
	 * Checks the vertically oriented word whether it is legally played,
	 * calculates scores and adds it to current PLayer's current score. Returns
	 * true if successfully executed, false if the word played is not a legal
	 * move.
	 * 
	 * @param int, int
	 * @return boolean
	 */

	public boolean verticleWord(int row, int col) {
		int state = 1;
		String word = "";
		int score = 0;
		int highestRow = 0;
		int lowestRow = 0;
		int baseCol = col;
		switch (state) {
		case 1:
			if (_b.hasUp(row, col)) {
				row = row - 1;
			} else {
				highestRow = row;
				state = 2;
			}
			break;
		case 2:
			if (_b.hasDown(row, col)) {
				word = word + _b.get(row, col).getChar();
				row = row + 1;
			} else {
				word = word + _b.get(row, col).getChar();
				lowestRow = row;
				state = 3;
			}
			break;
		case 3:
			if (isWord(word)) {
				int temp = wordScore(word);
				score = score + temp;
				SimpleImmutableEntry<String, Integer> entry = new SimpleImmutableEntry<String, Integer>(
						word, temp);
				_simpleList.add(entry);
				row = highestRow;
				word = "";
				state = 4;
			} else {
				state = 9;
			}
			break;
		case 4:
			for (int i = 0; i < _b.getTurnLocations().size(); i = i + 2) {
				if (_b.getTurnLocations().indexOf(i) == row
						&& _b.getTurnLocations().indexOf(i + 1) == col) {
					state = 5;
				} else if (lowestRow > row) {
					row = row + 1;
				} else {
					state = 8;
				}
			}
			break;
		case 5:
			if (_b.hasLeft(row, col)) {
				col = col - 1;
			} else {
				state = 6;
			}
			break;
		case 6:
			if (_b.hasRight(row, col)) {
				word = word + _b.get(row, col).getChar();
				col = col + 1;
			} else {
				state = 7;
			}
			break;
		case 7:
			if (isWord(word)) {
				int temp = wordScore(word);
				score = score + temp;
				SimpleImmutableEntry<String, Integer> entry = new SimpleImmutableEntry<String, Integer>(
						word, temp);
				_simpleList.add(entry);
				word = "";
				col = baseCol;
				row = row + 1;
				state = 4;
			} else if ((lowestRow == row) && (word.equals(""))) {
				state = 8;
			} else if (word.equals("")) {
				col = baseCol;
				row = row + 1;
				state = 4;
			} else {
				state = 9;
			}
			break;
		case 8: {
			Player p = getPlayer();
			p.setScore(p.getScore() + score);
			return true;
		}
		case 9: {
			return false;
		}
		}
		return false;
	}

	/**
	 * Checks the horizontally oriented word whether it is legally played,
	 * calculates scores and adds it to current PLayer's current score. Returns
	 * true if successfully executed, false if the word played is not a legal
	 * move.
	 * 
	 * @param int, int
	 * @return boolean
	 */

	public boolean horizontalWord(int row, int col) {
		int state = 1;
		String word = "";
		int score = 0;
		int highestCol = 0;
		int lowestCol = 0;
		int baseRow = row;
		switch (state) {
		case 1:
			if (_b.hasLeft(row, col)) {
				col = col - 1;
			} else {
				highestCol = col;
				state = 2;
			}
			break;
		case 2:
			if (_b.hasRight(row, col)) {
				word = word + _b.get(row, col).getChar();
				col = col + 1;
			} else {
				word = word + _b.get(row, col).getChar();
				lowestCol = col;
				state = 3;
			}
			break;
		case 3:
			if (isWord(word)) {
				int temp = wordScore(word);
				score = score + temp;
				SimpleImmutableEntry<String, Integer> entry = new SimpleImmutableEntry<String, Integer>(
						word, temp);
				_simpleList.add(entry);
				col = highestCol;
				word = "";
				state = 4;
			} else {
				state = 9;
			}
			break;
		case 4:
			for (int i = 0; i < _b.getTurnLocations().size(); i = i + 2) {
				if (_b.getTurnLocations().indexOf(i) == row
						&& _b.getTurnLocations().indexOf(i + 1) == col) {
					state = 5;
				} else if (lowestCol > col) {
					col = col + 1;
				} else {
					state = 8;
				}
			}
			break;
		case 5:
			if (_b.hasUp(row, col)) {
				row = row - 1;
			} else {
				state = 6;
			}
			break;
		case 6:
			if (_b.hasDown(row, col)) {
				word = word + _b.get(row, col).getChar();
				row = row + 1;
			} else {
				state = 7;
			}
			break;
		case 7:
			if (isWord(word)) {
				int temp = wordScore(word);
				score = score + temp;
				SimpleImmutableEntry<String, Integer> entry = new SimpleImmutableEntry<String, Integer>(
						word, temp);
				_simpleList.add(entry);
				word = "";
				row = baseRow;
				col = col + 1;
				state = 4;
			} else if ((lowestCol == col) && (word.equals(""))) {
				state = 8;
			} else if (word.equals("")) {
				row = baseRow;
				col = col + 1;
				state = 4;
			} else {
				state = 9;
			}
			break;
		case 8: {
			Player p = getPlayer();
			p.setScore(p.getScore() + score);
			return true;
		}
		case 9: {
			return false;
		}
		}
		return false;
	}

	/**
	 * Returns as a String the list of Players and their corresponding scores.
	 * 
	 * @return String
	 */
	public String getScoreBoard() {
		String scoreBoard = "";
		for (Player player : _list) {
			scoreBoard = scoreBoard + player.getName() + ": "
					+ player.getScore() + '\n';
		}
		return scoreBoard;
	}
}