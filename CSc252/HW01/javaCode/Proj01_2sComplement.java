/* Simulates a physical device that performs 2's complement on a 32-bit input.
 *
 * Author: Chris Bohlman
 */

public class Proj01_2sComplement
{
	public void execute()
	{
		// TODO: fill this in!
		//
		// REMEMBER: You may call execute() on sub-objects here, and
		//           copy values aroudn - but you MUST NOT create
		//           objects while inside this function.
		
		for (int i = 0; i <= 31; ++i) {
			p[i].in = in[i];
		}
		for (int i=0; i<32; i++)
			p[i].execute();
		
		for (int i=0; i<32; i++)
			q.a[i] = p[i].out;
		
		q.b[0] = true;
		q.execute();
		
		for (int i=0; i<32; i++)
			out[i] = q.sum[i];
	}



	// you shouldn't change these standard variables...
	public boolean[] in;
	public boolean[] out;
	

	// TODO: add some more variables here.  You must create them
	//       during the constructor below.  REMEMBER: You're not
	//       allowed to create any object inside the execute()
	//       method above!

	public boolean[] toAdd;
	Proj01_NOT p[] = new Proj01_NOT[32];
	Proj01_ADD q = new Proj01_ADD();



	public Proj01_2sComplement()
	{
		in  = new boolean[32];
		out = new boolean[32];

		// TODO: this is where you create the objects that
		//       you declared up above.
		
		for (int i=0; i<32; i++)
			p[i] = new Proj01_NOT();
	}
}

