/* Simulates a physical AND gate.
 *
 * Author: Chris Bohlman
 */

public class Proj01_AND
{
	public void execute()
	{
		if (a && b) {
			out = true;
		}
		else {
			out = false;
		}
	}



	// ------ DON'T CHANGE ANYTHING BELOW THIS LINE ------

	public boolean a,b;   // inputs
	public boolean out;   // output

	public Proj01_AND()
	{
		/* nothing is needed here */
	}
}

