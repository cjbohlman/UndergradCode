import java.lang.IllegalArgumentException;
import java.nio.BufferOverflowException;
import java.lang.ArithmeticException;
public class PolynomialTester{
    
    public static void main(String[] args){
        int passedOld = 0;
        int passedNew = 0;
        // Begin tests here
        passedOld += test1();
        passedOld += test2();
        passedOld += test3();
        passedOld += test4();
        passedOld += test5();
        passedOld += test6();
        passedOld += test7();
        passedOld += test8();
        passedOld += test9();
        passedOld += test10();
        passedOld += test11();
        passedOld += test12();
        passedOld += test13();
        passedOld += test14();
 //        passedOld += test15();
        passedOld = passedOld * 3;
         // New test cases
 //        passedNew += test16();
 //        passedNew += test17();
        passedNew += test18();
        passedNew += test19();
        passedNew += test20();
        passedNew += test21();
        passedNew += test22();
        passedNew += test23();
 //        passedNew += test24();
        passedNew += test25();
        passedNew *= 3;
        
        System.out.println(((passedOld + passedNew)/3)+"/25 tests passed.");
        System.out.println((passedOld + passedNew)+"/75 points earned.");
        
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
     * Use Polynomial(), see what we get for empty polynomial
     */
    public static int test1(){
        //Test of the basic constructor
         Polynomial poly = new Polynomial();
         if( poly.holding() == 0 )
             return(1);
         else{
             System.out.println( "Fail 1: Failed test of basic constructor\n");
             return(0);
         }
    }
    
    /*
     * Test adding a term
     */
    public static int test2(){     
        Polynomial poly = new Polynomial();
         poly.addTerm(4, 3);
         if( poly.toString().equals("(4)x^(3)") )
             return(1);
         else{
             System.out.println( "Fail 2: Failed test of addTerm to empty polynomial\n");
             return(0);
         }
    }
    
    /*
     * Test of evaluation
     */
    public static int test3(){
        Polynomial poly = new Polynomial();
        poly.addTerm(4, 3);
        if( poly.evaluate(10) == 4000 )
            return(1);
        else{
             System.out.println( "Fail 3: Failed test of evaluation\n");
             return(0);
         }
    }
    
    /*
     * Exception test with BinaryNumber(String), give "2". 
     */
    public static int test4(){
        Polynomial poly = new Polynomial();
        poly.addTerm(4, 3);
        poly.addTerm(16,2);
        if( poly.toString().equals("(4)x^(3) + (16)x^(2)") )
            return(1);
        else{
             System.out.println( "Fail 4: Failed adding another term to a polynomial with 1 term in it\n");
             return(0);
         }  
    }
    
    /*
     * Test adding three terms and looking for sorted toString 
     */
    public static int test5(){
        Polynomial poly = new Polynomial();
        poly.addTerm(4, 3);
        poly.addTerm(16,2);
        poly.addTerm(5, 5);
        if( poly.toString().equals("(5)x^(5) + (4)x^(3) + (16)x^(2)") )
            return(1);
        else{
             System.out.println( "Fail 5: Failed adding another term to a polynomial with 2 term in it and keeping toString sorted\n");
             return(0);
         }
    }
    
    /*
     * Test replicate
     */
    public static int test6(){
        Polynomial poly = new Polynomial();
        poly.addTerm(2,3);
        Polynomial replPoly = poly.replicate();
        if( poly.toString().equals(replPoly.toString()) )
            return(1);
        else{
             System.out.println( "Fail 6: Failed replicate\n");
             return(0);
         }
    }
    
    /*
     * Test get coefficient
     */
    public static int test7(){
        Polynomial poly = new Polynomial();
        poly.addTerm(2,3);
        if( poly.getCoefficient(3) == 2 )
            return(1);
        else{
             System.out.println( "Fail 7: Failed getCoefficient\n");
             return(0);
         }
    }
    
    /*
     * Test getCoefficient on coefficient not in polynomial
     */
    public static int test8(){
        Polynomial poly = new Polynomial();
        poly.addTerm(2,3);
        if( poly.getCoefficient(4) == 0 )
            return(1);
        else{
             System.out.println( "Fail 8: Failed getCoefficient() on a coefficient that does not exist in the polynomial\n");
             return(0);
         }
    }
    
    /*
     * Tests with adding a zero coefficient term
     */
    public static int test9(){
        //Test adding a zero coefficient to the Polynomial
        Polynomial blankPoly = new Polynomial();
        blankPoly.addTerm(0,2);
        if( blankPoly.holding() == 0)
            return(1);
        else{
             System.out.println( "Fail 9: Failed by creating a term with a zero coefficient (.holding() should be zero)\n");
             return(0);
         }
    }
    
    /*
     * Testing isEmpty
     */
    public static int test10(){
        //Test isEmpty()
        Polynomial anotherPoly = new Polynomial();
        if( anotherPoly.isEmpty() )
            return(1);
        else{
             System.out.println( "Fail 10: Failed isEmpty()\n");
             return(0);
        }
    }
    
    /*
     * Test is empty after adding a term
     */
    public static int test11(){
        Polynomial anotherPoly = new Polynomial();
        anotherPoly.addTerm(1, 1);
        if( !anotherPoly.isEmpty() )
            return(1);
        else{
             System.out.println( "Fail 11: Failed isEmpty()\n");
             return(0);
        }
    }
    
    /*
     * Test is full with large amount of ter,s
     */
    public static int test12(){
        Polynomial anotherPoly = new Polynomial();
        boolean full = false;
        for(int i = 0, k = 0; i < 1000; i++, k++)
        {
           anotherPoly.addTerm(i,k);
           if( anotherPoly.isFull() )
           {
               full = true;
               break;  
           }
        }

        if( !full ) 
            return(1);
        else{
             System.out.println( "Fail 12: Failed isEmpty() \n");
             return(0);
        }
    }
    
    /*
     * Scalar mult test
     */
    public static int test13(){
        Polynomial anotherPoly = new Polynomial();
        anotherPoly.addTerm(3,4);
        anotherPoly.scalarMultiply(4);
        if( anotherPoly.toString().equals("(12)x^(4)") )
            return(1);
        else{
             System.out.println( "Fail 13: Failed scalarMultiplication\n");
             return(0);
        }
    }
    
    /*
     * Test negate
     */
    public static int test14(){
        Polynomial anotherPoly = new Polynomial();
        anotherPoly.addTerm(3,4);
        anotherPoly.scalarMultiply(4);
        anotherPoly.addTerm(-3,5);     
        Polynomial negated = anotherPoly.negate();
        if ( negated.toString().equals("(3)x^(5) - (12)x^(4)") )
            return(1);
        else{
             System.out.println( "Fail 14: Failed negate test after adding negative term\n");
             return(0);
        }
    }
    
    /*
     * Test adding two polynomials with no like terms
     */
    public static int test15(){
        //Test of adding two polynomials
        Polynomial one = new Polynomial();
        Polynomial two = new Polynomial();
        one.addTerm(5, 3);
        one.addTerm(6, 4);
        two.addTerm(10, 5);
        Polynomial sum = one.add(two);
        if( sum.toString().equals("(10)x^(5) + (6)x^(4) + (5)x^(3)") )
            return(1);
        else{
             System.out.println( "Fail 15: Failed adding two polynomials (with no like terms)\n");
             return(0);
        }
    }
    
    /*
     * Test adding with like terms
     */
    public static int test16(){
        //Test of adding two polynomials
        Polynomial one = new Polynomial();
        Polynomial two = new Polynomial();
        one.addTerm(5, 3);
        one.addTerm(6, 4);
        two.addTerm(8, 4);
        two.addTerm(10, 5);
        Polynomial sum = one.add(two);
        if( sum.toString().equals("(10)x^(5) + (14)x^(4) + (5)x^(3)") )
            return(1);
        else{
             System.out.println( "Fail 16: Failed adding two polynomials (with like terms)\n");
             return(0);
        }
    }
    
    /*
     * Test evaluate
     */
    public static int test17(){
        //Test of adding two polynomials
        Polynomial one = new Polynomial();
        Polynomial two = new Polynomial();
        one.addTerm(5, 3);
        one.addTerm(6, 4);
        two.addTerm(8, 4);
        two.addTerm(10, 5);
        Polynomial sum = one.add(two);
        //Evaluate this more complex polynomial
        if( sum.evaluate(13) == 4123769.0 )
            return(1);
        else{
             System.out.println( "Fail 17: Failed advanced evaluate (two polynomials added then evaluated)\n");
             return(0);
        }
            
    }
    
    /*
     * Test summing to zero
     */
    public static int test18(){
        //Test of adding two polynomials
        Polynomial one = new Polynomial();
        Polynomial two = new Polynomial();
        one.addTerm(5, 3);
        two.addTerm(-5, 3);
        Polynomial sum = one.add(two);
        //Evaluate this more complex polynomial
        if( sum.isEmpty())
            return(1);
        else{
             System.out.println( "Fail 18: Failed summing -5x^3 and 5x^3\n");
             return(0);
        }
    }
    
    /*
     * equals test
     */
    public static int test19(){
        //Testing equals method
        Polynomial first = new Polynomial();
        Polynomial second = new Polynomial();
        first.addTerm(4,5);
        second.addTerm(4,5);
        if ( second.equals(first) )
            return(1);
        else{
             System.out.println( "Fail 19: Failed equals\n");
             return(0);
        }
    }
    
    /*
     * decrementBy test with 1 and 0, same sizes
     */
    public static int test20(){
        Polynomial laurent = new Polynomial();
        laurent.addTerm(4, 10);
        laurent.addTerm(8, -10);
        if( laurent.toString().equals("(4)x^(10) + (8)x^(-10)") )
            return(1);
        else{
             System.out.println( "Fail 20: Failed negative exponents\n");
             return(0);
        }
    }
    
    /*
     * Test get coefficient of negative exponent
     */
    public static int test21(){
        Polynomial laurent = new Polynomial();
        laurent.addTerm(4, 10);
        laurent.addTerm(8, -10);
        if( laurent.getCoefficient(-10) == 8 )
            return(1);
        else{
             System.out.println( "Fail 21: Failed getCoefficient of negative exponent\n");
             return(0);
        }
    }
    
    /*
     * add empty polynomials
     */
    public static int test22(){
        Polynomial p = new Polynomial();
        Polynomial p2 = new Polynomial();
        p = p.add(p2);
        if( p.holding() == 0 )
            return(1);
        else{
             System.out.println( "Fail 22: Failed adding of empty polynomials\n");
             return(0);
        }
    }
    
    /*
     * add test with negative exponent and like term
     */
    public static int test23(){
        Polynomial p = new Polynomial();
        p.addTerm(8, -10);
        p.addTerm(8, -10);
        p.addTerm(8, -10);
        p.addTerm(8, -10);
        if( p.toString().equals("(32)x^(-10)") )
            return(1);
        else{
            System.out.println(p.toString());
             System.out.println( "Fail 23: Failed adding of polynomial with like terms and negative exponents\n");
             return(0);
        }
    }
    
    /*
     * 
     */
    public static int test24(){
        Polynomial p = new Polynomial();
        Polynomial p2 = new Polynomial();
        p.addTerm(8, -10);
        p2.addTerm(-8, 10);
        p2 = p2.add(p);
        if( p2.toString().equals("- (8)x^(10) + (8)x^(-10)") )
            return(1);
        else{
             System.out.println( "Fail 24: Failed adding of polynomial with like terms and negative exponents\n");
             return(0);
        }
    }
    
    /*
     * Either constructing with empty string or additional incrementBy/decrementBy test case
     */
    public static int test25(){
        Polynomial p = new Polynomial();
        Polynomial p2 = new Polynomial();
        p.addTerm(8, -10);
        p.addTerm(8, -10);
        
        p2.addTerm(-8, -10);
        p2.addTerm(-8, -10);
        p2 = p2.add(p);
        if( p2.holding() == 0 )
            return(1);
        else{
             System.out.println( "Fail 25: Failed holding() tests when polynomials with negative exponents are added together\n");
             System.out.println(p2.toString());
             return(0);
        }
    }
}