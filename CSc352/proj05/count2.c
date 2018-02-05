/* File: strmath.c
 * Author: Chris Bohlman
 * Purpose: Count the number of times each int appears in a line
 */

 #include<stdio.h>
 #include<stdlib.h>
 #include<string.h>

//node
struct s {
	int value;
	struct s *next;
};
struct s *list_hd = NULL;

//read_ints()
//reads in an int from stdin and makes a new node for it and puts it at the front of the list
struct s *read_ints() {
	struct s *tmpnode;
	int buf;
	int status;
	status = scanf("%d",&buf);
	if (status == EOF) {
		return NULL;
	}
	if (status == 0) {
		fprintf(stderr,"Error: Non-integer characters in input\n");
		exit(1);		
 	}
	
	tmpnode = malloc(sizeof(struct s));
	if(tmpnode == NULL) {
		fprintf(stderr,"Out of memory!\n");
		exit(1);
	}

	tmpnode->value = buf;
	tmpnode->next = list_hd;
	list_hd = tmpnode;

	return tmpnode;
}

//void sort_list()
//sorts the linked list of nodes using a bubble sort
void sort_list() {
	struct s *ptr1, *ptr2;
	int tmp;

	for (ptr1 = list_hd; ptr1 != NULL; ptr1  = ptr1->next) {
		for(ptr2 = ptr1->next; ptr2 != NULL; ptr2 = ptr2->next) {
			if(ptr1->value>ptr2->value) {
				//ptr1->str1 is greater than ptr2 ->str2 -- swap
				tmp = ptr1->value;
				ptr1->value = ptr2->value;
				ptr2->value = tmp;
			}
		}
	}
}

//void print_list()
//prints however many times each int shows up in the list of ints
void print_list() {
	struct s *ptr;
	ptr = list_hd;	
	while(ptr != NULL) {
		int count = 1;
		int current_val = ptr->value;
		while (ptr->next != NULL && ptr->next->value == current_val) {
			ptr = ptr->next;
			count = count + 1;
		}
		printf("%d %d\n",current_val,count);
		ptr = ptr->next;
	}

}

int main(void) {
	while (read_ints() != NULL) {
		
	}
	
	sort_list();
	print_list();
	return 0;
}
