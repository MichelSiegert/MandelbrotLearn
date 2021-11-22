package Function;

import complex_numbers.Complex;

import java.util.ArrayList;

public class Function {
		private final ArrayList<FunctionPart> function = new ArrayList<FunctionPart>();
		
		public Function() {
		}
		
		/**
		 * adds a new Functionpart to the equation.
		 * @param p the new Function part
		 */
		public void addFunctionPart(FunctionPart p) {
				function.add(p);
		}
		
		/**
		 * Given an input for x: it calculates the result of the function.
		 *
		 * @param input the x-value
		 * @return The result of the function as a complex number
		 */
		public Complex CalculateValueOfFunction(Complex input) {
				Complex result = new Complex(0, 0);
				for (FunctionPart f : function) {
						result = result.add(f.CalculateValue(input));
				}
				return result;
		}
		
		/**
		 * prints the values of the function.
		 */
		public void printFunction() {
				for (FunctionPart f : function) {
						System.out.print("(" + f.value.getReal() + "+i" + f.value.getImg() + ")x^" + f.degree + "+");
				}
				System.out.println();
		}
		
		/**
		 * calculates the derivative of a function
		 * @return the derivative of the function.
		 */
		public Function getDerivative() {
				Function f = this;
				for (int i = 0; i < f.function.size(); i++) {
						f.function.set(i, f.function.get(i).calculateDerivative());
				}
				return f;
		}
		
		/**
		 * Calculating the integral of the function.
		 */
		public Function getIntegral() {
				Function f = this;
				for (int i = 0; i < f.function.size(); i++) {
						f.function.set(i, f.function.get(i).calculateIntegral());
				}
				return f;
		}
		
		
		/**
		 * adds a function to another function.
		 *
		 * @param other the other function
		 * @return the result of adding two functions
		 */
		public Function add(Function other) {
				Function result = new Function();
				for (FunctionPart f : function) {
						for (int i = 0; i < other.function.size(); i++) {
								if (f.hasSameExp(other.function.get(i))) {
										try {
												result.addFunctionPart(f.add(other.function.get(i)));
												break;
										} catch (Exception e) {
												e.printStackTrace();
										}
								}
								if (i == other.function.size() - 1) result.addFunctionPart(f);
						}
				}
				return result;
		}
		
		public int size() {
				return function.size();
		}
		
		public void setFunctionpart(int i, FunctionPart functionPart) {
				function.set(i,functionPart);
		}
}
