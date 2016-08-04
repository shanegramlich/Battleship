package co.gramlich.battleship.sprites;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FakeQueue<T> implements Iterable<T> {
	private List<T> q;
	private static int LIMIT = 20;
	
	public FakeQueue() {
		q = new LinkedList<T>();
	}
	
	public int size() {
		return q.size();
	}
	
	public T get(int i) {
		return q.get(i);
	}
	
	public T add(T t) {
		T doomed = null;
		if (q.size() >= LIMIT) {
			doomed = q.remove(0);
		}
		q.add(t);
		return doomed;
	}
	
	public void clear() {
		q.clear();
	}
	
	public boolean remove(T e) {
		return q.remove(e);
	}
	
	public T peekLast() {
		if (q.isEmpty()) {
			return null;
		} else {
			return q.get(q.size()-1);
		}
	}
	
	public List<T> peekLast2() {
		if (q.size() < 2) {
			return null;
		} else {
			List<T> result = new ArrayList<T>();
			result.add(q.get(q.size()-1));
			result.add(q.get(q.size()-2));
			return result;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return q.iterator();
	}
	
}
