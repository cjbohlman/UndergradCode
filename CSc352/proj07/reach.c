/* File: reach.c
 * Author: Chris Bohlman
 * Purpose: Analyze the reachibility of a graph
*/

struct node {
        char *name;
	int mark;
        struct node *next;
        struct edge *head;
};
struct edge {
        struct node *who;
        struct edge *next;
};

#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<ctype.h>


//Global variables
struct node *list_hd = NULL;
int return_val = 0;

//reachFunc(char *line) --  takes in a line and does the necessary operations to it
void reachFunc(char *line);

//free_list -- frees the entire list from memory
void free_list() {
        struct node *ptr = list_hd;
        struct edge *ptr2;
	
	if (ptr != NULL) {
		while(1) {
			ptr2 = ptr->head;
			if (ptr2 != NULL) {
				int cont1 = 1;
        	        	while(cont1) {	
                			if (ptr2->next != NULL) {
                        			struct edge *temp = ptr2->next;
						free(ptr2);
						ptr2 = temp;
                			}
               				else {
                				free(ptr2);
						cont1 = 0;
               				}
				}
        		}
			if (ptr->next != NULL) {
				struct node *tempo = ptr->next;
				free(ptr);
				ptr = tempo;
			}
			else {
				free(ptr);
				break;
			}
        	}
       	}
}

// dfs(fromNode, toNode) -- returns true if there is a directed path from fromNode to toNode.
int dfs(struct node *fromNode, struct node *toNode) {
	if (strcmp(fromNode->name,toNode->name) == 0) {
		return 1;
	}
	if (fromNode->mark == 1) {
		return 0;
	}
	fromNode->mark = 1;
	struct edge *ptr = fromNode->head;
	if(ptr == NULL) {
		return 0;
	}
	while(1) {	
		if (dfs(ptr->who, toNode)) {
			return 1;
		}
		if (ptr->next != NULL) {
			ptr = ptr->next;
		}
		else {
			break;
		}
	}	
	return 0;
}

// validNode(target) -- returns true if the given node currently exists
int validNode(struct node *target) {
	if (list_hd == NULL) {
		return 0;
	}
	struct node *ptr = list_hd;
	while(1) {
                if (strcmp(ptr->name,target->name) == 0) {
                        return 1;
                }
                if (ptr->next != NULL) {
                        ptr = ptr->next;
                }
                else {
                        break;
                }
        }
	return 0;
}

int main(int argc, char *argv[]) {
	if(argc == 1) {
                //This means you just read input from stdin
                char* line = NULL;
                size_t len = 1024;
		line = malloc(len*sizeof(char));
                while(getline(&line,&len, stdin) != EOF) {
			reachFunc(line);
                }
		free(line);
        }

        else {
                //input through file
                if(argc > 2) {
                        fprintf(stderr,"WARNING: too many arguments");
                }
                char line[1024];
                FILE *file = fopen(argv[1], "r");
                if (file) {
                    while (fgets(line,1024, file) != NULL) {
				reachFunc(line);
                    }
                    fclose(file);
                }
                else {
                        fprintf(stderr,"ERROR: File does not exist or is not readable\n");
                        return_val = 1;
                }
        }
	free_list();
        return return_val;
}

