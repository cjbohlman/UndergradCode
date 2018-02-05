/*************************************************************************
 *  Compilation:  javac Pet.java
 *  Execution:    java Pet
 *  author: Odile wolf
 *
 *************************************************************************/
// this is used to allow us to use classes in all the libraries of java.io
import java.io.*;

public class CIS129-ChrisBohlman-PC7 {

    /// attributes
    private String name;
    private String type;
    private String owner;
    private int age;
    /// methods
    public String getName() {return name;}
    public String getType() {return type;}
    public String getOwner() {return owner;}
    public int getAge() {return age;}
    
    //setters
    public void setName( String n) {name = n;}
    public void setType( String t) {type = t;}
    public void setOwner( String o) {owner = o;}
    public void setAge(int a) {age = a;}
   
    //constructor
    public Pet(int age) { this.age = age;}
    public Pet() { }
   
}


