import java.util.ArrayList;

public class ChainHashMap<K, V> extends AbstractHashMap<K, V>{
	/* the fixed capacity array of unsorted table map that serve as buckets*/
	
	public UnsortedTableMap<K , V>[] table; /* initialize within createTable*/
	
	public ChainHashMap(){super();} /* three constructors for the chain hash map*/
	public ChainHashMap(int cap){super(cap);} /* given the capacity*/
	public ChainHashMap(int cap, int p){super(cap, p);} /* given the capacity and the prime number*/
	
	protected void createTable() {
		table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity]; /* creates an empty table that has the length equal to the current capacity*/
		/* notice the structure here. The array serve as the bigger map with a fixed capacity and the element of the array is the unsorted map*/
	}
	
	protected V bucketGet(int h, K k){ /* returns value associated with key k in bucket with has value h, or else null*/
		 UnsortedTableMap<K, V> bucket = table[h]; /* get the bucket at the location h*/
		 
		 if(bucket == null) return null; /* return null if there is nothing in the given bucket*/
		 return bucket.get(k);
	}
	
	protected V bucketPut(int h, K k, V v){ /* associate key k with value v in bucket with hash value h, return the old value*/
		UnsortedTableMap<K, V> bucket = table[h];
		
		if(bucket == null)
			bucket = table[h] = new UnsortedTableMap<>();
		int oldSize = bucket.size();
		V answer = bucket.put(k, v);
		n += (bucket.size() - oldSize);
		return answer; 
	}
	
	protected V bucketRemove(int h, K k){ /* removes entry having key k from bucket with hash value h if any*/
		UnsortedTableMap<K, V> bucket = table[h];
		
		if(bucket == null) return null;
		int oldSize = bucket.size();
		V answer = bucket.remove(k);
		n -= (oldSize - bucket.size()); /* the size may decrease*/
		return answer;
	}
	
	public Iterable<Entry<K, V>> entrySet(){
		ArrayList<Entry<K, V>> buffer = new ArrayList<>();
		
		for(int h = 0; h < capacity; h++)
			if(table[h] != null)
				for(Entry<K, V> entry: table[h].entrySet())
					buffer.add(entry);
		return buffer;
	}
	
	public boolean isEmpty(){
		if(size() == 0)
			return true;
		return false;
	}
}
