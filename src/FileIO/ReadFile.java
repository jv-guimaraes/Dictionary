package FileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
	public static ArrayList<String> loadFile(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner myReader = new Scanner(file);
		ArrayList<String> words = new ArrayList<String>();
		while (myReader.hasNextLine()) {
			words.add(myReader.nextLine());
		}
		myReader.close();
		return words;
	}
	
	/*public static void main(String[] args) throws FileNotFoundException {
		var words = loadFile("corncob_lowercase.txt");
		for (String word : words) {
			System.out.println(word);
		}
	}*/
	
}
