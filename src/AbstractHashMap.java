import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V>{

	protected int n = 0; /* keep track of the numbers of entries*/
	
	protected int capacity; /* the length of the table*/
	
	private int prime; /* the prime factor*/
	
	private long scale, shift; /* the shifting and scaling factors*/
	
	public AbstractHashMap(int cap, int p){
		prime = p;
		capacity = cap;
		
		Random rand = new Random();
		scale = rand.nextInt(prime - 1) + 1;
		shift = rand.nextInt(prime);
		
		createTable();
	}
	
	public AbstractHashMap(int cap){ this(cap, 109345121);} /* call the constructor with the default prime number but given capacity*/
	
	public AbstractHashMap(){ this(17);} /* call the constructor with the default prime and capacity*/
	
	/* the public methods*/
	public int size(){ return n;} /* get the size of the entries in the map*/
	
	public V get(K key){ return bucketGet(hashValue(key), key); } /* get the entries from the given key in the located bucket*/
	
	public V remove(K key){ return bucketRemove(hashValue(key), key);} /* remove the entries from the given key in the located bucket*/
	
	public V put(K key, V value){
		V answer = bucketPut(hashValue(key), key, value);
		
		if(n > capacity/2) /* keep the load factor smaller than 0.5 to keep the hash table efficient*/
			resize(2*capacity - 1);
		return answer;
	}
	
	/* the private utilities, generating the hash code, resizing the hash table*/
	private int hashValue(K key){
		return (int)((Math.abs(key.hashCode()*scale + shift)%scale + shift)%capacity);
	}
	
	private void resize(int newCap){
		ArrayList<Entry<K, V>> buffer = new ArrayList<>(n);
		
		for(Entry<K, V> e: entrySet())
			buffer.add(e);
		capacity = newCap;
		createTable();
		n = 0;
		
		for(Entry<K, V> e: buffer)
			put(e.getKey(), e.getValue());
	}
	
	/* the following are the methods that will not be implemented here*/
	/* the implementation of the following values varies depending on the subclasses*/
	protected abstract void createTable();
	protected abstract V bucketGet(int h, K k);
	protected abstract V bucketPut(int h, K k, V v);
	protected abstract V bucketRemove(int h, K k);
}
