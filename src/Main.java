import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		/* the file of the Shakespare's work will be copied to a scanner, the scanner gets word by looping through it*/
		/* then it will call the method to generate the hash code and the word is placed in to the chain hash map based on the hash code*/
		
		int currentHash = 0; /* will be changed in the while loop to store the hash code for current word*/
		String currentWord; /* will be changed in the while loop to store the current word*/
		
		ChainHashMap<myString, Integer> wordFrequency = new ChainHashMap<myString, Integer>(500); /* create the chain hash map with the size of 500; there is 500 buckets in the map*/
		
		
		File file = new File("/Users/Jacob/Downloads/shakespeare.txt");
		/*please notice that the file cannot be found on the address given above, change it to test if it works!*/
		Scanner input = new Scanner(file); /* should be changed to file after debugging*/
		
		
		while(input.hasNext()){
			currentWord = input.next();
			
			while(currentWord.equals(".") || currentWord.equals(",") || currentWord.equals("!") || currentWord.equals("?") || currentWord.equals(";") || currentWord.equals(":")){
				if(input.hasNext()){
					currentWord = input.next();
				}else{
					break;
				}
			}
			myString wordString = new myString(currentWord);
			
			if(wordFrequency.get(wordString) == null){
				
				wordFrequency.put(wordString, 1); /* the key is the string and the value should be the word frequency*/
			}else{

				wordFrequency.put(wordString, wordFrequency.get(wordString) + 1); /* if the key is already in the map, increment the word frequency by 1*/
			}	
		}
		
		
		
		/* till this point, all the work of Shakespeare have been stored in the chained hash map*/
		/* the heap is used to get the top 1000 frequent work*/
		/* it adds the element to the heap and when the size of the heap reaches 1000, the word with the lowest  will be removed, which is the root of the heap*/
		/* the two element in the entry for the heap should be exchanged; the key should store the frequencies and the the value should be the string */
		
		HeapAdaptablePriorityQueue<Integer, String> frequencyHeap = new HeapAdaptablePriorityQueue<Integer, String>();
		
		for(Entry<myString, Integer> word: wordFrequency.entrySet()){
			int currentFrequency = word.getValue(); /* store the value of the entry in the chain hash map, will be used as the key for the heap*/
			String currentString = word.getKey()._word; /* store the string in the key of the entry of the chain hash map, will be used as the value for the heap*/
			
			frequencyHeap.insert(currentFrequency, currentString);
			
			if(frequencyHeap.size() > 1000){				
				frequencyHeap.removeMin(); /* keep the heap size fixed at 1000; remove the minimum in the heap if the size exceeds 1000*/		
			}
		}
		
		/* till now, all the entries has been stored in the heap*/
		/* get the minimum value and key (the frequency and the corresponding word), then remove the minimum from the heap*/
		/* the data is stored in the excel form; the screen shot is provided in the document*/
		
		while(frequencyHeap.size() > 0){
			System.out.println(frequencyHeap.min().getValue()); /* get the word from the ascending order of the frequency*/
			System.out.println(frequencyHeap.min().getKey()); /* get the frequency of the word*/
			
			frequencyHeap.removeMin();
		}

		
	}


}
