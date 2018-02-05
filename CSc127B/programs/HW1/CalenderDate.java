import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class CalenderDate extends TestCase {
  
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  public void testX() {
  }
  
}
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.text.SimpleDateFormat;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

public class CalendarDateTest {
 Random rand = new Random();

 private int randBetween(int start, int end) {
  return start + (int) Math.round(Math.random() * (end - start));
 }

 public GregorianCalendar randDate(int startYear, int endYear) {
  // Random date between January, 1 5000 and January 1, 1753
  GregorianCalendar gc = new GregorianCalendar();
  // Rand Year
  gc.set(gc.YEAR, randBetween(startYear, endYear));
  // Rand Month
  gc.set(gc.MONTH, randBetween(0, 11));
  // Rand Day
  gc.set(gc.DAY_OF_YEAR, randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR)));
  return gc;
 }

 @Test
 public void testGetYear() {
  int iters = 100;
  for (int i = 1; i < iters + 1; i++) {
   // Random date between January, 1 5000 and January 1, 1753
   GregorianCalendar gc = randDate(1753, 5000);
   GregorianCalendar gd = randDate(1753, 5000);

   // Create Cal Date
   CalendarDate t = new CalendarDate(gc.get(gc.YEAR), gc.get(gc.MONTH) + 1, gc.get(gc.DAY_OF_MONTH));

   CalendarDate u = new CalendarDate(gc.get(gc.YEAR), gc.get(gc.MONTH) + 1, gc.get(gc.DAY_OF_MONTH));

   // Ensure not equal to the other date
   while (gc.equals(gd)) {
    gd = randDate(1753, 5000);
   }
   CalendarDate v = new CalendarDate(gd.get(gd.YEAR), gd.get(gd.MONTH) + 1, gd.get(gd.DAY_OF_MONTH));

   // Print Testing output
   Date date = gc.getTime();
   String g = new SimpleDateFormat("MMMMMMMMMM d, yyyy", Locale.US).format(date);
   Date laterDate = gd.getTime();
   String f = new SimpleDateFormat("MMMMMMMMMM d, yyyy", Locale.US).format(laterDate);
   System.out.printf("[%d/%d] Testing Date %s and not equal to %s\n", i, iters, g, f);

   // Check Year
   assertEquals(gc.get(Calendar.YEAR), t.getYear());
   // Check Month
   assertEquals(gc.get(Calendar.MONTH) + 1, t.getMonth());
   // Check Day
   assertEquals(gc.get(Calendar.DAY_OF_MONTH), t.getDay());
   // Check String
   assertEquals(g, t.toString());
   // Check Equality
   assertTrue(t.equals(u));
   assertFalse(t.equals(v));
   // Check Tommorow
   gc.add(Calendar.DATE, 1);
   CalendarDate tomm = new CalendarDate(gc.get(gc.YEAR), gc.get(gc.MONTH) + 1, gc.get(gc.DAY_OF_MONTH));
   assertTrue(t.tomorrow().equals(tomm));
  }
 }

 public void testOutofRange() {
  ArrayList<CalendarDate> calDates = new ArrayList<CalendarDate>();
  ArrayList<String> calCheck = new ArrayList<String>();
  calDates.add(new CalendarDate(2014, 13, -6));
  calCheck.add("December 1, 2014");
  calDates.add(new CalendarDate(2016, 12, 1));
  calCheck.add("December 1, 2016");
  calDates.add(new CalendarDate(2016, 12, 31));
  calCheck.add("December 31, 2016");
  calDates.add(new CalendarDate(2015, 2, 29));
  calCheck.add("February 28, 2015");
  calDates.add(new CalendarDate(2400, 2, 29));
  calCheck.add("February 29, 2400");
  calDates.add(new CalendarDate(2352, 2, 50));
  calCheck.add("February 29, 2352");
  calDates.add(new CalendarDate(2352, 20, 50));
  calCheck.add("December 31, 2352");
  calDates.add(new CalendarDate(2352, -20, 50));
  calCheck.add("January 31, 2352");
  calDates.add(new CalendarDate(10000, -20, 50));
  calCheck.add("January 31, 10000");
  calDates.add(new CalendarDate(-10, -20, 0));
  calCheck.add("January 1, 1753");

  for (int i = 0; i < calDates.size(); i++) {
   assertEquals(calDates.get(i).toString(), calCheck.get(i));
  }
 }
}