/*-------------------------------------------------------------------
 Class MazeHolder
 Chris Bohlman
 Inherits from: None
 Package Contained In: java.util.*
 
 Purpose: generates a maze based off of several procedures
 
 Instance Variables: rows r, columns c
 
 Class Methods: n/a
 
 Instance Methods:
 mazeGenerate
 -------------------------------------------------------------------*/
//import package
import java.util.*;

public class MazeHolder {
	//instance variables
	private int r;
	private int c;

	//constructor: accepts input of desired number of rows and desired number of columns
	public MazeHolder(int rows, int columns) {
		this.r = rows;
		this.c = columns;
	}

	/* method: mazeGenerate()
	 * Explanation: 
	 * picks a starting point
	 * assigns a maze array with dimensions of 2r+1 and 2c+1
	 * fills that array up with character "#"
	 * pushes starting point onto arraylist, marks point on maze array with " "
	 * 
	 * enters a while loop that runs while arraylist is not empty
	 * peeks at top of arraylist for location
	 * determines which directions (up, down, left, or right) it can successfully cut (i.e. not out of bounds and not already visited)
	 * if it can't go anywhere, pops item off top of stack
	 * picks a random direction where to go
	 * Successful direction picked will have it's path cut and it's location pushed on stack
	 * Rinse and repeat until it can't go anywhere
	 * Cut out random entrance and exit in maze array
	 * print fully generated maze array
	 *****************/
	public void mazeGenerate() {
		ArrayStack stack = new ArrayStack();
		Random rand = new Random();
		int posx = 0;
		int posy = 0;
		int start_r = rand.nextInt(r);
		int start_c = rand.nextInt(c);
		int x = 2 * r + 1;
		int y = 2 * c + 1;
		posx = 2 * start_r + 1;
		posy = 2 * start_c + 1;
		String[][] maze = new String[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				maze[i][j] = "#";
			}
		}

		Location start = new Location(posx, posy);
		stack.itemPush(start);

		maze[posx][posy] = " ";

		int direction = 0;

		while (!stack.isEmpty()) {
			boolean up = false;
			boolean down = false;
			boolean left = false;
			boolean right = false;

			Location l = stack.topPeek();

			posx = l.getRow();
			posy = l.getColumn();

			if (posx > 2 && maze[posx - 2][posy] != " ")
				up = true;
			if (posx < x - 3 && maze[posx + 2][posy] != " ")
				down = true;
			if (posy > 2 && maze[posx][posy - 2] != " ")
				left = true;
			if (posy < y - 3 && maze[posx][posy + 2] != " ")
				right = true;

			boolean noGo = false;

			if (!up && !down && !left && !right) {
				noGo = true;
			}

			// if it can go in a direction
			while (!noGo) {
				direction = rand.nextInt(4);
				if (direction == 0 && up && maze[posx - 2][posy] != " ") {
					Location newl = new Location(posx - 2, posy);
					stack.itemPush(newl);
					maze[posx - 1][posy] = " ";
					maze[posx - 2][posy] = " ";
					break;
				} else if (direction == 1 && down && maze[posx + 2][posy] != " ") {
					Location newl = new Location(posx + 2, posy);
					stack.itemPush(newl);
					maze[posx + 1][posy] = " ";
					maze[posx + 2][posy] = " ";
					break;
				} else if (direction == 2 && left && maze[posx][posy - 2] != " ") {
					Location newl = new Location(posx, posy - 2);
					stack.itemPush(newl);
					maze[posx][posy - 1] = " ";
					maze[posx][posy - 2] = " ";
					break;
				} else if (direction == 3 && right && maze[posx][posy + 2] != " ") {
					Location newl = new Location(posx, posy + 2);
					stack.itemPush(newl);
					maze[posx][posy + 1] = " ";
					maze[posx][posy + 2] = " ";
					break;
				}
			} // end pick while

			if (noGo) {
				stack.itemPop();
			}

		} // end empty while

		int ent_r = rand.nextInt(r);
		int exit_r = rand.nextInt(r);

		int entrance = 2 * ent_r + 1;
		int exit = 2 * exit_r + 1;

		maze[entrance][0] = " ";
		maze[exit][y - 1] = " ";

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}
}
