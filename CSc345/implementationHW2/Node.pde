/*--------------------------------------------------------------------------------------------
 * Author: Chris Bohlman
 *
 * Class: Node
 * 
 * Description: Creates a node for a quad tree with various parameters
 *-------------------------------------------------------------------------------------------*/
class Node {
  
  //Attritbutes of node
  Node[] children;
  Segment[] seg_array;
  PVector UL;
  PVector LR;
  int size;
  boolean leaf;
  boolean flagged;

  /**
   * Constructor
   *
   * Parameters: upper left corner of node, lower right corner of node
   */
   Node(PVector ul, PVector lr) {     
     seg_array = new Segment[4];
     children = new Node[4];
     UL = ul;
     LR = lr;
     size = 0;
     leaf = true;
     flagged = false;
   } //End Constructor
   
  /* *
   * Method: Add
   * Parameters: A Segment object s
   * Description: Adds a segment to the node
   * Returns: nothing
   */
   void add(Segment s) {
     seg_array[size] = s;
     size++;
   } //End add
}