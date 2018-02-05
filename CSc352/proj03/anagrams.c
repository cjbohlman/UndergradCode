/*
 * File: anagrams.c
 * Author: Chris Bohlman
 * Purpose: Find anagrams from a list of words by sorting two strings and seeing if they're the same
 * isAnagram: checks to see if two submitted char arrays are anagrams, by ordering them and then comparing them
 * assuming both inputs are valid char arrays.
 * string_sort: sorts char array based off of ascii value. does this through bubble sort.
 * assumes input is valid char array
 */

#include<stdio.h>
#include<string.h>
#include<ctype.h>
void isAnagram(char *str, char *compared_str);
void string_sort(char *s);

int main (void) {
	char str[65];
 	if (scanf("%64s",str) != EOF) {
	int l;
	int badfirst = 0;
	for (l = 0; l < strlen(str); l++) {
        	if (isalpha(str[l]) == 0) {
                	badfirst = 1;
                }
        }
        if (badfirst == 1) {
        	fprintf(stderr,"Bad first string\n");
		return 1;
	}
	else {
		printf("%s\n",str);
		char compared_str[64];
		int k;
		int isnotvalid = 0;
		while (scanf("%s",compared_str) != EOF) {
			for (k = 0; k < strlen(compared_str); k++) {
					if (isalpha(compared_str[k]) == 0) {
					isnotvalid = 1;
				}
			}
			if (isnotvalid == 1) {
				fprintf(stderr,"Bad Input ... non-alphabetical character\n");
				isnotvalid = 0;
			}
			else {
				isAnagram(str,compared_str);
			}
		}	
		return 0;
	}
	}
	else {
	return 0;
	}
}		

void isAnagram(char *str, char *compared_str) {
	int len1 = strlen(str);
	int len2 = strlen(compared_str);
	if (len1 == len2) {
		int i;
		char str1[len1 + 1];
		str1[len1] = 0;
		char str2[len1 + 1];
		str2[len1] = 0;
		for (i = 0; i < len1; i++) {
			str1[i] = str[i];
		}
		for (i = 0; i < len1; i++) {
			str2[i] = compared_str[i];
		}
		
		for (i = 0; i < len1; i++) {
			str1[i] = tolower(str1[i]);
			str2[i] = tolower(str2[i]);
		}
		
		string_sort(str1);
		string_sort(str2);
		if (memcmp(str1,str2,len1)== 0) {
			printf("%s\n",compared_str);
		}
		else {
		}
	}
	
}

void string_sort(char *s) {
  char tmp;
  int i, j, len;
  len=strlen(s);
  for(i=0; i<len-1; i++) {
	for (j=i+1; j<len; j++) {
	   if (s[i] > s[j]) {
		 tmp=s[i];
		 s[i]=s[j];
		 s[j]=tmp;
	   }
	}
  }
}


	
