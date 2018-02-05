/* File: strmath.c
 * Author: Chris Bohlman
 * Purpose: Take two embarrasing long strings and either adds them together or subtracts them.
 */

 #include<stdio.h>
 #include<stdlib.h>
 #include<string.h> 
 #include<ctype.h>

 void add_str(char *line1, char *line2, int length1, char* answer);
 void sub_str(char *line1, char *line2, int length1, char* answer);


 int main(void) {
	char* operator = NULL;
        size_t len_op = 0;
        getline(&operator,&len_op, stdin);
	int op_length = strlen(operator);
	int add = 0,sub = 0;
	if (op_length == 4 && operator[0] == 'a' && operator[1] == 'd' && operator[2] == 'd') {
		add = 1;
	}
	
	else if (op_length == 4 && operator[0] == 's' && operator[1] == 'u' && operator[2] == 'b') {
                sub = 1;
        }
	
	else{
		fprintf(stderr,"Error: 1st line not equal to 'add' or 'sub'\n");
		return 1;
	}
	char* line = NULL;
        size_t len = 0;
        int errval = getline(&line,&len, stdin);
	if (errval == -1) {
		fprintf(stderr,"Error: not enough input\n");
                return 1;
	}	
	int length1 = strlen(line);
	if (length1 == 1) {
		fprintf(stderr,"Error: line does not contain any digits\n");
		return 1;
	}
	int j;
	for (j = 0; j < length1-1; j++) {
		if (!(isdigit(line[j]))) {
			fprintf(stderr,"Error: Non-integer character in input\n");
			return 1;
		}
	}

	char* line2 = NULL;
        size_t len2 = 0;
	int errval2 = getline(&line2,&len2, stdin);
	if (errval2 == -1) {
                fprintf(stderr,"Error: not enough input\n");
                return 1;
        }
	int length2 = strlen(line2);
	if (length2 == 1) {
                fprintf(stderr,"Error: line does not contain any digits\n");
                return 1;
        }
	for (j = 0; j < length2-1; j++) {
                if (!(isdigit(line2[j]))) {
                        fprintf(stderr,"Error: Non-integer character in input\n");
                        return 1;
		}
	}
	if (length1 > length2) {
		char new_line2[length1];
		new_line2[length1 - 1] = '\n';
		int k;
		int l = 2;
		for(k = length2 - 2; k >= 0; k--) {
			new_line2[length1 - l] = line2[k];
			l++;
		}
		int fill_length = length1 - length2;
		int o;
		for (o = 0; o < fill_length; o++) {
			new_line2[o] = '0';
		}
		char* answer;
		answer=malloc((length1+1)*sizeof(char));
                if (add) {
                        add_str(line,new_line2,length1,answer);
                }

                if (sub) {
                        sub_str(line,new_line2,length1,answer);
                }
		//printf("\n");
                return 0;
	}
	else if (length2 > length1) {
		char new_line[length2];
		new_line[length2 - 1] = '\n';
                int k;
                int l = 2;
                for(k = length1 - 2; k >= 0; k--) {
                        new_line[length2 - l] = line[k];
                        l++;
                }
                int fill_length = length2 - length1;
                int o;
                for (o = 0; o < fill_length; o++) {
                        new_line[o] = '0';
                }
                char* answer;
                answer=malloc((length2+1)*sizeof(char));

                if (add) {
                        add_str(new_line,line2,length2,answer);
                }

                if (sub) {
                        sub_str(new_line,line2,length2,answer);
                }
		//printf("\n");
		return 0;	
	}
		
	else{	
		char* answer;
                answer=malloc((length1+1)*sizeof(char));

		if (add) {
			add_str(line,line2,length1,answer);
		}

		if (sub) {
			sub_str(line,line2,length1,answer);
        	}
		//printf("\n");	
		return 0;
	}
}
//void add_str(char*, char*, int, char*
//takes in both character arrays to add, the length of both arrays, and the answer array to store the result in.
//Adds the two strings together as ints
void add_str (char* line, char* line2, int length1, char* answer) {
	int i;
        int addone = 0;
        for (i = length1 - 2; i >= 0; i--) {
        	char ans = (line[i] - '0') + (line2[i] - '0');
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
	if (addone) {
        	printf("1");
	}
	i = 0;
        int all_zero = 1;
        answer[length1-1] = '\n';
        for (i = 0; i < length1 -1; i++) {
        	if (answer[i] != '0') {
	                all_zero = 0;
                }
        }
        if (all_zero == 1) {
        	printf("%c\n",'0');
        }
        else {
        	i = 0;
                while(answer[i] == '0' && length1 > 2) {
                	i++;
                }
                for (i = i; i < length1; i++) {
                	printf("%c",answer[i]);
                }
	}
}	
//void sub_str(char*, char*, int, char*
//takes in both character arrays to add, the length of both arrays, and the answer array to store the result in.
//Subtracts the two strings together as ints

void sub_str (char* line, char* line2, int length1, char* answer) {
	int i;
        int subone = 0;
        for (i = length1 - 2; i >= 0; i--) {
        	char ans = (line[i] - '0') - (line2[i] - '0');
                if(subone) {
                	ans = ans - 1;
                        	subone = 0;
                        }
                        if (ans < 0) {
                                ans = ans+10;
                                subone = 1;
                        }
                        answer[i] = ans + '0';
	}
        if(subone) {
        	subone = 0;
                printf("-");
                sub_str(line2,line,length1,answer);
	}
	else {
		i = 0;
		int all_zero = 1;
		answer[length1-1] = '\n';
		for (i = 0; i < length1 -1; i++) {
                	if (answer[i] != '0') {
				all_zero = 0;
			}
                }
		if (all_zero == 1) {
			printf("%c\n",'0');
		}
		else {
			i = 0;
	        	while(answer[i] == '0' && length1 > 2) {
        		        i++;
       			}
			for (i = i; i < length1; i++) {
        			printf("%c",answer[i]);
                	}
        	}
	}
}		

