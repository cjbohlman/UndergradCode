/*  File name:  slave.c
   Author: Chris Bohlman
   Purpose: To obery the master
   master
   MASTER OF PUPPETS HUEGHHEHEHRHHEHRHGH
   handles signals tho from master
*/

#include "masterslave.h"

struct sigaction act;
extern int return_val;
extern char handler_text[31][64];

//void my_handler(int n)
//handles the @s command
void my_handler(int n) {
        printf("%s\n",handler_text[n]);
}

//void t_handler(int n)
//handles the @t command
void t_handler(int n) {
        exit(return_val);
}

int main ( void ) {
	sleep(2);
	FILE *fp = fopen("slave_pid","w+");
	if (fp == NULL) {
		exit(1);
	}
	fprintf(fp,"%d",getpid());
	fclose(fp);
	char* line = NULL;
        size_t len = 0;
        while(getline(&line,&len, stdin) != EOF) {
                char* buf = strdup(line);
		if (buf[0] == '@') {
                	if (buf[1] == 's') {
				char cmd_line[3];
				int sig_num;
				char write_test[64];
				sscanf(buf,"%s %d %64s",cmd_line,&sig_num,write_test);
				strcpy(handler_text[sig_num],write_test);
				act.sa_flags = 0;         // Or, perhaps it is already 0
				act.sa_flags |= SA_RESTART; // Written in a way that does not interfere with other flags. 
				act.sa_handler = my_handler;
				sigemptyset(&act.sa_mask);
				if (sigaction(sig_num,&act,NULL) == -1) {
					perror("Slave: bad s command\n");
					return_val = 1;
				}
				fsync(1);
                	}
                	else if (buf[1] == 'i') {
                		char cmd_line[3];
                                int sig_num;
                                sscanf(buf,"%s %d",cmd_line,&sig_num);
                                act.sa_flags = 0;         // Or, perhaps it is already 0
                                act.sa_flags |= SA_RESTART; // Written in a way that does not interfere with other flags.
                                act.sa_handler = SIG_IGN;
                                sigemptyset(&act.sa_mask);
                                if (sigaction(sig_num,&act,NULL) == -1) {
                                        perror("Slave: bad i command\n");
					return_val = 1;
                                }
				fsync(1);
			}
               		else if (buf[1] == 't') {
                                char cmd_line[3];
                                int sig_num;
                                sscanf(buf,"%s %d",cmd_line,&sig_num);
                                act.sa_flags = 0;         // Or, perhaps it is already 0
                                act.sa_flags |= SA_RESTART; // Written in a way that does not interfere with other flags.
                                act.sa_handler = t_handler;
                                sigemptyset(&act.sa_mask);
                                if (sigaction(sig_num,&act,NULL) == -1) {
                                        perror("Slave: bad t command\n");
					return_val = 1;
                                }
				fsync(1);
                	}
                	else if (buf[1] == 'r') {
				char cmd_line[3];
                                int sig_num;
                                sscanf(buf,"%s %d",cmd_line,&sig_num);
                                act.sa_flags = 0;         // Or, perhaps it is already 0
                                act.sa_flags |= SA_RESTART; // Written in a way that does not interfere with other flags.
                                act.sa_handler = SIG_DFL;
                                sigemptyset(&act.sa_mask);
				if (sigaction(sig_num,&act,NULL) == -1) {
                                        perror("Slave: bad r command\n");	
					return_val = 1;
                                }
				fsync(1);
                	}
                	else {
                		perror("Slave: Error in command processing\n");
                	        return_val = 1;
				free(buf);
				free(line);
				exit(return_val);
                	}
		}
		else {
			printf("%s",buf);
			fflush(stdout);
			fsync(1);
		}
		free(buf);
	}
	free(line);
        return return_val;
}
