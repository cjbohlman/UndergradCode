
/* File: count.c
 * Author: Chris Bohlman
 * Purpose: read in a certain amount of integers and count how many times each integer appears 
 */

#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<ctype.h>

int occurs(int a[],int amt,int place,int val,char exists[]);
void array_sort(int *s, int amt);

int main (void) {
	int amt;
	int val = scanf("%d",&amt);		
	if ( val != EOF && val == 1) {
		if (amt < 0) {
			fprintf(stderr,"Out of memory\n");
			return 1;
		}
		int *numarr = calloc(amt,sizeof(int));
		int i;
		int available = 0;
		char exists[amt];
		memset(exists, 0, amt*sizeof(int) );
		i = 0;
		while (i < amt && scanf("%d",&numarr[i]) != EOF) {
			available = available + 1;
			i = i + 1;
		}
		if (available != amt) {
			fprintf(stderr,"Input was not %d integers\n",amt);
			return 1;
		}
		array_sort(numarr,amt);
		for (i = 0; i < amt; i++) {
			if(occurs(numarr,amt,i,numarr[i],exists)) {
				int num_occurs = occurs(numarr,amt,i,numarr[i],exists);
				printf("%d %d\n",numarr[i],num_occurs);
				exists[i] = 1;
			}
        	}
		free(numarr);
		return 0;
	}
	else {
               	fprintf(stderr,"Bad or no number entered\n");
                return 1;
	}
}

/*
* sort(int *s, int amt):
* takes an array of ints and the amount of ints in the list, and sorts the array using bubble sort.
*/

void array_sort(int *s,int amt) {
  int tmp;
  int i, j;
  
  for(i=0; i<amt-1; i++) {
        for (j=i+1; j<amt; j++) {
           if (s[i] > s[j]) {
                 tmp=s[i];
                 s[i]=s[j];
                 s[j]=tmp;
           }
        }
  }
}

/*
* occurs(int a[],int amt,int place,int val,char exists[]): 
* takes an array of values, the place value in that array, what the number is at that place in the array, and a companion array to see if the value has already been checked or not
* returns the number of times the value is in the array, if it is a repeated value, function returns 0
*/ 

int occurs(int a[],int amt,int place,int val,char exists[]) {
	int i;
	int count = 0;
	for(i = 0; i < amt; i++) {
		if (a[i] == val) {
			if (exists[i] != 0) return 0;
			count = count + 1;
		}
	}
	return count;
}

