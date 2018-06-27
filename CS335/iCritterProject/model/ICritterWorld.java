package model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Chris Bohlman
 * Structure for all added ICritters to interact with each other in
 */

public class ICritterWorld {
	
	private List<ICritter> ICritters;
	
	/**
	* Instantiates LinkedList of ICritters in this structure
	*/
	public ICritterWorld() {
		ICritters = new LinkedList<ICritter>();
	}
	
	/**
	* @return LinkedList of ICritters in structure
	*/
	public List<ICritter> listICritters() {
		return ICritters;
	}
	
	/**
	* Adds ICritter to structure
	* @param toAdd ICritter to add
	* @return true if list of ICritters was changed, false otherwise
	*/
	public boolean addICritter(ICritter toAdd) {
		if(ICritters.contains(toAdd)) {
			return false;
		}
		return ICritters.add(toAdd);
	}
	
	/**
	* Removes ICritter from structure
	* @param toRemove ICritter to remove
	* @return true if list of ICritters was changed, false otherwise
	*/
	public boolean removeICritter(ICritter toRemove) {
		return ICritters.remove(toRemove);
	}
	
	/**
	* Makes every ICritter interact with every other ICritter in the structure
	*/
	public void runJamboree() {
		for(ICritter ic1: ICritters) {
			for(ICritter ic2: ICritters) {
				if (!ic1.equals(ic2)) {
					ic1.interact(ic2);
				}
			}
		}
	}
}
