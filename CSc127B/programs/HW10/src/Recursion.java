import java.util.Arrays;

public class Recursion {
	
	public static int gcd(int x, int y) {
		if (x%y==0)
			return y;
		return gcd(y, x%y);
	}

	public static double rangeSum(double [] array, int lower, int upper) {
		if (lower < 0 || upper >= array.length) throw new IllegalArgumentException("Error: invalid parameters");
		if (upper < lower)
			return 0;
		
		if (upper - lower == 0)
			return array[upper];
		
		return array[lower] + rangeSum(array,lower+1,upper);
	}
	
	public static String backSkip(String message) {
		if (message.length() == 0)
			return "";
		if (message.length() == 1)
			return message;
		char t = (message.charAt(message.length()-1));
		String temp = Character.toString(t);
		String messagearr = message.substring(0,message.length() -2);
		return temp + backSkip(messagearr);
		
	}
	
	public static int ackermann(int m, int n) {
		if (m < 0 || n < 0) throw new IllegalArgumentException("Error: input can't be negative.");
		if (m == 0)
			return n + 1;
		if (m > 0 && n == 0)
			return ackermann(m-1,1);
		if (m > 0 && n > 0)
			return ackermann(m-1,ackermann(m, n-1));
		return 0;
	}
	
	public static String rsoarll(LinkNode<String> head) {
		String flip = "";
		
		if (head == null)
			return "";
		
		for (int i = head.getData().length()-1; i >= 0; i--) {
			flip = flip + head.getData().charAt(i);
		}
				
		return (flip+" "+rsoarll(head.getNext())).trim();
	}
	
	public static int[] pascalRow(int row) {
		int[] answer = new int[row + 1];
		if (row ==  0) {
			answer[0] = 1;
			return answer;
		}
		for (int i = 0; i < row + 1; i++) {
			if (i == 0 || i == row) {
				answer[i] = 1;
			}
			else{
				answer[i] = pascalRow(row-1)[i] + pascalRow(row-1)[i-1];
			}
		}
		return answer;
	}
	
	public static void equineAdventure( int [][] board, int row, int col ) {
		if (row < 0 || col < 0) throw new IllegalArgumentException("Error: input can't be negative.");
		board[row][col] = biggestVal(board)+1;
		int rows = board.length;
		int cols = board[0].length;		
		
		if (biggestVal(board) >= rows*cols) {
			for (int i = 0; i < rows; i ++) {
				for (int j = 0; j < cols;  j++) {
					System.out.printf("%d\t",board[i][j]);
				}
				System.out.println();
			}
			System.out.println();
		}
		
		if (row > 1 && col < cols - 1 && board[row-2][col + 1] == 0) { 
			//board[row-2][col+1] = (biggestVal(board)+1);
			equineAdventure(board, row-2, col + 1);
		}
		
		if (row > 1 && col > 0 && board[row-2][col - 1] == 0) { 
			//board[row-2][col-1] = (biggestVal(board)+1);
			equineAdventure(board, row-2, col - 1);
		}
		
		if (row < rows - 2 && col > 0 && board[row+2][col - 1] == 0) { 
			//board[row+2][col-1] = (biggestVal(board)+1);
			equineAdventure(board, row+2, col - 1);
		}
		
		if (row < rows -2 && col < cols - 1 && board[row+2][col + 1] == 0) { 
			//board[row+2][col+1] = (biggestVal(board)+1);
			equineAdventure(board, row+2, col+1);
		}
		
		if (row > 0 && col < cols - 2 && board[row-1][col + 2] == 0) {
			//board[row-1][col+2] = (biggestVal(board)+1);
			equineAdventure(board, row-1, col+2);
		}
		
		if (row > 0 && col > 1 && board[row-1][col - 2] == 0) { 
			//board[row-1][col-2] = (biggestVal(board)+1);
			equineAdventure(board, row-1, col-2);
		}
		
		if (row < rows -1 && col < cols - 2 &&  board[row+1][col + 2] == 0) { 
			//board[row+1][col+2] = (biggestVal(board)+1);
			equineAdventure(board, row+1, col+2);
		}
		
		if (row < rows -1 && col > 1 &&  board[row+1][col - 2] == 0) { 
			//board[row+1][col-2] = (biggestVal(board)+1);
			equineAdventure(board, row+1, col-2);
		}
		board[row][col] = 0;
		
		return;
	}
	
	private static int biggestVal(int [][] array) {
		int rows = array.length;
		int cols = array[0].length;
		int num = 0;
		for (int i = 0; i < rows; i ++) {
			for (int j = 0; j < cols;  j++) {
				if (array[i][j] > num) {
					num = array[i][j];
				}
			}
		}
		return num;	
	}
}
