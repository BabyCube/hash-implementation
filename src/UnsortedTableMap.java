import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnsortedTableMap<K, V> extends AbstractMap<K, V>{
	
	private ArrayList<MapEntry<K, V>> table = new ArrayList<>(); /* create the array list to store the entries*/
	
	public UnsortedTableMap () {}; /* the constructor for the class, create an empty map*/
	
	/*  the private utility*/
	private int findIndex(K key){
		int n = table.size(); 
		for(int j = 0; j < n; j++)
			if(table.get(j).getKey().equals(key))
				return j; /* iterates through the map to compare the value, return the index if the matching key is founds*/
		return -1; /* the special value that indicates the value which is looking for is not found*/
	}
	
	public int size(){return table.size();} /* the size of the map is the number of entries stored in the array list*/
	
	public V get(K key){ /* returns the value associated with the specified key, or else null*/
		
		int j = findIndex(key);
		if(j == -1) return null; /* recall that -1 is the special value indicating that the key is not found in the map*/
		
		return table.get(j).getValue(); /* return the value if it is in the map*/
		
	}
	
	public V put(K key, V value){ /* associate the given value with a given key, replacing the previous value if there is one */
		int j = findIndex(key);
		
		if(j == -1){
			table.add(new MapEntry<>(key, value)); /* there is no entries that has the same key, create the entry and then add to the table*/
			return null;
		}else
			return table.get(j).setValue(value); /* there is already an entry with the key provided, update the value of that entry*/
	}
	
	public V remove(K key){
		int j = findIndex(key);
		int n = size();
		
		if(j == -1) return null; /* the given key is not found in the map*/
		
		V answer = table.get(j).getValue(); /* get the value of the entry with the given key found in the map*/
		
		if(j != n -1)
			table.set(j, table.get(n - 1)); /* replace the j element in the array list with the last element*/
		table.remove(n - 1);
		return answer; /* return the value of the element that is removed*/
	}
	
	private class EntryIterator implements Iterator<Entry<K , V>>{ /* the support for entry set method*/
		
		private int j = 0;
		
		public boolean hasNext(){
			return j < table.size();
		}
		
		public Entry<K, V> next(){
			if(j ==  table.size()) throw new NoSuchElementException(); /* already reached the end of the map*/
			return table.get(j++);
		}
		
		public void remove(){ throw new UnsupportedOperationException();} /* some of the iterator does not support removing elements*/
		
	}
	
	private class EntryIterable implements Iterable<Entry<K, V>>{
		public Iterator<Entry<K, V>> iterator(){ return new EntryIterator();}	
	}
	
	public Iterable<Entry<K, V>> entrySet() {return new EntryIterable();}
	
	public boolean isEmpty(){
		if(size() == 0)
			return true;
		return false;
	}

}
