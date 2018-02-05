/*************************************************************************
  *Java shell
  * 
  * NOTE: I have put a lot of comments to guide you in the code.
  *************************************************************************/

// this is used to allow us to use classes in all the libraries of java.io
import java.io.*;

public class example {
  
  public static void main(String[] args) {
    // first we define our input streams.
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);   
    
    // put declarations here
    int myInt;
    int myAge = 2;
    // we catch exceptions if some are thrown.
    try {
      // put code here
      // we need one I/O statement which can throw an exception otherwise we will have an 
      // error. remove after you create a real io statement.
      
      System.out.println("Please enter an integer");
      myInt = Integer.parseInt(reader.readLine());
      System.out.println("this is my int:"+myInt);
      
      if ( myInt>0){
        System.out.println(" You entered a positive number: " + (myInt - myAge) +" .");
        System.out.println("You are done");
      }
      else if ( myInt <0)
        System.out.println("You just enetered a negative number");
      else
        System.out.println("You entered 0");
      
      
      
    } catch (Exception e){
      System.out.println("Error reading from user");
    }
    
  }
  
}


