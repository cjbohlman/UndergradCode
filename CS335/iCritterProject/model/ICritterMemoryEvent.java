package model;

import java.io.Serializable;

/**
 * @author Chris Bohlman
 * Memory event object for an ICritter
 */

public class ICritterMemoryEvent implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Treat rememberedTreat;
	private ICritterReaction rememberedReaction;
	
	/**
	 * Sets reaction to a treat in a memory event
	 * @param treat Treat in memory
	 * @param reaction Reaction to treat in memory
	 */
	public ICritterMemoryEvent (Treat treat, ICritterReaction reaction) {
		rememberedTreat = treat;
		rememberedReaction = reaction;
	}
	
	/**
	 * @return Treat that is remembered
	 */
	public Treat getRememberedTreat() {
		return rememberedTreat;
	}
	
	/**
	 * @return ICritterReaction that is remembered
	 */
	public ICritterReaction getRememberedReaction() {
		return rememberedReaction;
	}
}
