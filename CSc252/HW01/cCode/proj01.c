/* Implementation of a 32-bit adder in C.
 *
 * Author: Chris Bohlman
 */


#include "proj01.h"


void execute_add(Proj01Data *obj)
{
	int curCarry = 0;
	int i;
	for (i = 0; i <= 31; ++i) {
		int val1 = (obj->a >> i) & 0x1;
		int val2 = (obj->b >> i) & 0x1;
		if (val1 && val2) {
			if (curCarry) {
				obj->sum  = obj->sum | (0x1 << i);
				curCarry = 1;
			}
			else {
				curCarry = 1;
			}
		}
		else if ((val1 && !val2) || (!val1 && val2)) {
			if (curCarry) {
				curCarry = 1;
			}
			else {
				obj->sum  = obj->sum | (0x1 << i);
				curCarry = 0;
			}
		}
		else {
			if (curCarry) {
				obj->sum  = obj->sum | (0x1 << i);
				curCarry = 0;
			}
			else {
				curCarry = 0;
			}
		}
	}
	
	obj->carryOut = curCarry;
	int val1 = (obj->a >> 31) & 0x1;
        int val2 = (obj->b >> 31) & 0x1;
	if (val1 == val2) {
		if (val1 != ((obj->sum >> 31) & 0x1))
			obj->overflow = 1;
	}
}

