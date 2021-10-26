package Function;

import complex_numbers.Complex;

public class FunctionPart {
		int degree;
		Complex value;
		
		public FunctionPart(Complex value, int degree) {
				this.degree = degree;
				this.value = value;
		}
		
		FunctionPart calculateDerivative() {
				return new FunctionPart(value.mul(degree), degree - 1);
		}
		
		FunctionPart calculateIntegral() {
				return new FunctionPart(value.div(degree), degree + 1);
		}
		
		Complex CalculateValue(Complex input) {
				return input.pow(degree).mul(value);
		}
}
