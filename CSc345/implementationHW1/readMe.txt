Project: Implementation HW #1
Class: CSc 345
Author: Chris Bohlman

To Use Program: Make sure valid input file is in data folder. Remember that 
file's full file name.
Open up ImplementationHW1.pde. Make sure Button.pde, ArrayStack.pde,
and MinHeap.pde are in the same folder.
Start program with green arrow.

Example file: positions.txt included

Functionality: The program shall only be used if there is a valid file input,
 and the buttons are pressed in the order of:
 
Read File => Sort => Graham Scan (all points are green or red) => Convex Hull
*Reset can be pressed at any time*

If any buttons are pressed out of order, an error message will appear and nothing
 will happen.

Before you press any buttons, you should type out a file name to read from. Just 
start the program and start typing, and you should see the file name showing on the GUI.
If it's not showing, move the mouse and click on the center of the program window and 
start typing again. It should show up.  Once the file name is complete, press Read File.
Enter does not work. Backspace deletes one character. Delete clears file name.
You probably should not press Control/alt. It won't go over well.
 
Pressing Reset clears all global variables and restarts program. You will have to edit 
the file name again (w/ backspace) and type in another filename if you so choose.


Pressing Read File will plot all of the points on the GUI. If file name is not valid, an
error message will appear and program will be terminated.


Pressing sort will sort the points by polar angle in a heapsort, and will label 
all of the points as such. The points will also be connected, showing a simple 
polygon.It will also stick the first 3 points onto the graham scan stack, as 
evidenced by P0, p1, and p2 turning green.


Pressing the Graham Scan button will cause one iteration of the graham scan to 
occur. This is signaled by a green dot covering the next point. If any points are
popped off, that will be signified by a red dot covering them. When graham scan is
finsihed (all points are pushed or popped off the stack and highlighted green or 
red), a message wll show on saying that Graham Scan has finished.
Attempting to press the graham scan button after this will result with an error message
saying that graham scan has already completed. 


Finally, pressing the Convex Hull 
button will result in the convex hull being drawn fro the graham scan stack. Also,
a file called points.out will be generated with all of the points and their indices.

Notes:
Messages will show on System.out or System.err. Watch for that.
 