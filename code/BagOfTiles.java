package code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Creates an an ArrayList<Tile> and populates it. These are all the pieces that
 * can be possibly played in the game.
 * 
 * @author Haider Atta Tung, Desean A. Abraham, Christopher Nohilly
 * 
 */

public class BagOfTiles {

	public ArrayList<Tile> _tileBag;
	private HashMap<Tile, Integer> _pointValueMap;

	/**
	 * This is a constructor for the BagOfTiles class. It creates an
	 * ArrayList<Tile> called _tilebag and populates it with characters ranging
	 * from 'A' to 'Z'. It adds each character into the array list as many times as specified in the 
	 * file that is read. Each character is then mapped to a specific point value (speciied in the file as well)   
	 * @throws IOException 
	 * @throws NumberFormatException 
	 * 
	 */
	public BagOfTiles() {
		_tileBag = new ArrayList<Tile>();
		_pointValueMap = new HashMap<Tile, Integer>();
		String[] s = null;
		@SuppressWarnings("resource")
		BufferedReader br = null;
	
		try {
			br = new BufferedReader(new FileReader("Txt/Characters"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			String currentLine;
				try {
					while ((currentLine = br.readLine()) != null) {
					s = currentLine.split(" ");
					for (int i = 0; i < Integer.parseInt(s[1]); i++) {
						for (int j = 0; j < s[0].length(); j++) {
							_tileBag.add(new Tile(s[0].charAt(j)));
						}
					}_pointValueMap.put(new Tile(s[0].charAt(0)), Integer.parseInt(s[2]));
						}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	/**
	 * 
	 * Returns the _tileBag variable that contains all the tiles playable within
	 * the game.
	 * 
	 * @return ArrayList<Tile>
	 */

	public ArrayList<Tile> getTileBag() {
		return _tileBag;
	}
}
