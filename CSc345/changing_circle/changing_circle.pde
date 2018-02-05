void setup() {
  size(500, 500);
  stroke(255);
}

void draw() {
  background(255,0,0);
  if (mousePressed) {
    fill(0);
  }
  ellipse(width/2,height/2,100,100);
}