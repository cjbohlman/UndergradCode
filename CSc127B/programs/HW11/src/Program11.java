/*-------------------------------------------------------------------
	 Class Program11
	 Chris Bohlman
	 Inherits from: none
	 Package Contained In: 
	 java.io.*, java.util.*
	 
	 Purpose: accepts input of preorder and inorder traversal and creates a tree, postorder traversal, and a decoded printing of the tree.
	 
	 Instance Variables:
	 BufferedReader br, String fileName, Scanner sc, BinaryNode root
	 
	 Class Methods: main method
	 
	 Instance Methods:
	 decode
	 constructTree
	 postorder

	 -------------------------------------------------------------------*/

import java.io.*;
import java.util.*;

public class Program11 {

	private static BufferedReader br = null;
	private static String fileName = "";
	private static Scanner sc = null;
	private static BinaryNode<Integer> root = null;

	public static void main(String[] args) throws Exception {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter a file name's complete path:");
			fileName = input.readLine();

			sc = new Scanner(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String preorder_s = sc.nextLine();
		String inorder_s = sc.nextLine();
		String path = sc.nextLine();

		root = constructTree(preorder_s, inorder_s);

		System.out.println("Postorder Traversal:"+postorder(root));
		
		System.out.print("Decoded sequence: ");
		decode(root, path);
	}

	//instance method: decode
	//accepts a root and path and then climbs down a tree, printing all leaves based on path
	private static void decode(BinaryNode<Integer> root, String path) {
		BinaryNode<Integer> walker = root;
		
		for(int i = 0; i < path.length(); i++) {
			if (path.charAt(i) == '0') {
				if (walker.getLeftChild() == null && walker.getRightChild() == null) {
					System.out.print(walker.getData());
					walker = root;
				}
				walker = walker.getLeftChild();
			}
			else {
				if (walker.getLeftChild() == null && walker.getRightChild() == null) {
					System.out.print(walker.getData());
					walker = root;
				}
				walker = walker.getRightChild();
			}
		}
		System.out.print(walker.getData());
	}

	//instance method constructTree
	//returns a binaryNode that constructs a tree based off of a preorder traversal and an inorder traversal
	private static BinaryNode constructTree(String preorder, String inorder) {
		if (inorder.length() <= 2) {
			return new BinaryNode(inorder.trim());
		} else {
			int firstInt = Integer.parseInt(preorder.substring(0, preorder.indexOf(' ')));
			String firstI = Integer.toString(firstInt);
			BinaryNode root = new BinaryNode(firstInt);
			
			preorder = preorder.substring(2);

			root.setLeftChild(constructTree(preorder, inorder.substring(0, inorder.indexOf(firstI)).trim()));
			preorder = preorder.substring(2);
			root.setRightChild(constructTree(preorder, inorder.substring(inorder.indexOf(firstI)+1).trim()));
			
			return root;
		}
	}

	//instance method: postorder
	//accepts a root as an arguement and creates a postorder traversal string based off of the tree passed
	private static String postorder(BinaryNode<Integer> node) {
		// base case
		if (node == null)
			return "";
		// recursive steps
		String str = "";
		str = str + "" + postorder(node.getLeftChild());
		str = str + "" + postorder(node.getRightChild());
		str = str + " " + node.getData();
		return str;
	}

}
