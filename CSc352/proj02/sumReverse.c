/*
*File: sumReverse.c
*Author: Chris Bohlman
*Purpose: Finds the reverse sum by adding a number with it's reverse
*/

#include<stdio.h>

int main(void) {
	int num;
	int returnint = 0;
	while(scanf("%d",&num) != EOF) {
		if (num <= 0) {
			fprintf(stderr,"Error: input value %d is not positive\n",num);
			returnint = 1;
		}
		else {
			int sum = 0;
			int orig_num = num;
			while (num != 0) {
				sum = sum*10;
				sum = sum + num%10;
				num = num/10;
				
			}
			sum = sum + orig_num;	
			printf("The sum of reverse of %d is %d\n",orig_num,sum);
		}
	}	
	return returnint;
}
