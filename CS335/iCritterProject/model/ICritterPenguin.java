package model;

/**
 * @author Chris Bohlman
 * Penguin ICritter, which is a subclass of MarineICritter
 */

public class ICritterPenguin extends MarineICritter{

	/**
	 * Calls MarineICritter class constructor
	 * @param name Name of ICritter
	 * @param owner Owner of ICritter
	 */
	public ICritterPenguin(String name, Owner owner) {
		super(name, owner);
	}

}
