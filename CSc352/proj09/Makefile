mymake: makefile_prog.c makefile_help.c makefile_prog.h
	gcc -c makefile_prog.c makefile_help.c
	gcc -Wall  makefile_prog.o makefile_help.o -o mymake -L/home/cs352/spring17/Assignments/proj09 -lcs352

.PHONY: coverage
coverage:
	gcc -Wall -g -fprofile-arcs -ftest-coverage makefile_prog.c makefile_help.c -o mymake -L/home/cs352/spring17/Assignments/proj09 -lcs352

.PHONY: clean
clean:
	rm -f *.o *.gch a.out mymake *.gcov *.gcno *.gcda
