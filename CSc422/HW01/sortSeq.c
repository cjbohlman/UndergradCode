/*****************
 * Project: This pprogram sorts a text file using quicksort sequentially.
 * Author: Chris Bohlman
 ****************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/time.h>

   /* used for timing */   
struct timeval startTime, endTime;
int seconds, micros;

int quicksort(char *[4000000] , int, int);
int partition(char *[4000000] , int, int);
int medianOf3Pivot(char *[4000000] , int, int);
int numLines;
char *arr[4000000];

int main(int argc, char *argv[])
{
	FILE *fptr;
	char filename[30];
	strncpy(filename,argv[1],30);
	fptr = fopen(filename, "r");
	if (fptr == NULL)
	{
		fprintf(stderr,"File %s cannot be opened\n",filename);
		exit(1);
	}
	char line[201];
	numLines = 0;
	int i= 0;
	while(fgets(line, 201, fptr))
	{
		arr[numLines] = calloc(201,sizeof(char));
		strncpy(arr[numLines], line, 201);
		++numLines;
	}
	fclose(fptr);

	gettimeofday(&startTime, NULL);
	quicksort(arr, 0, numLines-1);
	gettimeofday(&endTime, NULL);      
   	seconds = endTime.tv_sec  - startTime.tv_sec;
   	micros  = endTime.tv_usec - startTime.tv_usec;
   	if ( endTime.tv_usec < startTime.tv_usec ) {
      		micros += 1000000;    // one million
      		seconds--;
   	}
   	fprintf(stderr,"runtime: %d seconds, %d microseconds\n",seconds, micros);
	for (i = 0; i < numLines; i++) 
	{
		printf("%s",arr[i]);
		free(arr[i]);
	}
	return 0;
}

void swap(char **str1, char **str2)
{
	char *temp = *str1;
  	*str1 = *str2;
  	*str2 = temp;
}  


int medianOf3Pivot(char *arr[4000000], int lo, int hi) {
        int md = (lo + hi)/2;
	if (strcmp(arr[md], arr[lo]) < 0) 
	{
		swap(&arr[lo], &arr[md]);
	}
	if (strcmp(arr[hi], arr[lo]) < 0) 
	{
        swap(&arr[lo],&arr[hi]);
    }
	if (strcmp(arr[md], arr[hi]) < 0) 
	{
        swap(&arr[hi],&arr[md]);
    }
	return hi;
}

int quicksort(char *arr[4000000], int lo, int hi)
{
	if (lo < hi )
	{
		int p = partition(arr, lo, hi);
		quicksort(arr, lo, p);
		quicksort(arr, p+1, hi);
	}
	return 0;
}

int partition(char *arr[4000000], int lo, int hi)
{
	char* pivot = arr[medianOf3Pivot(arr, lo, hi)];
	int i = lo - 1, j = hi + 1;
  
    	while (1)
    	{
        	// Find leftmost element greater than
        	// or equal to pivot
        	do
        	{
        		i++;
        	} while (strcmp(arr[i],pivot) < 0);
  
        	// Find rightmost element smaller than
        	// or equal to pivot
        	do
        	{
        		j--;
        	} while (strcmp(arr[j],pivot) > 0);
  
        	// If two pointers met.
        	if (i >= j)
            		return j;
  
        	swap(&arr[i],&arr[j]);
    	}
}

