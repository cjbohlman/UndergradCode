import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Section12
{

   public static void main(String [] args) throws FileNotFoundException {

      BinarySearchTree<String> nameTree;
      String fileName = args[0];
      File inputFile = new File( fileName );
      Scanner inputScan = new Scanner( inputFile );
      String name;

      nameTree = new BinarySearchTree<String>();

      while ( inputScan.hasNext() ) {
         name = inputScan.nextLine();
         nameTree.add( name );
      }
      System.out.println("Tree contents after reading in:");
      System.out.print(nameTree);

      // Uncomment these two lines when ready to test toStringDepth()
         System.out.println("Tree contents with depth:");
         System.out.print(nameTree.toStringDepth());

      // Uncomment these two lines when ready to test maxDepth()
         System.out.print("Maximum depth of the tree: ");
         System.out.println(nameTree.maxDepth());

   }

} // Section12 class