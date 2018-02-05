/*
*File: isFactorial.c
*Author: Chris Bohlman
*Purpose: Reads in a sequence of integers and tells user whether they are factorials or not
*/

#include<stdio.h>

int main(void) {
	int a;
	int returnint = 0;
	while (scanf("%d",&a) > EOF) {
		if (a <= 0) {
			fprintf(stderr, "Error: input value %d is not positive\n",a);
			returnint = 1;
		}
		else if(a == 1) {
			printf("%d = 1!\n",a);
		}
		else {
			int n = 1;
			int endvalue = 1;
			while (endvalue <= a) {
				if (endvalue == a) {
					printf("%d = %d!\n",a,n-1);
					break;
				}
				else {
					if(a%n == 0) {
						endvalue = endvalue * n;
					}
					else {
						break;
					}
				}
			n = n + 1;
			}
			if (endvalue != a) {
				printf("%d is not factorial\n",a);
			}
		}
	}
	return returnint;	
}
