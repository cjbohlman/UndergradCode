#ifndef HEADER_FILE
#define HEADER_FILE

#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

#include "/home/cs352/spring17/Assignments/proj09/cs352.h"

char* trimwhitespace(char*);

void print_list();

struct node *findNode(char *);

void readLine(char*, FILE*);

int nodeExists(char*);

void printTraversal (char*);

void pot(struct node*);

void free_list();

typedef struct node {
 	char *name;
	struct cmd *cmd_head;
        int mark;
	int target;
        struct node *next;
        struct edge *head;
}node;
typedef struct edge {
        struct node *who;
        struct edge *next;
}edge;
typedef struct cmd {
	char* name;
	struct cmd *next;
}cmd;
//Global variables
struct node *list_hd;
int return_val;


#endif
