/* Simulates a physical device that performs (signed) addition on a 32-bit input.
 *
 * Author: Chris Bohlman
 */

public class Proj01_ADD
{
	public void execute()
	{
		boolean curCarry = false;;
		for (int i = 0; i <= 31; ++i) {
			if (a[i] && b[i]) {
				if (curCarry) {
					sum[i] = true;
					curCarry = true;
				}
				else {
					sum[i] = false;
					curCarry = true;
				}
			}
			else if ((a[i] && !b[i]) || (!a[i] && b[i])) {
				if (curCarry) {
					sum[i] = false;
					curCarry = true;
				}
				else {
					sum[i] = true;
					curCarry = false;
				}
			}
			else {
				if (curCarry) {
					sum[i] = true;
					curCarry = false;
				}
				else {
					sum[i] = false;
					curCarry = false;
				}
			}
		}
		carryOut = curCarry;
		if (a[31] == b[31]) {
			if (a[31] != sum[31])
                        	overflow = true;
		}
	}



	// ------ DON'T CHANGE ANYTHING BELOW THIS LINE ------

	// inputs
	public boolean[] a,b;

	// outputs
	public boolean[] sum;
	public boolean   carryOut, overflow;

	public Proj01_ADD()
	{
		a   = new boolean[32];
		b   = new boolean[32];
		sum = new boolean[32];
	}
}

