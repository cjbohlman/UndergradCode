/*************************************************************************
 *  Compilation:  javac JavaLab1.java
 *  Execution:    java JavaLab1
 *  Author: Odile wolf
 *  Date: Jan 15 2014
 * You will then modify it to accept cookieNames which contain chocolate and/or peanut butter, but don't contain gluten
 * What is the name of the cookieName? Peanut butter jelly thumb
 * Does it contain chocolate? no
 * Does it contain gluten? no
 * Does it contain peanut butter? yes
 * thank you, I love Peanut butter jelly thumb
 * NOTE: I have put a lot of comments to guide you in the code.
 *************************************************************************/

import java.io.*;

public class JavaLab2{

    public static void main(String[] args) {
      // first we define our input streams.
      InputStreamReader input = new InputStreamReader(System.in);
      BufferedReader reader = new BufferedReader(input);   
      
      String cookieName ;
      String containsChocolate;
      String containsGluten;
      String containsPeanutButter;
      // we catch exceptions if some are thrown.
      try {
            //prompt the user for the animals name and read the input
            //the name will be used when prompting for more information
            System.out.println("what is the name of the cookie?");
            cookieName = reader.readLine();
            
            //prompt the user for the animals color and read the input
            System.out.println("Does " + cookieName + " contain chocolate?");
            containsChocolate = reader.readLine();
            
             //prompt the user for the animals color and read the input
            System.out.println("Does " + cookieName + " contain gluten?");
            containsGluten = reader.readLine();
            
             //prompt the user for the animals color and read the input
            System.out.println("Does " + cookieName + " contain peanut butter?");
            containsPeanutButter = reader.readLine();
            
            //comparing strings can be tricky, using a function 
            //such as compareTo, compareToIgnoreCase will be the best
            // 0 means the two strings are identical
            // <0 sFriendly precedes "yes", and >0 the other way around
            if (containsGluten.compareToIgnoreCase("yes")==0) {
              System.out.println("I'm sorry, I can't eat " +cookieName);
            }
            else if (containsChocolate.compareToIgnoreCase("yes")==0||containsPeanutButter.compareToIgnoreCase("yes")==0) {
            System.out.println("Thank you, I love " +cookieName);
            }
            
            else {
              System.out.println("I'm sorry, I can't eat " +cookieName);
            }
      } catch (IOException e){
            System.out.println("Error reading from user");
       }
      
    }

}


