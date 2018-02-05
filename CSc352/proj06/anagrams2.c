/* File: anagrams2.c
 * Author: Chris Bohlman
 * Purpose: find the anagrams of every word inputted with a linked list inside a linked list
 */

 #include<stdio.h>
 #include<stdlib.h>
 #include<string.h>
 #include<ctype.h>

//global variable lmao
int returnval = 0;

//function: string_sort
//sorts the inputted string
void string_sort(char *s) {
  char tmp;
  int i, j, len;
  len=strlen(s);
  for(i=0; i<len; i++) {
	s[i] = tolower(s[i]);
  }
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
//struct sorted
//struct for each sorted word, contains a linked list of the initial words in it
struct sorted {
        char *sorted_str;
        struct sorted *next;
	struct words *head;
};
//struct words
//struct for each regular word that gets read in. Inside sorted struct
struct words {
        char *str;
        struct words *next;
};


struct sorted *list_hd = NULL;

//function read_string:
//reads in a string to sort into either a new node, or to add to a previous node
struct sorted *read_string() {
        struct sorted *tmpnode;
        char buf[64];
        int status;
        status = scanf("%64s",buf);
        if (status == EOF) {
                return NULL;
        }

        tmpnode = malloc(sizeof(struct sorted));
        if(tmpnode == NULL) {
                fprintf(stderr,"Out of memory!\n");
                exit(1);
        }
	int isnotvalid = 0;
	int k;
	for (k = 0; k < strlen(buf); k++) {
        	if (isalpha(buf[k]) == 0) {
                	isnotvalid = 1;
                }
        }
        if (isnotvalid == 1) {
        	fprintf(stderr,"Bad word %s\n",buf);
		returnval = 1;
                return tmpnode;
	}
	char *sorted_buf = strdup(buf);
	string_sort(sorted_buf);
	if (list_hd == NULL) {
        	tmpnode->sorted_str = sorted_buf;
        	list_hd = tmpnode;
		tmpnode->next = NULL;
		
		struct words *tmpnode2;		
		tmpnode2 = malloc(sizeof(struct words));
		tmpnode2->str = strdup(buf);
		tmpnode2->next = NULL;
		
		tmpnode->head = tmpnode2;
	}
	else {
		struct sorted *ptr = list_hd;
		while (1) {
			if ((strlen(sorted_buf) == strlen(ptr->sorted_str)) && strcmp(sorted_buf,ptr->sorted_str) == 0) {
                	        struct words *tmpnode2;
				tmpnode2 = malloc(sizeof(struct words));
                	        tmpnode2->str = strdup(buf);
                	        tmpnode2->next = NULL;
				
				struct words *ptr2 = ptr->head;
				while(1) {
					if (ptr2->next == NULL) {
						break;
					}
			 		ptr2 = ptr2->next;
				}
                	        ptr2->next = tmpnode2;

                        	break;
			}
			else if (ptr->next == NULL) {
				tmpnode->sorted_str = strdup(sorted_buf);
        	        	tmpnode->next = NULL;
			
				struct words *tmpnode2;
                		tmpnode2 = malloc(sizeof(struct words));
                		tmpnode2->str = strdup(buf);
                		tmpnode2->next = NULL;

                		tmpnode->head = tmpnode2;
	
				ptr->next = tmpnode;
				break;
			}
			ptr = ptr->next;
		}
	}

        return tmpnode;
}

//function: print_list
//Prints all of the words in the linked list NOT THE SORTED WORDS THOUGH
void print_list() {
        struct sorted *ptr;
	struct words *ptr2;

        for (ptr = list_hd; ptr; ptr = ptr->next) {
		for(ptr2 = ptr->head; ptr2; ptr2 = ptr2 -> next) {
                	printf("%s",ptr2->str);
			if (ptr2->next != NULL) {
				printf(" ");
			}
		}
		printf("\n");
        }
}

int main(void) {
        while (read_string() != NULL) {

        }
        print_list();
	if (returnval == 1) {
		return 1;
	}
        else {
		return 0;
	}
}
