//Buttons
Button restartButton;
Button readFileButton;
Button sortButton;
Button grahamScanButton;
Button convexHullButton;

//for reading in/out files
BufferedReader reader;
PrintWriter writer;
String line;

//Program structures
PVector[] array;
int array_size;
ArrayStack a;
PVector sorted_heap[];
int read_done = 0;
int sort_done = 0;
int graham_done = 0;
int convex_done = 0;
int graham_i = 3;
PFont f;  
String filename = "";

void setup() {
  background(255);
  size(800, 500);
  smooth();
  textSize(16);
  //Create Clickable Buttons
  restartButton = new Button("Restart", 15, 450, 115, 35);
  readFileButton = new Button("Read File", 145, 450, 115, 35);
  sortButton = new Button("Sort", 275, 450, 115, 35);
  grahamScanButton = new Button("Graham Scan", 405, 450, 115, 35);
  convexHullButton = new Button("Convex Hull", 535, 450, 115, 35);
  //open file
  writer = createWriter("points.out");
  //create fonts
  f = createFont("Arial",12,true); 
} //END setup

void draw() {
  //Make the gui and display file name
  fill(0);
  rect(0, 400, 800, 100);
  fill(256,256,256);
  drawButtons();
  fill(256,256,256);
  textAlign(LEFT, TOP);
  text(filename, 10, 410, width, height);
  fill(250,0,0);  
} //END draw

/*******************************************************************************
 * restart()
 *
 * Description: clears all global variables so that the user can restart the
 *              program without stopping the entire thing.
 *******************************************************************************/
void restart() {
  read_done = 0;
  sort_done = 0;
  graham_done = 0;
  convex_done = 0;
  graham_i = 3;
  setup();
} //END restart

/*******************************************************************************
 * mousePressed()
 *
 * Description: Handles logic for mouse presses.
 *              A single mouse click could be hitting any of the buttons.
 *******************************************************************************/
