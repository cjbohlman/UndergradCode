/**********************************************************************
  * class CalendarDateTest
  * 
  * Description:
  * This class tests the CalendarDate object class from Project 1 in
  * CSc 127b. These test cases should help you figure out what parts
  * of your program are working well and what needs work, but passing
  * these test cases alone is not enough to make sure that your program
  * is 100% perfect. In order to do that (and to make sure you get the
  * best grade possible on the project), you will need to do your own
  * additional testing. What edge cases don't these tests cover?
  * 
  * To run:
  * To run this program, you will first need to place it in the same 
  * folder as your CalendarDate.java file. You can do this one of two
  * ways
  * 
  * 1) Create a new java file in your editor of choice (eclipse,
  * drjava, etc), copy and paste this program into the file, and save
  * the file in the exact same folder as CalendarDate.java
  * 2) Download this file from the course website, then move it to the
  * folder that CalendarDate is saved inside of.
  * 
  * After that, simply compile and run! You should see the results of 
  * the test printed immediately afterwards. If you're unsure what a 
  * particular test was testing, make sure you read the comments in
  * this program before posting to piazza!
  **********************************************************************/

public class CalendarDateTester{
  
  public static void main(String[] args){
    //This variable will keep track of the number of test cases
    //your program passed
    int passed = 0;
    //Here are all of the test cases! Each test case returns the
    //number 1 if you pass the test in order to increment the 
    //counter variable.
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
      System.out.println("Great job!");
    else
      System.out.println("Good work so far, but it looks like CalendarDate could use some more work.");
  }//end main
  
  /**********************************************************************
    * testing methods
    * 
    * All of these test methods follow a consistent pattern. If your program
    * passes the test, then the method will return the number one. If your
    * program fails the test, then the method wil print a helpful error 
    * message and return the number 0.
    **********************************************************************/
  
  /*
   *Can your program successfully create a CalendarDate object and
   * return a correctly formatted string?
   */
  public static int test1(){
    CalendarDate test = new CalendarDate(2016, 8, 30);
    
    if (!test.toString().equalsIgnoreCase("August 30, 2016")){
      System.out.println("Test 1 failed.\n\tExpected 'August 30, 2016', but received "+test.toString());
      return 0;
    }//end if
    else
      return 1;
  }//end method
  
  /*
   *Can your program successfully create a CalendarDate object
   * when the day field is too big to be valid?
   */
  public static int test2(){
    CalendarDate test = new CalendarDate(2016, 8, 32);
    
    if (!test.toString().equalsIgnoreCase("August 31, 2016")){
      System.out.println("Test 2 failed.\n\tExpected 'August 31, 2016', but received "+test.toString());
      return 0;
    }//end if
    else
      return 1;
  }//end method
  
  /*
   *Can your program successfully create a CalendarDate object
   * when the day field is too small to be valid?
   */
  public static int test3(){
    CalendarDate test = new CalendarDate(2016, 8, -5);
    
    if (!test.toString().equalsIgnoreCase("August 1, 2016")){
      System.out.println("Test 3 failed.\n\tExpected 'August 1, 2016', but received "+test.toString());
      return 0;
    }//end if
    else
      return 1;
  }//end method
  
  /*
   *Can your program successfully create a CalendarDate object
   * when the month field is too big to be valid?
   */
  public static int test4(){
    CalendarDate test = new CalendarDate(2016, 100, 5);
    
    if (!test.toString().equalsIgnoreCase("December 5, 2016")){
      System.out.println("Test 4 failed.\n\tExpected 'December 5, 2016', but received "+test.toString());
      return 0;
    }//end if
    else
      return 1;
  }//end method
  
  /*
   *Can your program successfully create a CalendarDate object
   * when the month field is too small to be valid?
   */
  public static int test5(){
    CalendarDate test = new CalendarDate(2016, 0, 5);
    
    if (!test.toString().equalsIgnoreCase("January 5, 2016")){
      System.out.println("Test 5 failed.\n\tExpected 'January 5, 2016', but received "+test.toString());
      return 0;
    }//end if
    else
      return 1;
  }//end method
  
  /*
   *Can your program successfully create a CalendarDate object
   * when the year field is too small to be valid?
   */
  public static int test6(){
    CalendarDate test = new CalendarDate(1700, 12, 15);
    
    if (!test.toString().equalsIgnoreCase("January 1, 1753")){
      System.out.println("Test 6 failed.\n\tExpected 'January 1, 1753', but received "+test.toString());
      return 0;
    }//end if
    else
      return 1;
  }//end method
  
