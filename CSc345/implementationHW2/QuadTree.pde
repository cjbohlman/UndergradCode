/*--------------------------------------------------------------------------------------------
 * Author: Chris Bohlman
 *
 * Class: QuadTree
 * 
 * Description: Creates a Quad Tree data structure that stores nodes
 *-------------------------------------------------------------------------------------------*/
class QuadTree {
  
  //Attributes of the tree
  Node root;
  
  /**
   * Constructor
   * Parameters: none
   * Description: Creates quad tree from nodes
   */
   QuadTree(int h) {     
     PVector one = new PVector(0, 0);
     PVector two;
     if (h > 512) two = new PVector(512, 512);
     else two = new PVector(h, h);
     root = new Node(one, two);
     
   }//End Constructor //<>// //<>// //<>//
   
   /* *
   * Method: printbound
   * Parameters: A Node object v
   * Description: Recursive method that prints boundsof every node/highlights flagged nodes
   * Returns: nothing
   */
   void printbound(Node v) {
     if (v == null) return;
     
     int sw;
     int s;
     if (v.flagged == false) {
       sw = 3;
       s = 150;
     }
     else {
       sw = 7;
       s = 200;
     }
     noFill();
     stroke(s,s,0);
     strokeWeight(sw);
     rect(v.UL.x, v.UL.y,(v.LR.x - v.UL.x),(v.LR.y-v.UL.y));     
     printbound(v.children[0]);
     printbound(v.children[1]);
     printbound(v.children[2]);
     printbound(v.children[3]);
   }
   
   /* *
   * Method: printsegs
   * Parameters: Node, boolean for reporting, boolean for boundary, boolean for boundary
   * Description: Prints all segments, but if reporting is on, highlights segments in the report query
   * Returns: nothing
   */
   void printsegs(Node v, boolean r_query_done, PVector report_query_ll, PVector report_query_ur) {
     if (v == null) return;
     if (v.leaf) {
       for (int i = 0; i < v.size; i++) {
         stroke(255, 255, 255);
         int sw = 1;
           if(v.seg_array[i].flagged == true) {
            v.seg_array[i].strokeweight = 3;
            sw = 3;
           }
           else {
             v.seg_array[i].strokeweight = 1;
             sw = 1;
           }
         strokeWeight(sw);
         line(v.seg_array[i].v1.x, v.seg_array[i].v1.y, v.seg_array[i].v2.x, v.seg_array[i].v2.y);
       }
     }
     else {
       printsegs(v.children[0], r_query_done, report_query_ll, report_query_ur);
       printsegs(v.children[1], r_query_done, report_query_ll, report_query_ur);
       printsegs(v.children[2], r_query_done, report_query_ll, report_query_ur);
       printsegs(v.children[3], r_query_done, report_query_ll, report_query_ur);
     }
   }
   
   /* *
   * Method: reset_anim
   * Parameters: Node
   * Description: resets all animation flags
   * Returns: nothing
   */
   void reset_anim(Node v) {
     if (v == null) return;
     v.flagged = false;
     reset_anim(v.children[0]);
     reset_anim(v.children[1]);
     reset_anim(v.children[2]);
     reset_anim(v.children[3]);
   }
 
 
  /* *
   * Method: reset_segs
   * Parameters: Node
   * Description: resets all segment flags
   * Returns: nothing
   */
  void reset_segs(Node v) {
    if (v == null) return;
      for (int i = 0; i < v.size; i++)
        v.seg_array[i].flagged = false;
     reset_segs(v.children[0]);
     reset_segs(v.children[1]);
     reset_segs(v.children[2]);
     reset_segs(v.children[3]);
   }
 
  /* *
   * Method: dupl_segs
   * Parameters: Node, segment
   * Description: checks if the second segment is a duplicate of the node's segment
   * returns true if a duplicate is found, false otherwise
   * Returns: boolean
   */
  boolean dupl_segs(Node v, Segment s) {
    if (v == null) return false;
    for (int i = 0; i < v.size; i++) {
      if (segs_equal(v.seg_array[i],s)) {
        return true;
      }
    }
    if (dupl_segs(v.children[0], s))
      return true;
    if (dupl_segs(v.children[1], s))
      return true;
    if (dupl_segs(v.children[2], s))
      return true;
    if (dupl_segs(v.children[3], s))
      return true;
     
    return false;
   }
   
   /* *
   * Method: segs_equal
   * Parameters: segment s1, segment s2
   * Description: checks of one segment is inside the other
   * Returns: true if segments are inside, false otherwise
   */
   boolean segs_equal(Segment s1, Segment s2) {
     if (s1.v1.y == s2.v1.y) {
       if ((s1.v1.x <= s2.v1.x) && (s2.v1.x <= s1.v2.x)) return true; 
     }
     return false;
   }
   
}