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
				generateFunctionSpace(0, 1);
				generateVariableSpace(1, 0);
		}
		
		private void generateVariableSpace(int x, int y) {
				int col;
				for (int i = x * p.width / 2; i < (x + 1) * p.width / 2; i++) {
						for (int j = y * p.height / 2; j < (y + 1) * p.height / 2; j++) {
								if (isInsideVariableSet(i, j)) {
										col = p.color(0x88, 0, 0);
								} else {
										col = p.color(0x00);
								}
								p.set(i, j, col);
						}
				}
		}
		
		private void generateFunctionSpace(int x, int y) {
				int col;
				for (int i = x * p.width / 2; i < (x + 1) * p.width / 2; i++) {
						for (int j = y * p.height / 2; j < (y + 1) * p.height / 2; j++) {
								if (isInsideFunctionSet(i, j)) {
										col = p.color(0x88, 0, 0);
								} else {
										col = p.color(0x00);
								}
								p.set(i, j, col);
						}
				}
		}
		
		private boolean isInsideFunctionSet(int i, int j) {
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
		
		private boolean isInsideVariableSet(int i, int j) {
				Grid varSpace = new Grid(1, 0, p);
				varSpace.setYValues(2);
				varSpace.setXValues(2);
				
				Complex start = new Complex(0, 0);
				Complex c = varSpace.calculateValue(new PVector(i, j));
				
				Function fun = new Function();
				fun.addFunctionPart(new FunctionPart(new Complex(1, 0), 3));
				fun.addFunctionPart(new FunctionPart(c, 0));
				
				for (int k = 0; k < NUMITERATIONS; k++) {
						start = fun.CalculateValueOfFunction(start);
						if (start.dist() > 10) return false;
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
				Grid real = new Grid(1, 1, p);
				real.setXValues(5);
				real.setYValues(-Math.abs((int) f.CalculateValueOfFunction(new Complex(-5, 0)).getReal()));
				for (float i = -5; i < 5; i += 0.01) {
						real.addPoint(new Complex(i, f.CalculateValueOfFunction(new Complex(i, 0)).getReal()));
				}
				return real;
		}
		
		private Grid createInput() {
				Grid input = new Grid(0, 0, p);
				input.setXValues(2);
				input.setYValues(2);
				Complex c = new Complex(-1, 1);
				input.addPoint(c);
				
				Complex cResult = c;
				
				for (int i = 1; i < NUMITERATIONS+1; i++) {
						cResult = f.CalculateValueOfFunction(cResult);
						input.addPoint(cResult);
				}
				return input;
		}
		
		private Function createFunction() {
				Function function = new Function();
				
				function.addFunctionPart(new FunctionPart(new Complex(1, 0), 3));
				function.addFunctionPart(new FunctionPart(new Complex(0.42, 0), 0));
				
				return function;
		}
		
		public void draw() {
				cursorPosition();
				inputSpace.draw();
				realSpace.draw();
		}
		
		private void cursorPosition() {
				if (isInsideInput()) {
						Complex cursor = inputSpace.calculateValue();
						inputSpace.setPoint(0, cursor);
						
						Complex cursorResult = cursor;
						for (int i = 1; i < NUMITERATIONS+1; i++) {
								cursorResult = f.CalculateValueOfFunction(cursorResult);
								inputSpace.setPoint(i, cursorResult);
								
						}
				}
		}
		
		boolean isInsideInput() {
				return (p.mouseX < p.width / 2 && p.mouseY < p.height / 2);
		}
		
}