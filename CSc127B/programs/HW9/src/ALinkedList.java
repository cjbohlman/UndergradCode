
/*-------------------------------------------------------------------
  Class ALinkedList
  Chris Bohlman
  Inherits from: none
  Package Contained In: ArrayList
  
  Purpose: creates aLinkedList object of generics
  
  Instance Variables:
  LinkNode head
  
  Class Methods: n/a
  
  Instance Methods:
  insertInOrder
  remove
  getElements
  find
  toString
  toStringTitleOnly
  -------------------------------------------------------------------*/
import java.util.ArrayList;

public class ALinkedList<E> {

	// instance variables
	private LinkNode<E> head;

	// constructor: creates new aLinkedList object with a head
	public ALinkedList() {
		head = new LinkNode<E>(null);
	}

	// instance method insertInOrder: inserts a new element in list in order
	// based off of comparable method
	public void insertInOrder(E element) {
		LinkNode<E> newNode = new LinkNode<E>(element);
		LinkNode<E> walker;

		walker = head;
		while (walker.getNext() != null && ((element.toString().compareTo(walker.getData().toString()) >= 0))) {
			walker = walker.getNext();

		}
		LinkNode<E> temp = new LinkNode<E>(null);
		temp.setData(walker.getData());
		temp.setNext(walker.getNext());
		walker.setData(element);
		walker.setNext(temp);
	}

	// instance method remove: removes a certain item from list, returns true if
	// successful, false if otherwise
	public boolean remove(String out_title) {
		LinkNode<E> walker;
		walker = (LinkNode<E>) head;
		String result1[] = walker.getData().toString().split("\n");
		if (out_title.equals(result1[0])) {
			head = head.getNext();
			return true;
		}
		while (walker.getNext().getData() != null) {
			String result[] = walker.getNext().getData().toString().split("\n");
			if (result[0].equals(out_title.toString())) {
				walker.setNext(walker.getNext().getNext());
				return true;
			}
			walker = walker.getNext();
		}
		return false;
	}

	// instance method getElements: returns an arraylist of all the elements in
	// ALinkedList
	public ArrayList<E> getElements() {
		ArrayList<E> elements = new ArrayList<E>();
		LinkNode<E> walker;
		walker = head;
		while (walker.getNext() != null) {
			elements.add(walker.getData());
			walker = walker.getNext();
		}
		return elements;

	}

	// instance method find: tries to find an element in the list, returns
	// element if found, null if not found
	public E find(E element) {
		LinkNode<E> walker;
		walker = (LinkNode<E>) head;
		while (walker.getNext() != null) {
			String result[] = walker.getData().toString().split("\n");
			if (result[0].equals(element.toString())) {
				return walker.getData();
			}
			walker = walker.getNext();
		}
		return null;
	}
	
	

	// instance method toString: returns a string of the list
	public String toString() {
		LinkNode<E> walker;
		walker = (LinkNode<E>) head;
		String output = "";
		while (walker.getNext() != null) {
			output += walker.getData().toString() + "\n";
			walker = walker.getNext();
		}
		output = output.trim();
		return output;
	}

	// instance method toStringTitleOnly: returns a string of titles of the list TITLES ONLY
	public String toStringTitle() {
		LinkNode<Book> walker;
		walker = (LinkNode<Book>) head;
		String output = "";
		while (walker.getNext() != null) {
			output += walker.getData().toStringTitleOnly() + "\n";
			walker = walker.getNext();
		}
		return output;
	}
}
