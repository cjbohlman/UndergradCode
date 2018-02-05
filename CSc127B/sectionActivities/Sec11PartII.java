/* Sec11PartII.java
 * CSc 127B Fall 2016 Section 11 Part II McCann
 * Depends on BinaryNode.java
 *
 * Testing Output:
 * ---------------
 * OnlyARoot
 * 
 *                     D
 *                 E
 *             W
 *         E
 *     K
 * S
 * 
 *         B
 *     A
 *         L
 * A
 *         N
 *     C
 *         E
 */

class Sec11PartII
{

    public static void main (String [] args)
    {
        BinaryNode<String> root = new BinaryNode<String>("OnlyARoot");
        printTree(root,0);

        System.out.println();

        root = new BinaryNode<String>("S");
        root.setRightChild(new BinaryNode<String>("K"));
        root.getRightChild().setRightChild(new BinaryNode<String>("E"));
        root.getRightChild().getRightChild().setRightChild(new BinaryNode<String>("W"));
        root.getRightChild().getRightChild().getRightChild().setRightChild(new BinaryNode<String>("E"));
        root.getRightChild().getRightChild().getRightChild().getRightChild().setRightChild(new BinaryNode<String>("D"));
        printTree(root,0);

        System.out.println();

        root = new BinaryNode<String>("A");
        root.setLeftChild(new BinaryNode<String>("C"));
        root.setRightChild(new BinaryNode<String>("A"));
        root.getLeftChild().setLeftChild(new BinaryNode<String>("E"));
        root.getLeftChild().setRightChild(new BinaryNode<String>("N"));
        root.getRightChild().setLeftChild(new BinaryNode<String>("L"));
        root.getRightChild().setRightChild(new BinaryNode<String>("B"));
        printTree(root,0);
        
    }
    
    public static void printTree(BinaryNode<String> root, int depth) {
      if (root == null) {
       
       return;
      }
     printTree(root.getRightChild(),depth+1);
     String result = "";
     for (int i = 0; i < depth; i ++) {
       result += "   ";
     }
     System.out.println(result+""+root.getData());
     
     printTree(root.getLeftChild(), depth+1);
      
    }

} // Sec11PartII