package DataStructures;
import java.util.Arrays;

@SuppressWarnings("unchecked")
public class Table<V> {
	int capacity = 2048;
	int size = 0;
	final double maxLoad = 0.75;
	List<TableEntry<V>>[] slots = new List[capacity];
	
	public Table() {}
	
	public Table(int capacity) {
		this.capacity = capacity;
		this.slots = new List[capacity];
	}
	
	public int hash(String str) {
		long hash = 5381;
		for (int i = 0; i < str.length(); i++) {
			hash = ((hash << 5) + hash) + str.charAt(i); // hash * 33 * char
		}
		return (int) Long.remainderUnsigned(hash, capacity);
	}
	
	public void put(String key, V value) {
		int index = hash(key);
		if (slots[index] == null) slots[index] = new List<TableEntry<V>>();
		size += slots[index].insert(new TableEntry<V>(key, value));
		if (load() > maxLoad) {
			resize(capacity * 2);
		}
	}
	
	public V get(String key) {
		int index = hash(key);
		if (slots[index] == null) return null;
		var searchResult = slots[index].search(new TableEntry<V>(key, null));
		if (searchResult == null) return null;
		return searchResult.value;
	}
	
	public void remove(String key) {
		int index = hash(key);
		if (slots[index] != null) {
			size -= slots[index].remove(new TableEntry<V>(key, null));
		}
	}
	
	public boolean hasKey(String key) {
		int index = hash(key);
		if (slots[index] == null) return false;
		var searchResult = slots[index].search(new TableEntry<V>(key, null));
		if (searchResult == null) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(slots);
	}
	
	public String[] keys() {
		String[] keys = new String[size];
		int j = 0;
		for (int i = 0; i < slots.length; i++) {
			if (slots[i] != null) {
				var aux = slots[i];
				while(!aux.isEmpty()) {
					keys[j] = aux.data.key;
					j++;
					aux = aux.next;
				}
			}
		}
		return keys;
	}
	
	public TableEntry<V>[] pairs() {
		TableEntry<V>[] keys = new TableEntry[size];
		int j = 0;
		for (int i = 0; i < slots.length; i++) {
			if (slots[i] != null) {
				var aux = slots[i];
				while(!aux.isEmpty()) {
					keys[j] = aux.data;
					j++;
					aux = aux.next;
				}
			}
		}
		return keys;
	}
	
	public double load() {
		return (double) size / capacity;
	}
	
	public void resize(int newSize) {
		capacity = newSize;
		var newSlots = new List[capacity];
		var keys = pairs();
		for (var pair : keys) {
			int index = hash(pair.key);
			if (newSlots[index] == null) newSlots[index] = new List<TableEntry<V>>();
			newSlots[index].insert(pair);
		}
		slots = newSlots;
	}
	
	public int size() {
		return size;
	}

}
