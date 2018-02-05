/*-------------------------------------------------------------------
 Class Location
 Chris Bohlman
 Inherits from: None
 Package Contained In: None
 
 Purpose: creates a location object with a row location and a column location
 
 Instance Variables: int row, int column
 
 Class Methods: n/a
 
 Instance Methods:
 getRow
 getColumn
 setRow
 setColumn
 toString
 -------------------------------------------------------------------*/
public class Location {

 //instance variables
 private int row;
 private int column;

 //constructor: accepts input of a row location and a column location
 public Location(int row, int column) {
  this.row = row;
  this.column = column;
 }

 //instance method: getRow
 //gets the location of the row
public int getRow() {
  return row;
 }

 //instance method: getColumn
 //gets the location of the column
public int getColumn() {
  return column;
 }
 
 //instance method: setRow
 //sets the location of the row to int newRow
 void setRow(int newRow) {
  this.row = newRow;
 }

 //instance method: setColumn
 //sets the location of the column to int newColumn
 void setColumn(int newCol) {
  this.column = newCol;
 }
 
 //toString: prints object as a string
 public String toString() {
		String string;
		string = getRow()+","+getColumn();
		return string;
	}
}
