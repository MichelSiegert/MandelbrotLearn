package complex_numbers;

/**
 * Does calculations with complex numbers.
 * TODO finding Roots of complex numbers.
 */
public class Complex {
  private double real;
  private double img;
  
  public Complex(double r, double i) {
	real = r;
	img = i;
  }
  
  public Complex add(Complex other) {
	double r = real + other.real;
	double i = img + other.img;
	return new Complex(r, i);
  }
  
  public Complex sub(Complex other) {
	double r = real - other.real;
	double i = img - other.img;
	return new Complex(r, i);
  }
  
  // Multiplication
  //(a+ ib ) * (c + id)=  ac - bd + i(ad+bc)
  public Complex mul(Complex other) {
	double r = real * other.real - img * other.img;
	double i = img * other.real + real * other.img;
	return new Complex(r, i);
  }
  
  //division
  // (a+ib) / (c +id) =
  //((a+ib)*(c-id))/(c²- d²)
  //(ac+bd)/(c²-d²) + i(-ad + bc)/(c²-d²)
  public Complex div(Complex other) {
	double r = real * other.real + img * other.img;
	double i = img * other.real - real * other.img;
	return new Complex(r, i);
  }
  
  //c1*c1
  public Complex pow(int num) {
	num = Math.abs(num);
	if (num == 0) return new Complex(1, 0);
	Complex res = this;
	for (int i = 0; i < num - 1; i++) {
	  res = res.mul(this);
	}
	return res;
  }
  
  public void complexString() {
	System.out.println("real: " + real + ", imaginary: " + img);
  }
  
  public double getReal() { return real; }
  
  public double getImg(){ return img;}
  
  public Complex mul(int multiplier) {
    return this.mul(new Complex(multiplier,0));
  }
  
  public Complex div(int degree) {
    return this.div(new Complex(degree,0));
  }
  
  public double dist(){return Math.sqrt(img*img+real*real);}
}
