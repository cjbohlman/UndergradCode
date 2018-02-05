/*********************
 * HwProj03_ALU simulates an ALU
 * 
 * @author Chris Bohlman
 *
 */
public class HwProj03_ALU {
	public void execute()
	{
		//set all possible elements
		elem_arr[0].carryIn.set(bNegate.get());
		for (int i = 0; i < 32; i++) {
			elem_arr[i].a.set(a[i].get());
			elem_arr[i].b.set(b[i].get());
			elem_arr[i].bInvert.set(bNegate.get());
			elem_arr[i].aluOp[0].set(aluOp[0].get());
			elem_arr[i].aluOp[1].set(aluOp[1].get());
			
		}
		//set carry in of first and then do the same for the rest of the elements, as well as executing first pass
		elem_arr[0].execute_pass1();
		for (int i = 1; i < 32; i++) {
			boolean nextCarry = elem_arr[i-1].carryOut.get();
			elem_arr[i].carryIn.set(nextCarry);
			elem_arr[i].execute_pass1();
		}
		//set less for the first element (MSB of result into LSB)
		elem_arr[0].less.set(elem_arr[31].addResult.get());
		//Set less for all else
		for (int i = 1; i < 32; i++) {
			elem_arr[i].less.set(false);
		}
		//execute second pass of elements
		for (int i = 0; i < 32; i++) {
			elem_arr[i].execute_pass2();
		}
		//set result from all elements
		for (int i = 0; i < 32; i++) {
			result[i].set(elem_arr[i].result.get());
		}
	}
	// inputs
	public RussWire bNegate;
	public RussWire aluOp[];
	public RussWire a[];
	public RussWire b[];
	//output
	public RussWire result[];
	//helper
	public HwProj03_ALUElement elem_arr[];
	
	public HwProj03_ALU()
	{
		// the constructor for an object has to create all of the
		// RussWire objects to represent the inputs and outputs
		// of the object.
		aluOp  = new RussWire[2];
		for (int i = 0; i < 2; i++) {
			aluOp[i] = new RussWire();
		}
		a  = new RussWire[32];
		for (int i = 0; i < 32; i++) {
			a[i] = new RussWire();
		}
		b  = new RussWire[32];
		for (int i = 0; i < 32; i++) {
			b[i] = new RussWire();
		}
		result  = new RussWire[32];
		for (int i = 0; i < 32; i++) {
			result[i] = new RussWire();
		}
		elem_arr  = new HwProj03_ALUElement[32];
		for (int i = 0; i < 32; i++) {
			elem_arr[i] = new HwProj03_ALUElement();
		}
		bNegate = new RussWire();
	}
}
