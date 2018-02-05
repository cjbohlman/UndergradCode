/*--------------------------------------------------------------------------------------------
 * Author: Chris Bohlman
 *
 * Class: ArrayStack
 * 
 * Description: Creates an ArrayStack object for convex hull operation
 *-------------------------------------------------------------------------------------------*/
class ArrayStack {
  int size;
  PVector[] array;
  
  /**
   * Constructor
   *
   * Parameters: size of stack
   */
   ArrayStack(int s) {     
     array = new PVector[s]; 
     size = 0;
   }//End Constructor
   
   /**
   * Method: push
   * Pushes an element onto the stack
   */
   void push(PVector x, int s) {
     array[size] = x;
     ++size;
   }
   
   /**
   * Method: top
   * Returns element at top of stack
   */
   PVector top(int s) {
     return array[size-1];
   }
   
   /**
   * Method: pop
   * Pops an element from the top of the stack
   */
   void pop() {
     --size;
   }
     
     /**
   * Method: next_to_top
   * Returns element at next to top of stack
   */
   PVector next_to_top(int s) {
     return array[size - 2];
   }
   
   /**
   * Method: get
   * Returns element from stack
   */
   PVector get(int s) {
     return array[s];
   }
}