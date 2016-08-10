package co.gramlich.battleship.sprites;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FakeQueue<T> implements Iterable<T> {
	private static int LIMIT = 20;
	private List<T> queue;

	public FakeQueue(){
		queue = new LinkedList<T>();
	}
	
	public int size(){
		return queue.size();
	}
	
	public T get(int i){
		return queue.get(i);
	}
	
	public T add(T t){
		T doomed = null;
		if (queue.size() >= LIMIT) {
			doomed = queue.remove(0);
		}
		queue.add(t);
		return doomed;
	}
	
	public void clear(){
		queue.clear();
	}
	
	public boolean remove(T e) {
		return queue.remove(e);
	}
	
	public T peekLast() {
		if (queue.isEmpty()) {
			return null;
		} else {
			return queue.get(queue.size()-1);
		}
	}
	
	public List<T> peekLast2() {
		if (queue.size() < 2) {
			return null;
		} else {
			List<T> result = new ArrayList<T>();
			result.add(queue.get(queue.size()-1));
			result.add(queue.get(queue.size()-2));
			return result;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return queue.iterator();
	}
	
}
