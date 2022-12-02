package DataStructures;
public class TableEntry<V> {
	String key;
	V value;
	
	public TableEntry(String key, V value) {
		this.key = key;
		this.value = value;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		return key.equals(((TableEntry<V>) o).key);
	}
	
	@Override
	public String toString() {
		return String.format("{%s:%s}", key, value);
	}
}
