/*-------------------------------------------------------------------
	 Class BinaryNode
	 Chris Bohlman
	 Inherits from: none
	 Package Contained In: none
	 
	 Purpose: creates a BinaryNode object with generics
	 
	 Instance Variables:
	 E data, BinaryNode leftChild, BinaryNode rightChild
	 
	 Class Methods: n/a
	 
	 Instance Methods:
	 getData
	 getLeftChild
	 getRightChild
	 setData
	 setLeftChild
	 setRightChild

	 -------------------------------------------------------------------*/

public class BinaryNode<E> {
	private E data;
	private BinaryNode<E> leftChild;
	private BinaryNode<E> rightChild;

	public BinaryNode(E newData) {
		data = newData;
		leftChild = rightChild = null;
	}

	// Getters

	public E getData() {
		return data;
	}

	public BinaryNode<E> getLeftChild() {
		return leftChild;
	}

	public BinaryNode<E> getRightChild() {
		return rightChild;
	}

	// Setters

	public void setData(E ref) {
		data = ref;
	}

	public void setLeftChild(BinaryNode<E> ref) {
		leftChild = ref;
	}

	public void setRightChild(BinaryNode<E> ref) {
		rightChild = ref;
	}
}