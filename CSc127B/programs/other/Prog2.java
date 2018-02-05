/*-------------------------------------------------------------------
 * 
 Class Prog2
 Chris Bohlman
 Inherits from: None
 Package Contained In: None
 
 Purpose: Creates a program that counds the number of words, characters, and lines of text files
 
 Instance Variables: 
 
 
 Classs Methods:
 
 
 Instance Methods:
 
 
 -------------------------------------------------------------------*/

import java.io.*;

public class Prog2 {
  
  
  public static void main (String [] args) throws Exception{
    try{
      
    final int EndOfStream = -1;    // Returned by read() at end of file
    
    BufferedReader inFile = null; // The input file being copied
    String sourceName = null;  // File name/path of file being copied
    int currentChar = -1;          // Will read/write one char at a time
    
    
    if (args.length == 2) {  // if enough file names/paths are provided
      sourceName = args[0];
    }
    
    else {
      System.out.println("Usage:  java CopyTextFile <source> " 
                           + "<destination>");
      return;
    }
    // open both files 
    inFile = new BufferedReader(new FileReader(sourceName));
    // Read and write characters until a read fails because we
    // have reached EOF.
    
    while (true) {
      currentChar = inFile.read();
      // Try to read a character from the input file

      // If the read failed, we're done; leave the loop
      
      if (currentChar == EndOfStream) break;
      
      // Otherwise, write the character to the output file

    }  // infinite while
    
    // Having exhausted the input file, close both files.
    inFile.close();
      
      int numberWords = WordCount();
      System.out.println("Word count is = " + numberWords);
      
      int numberChars = CharCount();
      System.out.println("Char count is = " + numberChars);
      
      int numberLines = LineCount();
      System.out.println("Line count is = " + numberLines);
      
    }catch(Exception e){
      e.printStackTrace();}
  }
  
  public static int WordCount() throws Exception{
    String thisLine  = null;
    String space = " ";
    int wordCount = 0;
    try{
      while ((thisLine = inFile.readLine()) != null) {
        boolean Space=true;
        for (int i = 0; i < thisLine.length(); i++) 
        {
          if (thisLine.charAt(i) == ' ') {
            Space=true;
          }
          else{
            if(Space) wordCount++;
            Space = false; 
          }
        }
      }
    }catch(Exception e){
      e.printStackTrace();}
    return wordCount;
  }
  
  public static int CharCount() throws Exception{
    String thisLine  = null;
    String space = " ";
    int charCount = 0;
    try{
      while ((thisLine = inFile.readLine()) != null) {
        boolean Space=true;
        for (int i = 0; i < thisLine.length(); i++) 
        {
          if (thisLine.charAt(i) != 0) {
            charCount++;
          }
        }
      }
    }catch(Exception e){
      e.printStackTrace();}
    return charCount;
  }
  
  public static int LineCount() throws Exception{
    String thisLine  = null;
    String space = " ";
    int lineCount = 0;
    try{
      while ((thisLine = inFile.readLine()) != null) {
        
        if ((thisLine = inFile.readLine()) != null) { 
          lineCount++;
        }
      }
    }catch(Exception e){
      e.printStackTrace();}
    return lineCount;
  }
  
}


