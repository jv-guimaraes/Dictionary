package Dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;

import DataStructures.Table;
import Util.Timer;

@SuppressWarnings("unused")
public class Parser {
	
	public static String readFile(String filename) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(filename));
			StringBuffer words = new StringBuffer();
			while(bf.ready()) {
				words.append(bf.readLine());
			}
			bf.close();
			return words.toString();	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static Table<JSONArray> parse(String filename) throws IOException {
		String dump = readFile(filename);
		JSONArray words = new JSONArray(dump);
		Table<JSONArray> table = new Table<JSONArray>();
		
		for (int i = 0; i < words.length(); i++) {
			var array = words.getJSONArray(i);
			String word = array.getJSONObject(0).getString("word");
			table.put(word, array);
		}
		
		return table;
	}
	
	public static void main(String[] args) throws JSONException, IOException {
		var t = new Timer();
		var table = parse("newDump.json");
		t.end();
		System.out.println("done!");
	}

}
