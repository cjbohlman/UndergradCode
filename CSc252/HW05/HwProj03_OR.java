/*********************
 * HwProj03_OR simulates an OR gate
 * 
 * @author Chris Bohlman
 *
 */
public class HwProj03_OR {
	public void execute()
	{
		out.set(a.get() || b.get());				//set output of or gate using inputs
	}


	// inputs
	public RussWire a, b;
	// outputs
	public RussWire out;


	public HwProj03_OR()
	{
		// the constructor for an object has to create all of the
		// RussWire objects to represent the inputs and outputs
		// of the object.
		a  = new RussWire();
		b  = new RussWire();
		out = new RussWire();
	}
}
