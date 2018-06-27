package model;

/**
 * @author Chris Bohlman
 * Extends Treat class for a Fancy Treat
 */

public class FancyTreat extends Treat{
	
	/**
	 * Calls Treat class constructor
	 * @param string Description of Fancy Treat
	 */
	public FancyTreat(String string) {
		super(string);
	}

	/**
	 * @return Cost of Fancy Treat: 5 
	 */
	public int getCost() {
		return 5;
	}
}