void mousePressed() {
  // user presses "Restart"
  if (restartButton.mouseOver()) {
    restart();
  }
  
  // user presses "Read File"
  else if (readFileButton.mouseOver()) {
    //Reads the file of points
    if (read_done == 1) {
      System.err.println("File has already been read.");
      return;
    }
    read_done = 1;    
    reader = createReader(filename);
    //read number of points
    try {
      line = reader.readLine();
      array_size = int(line.trim());
      if (array_size < 3) {
        System.err.println("Number of points needs to be 3 or more, first line had number less than three");
        return;
      }
      //read in points
      array = new PVector[array_size];
      int iterator = 1;     
      while ((line = reader.readLine()) != null) {
        String[] pieces = line.split("\\s+");
        int x = int(pieces[0]);
        int y = int(pieces[1]);
        //create new vector of points
        PVector pv = new PVector(x,y);
        //add to array
        array[iterator - 1] = pv;
        //plot points
        fill(0,0,255);
        ellipse(x,y,5,5);
        iterator++;
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  // user presses "Sort"
  else if (sortButton.mouseOver()) {
    if (read_done == 0) {
      System.err.println("Buttons pressed out of order (Read File => Sort => Graham Scan to completion => Convex Hull).");
      return;
    }
    if (sort_done == 1) {
      System.err.println("Points have already been sorted.");
      return;
    }
    sort_done = 1;
    //find lowest x cord
      for (int i = 1; i < array_size; ++i) {
        if (array[i].y < array[0].y) {
          PVector tmp = array[i];
          array[i] = array[0];
          array[0] = tmp;
        }
      }
      //plot first x cord
      textFont(f,12);                  
      fill(0);                         
      text("p0",array[0].x+10,array[0].y-8);   
      //Sort array by polar angle in a minHeap
      MinHeap heap = sort_by_polar_angle(array);
      sorted_heap = new PVector[array_size];
      sorted_heap[0] = array[0];
      //extract min points from heap
      for (int i = 1; i < array_size; ++i) {
        String foo = "p"+i;
        textFont(f,12);                 
        fill(0);                        
        PVector next = heap.HeapExtractMax();
        sorted_heap[i] = next;
        //write text next to points
        text(foo,next.x+10,next.y-8);   
        fill(0);               
        //draw lines between oints
        line(sorted_heap[i].x,sorted_heap[i].y,sorted_heap[i-1].x,sorted_heap[i-1].y);  
      }
      //add first 3 points to convex hull stack and plot
      //also draw line between last points
      fill(0);                         
      line(sorted_heap[0].x,sorted_heap[0].y,sorted_heap[array_size-1].x,sorted_heap[array_size-1].y); 
      a = new ArrayStack(array_size);
      fill(0,255,0);
      ellipse(sorted_heap[0].x,sorted_heap[0].y,8,8);
      a.push(sorted_heap[0],0);
      fill(0,255,0);
      ellipse(sorted_heap[1].x,sorted_heap[1].y,8,8);
      a.push(sorted_heap[1],1);
      fill(0,255,0);
      ellipse(sorted_heap[2].x,sorted_heap[2].y,8,8);
      a.push(sorted_heap[2],2);
  }
  
  // user presses "Graham Scan"
  else if (grahamScanButton.mouseOver() ) {      
    if (read_done == 0 || sort_done == 0) {
            System.err.println("Buttons pressed out of order (Read File => Sort => Graham Scan to completion => Convex Hull).");
      return;
    }
    if (graham_done == 1) {
            System.err.println("Graham scan has already finished.");
      return;
    }
      //Perform one iteration of graham scan
      
      if (graham_i >= array_size) {
        System.out.println("Graham scan has finished!");
        graham_done = 1;
        return;
      }
      //check left turn
      while (left_turn_check(a.next_to_top(a.size),a.top(a.size),sorted_heap[graham_i])) {
          fill(255,0,0);
          ellipse(a.top(a.size).x,a.top(a.size).y,8,8);
          a.pop();
      }
      //push next point
      fill(0,255,0);
      ellipse(sorted_heap[graham_i].x,sorted_heap[graham_i].y,8,8);
      a.push(sorted_heap[graham_i],graham_i);
      graham_i++;
      
      if (graham_i >= array_size) {
        System.out.println("Graham scan finished!");
        graham_done = 1;
        return;
      }
  }
  
  // user presses "Convex Hull"
  else if (convexHullButton.mouseOver()) {    
    if (read_done == 0 || sort_done == 0 || graham_done == 0) {
      System.err.println("Buttons pressed out of order (Read File => Sort => Graham Scan to completion => Convex Hull). Maybe Graham scan hasn't completed?");
      return;
    }
    //Draws convex hull of graham scan
    for (int i = 0; i < a.size; ++i) {
        if (i == a.size - 1) {
          stroke(0,0,255);                        
          strokeWeight(3);
          line(a.get(i).x,a.get(i).y,a.get(0).x,a.get(0).y);   
          writer.print(a.get(i).x+" "+a.get(i).y+" "+i);
        }
        else {
          stroke(0,0,255); 
          strokeWeight(3);
          line(a.get(i).x,a.get(i).y,a.get(i+1).x,a.get(i+1).y);   
          writer.print(a.get(i).x+" "+a.get(i).y+" "+i);
          writer.println();
        }
        
      }
      writer.flush();
      writer.close();
  }
} //END mousePressed


/*******************************************************************************
 * drawButtons()
 *
 * Description: Draws all program buttons (defined as global variables)
 *******************************************************************************/
void drawButtons() {
  restartButton.drawButton();
  readFileButton.drawButton();
  sortButton.drawButton();
  grahamScanButton.drawButton();
  convexHullButton.drawButton();
 
}
/**
   * Method: sort_by_polar_angle
   * Makes a minHeap sorted by polar angle
   */
MinHeap sort_by_polar_angle(PVector[] a) {
        MinHeap heap = new MinHeap(a);
        return heap;
}
    
    /**
   * Method: left_turn_check
   * Checks left turn, return boolean
   * Parameters: 3 PVectors
   */
    boolean left_turn_check(PVector p0, PVector p1, PVector p2) {
      float x1 = p1.x - p0.x;
      float y1 = p1.y - p0.y;
      float x2 = p2.x - p0.x;
      float y2 = p2.y - p0.y;
      
      if ((x1*y2 - x2*y1) < 0) {
        return true;
      }
      else
        return false;
    }
    
    /**
   * Method: keyPressed
   * keyPressed gets file name
   */
    void keyPressed() {
  if (keyCode == BACKSPACE) {
    if (filename.length() > 0) {
      filename = filename.substring(0, filename.length()-1);
    }
  } 
  else if (keyCode == DELETE) {
    filename = "";
  }
    else if(keyCode == ENTER || keyCode == RETURN) {
      return;
    }
  else if (keyCode != SHIFT && keyCode != CONTROL && keyCode != ALT) {
    filename = filename + key;
  }
}
         