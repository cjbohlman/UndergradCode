package model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Chris Bohlman
 * @author Ilya Simkhovich
 * Creates a keyword collection of interests for an iCritter
 */

public class KeywordCollection implements Serializable {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static Map<String, Integer> keywordTable = new HashMap<String, Integer>();
  private long keywordBitmap;

	/**
	 * Instantiates HashSet of interest keywords in this structure
	 */
  public KeywordCollection() {
    keywordBitmap = 0;
  }

  /**
   * @return 
   */
  public long getKeywordBitmap() {
    return keywordBitmap;
  }

  /**
   * @param bitPos bit position you're looking at
   * @return true if keywordBitmap bit is set at this position, false otherwise
   */
  private boolean getBitSet(int bitPos) {
    if ((keywordBitmap & (1L << bitPos)) != 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if given keyword is a known keyword in the table
   * @param keyword String being looked up
   * @return true if keyword is in table, false otherwise
   */
  public static boolean isKnownKeyword(String theWord) {
    if (keywordTable.get(theWord.toLowerCase()) != null) {
      return true;
    } else {
	  return false;
    }
  }

  /**
   * @return set of ICritter interest keywords; could be an empty list.
   */
  public Set<String> listKeywords() {
    Set<String> list = new HashSet<String>();
    for (Entry<String, Integer> entry : keywordTable.entrySet()) {
   	  if (getBitSet(entry.getValue() - 1)) {
    	list.add(entry.getKey());
      }
	}
    return list;
  }

	/**
	 * Adds interest to interest keyword list. Repeats are not allowed.
	 * @param keyword The interest to add.
	 * @return true if list was changed, false otherwise.
	 */
  public boolean addKeyword(String keyword) {
	  
	  if (!(isKnownKeyword(keyword.toLowerCase()))) {	  
	  
      keywordTable.put(keyword.toLowerCase(), 64 - keywordTable.size());

      keywordBitmap = keywordBitmap | (1L << (64 - keywordTable.size()));
      return true;
		} else {
			int i = keywordTable.get(keyword.toLowerCase());
			keywordBitmap = keywordBitmap | (1L << i-1);
			return false;
		}
	}

	/**
	 * Removes interest from interest keyword list.
	 * @param keyword The interest to remove.
	 * @return true if list was changed, false otherwise.
	 */
	public boolean removeKeyword(String keyword) {
		// if keyword is in keywordTable  
		if (keywordTable.get(keyword.toLowerCase()) != null) {
			// remove the bit in keywordBitmap
			keywordBitmap = keywordBitmap & ~(1L << (64 - keywordTable.size()));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns correlation of KeywordCollection to other KeywordCollection
	 * @param other The KeywordCollection to check correlation with
	 * @return correlation of two KeywordCollections: |cardinality of intersection of two sets|/|cardinality of union of two sets|
	 * If comparing two empty words, throw NoInterestsException
	 */
	public double correlation(KeywordCollection other) {
		long intersection = keywordBitmap & other.getKeywordBitmap();
		long union  = keywordBitmap | other.getKeywordBitmap();
		double interBitsSet = 0.0, unionBitsSet = 0.0;
		
		for (int i =0; i<64; i++){
            if ( (intersection&1L) == 1) {
                interBitsSet++;
            }
            if ( (union&1L) == 1) {
                unionBitsSet++;
            }
            intersection = intersection >> 1;
			union = union >> 1;
        }
		if (other.getKeywordBitmap() == 0) {
			throw new NoInterestsException();
		}
		return interBitsSet/unionBitsSet;		
	}
}
