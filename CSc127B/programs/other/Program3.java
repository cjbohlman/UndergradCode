/**********************************************************************
  Class Program3
  Chris Bohlman
  Inherits from: None
  Package Contained In: None
  
  Purpose: to run tests to see if the BinaryNumber class works effectively
  
  Instance Variables: 
  
  Classs Methods:
  Main, determines which tests ran and if they work, provides totals of how many tests worked
  
  Instance Methods: all 18 test methods are described by their comment before the method
  **********************************************************************/
import java.nio.*;

public class Program3{
  
  public static void main(String[] args){
    int passed = 0;
    passed += test1();
    passed += test2();
    passed += test3();
    passed += test4();
    passed += test5();
    passed += test6();
    passed += test7();
    passed += test8();
    passed += test9();
    passed += test10();
    passed += test11();
    passed += test12();
    passed += test13();
    passed += test14();
    passed += test15();
    passed += test16();
    passed += test17();
    passed += test18();
    System.out.println(passed+"/18 tests passed.");
    if (passed == 18)
      System.out.println("Great job!");
    else
      System.out.println("Not all of the tests worked. You done messed up.");
  }//end main
  
  //Test 1: BinaryNumber creating an object
  public static int test1(){
    BinaryNumber test = new BinaryNumber("0011010");
    
    if (!test.toString().equals("0011010")){
      System.out.println("Test 1 failed.\n\tExpected '0011010', but received "+test.toString());
      return 0;
    }//end if
    else
      System.out.println("Test 1 passed");
    return 1;
  }//end method
  
  //Test 2: Creating an object with an illegal value in it
  public static int test2(){
    try{
      BinaryNumber test = new BinaryNumber("01012");
    }
    catch (IllegalArgumentException e){
      System.out.println("Test 2 passed");
      return 1;
    }
    return 0;
  }//end method
  
  //Test 3: Creating an object that must have 3 zeroes concatenated to the beginning
  public static int test3(){
    BinaryNumber test = new BinaryNumber("1");
    if (!test.toString().equals("0001")){
      System.out.println("Test 3 failed.\n\tExpected '0001', but received "+test.toString());
      return 0;
    }//end if
    else
      System.out.println("Test 3 passed");
    return 1;
  }//end method
  
  //Test 4: Creating an object that must have 2 zeroes concatenated to the beginning
  public static int test4(){
    BinaryNumber test = new BinaryNumber("11");
    if (!test.toString().equalsIgnoreCase("0011")){
      System.out.println("Test 4 failed.\n\tExpected '0011', but received "+test.toString());
      return 0;
    }//end if
    else
      System.out.println("Test 4 passed");
    return 1;
  }//end method
  
  //Test 5: Creating an object that must have 1 zero concatenated to the beginning
  public static int test5(){
    BinaryNumber test = new BinaryNumber("111");
    if (!test.toString().equalsIgnoreCase("0111")){
      System.out.println("Test 5 failed.\n\tExpected '0111', but received "+test.toString());
      return 0;
    }//end if
    else
      System.out.println("Test 5 passed");
    return 1;
  }//end method
  
  //Test 6: Exception if agrument has more bits than the object
  public static int test6(){
    try {
      BinaryNumber test = new BinaryNumber("0111");
      test.setValue("00111");
    }
    catch(IllegalArgumentException e) {
      System.out.println("Test 6 passed");
      return 1; 
    }//end catch
    System.out.println("Test 6 did not pass");
    return 0;
  }//end method
  
  //Test 7: Equals method works with 2 identical objets
  public static int test7(){
    BinaryNumber number1 = new BinaryNumber("01010");
    BinaryNumber number2 = new BinaryNumber("01010");
    if (!(number1.equals(number2))){
      System.out.println("Test 7 failed.\n\tThe two dates should have been identical \t"+number1+"\t"+number2);
      return 0;
    }//end if
    else
      System.out.println("Test 7 passed");
    return 1;
  }//end method
  
  //Test 8: Equals method works with 2 differently sized but identical objets
  public static int test8(){
    BinaryNumber number1 = new BinaryNumber("00001010");
    BinaryNumber number2 = new BinaryNumber("01010");
    if (!(number1.equals(number2))){
      System.out.println("Test 8 failed.\n\tThe two dates should have been identical \t"+number1+"\t"+number2);
      return 0;
    }//end if
    else
      System.out.println("Test 8 passed");
    return 1;
  }//end method
  
