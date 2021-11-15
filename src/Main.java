import GUI.Gui;
import complex_numbers.Complex;
import processing.core.PApplet;

public class Main extends PApplet {
		public static Gui gui;
		
		public static void main(String[] args) {
				gui = new Gui();
				Complex c = new Complex(Math.E,0);
				c.pow(PI).complexString();
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