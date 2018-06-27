package model;

/**
 * @author Ilya Simkhovich
 * Exception class that gets thrown whenever owner has insufficient credits
 */

public class NotEnoughCreditsException extends RuntimeException {

  public NotEnoughCreditsException(int xx, int yy) {
    super("Attempted to remove " + xx + " credits, but only had " + 
    yy + " credits.");
  }

}
