/*-------------------------------------------------------------------
 Class CalenderDate
 Chris Bohlman
 Inherits from: None
 Package Contained In: None
 
 Purpose: to run several methods on a new binary number object, including setting the value to an approiate value, 
 incrementing and decrementing the number according to certain parameters.
 
 Instance Variables: 
 String bNumber: holds the BinaryNumber object
 
 Classs Methods:
 setValue: Sets the value of the binary number to fall under certain parameters, otherwise, returns an exception
 
 Instance Methods:
 toString: returns the binary number as a string
 equals: checks if the BinaryNumber object is equal to another string, even if leading zeroes are there as well
 incrementBy: increments the BinaryNumber object by another binary number string, which returns an exception according 
 to some parameters.
 decrementBy: decrements the BinaryNumber object by another binary number string by finding the twos complement of the 
 other number and adding that to the BinaryNumber object.
 -------------------------------------------------------------------*/

//import packages
import java.io.*;
import java.nio.*;

//the class
public class BinaryNumber {
  
  //the instance variable
  public String bNumber;
  
  /*********************
    First Constructor
    Purpose: sets new object's value to "00000000"
    **********************/
  public BinaryNumber() {
    this.bNumber = "00000000";
  }
  
  /****************
    Second Constructor
    Purpose: sets new objects value into the bNumber instance variable
    ****************/
  public BinaryNumber(String bNumber) {
    this.bNumber = bNumber;
    setValue(bNumber);
  }
  /****************
    Class Method: setValue
    Purpose: This setter initializes the value so it isn't illegal.
    Pre condition/Post condition: none
    Return value: none
    Parameters: String bNumber
    ****************/
  public void setValue(String bNumber) {
    String currentNum = bNumber;
    while (bNumber.length() < 4) {
      String zero = "0";
      bNumber = zero + bNumber;
    }
    int i;
    for (i = 0; i < bNumber.length(); i++) {
      char a = bNumber.charAt(i);
      if (a != '0' && a != '1') {
        throw new IllegalArgumentException(bNumber+" cannot contain a number other than 0 or 1");
      }
    }
    if (currentNum.length() > this.bNumber.length()) {
      throw new IllegalArgumentException();
    }
    this.bNumber = bNumber;
  }
  
  /****************
    Instance Method: toString
    Purpose: This method outputs bNumber as a string
    Pre condition/Post condition: none
    Return value: String bNumber
    Parameters: none
    ****************/
  public String toString() {
    return this.bNumber;
  }
  
  /****************
    Instance Method: equals
    Purpose: This method checks to see if bNumber equals a passed object
    Return value: true or false
    Parameters: BinaryNumber object
    ****************/
  public boolean equals (BinaryNumber otherbNumber ) {
    String otherNumber = otherbNumber.bNumber;
    boolean equalNumber = false;
    int i;
    int j = otherNumber.length();
    //depends on the lengths
    if( otherNumber.length() > bNumber.length()){
      for(i = bNumber.length() -1 ; i >= 0; i = i - 1) {
        j  = j - 1;
        if (otherNumber.charAt(j) != bNumber.charAt(i)){
          return equalNumber;
        }
      }
    }
    else {
      j = bNumber.length();
      for(i = otherNumber.length() -1 ; i >= 0; i = i - 1) {
        j  = j - 1;
        if (otherNumber.charAt(i) != bNumber.charAt(j)){
          return equalNumber;
        }
      }
    }
    return !equalNumber;
  }
  
