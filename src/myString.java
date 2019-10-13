
public class myString {
	
	String _word;
	
	public myString(String word){
		_word = word;
	}
	
	@Override
	public boolean equals(Object anotherObject){ /* two myString only equals when the hash code is the same and the string stored inside is the same as well*/
		/* to override the equals class for the type Object: the parameter has to be an object as well, not the class*/
		
		myString anotherWord = (myString) anotherObject;
		
		return this._word.equals(anotherWord._word);
	}
	
	@Override
	public int hashCode(){ /* override the default hash code using the polynomial hash code with a = 41*/
		
		_word = this.stringConvert();
		
		int hash = 0;
		
		for(int i = 0; i < _word.length(); i++){
			hash = hash*41 + _word.charAt(i);
		}
		
		return hash;
	}

	public String stringConvert() { /* check and convert the capital letters into lower case letters if possible*/
		
		String correctedWord = "";
		
		for(int i = 0; i < _word.length(); i++){
			if((int)_word.charAt(i) < 91 && (int)_word.charAt(i) > 64){ /* the input is a capital letter*/
				correctedWord += Character.toString((char)((int)_word.charAt(i) + 32));
			}else{
				correctedWord += Character.toString(_word.charAt(i));
			}
		}
		
		return correctedWord; /* if there is no capital letter found, simply return the original string*/
		/* if there is capital letters in the string, the capitcal letters should have been all converted to lower case letters */

	}

}
