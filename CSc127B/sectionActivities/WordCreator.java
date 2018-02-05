 /*
  * I am going to use a different example so as not to just give you the answer. 
  * How about a Word class. A lot of words have prefixes and suffixes because english is a bully that beats up 
  * other languages and steals their vocabulary. So you have a word class(to represent term). 
  * So lets build a Word object and give it some attributes similar to how Term is to be used. 
  */ 
 class Word {
 
	private String prefix;
	private String sufix;
	private String root;

	/* A word constructor that takes three string arguments
	   Prefix, Sufix, Root */

	public Word(String start, String base, String end){
		this.prefix = start;
		this.sufix = end;
		this.root = base;
	}
	// An blank word constructor just in case
	public Word(){
		this.prefix = "";
		this.sufix = "";
		this.root = "";
	}

	public String toString(){
		return prefix + root + sufix;
	}
	
	public String getPrefix(){
		return this.prefix;
	}
	
	public String getSufix(){
		return this.sufix;
	}
	
	public String getRoot(){
		return this.root;
	}
	
	// Prints messages based on how many characteristics are similar between two words
	public void howSimilar(Word syn){
		
		if(this.root.equals(syn.root))
			System.out.println("The two words have the same root.");
		if(this.sufix.equals(syn.sufix))
			System.out.println("The two words have the same sufix.");
		if(this.prefix.equals(syn.prefix))
			System.out.println("The two words have the same prefix.");
		
	}
}
 
 public class WordCreator {
	 public static void main(String args[]){
		 Word word = new Word("anti", "diseslablishment", "arianism");
		 Word wordTwo = new Word("anti", "war", "");
		 
		 System.out.println(word.toString() + "\n" + wordTwo.toString());
		 word.howSimilar(wordTwo);
	 }
 }

