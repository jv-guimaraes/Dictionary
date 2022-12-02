package DataStructures;
import java.util.ArrayList;
import java.util.Arrays;

public class List<T> {
	T data;
	List<T> next;
		
	public boolean isEmpty() {
		return data == null;
	}
	
	/* Se o item já estiver na lista, insere o novo no lugar do velho
	 * Retorna 1 se o tamanho da lista aumentou e 0 se não aumentou */
	public int insert(T item) {
		if (isEmpty()) {
			data = item;
			next = new List<T>();
			return 1;
		}
		else if (data.equals(item)) {
			data = item;
			return 0;
		} else {
			return next.insert(item);
		}
	}
	
	/* Retorna 1 se o tamanho da lista diminuiu e 0 se não diminuiu */
	public int remove(T item) {
		if (isEmpty()) return 0;
		if (data.equals(item)) {
			data = next.data;
			next = next.next;
			return 1;
		} else {
			return next.remove(item);
		}
	}
	
	public T search(T item) {
		if (isEmpty()) return null;
		if (data.equals(item)) {
			return data;
		} else {
			return next.search(item);
		}
	}
	
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		ArrayList<T> result = new ArrayList<T>();
		_toArray(result, this);
		return (T[]) result.toArray();
	}
	
	private void _toArray(ArrayList<T> list, List<T> node) {
		if (!node.isEmpty()) {
			list.add(node.data);
			_toArray(list, node.next);
		}
	}
	
	@Override
	public String toString() {
		return Arrays.toString(this.toArray());
	}
	
	public int size() {
		if (isEmpty()) {
			return 0;
		}
		return 1 + next.size();
	}
	
}
