package model;

/**
 * @author Chris Bohlman
 * Cat ICritter, which is a subclass of LandICritter
 */

public class ICritterCat extends LandICritter{

	/**
	 * Calls LandICritter class constructor
	 * @param name Name of ICritter
	 * @param owner Owner of ICritter
	 */
	public ICritterCat(String name, Owner owner) {
		super(name, owner);
	}

}
