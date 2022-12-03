package Dictionary;

import org.json.JSONArray;

public class DictEntry {
	private String word;
	private JSONArray roots;

	public DictEntry(String word, JSONArray roots) {
		this.roots = roots;
		this.word = word;
	}

	public String getWord() {
		return word;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(word + ": ");
		buffer.append(roots.getJSONObject(0).getJSONArray("meanings").getJSONObject(0)
				.getJSONArray("definitions").getJSONObject(0).getString("definition"));
		return buffer.toString();
	}
}
