/*-------------------------------------------------------------------
	 Class LinkNode
	 Chris Bohlman
	 Inherits from: None
	 Package Contained In: none
	 
	 Purpose: creates a linked node object with references to its data and its links
	 
	 Instance Variables:
	 E data, Node next
	 
	 Class Methods: n/a
	 
	 Instance Methods:
	 getData
	 setData
	 getNext
	 setNext

	 -------------------------------------------------------------------*/
public class LinkNode<E> {
	 //instance variables
	 private E data;
	 private LinkNode<E> next;

	 //Constructor: LinkNode(Book b)
	 //makes a LinkNode object with reference to a generic element and a null next link, which needs to be set
	 public LinkNode(E node) {
	  this.data = node;
	  this.next = null;
	 }

	 //Instance method: getData
	 //returns a generic representation of the data in the current node
	 E getData() {
	  return data;
	 }

	 //Instance method: getNext
	 //returns a node representation of the next linked node
	 LinkNode<E> getNext() {
	  return next;
	 }

	 //Instance method: setData
	 //sets the data of a node to the given generic element
	 void setData(E element) {
	  this.data = element;
	 }

	 //Instance method: setNext
	 //sets the next linked node of a node to the given node element
	 void setNext(LinkNode<E> element) {
	  this.next = element;
	 }
}