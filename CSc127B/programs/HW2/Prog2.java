/*-------------------------------------------------------------------
 Class Prog2
 Chris Bohlman
 Inherits from: None
 Package Contained In: None
 
 Purpose: Creates a program that counds the number of words, characters, and lines of text files
 
 Instance Variables: 
 BufferedReader br provides the buffered reader for the entire class
 String[] sourceName holds the file name or file names in an array
 int numberLines holds the number of lines
 int numberWords holds the number of words
 int numberChars holds the number of characters
 
 Classs Methods:
 none
 
 Instance Methods:
 Count: based on the character currently being read, analyzes and increments, based on a variety of paraeters,
 whether to increment the amount of lines, words, or characters
 -------------------------------------------------------------------*/
//importing the entire java.io library
import java.io.*;

//the class
public class Prog2 {
  
  //the static instance variables, because I only needed one copy of them
  private static BufferedReader br = null; 
  private static String sourceName[] = null;
  private static int numberLines = 0;
  private static int numberWords = 0;
  private static int numberChars = 0;
  
  /*****************
   * the main method
   * Purpose: accept values that aren't passed through the command line or take values that were passed through the 
   * command line, and set those files in an array to be read.
   * Total up the number of words, lines, and characters in all of the files
   * Print all of that
   * Parameters: the arguement array
   ****************/
  public static void main (String [] args) throws Exception{
   //gotta catch those exceptions boi
    try{
      int totalWords = 0;
      int totalLines = 0;
      int totalChars = 0;
      
      if (args.length < 1) {
        BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
        System.out.println("This program determines the quantity of lines, words, and bytes in a file or files that you specify.");
        System.out.println("Please enter one or more file names, comma-separated: ");
        String dataFiles = input.readLine();
        
        sourceName= dataFiles.split(",");
      }
      else{
        sourceName = new String[args.length];
        for (int i=0; i < args.length; i++){
          sourceName[i] = args[i];
        }
      }
      System.out.println(" Lines     Words     Bytes");
      System.out.println("--------  --------  --------");
      for (int i=0; i < sourceName.length; i++) {
        br = new BufferedReader(new FileReader(sourceName[i]));
        Count();
        System.out.println("   "+numberLines+"         "+numberWords+"         "+numberChars+"       "+sourceName[i]+" ");
        
        totalWords = totalWords + numberWords;
        totalLines = totalLines + numberLines;
        totalChars = totalChars + numberChars;
        
        numberLines = 0;
        numberWords = 0;
        numberChars = 0;
      }
      System.out.println("--------------------------------------------");
      System.out.println("   "+totalLines+"         "+totalWords+"         "+totalChars+"       Totals"); 
      br.close();
    }catch(Exception e){
      e.printStackTrace();}
  }
  
  /*****************
   * the Count method
   * Purpose: count up all of those lines, words, and characters from all of the files
   * Do this within the given parameters and increment only when told to do so.
   * Parameters: the arguement array
   ****************/
  public static void Count() throws Exception{
    char thisChar;
    char space = ' ';
    try{
      boolean inWord = false;
      boolean carriageReturn = false;
      while ((thisChar = (char)br.read()) != (char)-1) {
        if (thisChar != -1) { 
          numberChars++;
          if (thisChar == ' '){
            inWord = false;
          }
          else if (carriageReturn) {
            carriageReturn = false;
            continue;
          }
          else if (thisChar == '\n'){
            inWord = false;
            numberLines++;
          }
          else if (thisChar == '\r'){
            inWord = false;
            carriageReturn = true;
            numberLines++;
          }
          else if(inWord){
            continue;
          }
          else{
           numberWords++;
           inWord = true;
          }
        }
      }
    }catch(Exception e){
      e.printStackTrace();}
  }
}