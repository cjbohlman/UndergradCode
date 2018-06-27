package model;

/**
 * @author Ilya Simkhovich
 * Exception class that gets thrown whenever correlation is gotten between two empty correlation objects
 */

public class NoInterestsException extends RuntimeException {

  public NoInterestsException() {
    super("There are no interests to use.");
  }

}