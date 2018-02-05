public class BinarySearchTree
             <E extends Comparable<E>>
{
   private TreeNode<E> root;

   public void add( E item) {
      root = addHelper( item, root );
   } // add

   private TreeNode<E> addHelper( E item,
                         TreeNode<E> current )
   {
      TreeNode<E> temp;

      // base case(s)
      if ( current == null ) {
         temp = new TreeNode( item );
         return temp;
      }
      // Recursive step
      if ( current.getData().compareTo( item) > 0) {
         // go left
         temp = addHelper( item, current.getLeft());
         current.setLeftChild( temp );
         return current;
      }
      else {
         // go right
         temp = addHelper( item, current.getRight());
         current.setRightChild( temp );
         return current;
      }
   }
   
   public String toString() {
     return inorder(root);
   }
   
   public String inorder(TreeNode<E> node){ 
     // base case   
     if ( node == null )      
       return "";   
     // recursive steps
     String str = "";
     str = str+""+inorder( node.getLeft() );   
     str = str+""+node.getData();   
     str = str+""+inorder( node.getRight() );
     return str;
   }
} // BinarySearchTree class