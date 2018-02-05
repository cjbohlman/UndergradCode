public class t03n01
{
    public static void main (String [] args)
    {

        int numSquares = 10,   // draw 10 nested squares
            x = 300, y = 100;  // center of first (smallest) square
        double radius = 50;    // 1st square is 100x100

                // setCanvasSize(x,y) sets the size of the canvas
                // to x by y pixels.  Default is 512x512.
        
        StdDraw.setCanvasSize(600,600);
        
                // setXscale(x1,x2) and setYscale(y1,y2) set the
                // X-axis and Y-axis ranges.  Here, I'm setting them
                // to have ranges that are 600 locations each,
                // to match the pixels set by setCanvasSize(), above.
        
        StdDraw.setXscale(0,599);
        StdDraw.setYscale(0,599);
        
                // setPenRadius(r) allows us to draw with thicker or
                // thinner lines.  As you can see, the radius values
                // are really small.
        
        StdDraw.setPenRadius(0.005);  // .002 is default

        int count = 0;
        while (count < numSquares) {
            StdDraw.square(x,y,radius);  // draw square (unfilled)
            y = y + 20;            // raise center of next square
            radius = radius + 25;  // increase size, too
            count = count + 1;
        }

        System.out.println("Close the graphics window to stop the program.");

    } // main

} // T03n02