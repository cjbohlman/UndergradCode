package model;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * @author Chris Bohlman
 * @author Ilya Simkhovich
 * Owner of ICritter, can call several methods to interact with iCritter
 */

public class Owner extends Observable implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ICritter iCritter;
	private List<Treat> treats = new LinkedList<Treat> ();
	private int credits;
	private String ownerName;
	
	/**
	 * Creates an iCritter with name of "Mark" and initializes credit count to 10
	 */
	public Owner(String ownerName, String iCritterName) {
		this.iCritter = new ICritterPenguin(iCritterName, this);
		this.credits = 30;
		this.ownerName = ownerName;
	}
	
	/**
	 * If the owner owns the treat, give treat to iCritter and removes it from list; If treat is not in list, return null
	 * @return ICritterReaction to the treat
	 */
	public ICritterReaction giveTreat(Treat treat) {
		int i = 0;
		while (treats.get(i) != treat) {
			i++;
		}
//		if (i == treats.size()) {
//			return null;
//		}
		ICritterReaction icr = iCritter.recieveTreat(treat);
		treats.remove(i);
		setChanged();
		notifyObservers(this);
		return icr;		
	}
	
	/**
	 * If owner has enough credits, buy a cheap treat and put it in list of treats; If owner does not have enough credits, return null
	 * @return CheapTreat bought
	 */
  public CheapTreat buyCheapTreat(String string) {	
    CheapTreat treat = new CheapTreat(string);
    addTreat(treat);
    adjustCredits(-treat.getCost());
	return treat;
  }
	
	/**
	 * If owner has enough credits, buy a fancy treat and put it in list of treats; If owner does not have enough credits, return null
	 * @return FancyTreat bought
	 */
  public FancyTreat buyFancyTreat(String string) {
    FancyTreat treat = new FancyTreat(string);
    addTreat(treat);
    adjustCredits(-treat.getCost());
    return treat;
  } 
	
	/**
	 * @return list of treats; could be an empty list
	 */
  public List<Treat> listTreats() {
    return treats;
  }
	
	/**
	 * @return ICritter belonging to Owner
	 */
	public ICritter getCritter() {
		return iCritter;
	}
	
	/**
	 * @return name belonging to Owner
	 */
	public String getName() {
		return ownerName;
	}
	
	/**
	 * @return number of credits Owner currently has
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * adjust owner's credit amount by amount passed
	 * @param num Number to increase/decrease credits by
	 * if credits are insufficient to purchase a treat, throw NotEnoughCredits exception
	 */
	private void adjustCredits(int num) {

      if (credits + num < 0) {
        throw new NotEnoughCreditsException(num, credits);			
      }

      credits = credits + num;
      setChanged();
      notifyObservers(this);
	}
	
	/**
	 * adds Treat to owner's list of treats
	 * @param treat Treat to add
	 */
	private boolean addTreat(Treat treat) {
	  boolean bool = treats.add(treat);
	  setChanged();
	  notifyObservers(this);
	  return bool;

	}
}
