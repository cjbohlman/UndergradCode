/*************************************************************************
  *  Compilation:  javac JavaLab3.java
  *  Execution:    java JavaLab3
  *  Author: Chris Bohlman
  *  Date: July 21, 2016
  * 
  *  % java JavaLab4
  *************************************************************************/

import java.io.*;

public class JavaLab4 {
  
  public static void main(String[] args) {
    // first we define our input streams.
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);   
    
    //Constant for parallel array size
    Integer size = 5;
    Integer monthSize = 12;
    
    
    //Create array for Apt #
    String[] aptNumber= new String[size];
    aptNumber[0] = "Apt 1";
    aptNumber[1] = "Apt 2";
    aptNumber[2] = "Apt 3";
    aptNumber[3] = "Apt 4";
    aptNumber[4] = "Apt 5";
    
    //Create array for rent
    String[] rentAmount = new String[size];
    rentAmount[0] = "$100.00";
    rentAmount[1] = "$200.00";
    rentAmount[2] = "$300.00";
    rentAmount[3] = "$400.00";
    rentAmount[4] = "500.00";
    
    //Create array for names
    String[] name= new String[size];
    name[0] = "Jane Doe";
    name[1] = "John Deere";
    name[2] = "Jane Smith";
    name[3] = "John Brown";
    name[4] = "Drawyne 'The Rock' Johnson";
    
    // Create array for months
    String[] months = new String[monthSize];
    months[0] = "January";
    months[1] =  "February";
    months[2] = "March";
    months[3] = "April";
    months[4] = "May";
    months[5] = "June";
    months[6] = "July";
    months[7] = "August";
    months[8] = "September";
    months[9] = "October";
    months[10] = "November";
    months[11] = "December";
    
//Variabe to use as loop counter
    Integer index1;
    Integer index2;
    
//Instructions
    System.out.println("This program generates rent slips");
    
    for (index1 = 0; index1 < size; index1++) {
      for (index2 = 0; index2 < monthSize; index2++) {
        System.out.println ("--------------------------------------------");
        System.out.println (name[index1]+ ", " +aptNumber[index1]+ "- " +months[index2]+ " rent due: $" +rentAmount[index1]+ " on " +months[index2]+" 1st.");
        System.out.println ("Late fees will be collected on " +months[index2]+" 5th.");
      }
    }
  }
}


