/*************************************************************************
  *  Compilation:  javac CIS129_ChrisBohlman_PC6.java
  *  Execution:    java CIS129 -ChrisBohlman_PC6
  *  author: Chris Bohlman
  * date: 7/23/2016
  * purpose: create a grading array thing
  *************************************************************************/
// this is used to allow us to use classes in all the libraries of java.io
import java.io.*;
import java.util.Scanner;

public class CIS129_ChrisBohlman_PC6 {
  
  public static void main(String[] args) {   
    //gotta get that scanner
    Scanner scanner = new Scanner(System.in );
    
    //set constants and user answer input array
    Integer Size = 20;
    String[] uAnswers = new String[Size];
    Integer index;
    Integer magicNumber = 15;
    
    //Asks for an input answer for each of the 20 questions
    for(index = 0; index<Size; index++) {
      System.out.println("Enter answer to question " +(index+1));
      uAnswers[index] = scanner.nextLine();
    }
    
    //function and getting the total number of correct answers
    Integer total; 
    total = grading(uAnswers);
    
    //grading decision statement for pass/fail
    if (total >= magicNumber) {
      System.out.println ("Congratulations, you passsed the test");
      System.out.println ("You got " +total+ " questions right");
      System.out.println ("You got " +(20 - total)+ "questions wrong");
    }
    else {
      System.out.println ("I am sorry, you failed the test");
      System.out.println ("You got " +total+ " questions right");
      System.out.println ("You got " +(20 - total)+ " questions wrong");
    }
  }
  //Function that the array is passed by reference to
  public static int grading(String[] uAnswers) {
    Integer Size = 20;
    //correct answers array
    String[] cAnswers = new String[Size];
    cAnswers[0] = "B";
    cAnswers[1] = "D";
    cAnswers[2] = "A";
    cAnswers[3] = "A";
    cAnswers[4] = "C";
    cAnswers[5] = "A";
    cAnswers[6] = "B";
    cAnswers[7] = "A";
    cAnswers[8] = "C";
    cAnswers[9] = "D";
    cAnswers[10] = "B";
    cAnswers[11] = "C"; 
    cAnswers[12] = "D"; 
    cAnswers[13] = "A"; 
    cAnswers[14] = "D";
    cAnswers[15] = "C"; 
    cAnswers[16] = "C"; 
    cAnswers[17] = "B";
    cAnswers[18] = "D"; 
    cAnswers[19] = "A";
    
    Integer numberRight = 0;
    Integer index;
    
    //loop for grading each question
    //thanks to james eli for this one
    for (index = 0; index < Size; index++) {                 
      if (uAnswers[index].equals( cAnswers[index] )) {
        numberRight = numberRight + 1;
      }
      else {
        System.out.println ("You got question " +(index+1)+" wrong.");
      } 
    }
    return numberRight;
    
  }
}