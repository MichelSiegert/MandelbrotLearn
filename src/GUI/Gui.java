package GUI;

import Function.Function;
import Function.FunctionPart;
import GUI.Space.FunctionSpace;
import GUI.Space.VariableSpace;
import complex_numbers.Complex;
import processing.core.PApplet;


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
				
				
		}
		
		private Grid createOutputSpace() {
				Complex c = new Complex(0, 0);
				Grid output = new Grid(1, 1, p);
				output.setXValues(2);
				output.setYValues(2);
				
				Complex cResult = c;
				Function fun = new Function();
				fun.addFunctionPart(new FunctionPart(new Complex(1, 0), 2));
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
				function.addFunctionPart(new FunctionPart(new Complex(-1, 0), 0));
				
				return function;
		}
		
		public void draw() {
				CheckForInput();
				inputSpace.draw();
				outputSpace.draw();
				varSpace.generateVariableSpace(false);
		}
		
		private void CheckForInput() {
				if (isInsideInput()) {
						Complex cursor = inputSpace.mouseToComplex();
						inputSpace.setPoint(0, cursor);
						
						Complex cursorResult = cursor;
						for (int i = 1; i < NUMITERATIONS + 1; i++) {
								cursorResult = f.CalculateValueOfFunction(cursorResult);
								inputSpace.setPoint(i, cursorResult);
						}
				} else if (isInsideFunctionInput()) {
						Complex c = new Complex(0, 0);
						Complex cursor = outputSpace.mouseToComplex();
						
						Function f = new Function();
						f.addFunctionPart(new FunctionPart(new Complex(1, 0), 2));
						f.addFunctionPart(new FunctionPart(cursor, 0));
						
						for (int i = 1; i < NUMITERATIONS; i++) {
								c = f.CalculateValueOfFunction(c);
								outputSpace.setPoint(i, c);
						}
				}
		}
		
		private boolean isInsideFunctionInput() {
				return (p.mouseX > p.width / 2 && p.mouseY > p.height / 2);
		}
		
		private boolean isInsideInput() {
				return (p.mouseX < p.width / 2 && p.mouseY < p.height / 2);
		}
}
