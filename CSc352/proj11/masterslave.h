/* File: masterslave.h
 * Author: Chris bohlman
 * Purpose: serve as a header file for two programs, master and slave
*/

#ifndef HEADER_FILE
#define HEADER_FILE

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#include<unistd.h>
#include<time.h>
#include<signal.h>
#include<sys/stat.h>

int return_val;
char handler_text[31][64];

void my_handler(int);

void t_handler(int);

void do_nothing(void);

#endif
