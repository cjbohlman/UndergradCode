// CopyTextFileS3.java:  An almost-complete version of the program from
// Parts II and III of Section #2.
// Used in Section 3 Part II of CSc 127B Fall 2016.

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class CopyTextFileS3
{

    public static void main (String [] args)
    {
        final int EndOfStream = -1;    // Returned by read() at end of file

        BufferedReader inFile = null;  // The input file being copied
        BufferedWriter outFile = null; // The output file copy being created
        String sourceName = null,      // File name/path of file being copied
               destinationName = null; // File name/path of new copy
        int currentChar = 0;           // Will read/write one char at a time


        if (args.length >= 2) {  // if enough file names/paths are provided
            sourceName = args[0];
            destinationName = args[1];
        } else {
            System.out.println("Usage:  java CopyTextFile <source> " 
                             + "<destination>");
            return;
        }

                // open both files
        try {
        inFile  = new BufferedReader (new FileReader (sourceName));
        } catch (FileNotFoundException e) {
                System.out.println("ERROR:  Cannot read file from " + sourceName
                                 + ".");
                e.printStackTrace();
            }
        
        try {
        outFile = new BufferedWriter (new FileWriter (destinationName));
        } catch (IOException e) {
                System.out.println("ERROR:  Cannot read file from " + sourceName
                                 + ".");
                e.printStackTrace();
            }

                // Read and write characters until a read fails because we
                // have reached EOF.
       
        while (true) {

                    // Try to read a character from the input file

            try {

                currentChar = inFile.read();

            } catch (IOException e) {
                System.out.println("ERROR:  Cannot read from " + sourceName
                                 + ".");
                e.printStackTrace();
            }

                    // If the read failed, we're done; leave the loop

            if (currentChar == EndOfStream) break;

                    // Otherwise, write the character to the output file

            try {

                outFile.write(currentChar);

            } catch (IOException e) {
                System.out.println("ERROR:  Cannot write to " + destinationName
                                 + ".");
            }

        }  // infinite while

                // Having exhausted the input file, close both files.
try {
        inFile.close();
        outFile.close();
        } catch (IOException e) {
                System.out.println("ERROR:  Cannot read file from " + sourceName
                                 + ".");
                e.printStackTrace();
            }

    }  // main

} // CopyTextFileS3
