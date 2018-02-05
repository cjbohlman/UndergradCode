#include <stdio.h>

int main (void) {
	int a = 5;
	int n = 1;
	int b = a | (0x1 << n);
	printf("a=%d  b=%d\n",a,b);
	return 0;
}
