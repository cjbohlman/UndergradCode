package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

/**
 * @author Chris Bohlman
 * @author Ilya Simkhovich
 * Creates a pet object, along with various methods to interact with the pet
 */

public abstract class ICritter extends Observable implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private List<ICritterMemoryEvent> memories;
	private Owner owner;
	private List<ICritter> friends;
	private KeywordCollection interests;
	
	/**
	 * Sets iCritter's name and owner, initializes list of memories, list of friends, and KeywordColelction of interests
	 * @param name Name of ICritter
	 * @param owner Owner of ICritter
	 */
	public ICritter(String name, Owner owner) {
		this.name = name;
		this.owner = owner;
		memories = new LinkedList<ICritterMemoryEvent> ();
		friends = new LinkedList<ICritter> ();
		interests = new KeywordCollection();
	}
	
	/**
	 * ICritter consumes treat and adds reaction to memory bin
	 * @param treat Treat for ICritter
	 * @return ICritterReaction to the treat
	 */
	public ICritterReaction recieveTreat(Treat treat) {
		ICritterReaction icr = consumeTreat(treat);
		addMemoryEvent(treat, icr);
		return icr;
	}
	
	/**
	 * @return list of memories, could be an empty list
	 */
	public List<ICritterMemoryEvent> getMemories() {
		return memories;
	}
	
	/**
	 * @return owner of ICritter
	 */
	public Owner getOwner() {
		return owner;
	}
	
	/**
	 * @return name of ICritter
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Adds treat and reaction to memory bank
	 * @param treat Treat related to memory event
	 * @param reaction ICritterReaction related to memory event
	 */
	public void addMemoryEvent(Treat treat, ICritterReaction reaction) {
		ICritterMemoryEvent icme = new ICritterMemoryEvent(treat, reaction);
		memories.add(icme);
		setChanged();
		notifyObservers("addMemoryEvent()");
	}
	
	/**
	 * Consumes treat and gets reaction of treat
	 * @param treat Treat for ICritter
	 * @return ICritterReaction to treat
	 */
	public ICritterReaction consumeTreat(Treat treat) {
			ICritterReaction icr = new ICritterReaction(treat.getCost());
			return icr;
	}
	
	/**
	 * @return list of ICritter friends; could be an empty list.
	 */
	public List<ICritter> getFriends() {
		return friends;
	}
	
	/**
	 * Adds friend to friend list. Repeats are not allowed.
	 * @param toAdd The ICritter to add.
	 * @return true if list was changed, false otherwise.
	 */
	public boolean addFriend(ICritter toAdd) {
		if (friends.contains(toAdd)) {
			return false;
		} else {
			boolean bool = friends.add(toAdd);
			setChanged();
			notifyObservers("addFriend()");
			return bool;
		}
	}
	
	/**
	 * Removes friend from friend list.
	 * @param toRemove The ICritter to remove.
	 * @return true if list was changed, false otherwise.
	 */
	public boolean removeFriend(ICritter toRemove) {
		boolean bool = friends.remove(toRemove);
		if (bool) {
			setChanged();
			notifyObservers("removeInterest()");
			return bool;
		} else {
			return bool;
		}		
	}
	
	/**
	 * @return set of ICritter interests; could be an empty set.
	 */
	public Set<String> listInterests() {
		return interests.listKeywords();
	}
	
	public long getKeyMap() {
      return interests.getKeywordBitmap();
	}
	
	/**
	 * Facade: Adds interest to ICritter's KeywordCollection
	 * @param keyword The interest to add.
	 * @return true if keyword was successfully added, false otherwise
	 */
	public boolean addInterest(String keyword) {
		boolean bool = interests.addKeyword(keyword);
		if (bool) {
			setChanged();
			notifyObservers("addInterest()");
			return bool;
		}
		else {
			return bool;
		}
	}
	
	/**
	 * Facade: Checks interest to ICritter's KeywordCollection
	 * @param keyword The interest to check.
	 * @return true if keyword was in ICritter, false otherwise
	 */
	public boolean containsInterest(String keyword) {
		return interests.listKeywords().contains(keyword);
	}
	
	/**
	 * Facade: Removes interest from ICritter's KeywordCollection
	 * @param keyword The interest to remove.
	 * @return true if keyword was successfully removed, false otherwise
	 */
	public boolean removeInterest( String keyword) {
		boolean bool = interests.removeKeyword(keyword);
		if (bool) {
			setChanged();
			notifyObservers("removeInterest()");
			return bool;
		}
		else {
			return bool;
		}
	}
	
	/**
	 * Facade: Returns correlation of ICritter's KeywordCollection to other's KeywordCollection
	 * @param other The ICritter to check correlation with
	 * @return correlation of two KeywordCollections
	 */
	public double interestCorrelation(ICritter other) {
		return interests.correlation(other.interests);
	}
	
	/**
	 * Abstract: Makes ICritter and other ICritter interact
	 * @param other The ICritter to interact with
	 */
	public abstract void interact(ICritter other);
	
	/**
	 * Calculates happiness of iCritter based off of treats eaten thus far
	 * If more than 8 memories, only look at last 8
	 * @return double that is the calculated happiness of the iCritter
	 */
	public double getHappiness() {
		double happiness = -4.0;
		int size = memories.size();
		if (size <= 8) {
			for (ICritterMemoryEvent icme: memories) {
				if (icme.getRememberedTreat() instanceof FancyTreat) {
					happiness = happiness + 1;
				}
			}
		} else {
			for (int i = size - 1 ; i >= size - 8; i--) {
				ICritterMemoryEvent icme = memories.get(i);
				if (icme.getRememberedTreat() instanceof FancyTreat) {
					happiness = happiness + 1;
				}
			}
		}
		return happiness;
	}
	
	
	
}
