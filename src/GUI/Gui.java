package GUI;

import complex_numbers.Complex;
import processing.core.PApplet;


public class Gui extends PApplet {
  private static PApplet p;
  private Grid inputSpace;
  private Grid outputSpace;
  
  public Gui() {
	
	PApplet.main("Main", null);
  }
  
  public void setup(PApplet p) {
	Gui.p = p;
	inputSpace = new Grid(0, 0, p);
	inputSpace.setXValues(-5, 5);
	inputSpace.setYValues(-5, 5);
	inputSpace.addPoint(new Complex(-1, 1));
	
	outputSpace = new Grid(1, 1, p);
  }
  
  public void draw() {
	p.background(0);
	inputSpace.drawGrid(p);
	inputSpace.drawPoints();
	
	outputSpace.drawGrid(p);
  }
  
  
}
