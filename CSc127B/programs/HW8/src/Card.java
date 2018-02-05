/*-------------------------------------------------------------------
 Class Card
 Chris Bohlman
 Inherits from: Comparable
 Package Contained In: none
 
 Purpose: creates a card, offers various methods to get info from card and compare the card and output the card as a string
 
 Instance Variables:
 int rank
 string suit
 
 Class Methods: n/a
 
 Instance Methods:
 getRank
 getSuit
 toString
 comparesTo

 -------------------------------------------------------------------*/
public class Card implements Comparable<Card> {

	//instance variables
	private int rank;
	private String suit;

	//Constructor: Card(int rank, String suit)
	//makes a card object with a certain rank and a certain suit
	public Card(int rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}

	//Instance method: getRank
	//returns a int representation of the current rank of card
	int getRank() {
		return rank;
	}

	//Instance method: getSuit
	//returns a string representation of the current suit of card with first letter capitalized
	String getSuit() {
		String new_suit = suit.substring(0, 1).toUpperCase() + suit.substring(1).toLowerCase();
		return new_suit;
	}

	//Instance method: toString
	//returns a string representation of the current deck
	public String toString() {
		String card;
		String rank_text = null;
		String suit_text = null;

		if (this.rank == 1) rank_text = "A";
		else if (this.rank == 2) rank_text = "2";
		else if (this.rank == 3) rank_text = "3";
		else if (this.rank == 4) rank_text = "4";
		else if (this.rank == 5) rank_text = "5";
		else if (this.rank == 6) rank_text = "6";
		else if (this.rank == 7) rank_text = "7";
		else if (this.rank == 8) rank_text = "8";
		else if (this.rank == 9) rank_text = "9";
		else if (this.rank == 10) rank_text = "T";
		else if (this.rank == 11) rank_text = "J";
		else if (this.rank == 12) rank_text = "Q";
		else if (this.rank == 13) rank_text = "K";
		else rank_text = "0";

		this.suit = this.suit.toLowerCase();

		if (this.suit.equals("clubs")) suit_text = "C";
		else if (this.suit.equals("diamonds")) suit_text = "D";
		else if (this.suit.equals("hearts")) suit_text = "H";
		else if (this.suit.equals("spades")) suit_text = "S";
		else suit_text = "";

		card = rank_text + "" + suit_text;
		return card;
	}

	//Instance method: compareTo
	//compares two cards and returns integers based off of said comparison
	public int compareTo(Card c) {
		String fsuit = this.getSuit().toLowerCase();
		String ssuit = c.getSuit().toLowerCase();

		int suit_int = 0;
		int c_suit_int = 0;

		if (fsuit.equals("clubs")) suit_int = 1;
		else if (fsuit.equals("diamonds")) suit_int = 2;
		else if (fsuit.equals("hearts")) suit_int = 3;
		else if (fsuit.equals("spades")) suit_int = 4;
		else suit_int = 0;

		if (ssuit.equals("clubs")) c_suit_int = 1;
		else if (ssuit.equals("diamonds")) c_suit_int = 2;
		else if (ssuit.equals("hearts")) c_suit_int = 3;
		else if (ssuit.equals("spades")) c_suit_int = 4;
		else suit_int = 0;
		if (suit_int == c_suit_int) {
			if (this.getRank() == c.getRank()) return 0;
			else if (this.getRank() > c.getRank()) return 1;
			else return -1;
		} 
		else if (suit_int > c_suit_int) return 1;
		else return -1;
	}
}
