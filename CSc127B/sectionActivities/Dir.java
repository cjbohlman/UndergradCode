//  CSc 127B Fall 2015 Section 3 Part III

import java.io.File;
import java.util.Scanner;

public class Dir
{
  public static void main (String [] args)
  {
    String   dirName;   // directory name supplied by user
    File     dirRef;    // File object representing the given directory
    Scanner  keyboard;  // used to read from standard input
    
    System.out.print("Enter the name of a directory: ");
    keyboard = new Scanner (System.in);
    dirName = keyboard.nextLine();
    dirRef = new File(dirName);
    
    // If the given directory does not exist, terminate.
    
    if (!dirRef.exists()) {
      System.out.printf("\nERROR: %s does not exist.\n\n",dirName);
      System.exit(1);    // any non-zero value means abnormal termination
    }
    
    // If the directory isn't actually a directory, terminate. 
    else if(!dirRef.isDirectory()){
      System.out.printf("\nERROR: %s is not a directory.\n\n",dirName);
      System.exit(1);    // any non-zero value means abnormal termination
      
    }
    // If the directory isn't readable, terminate. 
    else if(!dirRef.canRead()){
      System.out.printf("\nERROR: %s is not readable.\n\n",dirName);
      System.exit(1);    // any non-zero value means abnormal termination
      
    }
    // Access and display to the screen the directory's content.
    else{
      String[] fileNames;
      fileNames = dirRef.list();
      System.out.printf("\n%s contains these files:\n\n",dirName);
      for(int i = 0; i < fileNames.length; i++) {
        System.out.println(fileNames[i]);
      }
      
    }
    
  } // main()
  
} // Dir
