package GUI;

import Function.Function;
import Function.FunctionPart;
import complex_numbers.Complex;
import processing.core.PApplet;
import processing.core.PVector;

public class VariableSpace {
		PApplet p;
		Grid grid;
		int iteration = 0;
		double scaler = 0.70;
		double range = 2;
		Complex goal = new Complex(-0.75, -0.1);
		
		VariableSpace(int x, int y, PApplet p) {
				this.p = p;
				grid = new Grid(x, y, p);
		}
		
		public void generateVariableSpace(boolean zoom) {
				if (zoom)
				{
						range *= scaler;
						grid.setYValues(goal.getImg() - range, goal.getImg() + range);
						grid.setXValues(goal.getReal() - range, goal.getReal() + range);
				}
				
				int col;
				p.colorMode(p.HSB);
				for (int i = grid.x * p.width / 2; i < (grid.x + 1) * p.width / 2; i++) {
						for (int j = grid.y * p.height / 2; j < (grid.y + 1) * p.height / 2; j++) {
								if (isInsideVariableSet(i, j)) {
										col = p.color(0x00, 0, 0);
								} else {
										col = p.color((float) iteration * 0xff / Gui.NUMITERATIONS, 255, 255);
								}
								p.set(i, j, col);
						}
				}
		}
		
		private boolean isInsideVariableSet(int i, int j) {
				
				Complex start = new Complex(0, 0);
				Complex c = grid.mouseToComplex(new PVector(i, j));
				
				Function fun = new Function();
				fun.addFunctionPart(new FunctionPart(new Complex(1, 0), 2));
				fun.addFunctionPart(new FunctionPart(c, 0));
				
				for (int k = 0; k < Gui.NUMITERATIONS; k++) {
						iteration = k;
						start = fun.CalculateValueOfFunction(start);
						if (start.dist() > 10) return false;
				}
				return true;
		}
}
