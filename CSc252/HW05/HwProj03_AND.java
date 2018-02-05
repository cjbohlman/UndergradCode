/*********************
 * HwProj03_AND simulates an AND gate
 * 
 * @author Chris Bohlman
 *
 */
public class HwProj03_AND {
	public void execute()
	{
		out.set(a.get() && b.get());		//sets output value from input values
	}

	// inputs
	public RussWire a, b;
	// outputs
	public RussWire out;


	public HwProj03_AND()
	{
		// the constructor for an object has to create all of the
		// RussWire objects to represent the inputs and outputs
		// of the object.
		a  = new RussWire();
		b  = new RussWire();
		out = new RussWire();
	}
}
