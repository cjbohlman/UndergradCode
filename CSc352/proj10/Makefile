mymake2: makefile_prog.c makefile_help.c makefile_prog.h
	gcc -c -g makefile_prog.c makefile_help.c
	gcc -Wall -g makefile_prog.o makefile_help.o -o mymake2 -L/home/cs352/spring17/Assignments/proj09 -lcs352

.PHONY: clean
clean:
	rm -f *.o a.out mymake2
