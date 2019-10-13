import java.util.Comparator;

public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V>{
	
	protected static class PQEntry<K, V> implements Entry<K, V>{
		private K k; /* the key*/
		private V v; /* the value*/
		
		public PQEntry(K key, V value){ /* the constructor for the PQ entry class*/
			k = key;
			v = value;
		}
		
		/* methods of the entry interface*/
		public K getKey() {return k;}
		public V getValue() {return v;}
		/* methods to update the entry*/
		protected void setKey(K key){k = key;}
		protected void setValue(V value){v = value;}
	}
	
	
	private Comparator<K> comp; /* using the comparator to define the order of the entry*/
	
	protected AbstractPriorityQueue(Comparator<K> c){comp = c;} /* creates the priority queue using the comparator defined*/
	
	protected AbstractPriorityQueue() {this(new DefaultComparator<K>());} /* creates the priority queue using the default comparator*/
	
	protected int compare(Entry<K, V> a, Entry<K, V> b){/* compare the two given elements*/
		return comp.compare(a.getKey(), b.getKey());
	}
	
	public boolean checkKey(K key) throws IllegalArgumentException{
		try{
			return(comp.compare(key, key) == 0); /* compare the key to itself*/
		}catch(ClassCastException e){
			throw new IllegalArgumentException("Invalid key");
		}
	}
	
	public boolean isEmpty() {return size() == 0;} /* check to see if the priority queue is empty or not*/

}