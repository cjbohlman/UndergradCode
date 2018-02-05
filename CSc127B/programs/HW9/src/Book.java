/*-------------------------------------------------------------------
	 Class Book
	 Chris Bohlman
	 Inherits from: Comparable
	 Package Contained In: ArrayList
	 
	 Purpose: creates a Book object with a title and an author list
	 
	 Instance Variables:
	 String title, ArrayList author_list
	 
	 Class Methods: n/a
	 
	 Instance Methods:
	 getTitle
	 setTitle
	 getAuhors
	 addAuthor
	 toString
	 toStringTitleOnly

	 -------------------------------------------------------------------*/
import java.util.ArrayList;

public class Book implements Comparable<Book> {
	
	//instance variables
	private String title;
	private ALinkedList<String> author_list = null;
	
	
	//constructor: creates a new book with title and list of authors
	public Book (String t) {
		this.title = t;
		author_list = new ALinkedList<String>();
	}
	
	//instance method getTitle: returns title of book
	public String getTitle() {
		return title;
	}
	
	//instance method setTitle: sets the title of book
	public void setTitle(String new_title) {
		title = new_title;
	}
	
	//instance method getAuthors: returns arraylist of  authors of current book
	public ArrayList<String> getAuthors() {
		ArrayList<String> authors = new ArrayList<String>();
		authors = author_list.getElements();
		return authors;
	}
	
	//instance method addAuthor: adds an author to the author list of current book
	public void addAuthor(String author) {
		String contains_auth = author_list.find(author);
		if (!(contains_auth!= null)) {
			author_list.insertInOrder(author);
		}
	}
	
	//instance method toString: returns a string of title and authors of the book
	public String toString() {
		String str = title+"\n";
		ArrayList<String> authors = author_list.getElements();
		if (authors.size() !=0) {
			for (int i = 0; i < authors.size(); i++) {
				str = str + authors.get(i)+"";
			}
		}
		str = str.trim();
	return str;
	}
	
	//instance method toStringTitleOnly: returns a string of title of the book ONLY
	public String toStringTitleOnly() {
		String str = title+"\n";
		str = str.trim();
	return str;
	}

	//instance method compareTo: compares 2 books and returns an int based off of comparison
	@Override
	public int compareTo(Book book) {
		int comp = this.title.compareTo(book.getTitle());
		return comp;
	}
}
