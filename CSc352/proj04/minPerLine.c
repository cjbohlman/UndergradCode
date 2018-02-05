/* File: minPerLine.c
 * Author: Chris Bohlman
 * Purpose: reads in a line of numbers and print out the smallest number
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int main (void) {
	char* line = NULL;
	size_t len = 0;
	int return_val = 0;
	int min;	
	while(getline(&line,&len, stdin) != EOF) {
		int i, valid_line = 1;
		int empty = 0;
		for (i = 0; i < strlen(line); i++) {
			if (!(isspace(line[i]))) {
				empty = 1;
			}
		}
		if (empty == 0) {
			valid_line = 0;
                        fprintf(stderr,"Empty string\n");
                        return_val = 1;
		}
		for (i = 0; i < strlen(line);i++) {
			if (isdigit(line[i]) || isspace(line[i])) {
				continue;
			}

			else {
				valid_line = 0;
				fprintf(stderr,"Line contains something other than digits and white space\n");
				return_val = 1;
			}
		}

		if (valid_line) {
			char* walker = line;
                        sscanf(walker, "%d", &min);
			while (*walker) {
				int num;
				if (sscanf(walker, "%d", &num) == 1) {
					if (min > num) {
						min = num;
					}
				}
				while (isdigit(*walker)) {
					walker = walker + 1;
				}
				while(isspace(*walker)) {
					walker = walker + 1;
				}
			}

			/////////////////////////////////

			
			printf("%d\n",min);
		}
	}
	free(line);
	return return_val;
}

