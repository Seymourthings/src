package code;

/**
 * A stub Tile class to prevent compilation errors in the IBoard interface.
 */
public class Tile {
	
	private char _letter;
	private char[] _vowels;
	
	/**
	 * Creates a tile and assigns the given character as the Tiles character 
	 * @param char
	 */
	
	public Tile(char c){
		_letter = c;
		_vowels = new char[]{'A', 'E', 'I', 'O', 'U'};
	}
	
	/**
	 * Checks whether the letter is a vowel
	 * @return boolean
	 */
	public boolean isVowel(){
		for(int i = 0; i < _vowels.length; i++){
			if(_letter == _vowels[i]){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Accessor method that returns the letter 
	 * @return char
	 */
	
	public char getChar(){
		return _letter;
	}
	
	/**
	 * Determines and assigns the score for each letter in the English Alphabets
	 * Returns the corresponding score
	 * @return int
	 */
	
	public int getScore(){
		int score = 0;
		if(isVowel() && (_letter != 'Y')){
			score = 1;
		}
		else if((isVowel()==false) && (_letter != 'Y')){
			score = 5;
		}
		else {
			score = 2;
		}		
		return score;
	}
	
	
}
