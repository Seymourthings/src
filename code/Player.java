package code;

import java.util.ArrayList;

/**
 * Player holds an ArrayList of Tiles and has functionality to assign a score
 * and name for each new Player object
 * 
 * A stub Player class to prevent compilation errors in the IGame interface.
 */
public class Player {
	private int _score;
	private String _name;
	private ArrayList<Tile> _rack;

	/**
	 * Creates a new player object assigning player a name, score, and list of
	 * Tiles
	 * 
	 * @param name
	 *            _name assigned to name in order to access variable globally in
	 *            Player class
	 */

	public Player(String name) {
		_name = name;
		_score = 0;
		_rack = new ArrayList<Tile>();

	}

	/**
	 * a method to allow a Player object to remove Tile from ArrayList.
	 * 
	 * @param t
	 *            the item to be removed
	 * @return t returns the removed object to be accessed by a separate method
	 */
	public Tile playTile(Tile t) {
		_rack.remove(t);
		return t;

	} //
		// Useful because it will return the removed tile and then be accessed
		// by the place method in Board class
		// place(p1.remvoeTile(t), 1 , 1) - example

	/**Sets a players score. To be called after a player has successfully created a new word.
	 * 
	 * @param x the new score of the player.
	 */
	public void setScore(int x) {
		_score = x;
	}

	/** A simple accessor method to retrieve a person's current score 
	 * 
	 * @return _score 
	 */
	public int getScore() {
		return _score;
	}

	/**Adds a tile to a Player's ArrayList
	 * 
	 * @param t the tile to  be added
	 * @return _rack.add(t) returns true if the tile has been added
	 */
	public boolean addTile(Tile t) {
		if(_rack.size()>12){
			return false;
		}
		return _rack.add(t);

	}

	/**An accessor method that returns a Player's ArrayList
	 * @return _rack returns Player's ArrayList of tiles
	 */
	public ArrayList<Tile> getRack() {
		return _rack;
	}
	/** An accessor method that returns a Player's name
	 * 
	 * @return _name the string that represent's a Player's name
	 */
	public String getName() {
		return _name;
	}

}
