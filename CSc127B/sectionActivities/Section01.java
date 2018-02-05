
/*=============================================================================
 |   Assignment:  Section #1:  Sequence
 |       Author:  [Your Name (Your E-mail Address)]
 |       Grader:  [Your TA or Section Leader's name]
 |
 |       Course:  CSc 127B
 |   Instructor:  L. McCann
 |     Due Date:  By the end of section the first week of class.
 |
 |  Description:  This program implements the Sequence class and provides
 |                tests for its constructor and four instance methods.
 |                Students will be completing the Sequence class during
 |                their first section meeting.  Hopefully, this activity
 |                will help students remember some of the Java they have
 |                forgotten since the end of 127A!
 |
 |                A sequence is an ordered list of values.  Objects of the
 |                Sequence class store integer sequence elements in a 1-D
 |                array.  A key assumption is that every element of the array
 |                holds a sequence value.
 |                
 | Deficiencies:  None known.
 *===========================================================================*/


import java.util.Arrays;

        // The class containing the main() method of the application
        // doesn't require a class block comment in CSc 127B, because the
        // external block comment at the top explains what's going on.
        // However, you are free to include one if you wish.

public class Section01 {

    public static void main (String [] args) {

                // Test the Sequence class on a clearly non-decreasing seq.

        int[] values = {1,2,3,4};
        Sequence s = new Sequence(values);

        System.out.println("\nTest #1: " + Arrays.toString(values));

        if (s.numTerms() == 4) {
            System.out.println("Correct: numTerms() is 4.");
        } else {
            System.out.println("INCORRECT: numTerms() should be 4.");
        }

        if (s.sum() == 10) {
            System.out.println("Correct: sum() is 10.");
        } else {
            System.out.println("INCORRECT: sum() should be 10.");
        }

        if (s.isNonDecreasing()) {
            System.out.println("Correct: isNonDecreasing() is true.");
        } else {
            System.out.println("INCORRECT: isNonDecreasing() should be true.");
        }

        if (s.isArithmetic()) {
            System.out.println("Correct: isArithmetic() is true.");
        } else {
            System.out.println("INCORRECT: isArithmetic() should be true.");
        }


                // Test the Sequence class on a too-short seq.

        int[] values2 = {5};
        s = new Sequence(values2);

        System.out.println("\nTest #2: " + Arrays.toString(values2));

        if (s.numTerms() == 1) {
            System.out.println("Correct: numTerms() is 1.");
        } else {
            System.out.println("INCORRECT: numTerms() should be 1.");
        }

        if (s.sum() == 5) {
            System.out.println("Correct: sum() is 5.");
        } else {
            System.out.println("INCORRECT: sum() should be 5.");
        }

        if (!s.isNonDecreasing()) {
            System.out.println("Correct: isNonDecreasing() is false.");
        } else {
            System.out.println("INCORRECT: isNonDecreasing() should be false.");
        }

        if (!s.isArithmetic()) {
            System.out.println("Correct: isArithmetic() is false.");
        } else {
            System.out.println("INCORRECT: isArithmetic() should be false.");
        }


                // Test the Sequence class on a not non-decreasing seq.

        int[] values3 = {12, 10, 8, 6};
        s = new Sequence(values3);

        System.out.println("\nTest #3: " + Arrays.toString(values3));

        if (s.numTerms() == 4) {
            System.out.println("Correct: numTerms() is 4.");
        } else {
            System.out.println("INCORRECT: numTerms() should be 4.");
        }

        if (s.sum() == 36) {
            System.out.println("Correct: sum() is 36.");
        } else {
            System.out.println("INCORRECT: sum() should be 36.");
        }

        if (!s.isNonDecreasing()) {
            System.out.println("Correct: isNonDecreasing() is false.");
        } else {
            System.out.println("INCORRECT: isNonDecreasing() should be false.");
        }

        if (s.isArithmetic()) {
            System.out.println("Correct: isArithmetic() is true.");
        } else {
            System.out.println("INCORRECT: isArithmetic() should be true.");
        }


                // Test the Sequence class on a nondecreasing but also
                // non-arithmetic sequence.

        int[] values4 = {13, 14, 16};
        s = new Sequence(values4);

        System.out.println("\nTest #4: " + Arrays.toString(values4));

        if (s.numTerms() == 3) {
            System.out.println("Correct: numTerms() is 3.");
        } else {
            System.out.println("INCORRECT: numTerms() should be 3.");
        }

        if (s.sum() == 43) {
            System.out.println("Correct: sum() is 43.");
        } else {
            System.out.println("INCORRECT: sum() should be 43.");
        }

        if (s.isNonDecreasing()) {
            System.out.println("Correct: isNonDecreasing() is true.");
        } else {
            System.out.println("INCORRECT: isNonDecreasing() should be true.");
        }

        if (!s.isArithmetic()) {
            System.out.println("Correct: isArithmetic() is false.");
        } else {
            System.out.println("INCORRECT: isArithmetic() should be false.");
        }


                // Test the Sequence class on a longer nondecreasing
                // sequence that also is nonincreasing.

        int[] values5 = {17, 17, 17, 17, 17, 17, 17, 17, 17, 17};
        s = new Sequence(values5);

        System.out.println("\nTest #5: " + Arrays.toString(values5));

        if (s.numTerms() == 10) {
            System.out.println("Correct: numTerms() is 10.");
        } else {
            System.out.println("INCORRECT: numTerms() should be 10.");
        }

        if (s.sum() == 170) {
            System.out.println("Correct: sum() is 170.");
        } else {
            System.out.println("INCORRECT: sum() should be 170.");
        }

        if (s.isNonDecreasing()) {
            System.out.println("Correct: isNonDecreasing() is true.");
        } else {
            System.out.println("INCORRECT: isNonDecreasing() should be true.");
        }

        if (s.isArithmetic()) {
            System.out.println("Correct: isArithmetic() is true.");
        } else {
            System.out.println("INCORRECT: isArithmetic() should be true.");
        }

    } // main()

} // Section01


