package GUI;

import complex_numbers.Complex;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class Grid {
		private PApplet p;
		public int x, y;
		private double x1, x2;
		private double y1, y2;
		private ArrayList<Complex> points = new ArrayList<>();
		
		Grid(int x, int y, PApplet p) {
				this.x = x;
				this.y = y;
				this.p = p;
		}
		
		public void setXValues(double x) {
				this.x1 = -x;
				this.x2 = x;
		}
		
		public void setXValues(double lower, double upper) {
				this.x1 = lower;
				this.x2 = upper;
		}
		
		public void setYValues(double y) {
				this.y1 = -y;
				this.y2 = y;
		}
		
		public void setYValues(double lower, double upper) {
				this.y1 = lower;
				this.y2 = upper;
		}
		
		
		public void addPoint(Complex p) {
				if (false == points.contains(p))
						points.add(p);
		}
		
		private void drawPoints() {
				for (Complex c : points) {
						PVector position = calculatePosition(c);
						p.stroke(0x00);
						p.strokeWeight(3);
						p.point(position.x, position.y);
						p.strokeWeight(1);
				}
		}
		
		private PVector calculatePosition(Complex point) {
				//value on a scale from -5 to 5, and I want to map it to width/2
				// same for height.
				float xPos = PApplet.constrain(PApplet.map((float) point.getReal(), (float) x1, (float) x2, x * p.width / 2, x * p.width / 2 + p.width / 2), x * p.width / 2, (x + 1) * p.width / 2);
				float yPos = PApplet.constrain(PApplet.map((float) point.getImg(), (float) y1, (float) y2, y * p.height / 2, y * p.height / 2 + p.height / 2), y * p.height / 2, (y + 1) * p.height / 2);
				return new PVector(xPos, yPos);
		}
		
		public Complex mouseToComplex() {
				double real = PApplet.constrain(PApplet.map(p.mouseX, x * p.width / 2, (x + 1) * p.width / 2, (float) x1, (float) x2), (float) x1, (float) x2);
				double img = PApplet.constrain(PApplet.map(p.mouseY, y * p.height / 2, (y + 1) * p.height / 2, (float) y1, (float) y2), (float) x1, (float) x2);
				return new Complex(real, img);
		}
		
		public Complex mouseToComplex(PVector vec) {
				double real = PApplet.map(vec.x, x * p.width / 2, (x + 1) * p.width / 2, (float) x1, (float) x2);
				double img = PApplet.map(vec.y, y * p.height / 2, (y + 1) * p.height / 2, (float) y1, (float) y2);
				return new Complex(real, img);
		}
		
		private void drawGrid() {
				p.noStroke();
				p.fill(0xff);
				p.rect(x * p.width / 2, y * p.height / 2, p.width / 2, p.height / 2);
				p.pushMatrix();
				p.stroke(0x00);
				p.noFill();
				p.translate(x * p.width / 2 + p.width / 4, y * p.height / 2 + p.height / 4);
				p.line(-p.width / 4, 0, p.width / 4, 0);
				p.line(0, -p.height / 4, 0, p.height / 4);
				p.popMatrix();
		}
		
		public void setPoint(int i, Complex calculateValue) {
				points.set(i, calculateValue);
		}
		
		public void draw() {
				drawGrid();
				drawPoints();
		}
}
