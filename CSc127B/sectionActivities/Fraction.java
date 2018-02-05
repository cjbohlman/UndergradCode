/*+----------------------------------------------------------------------
 ||  Class Fraction
 ||
 ||         Author:  L. McCann
 ||
 ||        Purpose:  This is the fraction class from T01n18.java.  It
 ||                  is separated from T01n19.java to demonstrate how
 ||                  easy it is to have separate classes in separate
 ||                  .java files.  To compile T01n19 and Fraction, just
 ||                  compile T01n19.  So long as Fraction is in the same
 ||                  directory, it will be compiled, too.
 ||
 ||                  Also, this serves to demonstrate the use of the
 ||                  block comment templates.
 ||
 ||  Inherits From:  None.
 ||
 ||     Interfaces:  None.
 ||
 |+-----------------------------------------------------------------------
 ||
 ||      Constants:  None.
 ||
 |+-----------------------------------------------------------------------
 ||
 ||   Constructors:  Fraction ()
 ||                  Fraction (int numerator, int denominator)
 ||
 ||  Class Methods:  None.
 ||
 ||  Inst. Methods:  int      getNumerator ()
 ||                  int      getDenominator ()
 ||                  void     setNumerator (int newNumerator)
 ||                  void     setDenominator (int newDenominator)
 ||                  double   asDouble ()
 ||                  String   asString ()
 ||                  Fraction multiplyBy (int multiplier)
 ||                  Fraction multiplyBy (Fraction multiplier)
 ||
 ++-----------------------------------------------------------------------*/


class Fraction
{

            /* Instance Variables, a.k.a. Data Members */

    private int numerator,    // Top number of the fraction
                denominator;  // Bottom number of the fraction


       /*---------------------------------------------------------------------
        |  Method Fraction (constructors)
        |
        |  Purpose:  Two ways for Fraction objects to be created.  The first
        |            is a parameterless constructor that sets the new 
        |            fraction to hold 0/1.  The second takes integer
        |            components and constructs the fraction from them.
        |
        |  Pre-condition:  None.
        |
        |  Post-condition: Valid fractions are constructed.
        |
        |  Parameters:  For Fraction(), none.  For Fraction(int,int), the
        |               first argument is the numerator and the second the
        |               denominator.
        |
        |  Returns:  A new Fraction object.
        *-------------------------------------------------------------------*/
             
    public Fraction ()
    {
        numerator = 0;
        denominator = 1;
    }

    public Fraction (int numerator, int denominator)
    {
        this.numerator = numerator;    // "this" references the current object
        this.denominator = denominator;
    }


       /*---------------------------------------------------------------------
        |  Methods:  getNumerator(), getDenominator(), 
        |            setNumerator(), setDenominator()
        |
        |  Purpose:  Permits the programmer to access or adjust the
        |            fraction's numerator and/or denominator.
        |
        |  Pre-condition:  None.
        |
        |  Post-condition: In the case of the getters, the component is
        |                  returned and is unchanged.  For the setters,
        |                  the component is changed to match the value of
        |                  the argument.
        |
        |  Parameters:  None for getters; the appropriate fraction 
        |               component for the setters.
        |
        |  Returns:  The appropriate fraction component from the getters;
        |            nothing from the setters.
        *-------------------------------------------------------------------*/

    public int getNumerator ()
    {
        return numerator;
    }

    public int getDenominator ()
    {
        return denominator;
    }

    public void setNumerator (int newNumerator)
    {
        numerator = newNumerator;
    }

    public void setDenominator (int newDenominator)
    {
        denominator = newDenominator;
    }


       /*---------------------------------------------------------------------
        |  Method asDouble()
        |
        |  Purpose:  Returns the decimal value of the fraction.
        |
        |  Pre-condition:  The denominator is non-zero.
        |
        |  Post-condition: The ratio is returned and the fraction's value
        |                  is unchanged.
        |
        |  Parameters: None.
        |
        |  Returns:  The ratio of the numerator to the denominator.
        *-------------------------------------------------------------------*/

    public double asDouble ()
    {
        return (double)numerator / denominator;
    }


       /*---------------------------------------------------------------------
        |  Method asString()
        |
        |  Purpose:  Constructs and returns a reference to a String object
        |            containing the fraction in the form "x/y".
        |
        |  Pre-condition:  None.
        |
        |  Post-condition: The fraction is returned in string form without
        |                  changing the fraction object's content.
        |
        |  Parameters: None.
        |
        |  Returns:  The fraction in the "x/y" string form.
        *-------------------------------------------------------------------*/

    public String asString ()
    {
        return numerator + "/" + denominator;
    }


       /*---------------------------------------------------------------------
        |  Method  multiplyBy (int)
        |
        |  Purpose:  Returns a reference to a new Fraction object that is
        |            the product of the current object with the argument.
        |            The new fraction is not reduced.
        |
        |  Pre-condition:  None.
        |
        |  Post-condition: The new fraction shares the denominator with the
        |                  current fraction, the new fraction's numerator
        |                  is the product of the current fraction's numerator
        |                  with the argument, and the current fraction is
        |                  unchanged.
        |
        |  Parameters: int multiplier -- the scalar value by which the
        |                   current object's numerator is to be multiplied.
        |
        |  Returns:  A reference to the new fraction object.
        *-------------------------------------------------------------------*/

    public Fraction multiplyBy (int multiplier)
    {
        return new Fraction(numerator * multiplier,denominator);
    }


       /*---------------------------------------------------------------------
        |  Method multiplyBy (Fraction)
        |
        |  Purpose:  Constructs the product of this fraction with the argument.
        |            The new fraction is not reduced.
        |
        |  Pre-condition:  None.
        |
        |  Post-condition: The new fraction object hold the representation
        |                  of the new fraction; the current fraction is
        |                  unchanged.
        |
        |  Parameters: Fraction multiplier -- the fraction object to be
        |                  multiplied against the current fraction.
        |
        |  Returns:  A new fraction representing the product.
        *-------------------------------------------------------------------*/

    public Fraction multiplyBy (Fraction multiplier)
    {
        Fraction result;          // reference to the resulting fraction
        int      newNumerator,    // the product of the existing numerators
                 newDenominator;  // the product of the existing denominators

        newNumerator = numerator * multiplier.getNumerator();
        newDenominator = denominator * multiplier.getDenominator();
        result = new Fraction(newNumerator,newDenominator);
        return result;
    }

} // class Fraction