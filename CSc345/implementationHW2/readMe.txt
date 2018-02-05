Project: Implementation HW #2
Class: CSc 345
Author: Chris Bohlman

To Use Program: Make sure valid input file is in data folder. Remember that file's full file name.
Open up bohlman_implementationhw2.pde. Make sure Button.pde, Node.pde, Segment.pde, QuadTree.pde, and insert_query.pde are in the same folder.
Start program with green arrow.

Example file: positions.txt included

Functionality: The program shall only be used if there is a valid file input. For example, if the file contains extraneous new lines/new line characters, the program will not work.

Before you press any buttons, you should type out a file name to read from. Just 
start the program and start typing, and you should see the file name showing on the GUI.
If it's not showing, move the mouse and click on the center of the program window and 
start typing again. It should show up above the readFile button.  Once the file name is complete, press Read File.
Enter does not work. Backspace deletes one character. Delete clears file name.
You probably should not press Control/alt. It won't go over well.

Once the file is read, the program will display all segments in the file. It will also show the quad tree constructed from the segments on the display.
On the GUI part, the program will display the amount of segments currently shown, the amount of nodes in the quad tree, and the states of Animation Mode, Insert Mode, and Report Mode.
The program should start with animation mode on.

Pressing 'i' brings up insert mode. If insert mode is on, and the display is clicked, the program will add a segment at that point. Note that the segment will be a dot. The program will
show the dot, and show the updated quad tree as a result of inputting that segment.
If animation mode is on, the program will highlight the nodes that the segment had to go through to get inserted for 5 seconds.
If click is on an existed segment, program will throw an error.

Pressing 'r' brings up report mode. If report mode is on, a dialog box will come up asking for points for a query. TO make the query, click on the display in the correct order. 
WARNING: you must report the query correctly, with the bottom left corner first, and then the top right corner immediately afterwards. If you do the wrong order, the query will 
show up, but it will not hightlight the points correctly. 
I reiterate, you MUST click on the query in the correct order: LOWER LEFT AND THEN UPPER RIGHT
The query will show up as a blue box, and the segments inside should be highlighted.
If animation mode is turned on, all nodes that were visited in the query reporting are highlighted for 5 seconds.
To ask the program to report another query, you can press 'r' again, OR you can click a point again. That point will be the lower left node of a new query. This was mandatory in the spec.

To turn off animtation mode, press 'a'.

Notes:
The program will throw an error if the insertion of a segment is on another segment
Error messages will show on System.err.
While the program was tested and does work with files h<9 or h>9, preferably, the program is tested with files that say h = 9. 
Note that segments are always printed first, and then boundaries.

Bugs:
If the read file contains certain invalid inputs (like a segment completely encased in another segment: 1, 4, 3 & 2, 3, 3), the program could potentially throw a stack overflow error.
Note that the input has to be invalid for this to occur, and that the program has some built in error checks. Otherwise, the program is fine.
This bug is shown with the 3.in file posted on Piazza, where there are duplicate segments.