  //Test 9: incrementBy works with a simple calculation, same number of bits
  public static int test9(){
    BinaryNumber number1 = new BinaryNumber("0011");
    number1.incrementBy("0011");
    if (number1.toString().equals("0110")){
      System.out.println("Test 9 passed ");
      return 1;
    }//end if
    else {
      System.out.println("Test 9 failed.\n\tAnswer recieved = "+number1);
      return 0;
    }
  }//end method
  
  //Test 10: incrementBy works with a simple calculation, different number of bits
  public static int test10(){
    BinaryNumber number1 = new BinaryNumber("0001");
    number1.incrementBy("101");
    if (number1.toString().equals("0110")){
      System.out.println("Test 10 passed ");
      return 1;
    }//end if
    else {
      System.out.println("Test 10 failed.\n\tAnswer recieved = "+number1);
      return 0;
    }
  }//end method
  
  //Test 11: incrementBy object's bit capacity is smaller than the arguement's, so an exception must be thrown
  public static int test11(){
    try {
      BinaryNumber number1 = new BinaryNumber("0011");
      number1.incrementBy("000000010");
    }
    catch (BufferOverflowException e) {
      System.out.println("Test 11 passed");
      return 1;
    }
    return 0;
  }//end method
  
  //Test 12: Catch an exception where a positive object is increment by a positive value and a negative value occurs
  //6 + 3
  public static int test12(){
    try{
      BinaryNumber number1 = new BinaryNumber("0110");
      number1.incrementBy("0011");
    }
    catch (ArithmeticException e) {
      System.out.println("Test 12 passed");
      return 1;
    }
    return 0;
  }//end method
  
  //Test 13: Catch an exception where a negative object is increment by a negative value and a positive value occurs.
  //-6 + -3
  public static int test13(){
    try{
      BinaryNumber number1 = new BinaryNumber("1010");
      number1.incrementBy("1101");
    }
    catch (ArithmeticException e) {
      System.out.println("Test 13 passed");
      return 1;
    }
    return 0;
  }
  
  //Test 14: decrementBy works a simple calculation, same number of bits
  public static int test14(){
    BinaryNumber number1 = new BinaryNumber("0101");
    number1.decrementBy("0010");
    if (number1.toString().equals("0011")){
      System.out.println("Test 14 passed");
      return 1;
    }//end if
    else {
      System.out.println("Test 14 failed.\n\tAnswer recieved = "+number1);
      return 0;
    }
  }//end method
  
  //Test 15: decrementBy works a calculation, different number of bits
  public static int test15(){
    BinaryNumber number1 = new BinaryNumber("0111");
    number1.decrementBy("100");
    if (number1.toString().equals("0011")){
      System.out.println("Test 15 passed");
      return 1;
    }//end if
    else {
      System.out.println("Test 15 failed.\n\tAnswer recieved = "+number1);
      return 0;
    }
  }//end method
  
  //Test 16: The object's bit capacity is smaller than the arguement's, so an exception must be thrown in decrementBy
  public static int test16(){
    try {
      BinaryNumber number1 = new BinaryNumber("0011");
      number1.decrementBy("000000010");
    }
    catch (BufferOverflowException e) {
      System.out.println("Test 16 passed");
      return 1;
    }
    return 0;
  }//end method
  
  //Test 17: Catch an exception where a negative object is decremented by a positive value and a positive value occurs.
  // -7 - +5
  public static int test17(){
    try{
      BinaryNumber number1 = new BinaryNumber("1001");
      number1.decrementBy("0101");
    }
    catch (ArithmeticException e) {
      System.out.println("Test 17 passed");
      return 1;
    }
    System.out.println("Test 17 failed");
    return 0;
  }//end method
  
  //Test 18: Catch an exception where a positive object is decremented by a negative value and a negative value occurs.
  //7 - (-5)
  public static int test18(){
    try{
      BinaryNumber number1 = new BinaryNumber("0111");
      number1.decrementBy("1011");
    }
    catch (ArithmeticException e) {
      System.out.println("Test 18 passed");
      return 1;
    }
    System.out.println("Test 18 failed");
    return 0;
  }//end method
  
}//end BinaryNumberTest