package model;


/**
 * @author Ilya Simkhovich
 * Slug ICritter, which is a subclass of LandICritter
 */

public class ICritterSlug extends LandICritter {

	/**
	 * Calls LandICritter class constructor
	 * @param name Name of ICritter
	 * @param owner Owner of ICritter
	 */
  public ICritterSlug(String name, Owner owner) {
    super(name, owner);
  }
  
  /**
	* Interact with other ICritter.
	* If other ICritter has no exceptions, add as friend
	* @param other ICritter to interact with.
	*/
  @Override
  public void interact(ICritter other) {

    try {
      interestCorrelation(other);
    } catch(NoInterestsException nie) {
      addFriend(other);
    }
  }

}