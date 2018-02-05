/* File name: master.c
   Author: Chris Bohlman
   Purpose: To be the master
   master
   MASTER OF PUPPETS HUEGHHEHEHRHHEHRHGH
*/

#include "masterslave.h"

struct sigaction act;
extern int return_val;

int main ( void ) {
	return_val = 0;
	time_t *time_s = malloc(sizeof(time_t));
	time(time_s);
	sleep(1);
	FILE *f = fopen("slave_pid","r");
	fflush(stdout);
	int t  = 0;
		while (f == NULL || t < 10) {
			sleep(1);
			++t;
			if (f != NULL) fclose(f);
			f = fopen("slave_pid","r");
			if (f != NULL) {
				struct stat *buffer_s = calloc(1,sizeof(struct stat));
       				int status = stat("slave_pid",buffer_s);
				if (status < 0) {
					perror("Master: file status unable to be read");
                			return_val = 1;
					free(time_s);
                			exit(return_val);
				}
				time_t n_time = 0;
        			n_time =  buffer_s->st_atime;
        			if (n_time > *time_s) {
					fsync(1);
					free(buffer_s);
					break;
        			}
				free(buffer_s);
			}
		}
	if (t == 10) {
		perror("Master: file unable to be read");
		return_val = 1;
		free(time_s);
		fclose(f);
		exit(return_val);
	}
	char* pid_line = NULL;
	int slave_pid = 0;
	size_t len_pid = 0;
	getline(&pid_line,&len_pid, f);
	sscanf(pid_line,"%d",&slave_pid);
	free(pid_line);
	free(time_s);
	char* line = NULL;
        size_t len = 0;
        while(getline(&line,&len, stdin) != EOF) {
		char* buf = calloc(strlen(line)+2,sizeof(char));
		strcpy(buf,line);
		while (isspace(*buf) && strlen(buf) > 1) {
			buf++;
		}
		size_t buf_len = strlen(buf);
		
		buf = realloc(buf,buf_len+1);
		if ('@' == buf[0]) {
			//do some shit with commands
			if (buf[1] == 'c' && isspace(buf[2])) {
				do_nothing();	
			}
			else if (buf[1] == 'k' && isspace(buf[2])) {
                        	char cmd[3];
				int sig_num;
				sscanf(buf,"%s %d",cmd,&sig_num);
				pid_t sig_num_kill = slave_pid;
				fsync(1);
				sleep(1);
				int kill_num = kill(sig_num_kill,sig_num);
				if (kill_num == -1) {
					perror("Master: error with k command");
				}
				sleep(1);	
			} 
			else if (buf[1] == 's' && isspace(buf[2])) {
				int return_write = write(1,buf,buf_len);
				if (return_write == -1) {
					free(buf);
					free(line);
					return_val = 1;
					exit(return_val);
				}
				fsync(1);
			}
			else if (buf[1] == 'i' && isspace(buf[2])) {
				int return_write = write(1,buf,buf_len);
                                if (return_write == -1) {
                                        free(buf);
                                        free(line);
                                        return_val = 1;
                                        exit(return_val);

                                }
				fsync(1);
                        }
			else if (buf[1] == 't' && isspace(buf[2])) {
				int return_write = write(1,buf,buf_len);
                                if (return_write == -1) {
                                        //ERROR
                                        free(buf);
                                        free(line);
                                        return_val = 1;
                                        exit(return_val);
                                }
				fsync(1);
                        }
			else if (buf[1] == 'r' && isspace(buf[2])) {
				int return_write = write(1,buf,buf_len);
                                if (return_write == -1) {
                                        //ERROR
                                        free(buf);
                                        free(line);
                                        return_val = 1;
                                        exit(return_val);
                                }
				fsync(1);
                        }
			else {
				perror("Master: Error in command processing");
				return_val = 1;
			}
		}
		else {
			int line_len = strlen(line);
			int return_write = write(1,line,line_len);
                        if (return_write == -1) {
                                        free(buf);
                                        free(line);
                                        return_val = 1;
                                        exit(return_val);

                        }
			fsync(1);
		}
		free(buf);
	}
	free(line);
	fclose(f);
	return return_val;
}
