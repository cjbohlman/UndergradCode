/*  Class: Program5
 * Purpose: tests all methods in the PolynomialA class
 * 
 * 
 * 
 * 
 * */
import java.io.*;
import java.lang.*;

public class Program5A {
  
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
    System.out.println(passed+"/15 tests passed.");
    if (passed == 15)
      System.out.println("HOLY CRAP DONE");
    else
      System.out.println("Not all of the tests worked. You done messed up.");
  }//end main
  
  //Test 1: single polynomial
  public static int test1(){
    PolynomialA a = new PolynomialA();
    a.addTerm(1, 2);
    if (!a.toString().equals("1x^2")){
      System.out.println("Test 1 failed.\n\tExpected '1x^2', but received "+a.toString());
      return 0;
    }//end if
    else
      System.out.println("Test 1 passed");
    return 1;
  }//end method
  
  //Test 2: multiple polynomials, out of order
  public static int test2(){
    PolynomialA a = new PolynomialA();
    a.addTerm(4, 4);
    a.addTerm(1, 2);
    if (!a.toString().equals("1x^2 + 4x^4")){
      System.out.println("Test 2 failed.\n\tExpected '1x^2 + 4x^4', but received "+a.toString());
      return 0;
    }//end if
    else
      System.out.println("Test 2 passed");
    return 1;
  }//end method
  
  //Test 3: multiple polynomials with same exponent
  public static int test3(){
    PolynomialA a = new PolynomialA();
    a.addTerm(1, 2);
    a.addTerm(4, 2);
    if (!a.toString().equals("5x^2")){
      System.out.println("Test 3 failed.\n\tExpected '5x^2', but received "+a.toString());
      return 0;
    }//end if
    else
      System.out.println("Test 3 passed");
    return 1;
  }//end method
  
  //Test 4: evaluate method
  public static int test4(){
    PolynomialA a = new PolynomialA();
    a.addTerm(1, 2);
    a.addTerm(4, 2);
    double c = a.evaluate(2.0);
    if (c != 20.0){
      System.out.println("Test 4 failed.\n\tExpected '20', but received "+c);
      return 0;
    }//end if
    else
      System.out.println("Test 4 passed");
    return 1;
  }//end method
  
  //Test 5: scalar multiply method
  public static int test5(){
    PolynomialA a = new PolynomialA();
    a.addTerm(1, 2);
    a.addTerm(4, 3);
    a.addTerm(3, 4);
    a.scalarMultiply(2);
    if (!a.toString().equals("2x^2 + 8x^3 + 6x^4")){
      System.out.println("Test 5 failed.\n\tExpected '2x^2 + 8x^3 + 6x^4', but received "+a.toString());
      return 0;
    }//end if
    else
      System.out.println("Test 5 passed");
    return 1;
  }//end method
  
  //Test 6: negate method
  public static int test6(){
    PolynomialA a = new PolynomialA();
    PolynomialA b = new PolynomialA();
    a.addTerm(1, 2);
    a.addTerm(4, 3);
    b = a.negate();
    if (!b.toString().equals("-1x^2 + -4x^3")){
      System.out.println("Test 6 failed.\n\tExpected '-1x^2 + -4x^3', but received "+b.toString());
      return 0;
    }//end if
    else
      System.out.println("Test 6 passed");
    return 1;
  }//end method
  
  //Test 7: holding method
  public static int test7(){
    PolynomialA a = new PolynomialA();
    a.addTerm(1, 2);
    a.addTerm(4, 3);
    a.addTerm(4, 6);
    a.addTerm(4, 7);
    int c = a.holding();
    if (c != 4){
      System.out.println("Test 7 failed.\n\tExpected '4', but received "+c);
      return 0;
    }//end if
    else
      System.out.println("Test 7 passed");
    return 1;
  }//end method
  
  //Test 8: isFull for a full array
  public static int test8(){
    PolynomialA a = new PolynomialA();
    a.addTerm(1, 2);
    a.addTerm(4, 3);
    a.addTerm(4, 6);
    a.addTerm(4, 7);
    boolean c = a.isFull();
    if (!c){
      System.out.println("Test 7 failed.\n\tExpected true, but received "+c);
      return 0;
    }//end if
    else
      System.out.println("Test 8 passed");
    return 1;
  }//end method
  
  //Test 9: isEmpty for empty object
  public static int test9(){
    PolynomialA a = new PolynomialA();
    boolean c = a.isEmpty();
    if (!c){
      System.out.println("Test 9 failed.\n\tExpected true, but received "+c);
      return 0;
    }//end if
    else
      System.out.println("Test 9 passed");
    return 1;
  }//end method
  
  //Test 10: replicate method
  public static int test10(){
    PolynomialA a = new PolynomialA();
    PolynomialA b = new PolynomialA();
    a.addTerm(1, 2);
    a.addTerm(4, 3);
    a.addTerm(4, 6);
    a.addTerm(4, 7);
    b = a.replicate();
    if (!b.toString().equals("1x^2 + 4x^3 + 4x^6 + 4x^7")){
      System.out.println("Test 10 failed.\n\tExpected '1x^2 + 4x^3 + 4x^6 + 4x^7', but received "+b.toString());
      return 0;
    }//end if
    else
      System.out.println("Test 10 passed");
    return 1;
  }//end method
  
  //Test 11: equals method: true
  public static int test11(){
    PolynomialA a = new PolynomialA();
    PolynomialA b = new PolynomialA();
    a.addTerm(1, 2);
    a.addTerm(4, 3);
    b.addTerm(1, 2);
    b.addTerm(4, 3);
    boolean equalTo = b.equals(a);
    if (!equalTo){
      System.out.println("Test 11 failed.\n\tExpected '-1x^2 + -4x^3', but received "+equalTo);
      return 0;
    }//end if
    else
      System.out.println("Test 11 passed");
    return 1;
  }//end method
  
  //Test 12: equals method: false
  public static int test12(){
    PolynomialA a = new PolynomialA();
    PolynomialA b = new PolynomialA();
    a.addTerm(1, 2);
    a.addTerm(4, 3);
    b.addTerm(1, 2);
    b.addTerm(2, 4);
    boolean equalTo = b.equals(a);
    if (equalTo){
      System.out.println("Test 12 failed.\n\tExpected '-1x^2 + -4x^3', but received "+equalTo);
      return 0;
    }//end if
    else
      System.out.println("Test 12 passed");
    return 1;
  }//end method
  
  //Test 13: add method
  public static int test13(){
    PolynomialA a = new PolynomialA();
    PolynomialA b = new PolynomialA();
    PolynomialA c = new PolynomialA();
    a.addTerm(1, 2);
    a.addTerm(4, 3);
    b.addTerm(1, 2);
    b.addTerm(2, 4);
    c = a.add(b);
    if (!c.toString().equals("2x^2 + 4x^3 + 2x^4")){
      System.out.println("Test 13 failed.\n\tExpected '2x^2 + 4x^3 + 2x^4', but received "+c.toString());
      return 0;
    }//end if
    else
      System.out.println("Test 13 passed");
    return 1;
  }//end method

//Test 14: getCoefficient method
  public static int test14(){
    PolynomialA a = new PolynomialA();
    a.addTerm(1, 2);
    a.addTerm(1, 3);
    int c = a.getCoefficient(3);
    if (c != 3){
      System.out.println("Test 14 failed.\n\tExpected '4', but received "+c);
      return 0;
    }//end if
    else
      System.out.println("Test 14 passed");
    return 1;
  }//end method
  
  //Test 15: zero as a coefficient
  public static int test15(){
    PolynomialA a = new PolynomialA();
    a.addTerm(1, 2);
    a.addTerm(-1, 2);
    a.addTerm(-1, 2);
    if (!a.toString().equals("-1x^2")){
      System.out.println("Test 15 failed.\n\tExpected '', but received "+a.toString());
      return 0;
    }//end if
    else
      System.out.println("Test 15 passed");
    return 1;
  }//end method
}