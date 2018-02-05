/*-------------------------------------------------------------------
 * 
 Class CalenderDate
 Chris Bohlman
 Inherits from: None
 Package Contained In: None
 
 Purpose: Creates a CalenderDate object that holds a certain month, day and year.
 The object can then be manipulated with several methods also contained within the class.
 
 Instance Variables: 
 int year holds the year
 int month holds the month
 int day holds the day
 
 Classs Methods:
 setDate: This setter initializes the date so it is correct, and not an illegal 
 date or any otherwise wrong representation of the dates passed to the object.
 
 Instance Methods:
 getYear: returns the year value
 getMonth: returns the month value
 getMonthAsString: returns the month name equivalent to it's integer value
 getDay: returns the day value
 toString: returns the string of the month name, day and year
 equals: returns true if the otherDate object and the CalendarDate parameters are equal
 tomorrow: returns a reference to the day after the given paraneters of CalendarDate.
 
 -------------------------------------------------------------------*/

//The class
public class CalendarDate {
  
  //Instance variables
  private int year, month, day;//holds the given year, month, and day
  
  //The constructor
  public CalendarDate(int year, int month, int day) {
    this.year = year;
    this.month = month;
    this.day = day;
    setDate(year, month, day);
  }
  
  /****************
    Class Method: setDate
    Purpose: This mutator method is a setter that initializes the date, so it isn't an illegal date or one that can't be proccessed.
    Pre condition/Post condition: none
    Return value: none
    Parameters: int year, month, day go into the mthod in order to be changed within the parameters.
    ****************/
  public void setDate (int year, int month, int day) {
    if (month==2) {
      if (year%4==0) {
        if (year%100!=0 || year%400==0) {
          if (day>29) {
            day = 29;
          }
        }
        else {
          if (day>28) {
            day = 28;
          }
        }
      }
    }
    if (year<1753) {
      this.year = 1753;
      this.month = 1;
      this.day = 1;
    }
    if (month<1) {
      this.month = 1;
    }
    if (month>12) {
      this.month = 12;
    }
    if (day<1) {
      this.day  = 1;
    }
    if (month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
      if (day>31) {
        this.day = 31;
      }
    }
    if (month==4||month==6||month==9||month==11) {
      if (day>30) {
        this.day = 30;
      }
    }
    if ((month >= 1) && (month <= 12) && (day >= 1) && (day <= 31) && (year >= 1753)) {
      this.month = month;
      this.day = day;
      this.year = year;
    }
  }
  
  /****************
    Instamce Method: getYear
    Purpose: This getter method gets the year integer value
    Pre condition/Post condition: none
    Return value: int year
    Parameters: none
    ****************/
  public int getYear() {
    return this.year;
  }
  
  /****************
    Instamce Method: getMonth
    Purpose: This getter method gets the month integer value
    Pre condition/Post condition: none
    Return value: int month
    Parameters: none
    ****************/
  public int getMonth() {
    return this.month;
  }
  
  /****************
    Instamce Method: getMonthAsString
    Purpose: This getter method takes the integer for the month and makes a string of the month's name depending on which month integer it is
    Pre condition/Post condition: none
    Return value: the string of the month's name
    Parameters: none
    ****************/
  public String getMonthAsString() {
    String monthName = "";//holds the month name that is equivalent to the month integer value
    if (month==1) {
      monthName = "January";
    }
    if (month==2) {
      monthName = "February";
    }
    if (month==3) {
      monthName = "March";
    }
    if (month==4) {
      monthName = "April";
    }
    if (month==5) {
      monthName = "May";
    }
    if (month==6) {
      monthName = "June";
    }
    if (month==7) {
      monthName = "July";
    }
    if (month==8) {
      monthName = "August";
    }
    if (month==9) {
      monthName = "September";
    }
    if (month==10) {
      monthName = "October";
    }
    if (month==11) {
      monthName = "November";
    }
    if (month==12) {
      monthName = "December";
    }
    return monthName;
  }
  
  /****************
    Instamce Method: getDay
    Purpose: This getter method gets the day integer value
    Pre condition/Post condition: none
    Return value: int day
    Parameters: none
    ****************/
  public int getDay() {
    return this.day;
  }
  
  /****************
    Instamce Method: toString
    Purpose: This method converts the monthName string and the day and year integers to a date string
    Pre condition/Post condition: none
    Return value: string with monthName, ints with day and year
    Parameters: none
    ****************/
  public String toString() {
    String monthName = getMonthAsString();
    return monthName+ " " +this.day+ ", " +this.year;
  }
  
  /****************
    Instamce Method: equals
    Purpose: Returns true if this object and the other CalendarDate object are identical
    Pre condition/Post condition: none
    Return value: true or !true
    Parameters: Object otherDate
    ****************/
  public boolean equals (CalendarDate otherDate) {
    boolean equalCalendar = false;
    if (otherDate.year==this.year && otherDate.month == this.month && otherDate.day == this.day) {
      return !equalCalendar;
    }
    return equalCalendar;
  }
  
  /****************
    Instamce Method: tomorrow
    Purpose: Returns values that are equivalent to the next day when looking at the first year, month, and day values
    Pre condition/Post condition: none
    Return value: an object with tomorrow's values of int, month, and day
    Parameters: none
    ****************/
  public CalendarDate tomorrow() {
    if (month==1||month==3||month==5||month==7||month==8||month==10) {
      if (day==31) {
        day = 1;
        month++;
      }
      else {
        day++;
      }
    }
    if (month==4||month==6||month==9||month==11) {
      if (day==30) {
        day = 1;
        month++;
      }
      else {
        day++;
      }
    }
    if (month==2) {
      if (year%4==0 && year %100 != 0) {
        if (day==29) {
          day = 1;
          month++;
        }
      }
      else if(day==28) {
        day = 1;
        month++;
      }
      else {
        day++;
      }
    }
    if (month==12&&day==31) {
      month = 1;
      day = 1;
      year++;
    }
    return new CalendarDate(year,month,day);
  }
  
}