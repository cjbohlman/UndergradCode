
public class LinkNode<T> {
	//instance variables
		 private T data;
		 private LinkNode<T> next;

		 //Constructor: LinkNode()
		 //makes a LinkNode object with reference to a generic element and a null next link, which needs to be set
		 public LinkNode(T node) {
		  this.data = node;
		  this.next = null;
		 }

		 //Instance method: getData
		 //returns a generic representation of the data in the current node
		 T getData() {
		  return data;
		 }

		 //Instance method: getNext
		 //returns a node representation of the next linked node
		 LinkNode<T> getNext() {
		  return next;
		 }

		 //Instance method: setData
		 //sets the data of a node to the given generic element
		 void setData(T element) {
		  this.data = element;
		 }

		 //Instance method: setNext
		 //sets the next linked node of a node to the given node element
		 void setNext(LinkNode<T> element) {
		  this.next = element;
		 }
}
