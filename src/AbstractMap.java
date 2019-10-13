import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V>{
	
	protected static class MapEntry<K, V> implements Entry<K, V>{
		private K k; /* the key*/
		private V v; /* the value*/
		
		public MapEntry(K key, V value){ /* the constructor for map entry, stores the key and the value*/
			k = key;
			v = value;
		}
		
		public K getKey(){return k;} /* return the key*/
		public V getValue(){return v;} /* return the value*/
		
		protected void setKey(K key){ k = key;} /* alter the key of the entry*/
		protected V setValue(V value){
			V old = v; /* keep track of the old value of the entry*/
			v = value; /* update the value of the entry*/
			return old; /* return the old value of the entry*/
		}
		
		/* the end of the nested entry class*/
	}
	
	/* the support for the key set method*/
	private class KeyIterator implements Iterator<K>{
		private Iterator<Entry<K, V>> entries = entrySet().iterator(); /* get the iterator of the entry set of the map*/
		
		public boolean hasNext(){return entries.hasNext();} /* true if there is a next*/
		
		public K next(){return entries.next().getKey();} /* return the key of the next entry*/
		
		public void remove(){throw new UnsupportedOperationException();}
	}
	
	private class KeyIterable implements Iterable<K>{
		public Iterator<K> iterator() { return new KeyIterator();}
	}
	
	public Iterable<K> keySet() {return new KeyIterable();}
	
	/* the support for public values method*/
	private class ValueIterator implements Iterator<V>{
		private Iterator<Entry<K, V>> entries = entrySet().iterator();
		
		public boolean hasNext(){ return entries.hasNext();} /* return true if there is a next*/
		
		public V next(){return entries.next().getValue();} /* get the value of the next entry*/
		
		public void remove(){throw new UnsupportedOperationException();}
	}
	
	private class ValueIterable implements Iterable<V>{
		public Iterator<V> iterator(){return new ValueIterator();}
	}
	
	public Iterable<V> values(){ return new ValueIterable();}

}
