/*************************************************************************
  *Java compute an average
  * 
  * NOTE: I have put a lot of comments to guide you in the code.
  *************************************************************************/

// this is used to allow us to use classes in all the libraries of java.io
import java.io.*;

public class simpleFunction{
  
  public static void main(String[] args) {
    // first we define our input streams.
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);   
    
    // put declarations here
    double average;
    int value1, value2, value3;
    
    // we catch exceptions if some are thrown.
    try {
      display("please enter the first value you want to average");
      value1 =Integer.parseInt(reader.readLine()); 
      
      display("please enter the second value you want to average");
      value2  =Integer.parseInt(reader.readLine()); 
      
      display("please enter the third value you want to average");
      value3  =Integer.parseInt(reader.readLine()); 
      
      average = (value1 + value2 + value3)/3;
      
      display("your average is :" + average);
      
    } catch (IOException e){
      System.out.println("Error reading from user");
    }
    
  }
  /*************************** FUNCTIONS*************************/
  public static void display (String mydisplay) {
    System.out.println(mydisplay);
  }
}


