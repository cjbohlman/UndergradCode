#include "makefile_prog.h"
#include "/home/cs352/spring17/Assignments/proj09/cs352.h"
//function trimWhiteSpace(char* str)
//trims whitespace from line
char *trimwhitespace(char *str) {
  char *end;

  // Trim leading space
  while(isspace((unsigned char)*str)) str++;

  if(*str == 0)  // All spaces?
    return str;

  // Trim trailing space
  end = str + strlen(str) - 1;
  while(end > str && isspace((unsigned char)*end)) end--;

  // Write new null terminator
  *(end+1) = 0;

  return str;
}

//function nodeExists(char* line)
//reads in the given line and determines if a node exists w/ line
int nodeExists(char* line) {
        struct node* ptr = list_hd;
        while (1) {
                if (ptr == NULL) {
                        return 0;
                }
                if (strcmp(ptr->name,line) == 0) {
                        return 1;
                }
                if (ptr->next == NULL) {
                        return 0;
                }

                else {
                        ptr = ptr->next;
                }
        }
}

//function nodeTarget(char* line)
//is the node a target? LETS FIND OUT
int nodeTarget(char* line) {
        struct node* ptr = list_hd;
        while (1) {
                if (ptr == NULL) {
                        return 0;
                }
                if (strcmp(ptr->name,line) == 0 && ptr->target == 1) {
                        return 1;
                }
                if (ptr->next == NULL) {
                        return 0;
                }

                else {
                        ptr = ptr->next;
                }
        }
}
//function findNode(char* line)
//reads in the given line and determines where node exists w/ line
struct node *findNode(char *line) {
        if (list_hd == NULL) {
		return NULL;
	}
	struct node* ptr = list_hd;
        while (1) {
                if (strcmp(ptr->name,line) == 0) {
                        return ptr;
                }
                if (ptr->next == NULL) {
                        return NULL;
                }

                else {
                        ptr = ptr->next;
                }
        }
}

//function free_list()
//frees entire list
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
			struct cmd *ptr3 = ptr->cmd_head;
			if (ptr3 != NULL) {
				int cont2 = 1;
                                while(cont2) {
                                        if (ptr3->next != NULL) {
                                                struct cmd *temp2 = ptr3->next;
						free(ptr3->name);
						free(ptr3);
                                                ptr3 = temp2;
                                        }
                                        else {
                                                free(ptr3->name);
						free(ptr3);
                                                cont2 = 0;
                                        }
                                }
			}
                        if (ptr->next != NULL) {
                                struct node *tempo = ptr->next;
                                free(ptr->name);
                                free(ptr);
                                ptr = tempo;
                        }
                        else {
                                free(ptr->name);
                                free(ptr);
                                break;
                        }
                }
        }
}

//function printTraversal(char* line)
//prints traversal starting from node with line
void printTraversal(char* line) {
        struct node *ptr = list_hd;
        if (ptr == NULL) {
        }
        else {
                while (1) {
                        if (ptr->next == NULL) {
                                ptr->mark = 0;
				ptr->visited_mark = 0;
                                break;
                        }
                        ptr->mark = 0;
			ptr->visited_mark = 0;
                        ptr = ptr->next;
                }
        }

        struct node *tmpnode = findNode(line);
        pot(tmpnode);
}

//function pot(struct node)
//post order traversal. hell of a function
void pot(struct node *tmpnode) {
        if (tmpnode->mark == 1) {
                return;
        }
        tmpnode->mark = 1;
	struct stat *buf = calloc(1,sizeof(struct stat));
	int status = stat(tmpnode->name,buf);
	time_t n_time = 0;
	n_time =  buf->st_mtime;
	if (status == -1 && tmpnode->target == 0) {
		 fprintf(stderr,"File pot error: file dne and n is not a target\n");
                 free_list();
		 free(buf);
                 exit(1);
	}
        struct edge *tmpnode2 = tmpnode->head;
	int run_cmd = 0;
	if (tmpnode2 != NULL) {
		/*
		for(tmpnode2; tmpnode2->next != NULL; tmpnode2 = tmpnode2->next) {
			if (tmpnode2->who->mark == 1 && tmpnode2->who->visited_mark == 0) {
                                fprintf(stderr,"Circular dependency detected, skipping\n");
				pot(tmpnode2->next->who);
                        }
			else {
				struct stat *buf2 = calloc(1,sizeof(struct stat));
				struct node *looking_tmpnode = tmpnode2->who;
				int status2 = stat(looking_tmpnode->name,buf2);
				time_t n_time_tmp = 0;
				n_time_tmp = buf2->st_mtime;
				double seconds = difftime(n_time_tmp,n_time);
        			if (status2 == -1) {
					run_cmd = 1;
				}
				else if (seconds > 0) {
					run_cmd = 1;
				}
				free(buf2);
				pot(tmpnode2->next->who);
                	}
		}
		*/
		while (1) {
			struct stat *buf2 = calloc(1,sizeof(struct stat));
                        struct node *looking_tmpnode = tmpnode2->who;
                        int status2 = stat(looking_tmpnode->name,buf2);
                        time_t n_time_tmp = 0;
                        n_time_tmp = buf2->st_mtime;
                        double seconds = difftime(n_time_tmp,n_time);
                        if (status2 == -1 || seconds > 0) {
                        	run_cmd = 1;
                        }
                        free(buf2);
                        pot(tmpnode2->who);
			if (tmpnode2->who->mark == 1 && tmpnode2->who->visited_mark == 0) {
                                fprintf(stderr,"Circular dependency detected, skipping\n");
                        }
			if (tmpnode2->next == NULL) {
                        	break;
			}
                        else {
                                tmpnode2 = tmpnode2->next;
                        }

		}
		/*
		do {
                        printf("dependency: %s\n",tmpnode2->who->name);
			if (tmpnode2->who->mark == 1 && tmpnode2->who->visited_mark == 0) {
                                fprintf(stderr,"Circular dependency detected, skipping\n");
                                if (tmpnode2->next != NULL) pot(tmpnode2->next->who);
                        }
                        else {
                                struct stat *buf2 = calloc(1,sizeof(struct stat));
                                struct node *looking_tmpnode = tmpnode2->who;
                                int status2 = stat(looking_tmpnode->name,buf2);
                                time_t n_time_tmp = 0;
                                n_time_tmp = buf2->st_mtime;
                                double seconds = difftime(n_time_tmp,n_time);
                                if (status2 == -1 || seconds > 0) {
                                        run_cmd = 1;
                                }
                                free(buf2);
                                pot(tmpnode2->who);
                        }
			if (tmpnode2->next != NULL) tmpnode2 = tmpnode2->next;
                }  while (tmpnode2->next != NULL);
		*/
	}
        if (status == -1 || run_cmd ) {
		if (tmpnode->cmd_head != NULL) {
			struct cmd *ptr3 = tmpnode->cmd_head;
                        while (ptr3 != NULL) {
				printf("%s\n",(ptr3->name));
				int result  = system(ptr3->name);
                                if (result == 0) {
                                }
                                else {
                                        fprintf(stderr,"command executuion failed");
                                        free_list();
                                	exit(1);
                                }
                        	ptr3 = ptr3->next;
                	}
			int status2 = stat(tmpnode->name,buf);
			time_t n_time = buf->st_mtime;
		}
	}
	tmpnode->visited_mark = 1;
	free(buf);
        return;
}
