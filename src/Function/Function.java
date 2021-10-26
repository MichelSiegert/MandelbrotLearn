package Function;

import complex_numbers.Complex;

import java.util.ArrayList;

public class Function {
		private final ArrayList<FunctionPart> function = new ArrayList<FunctionPart>();
		
		public Function() {
		}
		
		public void addFunctionPart(FunctionPart p) {
				function.add(p);
		}
		
		public Complex CalculateValueOfFunction(Complex input) {
				Complex result = new Complex(0, 0);
				for (FunctionPart f : function) {
						result = result.add(f.CalculateValue(input));
				}
				return result;
		}
		
		public void printFunction() {
				for (FunctionPart f : function) {
						System.out.print("(" + f.value.getReal() + "+i" + f.value.getImg() + ")x^" + f.degree + "+");
				}
				System.out.println();
		}
		
		public Function getDerivative() {
				Function f = this;
				for (int i = 0; i < f.function.size(); i++) {
						f.function.set(i, f.function.get(i).calculateDerivative());
				}
				return f;
		}
		
		public Function getIntegral() {
				Function f = this;
				for (int i = 0; i < f.function.size(); i++) {
						f.function.set(i, f.function.get(i).calculateIntegral());
				}
				return f;
		}
		
}
