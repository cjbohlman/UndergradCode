/*************************************************************************
  *  Compilation:  javac CIS129_ChrisBohlman_Final.java
  *  Execution:    java CIS129 -ChrisBohlman_Final
  *  author: Chris Bohlman
  * date: 8/9/2016
  * purpose: create a solivng puzzle program
  *************************************************************************/
// this is used to allow us to use classes in all the libraries of java.io
import java.io.*;

public class CIS129_ChrisBohlman_Final {
  
  public static void main(String[] args) {  
    //gotta accept those readers
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);   
    
    try {
      //Introduction
      System.out.println("Imagine this figure, with three numbers where the xx's are");
      System.out.println("         (  )");
      System.out.println("        /    \\ ");
      System.out.println("      [xx]  [xx]");
      System.out.println("      /        \\ ");
      System.out.println("   (  )--[xx]--(  )");
      System.out.println("The numbers in the squares are the sums of the numbers of the circles.");
      System.out.println("This program will ask for the values of the 3 spots in [], and produce the three numbers in the circles");
      System.out.println("The numbers inputted must be between -40 and 40.");
      
      //Constant declaration
      Integer Cols = 3;
      Double[] values = new Double[Cols];
      Double[] dvalues = new Double[Cols];
      Double sumCircles;
      
      //Asking for numbers and validating inputs
      System.out.println("What is the number in the top left square?");
      values[0] = Double.parseDouble(reader.readLine());
      while (values[0]< -40 || values[0] > 40) {
        System.out.println("I'm sorry, that number is not valid. Please enter a value between -40 and 40.");
        values[0] = Double.parseDouble(reader.readLine());
      }
      
      System.out.println("What is the number in the top right square?");
      values[1] = Double.parseDouble(reader.readLine());
      while (values[1] < -40 || values[1] > 40) {
        System.out.println("I'm sorry, that number is not valid. Please enter a value between -40 and 40.");
        values[1] = Double.parseDouble(reader.readLine());
      }
      
      System.out.println("What is the number in the bottom square?");
      values[2] = Double.parseDouble(reader.readLine());
      while (values[2] < -40 || values[2] > 40) {
        System.out.println("I'm sorry, that number is not valid. Please enter a value between -40 and 40.");
        values[2] = Double.parseDouble(reader.readLine());
      }
      
      //Functions
      sumCircles = .5*(values[0] + values[1] + values[2]);
      
      dvalues[2] = getTopCircle(values, sumCircles);
      
      dvalues[0] = getLeftCircle(values, dvalues);
      
      dvalues[1] = getRightCircle(values, dvalues);
      
      //Displaying results
      System.out.println("The number in the top circle is " +dvalues[2]);
      System.out.println("The number in the left circle is " +dvalues[0]);
      System.out.println("The number in the right circle is " +dvalues[1]);
      System.out.println("Here is the filled in chart: ");
      System.out.println("         (" +dvalues[2]+ ")");
      System.out.println("        /    \\ ");
      System.out.println("      [" +values[0]+ "]  [" +values[1]+ "]");
      System.out.println("      /        \\ ");
      System.out.println("   (" +dvalues[0]+ ")--[" +values[2]+ "]--(" +dvalues[1]+ ")");
    }
      
      catch (Exception e){
        System.out.println("Error reading from user");
      }
    }
    
  //3 functions
  
  public static Double getTopCircle(Double[] array, Double sum) {
    Double total = 0.0;
    total = array[0] + array[1] - sum;
    return total;
  }
  
  public static Double getLeftCircle(Double[] array, Double[] array2) {
    Double total = 0.0;
    total = array[0] - array2[2];
    return total;
  }
  
  
  public static Double getRightCircle(Double[] array, Double[] array2) {
    Double total = 0.0;
    total = array[1] - array2[2];
    return total;
  }
  
}
