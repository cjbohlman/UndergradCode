/* Hello127B:  Tests that DrJava and stdlib.jar are ready to go.
 * Create for use in CSc 127B, Fall 2015, Section #1.
 *
 * Compile:  javac -cp .:/cs/contrib/linux/stdlib.jar Hello127B.java
 * Execute:  java -cp .:/cs/contrib/linux/stdlib.jar Hello127B
 *
 * Author:  L. McCann, 2015/08/08
 */

import java.awt.Color;
import java.awt.Font;

public class Hello127B {

    public static void main (String [] args) {

        final int   DRAWING_WIDTH = 800;           // draw within 800x800 sq.
        final Color uaRed = new Color (171,5,32);  // Cochineal Red (PMS 200 C)
        final Color uaBlue = new Color (12,35,75); // Arizona Blue

                // The official UA fonts are Milo OT and Milo Serif OT,
                // but they're not generally available, so we'll use Verdana,
                // one of the suggested alternates.  If your computer doesn't
                // have Verdana, Java will substitute a default font.

        Font label = new Font ("Verdana", Font.BOLD, 64);  // our label's font

                // Set up the graphics environment

        StdDraw.setCanvasSize(DRAWING_WIDTH, DRAWING_WIDTH);  // a square
        StdDraw.setXscale(-DRAWING_WIDTH/2,DRAWING_WIDTH/2);  // 0,0 at center
        StdDraw.setYscale(-DRAWING_WIDTH/2,DRAWING_WIDTH/2);  

                // Draw a thick blue circle with centered bold red text, using
                // official UA blue, red, and font, of course!

        StdDraw.setPenColor(uaBlue);
        StdDraw.filledCircle(0,0,0.4*DRAWING_WIDTH);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(0,0,0.3*DRAWING_WIDTH);

        StdDraw.setFont(label);
        StdDraw.setPenColor(uaRed);
        StdDraw.text(0,0,"CSc 127B!");

    } // main

} // Hello127B