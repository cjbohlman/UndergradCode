
public class Program8 {
	public static void main(String[] args) {
		Deck deck = new Deck("standardDeck.dat");
		deck.outShuffle();
		deck.outShuffle();
		deck.outShuffle();
		deck.outShuffle();
		deck.outShuffle();
		deck.outShuffle();
		deck.outShuffle();
		deck.outShuffle();
		System.out.println(deck.toString());
		
		StringBuilder str = new StringBuilder("standardDeck.dat");
		Deck deck5 = new Deck(str);
		deck5.inShuffle();
		deck5.inShuffle();
		deck5.inShuffle();
		deck5.inShuffle();
		deck5.inShuffle();
		deck5.inShuffle();
		deck5.inShuffle();
		deck5.inShuffle();
		System.out.println(deck5.toString());
	}

}