void reachFunc(char *line) {
        char *op = calloc(4,sizeof(char));
	char *vname1 = calloc(65,sizeof(char));
	char *vname2 = calloc(65,sizeof(char));
	char *vname3 = calloc(65,sizeof(char));
	sscanf(line,"%s %65s %65s %65s",op,vname1,vname2,vname3);
        if (strlen(op) != 2) {
                fprintf(stderr,"ERROR: malformed input %s",line);
                return_val = 1;
        }
	//else if (!(strcmp(vname3,"") || (strlen(vname3) == 0))) {

        else if (*vname3 != '\0') {
                fprintf(stderr,"ERROR: malformed input %s",line);
                return_val = 1;
        }
        else{
                int valid_num = 0, i;
		if (strlen(vname1) == 0) {
			valid_num = 1;
		}
		for(i=0; i < strlen(vname1); i++){
			if(!isalnum(vname1[i])){
				valid_num = 1;
			}
		}
		
		if (op[1] == 'n' && !valid_num && *vname2 == '\0') {
                        struct node *tmpnode;
                        tmpnode = malloc(sizeof(struct node));
                        tmpnode->name = strdup(vname1);
                        tmpnode->mark = 0;
                        tmpnode->head = NULL;
                        if (validNode(tmpnode) == 0) {
                                if (list_hd == NULL) {
                                        list_hd = tmpnode;
                                        tmpnode->next = NULL;
                                }
                                else {
                                        struct node *ptr = list_hd;
                                        while(1) {
                                                if (ptr->next == NULL) {
                                                        break;
                                                }
                                                ptr = ptr->next;
                                        }
                                        ptr->next = tmpnode;
                                        tmpnode->next = NULL;
                               }
                        }
                        else {
                                fprintf(stderr,"ERROR: node already exists\n");
                                return_val = 1;
                                free(tmpnode);
                        }
                }
                else if (op[1] == 'e' && !valid_num) {
                        struct node *ptr = list_hd;
                        struct node *ex1;
                        ex1 = malloc(sizeof(struct node));
                        ex1->name = vname1;
                        struct node *ex2;
                        ex2 = malloc(sizeof(struct node));
                        ex2->name = vname2;
                        int valid1 = validNode(ex1), valid2 = validNode(ex2);
			free(ex1);
                        free(ex2);
                        if (valid1 && valid2) {
                                while(1) {
                                        if (strcmp(ptr->name,vname1) == 0) {
                                                break;
                                        }
                                        ptr = ptr->next;
                                }
                                struct node *ptr2 = list_hd;
                                while(1) {
                                        if (strcmp(ptr2->name,vname2) == 0) {
                                                break;
                                        }
                                        ptr2 = ptr2->next;
                                }
                                struct edge *tmpnode;
                                tmpnode = malloc(sizeof(struct edge));
                                if (ptr->head == NULL) {
					ptr->head = tmpnode;
					tmpnode->who = ptr2;
					tmpnode->next = NULL;
                                }
                                else {
                                        struct edge *ptr3 = ptr->head;
					int dupe = 0;
                                        while(1) {
						if(strcmp(ptr2->name,ptr3->who->name) == 0) {
							dupe = 1;
							break;
						}
						if (ptr3->next == NULL) {
                                                        break;
                                                }
                                                ptr3 = ptr3->next;
                                        }
					if (!dupe) {
                                        	ptr3->next = tmpnode;
                                        	tmpnode->who = ptr2;
                                        	tmpnode->next = NULL;
					}
                                }
                        }
			else {
                                fprintf(stderr,"ERROR: Invalid node\n");
                                return_val = 1;
                        }
                }
                else if (op[1] == 'q' && !valid_num) {
                        struct node *ptr = list_hd;
                        struct node *ex1;
                        ex1 = malloc(sizeof(struct node));
                        ex1->name = vname1;
                        struct node *ex2;
                        ex2 = malloc(sizeof(struct node));
                        ex2->name = vname2;
                        int valid1 = validNode(ex1), valid2 = validNode(ex2);
			free(ex1);
			free(ex2);
                        if (valid1 && valid2) {
				struct node *markptr = list_hd;
				while (1) {
					if(markptr->mark == 1) {
                                                markptr->mark = 0;
                                        }
                                        if(markptr->next == NULL) {
                                                break;
                                        }
                                        markptr = markptr->next;
                                }
                                while(1) {
                                        if (strcmp(ptr->name,vname1) == 0) {
						break;
                                        }
                                        ptr = ptr->next;
                                }
                                struct node *ptr2 = list_hd;
                                while(1) {
                                        if (strcmp(ptr2->name,vname2) == 0) {
                                                break;
                                        }
                                        ptr2 = ptr2->next;
                                }
                                if(dfs(ptr,ptr2)) {
                                        printf("1\n");
                                }
                                else {
                                        printf("0\n");
                                }
                        }
                        else {
                                fprintf(stderr,"ERROR: Invalid input\n");
                                return_val = 1;
                        }
                }
                else {
                        fprintf(stderr,"ERROR: malformed input %s",line);
                        return_val = 1;
                }
        }
	free(op);
	free(vname1);
	free(vname2);
	free(vname3);
}
