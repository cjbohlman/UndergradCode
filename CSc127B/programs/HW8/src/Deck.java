/*-------------------------------------------------------------------
 Class Deck
 Chris Bohlman
 Inherits from: None
 Package Contained In: java.io.*, java.util.Scanner
 
 Purpose: creates, shuffles and outputs a deck
 
 Instance Variables:
 Node head, int occupancy
 
 Class Methods: n/a
 
 Instance Methods:
 outShuffle
 inShuffle
 isEmpty
 size
 toString

 -------------------------------------------------------------------*/

import java.io.*;
import java.util.Scanner;

public class Deck {

 //instance variables
 private Node head =new Node(null);
 private int occupancy;
 private Node walker;

 //Constructor: Deck(String infile)
 //takes input from a supplied file, reads every line and makes a deck based off of that
 public Deck(String infile) {
  Node walker = null;
  occupancy = 0;
  Scanner sc = null;
  try {
   sc = new Scanner(new FileReader(infile));
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  }
  String new_line;
  while (sc.hasNextLine() && occupancy < 104) {
   new_line = sc.nextLine();
   String line = new_line.replaceAll("\\W", "");
   int split_num = 0;
   for (int i = 0; i < line.length(); i++) {
    if (Character.isLetter(line.charAt(i))) {
     split_num = i;
     break;
    }
   }
   int card_rank = Integer.parseInt(line.substring(0, split_num));
   String card_suit = line.substring(split_num).toLowerCase();

   boolean spades = card_suit.equals("spades");
   boolean clubs = card_suit.equals("clubs");
   boolean hearts = card_suit.equals("hearts");
   boolean diamonds = card_suit.equals("diamonds");
   boolean valid_rank = card_rank > 0 && card_rank < 14;

   if ((spades || clubs || hearts || diamonds) && valid_rank) {
    walker = head;
    while (walker.getNext() != null) {
     walker = walker.getNext();
    }
    Card card = new Card(card_rank, card_suit);
    Node newc = new Node(card);
    walker.setNext(newc);
    walker.getNext().setNext(null);
    occupancy++;
   } else {
    continue;
   }
  }
  if (occupancy % 2 != 0) {
   walker = head;
   while (walker.getNext().getNext() != null) {
    walker = walker.getNext().getNext();
   }
   walker.setNext(null);
  }
 }

 //Constructor: Deck(Stringbuilder)
 //takes stringbuilder input from a supplied file, reads every line and makes a sorted deck based off of that
 public Deck(StringBuilder deck) {
  Node walker = null;
  occupancy = 0;
  Scanner sc = null;
  try {
   sc = new Scanner(new FileReader(deck.toString()));
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  }
  String new_line;
  while (sc.hasNextLine() && occupancy < 104) {
   new_line = sc.nextLine();
   String line = new_line.replaceAll("\\W", "");
   int split_num = 0;
   for (int i = 0; i < line.length(); i++) {
    if (Character.isLetter(line.charAt(i))) {
     split_num = i;
     break;
    }
   }
   int card_rank = Integer.parseInt(line.substring(0, split_num));
   String card_suit = line.substring(split_num).toLowerCase();

   boolean spades = card_suit.equals("spades");
   boolean clubs = card_suit.equals("clubs");
   boolean hearts = card_suit.equals("hearts");
   boolean diamonds = card_suit.equals("diamonds");
   boolean valid_rank = card_rank > 0 && card_rank < 14;

   if ((spades || clubs || hearts || diamonds) && valid_rank) {
    walker = head;
    Card card = new Card(card_rank, card_suit);
    Node newc = new Node(card);
    
    if(this.size() == 0) {
     walker.setNext(newc);
     walker.getNext().setNext(null);
    }
    else if(this.size() == 1) {
     if (walker.getNext().getData().compareTo(card) < 0) {
      Card temp = new Card(walker.getNext().getData().getRank(),walker.getNext().getData().getSuit());
      Node tempn = new Node(temp);
      newc.setNext(tempn);
      walker.getNext().setNext(newc);
      walker.getNext().getNext().setNext(null);
     }
     else if(walker.getNext().getData().compareTo(card) > 0) {
      Card temp = new Card(walker.getNext().getData().getRank(),walker.getNext().getData().getSuit());
      Node tempn = new Node(temp);
      newc.setNext(tempn);
      walker.setNext(newc);
      walker.getNext().getNext().setNext(null);
     }
    }
    else {
     while (walker.getNext() != null) {
      if (walker.getNext().getData().compareTo(card) == 1) {
       break;
      }
      else walker = walker.getNext();
     }
     if (!(walker.getNext() != null)) {
      walker.setNext(newc);
      walker.getNext().setNext(null);
     }
     else {
      Card temp = new Card(walker.getNext().getData().getRank(),walker.getNext().getData().getSuit());
      Node tempn = new Node(temp);      
      tempn.setNext(walker.getNext().getNext());
      newc.setNext(tempn);
      walker.setNext(newc);
     }
    }
    occupancy++;
   } 
   else {
    continue;
   }
  }
  if (occupancy % 2 != 0) {
   walker = head;
   while (walker.getNext().getNext() != null) {
    walker = walker.getNext().getNext();
   }
   walker.setNext(null);
  }
 }
 
