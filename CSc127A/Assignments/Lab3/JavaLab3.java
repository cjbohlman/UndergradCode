/*************************************************************************
  *  Compilation:  javac JavaLab4.java
  *  Execution:    java JavaLab4
  *  Author: Chris Bohlman
  *  Date: July 18, 2016
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
    Final Integer size = 5;
    FInal Integer monthSize = 12;

    
    // variable declarations
    String   sName;
    Integer  numberGuessed;
    Integer  numberTries = 1;
    
    // we catch exceptions if some are thrown.
    // an exception would be entering a string when a number is expected
    try {
      System.out.println("What is your name?");
      
      // we read a string from the stream
      sName = reader.readLine();
      
      System.out.println(sName+", what number do you guess between 0 and 10?");
      // parseint will transform a string i.e."123" into a number 123
      numberGuessed = Integer.parseInt(reader.readLine());
      
      //What happens if number is wrongly guessed on 1st, 2nd, or 3rd try
      while (numberGuessed != MAGIC_NUMBER&&numberTries < 4) {
        //number is too high
        if (numberGuessed > MAGIC_NUMBER) {
          System.out.println(numberGuessed+ " is too high.");
        }
        //number is too low
        else {
          System.out.println(numberGuessed+ " is too low.");
        }
        //defaulting and asking for a new number
        System.out.println("Let’s try again: What number do you guess between 0 and 10?");
        numberGuessed = Integer.parseInt(reader.readLine());
        //adding to the number of tries
        numberTries = numberTries + 1;
      }
      
      //too many guesses
      while (numberTries ==4&&numberGuessed != MAGIC_NUMBER) {
        System.out.println(sName + ", you ran out of guesses. Sorry!");
        break;
      }
      
      //if number is guessed correctly
      while (numberGuessed == MAGIC_NUMBER) {
        System.out.println("Congratulations! You guessed the correct number in "+numberTries+ " tries.");
        break;
      }
      
    } catch (IOException e){
      System.out.println("Error reading from user");
    }
    
  }
  
}


