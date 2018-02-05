/*************************************************************************
 *Java shell
 * 
 * NOTE: I have put a lot of comments to guide you in the code.
 *************************************************************************/

// this is used to allow us to use classes in all the libraries of java.io
import java.io.*;

public class PS1 {
  
    public static void main(String[] args) {
      // first we define our input streams.
      InputStreamReader input = new InputStreamReader(System.in);
      BufferedReader reader = new BufferedReader(input);   
      
      // put declarations here
      int serving;
      // we catch exceptions if some are thrown.
      try {
           // put code here
           // we need one I/O statement which can throw an exception otherwise we will have an 
           // error. remove after you create a real io statement.
       /* System.out.println("please enter an integer");
        myInt = Integer.parseInt(reader.readLine());
        System.out.println("this is my int :"+myInt);
        
        if ( myInt >0) 
          System.out.println(" you entered a positive number");
        else if (myInt <0)
          System.out.println("you entered a negative number");
        else 
          System.out.println("you entered 0");*/
        
        System.out.println("How many servings is this recipe for (One person = One serving)?");
        serving = Integer.parseInt(reader.readLine());
        System.out.println("Here is Chris’ spaghetti recipe:");
          System.out.println("1. Put "+(.25*serving)+ " pounds of ground beef, " + (.25*serving)+ " teaspoons of salt, " +(serving)+ " cloves of garlic, and "+  (.25*serving)+ " of a chopped onion into a pan.");
          System.out.println("2. Brown the ground beef, making sure to fully mix all the spices into the cooked ground beef.");
          System.out.println("3. Add "+(.25*serving)+ " cans of tomato sauce and "+(.25*serving)+ " cans of diced tomatoes to pan.");
          System.out.println("4. Simmer for 1 hour at a low temperature.");
          System.out.println("5. Add one tablespoon of parmesan cheese per plate.");

        
      } catch (IOException e){
            System.out.println("Error reading from user");
       }
      
    }
}


