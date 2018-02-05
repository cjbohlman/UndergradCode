/*********************
 * HwProj03_AND simulates an XOR gate with (A AND NOT B) OR (B AND NOT A)
 * 
 * @author Chris Bohlman
 *
 */
public class HwProj03_XOR {
	public void execute()
	{
		not1.in.set(b.get());				//set up both not gates and execute
		not2.in.set(a.get());
		not1.execute();
		not2.execute();
		
		and1.a.set(a.get());				//set up both and gates and execute
		and1.b.set(not1.out.get());
		and2.a.set(b.get());
		and2.b.set(not2.out.get());
		and1.execute();
		and2.execute();
		
		or.a.set(and1.out.get());			//set up or gate and execute for final result
		or.b.set(and2.out.get());
		or.execute();
		
		out.set(or.out.get());
	}


	// inputs
	public RussWire a,b;
	// outputs
	public RussWire out;
	
	public HwProj03_AND and1, and2;
	public HwProj03_OR or;
	public HwProj03_NOT not1, not2;


	public HwProj03_XOR()
	{
		// the constructor for an object has to create all of the
		// RussWire objects to represent the inputs and outputs
		// of the object.
		a  = new RussWire();
		b  = new RussWire();
		out = new RussWire();
		and1 = new HwProj03_AND();
		and2 = new HwProj03_AND();
		not1 = new HwProj03_NOT();
		not2 = new HwProj03_NOT();
		or = new HwProj03_OR();
	}
}
