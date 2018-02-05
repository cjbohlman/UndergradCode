/*************************************************************************
  *Java shell
  * 
  * NOTE: I have put a lot of comments to guide you in the code.
  *************************************************************************/

// this is used to allow us to use classes in all the libraries of java.io
import java.io.*;

public class CIS129-ChrisBohlman-PC3{
  
  public static void main(String[] args) {
    // first we define our input streams.
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);   
    
    // we catch exceptions if some are thrown.
    try { 
      System.out.println ("Let's plan your week long trip.");
      System.out.println ("How much money do you have?");
      int moneyValue = Integer.parseInt(reader.readLine());
      
      System.out.println ("Do you want to fly somewhere international?");
      string hAnswer  = reader.readLine();
      if (hAnswer.compareToIgnoreCase("y")==0) {
        System.out.println ("You can go to Florence, Italy.");
        System.out.println ("Would you like stay in a hotel ($150 a night)?");
        string hoAnswer  = reader.readLine();
        if (hoAnswer.compareToIgnoreCase("y")==0) {
          int planeTicket = 1500;
          int hotelPrice = 150 * 7;
          int tripCost = planeTicket + hotelPrice;
          int leftoverMoney = moneyValue - tripCost;
          if (leftoverMoney>=0) {
            System.out.println ("Great! Your trip will cost " +tripCost ".");
            System.out.println ("Enjoy your hotel stay at Florence!");
          }
          else {
            System.out.println ("Your trip will cost " +tripCost ".");
            System.out.println ("Sorry, you don't have enough money to make this trip.");
          }
        }
        
        else (
              System.out.println ("Looks like you're staying in a hostel ($20 a night).");
              int planeTicket = 1500;
              int hostelPrice = 20 * 7;
              int tripCost = planeTicket + hostelPrice;
              int leftoverMoney = moneyValue - tripCost;
              if (leftoverMoney>=0) {
                System.out.println ("Great! Your trip will cost " +tripCost ".");
                System.out.println ("Enjoy your hostel stay at Florence!");
              }
              else {
                System.out.println ("Your trip will cost " +tripCost ".");
                System.out.println ("Sorry, you don't have enough money to make this trip.");
              }
           )
      }
      else {
        System.out.println ("Would you like to vacation in the outdoors?");
        string oAnswer  = reader.readLine();
        if (oAnswer.compareToIgnoreCase("y")==0) {
          System.out.println ("Would you like to camp?");
          string cAnswer  = reader.readLine();
          if (cAnswer.compareToIgnoreCase("y")==0) {
            int gasCost = 100;
            int campRentCost = 20 * 7;
            int tripCost = gasCost + campRentCost;
            int leftoverMoney = moneyValue - tripCost;
            
            if (leftoverMoney>=0) {
              System.out.println ("Great! Your trip will cost " +tripCost ".");
              System.out.println ("Enjoy your camping!");
            }
            
            else (
                  System.out.println ("Your trip will cost " +tripCost ".");
                  System.out.println ("Sorry, you don't have enough money to make this trip.");
                  )
          }
        }
        else ( 
              System.out.println ("Looks like you're kayaking, then.");
              
              int gasCost = 100;
              int tripCost = gasCost;
              int leftoverMoney = moneyValue - tripCost;
              
              if (leftoverMoney>=0) {
                System.out.println ("Great! Your trip will cost " +tripCost ".");
                System.out.println ("Enjoy your kayaking! Beware of mosquitos galore.");
              }
              else {
                System.out.println ("Your trip will cost " tripCost ".");
                System.out.println ("Sorry, you don't have enough money to make this trip.");
              }
              )
          
          
          else (
                System.out.println ("You want to stay indoors, then.");
                System.out.println ("Would you like to stay at a nearby resort?");
                string rAnswer  = reader.readLine();
                if (rAnswer.compareToIgnoreCase("y")==0) {
                  int tripCost = 200 * 7;
                  int leftoverMoney = moneyValue - tripCost;
                  
                  if (leftoverMoney>=0) {
                    System.out.println ("Great! Your trip will cost " +tripCost ".");
                    System.out.println ("Enjoy your staycation!");
                  }
                  
                  else (
                        System.out.println ("Your trip will cost " +tripCost ".");
                        System.out.println ("Sorry, you don't have enough money to make this trip.");
                        )
                }
  else {
  System.out.println ("Looks like you have to stay with your parents.");
  int gasCost = 100;
  int tripCost = gasCost;
  int leftoverMoney = moneyValue - tripCost;
  
  if (leftoverMoney>=0) {
    System.out.println ("Great! Your trip will cost " +tripCost ".");
    System.out.println ("Enjoy your parents!");
  }
  else {
    System.out.println ("Your trip will cost " +tripCost ".");
    System.out.println ("Sorry, you don't have enough money to make this trip.");
  }
}
)
  
  
  
  } catch (Exception e){
  System.out.println("Error reading from user");
}

}
    
  }
  
}
  
  
  
  
