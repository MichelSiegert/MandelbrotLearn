import GUI.Gui;
import complex_numbers.Complex;
import processing.core.PApplet;


public class Main extends PApplet {
  public static Gui gui;
  
  public static void main(String[] args) {
	gui = new Gui();
	Complex c = new Complex(0,1);
	c.mul(c).complexString();
	
	c.pow(4).complexString();
	c.div(c).complexString();
	c.add(c).complexString();
	c.sub(c).complexString();
	
  }
  
  public void settings() {
	size(800, 800);
  }
  
  // do setup in gui class
  public void setup() {
	gui.setup(this);
  }
  
  public void draw() {
	gui.draw();
  }
}