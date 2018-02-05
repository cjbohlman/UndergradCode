/*
 * File07.java -- Creates a pair of output files.  The first, named
 * File07a.out, is a binary file containing an integer value.
 * The second, File07b.out, is also a binary file, but one containing
 * the serialized representation of an Integer object representing the
 * same integer value.  Note the size difference between the two output
 * files, and that Integer is defined to be a Serializable class, which
 * means that we can write it.
 *
 * Can you write a program that reads the content of the File07b.out file?
 */
import java.io.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class ExceptionsFile07
{
    public static void main (String [] args) throws IOException
    {
        final String       fileNameA = "File07a.out",
                           fileNameB = "File07b.out";
        final int          myInt = 48;
        File               fileRef;
        FileOutputStream   byteStream = null;
        DataOutputStream   intStream = null;
        ObjectOutputStream objStream = null;

        System.out.println("Writing the int " + myInt 
                       + " to the binary file '" + fileNameA + "'...");

        try {
            fileRef = new File(fileNameA);
            byteStream = new FileOutputStream(fileRef);
            intStream = new DataOutputStream(byteStream);

            intStream.writeInt(myInt);

            intStream.close();
        
        } catch (FileNotFoundException e) {
          System.out.println("ERROR:  File not found");
                e.printStackTrace();
        }
         catch (IOException e) {
            System.out.println("I/O ERROR:  Something went wrong with the "
                               + "creation of the int file,\nbut because "
                               + "some lazy programmer dumped all of the file "
                               + "code into\none try block, I can't be more "
                               + "specific!  Here's what I can tell you:");
            System.out.println("  The Error: " + e.getClass().getName());
            String mesg = e.getMessage();
            if (mesg == null) {
                System.out.println("The message:  None.");
            } else {
                System.out.println("The message: " + mesg);
            }
            System.out.println("The stack trace:");
            e.printStackTrace();

        }

        System.out.println("Writing an Integer object containing "
                       + myInt + " to the binary file '"
                       + fileNameB + "'...");

        try {
        fileRef = new File(fileNameB);
        byteStream = new FileOutputStream(fileRef);
        objStream = new ObjectOutputStream(byteStream);

        objStream.writeObject(new Integer(myInt));

        objStream.close();
        
        } catch (IOException e) {
            System.out.println("I/O ERROR:  Something went wrong with the "
                               + "creation of the file of Integer,\nbut because "
                               + "some lazy programmer dumped all of the file "
                               + "code into\none try block, I can't be more "
                               + "specific!  Here's what I can tell you:");
            System.out.println("  The Error: " + e.getClass().getName());
            String mesg = e.getMessage();
            if (mesg == null) {
                System.out.println("The message:  None.");
            } else {
                System.out.println("The message: " + mesg);
            }
            System.out.println("The stack trace:");
            e.printStackTrace();
        }

    } // main
} // ExceptionsFile07

