package model;

/**
 * @author Chris Bohlman
 * Dog ICritter, which is a subclass of LandICritter
 */

public class ICritterDog extends LandICritter{

	/**
	 * Calls LandICritter class constructor
	 * @param name Name of ICritter
	 * @param owner Owner of ICritter
	 */
	public ICritterDog(String name, Owner owner) {
		super(name, owner);
	}

}
