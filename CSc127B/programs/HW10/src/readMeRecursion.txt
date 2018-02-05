Chris Bohlman, CSC 127B-001B
Homework #9 - Recursion
Instructor: Patrick Homer
TA: Nathaniel Hendrix
Due Date: 11/18/2016



This collection of classes was written in order to demonstrate my ability to write recursive programming. There are 3 classes: Recursion, Link Node, and Java.

Recursion contains 7 static methods: 
gcd: which finds the greatest common divisor of two numbers
rangeSum: when given an array and two indexs, adds the total up of numbers between the indexes.
backskip: which reverses a piece of text and prints it
ackermann: it finds numbers somehow I guess
rsoarll: reverses a linked list's strings, from back to front (NEEDS LINKNODE CLASS IN THE SAME FOLDER TO WORK)
pascal's triangle: which returns any row of the pascal's triangle
equineAdventure: how many times can a knight go around a board? can it even go around the board? let's find out!

LinkNode is just a linked list node template.

And Program10 tests everything.


I used JDE 8.0_91, wrote the program through Eclipse, and was on a Windows
10 laptop. To test this program, all submitted classes must be in the same folder as each other. LINK NODE CLASS MUST BE THERE FOR THE RSOARLL PROGRAM TO FUNCTION

I couldn't get the equineAdventure program to print "No paths found" if it didn't find a path. Booleans didn't work. So instead, it just prints out nothing. Everything else works, though.