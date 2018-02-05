/*-------------------------------------------------------------------
 Class Program7
 Chris Bohlman
 Inherits from: None
 Package Contained In: java.io*, java.util.Scanner
 
 Purpose: has main method to run the boundary fill of the object
 
 Instance Variables: 
 String rows_and_cols[]
 String name
 String rows_cols
 char character
 BufferedReader input
 
 Class Methods: 
 the main method is the only method here
 
 Instance Methods:
 n/a
 -------------------------------------------------------------------*/
//import packages
import java.io.*;
import java.util.Scanner;

public class Program7 {

	//instance variables
	private static String rows_and_cols[] = null;
	private static String name = "";
	private static String rows_cols = "";
	private static char character;
	public static BufferedReader input = null;

	//main method
	public static void main(String args[]) throws Exception {
		try {
			input = new BufferedReader(new InputStreamReader(System.in));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		int start_row = 0;
		int start_col = 0;

		if (args.length != 4) {
			System.out.println("Enter the name of the image file: ");
			name = input.readLine();
			System.out.println("Enter the row,col starting location of the fill area, comma-separated: ");
			rows_cols = input.readLine();
			System.out.println("Enter the character to be used for filling: ");
			character = (char) input.read();

			rows_and_cols = rows_cols.split(",");
			start_row = Integer.parseInt(rows_and_cols[0]);
			start_col = Integer.parseInt(rows_and_cols[1]);

		} else {
			name = args[0];
			start_row = Integer.parseInt(args[1]);
			start_col = Integer.parseInt(args[2]);
			character = args[3].charAt(0);
		}

		Scanner sc = new Scanner(new FileReader(name));

		String num_rows_cols = sc.nextLine();
		String number_rc[] = num_rows_cols.split(" ");

		int number_rows = Integer.parseInt(number_rc[0]);
		int number_cols = Integer.parseInt(number_rc[1]);

		char[][] infile = new char[number_rows][number_cols];

		for (int i = 0; i < number_rows; i++) {
			String line = sc.nextLine();
			for (int j = 0; j < number_cols; j++) {
				char ch = line.charAt(j);
				infile[i][j] = ch;
			}
		}

		for (int i = 0; i < number_rows; i++) {
			for (int j = 0; j < number_cols; j++) {
				System.out.print(infile[i][j]);
			}
			System.out.println();
		}

		System.out.println();

		System.out.println("Starting Location: (" + start_row + "," + start_col + ")");
		System.out.println("Character at location (and to be replaced): " + infile[start_row][start_col]);
		System.out.println("Character to fill specified area with: " + character);

		CS127BQueue loc_queue = new CS127BQueue();
		
		Location start = new Location(start_row, start_col);
		loc_queue.enqueue(start);
		
		char ch = infile[start_row][start_col];
		System.out.println(infile[0][2]);
		int row;
		int col;
		int next;
		int enqueue_count = 1;//starts at one because start row is already enqueued, and that is basis for incrementing this variable
		int pixel_fill = 0;
		while (!loc_queue.isEmpty()) {
			next = 0;
			row = loc_queue.peek().getRow();
			col = loc_queue.peek().getColumn();
			while ((col + next) != 20 && infile[row][col + next] == ch) {
				infile[row][col + next] = character;
				pixel_fill = pixel_fill + 1;
				if (row != 11 && infile[row + 1][col + next] == ch) {
					int new_col = col + next;
					while (new_col != 0 && infile[row + 1][new_col - 1] == ch) {
						new_col = new_col - 1;
					}
					Location loc = new Location(row + 1, new_col);
					if (!loc_queue.contains(loc)) {
						loc_queue.enqueue(loc);
						enqueue_count = enqueue_count + 1;
					} 
				}
				
				if (row != 0 && infile[row - 1][col + next] == ch) {
					int new_col = col + next;
					while (new_col != 0 && infile[row - 1][new_col - 1] == ch) {
						new_col = new_col - 1;
					}
					Location loc = new Location(row - 1, new_col);
					if (!loc_queue.contains(loc)) {
						loc_queue.enqueue(loc);
						enqueue_count = enqueue_count + 1;
					}
				}
				
				next = next + 1;
			}
			loc_queue.dequeue();
			System.out.println("Queue: " + loc_queue.printAll());
		}
		System.out.println();

		for (int i = 0; i < number_rows; i++) {
			for (int j = 0; j < number_cols; j++) {
				System.out.print(infile[i][j]);
			}
			System.out.println();
		}
		System.out.println("Spans filled: " +enqueue_count);
		System.out.println("Total pixels filled: " +pixel_fill);
		
	}
}
