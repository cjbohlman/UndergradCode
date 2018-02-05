// CopyTextFile.java:  CSc 127B, Fall 2015, Section 4, Part II
// (McCann)
//   2016/09/01: Exception-handling removed for 127B F'16 Sec 2 Part II

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;



public class CopyTextFile
{
  
  public static void main (String [] args) throws Exception
  {
    final int EndOfStream = -1;    // Returned by read() at end of file
    
    BufferedReader inFile = null;  // The input file being copied
    BufferedWriter outFile = null; // The output file copy being created
    String sourceName = null,      // File name/path of file being copied
      destinationName = null; // File name/path of the new copy
    int currentChar = -1;          // Will read/write one char at a time
    
    
    if (args.length >= 2) {  // if enough file names/paths are provided
      sourceName = args[0];
      destinationName = args[1];
    }
    
    else {
      System.out.println("Usage:  java CopyTextFile <source> " 
                           + "<destination>");
      return;
    }
    // open both files 
    inFile = new BufferedReader(new FileReader(sourceName));
    outFile = new BufferedWriter(new FileWriter(destinationName));
    
    // Read and write characters until a read fails because we
    // have reached EOF.
    
    while (true) {
      currentChar = inFile.read();
      // Try to read a character from the input file

      // If the read failed, we're done; leave the loop
      
      if (currentChar == EndOfStream) break;
      
      // Otherwise, write the character to the output file
      outFile.write(currentChar);

    }  // infinite while
    
    // Having exhausted the input file, close both files.
    inFile.close();
    outFile.close();

    
  }  // main
  
} // CopyTextFile
