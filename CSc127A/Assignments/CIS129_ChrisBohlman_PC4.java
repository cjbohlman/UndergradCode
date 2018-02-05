/*************************************************************************
Chris Bohlman
CIS 129
Program: CIS129_ChrisBohlman_PC4.java
Task: outputs a running total after 7 iterations
 *************************************************************************/


// this is used to allow us to use classes in all the libraries of java.io
import java.io.*;

public class CIS129_ChrisBohlman_PC4 {

    public static void main(String[] args) {
      // first we define our input streams.
      InputStreamReader input = new InputStreamReader(System.in);
      BufferedReader reader = new BufferedReader(input);   
      
      // put declarations here
      
      int amount;
      double cumulative = 0;
      int counter;
      // we catch exceptions if some are thrown.
      try {
        System.out.println("This program keeps a running total of how many bugs were collected for seven days.");
        //totalIterations= Integer.parseInt(reader.readLine());
        
        //step 1 = initialization of control variable
        //step 2 = test
        //step 3 = preparation for the next iteration
        
        // i++ is the same as saying i = i+1
        for (counter = 0/*step1*/ ; counter < 7/*step2*/; counter++/*step3*/ ) {
          System.out.println("How many bugs were collected on day "+ (counter+1) +"?");
          amount = Integer.parseInt(reader.readLine());
          cumulative = cumulative + amount;
        }
        // at this point we have entered totalIterations values.
        System.out.println("The amount of bugs collected over 7 days is " + cumulative);
        
      } catch (IOException e){
            System.out.println("Error reading from user");
       }
      
    }

}



