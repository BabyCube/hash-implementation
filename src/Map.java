
public interface Map<K, V> {
	
	int size(); /* keep track of the size of the map*/
	
	boolean isEmpty(); /* is the map empty?*/
	
	V get(K key); /* return the value given the key*/
	
	V put(K key, V value); /* return null if the value if not in the map, return the original value being replace if it was in the map*/
	
	V remove(K key); /* remove the entry in the map given the value in the key*/
	
	Iterable<K> keySet(); /* returns an iterable collection of the key in the map*/
	
	Iterable<V> values(); /* returns an iterable collection of the values in the map*/
	
	Iterable<Entry<K, V>> entrySet(); /* returns an iterable collection of the entries in the map*/

}
