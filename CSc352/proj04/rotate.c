/* File: rotate.c
 * Author: Chris Bohlman
 * Purpose: read in a vector array and rotate it by a given amount
 */

#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int occurs(int a[],int amt,int place,int val,char exists[]);

int main (void) {

	int amt;
	int val = scanf("%d",&amt);
        if ( val != EOF && val == 1) {
                if (amt < 0) {
                        fprintf(stderr,"Vector size is not positive\n");
                        return 1;
                }
	        int *numarr = calloc(amt,sizeof(int));
	        int i;
		int available = 0;
		for (i = 0; i < amt; i++) {
			int val;
        	        val = scanf("%d",&numarr[i]);
			if (val == 0 || val == EOF) {
				fprintf(stderr,"Error in inputting vector.\n");
        	                return 1;
			}
			available = available + 1;
	        }
		if (available != amt) {
        	                fprintf(stderr,"Error in inputting vector.\n");
                	        return 1;
      		}
		int rot;
	        scanf("%d",&rot);
		rot = rot * -1; 
		if (abs(rot) > amt) {
			rot  = rot%amt;
		}
		int numarr_rot[amt];
		if (rot > 0) {
			for (i  = 0; i < amt; i++) {
				int val = i - rot;
				if (val < 0) {
					numarr_rot[i] = numarr[val + amt];
				}
				else{
					numarr_rot[i] = numarr[val];
				}
			}
		}
		else {
			rot = rot*-1; 
			for (i  = 0; i < amt; i++) {
				if ((i + rot) >= amt) {
                	                numarr_rot[i] = numarr[(i + rot)-amt];
                        	}
                        	else{
                                	numarr_rot[i] = numarr[i + rot];
                        	}	
                	}
		}
		for (i = 0; i < amt; i++) {
			printf("%d ",numarr_rot[i]);
		}
		printf("\n");
		free(numarr);	
		return 0;
	}
	else {
		fprintf(stderr,"Error in inputting vector size\n");
                return 1;
        }
}

