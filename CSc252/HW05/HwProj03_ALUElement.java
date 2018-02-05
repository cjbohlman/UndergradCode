/*********************
 * HwProj03_ALUElement simulates an element in an ALU
 * 
 * @author Chris Bohlman
 *
 */
public class HwProj03_ALUElement {
	public void execute_pass1() {
		//set all inputs EXCEPT FOR LESS
		//run adder (set binvert)
		//set addresult and carryout
		
		//AND CALC
		and1.a.set(a.get());
		and1.b.set(b.get());
		and1.execute(); //set this result to result in pass2
		
		//OR CALC
		or1.a.set(a.get());
		or1.b.set(b.get());
		or1.execute(); //set this result to result in pass2
		
		//ADD CALC: use 2 bit mux to set adder to add or sub
		add_mux.in[0].set(b.get());
		add_mux.in[1].set(!b.get());
		add_mux.in[2].set(false);
		add_mux.in[3].set(false);
		add_mux.control[1].set(false);
		add_mux.control[0].set(bInvert.get());
		add_mux.execute();
		
		adder.a.set(a.get());
		adder.b.set(add_mux.out.get());
		adder.carryIn.set(carryIn.get());
		adder.execute();
		addResult.set(adder.sum.get());
		carryOut.set(adder.carryOut.get());
		
	}
	public void execute_pass2() {
		//all inputs will be valid (less) so set all of them
		//generate result output from mux
		end_mux.in[0].set(and1.out.get());
		end_mux.in[1].set(or1.out.get());
		end_mux.in[2].set(addResult.get());
		end_mux.in[3].set(less.get());
		end_mux.control[0].set(aluOp[0].get());
		end_mux.control[1].set(aluOp[1].get());
		end_mux.execute();
		result.set(end_mux.out.get());
	}
	// inputs
	public RussWire bInvert, a, b, carryIn, less;
	public RussWire aluOp[];
	//outputs
	public RussWire result, addResult, carryOut;
	//helpers
	public HwProj03_AND and1;
	public HwProj03_OR or1;
	public HwProj03_NOT not1;
	public HwProj03_MUX_4by1 add_mux;
	public HwProj03_FullAdder adder;
	public HwProj03_MUX_4by1 end_mux;
	
	public HwProj03_ALUElement()
	{
		// the constructor for an object has to create all of the
		// RussWire objects to represent the inputs and outputs
		// of the object.
		aluOp  = new RussWire[2];
		for (int i = 0; i < 2; i++) {
			aluOp[i] = new RussWire();
		}
		bInvert = new RussWire();
		a = new RussWire();
		b = new RussWire();
		carryIn = new RussWire();
		less = new RussWire();
		result = new RussWire();
		addResult = new RussWire();
		carryOut = new RussWire();
		and1  = new HwProj03_AND();
		or1  = new HwProj03_OR();
		add_mux = new HwProj03_MUX_4by1();
		end_mux = new HwProj03_MUX_4by1();
		adder = new HwProj03_FullAdder();
	}
}