 //Constructor: Deck()
 //makes a new deck object, ONLY FOR USE WITH SHUFFLE METHODS
 public Deck() {
  walker = head;
  occupancy = 0;
 }

 //Instance method: outShuffle
 //modifies existing deck object by performing a faro out shuffle on the deck
 public void outShuffle() {
  Deck back = new Deck();
  int half = occupancy/2;
  Node walker2 = head;
  for (int i = 0; i < half; i++) {
   walker2 = walker2.getNext();
  }
  for(int i = 0; i < half; i++) {
   Node temp = new Node(walker2.getNext().getData());
   back.walker.setNext(temp);
   walker2 = walker2.getNext();
   back.walker = back.walker.getNext();
  }
  back.walker = back.head;
  back.walker = back.walker.getNext();
  walker2 = head;
  walker2 = walker2.getNext();
  for(int i = 0; i < half-1; i++) {
   Card temp = new Card(walker2.getNext().getData().getRank(),walker2.getNext().getData().getSuit());
   Node tempn = new Node(temp);
   tempn.setNext(walker2.getNext().getNext());
   Card tempb = new Card(back.walker.getData().getRank(),back.walker.getData().getSuit());
   Node tempbn = new Node(tempb);
   
   walker2.setNext(tempbn);
   walker2.getNext().setNext(tempn);
   back.walker = back.walker.getNext();
   walker2 = walker2.getNext().getNext();   
  }
  walker2.setNext(back.walker);
  walker2.getNext().setNext(null);
 }

 //Instance method: inShuffle
 //modifies existing deck object by performing a faro in shuffle on the deck
 void inShuffle() {
  Deck back = new Deck();
  int half = occupancy/2;
  Node walker2 = head;
  for (int i = 0; i < half; i++) {
   walker2 = walker2.getNext();
  }
  for(int i = 0; i < half; i++) {
   Node temp = new Node(walker2.getNext().getData());
   back.walker.setNext(temp);
   walker2 = walker2.getNext();
   back.walker = back.walker.getNext();
  }
  back.walker = back.head;
  back.walker = back.walker.getNext();
  walker2 = head;
  for(int i = 0; i < half; i++) {
   Card temp = new Card(walker2.getNext().getData().getRank(),walker2.getNext().getData().getSuit());
   Node tempn = new Node(temp);
   tempn.setNext(walker2.getNext().getNext());
   Card tempb = new Card(back.walker.getData().getRank(),back.walker.getData().getSuit());
   Node tempbn = new Node(tempb);
   
   walker2.setNext(tempbn);
   walker2.getNext().setNext(tempn);
   back.walker = back.walker.getNext();
   walker2 = walker2.getNext().getNext();   
  }
  walker2.setNext(null);

 }

 //Instance method: isEmpty
 //returns boolean true if deck is empty, false otherwise
 public boolean isEmpty() {
  if (head == null) {
   return true;
  }
  return head.getNext() == null;

 }

 //Instance method: size
 //returns an int detailing how many items are in the deck
 public int size() {
  return occupancy;
 }

 //Instance method: toString
 //returns a string representation of the current deck
 public String toString() {
  Node walker2 = head;
  String deck = "";
  while (walker2.getNext() != null) {
   deck = deck + walker2.getNext().getData().toString() + " ";
   walker2 = walker2.getNext();
  }
  int deck_length = deck.length();
  StringBuilder deck_string = new StringBuilder(deck);
  deck_string = deck_string.deleteCharAt(deck_length-1);
  deck = deck_string.toString();
  return deck;
 }
}