/*--------------------------------------------------------------------------------------------
 * Author: Chris Bohlman
 *
 * Class: MinHeap
 * 
 * Description: Creates a MinHeap object for graham scan operation
 *-------------------------------------------------------------------------------------------*/
class MinHeap {
  int size;
  PVector[] heap;
  
  /**
   * Constructor
   *
   * Parameters: array of PVectors
   */
  MinHeap(PVector[] array) {
    heap = new PVector[array.length];
    System.arraycopy(array,0,heap,0,array.length);
    size = heap.length-1;
    BuildMaxHeap(heap);
  }
   /**
   * Method: BuildMaxHeap
   * build max heap by polar angles
   Parameters: array of PVectors
   */
    void BuildMaxHeap(PVector[] array) {
      for (int i = size/2; i >= 1; i--) {
        MaxHeapify(array, i);
      }
    }
    
   /**
   * Method: MaxHeapify
   * build one iteration of max heap of polar values
   Parameters: array of PVectors, int of head of tree
   */
    void MaxHeapify(PVector[] array, int i) {
      int left = 2*i;
      int right = 2*i + 1;
      if (left <= size) {
          if (polar_angle_calc(array[0],array[i],array[left]) < 0) {
            //swap
            PVector tmp = array[i];
            array[i] = array[left];
            array[left] = tmp;
            MaxHeapify(array, left);
          }
      }
      if ( right <= size) {
       if (polar_angle_calc(array[0],array[i],array[right]) < 0) {
         //swap
          PVector tmp = array[i];
          array[i] = array[right];
          array[right] = tmp;
          MaxHeapify(array, right);
        }
      }
    }
   /**
   * Method: polar_angle_calc
   * returns polar angle calc of 3 PVectors
   * Parameters: 3 PVectors
   */
     float polar_angle_calc(PVector p0, PVector p1, PVector p2) {
      return (p1.x - p0.x)*(p2.y - p0.y) - (p2.x - p0.x)*(p1.y - p0.y);
    }
    
   /**
   * Method: HeapExtractMax
   * returns the maximum (technically minumum) element from the min heap
   Parameters: none
   */
    PVector HeapExtractMax() {
      PVector max = heap[1]; //gets element
      heap[1] = heap[size];  
      size--;
      MaxHeapify(heap,1);   //maxHeapifys remaining heap
      return max;
    }
}