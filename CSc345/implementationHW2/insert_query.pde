/*--------------------------------------------------------------------------------------------
 * Author: Chris Bohlman
 *
 * Class: insert_query
 * 
 * Description: handles all insertions and reporting methods
 *-------------------------------------------------------------------------------------------*/
class insert_query{
  //attributes
  int segs;
  int nodes;
  /**
   * Constructor
   *
   * Parameters: Nothing
   */
  insert_query() {
    nodes = 1;
     segs = 0;
  } //End Constructor
   
   /* *
   * Method: insert
   * Parameters: Segment, Node, Boolean for animation
   * Description: If node is a leaf and segment is not disjoint from node, inserts segment in node
   * If node has more than 3 segments, splits node
   * Returns: nothing
   */
   void insert(Segment s, Node v, boolean anim) {
     if (v == null)
       return;

     if (disjoint_s_n(s, v))
       return;
     
     if (anim == true) {
           v.flagged = true;
       }
     
     if (v.leaf == false) {
       insert(s, v.children[0], anim);
       insert(s, v.children[1], anim);
       insert(s, v.children[2], anim);
       insert(s, v.children[3], anim);
     }
     else { //v is a leaf
       for (int i = 0; i < v.size; i++) {
         if (s.v1.x == v.seg_array[i].v1.x && s.v2.x == v.seg_array[i].v2.x && s.v1.y == v.seg_array[i].v1.y) {
           System.err.println("Segment already exists");
           segs--;
           return;
         }
       }  
       v.add(s);
       if (v.size > 3) {
          v.size = 0;
          nodes = nodes + 4;
          split(v, anim);
          v.leaf = false;
          v.seg_array = null;
       }
     }
   }
   
   /* *
   * Method: split
   * Parameters: A Node object v, Boolean for animation
   * Description: Splits node v amd adds v's segments to each node
   * Returns: nothing
   */
   void split (Node v, Boolean anim) {     
     float low_x = v.UL.x;
     float low_y = v.UL.y;
     float mid_x = mid(v.UL.x, v.LR.x);
     float mid_y =  mid(v.UL.y, v.LR.y);
     float high_x = v.LR.x;
     float high_y = v.LR.y;
     
     PVector ul = new PVector(low_x, low_y);
     PVector lr = new PVector(mid_x, mid_y);
     v.children[0] = new Node(ul, lr);
     
     ul = new PVector(mid_x, low_y);
     lr = new PVector(high_x,mid_y);
     v.children[1] = new Node(ul, lr);
     
     ul = new PVector(low_x, mid_y);
     lr = new PVector(mid_x, high_y);
     v.children[2] = new Node(ul, lr);
     
     ul = new PVector(mid_x, mid_y);
     lr = new PVector(high_x, high_y);
     v.children[3] = new Node(ul, lr);
     
     for (int i = 0; i < 4; i++) {
       for (int j = 0; j < 4; j++) {
         insert(v.seg_array[j],v.children[i], anim);
       }
     }
   }
   
   /* *
   * Method: disjoint_s_n
   * Parameters: Segment s, Node object v
   * Description: Checks if segment is disjoint from node
   * Returns: true if disjoint, false otherwise
   */
   boolean disjoint_s_n(Segment s, Node v) {
     // is y in the middle
     if (v.UL.y <= s.v1.y && s.v1.y < v.LR.y) {
       // either one of the points are within the boundaries
       if ((v.UL.x <= s.v1.x && s.v1.x < v.LR.x) || (v.UL.x <= s.v2.x && s.v2.x < v.LR.x)) {
         return false;
       }
       else if (s.v1.x <= v.UL.x && v.LR.x <= s.v2.x) {
         return false;
       }
     }
     return true;
   }
   
   /* *
   * Method: disjoint_s_q
   * Parameters: Segment s, PVector lower left, PVector upper right
   * Description: Checks if segment is disjoint from query with provided lower left and upper right corners
   * Returns: true if disjoint, false otherwise
   */
   boolean disjoint_s_q(Segment s, PVector ll, PVector ur) {
     // is y in the middle
     if (ur.y <= s.v1.y && s.v1.y < ll.y) {
       // either one of the points are within the boundaries
       if ((ll.x <= s.v1.x && s.v1.x < ur.x) || (ll.x <= s.v2.x && s.v2.x < ur.x)) {
         return false;
       }
       else if (s.v1.x <= ll.x && ur.x <= s.v2.x) {
         return false;
       }
     }
     return true;
   }
   
   /* *
   * Method: disjoint_n_q
   * Parameters: Node v, PVector lower left, PVector upper right
   * Description: Checks if node is disjoint from query with provided lower left and upper right corners
   * Returns: true if disjoint, false otherwise
   */
   boolean disjoint_n_q(Node v, PVector ll, PVector ur) {
     // is y in the middle
     
     if (v.LR.y > ll.y && ur.y > v.UL.y) {
       // either one of the points are within the boundaries
       if ((v.UL.x < ll.x && ll.x < v.LR.x) || (v.UL.x < ur.x && ur.x < v.LR.x)) {
         return false;
       }
     }
     return true;
   }
   
   /* *
   * Method: report_nodes
   * Parameters: Node v, Boolean for reporting, Boolean for lower left corner, Boolean for upper right corner
   * Description: report all nodes visited in animation mode
   * Returns: Nothing
   */
   void report_nodes(Node v, boolean r_query_done, PVector report_query_ll, PVector report_query_ur) {
     if (v == null) return;
         if (r_query_done) {
           if(disjoint_n_q(v, report_query_ll, report_query_ur) == false) {
             v.flagged = true;
             
           }
         }
       report_nodes(v.children[0], r_query_done, report_query_ll, report_query_ur);
       report_nodes(v.children[1], r_query_done, report_query_ll, report_query_ur);
       report_nodes(v.children[2], r_query_done, report_query_ll, report_query_ur);
       report_nodes(v.children[3], r_query_done, report_query_ll, report_query_ur);
   }
   
   /* *
   * Method: report_segs
   * Parameters: Node v, Boolean for reporting, Boolean for lower left corner, Boolean for upper right corner
   * Description: report all segments visited in animation mode
   * Returns: Nothing
   */
   void report_segs(Node v, boolean r_query_done, PVector report_query_ll, PVector report_query_ur) {
     if (v == null) return;
     for (int i = 0; i < v.size; i++) {
       if (r_query_done) {
         if(disjoint_s_q(v.seg_array[i], report_query_ll, report_query_ur) == false) {
           v.seg_array[i].flagged = true;
         }
         //else {
         //  v.seg_array[i].flagged = false;
         //}
       }
     }
     report_segs(v.children[0], r_query_done, report_query_ll, report_query_ur);
     report_segs(v.children[1], r_query_done, report_query_ll, report_query_ur);
     report_segs(v.children[2], r_query_done, report_query_ll, report_query_ur);
     report_segs(v.children[3], r_query_done, report_query_ll, report_query_ur);
   }
   
   /* *
   * Method: mid
   * Parameters: Float i, Float j
   * Description: calculates midpoint of i and j
   * Returns: midpoint
   */
   float mid(float j, float i) {
       return (j + i)/2;
   }
}