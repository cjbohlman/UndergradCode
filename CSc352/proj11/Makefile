master: 
	gcc -g -Wall -o master master.c help.c masterslave.h
slave:
	gcc -g -Wall -o slave slave.c masterslave.h

all:
	gcc -g -Wall -o master master.c help.c masterslave.h
	gcc -g -Wall -o slave slave.c masterslave.h

.PHONY: clean
clean:
	rm -f *.o a.out master slave
