/*-------------------------------------------------------------------
 Class Polynomial
 Chris Bohlman
 Inherits from: None
 Package Contained In: None
 
 Purpose: holds an array of term objects in one object
 
 Instance Variables: terms array
 
 Classs Methods: n/a
 
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
 -------------------------------------------------------------------*/

//import packages
import java.io.*;
import java.lang.*;
import java.math.*;

//the class
public class Polynomial {
  
  private Term[] terms;
  
  //constructor: sets new objects value to zero
  public Polynomial() {
    this.terms = new Term[0];
  }
  
  //add method: adds Polynomial p to current polynomial
  Polynomial add(Polynomial p) {
    Polynomial added = new Polynomial();
    added.terms = terms;
    for(int i = 0; i <p.terms.length; i++) {    
      added.addTerm(p.terms[i].getCoefficient(),p.terms[i].getExponent());
    }
    return added;
  }
  
  
  
  //addTerm method: adds a term to the current object wit coefficient c and exponent e
  void addTerm(int c, int e) {
    Term values = new Term(c,e);
    if (terms[0] == null) {
      this.terms[0] = values;
      return;
    }
    for(int i = 0; i < terms.length; i++) {
      if (e == terms[i].getExponent()) {
        this.terms[i].setCoefficient((c+(terms[i].getCoefficient())));
        break;
      }
      else{
        Term[] temp = new Term[terms.length + 1];
        for (i = 0; i < terms      .length; i++) {
          temp[i] = terms[i];
        }
        temp[temp.length - 1] = values;
        terms = new Term[temp.length];
        for (i = 0; i < temp.length; i++) {
          this.terms[i] = temp[i];
        }
      }
    }
    sort();
  }
  
  //sort method: sorts exponents in ascending order
  void sort() {
    boolean swap = true;
    Term[] temp = new Term[1];
    while(swap) {
      swap = false;
      for( int i = 0; i < terms.length -1; i++) {
        if (terms[i].getExponent() > terms[i+1].getExponent()) {
          temp[0] = terms[i];
          terms[i] = terms[i+1];
          terms[i+1] = temp[0];
          swap = true;
        }
      }
    } 
  }
  
  //replicate methd: replicates current polynomial into new polynomial
  Polynomial replicate() {
    Polynomial rep = new Polynomial();
    rep.terms = terms;
    return rep;
  }
  
  
  
  //equals method: if two polynomials share the same exponents, coefficients, and length, returns true
  boolean equals(Polynomial p) {
    boolean equals = false;
    for(int i = 0; i < terms.length; i++) {
      if(terms[i].getExponent() == p.terms[i].getExponent() && terms[i].getCoefficient() == p.terms[i].getCoefficient() && terms.length == p.terms.length) {
        equals = true;
      }
      else equals = false;
    }
    return equals;
  }
  
  //getCoefficient method: returns coefficient of term with exponent e
  int getCoefficient(int e) {
    int f = 0;
    for(int i = 0; i < terms.length; i++) {
      if (e == terms[i].getExponent()) {
        f = terms[i].getExponent();
      }
    }
    return f;
  }
  
//evaluate method: evaluates current polynomial by double x
  double evaluate(double x) {
    double total = 0;
    for( int i = 0; i < terms.length; i++) {
      int e = terms[i].getExponent();
      int c = terms[i].getCoefficient();
      total = total + c*Math.pow(x,e);
    }
    return total;
  }
  
//isEmpty method: returns true if the current array is empty
  boolean isEmpty() {
    boolean empty = true;
    for (int i = 0; i > terms.length; i++) {
      if (!(terms[i] == null)) {
        empty = false;
      }
    }
    return empty;
  }
  
//isFull method: returns true if current array has non-zero terms filling all objects
  boolean isFull() {
    boolean full = false;
    int holding = 0;
    for(int i = 0; i < terms.length; i++) {
      int e = terms[i].getExponent();
      int c = terms[i].getCoefficient();
      if (c != 0) {
        holding = holding + 1;
      }
      if (terms.length == holding) {
        full = true;
      }
    }
    return full;
  }
  
//holding method: returns how many terms have non zero coefficients on them
  int holding() {
    int i;
    int holding = 0;
    for (i = 0; i < terms.length; i++) {
      holding = holding + 1;
    }
    return holding;
  }
  
//negate method: changes all coefficients in the current object to be negative if they are positive, or positive if they are negative
  Polynomial negate() {
    Polynomial negate = new Polynomial();
    negate.terms = terms;
    for(int i = 0; i < terms.length; i++) {
      int e = terms[i].getExponent();
      int c = -1 * terms[i].getCoefficient();
      negate.terms[i].setCoefficient(c);
    }
    return negate;
  }
  
//scalarMultiply method: multiplies all coefficients by int s
  public void scalarMultiply(int s) {
    for (int i = 0; i < terms.length; i++) {
      int e = terms[i].getExponent();
      int c = s * terms[i].getCoefficient();
      terms[i].setCoefficient(c);
    }
  }
  
//toString method: spits out Polynomial object as a string
  public String toString() {
    String poly = "";
    int c = 0;
    int e = 0;
    for (int i = 0; i < terms.length; i++) {
      e = terms[i].getExponent();
      c = terms[i].getCoefficient();
      if (c == 0) {
        poly = "";
        continue;
      }
      if (i == 0) {
        poly = c+"x^"+e;
      }
      else {
        poly = poly+" + "+c+"x^"+e;
      }
    }
    return poly;
  }
}//class end