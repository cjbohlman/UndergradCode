/*************************************************************************
 *  Compilation:  javac JavaLab2.java
 *  Execution:    java JavaLab2
 *  Author: Chris Bolman
 *  Date: June 25, 2016
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
            //prompt the user for the cookie's name and read the input
            //the name will be used when prompting for more information
            System.out.println("what is the name of the cookie?");
            cookieName = reader.readLine();
            
            //prompt the user for the chocolate content of cookie and read the input
            System.out.println("Does " + cookieName + " contain chocolate?");
            containsChocolate = reader.readLine();
            
             //prompt the user for the gluten content of cookie and read the input
            System.out.println("Does " + cookieName + " contain gluten?");
            containsGluten = reader.readLine();
            
             //prompt the user for the peanut butter content of cookie and read the input
            System.out.println("Does " + cookieName + " contain peanut butter?");
            containsPeanutButter = reader.readLine();
            
            //comparing strings can be tricky, using a function 
            //such as compareTo, compareToIgnoreCase will be the best
            // 0 means the two strings are identical
            // <0 sFriendly precedes "yes", and >0 the other way around
            //Gluten first, since it's a catch all
            if (containsGluten.compareToIgnoreCase("yes")==0) {
              System.out.println("I'm sorry, I can't eat " +cookieName);
            }
            //chocolate or peanut butter  = accept cookie
            else if (containsChocolate.compareToIgnoreCase("yes")==0||containsPeanutButter.compareToIgnoreCase("yes")==0) {
            System.out.println("Thank you, I love " +cookieName);
            }
            //Or if the cookie has nothing
            else {
              System.out.println("I'm sorry, I can't eat " +cookieName);
            }
      } catch (IOException e){
            System.out.println("Error reading from user");
       }
      
    }

}


