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
  Function f = new Function();
  public static final int NUMITERATIONS =50;
  public Gui() {
	
	PApplet.main("Main", null);
  }
  
  public void setup(PApplet p) {
	Gui.p = p;
	inputSpace = new Grid(0, 0, p);
	try {
	  inputSpace.setXValues(-2, 2);
	  inputSpace.setYValues(-2, 2);
	} catch (Exception e) {
	  System.out.println(e);
	}
	Complex c = new Complex(-1, 1);
	inputSpace.addPoint(c);
	outputSpace = new Grid(1, 1, p);
	try {
	  outputSpace.setXValues(-2, 2);
	  outputSpace.setYValues(-2, 2);
	} catch (Exception e) {
	  System.out.println(e);
	}
	
	f.addFunctionPart(new FunctionPart(new Complex(1, 0), 2));
	f.addFunctionPart(new FunctionPart(new Complex(-1, 0), 0));
	Complex cResult = c;
	
	for (int i=0; i< NUMITERATIONS; i++)
	{
	  cResult = f.CalculateValueOfFunction(cResult);
	  outputSpace.addPoint(cResult);
	}
  }
  
  public void draw() {
	p.background(0);
	if (isInsideInput()) {
	  Complex cursor = inputSpace.calculateValue();
	  inputSpace.setPoint(0, cursor);
	  
	  Complex cursorResult = cursor;
	  for(int i=0; i< NUMITERATIONS; i++)
	  {
	    cursorResult = f.CalculateValueOfFunction(cursorResult);
		outputSpace.setPoint(i, cursorResult);
	  
	  }
	}
	
	inputSpace.drawGrid();
	inputSpace.drawPoints();
	
	outputSpace.drawGrid();
	outputSpace.drawPoints();
  }
  
  boolean isInsideInput() {
	return (p.mouseX < p.width / 2 && p.mouseY < p.height / 2);
  }
  
}
