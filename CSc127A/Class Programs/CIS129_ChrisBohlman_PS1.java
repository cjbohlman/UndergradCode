/*************************************************************************
 Program: CIS129_ChrisBohlman_PS1
 Based off of the Java shell.
  Asks for serving size and then delivers a recipie.
  *************************************************************************/
import java.io.*;

public class CIS129_ChrisBohlman_PS1 {
  
  public static void main(String[] args) {
    
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);   
    
    //declarations
    int serving;
    
    try {   
      //If the serving size works
      System.out.println("How many servings is this recipe for (One person = One serving)?");
      serving = Integer.parseInt(reader.readLine());
      if (serving >0) {
        
        System.out.println("Here is Chris’ spaghetti recipe:");
        System.out.println("1. Put "+(.25*serving)+ " pounds of ground beef, " + (.25*serving)+ " teaspoons of salt, " +(serving)+ " cloves of garlic, and "+  (.25*serving)+ " of a chopped onion into a pan.");
        System.out.println("2. Brown the ground beef, making sure to fully mix all the spices into the cooked ground beef.");
        System.out.println("3. Add "+(.25*serving)+ " cans of tomato sauce and "+(.25*serving)+ " cans of diced tomatoes to pan.");
        System.out.println("4. Simmer for 1 hour at a low temperature.");
        System.out.println("5. Add one tablespoon of parmesan cheese per plate.");
        //All of the multipliers change the amounts of each thing based on serving size. Calibrated to 1 serving size
      }
      
      else //If the serving size don't work
        System.out.println("You entered an invalid serving size. Why?");
      
    } catch (IOException e){
      System.out.println("Error reading from user");
    }
    
  }
}