  /*
   *Can your program successfully create a CalendarDate object
   * when the month is February, the day is 30, and it's a
   * leap year?
   */
  public static int test7(){
    CalendarDate test = new CalendarDate(2000, 2, 30);
    
    if (!test.toString().equalsIgnoreCase("February 29, 2000")){
      System.out.println("Test 7 failed.\n\tExpected 'February 29, 2000', but received "+test.toString());
      return 0;
    }//end if
    else
      return 1;
  }//end method
  
  /*
   *Do all of your getters work correctly?
   */
  public static int test8(){
    CalendarDate test = new CalendarDate(1775, 4, 19);
    
    if (test.getYear()!=1775 || test.getMonth()!=4 || test.getDay()!=19){
      System.out.println("Test 8 failed.\n\tExpected year=1775, month=4, day=19, but year="+ test.getYear() +
                         ", month=" + test.getMonth() + ", day=" + test.getDay());
      return 0;
    }//end if
    else
      return 1;
  }//end method
  
  /*
   *Does your getMonthAsString() method work correctly
   * for the month of December?
   */
  public static int test9(){
    CalendarDate test = new CalendarDate(2001, 12, 30);
    String monthName = "December";
    
    if (!test.getMonthAsString().equalsIgnoreCase(monthName)){
      System.out.println("Test 9 failed.\n\tExpected " + monthName +", but received "+test.getMonthAsString()+".");
      return 0;
    }//end if
    else
      return 1;
  }//end method
  
  /*
   *Does your setDate() method work correctly?
   */
  public static int test10(){
    CalendarDate test = new CalendarDate(1863, 11, 19);
    test.setDate(1991, 5, 30);
    
    if (!test.toString().equalsIgnoreCase("May 30, 1991")){
      System.out.println("Test 10 failed.\n\tExpected 'May 30, 1991', but received "+test.toString());
      return 0;
    }//end if
    else
      return 1;
  }//end method
  
  /*
   *Does your equals method work correctly for two
   * identical dates?
   */
  public static int test11(){
    CalendarDate date1 = new CalendarDate(2014, 10, 24);
    CalendarDate date2 = new CalendarDate(2014, 10, 24);
    
    if (!date1.equals(date2)){
      System.out.println("Test 11 failed.\n\tThe two dates should have been identical");
      return 0;
    }//end if
    else
      return 1;
  }//end method
  
  /*
   *Does your equals method work correctly for two
   * different dates?
   */
  public static int test12(){
    CalendarDate date1 = new CalendarDate(2014, 10, 30);
    CalendarDate date2 = new CalendarDate(2014, 10, 32);
    
    if (date1.equals(date2)){
      System.out.println("Test 12 failed.\n\tThe two dates should not have been identical");
      return 0;
    }//end if
    else
      return 1;
  }//end method
  
  /*
   *Does your tomorrow method work for a date that
   * is in the middle of a month?
   */
  public static int test13(){
    CalendarDate date1 = new CalendarDate(2014, 10, 30);
    CalendarDate date2 = date1.tomorrow();
    
    if (!date2.toString().equalsIgnoreCase("October 31, 2014")){
      System.out.println("Test 13 failed.\n\tExpected 'October 31, 2014' but received " + date2.toString());
      return 0;
    }//end if
    else
      return 1;
  }//end method
  
  /*
   *Does your tomorrow method work for a date that
   * is at the end of a month?
   */
  public static int test14(){
    CalendarDate date1 = new CalendarDate(2014, 4, 30);
    CalendarDate date2 = date1.tomorrow();
    
    if (!date2.toString().equalsIgnoreCase("May 1, 2014")){
      System.out.println("Test 14 failed.\n\tExpected 'May 1, 2014' but received " + date2.toString());
      return 0;
    }//end if
    else
      return 1;
  }//end method
  
  /*
   *Does your tomorrow method work for a date that
   * is at the end of a year?
   */
  public static int test15(){
    CalendarDate date1 = new CalendarDate(2011, 12, 31);
    CalendarDate date2 = date1.tomorrow();
    
    if (!date2.toString().equalsIgnoreCase("January 1, 2012")){
      System.out.println("Test 15 failed.\n\tExpected 'January 1, 2012' but received " + date2.toString());
      return 0;
    }//end if
    else
      return 1;
  }//end method
  
}//end CalendarDateTest