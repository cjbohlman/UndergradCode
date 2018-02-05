/*-------------------------------------------------------------------
 Class ArrayList
 Chris Bohlman
 Inherits from: None
 Package Contained In: java.util.ArrayList
 
 Purpose: creates an arraylist object that has the same methods as a stack would
 
 Instance Variables: ArrayList<Location>
 
 Class Methods: n/a
 
 Instance Methods:
 topPeek
 itemPush
 ItemPop
 size
 isEmpty
 -------------------------------------------------------------------*/
import java.util.ArrayList;

public class ArrayStack {
	//instance variables
	private ArrayList<Location> list = null;
	
	//constructor: initializes the ArrayList
	public ArrayStack() {
		this.list = new ArrayList<Location>();
	}

	//instance method topPeek:
	//returns a reference to the object and the top of the list, similar to peek method of stacks
	Location topPeek() {
		return list.get(list.size() - 1);
	}

	//instance method itemPush:
	//inserts a new obkect at top of list, similar to push method for stacks
	void itemPush(Location l) {
		list.add(l);
	}

	//instance method itemPop:
	//removes the item at the top of the list, similar to pop method of stacks
	void itemPop() {
		list.remove(list.size() - 1);
	}

	//instance method size:
	//returns how many objects are in the stack
	int size() {
		return list.size();
	}

	//instance method isEmpty:
	//if no objects are in the list, returns true. else, returns false
	boolean isEmpty() {
		int size = list.size();
		if (size != 0) {
			return false;
		} else
			return true;
	}
}
