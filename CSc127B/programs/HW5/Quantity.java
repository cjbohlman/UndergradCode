/* The Quantity interface for CSC 127B Program #4, Fall 2016
 *
 * As you can see, an interface doesn't require much content.
 * Its purpose is to provide a common set of method signatures
 * that classes agree to provide as complete methods.
 * Programmers who use the class will know exactly what to
 * expect from those methods, because they know the purpose
 * of the interface.  In this case, the interface specifies
 * three methods that deal with quantity.  The Polynomial
 * class will implement this interface. If you forget to
 * provide these methods, Java will complain when you try to
 * compile the Polynomial class.
 */

interface Quantity {
    boolean isEmpty();
    boolean isFull();
    int holding();
}