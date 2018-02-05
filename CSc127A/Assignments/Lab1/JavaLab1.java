/*************************************************************************
 *  Compilation:  javac JavaLab1.java
 *  Execution:    java JavaLab1
 *  Author: Chris Bohlman
 *  Date: June 18, 2016
 *  % java JavaLab1
 * What is your name ? Jane
 * Jane, what is your age? 30
 * What is your child's age? 10
 * you were 20 when your child was born.
 * NOTE: I have put a lot of comments to guide you in the code.
 *************************************************************************/

import java.io.*;

public class JavaLab1 {

    public static void main(String[] args) {
      // first we define our input streams.
      InputStreamReader input = new InputStreamReader(System.in);
      BufferedReader reader = new BufferedReader(input);   
      
      //define variables
      String   sName;
      Integer  motherAge;
      Integer childAge;
      Integer bornAge;
      
      // we catch exceptions if some are thrown.
      // an exception would be entering a string when a number is expected
      try {
        //ask and input name
        System.out.println("what is your name?");
        // we read a string from the stream
        sName = reader.readLine();  
        
        //ask and input age
        System.out.println(sName+" ,what is your age?");
        motherAge = Integer.parseInt(reader.readLine());
        
        //ask and input child age
        System.out.println("What is your child's age?");
        childAge = Integer.parseInt(reader.readLine());
        
        //how to figure out the age the mother was when the child was born
        bornAge = motherAge - childAge;
        
        // prints age when mother was born
        System.out.println("you were "+ bornAge + " when your child was born");
      } catch (IOException e){
            System.out.println("Error reading from user");
      }
      
    }

}


