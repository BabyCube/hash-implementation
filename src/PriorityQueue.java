
public interface PriorityQueue <K, V>{

	int size(); /* get the size of the queue*/
	boolean isEmpty(); /* return true if the priority queue is empty*/
	Entry<K, V> insert(K key, V value) throws IllegalArgumentException; /* insert an entry to the priority queue*/
	Entry<K, V> min(); /* get the element with the min key in the queue*/
	Entry<K, V> removeMin(); /* remove the entry with the min key in the priority queue*/
	
}