/*+----------------------------------------------------------------------
 ||
 ||  Class Sequence
 ||
 ||         Author:  L. McCann, 2015/08/14
 ||
 ||        Purpose:  Objects of this class represent sequences of 1 or more
 ||                  integers, and provide four elementary sequence
 ||                  operations.
 ||
 ||  Inherits From:  Object.
 ||
 ||     Interfaces:  None.
 ||
 |+-----------------------------------------------------------------------
 ||
 ||      Constants:  None.
 ||
 |+-----------------------------------------------------------------------
 ||
 ||   Constructors:  Sequence(int[] values) -- Copies the content of the
 ||                  given array of integers as the content of the sequence.
 ||                  We assume that the given array is completely full of
 ||                  sequence values.
 ||
 ||  Class Methods:  None.
 ||
 ||  Inst. Methods:  int numTerms ()
 ||                  int sum ()
 ||                  boolean isNonDecreasing ()
 ||                  boolean isArithmetic ()
 ||
 ++-----------------------------------------------------------------------*/

class Sequence {

    int[] terms;  // reference to sequence array, built by constructor


            // The constructor.  No block comment is required, but
            // adding a description in the block comment (as shown above),
            // or placing a short comment here instead, is a good idea.

    public Sequence (int[] values) {

        terms = new int[values.length];  // match size to given sequence

                // We copy the data from the given array to our local
                // array, rather than reference the given array, so that
                // we don't depend on another method's array continuing
                // to exist.

        for (int i = 0; i < values.length; i++) {
            terms[i] = values[i];
        }

    } // constructor


       /*---------------------------------------------------------------------
        |  Method numTerms()
        |
        |  Purpose:  Returns the number of terms in the sequence stored by
        |            this sequence object.
        |
        |  Pre-condition:  The local sequence array has been populated
        |
        |  Post-condition: The local sequence array is unchanged.
        |
        |  Parameters:  None.
        |
        |  Returns:  The quantity of terms in this object's sequence.
        *-------------------------------------------------------------------*/

    public int numTerms () {

        return terms.length;

    } // numTerms()


       /*---------------------------------------------------------------------
        |  Method sum()
        |
        |  Purpose:  Computes the sum of the members of this object's
        |            sequence.  In effect, this treats the sequence as
        |            a series.
        |
        |  Pre-condition:  The local sequence array has been populated
        |
        |  Post-condition: The local sequence array is unchanged.
        |
        |  Parameters: None.
        |
        |  Returns:  The computed sum of the sequence elements.
        *-------------------------------------------------------------------*/

    public int sum () {

        return -1;  // Stub method

    }  // sum()


       /*---------------------------------------------------------------------
        |  Method isNonDecreasing()
        |
        |  Purpose:  In the study of sequences, a sequence of values is
        |      non-decreasing (a.k.a. increasing) when term[x-1] is <=
        |      term[x].  The method checks whether or not this object's
        |      sequence fits this definition.  If the sequence has fewer
        |      than two elements, the sequence cannot be said to have
        |      this property.
        |
        |  Pre-condition:  The local sequence array has been populated,
        |
        |  Post-condition: The local sequence array is unchanged.
        |
        |  Parameters:  None.
        |
        |  Returns:  true if the sequence elements are nondecreasing,
        |            false otherwise.
        *-------------------------------------------------------------------*/

    public boolean isNonDecreasing () {

        boolean nonDecreasing = true;  // assume the sequence is nondecreasing

                // Sequences with 0 or 1 elements do not have sufficient
                // length for this concept to be relevant.  That is, we can't
                // say the sequence is nondecreasing.

        if (this.numTerms() < 2) return !nonDecreasing;

                // test all adjacent pairs; when a pair isn't nondecreasing,
                // we can stop early.

        for (int i = 0; i < this.numTerms()-1; i++) {
            if (terms[i] > terms[i+1]) 
                return !nonDecreasing;
        }

        return nonDecreasing;  // no problems, so it must be nondecreasing

    } // isNonDecreasing()


       /*---------------------------------------------------------------------
        |  Method isArithmetic()
        |
        |  Purpose:
        |
        |  Pre-condition:
        |
        |  Post-condition:
        |
        |  Parameters:
        |
        |  Returns:
        *-------------------------------------------------------------------*/

    public boolean isArithmetic () {

        return false;  // Stub method

    } // isArithmetic()

} // class Sequence