  /****************
    Instance Method: incrementBy
    Purpose: This method increments the bNumber object by a given value in binary
    Return value: bNumber
    Parameters: String otherNumber
    ****************/
  public String incrementBy (String otherNumber) {
    boolean carry = false;
    String incrementNum = otherNumber;
    //shorter arguement concatenation
    while (incrementNum.length() < bNumber.length()) {
      incrementNum = "0" + incrementNum.substring(0);
    }
    int lo = bNumber.length();
    int i;
    //determines which numnbers are positive and whih are negative
    boolean bPositive = false;
    char b1 = bNumber.charAt(0);
    if (b1 == '0') {
      bPositive = true;
    }
    boolean iPositive = false;
    char i1 = incrementNum.charAt(0);
    if (i1 == '0') {
      iPositive = true;
    }
    //If number lengths don't work
    if (bNumber.length() < incrementNum.length()) {
      throw new BufferOverflowException();
    }
    else {
      int j = incrementNum.length() - 1;
      for(i = bNumber.length() - 1; i >= 0; i = i - 1) {
        char a = bNumber.charAt(i);
        char b = incrementNum.charAt(j);
        //in the event of a number needing to carry over
        if (carry == true) {
          if (a == '0') {
            a = '1';
            carry = false;
          }
          else {
            a = '0';
            carry = true;
          }
        }
        if (a == '0') {
          if (b == '1') {
            a = '1';         
          }
        }
        else {
          if (b == '1') {
            a = '0';
            carry = true;
          }
        }
        //decrements j and adjusts the bNumber string
        j = j - 1;
        bNumber = bNumber.substring(0,i) + a + bNumber.substring((i+1),lo);
      }
    }
    //checks whether answer is positive or negative
    boolean b2Positive = true;
    char b2 = this.bNumber.charAt(0);
    if (b2 == '1') {
      b2Positive = false;
    }
    //throwing exceptions
    if (bPositive && iPositive && !b2Positive) {
      throw new ArithmeticException();
    }
    else if (!bPositive && !iPositive && b2Positive) {
      throw new ArithmeticException();
    }
    return this.bNumber;
  }
  
  /****************
    Instance Method: decrementBy
    Purpose: This method decrements the bNumber object by a given value in binary, my taking the two's 
    complement of the value and adding it to the object
    Return value: bNumber
    Parameters: String otherNumber
    ****************/
  public String decrementBy (String otherNumber) {
    String decrementNum  = otherNumber;
    boolean carry = false;
    int lo = bNumber.length();
    int i;
    //shorter arguement concatenation
    while (decrementNum.length() < bNumber.length()) {
      decrementNum = "0" + decrementNum.substring(0);
    }
    //checks whether numbers are positive or negative
    boolean bPositive = false;
    char b1 = bNumber.charAt(0);
    if (b1 == '0') {
      bPositive = true;
    }
    boolean iPositive = false;
    char i1 = decrementNum.charAt(0);
    if (i1 == '0') {
      iPositive = true;
    }
    //length exception
    if (bNumber.length() < decrementNum.length()) {
      throw new BufferOverflowException();
    }
    else {
      //gotta find the twos complement first
      int j = decrementNum.length() - 1;
      //reversing the number
      for(i = 0; i <= (decrementNum.length() -1); i++){
        char a = decrementNum.charAt(i);
        if (a == '0') {
          a = '1';
          decrementNum = decrementNum.substring(0,i) + a + decrementNum.substring((i+1),decrementNum.length());
        }
        else {
          a = '0';
          decrementNum = decrementNum.substring(0,i) + a + decrementNum.substring((i+1),decrementNum.length());
        }
      }
      //adding one
      for(j = decrementNum.length() - 1; j >= 0; j = j - 1) {
        boolean done = false;
        char b = decrementNum.charAt(j);
        if (carry == true) {
          if (b == '0') {
            b = '1';
            done = true;
          }
          else {
            b = '0';
            decrementNum = decrementNum.substring(0,j) + b + decrementNum.substring((j+1),decrementNum.length());
            carry = true;                    
          }
        }
        if (carry == false) {
          if (b == '0') {
            b = '1';  
            done = true;
          }
          else if (b == '1') {
            b = '0';
            carry = true;
          }
        }
        decrementNum = decrementNum.substring(0,j) + b + decrementNum.substring((j+1),decrementNum.length());
        if(done) {
          break;
        }
      }
      //adding the number
      carry = false;
      j = decrementNum.length() - 1;
      for(i = bNumber.length() - 1; i >= 0; i = i - 1) {
        char a = bNumber.charAt(i);
        char b = decrementNum.charAt(j);
        if (carry == true) {
          if (a == '0') {
            a = '1';
            carry = false;
          }
          else {
            a = '0';
            carry = true;
          }
        }
        if (a == '0') {
          if (b == '1') {
            a = '1';         
          }
        }
        else {
          if (b == '1') {
            a = '0';
            carry = true;
          }
        }
        j = j - 1;
        bNumber = bNumber.substring(0,i) + a + bNumber.substring((i+1),lo);
      }
      
      //answer positive or negative
      boolean b2Positive = true;
      char b2 = this.bNumber.charAt(0);
      if (b2 == '1') {
        b2Positive = false;
      }
      //exceptions
      if (!bPositive && iPositive && b2Positive) {
        throw new ArithmeticException();
      }
      else if (bPositive && !iPositive && !b2Positive) {
        throw new ArithmeticException();
      }
      return this.bNumber;
    }
  }
  
}//class end