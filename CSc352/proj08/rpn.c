/* File: rpn.c
 * Author: Chris bohlman
 * Purpose: Reverse polish notation
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>

struct node {
	char *value;
	char *sign;
	struct node *next;
};

struct subNum {
	char *value;
	char *sign;
};

struct node *list_hd = NULL;
int return_val = 0;

//void push(char*): pushes an int onto the stack
void push(char* val, char* sign_n) {
	struct node *temp;
	temp = malloc(sizeof(struct node));
	temp->value = strdup(val);
	temp->sign = strdup(sign_n);
	
	if (list_hd == NULL) {
		list_hd = temp;
		temp->next = NULL;
	}
	else {
		temp->next = list_hd;
		list_hd = temp;
	}
}

//char* pop(): pops the element off the pop of the stack and returns it
struct node pop() {
	struct node *ptr = list_hd;
	list_hd = ptr->next;
	struct node tmp;
	tmp.value = strdup(ptr->value);
	tmp.sign = strdup(ptr->sign);
	free(ptr->value);
	free(ptr->sign);
	free(ptr);
	return tmp;
}

//int isempty(): returns true if stuck is empty, false otherwise
int isempty() {
	if (list_hd == NULL) {
		return 1;
	}
	else {
		return 0;
	}
}

//void print_list(): prints the entire list
void print_list(struct node *ptr) {
        if (ptr == NULL) {
		printf("Stack Empty\n");
		return;
	}
	if (ptr->next != NULL){
		print_list(ptr->next);
	}
	if(strcmp(ptr->sign,"-") == 0) {
		printf("-");
	}
	
	int i = 0;
        int all_zero = 1;
        for (i = 0; i < strlen(ptr->value) - 1; i++) {
                if (ptr->value[i] != '0') {
                        all_zero = 0;
                }
        }

        if (all_zero == 1) {
                printf("0\n");
                return;
        }

	int k, lead_zero = 1;
        for (k = 0; k < strlen(ptr->value); k++) {
        	
		if (ptr->value[k] != '0') {
                	lead_zero = 0;
                        printf("%c",ptr->value[k]);
                }
               	else if (ptr->value[k] == '0' && lead_zero == 0) {
                	printf("%c",ptr->value[k]);
                }
       	}
}
//int isNum(char *line): is the line anumber
int isNum(char *line) {
	int len = strlen(line);
	if (len < 2) {
		return_val = 1;
		return 0;
	}
	int i;
	for (i = 0; i < len-1; i++) {
		if (!(isdigit(line[i]))) {
			return_val = 1;
			return 0;
		}
	}
	return 1;
}

//int isCommOp(char *line): is the line a c, u, p, or a
int isCommOp(char *line) {
        int len = strlen(line);
        if (len < 2) {
                return_val = 1;
                return 0;
        }
	if (line[0] == 'c' || line[0] == 'u' || line[0] == 'p' || line[0] == 'a') {
        	int i;
        	for (i = 1; i < len-1; i++) {
                	if (!(isspace(line[i]))) {
                        	return_val = 1;
                        	return 0;
                	}
        	}	
       		return 1;
	}
	else {
		return 0;
	}
}

//int isOp(char *line): is the line a + or -
int isOp(char *line) {
        int len = strlen(line);
        if (len < 2) {
                return_val = 1;
                return 0;
        }
        if (line[0] == '+' || line[0] == '-') {
                int i;
                for (i = 1; i < len-1; i++) {
                        if (!(isspace(line[i]))) {
                                return_val = 1;
                                return 0;
                        }
                }
                return 1;
        }
        else {
                return 0;
        }
}

//int isAddOp(char *line): is the line a + or a -
int isAddOp(char *line) {
        if (line[0] == '+') {
		return 1;
	}
	else {
		return 0;
	}
}

//int addStr(char *line): adds two strings together
char* addStr(char *line1, char *line2) {
  	int len;
	if (strlen(line1) > strlen(line2)) {
		len = strlen(line1) - 1;
	}
	else if (strlen(line1) < strlen(line2)) {
		len = strlen(line2) - 1;
	}
	else {
		len = strlen(line1) - 1;
	}
	char *answer = calloc((len+2),sizeof(char));
        int i;
        int addone = 0;
	if (strlen(line1) > strlen(line2)) {
		char l2;
		for (i = 0; i < len; i++) {
			int real_len = strlen(line2) - i - 2;
			if (real_len < 0 ) {
				l2 = 0;
			}
			else {
				l2 = line2[real_len] - '0';
			}
                	char ans = (line1[len - i - 1] - '0') + l2;
                	if(addone) {
                	        ans = ans + 1;
                	        addone = 0;
                	}
                	if (ans > 9) {
                	        ans = ans - 10;
                	        addone = 1;
                	}
                	answer[len - i - 1] = ans + '0';
        	}
		answer[len] = '\n';
	}
	else if (strlen(line1) < strlen(line2)) {
		char l1;
                for (i = 0; i < len; i++) {
			int real_len = strlen(line1) - i - 2;
                        if (real_len < 0 ) {
				l1 = 0;
                        }
                        else {
                                l1 = line1[real_len] - '0';
                        }
                        char ans = (line2[len - i - 1] - '0') + l1;
                        if(addone) {
                                ans = ans + 1;
                                addone = 0;
                        }
                        if (ans > 9) {
                                ans = ans - 10;
                                addone = 1;
                        }
                        answer[len - i - 1] = ans + '0';
                }
		answer[len] = '\n';
	}
	else {
        	for (i = len - 1; i >= 0; i--) {
        	        char ans = (line1[i] - '0') + (line2[i] - '0');
        	        if(addone) {
        	                ans = ans + 1;
        	                addone = 0;
        	        }
        	        if (ans > 9) {
        	                ans = ans - 10;
        	                addone = 1;
                	}
                	answer[i] = ans + '0';
        	}
		answer[len] = '\n';
	}
        if (addone) {
                char *answer2 = calloc((len+3),sizeof(char));
		char *extra_one = "1";
		strcat(answer2,extra_one);
		strcat(answer2,answer);
		char *return_ans = strdup(answer2);
		free(answer);
		free(answer2);
		return return_ans;
        }
	
	//check if answer is all zero
        i = 0;
        int all_zero = 1;
        for (i = 0; i < len; i++) {
                if (answer[i] != '0') {
                        all_zero = 0;
                }
        }
        if (all_zero == 1) {
		free(answer);
		char* zero_ans = "0\n";
                return strdup(zero_ans);
        }
        else {
		char *return_ans = strdup(answer);
                free(answer);
		return return_ans;
        }

}

//struct subStr(char *line1, char *line2, char *ans_sign): subtracts one string from another
struct subNum subStr(char *line1, char *line2, char *ans_sign) {
	int len;
        if (strlen(line1) > strlen(line2)) {
                len = strlen(line1) - 1;
        }
        else if (strlen(line1) < strlen(line2)) {
                len = strlen(line2) - 1;
        }
        else {
                len = strlen(line1) - 1;
        }
        char *answer = calloc((len+2),sizeof(char));
        int i;
        int subone = 0;
        if (strlen(line1) > strlen(line2)) {
                char l2;
                for (i = 0; i < len; i++) {
                        int real_len = strlen(line2) - i - 2;
                        if (real_len < 0 ) {
                                l2 = 0;
                        }
                        else {
                                l2 = line2[real_len] - '0';
                        }
                        char ans = (line1[len - i - 1] - '0') - l2;
                        if(subone) {
                                ans = ans - 1;
                                subone = 0;
                        }
                        if (ans < 0) {
                                ans = ans + 10;
                                subone = 1;
                        }
                        answer[len - i - 1] = ans + '0';
                }
                answer[len] = '\n';
        }
        else if (strlen(line1) < strlen(line2)) {
                char l1;
                for (i = 0; i < len; i++) {
                        int real_len = strlen(line1) - i - 2;
                        if (real_len < 0 ) {
                                l1 = 0;
                        }
                        else {
                                l1 = line1[real_len] - '0';
                        }
                        char ans = l1 - (line2[len - i - 1] - '0');
                        if(subone) {
                                ans = ans - 1;
                                subone = 0;
                        }
                        if (ans < 0) {
                                ans = ans + 10;
                                subone = 1;
                        }
                        answer[len - i - 1] = ans + '0';
                }
                answer[len] = '\n';
        }
        else {
                for (i = len - 1; i >= 0; i--) {
                        char ans = (line1[i] - '0') - (line2[i] - '0');
                        if(subone) {
                                ans = ans - 1;
                                subone = 0;
                        }
                        if (ans < 0) {
                                ans = ans + 10;
                                subone = 1;
                        }
                        answer[i] = ans + '0';
                }
                answer[len] = '\n';
        }
        struct subNum number;
	number.sign = "+";
        if (subone) {
        	//negative number
		char* ans_sign = "-";
		number.value = subStr(line2,line1,ans_sign).value;
		number.sign = strdup(ans_sign);
		free(answer);
		return number;
	}

        //check if answer is all zero
        i = 0;
        int all_zero = 1;
        for (i = 0; i < len; i++) {
                if (answer[i] != '0') {
                        all_zero = 0;
                }
        }
	
        if (all_zero == 1) {
		char* zero_ans = "0\n";
                number.value = strdup(zero_ans);
		free(answer);
		return number;
        }
        else {
		number.value = strdup(answer);
		free(answer);
                return number;
        }
        
}

//void free_list(): frees the list
void free_list() {
        struct node *ptr;
                while(list_hd != NULL) {
                        ptr = list_hd;
			list_hd = list_hd->next;
			free(ptr->value);
			free(ptr->sign);
			free(ptr);
                }
}

//void read_string(): reads all strings from stdin
void read_string() {
	char* line = NULL;
        size_t len = 0;
        while(getline(&line,&len, stdin) != EOF) {
        	//all the read shit here
		if (isNum(line)) {
			//store number as a string
			push(line,"+");
		}
		else if (isOp(line)) {
			if (list_hd == NULL || list_hd->next == NULL) {
				fprintf(stderr,"Not enough operators\n");
				return_val = 1;
			}
			else {
				struct node tmp = pop();
				char* op2 = tmp.value;
				char* op2sign = tmp.sign;
				tmp = pop(); 
				char *op1 = tmp.value;
				char* op1sign = tmp.sign;
				if (isAddOp(line)) {
					if (strcmp(op2sign,"+") == 0 && strcmp(op1sign,"+") == 0) {
						char* num = addStr(op2,op1);
						push(num, "+");
						free(num);
					}
					else  if (strcmp(op2sign,"-") == 0 && strcmp(op1sign,"-") == 0) {
						char* num = addStr(op2,op1);
                                                push(num, "-");
                                                free(num);
					}
					else  if (strcmp(op2sign,"-") == 0 && strcmp(op1sign,"+") == 0) {
                                                char* ans_sign = "+";
                                        	struct subNum val = subStr(op1,op2,ans_sign);
                                        	char* num  = val.value;
                                        	push(num,val.sign);
                                        	free(num);
                                        	if (strcmp(val.sign,"-") == 0) {
                                                	free(val.sign);
                                        	}
					}
					else  if (strcmp(op2sign,"+") == 0 && strcmp(op1sign,"-") == 0) {
                                                char* ans_sign = "+";
                                                struct subNum val = subStr(op2,op1,ans_sign);
                                                char* num  = val.value;
                                                push(num,val.sign);
                                                free(num);
                                                if (strcmp(val.sign,"-") == 0) {
                                                        free(val.sign);
                                                }
					}
				}
				else {
                        	        if (strcmp(op2sign,"+") == 0 && strcmp(op1sign,"+") == 0) {
                                                char* ans_sign = "+";
                                        	struct subNum val = subStr(op1,op2,ans_sign);
                                        	char* num  = val.value;
                                        	push(num,val.sign);
                                        	free(num);
						if (strcmp(val.sign,"-") == 0) {
                                                        free(val.sign);
                                                }
                                        }
                                        else  if (strcmp(op2sign,"-") == 0 && strcmp(op1sign,"-") == 0) {
						char* ans_sign = "+";
                                                struct subNum val = subStr(op2,op1,ans_sign);
                                                char* num  = val.value;
                                                push(num,val.sign);
                                                free(num);
                                                if (strcmp(val.sign,"-") == 0) {
                                                        free(val.sign);
                                                }
                                        }
                                        else  if (strcmp(op2sign,"-") == 0 && strcmp(op1sign,"+") == 0) {
                                                char* num = addStr(op2,op1);
                                                push(num, "+");
                                                free(num);
                                        }
                                        else  if (strcmp(op2sign,"+") == 0 && strcmp(op1sign,"-") == 0) {
                                                char* num = addStr(op2,op1);
                                                push(num, "-");
                                                free(num);
                                        }
				}
				free(op2);
				free(op2sign);
				free(op1);
				free(op1sign);
			}
		}
		else if (isCommOp(line)) {
			//execute command clear
			if(line[0] == 'c') {
                                while (!(isempty())) {
					struct node tmp = pop();
					free(tmp.value);
					free(tmp.sign);
				}
                        }

			//execute command undo
			if(line[0] == 'u') {
                                if (isempty()) {
					fprintf(stderr,"Nothing to undo\n");
					return_val = 1;
				}
				else {
					struct node tmp = pop();
                                        free(tmp.value);
                                        free(tmp.sign);
                        	}
			}
			
			//execute command print
			if(line[0] == 'p') {
				if (isempty()) {
                                        printf("Stack Empty\n");
                                }
                                else {
					int i = 0;
        				int all_zero = 1;
        				for (i = 0; i < strlen(list_hd->value) - 1; i++) {
                				if (list_hd->value[i] != '0') {
                        				all_zero = 0;
                				}
        				}
        				if (all_zero == 1) {
                				printf("0\n");
        				}
					else {	
						if (strcmp(list_hd->sign,"-") == 0) {
							printf("-");
						}
						int k, lead_zero = 1;
						for (k = 0; k < strlen(list_hd->value); k++) {
							if (list_hd->value[k] != '0') {
								lead_zero = 0;
								printf("%c",list_hd->value[k]);
							}
							else if (list_hd->value[k] == '0' && lead_zero == 0) {
								printf("%c",list_hd->value[k]);
							}
						}
					}
				}
			}

			//execute print all
			if(line[0] == 'a') {
                                print_list(list_hd);
                        }
		}
		else {
			fprintf(stderr,"Illegal input %s",line);
			return_val = 1;
		}
	}	
	free(line);
}

int main(void) {
        read_string();
	free_list();
        return return_val;
}
	


