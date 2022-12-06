package DataStructures;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class TableLP<V> {
	int capacity = 4;
	int keyCount = 0; // Numero de chaves
	int usedSlots = 0; // Número de slots ocupados (incluindo chaves deletadas)
	final double maxLoad = 0.6;
	TableEntry<V>[] slots = new TableEntry[capacity];
		
	public int hash(String str) {
		long hash = 5381;
		for (int i = 0; i < str.length(); i++) {
			hash = ((hash << 5) + hash) + str.charAt(i); // hash * 33 * char
		}
		return (int) Long.remainderUnsigned(hash, capacity);
	}
	
	public double loadFactor() {
		return (double) usedSlots / capacity;
	}
	
	public boolean hasKey(String key) {
		int index = hash(key);
		
		while (true) {
			if (slots[index] == null) {
				return false;
			}
			
			if (!slots[index].isDeleted() && slots[index].key.equals(key)) {
				return true;
			}
			
			index = (index + 1) % capacity;
		}
	}
	
	private void _insert(String key, V value) {
		int index = hash(key);
		while (true) {
			if (slots[index] == null) {
				slots[index] = new TableEntry<V>(key, value);
				keyCount++;
				usedSlots++;
				return;
			}
			
			if (slots[index].isDeleted()) {
				slots[index] = new TableEntry<V>(key, value);
				keyCount++;
				return;
			}
			
			index = (index + 1) % capacity;
		}
	}
	
	public void put(String key, V value) {
		if (hasKey(key)) {
			slots[hash(key)].value = value;
		} else {
			_insert(key, value);
		}
		
		if (loadFactor() >= maxLoad) resize(capacity * 2);
	}
	
	public void remove(String key) {
		int index = hash(key);
		
		while (true) {
			if (slots[index] == null) { return; }
			
			if (!slots[index].isDeleted() && slots[index].key.equals(key)) {
				slots[index].delete();
				keyCount--;
				return;
			}
			
			index = (index + 1) % capacity;
		}
	}
	
	public V get(String key) {
		int index = hash(key);
		
		while (true) {
			if (slots[index] == null) {
				return null;
			}
			
			if (!slots[index].isDeleted() && slots[index].key.equals(key)) {
				return slots[index].value;
			}
			
			index = (index + 1) % capacity;
		}
	}
	
	public void resize(int newCapacity) {
		capacity = newCapacity;
		keyCount = usedSlots = 0;
		var oldSlots = slots;
		slots = new TableEntry[capacity];
		
		for (TableEntry<V> entry : oldSlots) {
			if (entry != null && !entry.isDeleted()) {
				_insert(entry.key, entry.value);
			}
		}
	}
	
	public int size() {
		return keyCount;
	}
	
	public boolean isEmpty() {
		return keyCount == 0;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(slots) + " " + loadFactor();
	}
	
}
