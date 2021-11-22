package GUI;

import Function.Function;
import Function.FunctionPart;
import GUI.Space.FunctionSpace;
import GUI.Space.VariableSpace;
import complex_numbers.Complex;
import processing.core.PApplet;
import processing.core.PImage;

// TODO f'^1(x) > f'^2(x) if lim( q -> inifity) (f'^q(x)) = 0.

/**
 * creates the GUI. requires the last parameter to x^0 to work properly.
 */
public class Gui extends PApplet {
		
		private static PApplet p;
		boolean firstFrame = true;
		private Grid inputSpace;
		private Grid outputSpace;
		Complex outputFactor = new Complex(0, 0);
		public static Function f = new Function();
		public static final int NUMITERATIONS = 40;
		PImage varImage;
		
		VariableSpace varSpace;
		FunctionSpace funSpace;
		
		
		public Gui() {
				
				PApplet.main("Main", null);
		}
		
		public void setup(PApplet p) {
				Gui.p = p;
				f = createFunction();
				
				inputSpace = createInput();
				outputSpace = createOutputSpace();
				
				varSpace = new VariableSpace(1, 0, p);
				funSpace = new FunctionSpace(0, 1, p);
				varSpace.generateVariableSpace(false);
		}
		
		private Grid createOutputSpace() {
				Complex c = new Complex(0, 0);
				Grid output = new Grid(1, 1, p);
				output.setXValues(2);
				output.setYValues(2);
				
				Complex cResult = c;
				Function fun = new Function();
				fun.addFunctionPart(new FunctionPart(new Complex(1, 0), 1.6));
				fun.addFunctionPart(new FunctionPart(c, 0));
				
				for (int i = 1; i < NUMITERATIONS + 1; i++) {
						cResult = fun.CalculateValueOfFunction(cResult);
						output.addPoint(cResult);
				}
				return output;
		}
		
		private Grid createInput() {
				Grid input = new Grid(0, 0, p);
				input.setXValues(2);
				input.setYValues(2);
				Complex c = new Complex(-1, 1);
				input.addPoint(c);
				
				Complex cResult = c;
				
				for (int i = 1; i < NUMITERATIONS + 1; i++) {
						cResult = f.CalculateValueOfFunction(cResult);
						input.addPoint(cResult);
				}
				return input;
		}
		
		private Function createFunction() {
				Function function = new Function();
				
				function.addFunctionPart(new FunctionPart(new Complex(1, 0), 2));
				function.addFunctionPart(new FunctionPart(new Complex(-1,0), 0));
				
				return function;
		}
		
		public void draw() {
				CheckForInput();
				inputSpace.draw();
				outputSpace.draw();
				if (p.mousePressed && isInsideFunctionInput() || firstFrame) {
						firstFrame = false;
						funSpace.generateFunctionSpace(outputFactor);
						varImage = p.get(funSpace.getX() * p.width >> 1, funSpace.getY() * p.height >> 1, (p.width >> 1) - 1, (p.height >> 1) - 1);
						
				} else {
						p.image(varImage, funSpace.getX() * p.width >> 1, funSpace.getY() * p.height >> 1);
				}
		}
		
		private void CheckForInput() {
				if (isInsideInput()) {
						handleInputSpace();
				} else if (isInsideFunctionInput()) {
						handleFunctionSpace();
				}
		}
		
		private void handleFunctionSpace() {
				
				Function oldf = f;
				setupFunction();
				executeFunctionSpace();
				f = oldf;
				
		}
		
		private void executeFunctionSpace() {
				Complex c = new Complex(0, 0);
				for (int i = 1; i < NUMITERATIONS; i++) {
						c = f.CalculateValueOfFunction(c);
						
						if (outputSpace.isOutsideGrid(c)) {
								break;
						}
						outputSpace.setPoint(i, c);
				}
		}
		
		private void setupFunction() {
				outputSpace.reset();
				Complex cursor = outputSpace.mouseToComplex();
				f.setFunctionpart(f.size() - 1, new FunctionPart(cursor, 0));
				if (p.mousePressed) {
						outputFactor = cursor;
				}
		}
		
		private void handleInputSpace() {
				Complex cursor = setupInput();
				executeInputSpace(cursor);
				
		}
		
		private void executeInputSpace(Complex cursor) {
				for (int i = 1; i < NUMITERATIONS + 1; i++) {
						cursor = f.CalculateValueOfFunction(cursor);
						if (inputSpace.isOutsideGrid(cursor)) {
								break;
						}
						inputSpace.setPoint(i, cursor);
				}
		}
		
		private Complex setupInput() {
				Complex cursor = inputSpace.mouseToComplex();
				inputSpace.reset();
				inputSpace.setPoint(0, cursor);
				return cursor;
		}
		
		private boolean isInsideFunctionInput() {
				return (p.mouseX > p.width / 2 && p.mouseY > p.height / 2);
		}
		
		private boolean isInsideInput() {
				return (p.mouseX < p.width / 2 && p.mouseY < p.height / 2);
		}
}
