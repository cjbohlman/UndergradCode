package model;

/**
 * @author Chris Bohlman
 * Marine ICritter, which is a subclass of ICritter
 */

public abstract class MarineICritter extends ICritter{

	/**
	 * Calls MarineICritter class constructor
	 * @param name Name of ICritter
	 * @param owner Owner of ICritter
	 */
	public MarineICritter(String name, Owner owner) {
		super(name, owner);
	}
	
	/**
	* Interact with other ICritter.
	* If correlation between both ICritters is at least 50%, add to friend list
	* @param other ICritter to interact with.
	*/
	public void interact(ICritter other) {
      try {
//        interestCorrelation(other);
        if (this.interestCorrelation(other) >= .5) {
      	    this.addFriend(other);
      	  }      
      } catch(NoInterestsException nie) {
      }
      

    	  
      }
	

}
