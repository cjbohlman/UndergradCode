package model;

import java.io.Serializable;

/**
 * @author Chris Bohlman
 * ICritter reaction which stores a mood modifier for an event
 */

public class ICritterReaction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int moodModifier;
	
	/**
	 * Sets mood modifier to what is passed, ensuring reaction depends on passed value
	 * @param num The mood modifier
	 */
	public ICritterReaction(int num) {
		moodModifier = num;
	}
	
	/**
	 * @return Mood modifier for reaction
	 */
	public int getMoodModifier() {
		return moodModifier;
	}
}
