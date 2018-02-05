/*--------------------------------------------------------------------------------------------
 * Author: Chris Bohlman
 *
 * Class: Segment
 * 
 * Description: Creates a segment object for usage in the nodes
 *-------------------------------------------------------------------------------------------*/
class Segment {
  //Segment attributes
  PVector v1;
  PVector v2;
  boolean flagged;
  int strokeweight;
  
  /**
   * Constructor
   *
   * Parameters: first x-coordinate, second x-coordinate, y-coordinate
   */
   Segment(int x1, int x2, int y) {     
     v1 = new PVector(x1, y);
     v2 = new PVector(x2, y);
     flagged = false;
     strokeweight = 1;
   }//End Constructor
}