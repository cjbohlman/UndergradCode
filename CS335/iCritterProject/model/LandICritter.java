package model;

/**
 * @author Chris Bohlman
 * Land ICritter, which is a subclass of ICritter
 */

public abstract class LandICritter extends ICritter {

	/**
	 * Calls ICritter class constructor
	 * @param name Name of ICritter
	 * @param owner Owner of ICritter
	 */
  public LandICritter(String name, Owner owner) {
    super(name, owner);
  }
	
	/**
	* Interact with other ICritter.
	* If other ICritter is a LandICritter, add to friend list; else, do nothing
	* @param other ICritter to interact with.
	*/
  public void interact(ICritter other) {
    try {
      interestCorrelation(other);
    } catch(NoInterestsException nie) {
//	  if (other instanceof LandICritter) {
//			this.addFriend(other);
//		}
	}
    
    if (other instanceof LandICritter) {
	  this.addFriend(other);
	}

  }
	
}
