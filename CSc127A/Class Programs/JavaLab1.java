/*************************************************************************
 *  Compilation:  javac JavaLab1.java
 *  Execution:    java JavaLab1
 *  Author: Odile wolf
 *  Date: Jan 15th 2014
 * You will modify the code to ask the user for their age and the age of their child, and output how old the mother
 * was when the child was born
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
      
      String   sName;
      Integer  motherAge;
      Integer childAge;
      Integer bornAge;
      
      // we catch exceptions if some are thrown.
      // an exception would be entering a string when a number is expected
      try {
            System.out.println("what is your name?");
            // we read a string from the stream
            sName = reader.readLine();  
            
         
            System.out.println(sName+" ,what is your age?");
            // parseint will transform a string i.e."123" into a number 123
            motherAge = Integer.parseInt(reader.readLine());
            
            System.out.println("What is your child's age?");
            // parseint will transform a string i.e."123" into a number 123
            childAge = Integer.parseInt(reader.readLine());
            
            bornAge = motherAge - childAge;
            
            // this will print "Jane, you are 30", if the inputs were "Jane" and 30
            System.out.println("you were "+ bornAge + " when your child was born");
      } catch (IOException e){
            System.out.println("Error reading from user");
      }
      
    }

}


