/*********************
 * HwProj03_FullAdder simulates a full adder gate from two half adders and an OR gate
 * 
 * @author Chris Bohlman
 *
 */
public class HwProj03_FullAdder {
	public void execute()
	{
		ha1.a.set(a.get());              //set up first half adder and execute
		ha1.b.set(b.get());
		ha1.execute();
		
		ha2.a.set(carryIn.get());        //set up second half adder and execute
		ha2.b.set(ha1.sum.get());
		ha2.execute();
		sum.set(ha2.sum.get());
		
		or.a.set(ha2.carry.get());       //set up OR gate and execute
		or.b.set(ha1.carry.get());
		or.execute();
		carryOut.set(or.out.get());
	}


	// inputs
	public RussWire a,b,carryIn;
	// outputs
	public RussWire sum,carryOut;
	//helpers
	public HwProj03_HalfAdder ha1, ha2;
	public HwProj03_OR or;
	


	public HwProj03_FullAdder()
	{
		// the constructor for an object has to create all of the
		// RussWire objects to represent the inputs and outputs
		// of the object.
		a  = new RussWire();
		b  = new RussWire();
		carryIn  = new RussWire();
		sum = new RussWire();
		carryOut  = new RussWire();
		ha1 = new HwProj03_HalfAdder();
		ha2 = new HwProj03_HalfAdder();
		or = new HwProj03_OR();
	}
}
