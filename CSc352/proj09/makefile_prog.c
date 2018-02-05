/* File: makefile_prog.c
   Author: Chris Bohlman
   Purpose: creates adjancency list
*/

#include "makefile_prog.h"
#include "/home/cs352/spring17/Assignments/proj09/cs352.h"

extern int return_val;
struct node *lastTarget = NULL;

int main (int argc, char *argv[]) {
	if (argc > 2) {
		FILE *file = fopen(argv[1], "r");
		char line[1024];
        	if (file) {
                	while (fgets(line,1024, file) != NULL) {
                	        readLine(line,file);
                	}
			fclose(file);
			char* findLine = strdup(argv[2]);
			findLine = trimwhitespace(findLine);
			if (findNode(findLine) != NULL) {
				printTraversal(argv[2]);
				free_list();
			}
			else {
				free_list();
				fprintf(stderr,"Target %s not found\n",argv[2]);
				exit(1);
			}
			free(findLine);
        	}	
        	else {
        		fprintf(stderr,"ERROR: File does not exist or is not readable\n");
                	return_val = 1;
        	}
	}
	else {
		fprintf(stderr,"ERROR: Not enough arguments\n");
                return_val = 1;
	}
	return return_val;
	
}

//function readline(char* line)
//reads in the given line
void readLine (char* line, FILE *file) {
	char* buf = line;
	int notspace = 0;
        char *space_line = buf;
        while (*space_line != '\0') {
        	if (!isspace(*space_line))
        		notspace = 1;
        	space_line++;
        }
	if (!notspace) {
		return;
	}
	
	else if (*buf == '\t') {
		//printf("*%s",buf);
		if (lastTarget == NULL) {
			fprintf(stderr,"Error in file, command with no target\n");
                        free_list();
                        fclose(file);
                        exit(1);
		}
		struct node *tmpnode = lastTarget;
		struct cmd *tmpnode2 = cs352_malloc(sizeof(struct cmd));
		if(tmpnode2 == NULL) {
                	fprintf(stderr,"Out of memory!\n");
			free_list();
			fclose(file);
                	exit(1);
        	}
		char* buf_line = ++buf;
		tmpnode2->name = cs352_strdup(trimwhitespace(buf_line));
		if(tmpnode2->name == NULL) {
                        fprintf(stderr,"Out of memory!\n");
                        free_list();
			fclose(file);
                        exit(1);
                }
		tmpnode2->next = NULL;
		if (tmpnode->cmd_head == NULL) {
			tmpnode->cmd_head = tmpnode2;
		}
		else {
			struct cmd *ptr3 = tmpnode->cmd_head;
			while (ptr3->next != NULL) {
				ptr3 = ptr3->next;
			}
			ptr3->next = tmpnode2;
		}
	}
	else {
		while (isspace(*buf)) {
                	buf++;
        	}
		char *newline = strchr( buf,'\n');
		if (newline) {
  			*newline = 0;
		}
		char *tabline = strchr( buf,'\t');
                if (tabline) {
                        *tabline = ' ';
                }
		const char col = ':';
		const char *ret = strchr(buf,col);
		if (ret != NULL) {
			const char col[2] = ":";
			char *tmpline = strtok(buf,col);
			struct node *tmpnode = NULL;
			if (!nodeExists(trimwhitespace(tmpline))) {
				tmpnode = cs352_malloc(sizeof(struct node));
				if(tmpnode == NULL) {
                			fprintf(stderr,"Out of memory!\n");
                			free_list();
					fclose(file);
					exit(1);
        			}
				tmpnode->name = cs352_strdup(trimwhitespace(tmpline));
				if(tmpnode->name == NULL) {
                        		fprintf(stderr,"Out of memory!\n");
                        		free_list();
					fclose(file);
                        		exit(1);
                		}
				tmpnode->mark = 0;
				tmpnode->target = 1;
				tmpnode->next = NULL;
				tmpnode->head = NULL;
				tmpnode->cmd_head = NULL;
				if (list_hd == NULL) {
					list_hd = tmpnode;
				}
				else {
					struct node *ptr = list_hd;
					while (1) {
						if (ptr->next == NULL) {
							ptr->next = tmpnode;
							break;
						}
						ptr = ptr->next;
					}
				}
			}
			else if ( findNode(trimwhitespace(tmpline))->target == 1) {
				fprintf(stderr,"Repeated target: %s\n",tmpline);
                                free_list();
				fclose(file);
                                exit(1);	
			}
			else {
				tmpnode =  findNode(trimwhitespace(tmpline));
				tmpnode->target = 1;
			}
			lastTarget = tmpnode;
			//add target
			while (tmpline != NULL) {
				const char spa[2] = " ";
				tmpline = strtok(NULL,spa);
				if (tmpline != NULL) {
					int i;
					for (i = 0; i < strlen(tmpline); i++) {
						if (isspace(*tmpline)) tmpline++;
					}
				}
				if (tmpline != NULL) {
					if (trimwhitespace(tmpline) != NULL) {
					if (nodeExists(trimwhitespace(tmpline))) {
						struct edge *tmpnode2 = cs352_malloc(sizeof(struct edge));
						if(tmpnode2 == NULL) {
                					fprintf(stderr,"Out of memory!\n");
                					free_list();
							fclose(file);
							exit(1);
        					}		
						tmpnode2->who = findNode(trimwhitespace(tmpline));
						tmpnode2->next = NULL;
						if (tmpnode->head == NULL) {
							tmpnode->head = tmpnode2;
						}
						else {
							struct edge *ptr2 = tmpnode->head;
							while (1) {
								if (ptr2->next == NULL) {
									ptr2->next = tmpnode2;
									break;
								}
								ptr2 = ptr2->next;
							}
						}
					}
					
					if (!nodeExists(tmpline)) {
						struct node *newNode = cs352_malloc(sizeof(struct node));
						if(newNode == NULL) {
                					fprintf(stderr,"Out of memory!\n");
                					free_list();
							fclose(file);
							exit(1);
        					}
						newNode->next = NULL;
						newNode->name = cs352_strdup(tmpline);
						if(newNode->name == NULL) {
                        				fprintf(stderr,"Out of memory!\n");
                        				free_list();
							fclose(file);
                        				exit(1);
                				}
						newNode->mark = 0;
						newNode->target = 0;
						newNode->cmd_head = NULL;
						newNode->head = NULL;
						if (tmpnode->next == NULL) {
							tmpnode->next = newNode;
						}
						else {
							struct node *ptr3 = tmpnode->next;
                                                        while (1) {
                                                                if (ptr3->next == NULL) {
                                                                        ptr3->next = newNode;
                                                                        break;
                                                                }
                                                                ptr3 = ptr3->next;
                                                        }
						}
						struct edge *tmpnode2 = cs352_malloc(sizeof(struct edge));
                                                if(tmpnode2 == NULL) {
                					fprintf(stderr,"Out of memory!\n");
                					free_list();
							fclose(file);
							exit(1);
        					}
						tmpnode2->next = NULL;
						tmpnode2->who = newNode;
                                                if (tmpnode->head == NULL) {
                                                	tmpnode->head = tmpnode2;
                                                }
                                                else {
                                                	struct edge *ptr2 = tmpnode->head;
                                                        while (1) {
                                                        	if (ptr2->next == NULL) {
                                                                	ptr2->next = tmpnode2;
                                                                	break;
								}
                                                                ptr2 = ptr2->next;
                                                        }
                                                }
					}
					}
				}
			}
		}
		else {
			int notspace = 0;
			char *space_line = buf;
			while (*space_line != '\0') {
				if (!isspace(*space_line))
      					notspace = 1;
   				space_line++;
  			}
  			if (notspace) {
				fprintf(stderr,"Illegal line: %s\n",buf);
				free_list();
				fclose(file);
				exit(1);
			}
		}
	}
}
