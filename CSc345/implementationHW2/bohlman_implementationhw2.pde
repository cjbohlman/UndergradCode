//Buttons
Button readFileButton;

//for reading in files
BufferedReader reader;

//Program structures
QuadTree T;
int h;
int read_file = 0;
int startTime= 0;
int highlightTime = 5000;
PFont f;  
String anim_str = "Animation Mode: On";
String insert_str = "Insert Mode: Off";
String report_str = "Report Mode: Off";
String num_segs = "Number of segments: ";
String num_segs_int;
String num_nodes = "Number of nodes: ";
String num_nodes_int;
String filename = "";
String line;
PVector report_query_ll;
PVector report_query_ur;
boolean r_query_done = false;
boolean reporting = false;
boolean ll_init = false;
boolean insert = false;
boolean anim = true;
boolean inserted = false;
insert_query iq = new insert_query();

void setup() {
  background(50);
  size(512, 590);
  smooth();
  textSize(14);
  //Create Clickable Buttons
  readFileButton = new Button("Read File", 5, 550, 115, 35);
  //create fonts
  f = createFont("Arial",12,true);
  
} //END setup

void draw() {
  clear();
  num_nodes_int = Integer.toString(iq.nodes);
  num_segs_int = Integer.toString(iq.segs);
  background(50);
  //Make the gui and display file name and other text
  strokeWeight(0);
  fill(0, 0, 50);
  rect(0, 515, 512, 75);
  drawButtons();
  textAlign(LEFT, TOP);
  text(filename, 5, 520, width, height);
  text(anim_str, 340, 521, width, height);
  text(insert_str, 340, 541, width, height);
  text(report_str, 340, 561, width, height);
  text(num_segs, 160, 521, width, height);
  text(num_segs_int, 310, 521, width, height);
  text(num_nodes, 160, 541, width, height);
  text(num_nodes_int, 285, 541, width, height);
  
  //animate tree only if file is read
  if (read_file == 1) {
      int currentTime= (int) System.currentTimeMillis();
      if ((currentTime-startTime)>highlightTime) {
        T.reset_anim(T.root);
      }
      T.printsegs(T.root, r_query_done, report_query_ll, report_query_ur);
      T.printbound(T.root);
  }
  
  //printing report query
  if (r_query_done == true) {
     strokeWeight(2);
     stroke(0, 0, 200);
     noFill();
     rect(report_query_ll.x,report_query_ur.y, (report_query_ur.x - report_query_ll.x), (report_query_ll.y - report_query_ur.y));
  }
  
} //END draw

/*******************************************************************************
 * mousePressed()
 *
 * Description: Handles logic for mouse presses.
 *              A single mouse click could be hitting any of the buttons.
 *******************************************************************************/
void mousePressed() {
  //if insert mode is on and you click on the display
  if (insert == true) {
    if (mousePressed == true) {
      Segment s = new Segment(mouseX, mouseX, mouseY);
      //check for duplicate segment
      if (T.dupl_segs(T.root, s)) {
        System.err.println("Segment already exists");
      }
      else {
        T.reset_anim(T.root);
        iq.insert(s, T.root, anim);
        iq.segs++;
        startTime= (int) System.currentTimeMillis();
        if (anim == true) inserted = true;
      }
    }
  }
  
  //if reporting is turned on and query isn't done
  if (reporting == true && r_query_done == false) {
    if (mousePressed == true && ll_init == true) {
      report_query_ur = new PVector(mouseX, mouseY);
      r_query_done = true;
      iq.report_segs(T.root, r_query_done, report_query_ll, report_query_ur);
      if (anim == true) {
        iq.report_nodes(T.root, r_query_done, report_query_ll, report_query_ur);
      }
      startTime= (int) System.currentTimeMillis();
    }
    if ((mousePressed == true && ll_init == false)) {
      report_query_ll = new PVector(mouseX, mouseY);
      r_query_done = false;
      ll_init = true;
    }
  }
  //if reporting is turned on and query is done (makes new ll pvector)
  else if (reporting == true && r_query_done == true) {
    if (mousePressed == true) {
      report_query_ll = new PVector(mouseX, mouseY);
      r_query_done = false;
      ll_init = true;
      T.reset_anim(T.root);
      T.reset_segs(T.root);
    }
  }
  
  
  //handles file reading logic
  if (readFileButton.mouseOver()) {
    if (read_file == 1) {
      System.err.println("Already read the file");
      return;
    }
    //Reads the file of points    
    reader = createReader(filename);
    //read number of points
    try {
      line = reader.readLine();
      h = int(line.trim());
        T = new QuadTree(square_calc(h));
      //read in points
      while (((line = reader.readLine()) != null) && !(line.isEmpty())) {
        String delims = ",";
        String[] pieces = line.split(delims);
        int x1 = int(pieces[0]);
        int x2 = int(pieces[1]);
        int y = int(pieces[2]);
        Segment s;
        if (x1 > x2)
          s = new Segment(x2, x1, y);
        else
          s = new Segment(x1, x2, y);
          
        iq.insert(s, T.root, false);
        iq.segs++;
      }
    }
    catch (IOException e) {
      e.printStackTrace();
      return;
    }
    read_file = 1;
  }
} //END mousePressed


/*******************************************************************************
 * drawButtons()
 *
 * Description: Draws all program buttons (defined as global variables)
 *******************************************************************************/
void drawButtons() {
  readFileButton.drawButton();
} // END drawButtons

/*******************************************************************************
 * keyPressed()
 *
 * Description: handles program behavior for key presses
 *******************************************************************************/
void keyPressed() {
  //If file hasn't been read yet, key presses default to naming file 
  if (read_file == 0) {
    if (keyCode == BACKSPACE) {
      if (filename.length() > 0) {
        filename = filename.substring(0, filename.length()-1);
      }
    } 
    else if (keyCode == DELETE) {
      filename = "";
    }
    else if (keyCode == ENTER || keyCode == RETURN) {
      return;
    }
    else if (keyCode != SHIFT && keyCode != CONTROL && keyCode != ALT) {
      filename = filename + key;
    }
  }
  else {
    //animation mode
    if (keyCode == 'a' || keyCode == 'A') {
      if (anim == true) {
        anim_str = "Animation Mode: Off";
        anim = false;
      }
      else {
        anim_str = "Animation Mode: On";
        anim = true;
      }
    }
    //insert mode
    else if (keyCode == 'i' || keyCode == 'I') {
      insert_str = "Insert Mode: On";
      report_str = "Report Mode: Off";
      r_query_done = false;
      reporting = false;
      ll_init = false;
      insert = true;
 
      T.reset_segs(T.root);
      T.reset_anim(T.root);
    }
    //report mode
    else if (keyCode == 'r' || keyCode == 'R') {
      insert_str = "Insert Mode: Off";
      report_str = "Report Mode: On";
      reporting = true;
      insert = false;
      r_query_done = false;
      ll_init = false;
      T.reset_segs(T.root);
      T.reset_anim(T.root);
      javax.swing.JOptionPane.showMessageDialog(null, "Please select the bottom left corner of query, and then the top right corner of query.");
    }
  }
} //END keyPressed

int square_calc (int h) {
  int result = 1;
  for (int i = 1; i <= h; i++) {
    result = result * 2;
  }
  return result;
}
         