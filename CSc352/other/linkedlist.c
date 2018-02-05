/* File: strmath.c
 * Author: Chris Bohlman
 * Purpose: Count the number of times each int appears in a line
 */

 #include<stdio.h>
 #include<stdlib.h>
 #include<string.h>

struct s {
	char *str;
	struct s *next;
};

struct s *list_hd = NULL;

struct s *read_string() {
	struct s *tmpnode;
	char buf[64];
	int status;
	status = scanf("%s",buf);
	if (status == EOF) {
		return NULL;
	}
 	
	tmpnode = malloc(sizeof(struct s));
	if(tmpnode == NULL) {
		fprintf(stderr,"Out of memory!\n");
		exit(1);
	}

	tmpnode->str = strdup(buf);
	tmpnode->next = list_hd;
	list_hd = tmpnode;

	return tmpnode;
}

void sort_list() {
	struct s *ptr1, *ptr2;
	char *tmp;

	for (ptr1 = list_hd; ptr1 != NULL; ptr1  = ptr1->next) {
		for(ptr2 = ptr1->next; ptr2 != NULL; ptr2 = ptr2->next) {
			if(strcmp(ptr1->str,ptr2->str) > 0) {
				//ptr1->str1 is greater than ptr2 ->str2 -- swap
				tmp = ptr1->str;
				ptr1->str = ptr2->str;
				ptr2->str = tmp;
			}
		}
	}
}

void print_list() {
	struct s *ptr;
	
	printf("------List contents-----\n");
	for (ptr = list_hd; ptr; ptr = ptr->next) {
		printf("%s\n",ptr->str);
	}
}

int main(void) {
	while (read_string() != NULL) {
		
	}
	
	//sort_list();
	print_list();
	return 0;
}
