/*********************
 * HwProj03_NOT simulates a NOT gate
 * 
 * @author Chris Bohlman
 *
 */
public class HwProj03_NOT
{
	public void execute()
	{
		out.set(!in.get());  			//set output of note gate from input
	}


	// inputs
	public RussWire in;
	// outputs
	public RussWire out;


	public HwProj03_NOT()
	{
		// the constructor for an object has to create all of the
		// RussWire objects to represent the inputs and outputs
		// of the object.
		in  = new RussWire();
		out = new RussWire();
	}
}

