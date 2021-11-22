import GUI.Gui;
import processing.core.PApplet;

// 945 LOC!
public class Main extends PApplet {
		public static Gui gui;
		
		public static void main(String[] args) {
				gui = new Gui();
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