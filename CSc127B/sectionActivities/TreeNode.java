public class TreeNode<E>
{
   private E data;
   private TreeNode<E> leftChild;
   private TreeNode<E> rightChild;

   // Constructor
   public TreeNode( E myData )
   {
      setData(myData);
      setLeftChild(null);
      setRightChild(null);
   } // Constructor

   // getData, setData methods
   E getData() {
     return data;
   }
   
   void setData(E object) {
     data = object;
   }
   
   // getLeftChild,  setLeftChild  methods
   TreeNode<E> getLeft() {
     return leftChild;
   }
   
   void setLeftChild(TreeNode<E> object) {
     this.leftChild = object;
   }
   
   // getRightChild, setRightChild methods
   TreeNode<E> getRight() {
     return rightChild;
   }
   
   void setRightChild(TreeNode<E> object) {
     this.rightChild = object;
   }

} // TreeNode class