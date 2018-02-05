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
       return "\n";   
     // recursive steps
     String str = "";
     str = str+""+inorder( node.getLeft() );   
     str = str+""+node.getData();   
     str = str+""+inorder( node.getRight() );
     return str;
   }
   
   public String toStringDepth() {
     return inorderdepth(root,0);
   }
   
   public String inorderdepth(TreeNode<E> node, int count){ 
     // base case   
     if ( node == null ) {
       return "\n";   
     }
     // recursive steps
     String str = "";
     str = str+""+inorderdepth( node.getLeft() ,++count);   
     str = str+""+node.getData()+" "+count;   
     str = str+""+inorderdepth( node.getRight(),count);
     return str;
   }
   
   public int maxDepth() {
     return helper(0, root);
   }
   
   public int helper(int num, TreeNode<E> node) {
     num = num + 1;
     if (node == null)
       return --num;
     
     if (node.getLeft() == null && node.getRight() == null) {
       return num;
     }
     
     int max = 0;
     
     if (node.getRight() == null && node.getLeft() != null) { 
       max = helper(num,  node.getLeft());  
     }
     
      if (node.getRight() != null && node.getLeft() == null) { 
       max = helper(num,  node.getRight());  
     }
      
      else {
       int num1 = helper(num,  node.getRight()); 
       int num2 = helper(num,  node.getLeft()); 
       
       if (num1 > num2)
         max = num1;
       else max = num2;
       
     }
     
     return max;
   }
   
} // BinarySearchTree class