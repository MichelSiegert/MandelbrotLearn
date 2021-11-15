package Function;

import complex_numbers.Complex;

public class FunctionPart {
		double degree;
		Complex value;
		
		public FunctionPart(Complex value, int degree) {
				this.degree = degree;
				this.value = value;
		}
		
		public FunctionPart(Complex value, double degree) {
				this.degree = degree;
				this.value = value;
		}
		
		/**
		 * calculates derivative.
		 *
		 * @return the derivative.
		 */
		public FunctionPart calculateDerivative() {
				if (degree > 0) return new FunctionPart(value.mul(degree), degree - 1);
				else return new FunctionPart(new Complex(0, 0), 0);
		}
		
		/**
		 * calculates the integral.
		 * @return returns an integral of the functionpart.
		 */
		public FunctionPart calculateIntegral() {
				return new FunctionPart(value.div(degree), degree + 1);
		}
		
		/**
		 *  calculate the result for a given value for x.
		 * @param input the X value in the complex plane
		 * @return the result
		 */
		public Complex CalculateValue(Complex input) {
				if (((int) (degree)) == degree) {
						return input.pow((int) (degree)).mul(value);
				} else {
						return input.pow(degree).mul(value);
				}
		}
		
		/**
		 * multiply a functionpart by another functionpart.
		 *
		 * @param other the other functionpart
		 * @return the result of multiplication.
		 */
		public FunctionPart mul(FunctionPart other) {
				return new FunctionPart(this.value.mul(other.value), this.degree * other.degree);
		}
		
		/**
		 * Divides a functionpart by another functionpart if possible.
		 *
		 * @param other the other functionpart.
		 * @return the result of the division.
		 * @throws Exception currently exponents can only be integers, so the result has to be an int or will throw an exception.
		 */
		public FunctionPart div(FunctionPart other) {
				return new FunctionPart(this.value.div(other.value), this.degree / other.degree);
		}
		
		/**
		 * adds a functionpart to another functionpart.
		 *
		 * @param other the other functionpart.
		 * @return the result of addition
		 * @throws Exception can only be added if the exponent is the same.
		 */
		public FunctionPart add(FunctionPart other) throws Exception {
				if (this.degree == other.degree) {
						return new FunctionPart(this.value.add(other.value), degree);
				} else {
						throw new Exception("different degree functions can not be added!");
				}
		}
		
		/**
		 * Subtracts a function from another function
		 *
		 * @param other The other function part that it should be subtracted by.
		 * @return a new Functionpart
		 * @throws Exception only functions with the same degree can be subtracted from another.
		 */
		public FunctionPart sub(FunctionPart other) throws Exception {
				if (this.degree == other.degree) {
						return new FunctionPart(this.value.sub(other.value), degree);
				} else {
						throw new Exception("different degree functions can not be subtracted!");
				}
		}
		
		/**
		 * checks whether or not 2 functions have the same degree.
		 *
		 * @param other the other function
		 * @return true: they have the same Exp. false: they differ.
		 */
		public Boolean hasSameExp(FunctionPart other) {
				return this.degree == other.degree;
		}
}