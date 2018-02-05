//all methods in this class call their counterparts in ALinkedList
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BookList
{

   private ALinkedList<Book> titles;
   private Book currentTitle;
   
   // Constructor:
   // Opens the file and reads in Title and Author lines
   // Creates a singly-linked list of titles.  Titles are maintained in
   // alphabetical order.
   // Each title has a singly-linked list of authors.  Authors are in
   // the order listed in the file (not necessarily alphabetical).
   public BookList( String fileName ) throws FileNotFoundException
   {
      File    inputFile = new File( fileName );
      Scanner inputScan = new Scanner( inputFile );
      String  line;
      Book    tempTitle = null;
      Book    aTitle;
      String  tempAuthor;
      
      currentTitle = null;
      
      // Create an empty list
      titles = new ALinkedList<Book>();
      
      while ( inputScan.hasNext() ) {
         line = inputScan.nextLine();
  
         // If this is a title, put it in the Book list in sorted order
         if ( line.substring(0,8).equals("Title:  ") ) {
            tempTitle = new Book( line.substring(8) );

            // check if the title is already in the list
            currentTitle = titles.find( tempTitle );
            if ( currentTitle == null ) {
               titles.insertInOrder( tempTitle );  // not in list, can add it
               currentTitle = titles.find( tempTitle );
            }
         }

         // If this is an author, add it to the list of authors for the
         // current title
         if ( line.substring(0,8).equals("Author: ") ) {

            if ( currentTitle != null ) {
               // Put 3 blank spaces at start of title
               // so author will indent correctly underneath title
               tempAuthor = "   " + line.substring(8) + "\n";
               currentTitle.addAuthor(tempAuthor);//y
            }
         }
      } // while
      
   } // constructor
   
   
   public boolean insertTitle( String newTitle )
   {
	   Book book = new Book(newTitle);
	   titles.insertInOrder(book);
	   return true;
      
   } // insertTitle
   
      
   public Book findTitle( String aTitle )
   {
	   Book book = new Book(aTitle);
	   Book temp = null;
	   temp = titles.find(book);
	   return temp;

   } // findTitle
   
   
   public boolean remove(String title)
   {
	   return titles.remove(title);
   } // remove
   
   
   public String toStringTitles()
   {
	   return titles.toStringTitle();
   } // toStringTitles


   public String toString()
   {
	   return titles.toString();
   } // toString

} // BookList