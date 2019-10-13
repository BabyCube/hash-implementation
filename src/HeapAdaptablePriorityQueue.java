import java.util.Comparator;


public class HeapAdaptablePriorityQueue<K, V> extends HeapPriorityQueue<K, V >{
	
	protected static class AdaptablePQEntry<K, V> extends PQEntry<K, V>{ /* modify the entry to let it contain the location information*/
		private int index; /* current index within the heap*/
		
		public AdaptablePQEntry(K key, V value, int j){
			super(key, value); /* call the constructor of the PQ entry*/
			index = j; /* the entry for the adaptable priority queue will include the location awareness entry*/
		}
		
		public int getIndex() {return index;} /* get the index of the entry*/
		public void setIndex(int j) {index = j;}
	}
	
	public HeapAdaptablePriorityQueue() {super();} /* construct the empty priority queue using the default comparator*/
	
	public HeapAdaptablePriorityQueue(Comparator<K> comp) {super(comp);} /*construct the empty queue using the given comparator*/
	
	protected AdaptablePQEntry<K, V> validate(Entry<K, V> entry) throws IllegalArgumentException{
		if(!(entry instanceof AdaptablePQEntry))
			throw new IllegalArgumentException("Invalid Entry");
		
		AdaptablePQEntry<K, V> locator = (AdaptablePQEntry<K, V>) entry; 
		int j = locator.getIndex();
		if(j >= heap.size() || heap.get(j) != locator)
			throw new IllegalArgumentException("Invalid Entry");
		return locator;
	}
	
	protected void swap(int i, int j){
		super.swap(i, j);
		((AdaptablePQEntry<K, V>) heap.get(i)).setIndex(i); /* reset the index after the swap has been performed*/
		((AdaptablePQEntry<K, V>) heap.get(j)).setIndex(j); /* reset the index after the swap has been performed*/
	}
	
	protected void bubble(int j){
		if(j>0 && compare(heap.get(j), heap.get(parent(j))) < 0)
			upheap(j);
		else
			downheap(j); /* although the downheap may not be necessary*/
	}
	
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException{
		checkKey(key);
		Entry<K, V> newest = new AdaptablePQEntry<>(key, value, heap.size());
		heap.add(newest); /* add to the end of the queue*/
		upheap(heap.size() - 1); /* do the upheap to the new entry to maintain the heap order*/
		return newest;
	}
	
	public void remove(Entry<K, V> entry) throws IllegalArgumentException{
		AdaptablePQEntry<K, V> locator = validate(entry);
		int j = locator.getIndex();
		if(j == heap.size() - 1)
			heap.remove(heap.size() - 1); /* remove the last element if it is the entry*/
		else{
			swap(j, heap.size()-1);
			heap.remove(heap.size()-1);
			bubble(j); /* fix the heap order*/
		}
	}
	
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException{
		AdaptablePQEntry<K, V> locator = validate(entry);
		checkKey(key);
		locator.setKey(key);
		bubble(locator.getIndex());
	}
	
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException{
		AdaptablePQEntry<K, V> locator = validate(entry);
		locator.setValue(value);
	}

}