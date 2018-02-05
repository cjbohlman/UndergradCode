import java.util.Arrays;

public class Program10 {
	
	public static void main(String args[]) {
		//testing greatest common denominator
		System.out.println("Testing GCD method:");
		System.out.println(Recursion.gcd(12,15));
		System.out.println(Recursion.gcd(7,143));
		System.out.println(Recursion.gcd(52,65));
		System.out.println();
		
		
		//testing range sum
		System.out.println("Testing range sum:");
		double[] array = new double[7];
		array[0] = 7;
		array[1] = -2;
		array[2] = 4;
		array[3] = 0;
		array[4] = 8;
		array[5] = -1;
		array[6] = 2;
		
		System.out.println(Recursion.rangeSum(array,1,4));
		System.out.println(Recursion.rangeSum(array,5,5));
		System.out.println(Recursion.rangeSum(array,6,5));
		System.out.println();
		
		
		//testing reversing alternate characters
		System.out.println("Testing reverse alternate characters:");
		String phrase = "Anything you do in life will be insignificant, but it is very important that you do it. Mahatma Gandhi";
		String result = Recursion.backSkip(phrase);
		System.out.println(result);
		System.out.println();
		
		
		//testing ackermann function
		System.out.println("Testing ackermann function:");
		int a_result = Recursion.ackermann(0, 0);
		System.out.println(a_result);
		int b_result = Recursion.ackermann(2, 4);
		System.out.println(b_result);
		//These next two lines are supposed to throw an illegal arguement exception
		//int c_result = Recursion.ackermann(-1, 1);
		//System.out.println(c_result);
		System.out.println();

		
		//testing pascal's triangle
		System.out.println("Testing pascal's triangle:");
		int[] l_result = Recursion.pascalRow(1);
		System.out.println(Arrays.toString(l_result));
		int[] m_result = Recursion.pascalRow(2);
		System.out.println(Arrays.toString(m_result));
		int[] n_result = Recursion.pascalRow(3);
		System.out.println(Arrays.toString(n_result));
		int[] o_result = Recursion.pascalRow(4);
		System.out.println(Arrays.toString(o_result));
		int[] p_result = Recursion.pascalRow(5);
		System.out.println(Arrays.toString(p_result));
		System.out.println();
		
		//testing rsoarll
		System.out.println("Testing rsoarll: ");
		
		LinkNode<String> head = new LinkNode<String>("stressed");
		LinkNode<String> node1 = new LinkNode<String>("diaper");
		LinkNode<String> node2 = new LinkNode<String>("palindromes");

		head.setNext(node1);
		node1.setNext(node2);
		
		String reversed = Recursion.rsoarll(head);
		System.out.println(reversed);
		System.out.println();
		
		LinkNode<String> head0 = new LinkNode<String>("racecar");
		LinkNode<String> node01 = new LinkNode<String>("racecar");
		LinkNode<String> node02 = new LinkNode<String>("racecar");

		head0.setNext(node01);
		node01.setNext(node02);
		
		String reversed0 = Recursion.rsoarll(head0);
		System.out.println(reversed0);
		System.out.println();
		
		
		//testing equine adventure
		System.out.println("Testing equine adventure: ");
		int [][] board = new int[3][4];
		int row = 0;
		int col = 0;
		Recursion.equineAdventure(board, row, col);
		
		int [][] board2 = new int[2][3];
		int row2 = 0;
		int col2 = 0;
		Recursion.equineAdventure(board2, row2, col2);
		
	}

}
