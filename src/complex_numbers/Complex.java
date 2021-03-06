package complex_numbers;

// TODO make more friendly for garbage collector.

/**
 * Does calculations with complex numbers.
 * TODO finding Roots of complex numbers.
 */
public class Complex {
		private final double real;
		private final double img;
		
		public Complex(double r, double i) {
				real = r;
				img = i;
		}
		
		/**
		 * adds 2 Complex numbers.
		 *
		 * @param other the number that should be added
		 * @return the result of adding 2 numbers.
		 */
		public Complex add(Complex other) {
				double r = real + other.real;
				double i = img + other.img;
				return new Complex(r, i);
		}
		
		/**
		 * add a number to a complex number that is a rational number.
		 *
		 * @param other the rational number
		 * @return the complex number.
		 */
		public Complex add(double other) {
				return new Complex(real + other, img);
		}
		
		/**
		 * Subtraction of 2 Complex numbers.
		 *
		 * @param other the other complex number.
		 * @return the result of the subtraction.
		 */
		public Complex sub(Complex other) {
				double r = real - other.real;
				double i = img - other.img;
				return new Complex(r, i);
		}
		
		/**
		 * subtract a number from a rational number
		 *
		 * @param other the rational number
		 * @return the result of the subtraction.
		 */
		public Complex sub(double other) {
				return add(-other);
		}
		
		// Multiplication
		//(a+ ib ) * (c + id)=  ac - bd + i(ad+bc)
		
		/**
		 * multiplication of 2 complex Numbers.
		 *
		 * @param other the number that this number should be multiplied with.
		 * @return the result of multiplication.
		 */
		public Complex mul(Complex other) {
				double r = real * other.real - img * other.img;
				double i = img * other.real + real * other.img;
				return new Complex(r, i);
		}
		
		/**
		 * multiplying by a real number.
		 *
		 * @param multiplier the real number.
		 * @return the complex number.
		 */
		public Complex mul(double multiplier) {
				return this.mul(new Complex(multiplier, 0));
		}
		
		
		//division
		// (a+ib) / (c +id) =
		//((a+ib)*(c-id))/(c??- d??)
		//(ac+bd)/(c??-d??) + i(-ad + bc)/(c??-d??)
		
		/**
		 * division
		 *
		 * @param other the number that it should be divided by.
		 * @return the result.
		 */
		public Complex div(Complex other) {
				double r = real * other.real + img * other.img;
				double i = img * other.real - real * other.img;
				return new Complex(r, i);
		}
		
		/**
		 * Dividing by a real number.
		 *
		 * @param div the real number.
		 * @return the result of the division.
		 */
		public Complex div(double div) {
				return this.div(new Complex(div, 0));
		}
		
		/**
		 * calculates a number to the (natural number only) power of something.
		 *
		 * @param exponent the exponent
		 * @return the result.
		 */
		public Complex pow(int exponent) {
				exponent = Math.abs(exponent);
				if (exponent == 0) return new Complex(1, 0);
				Complex res = this;
				for (int i = 0; i < exponent - 1; i++) {
						res = res.mul(this);
				}
				return res;
		}
		
		/**
		 * Slower but more versatile method to calculate Powers of something. Does floats, roots, negative.
		 * @param exponent the exponent of the complex number
		 * @return the result of this to the power of the exponent.
		 */
		public Complex pow(double exponent) {
				double angle = Math.atan2(img, real);
				double r = this.dist();
				double rRooted = Math.pow(r, exponent);
				angle *= exponent;
				double real = rRooted * Math.cos(angle);
				double img = rRooted * Math.sin(angle);
				return new Complex(real, img);
		}
		
		public double getReal() {
				return real;
		}
		
		public double getImg() {
				return img;
		}
		
		/**
		 * calculates the distance from the point (0,0)
		 *
		 * @return the distance from the origin.
		 */
		public double dist() {
				return Math.sqrt(img * img + real * real);
		}
		
		/**
		 * prints the complex number.
		 */
		public void complexString() {
				System.out.println("real: " + real + ", imaginary: " + img);
		}
		
}
