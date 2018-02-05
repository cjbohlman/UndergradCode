Chris Bohlman, CSC 127B-001B
Homework #7 - Fun with Boundary filling
Instructor: Patrick Homer
TA: Nathaniel Hendrix
Due Date: 10/21/2016


This collection of classes was written in order to generate a maze based off of user input.
The classes include Program7, CS127BQueue, CS127BQueueInterface, and Location.

First, Program7 got either command line input or user input for the file name, the starting 
point, and the character to fill in with. The file is read into the program, along with each 
of the values in theiir own seperate variables. Every character in the file is loaded up into 
a 2 dimensional array. Next, a new queue is made, implementing the 
interface, and that starting location is enqueued onto said queue. The queue is made up of 
location objects, and the starting row is filled, while checking to see if the character that 
is being replaced exists above or below the row that is currently being worked on. If there is, 
those locations are enqueued at the beginning of their rows as well. After a row is filled, the 
location object that pointed to said row is dequeued. The filling stops when the queue is 
empty, and the new filled file is printed out, along with some other information.

I used JDE 8.0_91, wrote the program through Eclipse, and was on a Windows
10 laptop. To test this program, all submitted classes 
must be in the same folder as each other.

No known bugs exist, as I spent much time writing this and checking several different places 
in the sample file. However, I was having some difficulties with subitting certain characters 
that I wanted to fill the space. FOr example, I submitted an '@', but the boundaries got 
filled with '"' instead. However, that is not the code's fault, I suppose it has something 
to do with my computer.

Also, my queue is out of order when compared to the example. However, everything it prints is 
still technically right, just in the wrong order.

This was a hard program. I tried.