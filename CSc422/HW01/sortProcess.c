/*****************
 * Title: sortProcess.c
 * Project: This pprogram sorts a text file using quicksort sequentially.
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
int dupeSide;
int processes;
char **arr;
char** memoryBuffer;
char** mergeMemoryPointers;

struct endpoints {
	int low, hi;
};

//void swap2(struct **endpoints, struct **endpoints);

int main(int argc, char *argv[])
{
	FILE *fptr;
	dupeSide = 0;
	char filename[30];
	processes = atoi(argv[1]);
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
	while(fgets(line, 201, fptr))
	{
		arr[numLines] = calloc(201,sizeof(char));
		if (arr[numLines] == NULL) {
			fprintf(stderr,"Memory not alloc'd correctly\n");
		}
		strncpy(arr[numLines], line, 201);
		++numLines;
	}
	fclose(fptr);
	struct endpoints endpointArray[processes];
	int incrementer = numLines/processes;
	int startPoint = 0;
	for (i = 0; i < processes - 1; i++) {
		endpointArray[i].low=startPoint;
		endpointArray[i].hi=startPoint+incrementer - 1;
		startPoint += incrementer;
	}
	endpointArray[processes - 1].low = startPoint;
	endpointArray[processes - 1].hi = numLines - 1;
	memoryBuffer = mmap(NULL, numLines*201*sizeof(char), PROT_READ | PROT_WRITE, MAP_ANONYMOUS | MAP_SHARED, -1, 0);
	mergeMemoryPointers =  mmap(NULL, numLines*201*sizeof(char), PROT_READ | PROT_WRITE, MAP_ANONYMOUS | MAP_SHARED, -1, 0);
	if ( memoryBuffer == MAP_FAILED ) {
      		fprintf(stderr, "Error with shared memory");
      		exit(1);
   	}
	gettimeofday(&startTime, NULL);
	for (i = 0; i < numLines; i++) {
		mergeMemoryPointers[i] = calloc(201,sizeof(char));
	}
	int pidarray[processes];
	memcpy(memoryBuffer, arr,  numLines*201*sizeof(char));
   	for (i = 0; i < processes; i++) {
		pidarray[i] = fork();
		/* both parent and child execute this if statement! */
   		if (pidarray[i] < 0) {
      			fprintf(stderr, "fork failed!\n"); 
      			exit(1);
		}
		if (pidarray[i] == 0) {
			quicksort(memoryBuffer, endpointArray[i].low, endpointArray[i].hi);
			exit(0);
      	}
	}
	int status = 0;
	for (i = 0; i < processes; i++)
    {
        waitpid(pidarray[i], &status, 0);
    }
	
	int currentProcesses = processes/2;
	
	while (currentProcesses > 0) {	
		int newpidarr[currentProcesses];
		for (i = 0; i < currentProcesses; i++) {
			newpidarr[i] = fork();
			if (newpidarr[i] < 0) {
      			fprintf(stderr, "fork failed!\n"); 
      			exit(1);
			}
			if (newpidarr[i] == 0) {
				merge(endpointArray[i*2].low, endpointArray[i*2].hi, endpointArray[(i*2)+1].hi);
				exit(0);
			}
			
			
		}
		for (i = 0; i < currentProcesses; i++)
		{
			waitpid(newpidarr[i], &status, 0);
			endpointArray[i].low = endpointArray[i*2].low;
			endpointArray[i].hi = endpointArray[(i*2)+1].hi;
		}
		currentProcesses/=2;
		swap3(&memoryBuffer, &mergeMemoryPointers);
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
	for ( i = 0, bufPtr = memoryBuffer; i < numLines; i++) {
      		printf("%s", bufPtr[i]);
			fflush(stdout);
	}
	
	return 0;
}

void merge(int beginning, int middle, int end) {
	int i = beginning, j = middle+1, k = beginning; 
  	char **bufPtr = memoryBuffer;
	char **bufPtr2 = mergeMemoryPointers;
	while (i<=middle && j <=end) 
    	{	 
        	if (strcmp(bufPtr[i], bufPtr[j]) < 0) 
            {
			swap(&bufPtr2[k],&bufPtr[i]); 
			k++;
			i++;
		} else {
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

