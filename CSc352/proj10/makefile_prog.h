#ifndef HEADER_FILE
#define HEADER_FILE

#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <time.h>
#include "/home/cs352/spring17/Assignments/proj09/cs352.h"

char* trimwhitespace(char*);

void print_list();

void readLine (char* line, FILE *file);

struct node *findNode(char *);

int nodeExists(char*);

int nodeTarget(char*);

void printTraversal (char*);

void pot(struct node*);

void free_list();

typedef struct stat stats_node;

typedef struct node {
 	char *name;
	struct cmd *cmd_head;
        int mark;
	int visited_mark;
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

struct node *firstTarget;

#endif
