/*-------------------------------------------------------------------
 Class Node
 Chris Bohlman
 Inherits from: None
 Package Contained In: none
 
 Purpose: creates a linked node object with references to its data and its links
 
 Instance Variables:
 Card data, Node next
 
 Class Methods: n/a
 
 Instance Methods:
 getCard
 setCard
 getNext
 setNext

 -------------------------------------------------------------------*/
public class Node {
	//instance variables
	private Card data;
	private Node next;

	//Constructor: Node(card c)
	//makes a Node object with reference to a card c and a null next link, which needs to be set
	public Node(Card c) {
		this.data = c;
		this.next = null;
	}

	//Instance method: getData
	//returns a Card representation of the current card in node
	Card getData() {
		return data;
	}

	//Instance method: getNext
	//returns a node representation of the next linked node
	Node getNext() {
		return next;
	}

	//Instance method: setData
	//sets the data of a node's card to the given card element
	void setData(Card element) {
		this.data = element;
	}

	//Instance method: setNext
	//sets the next linked node of a node to the given node element
	void setNext(Node element) {
		this.next = element;
	}
}
