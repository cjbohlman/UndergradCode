/*-------------------------------------------------------------------
 Class PolynomialB
 Chris Bohlman
 Inherits from: None
 Package Contained In: None
 
 Purpose: holds an arraylist of term objects
 
 Instance Variables: terms array
 
 Class Methods: n/a
 
 Instance Methods:
 add
 addTerm
 sort
 replicate
 equals
 getCoefficient
 evaluate
 isEmpty
 isFull
 holding
 negate
 scalarMultiply
 toString
 exponentList
 -------------------------------------------------------------------*/

//import packages
import java.util.ArrayList;
import java.util.*;

//the class
public class PolynomialA implements Quantity{
  private ArrayList<Term> a1 = null;

  
  //constructor: sets new objects value to zero
  public PolynomialA() {
	  this.a1 = new ArrayList<Term>();
  }
  
  public ArrayList<Term> getList() {
	  return this.a1;
  }
  
  //add method: adds Polynomial p to current polynomial
  public PolynomialA add(PolynomialA a) {
	PolynomialA added = new PolynomialA();
	added.a1 = a1;
    int aSize = a.getList().size();
    for (int i = 0; i < aSize; i++) {
    	added.addTerm(a.getList().get(i).getCoefficient(),a.getList().get(i).getExponent());
    }
    return added;
  }
  
  //addTerm method: adds a term to the current object wit coefficient c and exponent e
  public void addTerm(int c, int e) {
    int newCoef;
    int aSize = a1.size();
    boolean set = false;
    if (aSize == 0) {
        a1.add(new Term(c,e));
        return;
    }
    for(int i = 0; i < aSize; i++) {
      if (e == a1.get(i).getExponent()) {
        newCoef = c + a1.get(i).getCoefficient();
        if(newCoef == 0) {
        	a1.set(i,new Term(0,e));
        	set = true;
            break;
        }
        a1.set(i,new Term(newCoef,e));
        set = true;
        break;
      }
    }
        if (!set) a1.add(new Term(c,e));
      }
  
  //sort method: sorts exponents in ascending order
  void sort() {
    Term temp;
    boolean swap = true;
    while(swap) {
      swap = false;
      int i;
      for(i = 0; i < a1.size()-1; i++) {
        if (a1.get(i).getExponent() < a1.get(i+1).getExponent()) {
          temp = a1.get(i);
          a1.set(i,a1.get(i+1));
          a1.set(i+1,temp);
          swap = true;
        }
      }
    } 
  }
  
  //replicate method: replicates current polynomial into new polynomial
  PolynomialA replicate() {
    PolynomialA rep = new PolynomialA();
    rep.a1 = a1;
    return rep;
  }
  
  //equals method: if two polynomials share the same exponents, coefficients, and length, returns true
  boolean equals(PolynomialA p) {
    boolean equals = false;
    int a1Size = a1.size();
    int pSize = p.getList().size();
    for(int i = 0; i < a1.size(); i++) {
      if(a1.get(i).getExponent() == p.getList().get(i).getExponent() && a1.get(i).getCoefficient() == p.getList().get(i).getCoefficient() && a1Size == pSize) {
        equals = true;
      }
      else equals = false;
    }
    return equals;
  }
  
  //getCoefficient method: returns coefficient of term with exponent e
  int getCoefficient(int e) {
    int f = 0;
    int a1Size = a1.size();
    for(int i = 0; i < a1Size; i++) {
      if (e == a1.get(i).getExponent()) {
        f = a1.get(i).getCoefficient();
      }
    }
    return f;
  }
  
  //evaluate method: evaluates current polynomial by double x
  double evaluate(double x) {
    double total = 0;
    int a1size = a1.size();
    for( int i = 0; i < a1size; i++) {
      int e = a1.get(i).getExponent();
      int c = a1.get(i).getCoefficient();
      total = total + c*Math.pow(x,e);
    }
    return total;
  }
  
  //isEmpty method: returns true if the current array is empty
  public boolean isEmpty() {
    boolean empty = true;
    int a1Size = a1.size();
    for (int i = 0; i < a1Size; i++) {
      if (!(a1.get(i).getCoefficient() == 0)) {
        empty = false;
        }
    }
    return empty;
  }
  
  //isFull method: returns true if current array has non-zero terms filling all objects
  public boolean isFull() {
    boolean full = false;
    int holding = 0;
    int a1Size = a1.size();
    for(int i = 0; i < a1Size; i++) {
      int c = a1.get(i).getCoefficient();
      if (c != 0) {
        holding = holding + 1;
      }
      if (a1Size == holding) {
        full = true;
      }
    }
    return full;
  }
  
  //holding method: returns how many terms have non zero coefficients on them
  public int holding() {
    int i;
    int holding = 0;
    int c;
    int a1Size = a1.size();
    for (i = 0; i < a1Size; i++) {
      c = a1.get(i).getCoefficient();
      if (c != 0) {
        holding = holding + 1;
      }
    }
    return holding;
  }
  
  //negate method: changes all coefficients in the current object to be negative if they are positive, or positive if they are negative
  PolynomialA negate() {
    PolynomialA negate = new PolynomialA();
    negate.a1 = a1;
    int a1Size = a1.size();
    for(int i = 0; i < a1Size; i++) {
      int c = -1 * a1.get(i).getCoefficient();
      negate.a1.get(i).setCoefficient(c);
    }
    return negate;
  }
  
  //scalarMultiply method: multiplies all coefficients by int s
  public void scalarMultiply(int s) {
    int a1Size = a1.size();
    for(int i = 0; i < a1Size; i++) {
      int c = s * a1.get(i).getCoefficient();
      a1.get(i).setCoefficient(c);
    }
  }
  //toString method: spits out Polynomial object as a string
  public String toString() {
    String poly = "";
    sort();
    int a1Size = a1.size();
    int c = 0;
    int e = 0;
    for (int i = 0; i < a1Size; i++) {
      e = a1.get(i).getExponent();
      c = a1.get(i).getCoefficient();
      if (c == 0) {
        poly = "";
        continue;
      }
      if (i == 0) {
        poly = "("+c+")x^("+e+")";
      }
      else {
        poly = poly+" + "+"("+c+")x^("+e+")";
      }
    }
    return poly;
  }
  
  //exponentList method: this method takes all exponents from all the terms and puts it into an array list
  ArrayList<Integer> exponentList() {
	  ArrayList<Integer> expList = new ArrayList<Integer>();
	  for (int i = 0; i > a1.size(); i++) {
		  int exp = a1.get(i).getExponent();
		  expList.set(i,exp);
	  }
	return expList;
  }

}//class end