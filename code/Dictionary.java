package code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary {
	private ArrayList<String> _list;
	public Dictionary() {
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader("Txt/Dictionary"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_list = new ArrayList<String>();
		String[] s = null;
		String currentLine;
		try {
			while((currentLine=bf.readLine())!=null){
				s = currentLine.split(" ");
				_list.add(s[0].toUpperCase());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<String> getDictionary(){
		return _list;
	}

}
