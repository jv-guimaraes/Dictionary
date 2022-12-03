package JsonEquivalentJavaClass;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import Dictionary.Parser;

public class Root{
    public String word;
    public ArrayList<Meaning> meanings;
    
    @Override
    public String toString() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append(word + "\n");
    	for (Meaning m : meanings) {
    		buffer.append("  " + m.partOfSpeech + "\n");
    		for (Definition d : m.definitions) {
    			buffer.append("    - " + d.definition + "\n");
    		}
    	}
    	return buffer.toString();
    }
    
    public static void main(String[] args) throws Exception {
    	String myJsonString = Parser.readFile("entry.txt");
    	ObjectMapper om = new ObjectMapper();
    	Root root = om.readValue(myJsonString, Root.class);
    	System.out.println(root);
	}
}

