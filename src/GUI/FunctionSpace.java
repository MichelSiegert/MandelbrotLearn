package GUI;

import complex_numbers.Complex;
import processing.core.PApplet;
import processing.core.PVector;

public class FunctionSpace {
		PApplet p;
		Grid grid;
		private int iteration;
		
		FunctionSpace(int x, int y, PApplet p) {
				this.p = p;
				grid = new Grid(x, y, p);
				grid.setXValues(2);
				grid.setYValues(2);
		}
		
		/**
		 * Generates the function space where f(x) = your function.
		 */
		public void generateFunctionSpace() {
				int col;
				for (int i = grid.x * p.width / 2; i < (grid.x + 1) * p.width / 2; i++) {
						for (int j = grid.y * p.height / 2; j < (grid.y + 1) * p.height / 2; j++) {
								if (isInsideFunctionSet(i, j)) {
										col = p.color(0x00);
								} else {
										col = p.color((float) iteration * 0xff / Gui.NUMITERATIONS, 255, 255);
								}
								p.set(i, j, col);
						}
				}
		}
		
		/**
		 * checks wether or not something is inside the functionset.
		 *
		 * @param i x coordinate.
		 * @param j y coordinate
		 * @return true: its inside the function set. False its not.
		 */
		private boolean isInsideFunctionSet(int i, int j) {
				
				Complex c = grid.mouseToComplex(new PVector(i, j));
				for (int k = 0; k < Gui.NUMITERATIONS; k++) {
						iteration = k;
						c = Gui.f.CalculateValueOfFunction(c);
						if (c.dist() > 10) return false;
				}
				return true;
		}
		
		
}
