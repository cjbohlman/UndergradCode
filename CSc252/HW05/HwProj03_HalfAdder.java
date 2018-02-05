/*********************
 * HwProj03_AND simulates a half adder gate from an XOR gate and an and gate
 * 
 * @author Chris Bohlman
 *
 */
public class HwProj03_HalfAdder {
	public void execute()
	{
		xor.a.set(a.get());                //set up XOR gate and execute
		xor.b.set(b.get());
		xor.execute();
		sum.set(xor.out.get());
		
		and.a.set(a.get());					//set up AND gate and execute
		and.b.set(b.get());
		and.execute();
		carry.set(and.out.get());
	}


	// inputs
	public RussWire a,b;
	// outputs
	public RussWire sum, carry;
	public HwProj03_XOR xor;
	public HwProj03_AND and;

	public HwProj03_HalfAdder()
	{
		// the constructor for an object has to create all of the
		// RussWire objects to represent the inputs and outputs
		// of the object.
		a  = new RussWire();
		b  = new RussWire();
		sum = new RussWire();
		carry = new RussWire();
		xor = new HwProj03_XOR();
		and = new HwProj03_AND();
	}
}
