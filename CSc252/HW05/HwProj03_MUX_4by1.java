/*********************
 * HwProj03_MUX_4by1 simulates a multiplexer in an ALU
 * 
 * @author Chris Bohlman
 *
 */
public class HwProj03_MUX_4by1 {
	public void execute()
	{
		// 00: and
		if (!control[0].get() && !control[1].get()) {
			out.set(in[0].get());
		}
		//01: or
		else if (control[0].get() && !control[1].get()) {
			out.set(in[1].get());
		}
		//10: add
		else if (!control[0].get() && control[1].get()) {
			out.set(in[2].get());
		}
		//11: less
		if (control[0].get() && control[1].get()) {
			out.set(in[3].get());
		}
	}

	
	// inputs
	public RussWire control[];
	public RussWire in[];
	// outputs
	public RussWire out;

	
	public HwProj03_MUX_4by1()
	{
		// the constructor for an object has to create all of the
		// RussWire objects to represent the inputs and outputs
		// of the object.
		control  = new RussWire[2];
		for (int i = 0; i < 2; i++) {
			control[i] = new RussWire();
		}
		in  = new RussWire[4];
		for (int i = 0; i < 4; i++) {
			in[i] = new RussWire();
		}
		out = new RussWire();
	}
}
