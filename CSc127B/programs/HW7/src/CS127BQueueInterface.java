/*-------------------------------------------------------------------
 Interface CS127BQueueInterface
 Chris Bohlman
 Inherits from: None
 Package Contained In: None
 
 Purpose: creates an interface to base the CS127BQueue on
 
 Instance Variables: none
 
 Class Methods: n/a
 
 Instance Methods:
 enqueue
 dequeue
 isFull
 isEmpty
 peek
 -------------------------------------------------------------------*/
public interface CS127BQueueInterface {
	public void enqueue(Location point);
	public void dequeue();
	public boolean isEmpty();
	public boolean isFull();
	public Location peek();
}
