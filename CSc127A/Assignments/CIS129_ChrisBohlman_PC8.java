/*************************************************************************
  *  Compilation:  javac CIS129_ChrisBohlman_PC8.java
  *  Execution:    java CIS129 -ChrisBohlman_PC8
  *  author: Chris Bohlman
  * date: 8/6/2016
  * purpose: create a sorting golf scores program
  *************************************************************************/
// this is used to allow us to use classes in all the libraries of java.io
import java.io.*;
import java.util.Scanner;

public class CIS129_ChrisBohlman_PC8 {
  
  public static void main(String[] args) {  
    //gotta accept those readers
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);   

    try {
    //set constants and user answer input array
    Integer Size = 10;
    Integer[] scores = new Integer[Size];
    Integer index;
    
    //Asks for an input answer for each of the 10 scores
    for(index = 0; index<Size; index++) {
      System.out.println("What is Score #" +(index+1));
      scores[index] = Integer.parseInt(reader.readLine());
    }
    //sort the scores
    selectionSort(scores);
    
    //display the sorted scores
    for(index = 0; index<Size; index++) {
      System.out.println(" " +scores[index]+ " ");
    }
    
    //search for a score
    Integer searchScore;
    System.out.println("What score would you like to search for?");
    searchScore = Integer.parseInt(reader.readLine());
    search(scores, searchScore);
    }
    
    catch (Exception e){
      System.out.println("Error reading from user");
  }
  }
  
  //Function that the array is passed by reference to
  public static void selectionSort(Integer[] scores) {
    //set constants
    Integer Size = 10;
    Integer startScan;
    Integer minIndex;
    Integer minValue;
    Integer index;
    
    //selection sort functions
    for (startScan = 0; startScan < Size-1; startScan++) {
      minIndex = startScan;
      minValue = scores[startScan];
      for (index = startScan + 1; index < Size; index++) {
        if (scores[index] < minValue) {
        minValue = scores[index];
        minIndex = index;
        }
      }
      
      //swap function
      Integer temp;
      temp = scores[minIndex];
        scores[minIndex] = scores[startScan];
          scores[startScan] = temp;
    }
                    }
  
  public static void search(Integer [] scores, Integer searchScore) {
    Integer Size  = 10;
     Integer startScan;
    Integer minIndex;
    Integer minValue;
    Integer index;
    
    //selection sort function again looool
    for (startScan = 0; startScan < Size-1; startScan++) {
      minIndex = startScan;
      minValue = scores[startScan];
      for (index = startScan + 1; index < Size; index++) {
        if (scores[index] < minValue) {
        minValue = scores[index];
        minIndex = index;
        }
      }
      
      //swap function
      Integer temp;
      temp = scores[minIndex];
        scores[minIndex] = scores[startScan];
          scores[startScan] = temp;
    }
    //printing all ofthe found scores
    for (index = 0; index<Size; index++) {
      if (searchScore == scores[index])
        System.out.println("Your score is the same as Score #" +(index+1));
    }
}
}
