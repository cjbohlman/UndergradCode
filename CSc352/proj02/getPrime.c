/*
*File: getPrime.c 
*Author: Chris Bohlman
*Purpose: Returns the next highest prime number after a given integer 
*/

#include<stdio.h>
#include<math.h>

int main (void) {
	int num;
	scanf("%d",&num);
	if(num <= 0) {
		fprintf(stderr,"Error: %d is not a positive number\n",num);
		return 1;
	}
	else {
		int isPrime = 0;
		while (isPrime != 1) {
			int isNotPrime = 0;
			num++;		
			int j;
			for (j = 2; j <=sqrt(num); j++) {
				if (num%j == 0) {
					isNotPrime = 1;
				}
			}
			if(isNotPrime != 1) {
				isPrime = 1;
			}
		}	
		printf("%d\n",num);
		return 0;
	}
}
