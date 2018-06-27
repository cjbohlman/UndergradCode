package model;

import java.io.Serializable;

/**
 * @author Chris Bohlman
 * Abstract treat for ICritter, inheriting subclass must implement these
 */

public abstract class Treat implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	
	/**
	 * Sets string description
	 * @param Description of treat
	 */
	public Treat(String string) {
		this.description = string;
	}
	
	/**
	 * @return Treat description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Abstract getCost()
	 * @return cost of instantiated treat class
	 */
	public abstract int getCost();
}
