/*************************************************************************
  *Java shell
  * 
  * NOTE: I have put a lot of comments to guide you in the code.
  *************************************************************************/


import java.io.*;
import java.util.Random;

public class CIS129_ChrisBohlman_PS2{
  
  
  public static void main(String[] args) {
    
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);   
    
    try {
      //local variables to hold money values
      double money, doubleMoney, tripleMoney;
      int betMoney;
      
      System.out.println ("Welcome to the slot machine!");
      
      System.out.println("How much money would you like to wager?");
      betMoney = Integer.parseInt(reader.readLine());
      
      money = betMoney;
      
      doubleMoney = money * 2;
      tripleMoney = money * 3;
      Random rand = new Random ();
      int Digit1, Digit2, Digit3;
      Digit1 = getDigit (rand);
      Digit2 = getDigit (rand);
      Digit3 = getDigit (rand);
      
      if (Digit1 == Digit2 && Digit1 == Digit3) {
        System.out.println("3 matched. You have won " +tripleMoney);
      }
      else if (Digit1 == Digit2) {
        System.out.println("2 matched. You have won " +doubleMoney);
      }
      else if (Digit1 == Digit3) {
        System.out.println("2 matched. You have won "+doubleMoney);
      }
      else if (Digit2 == Digit3) {
        System.out.println("2 matched. You have won " +doubleMoney);
      }
      else {
        System.out.println("You won nothing.");
      }
      
      
      
    } catch (Exception e){
      System.out.println("Error reading from user");
    }
    
    System.exit (0);
  }
  
  public static int getDigit(Random rand) {
    
    int digit = rand.nextInt(6);
    
    if (digit==1) {
      System.out.println("cherries");
    }
    else if (digit==2) {
      System.out.println("oranges");
    }
    else if (digit==3) {
      System.out.println("plums");
    }
    else if (digit==4) {
      System.out.println("bells");
    }
    else if (digit==5) {
      System.out.println("melonss");
    }
    else {
      
      System.out.println("bars");
    }
    
    return digit;    
  }

}


