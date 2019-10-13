import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V>{
	
	protected ArrayList<Entry<K, V>> heap = new ArrayList<>(); /* primary collections of the priority queue entries*/
	
	public HeapPriorityQueue() {super();} /* creates the empty heap priority queue using the natural order of the keys*/
	
	public HeapPriorityQueue(Comparator<K> comp){super(comp);} /* creates the empty heap priority queue using the defined comparator*/
	
	/* protected utilities, for the heap*/
	/* based on the implementation in the array of the heap*/
	protected int parent(int j){return (j-1)/2;}
	protected int left(int j) {return 2*j + 1;}
	protected int right(int j){return 2*j + 2;}
	protected boolean hasLeft(int j) {return left(j) < heap.size();} /* compare the size of the array list in which the heap is implemented with location of left child*/
	protected boolean hasRight(int j) {return right(j) < heap.size();} /* compare the size of the array list in which the heap is stored with the location of the right child*/
	
	/* exchanges the entries at the indices i and j of the array list*/
	protected void swap(int i, int j){
		Entry<K, V> temp = heap.get(i); /* store the element temporarily*/
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
	
	protected void upheap(int j){ /* check and change the heap to maintain the heap priority*/
		while(j>0){
			int p = parent(j);
			if(compare(heap.get(j), heap.get(p)) >= 0) break; /* the heap order is checked and it is correct*/
			swap(j, p);
			j = p; /* continue from the parent location*/
		}
	}
	
	protected void downheap(int j){ /* check and change the heap to maintain the heap priority*/
		while(hasLeft(j)){
			int leftIndex = left(j);
			int smallChildIndex = leftIndex; 
			
			if(hasRight(j)){ /* must have a left child but unsure if there is a right child present or not*/
				int rightIndex = right(j);
				
				if(compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
					smallChildIndex = rightIndex; /* the comparator is positive meaning that the left child is bigger than the right child*/
			}
			
			if(compare(heap.get(smallChildIndex), heap.get(j)) >= 0) /* checked and the heap order is correct*/
				break;
			swap(j, smallChildIndex); /* continue at the position of the child*/
			j = smallChildIndex; /* continue at the position of the child*/
		}
	}
	
	public int size() {return heap.size();} /* get the size of the heap*/
	
	public Entry<K, V> min(){ /* get the min but not remove it*/
		if(heap.isEmpty()) return null;
		return heap.get(0); /* recall that the min of the heap is stored at the root*/
	}
	
	public Entry<K, V> removeMin(){ /* remove the min of the heap and then return it*/
		if(heap.isEmpty()) return null;
		Entry<K, V> answer = heap.get(0);
		swap(0, heap.size() - 1); /* put the min item at the end*/
		heap.remove(heap.size() - 1); /* remove the last element of the heap, which store the root of the heap*/
		downheap(0); /* check and maintain the new heap order*/
		return answer;
	}
	
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException{
		checkKey(key); /* check if the key is valid, the exception might be thrown*/
		Entry<K, V> newest = new PQEntry<>(key, value);
		heap.add(newest); /* add the new entry to the end of the heap, the heap order might be violated*/ 
		upheap(heap.size() - 1); /* perform the upheap action to maintain the heap order*/
		return newest;
	}

}