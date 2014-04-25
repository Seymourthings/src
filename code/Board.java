package code;

import java.util.ArrayList;
import java.util.HashMap;

import code.interfaces.IBoard;

/** Represents the basis for which Tiles will be placed during game play
 *  @author Desean Abraham
 *  @version 1.2 April 2014
 */

public class Board implements IBoard {
private int _row;
private int _col;
private int _selectedRow;
private int _selectedCol;
private Tile[][] _board;
private ArrayList<Integer> _turnLocations;


/**
 * Constructor for the Board class. Creates a multi-dimensional array that represents
 * the board used during gameplay. If the index is not in the required scope, it sets 
 * it to null, otherwise, the tile is set to '_'
 * 
 */
	public Board(){
		_turnLocations = new ArrayList<Integer>();
		_board = new Tile[20][20];
		_row = 0;
		_col = 0;
		Tile t = new Tile('_');
		for (int i = 0; i < 20; i++){
			for (int j = 0; j < 20; j++){
				if ((i < 6 && j > 13) || (i > 13 && j < 6)){
					_board[i][j] = null;
				}
				else {
					_board[i][j] = t;
				}
			}
		}
	}
	

/** returns a random integer from index 0 to 19
 * @return _row represents the randomly chosen row between 1 to 20
 * @see code.interfaces.IBoard#homeSquareRow()
 */
	@Override
public int homeSquareRow() {
		double d = Math.random(); 
		return (int)(d);
}
/** returns a random integer from index 0 to 19
 * @return _col represents the randomly chosen column between 1 to 20
 * @see code.interfaces.IBoard#homeSquareCol()
 */
@Override
public int homeSquareCol() {
	double d = Math.random();
	return  (int) d;
}

/**
 * Chooses the home square row and column until it is a valid location
 * 
 */

public void setHomeSquare(){
	_row = homeSquareRow();
	_col = homeSquareCol();
	while(get(_row,_col)==null){
		_row = homeSquareRow();
		_col = homeSquareCol();
	}
}
public int getHomeRow(){
	return _row;
}
public int getHomeCol(){
	return _col;
}

/**
 * Returns a string representation of the row and column of the home square
 * @return String
 * 
 */
public String getHomeSquare(){
	String homeSquare = "";
	homeSquare = homeSquare+"Home Square Row: " + _row + '\n'+
			"Home Square Column: "+_col;
	return homeSquare;
}

/**
 * 
 * Returns the corresponding Tile at the given location
 * @param int, int
 * @return Tile
 */
@Override
public Tile get(int row, int col) {
	return _board[row][col];
}


/**
 * Place the Tile at the given location as long it is a valid move
 * according to gaming procedures. The first int parameter corresponds to desired row
 * while the second corresponds to the desired column. Returns true it is a valid move, false otherwise
 * @param Tile, int, int
 * @return boolean
 * 
 */
@Override
public boolean place(Tile tile, int row, int col) {
	if (_board[row][col] == null){return false;}
	if (_board[row][col].getChar() == '_'){
		int state = 0;
		if(get(_row,_col).getChar() == '_'){
			if(row==_row && col==_col){
				state = 3;
			}
			else {return false;}
		}
		switch(state){
		case 0: if (hasUp(row,col) || hasDown(row,col) || hasLeft(row,col) || hasRight(row,col)){
					if (_turnLocations.size() > 3){state = 2;}
					else {state = 1;}
				}
				else { return false; }
				break;
		case 1: if (!_turnLocations.isEmpty()){
					if (row==_turnLocations.get(0)){
						state = 3;
					}
					else if (col+1==col || col-1==col){
						state = 3;
					}
					else { return false; }
				}
				else { state = 3; }
				break;
		case 2: if(row==_turnLocations.get(0)){
					if ((col == _turnLocations.get(1)+1)||(col == _turnLocations.get(1)+1)){
						state = 3;
					}
				}
				else if(col==_turnLocations.get(1)){
					if ((row == _turnLocations.get(0)+1)||(row == _turnLocations.get(0)+1)){
						state = 3;
					}
				}
				else { return false; }
		case 3:{
			_board[row][col] = tile;
			_turnLocations.add(row);
			_turnLocations.add(col);
			return true;
			}
		}
	}
	return false;
}

/**
 * Removes the desired Tile from the board as long as it was played during the current player's turn.
 * Returns true if executed successfully, false otherwise.
 * 
 * @param Tile
 * @return boolean
 */

@Override
public boolean retract(Tile tile) {
	for (int i = 0; i < _turnLocations.size(); i = i + 2){
		if (_turnLocations.get(i)==_selectedRow && 
			_turnLocations.get(i+1) == _selectedCol){
			System.out.println(_turnLocations.get(i));
			System.out.println(_turnLocations.get(i+1));
			_board[_selectedRow][_selectedCol] = new Tile('_');
			
			_turnLocations.remove(i);
			_turnLocations.remove(i+1);
			return true;
		}
	}
	return false;
}

/**
 * Checks if the location corresponding to the given coordinates is empty.
 * If so, returns true, otherwise, returns false.
 *  
 *  @param int, int
 *  @return boolean
 * 
 */
@Override
public boolean isEmpty(int row, int col) {
	if(get(row,col)!=null){
	if (get(row,col).getChar() == '_'){
		return true;
	}
	}
	return false;
}

/**
 * Gives a string representation of the current state of the board.
 * '_' represents an empty square, ' ' represents a null and character otherwise.
 *@return String.  
 * 
 */
@Override
public String boardConfiguration() {
	String boardConfig = "";
	for (int i = 0; i < 20; i++){
		for (int j = 0; j < 20; j++){
			if (_board[i][j] != null){
				boardConfig += _board[i][j].getChar();
			}
			else {
				boardConfig += ' ';
			}
		}
		boardConfig += '\n';
	}
	return boardConfig;
}

/**
 * Mutator method to set row to the selected row
 * @param int
 * 
 */
public void setRow(int x){
	_selectedRow = x;
}

/**
 * Mutator method to set column to the selected column
 * @param int
 * 
 */

public void setCol(int x){
	_selectedCol = x;
}

/**
 * Returns the number of columns on the board.
 * @return int
 * 
 */
public int getNumberofColumns() {
	return 20;
}

/**
 * Returns the number of rows on the board
 * @return int
 * 
 */
public int getNumberofRows() {
	return 20;
}

/**
 * Ends the turn of the current player and resets the record of moves they made.
 */
public void endTurn(){
	_turnLocations = new ArrayList<Integer>();
}

/**
 * Accessor method to obtain the list of moves made by the current player
 * @return ArrayList<Integer>
 */
public ArrayList<Integer> getTurnLocations(){
	return _turnLocations;
}

/**
 * Determines whether the word player is horizontally or vertically oriented
 * @return int
 */
public int wordOrientation(){
	int orientation = 0;
	if(_turnLocations.indexOf(0) == _turnLocations.indexOf(2)){
		orientation = 1;
	}
	if(_turnLocations.indexOf(1) == _turnLocations.indexOf(3)){
		orientation = 2;
	}
	return orientation;
}

/**
 * Returns true if the given location has a Tile above it. False otherwise.
 * @return boolean
 */
public boolean hasUp(int row, int col){
	if (!(row == 0)){
		if (get(row-1,col) != null){
			if (get(row-1,col).getChar() != '_'){
				return true;
			}
		}
	}
	return false;
  }

/**
 * Returns true if the given location has a Tile below it. False otherwise.
 * @return boolean
 */
 
public boolean hasDown(int row, int col){
	if (!(row == 19)){
		if (get(row+1,col) != null){
			if (get(row+1,col).getChar() != '_'){
				return true;
			}
		}
	}
	return false;
  }

/**
 * Returns true if the given location has a Tile to the left of it. False otherwise.
 * @return boolean
 */
 
public boolean hasLeft(int row, int col){
	if (!(col == 0)){
		if (get(row,col-1) != null){
			if (get(row,col-1).getChar() != '_'){
				return true;
			}
		}
	}
	return false;
  }

/**
 * Returns true if the given location has a Tile to the right of it. False otherwise.
 * @return boolean
 */

public boolean hasRight(int row, int col){
	if (!(col == 19)){
		if (get(row,col+1) != null){
			if (get(row,col+1).getChar() != '_'){
				return true;
			}
		}
	}
	return false;
  }
public Tile[][] getBoard(){
	return _board;
}
}
