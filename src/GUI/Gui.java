package GUI;

import Function.Function;
import Function.FunctionPart;
import GUI.Space.FunctionSpace;
import GUI.Space.VariableSpace;
import complex_numbers.Complex;
import processing.core.PApplet;

// TODO f'^1(x) > f'^2(x) wenn lim( q -> unendlich) (f'^q(x)) = 0 ist.
// nth root of complex number...  Eulers alg => x^n-res=0, to find solutions? does this work for stupid cases?
// what if n = 2.5? x^1/2.5 = 1/(9/4) = reucsion?  so x^(9/4) -result =0? that sounds fucking slow!
// what if n = 2.5+2i?
/**
 * creates the GUI. requires the last parameter to x^0 to work properly.
 */
public class Gui extends PApplet {
		
		private static PApplet p;
		private Grid inputSpace;
		private Grid outputSpace;
		public static Function f = new Function();
		public static final int NUMITERATIONS = 30;
		
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
				funSpace.generateFunctionSpace();
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
				
				function.addFunctionPart(new FunctionPart(new Complex(1, 0), 3));
				function.addFunctionPart(new FunctionPart(new Complex(1, 0), 1));
				function.addFunctionPart(new FunctionPart(new Complex(0.34, -0.15), 0));
				
				return function;
		}
		
		public void draw() {
				CheckForInput();
				inputSpace.draw();
				outputSpace.draw();
		}
		
		private void CheckForInput() {
				if (isInsideInput()) {
						Complex cursor = inputSpace.mouseToComplex();
						inputSpace.reset();
						inputSpace.setPoint(0, cursor);
						Complex cursorResult = cursor;
						for (int i = 1; i < NUMITERATIONS + 1; i++) {
								cursorResult = f.CalculateValueOfFunction(cursorResult);
								if (inputSpace.isOutsideGrid(cursorResult)) {
										break;
								}
								inputSpace.setPoint(i, cursorResult);
						}
				} else if (isInsideFunctionInput()) {
						Complex c = new Complex(0, 0);
						outputSpace.reset();
						Complex cursor = outputSpace.mouseToComplex();
						Function oldf = f;
						f.setFunctionpart(f.size() - 1, new FunctionPart(cursor, 0));
						
						for (int i = 1; i < NUMITERATIONS; i++) {
								c = f.CalculateValueOfFunction(c);
								if (outputSpace.isOutsideGrid(c)) {
										break;
								}
								outputSpace.setPoint(i, c);
						}
						f = oldf;
				}
		}
		
		private boolean isInsideFunctionInput() {
				return (p.mouseX > p.width / 2 && p.mouseY > p.height / 2);
		}
		
		private boolean isInsideInput() {
				return (p.mouseX < p.width / 2 && p.mouseY < p.height / 2);
		}
}
