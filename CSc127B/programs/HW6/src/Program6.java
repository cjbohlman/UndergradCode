/*-------------------------------------------------------------------
 Class Program6
 Chris Bohlman
 Inherits from: None
 Package Contained In: None
 
 Purpose: takes a MazeHolder object and generates a maze with it
 
 Instance Variables: (through command line) number of rows rows, number of columns columns
 
 Class Methods:
 main(String args[])
 
 Instance Methods:
 n/a
 -------------------------------------------------------------------*/

public class Program6 {
	private static int rows;
	private static int columns;

	//main method: creates a MazeHolder object and generates a maze
	public static void main(String args[]) {
		//Through command line, also gotta catch those exceptions
		rows = Integer.parseInt(args[0]);
		if (rows < 1) {
			throw new IllegalArgumentException("Sorry, number of rows input ("+rows+") is not acceptable.\n");
		}
		columns = Integer.parseInt(args[1]);
		if (columns < 1) {
			throw new IllegalArgumentException("Sorry, number of columns input ("+columns+") is not acceptable.\n");
		}

		//creates new object
		MazeHolder maze = new MazeHolder(rows, columns);
		//method to generate maze
		maze.mazeGenerate();
	}
}