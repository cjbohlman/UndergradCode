// A basic Binary Node class

class BinaryNode<E>
{
    private E             data;
    private BinaryNode<E> leftChild,
                          rightChild;

    public BinaryNode (E newData)
    {
        data = newData;
        leftChild = rightChild = null;
    }

            // Getters

    public E             getData ()       { return data; }
    public BinaryNode<E> getLeftChild ()  { return leftChild; }
    public BinaryNode<E> getRightChild () { return rightChild; }

            // Setters

    public void setData (E ref)                   { data = ref; }
    public void setLeftChild (BinaryNode<E> ref)  { leftChild = ref; }
    public void setRightChild (BinaryNode<E> ref) { rightChild = ref; }
}