package GUI;

import Function.Function;
import Function.FunctionPart;
import complex_numbers.Complex;
import processing.core.PApplet;
import processing.core.PVector;


public class Gui extends PApplet {
		private static PApplet p;
		private Grid inputSpace;
		private Grid outputSpace;
		private Grid realSpace;
		Function f = new Function();
		public static final int NUMITERATIONS = 20;
		
		public Gui() {
				
				PApplet.main("Main", null);
		}
		
		public void setup(PApplet p) {
				Gui.p = p;
				
				f = createFunction();
				inputSpace = createInput();
				realSpace = createRealFunction();
				outputSpace = createOutputSpace();
				createMandelBrot(0, 1);
		}
		
		private void createMandelBrot(int x, int y) {
				int col;
				for (int i = x * p.width / 2; i < (x + 1) * p.width / 2; i++) {
						for (int j = y * p.height / 2; j < (y + 1) * p.height / 2; j++) {
								if (isInsideSet(i, j)) {
										col = p.color(0x88, 0, 0);
								} else {
										col = p.color(0x00);
								}
								p.set(i, j, col);
						}
				}
		}
		
		private boolean isInsideSet(int i, int j) {
				Grid MandelBrotspace = new Grid(0, 1, p);
				MandelBrotspace.setYValues(2);
				MandelBrotspace.setXValues(2);
				Complex c = MandelBrotspace.calculateValue(new PVector(i, j));
				for (int k = 0; k < NUMITERATIONS; k++) {
						c = f.CalculateValueOfFunction(c);
						if (c.dist() > 10) return false;
				}
				return true;
		}
		
		private Grid createOutputSpace() {
				Complex c = new Complex(-1, 1);
				Grid output = new Grid(1, 1, p);
				output.setXValues(2);
				output.setYValues(2);
				
				Complex cResult = c;
				
				for (int i = 0; i < NUMITERATIONS; i++) {
						cResult = f.CalculateValueOfFunction(cResult);
						output.addPoint(cResult);
				}
				
				return output;
		}
		
		private Grid createRealFunction() {
				Grid real = new Grid(1, 0, p);
				real.setXValues(5);
				real.setYValues(-Math.abs((int) f.CalculateValueOfFunction(new Complex(-5, 0)).getReal()));
				for (float i = -5; i < 5; i += 0.01) {
						real.addPoint(new Complex(i, f.CalculateValueOfFunction(new Complex(i, 0)).getReal()));
				}
				return real;
		}
		
		private Grid createInput() {
				Grid input = new Grid(0, 0, p);
				input.setXValues(1);
				input.setYValues(1);
				Complex c = new Complex(-1, 1);
				input.addPoint(c);
				return input;
		}
		
		private Function createFunction() {
				Function function = new Function();
				
				function.addFunctionPart(new FunctionPart(new Complex(1, 0), 2));
				function.addFunctionPart(new FunctionPart(new Complex(-1,0 ), 0));
				
				return function;
		}
		
		public void draw() {
				cursorPosition();
				inputSpace.draw();
				realSpace.draw();
				outputSpace.draw();
		}
		
		private void cursorPosition() {
				if (isInsideInput()) {
						Complex cursor = inputSpace.calculateValue();
						inputSpace.setPoint(0, cursor);
						
						Complex cursorResult = cursor;
						for (int i = 0; i < NUMITERATIONS; i++) {
								cursorResult = f.CalculateValueOfFunction(cursorResult);
								outputSpace.setPoint(i, cursorResult);
								
						}
				}
		}
		
		boolean isInsideInput() {
				return (p.mouseX < p.width / 2 && p.mouseY < p.height / 2);
		}
		
}