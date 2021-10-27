package GUI;

import complex_numbers.Complex;
import processing.core.PApplet;
import processing.core.PVector;

public class FunctionSpace {
		PApplet p;
		Grid grid;
		
		FunctionSpace(int x, int y, PApplet p) {
				this.p = p;
				grid = new Grid(x, y, p);
		}
		
		public void generateFunctionSpace() {
				int col;
				for (int i = grid.x * p.width / 2; i < (grid.x + 1) * p.width / 2; i++) {
						for (int j = grid.y * p.height / 2; j < (grid.y + 1) * p.height / 2; j++) {
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
				Complex c = MandelBrotspace.mouseToComplex(new PVector(i, j));
				for (int k = 0; k < Gui.NUMITERATIONS; k++) {
						c = Gui.f.CalculateValueOfFunction(c);
						if (c.dist() > 10) return false;
				}
				return true;
		}
		
		
}
