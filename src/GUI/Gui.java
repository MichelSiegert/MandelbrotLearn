package GUI;

import processing.core.PApplet;


public class Gui extends PApplet {
  private static PApplet p;
  
  public Gui() {
	
	PApplet.main("Main", null);
  }
  
  public void setup(PApplet p) {
	Gui.p = p;
  }
  
  public void draw() {
	p.background(0);
	p.text("nice", (float)p.width/2,(float)p.height/2);
  }
  
}
