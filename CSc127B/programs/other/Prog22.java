import java.io.*;

public class Prog22 {
  public static void main (String [] args) throws Exception{
    final int EndOfStream = -1;    // Returned by read() at end of file
    String sourceName = " ";
    BufferedReader inFile = null;  // The input file being copied
    sourceName = args[0];
    inFile = new BufferedReader(new FileReader(sourceName));
    
    /*while (true) {
      currentChar = inFile.read();
      // Try to read a character from the input file
      
      // If the read failed, we're done; leave the loop
      
      if (currentChar == EndOfStream) break;
      */
    
    String thisLine  = null;
    String space = " ";
  
    int wordCount = 0;
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
    
    
    int charCount = 0;
    while ((thisLine = inFile.readLine()) != null) {
      boolean Space=true;
      for (int i = 0; i < thisLine.length(); i++) 
      {
        if (thisLine.charAt(i) != 0) {
          charCount++;
        }
      }
    }
    
    int lineCount = 0;
    while ((thisLine = inFile.readLine()) != null) {
      if ((thisLine = inFile.readLine()) != null) { 
        lineCount++;
      }
    }
    //}  // infinite while
    // Having exhausted the input file, close both files.
    inFile.close();
    
  }  // main
  
} // CopyTextFile