/*-------------------------------------------------------------------
 Class CS127BQueue
 Chris Bohlman
 Inherits from: None
 Implements CS127BQueueInterface
 Package Contained In: None
 
 Purpose: creates a bunch of queue methods for Program7 to work on boundary fill with a queue
 
 Instance Variables:
 Location[] queue
 
 Class Methods: n/a
 
 Instance Methods:
 enqueue
 dequeue
 isEmpty
 isFull
 peek
 size
 printAll
 contains

 -------------------------------------------------------------------*/
public class CS127BQueue implements CS127BQueueInterface {

	//instance variables
	public int front = 0;
	public int rear = 0;
	public Location[] queue = null;

	//constructor: creates a new queue Location array with no objects in it
	public CS127BQueue() {
		this.queue = new Location[0];
	}

	//enqueue method: adds an object to array by growing array and putting object at the end
	@Override
	public void enqueue(Location point) {
		Location[] temp = new Location[queue.length + 1];
		for (int i = 0; i < queue.length; i++) {
			temp[i] = queue[i];
		}
		temp[queue.length] = point;

		queue = new Location[temp.length];

		for (int i = 0; i < temp.length; i++) {
			queue[i] = temp[i];
		}
	}

	//dequeue method: takes object off of front of array
	@Override
	public void dequeue() {
		Location[] temp = new Location[queue.length - 1];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = queue[i + 1];
		}

		queue = new Location[temp.length];

		for (int i = 0; i < temp.length; i++) {
			queue[i] = temp[i];
		}
	}

	//isEmpty: checks to see if array is empty
	@Override
	public boolean isEmpty() {
		if (queue.length == 0) {
			return true;
		} else
			return false;
	}

	//isFull: checks to see if array is filled
	@Override
	public boolean isFull() {
		boolean full = true;
		for (int i = 0; i < queue.length; i++) {
			if (queue[i] == null)
				full = false;
		}
		return full;
	}

	//peek method: returns object at front of array
	@Override
	public Location peek() {
		return queue[0];
	}

	//size method: returns size of array
	public int size() {
		return queue.length;
	}

	//printAll method: prints every single object in array
	public String printAll() {
		if (queue.length == 0) {
			return "<empty>";
		} else {
			String str = "";
			for (int i = 0; i < queue.length; i++) {
				String q = "";
				if (i == queue.length - 1) {
					q = "<" + queue[i].toString() + ">";
				} else
					q = "<" + queue[i].toString() + ">,";
				str = str + q;
			}
			return str;
		}
	}
	
	//contains method: checks to see if queue array has a certain object loc
	public boolean contains(Location loc) {
		boolean same = false;
		for (int i = 0; i < queue.length; i++) {
			if (loc.getRow() == queue[i].getRow() && loc.getColumn() == queue[i].getColumn()) {
				same = true;
			}
		}
		return same;
	}
}
