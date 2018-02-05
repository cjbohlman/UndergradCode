/*************************************************************************
 *  Compilation:  javac Array1.java
 *  Execution:    java Array1
 *  author: Odile wolf
 * date: 4-3
 * purpose: creating an array and displaying out it works.
 *************************************************************************/
// this is used to allow us to use classes in all the libraries of java.io
import java.io.*;

public class CIS129-ChrisBohlman-PC6 {

    public static void main(String[] args) {
      final int SIZE = 4;
      
      //declarations and allocation
      int[] A = new int[SIZE];
      //sets the values
      A[0] = 1;
      A[1] = 1;
      A[2] = A[0] + A[1];
      A[3] = A[2] + A[1];
      PrintArray(A);
    }
    ///////////////////// function
    public static void PrintArray(int[] A) {
      //printing
      for (int i = 0; i <A.length ; i++ ) 
        System.out.print(A[i] + " ");
      System.out.println();
    }
}


