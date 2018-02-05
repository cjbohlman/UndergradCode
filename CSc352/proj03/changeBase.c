/* 
 * File: changeBase.c
 * Author: Chris Bohlman
 * Purpose: Get a valid base number and convert a number in that base to base 10.
 */

#include<stdio.h>
#include <string.h>
#include <ctype.h>
#include <math.h>

int main (void) {
	int base;
	if (scanf("%d",&base)==1) {
		if (base > 36 || base < 2) {
                	fprintf(stderr,"Bad value for base.\n");
                	return 1;
        	}
		char str[64];
        	while (scanf("%s",str) != EOF) {
       			int len = strlen(str);
			int i;
			int yes = 1;
			int printederr = 0;
			for (i = 0; i < len; i++) {
				if (isdigit(str[i]) && (str[i] - '0') < base) {
					str[i] = str[i] - '0';
				}
				else if(isalpha(str[i]) && (tolower(str[i]) - 'a' + 10) < base) {
					str[i] = tolower(str[i]) - 'a' + 10;
				}	
				else {
					if (printederr == 0) {
						fprintf(stderr,"Not a base %d number\n",base);
						yes = 0;
						printederr = 1;
					}
				}
			}	
			if (yes == 1) {
				long sum = 0;
				for (i = 0; i < len; i++) {
	    				int num = str[i];
            				sum = sum + num*pow(base,len - (i + 1));   
        			}	
				printf("%lu\n",sum);
			}	
		}
        	return 0;
	}
	else{
		fprintf(stderr,"Bad base input\n");
		return 1;
	}
}
