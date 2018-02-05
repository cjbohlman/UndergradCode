/*************************************************************************
 *Looping
 * 
 * NOTE: I have put a lot of comments to guide you in the code.
 *************************************************************************/

// this is used to allow us to use classes in all the libraries of java.io
import java.io.*;

public class firstForLoop {

    public static void main(String[] args) {
      // first we define our input streams.
      InputStreamReader input = new InputStreamReader(System.in);
      BufferedReader reader = new BufferedReader(input);   
      
      // put declarations here
      int i, totalIterations;
      double accumulator = 0, currentValue;
      
      // we catch exceptions if some are thrown.
      try {
        System.out.println("please enter the total number of elements you want to enter");
        totalIterations= Integer.parseInt(reader.readLine());
        
        //step 1 = initialization of control variable
        //step 2 = test
        //step 3 = preparation for the next iteration
        
        // i++ is the same as saying i = i+1
        for (i = 0/*step1*/ ; i <totalIterations/*step2*/; i++/*step3*/ ) {
          System.out.println("please enter the "+ (i+1) +" element");
          currentValue = Integer.parseInt(reader.readLine());
          accumulator = accumulator + currentValue;
        }
        // at this point we have entered totalIterations values.
        System.out.println("the total number of values that you have entered is "+ accumulator);
        
      } catch (IOException e){
            System.out.println("Error reading from user");
       }
      
    }

}


