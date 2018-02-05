/* Sec11PartIII.java
 * CSc 127B Fall 2016 Section 11 Part III McCann
 * Depends on BinaryNode.java
 *
 * Testing Output:
 * ---------------
 * This tree has no nodes.
 * 
 * "OnlyARoot" has 9 characters.
 * 
 * "S, K, E, W, E, D" has 16 characters.
 * 
 * "A, C, A, E, N, L, B" has 19 characters.
 */

import java.util.*;

class Sec11PartIII
{

    public static void main (String [] args)
    {
        BinaryNode<String> root = null;
        levelOrder(root);

        System.out.println();

        root = new BinaryNode<String>("OnlyARoot");
        levelOrder(root);

        System.out.println();

        root = new BinaryNode<String>("S");
        root.setRightChild(new BinaryNode<String>("K"));
        root.getRightChild().setRightChild(new BinaryNode<String>("E"));
        root.getRightChild().getRightChild().setRightChild(new BinaryNode<String>("W"));
        root.getRightChild().getRightChild().getRightChild().setRightChild(new BinaryNode<String>("E"));
        root.getRightChild().getRightChild().getRightChild().getRightChild().setRightChild(new BinaryNode<String>("D"));
        levelOrder(root);

        System.out.println();

        root = new BinaryNode<String>("A");
        root.setLeftChild(new BinaryNode<String>("C"));
        root.setRightChild(new BinaryNode<String>("A"));
        root.getLeftChild().setLeftChild(new BinaryNode<String>("E"));
        root.getLeftChild().setRightChild(new BinaryNode<String>("N"));
        root.getRightChild().setLeftChild(new BinaryNode<String>("L"));
        root.getRightChild().setRightChild(new BinaryNode<String>("B"));
        levelOrder(root);
        
    }
    
    public static void levelOrder(BinaryNode<String> root) {
      if (root == null) {
        System.out.println("This tree has no nodes");
        return;
      }
      else {
        LinkedList<BinaryNode<String>> queue = new LinkedList<BinaryNode<String>>();
        queue.add(root);
        System.out.print("\"");
        while(!queue.isEmpty()) {
          BinaryNode<String> temp= queue.removeLast();
          
          System.out.print(temp.getData()+", ");
          
  
          if (temp.getLeftChild() != null) {
            queue.add(temp.getLeftChild());
            
          }
          if (temp.getRightChild() != null) {
            //System.out.println("flag");
            queue.add(temp.getRightChild());
            
          }
        }
        System.out.print("\" has 0000 characters\n");
        
      }
    }
        


} // Sec11PartIII