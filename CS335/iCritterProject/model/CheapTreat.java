package model;

/**
 * @author Chris Bohlman
 * Extends Treat class for a Cheap Treat
 */

public class CheapTreat extends Treat {
	
	/**
	 * Calls Treat class constructor
	 * @param string Description of Cheap Treat
	 */
	public CheapTreat(String string) {
		super(string);
	}

	/**
	 * @return Cost of Cheap Treat: 1 
	 */
	public int getCost() {
		return 1;
	}
}
