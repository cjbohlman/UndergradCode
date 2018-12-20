/*****************
 * Title: sortThread.c
 * Project: This program sorts a text file using quicksort using threads.
 * Author: Chris Bohlman
 ****************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h> 
#include <sys/mman.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <pthread.h>

   /* used for timing */
struct timeval startTime, endTime;
int seconds, micros;

int quicksort(char *[4000000] , int, int);
int partition(char *[4000000] , int, int);
int medianOf3Pivot(char *[4000000] , int, int);
void merge(int, int, int);
void swap(char**, char**);
void swap3(char***, char***);

int numLines;
int threads;
char **arr;
char **mergeArr;
struct endpoints *arrayPointer;

struct endpoints 
{
	int low, hi;
};

void *quicksortWorker( void *arg ) 
{
   int i = (int) arg;
   quicksort(arr,arrayPointer[i].low, arrayPointer[i].hi);
   return NULL;
} /* Worker */

void *mergeWorker( void *arg ) 
{
   int i = (int) arg;
   merge(arrayPointer[i*2].low, arrayPointer[i*2].hi, arrayPointer[(i*2)+1].hi);
   return NULL;
} /* Worker */

int main(int argc, char *argv[])
{
	FILE *fptr;
	char filename[30];
	threads = atoi(argv[1]);
	strncpy(filename,argv[2],30);
	fptr = fopen(filename, "r");
	if (fptr == NULL)
	{
		fprintf(stderr,"File %s cannot be opened\n",filename);
		exit(1);
	}
	char line[201];
	numLines = 0;
	int i= 0;
	arr = malloc(4000000*201*sizeof(char));
	mergeArr = malloc(4000000*201*sizeof(char));
	while(fgets(line, 201, fptr))
	{
		arr[numLines] = calloc(201,sizeof(char));
		if (arr[numLines] == NULL) 
		{
			fprintf(stderr,"Memory not alloc'd correctly\n");
		}
		strncpy(arr[numLines], line, 201);
		++numLines;
	}
	fclose(fptr);
	struct endpoints endpointArray[threads];
	arrayPointer = endpointArray;
	int incrementer = numLines/threads;
	int startPoint = 0;
	for (i = 0; i < threads - 1; i++) 
	{
		endpointArray[i].low=startPoint;
		endpointArray[i].hi=startPoint+incrementer - 1;
		startPoint += incrementer;
	}
	endpointArray[threads - 1].low = startPoint;
	endpointArray[threads - 1].hi = numLines - 1;
	
	pthread_t workerid[threads];
	pthread_attr_t attr;
	pthread_attr_init( &attr );
	pthread_attr_setscope( &attr, PTHREAD_SCOPE_SYSTEM );

	gettimeofday(&startTime, NULL);
	
   	for (i = 0; i < threads; i++) {
		pthread_create( &workerid[i], &attr, quicksortWorker, (void *) i );
		//fprintf(stderr,"main: created thread %ld\n", workerid[i]);
		
	}
	
	for (i = 0; i < threads; i++) {
		pthread_join(workerid[i], NULL);
	}
	
	int currentthreads = threads/2;
	
	while (currentthreads > 0) {	
		for (i = 0; i < currentthreads; i++) {
			pthread_create( &workerid[i], &attr, mergeWorker, (void *) i );
			
			
		}
		for (i = 0; i < currentthreads; i++) {
			pthread_join(workerid[i], NULL);
			endpointArray[i].low = endpointArray[i*2].low;
			endpointArray[i].hi = endpointArray[(i*2)+1].hi;
		}
		currentthreads/=2;
		swap3(&arr, &mergeArr);
	}
	gettimeofday(&endTime, NULL);
	seconds = endTime.tv_sec  - startTime.tv_sec;
    micros  = endTime.tv_usec - startTime.tv_usec;
    if ( endTime.tv_usec < startTime.tv_usec ) {
        micros += 1000000;    // one million
        seconds--;
    }
    fprintf(stderr,"runtime: %d seconds, %d microseconds\n",seconds, micros);
	char **bufPtr;
	for ( i = 0, bufPtr = arr; i < numLines; i++) {
      		printf("%s", bufPtr[i]);
			fflush(stdout);
	}
	pthread_exit( NULL );
	return 0;
}

void merge(int beginning, int middle, int end) {
	int i = beginning, j = middle+1, k = beginning; 
  	char **bufPtr = arr;
	char **bufPtr2 = mergeArr;
	while (i<=middle && j <=end) 
    {	 
        if (strcmp(bufPtr[i], bufPtr[j]) < 0) 
        {
			swap(&bufPtr2[k],&bufPtr[i]); 
			k++;
			i++;
		} else 
		{
			swap(&bufPtr2[k],&bufPtr[j]);
			k++;
			j++; 
		}
	} 
  
    	while (i < middle+1) 
        	swap(&bufPtr2[k++],&bufPtr[i++]); 
  
    	while (j < end+1) { 
			swap(&bufPtr2[k++],&bufPtr[j++]); 
	}
}

void swap(char **str1, char **str2)
{
	char *temp = *str1;
  	*str1 = *str2;
  	*str2 = temp;
}  

void swap2(struct endpoints **str1, struct endpoints **str2)
{
        struct endpoints *temp = *str1;
        *str1 = *str2;
        *str2 = temp;
}

void swap3(char ***str1, char ***str2)
{
        char **temp = *str1;
        *str1 = *str2;
        *str2 = temp;
}

int medianOf3Pivot(char *arr[4000000], int lo, int hi) {
        int md = (lo + hi)/2;
	if (strcmp(arr[md], arr[lo]) < 0) {
		swap(&arr[lo], &arr[md]);
	}
	if (strcmp(arr[hi], arr[lo]) < 0) {
                swap(&arr[lo],&arr[hi]);
        }
	if (strcmp(arr[md], arr[hi]) < 0) {
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

