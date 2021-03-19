import java.util.Objects;


/** Quaternions. Basic operations. */
public class Quaternion {

   //checklist 5
   public static final double ALPHA = 0.00001; //Treshold for checking zero
   public static final double BETA = 1000000.0; //for rounding

   private double a, b, c, d;


   //source: https://stackoverflow.com/questions/22833515/rounding-to-6-decimal-places-using-math-round-method-in-java-android/22833857
   private double round(double k){
      return Math.round(k*BETA)/BETA;
   }

   private void round(){
      this.a = round(a);
      this.b = round(b);
      this.c = round(c);
      this.d = round(d);
   }



   /** Constructor from four double values.
    * @param a real part
    * @param b imaginary part i
    * @param c imaginary part j
    * @param d imaginary part k
    */
   public Quaternion (double a, double b, double c, double d) {
      // TODO!!! Your constructor here!
      this.a = a;
      this.b = b;
      this.c = c;
      this.d = d;
      this.round();
   }



   /** Real part of the quaternion.
    * @return real part
    */
   public double getRpart() {

      return a;
   }

   /** Imaginary part i of the quaternion. 
    * @return imaginary part i
    */
   public double getIpart() {

      return b;
   }

   /** Imaginary part j of the quaternion. 
    * @return imaginary part j
    */
   public double getJpart() {

      return c;
   }

   /** Imaginary part k of the quaternion. 
    * @return imaginary part k
    */
   public double getKpart() {

      return d;
   }

   /** Conversion of the quaternion to the string.
    * @return a string form of this quaternion: 
    * "a+bi+cj+dk"
    * (without any brackets)
    */
   @Override
   public String toString() {

      if (!this.isZero()){

         StringBuffer buf = new StringBuffer();
         if(!isZero(a)){
            buf.append(a);
         }
         if(!isZero(b)){
            if(buf.length()>0 && b > 0){
               buf.append("+");
            }
            buf.append(b + "i");
         }
         if(!isZero(c)){
            if(buf.length()>0 && c > 0){
               buf.append("+");
            }
            buf.append(c + "j");
         }
         if(!isZero(d)){
            if(buf.length()>0 && d > 0){
               buf.append("+");
            }
            buf.append(d + "k");
         }
         return buf.toString();
      }

      return "0";


   }


   /** Conversion from the string to the quaternion. 
    * Reverse to <code>toString</code> method.
    * @throws IllegalArgumentException if string s does not represent 
    *     a quaternion (defined by the <code>toString</code> method)
    * @param s string of form produced by the <code>toString</code> method
    * @return a quaternion represented by string s
    */
   public static Quaternion valueOf (String s) {

      double r = 0;
      double i = 0;
      double j = 0;
      double k = 0;

      //delete white space before classified as not quaternion

      s = s.replaceAll("\\s+","");

      if(s.equals("") ){
         throw new IllegalArgumentException("The given string does not represent a quaternion: empty string ");
      }

      if( s.equals("0")){
         return new Quaternion(0,0,0,0);
      }


      //regex solution https://enos.itcollege.ee/~japoia/algorithms/examples/Qregex.java

      String orop = "|";
      String seq = "\\d+";
      String deli = "\\.";
      String fixp = "(" + seq +
              orop + seq + deli +
              orop + deli + seq +
              orop + seq + deli + seq + ")";
      String floa = "(" + fixp +
              orop + fixp + "[eE]" + "[+-]?"+ seq + ")";
      String doub = "(" +  floa +
              orop + floa + "[dD]" + ")";
      String real = "(" + doub +
              orop + "[+-]" + doub + ")";
      String imag = "(" + "[+-]" + doub + "[iI]" +
              orop + "[+-]?" + "[iI]" + ")";
      String jmag = "(" + "[+-]" + doub + "[jJ]" +
              orop + "[+-]?" + "[jJ]" + ")";
      String kmag = "(" + "[+-]" + doub + "[kK]" +
              orop + "[+-]?" + "[kK]" + ")";
      String expr = "(" + real +
              orop + real + imag +
              orop + real + imag + jmag +
              orop + real + imag + jmag + kmag +
              orop + real + imag + kmag +
              orop + real + jmag +
              orop + real + jmag + kmag +
              orop + real + kmag +
              orop + imag +
              orop + imag + jmag +
              orop + imag + jmag + kmag +
              orop + imag + kmag +
              orop + jmag +
              orop + jmag + kmag +
              orop + kmag + ")";

      String pattern = "^" + expr + "$";

      if(s.matches(pattern)){

         //source: forum discussion by Jaanus Pöial
         s = s.toLowerCase().replaceAll("-", "+-");
         if(s.startsWith("+")){
            s = s.substring(1); //source: https://stackoverflow.com/questions/4503656/java-removing-first-character-of-a-string
         }
         s = s.replaceAll("\\+", "/");


         String[] letters = s.split("/");
         for(String c : letters){
            if(c.charAt(c.length()-1) == 'i'){
               i = Double.parseDouble(c.substring(0,c.length()-1));
            }
            else if(c.charAt(c.length()-1) == 'j'){
               j = Double.parseDouble(c.substring(0,c.length()-1));
            }
            else if(c.charAt(c.length()-1) == 'k'){
               k = Double.parseDouble(c.substring(0,c.length()-1));
            }
            else{
               r = Double.parseDouble(c);
            }
         }

         return new Quaternion(r, i, j, k);
      }
      else{
         throw new IllegalArgumentException("The given string does not represent a quaternion " + s);
      }
   }

   /** Clone of the quaternion.
    * @return independent clone of <code>this</code>
    */
   @Override
   public Object clone() throws CloneNotSupportedException {
      if(this.getClass() != new Quaternion(0,0,0,0).getClass()){
         throw new CloneNotSupportedException("Clone is not supported because object doesn't belong in the same class");
      }
      return new Quaternion(a, b, c, d); // TODO!!! // TODO!!!
   }

//   https://stackoverflow.com/questions/18260213/how-to-test-if-a-double-is-zero/18260267
   private boolean isZero(double k){
      return k>= -ALPHA && k <= ALPHA;
   }

   /** Test whether the quaternion is zero. 
    * @return true, if the real part and all the imaginary parts are (close to) zero
    * @param
    */
   public boolean isZero() {
      return isZero(a) && isZero(b) && isZero(c) && isZero(d);

   }

   /** Conjugate of the quaternion. Expressed by the formula 
    *     conjugate(a+bi+cj+dk) = a-bi-cj-dk
    * @return conjugate of <code>this</code>
    */
   public Quaternion conjugate() {
      return new Quaternion(a, -b,-c,-d); // TODO!!!
   }

   /** Opposite of the quaternion. Expressed by the formula 
    *    opposite(a+bi+cj+dk) = -a-bi-cj-dk
    * @return quaternion <code>-this</code>
    */
   public Quaternion opposite() {
      return new Quaternion(-a,-b,-c,-d); // TODO!!!
   }

   /** Sum of quaternions. Expressed by the formula 
    *    (a1+b1i+c1j+d1k) + (a2+b2i+c2j+d2k) = (a1+a2) + (b1+b2)i + (c1+c2)j + (d1+d2)k
    * @param q addend
    * @return quaternion <code>this+q</code>
    */
   public Quaternion plus (Quaternion q) {

      double r = a + q.getRpart();
      double i = b + q.getIpart();
      double j = c + q.getJpart();
      double k = d + q.getKpart();
      return new Quaternion(r,i,j,k);
   }

   /** Product of quaternions. Expressed by the formula
    *  (a1+b1i+c1j+d1k) * (a2+b2i+c2j+d2k) = (a1a2-b1b2-c1c2-d1d2) + (a1b2+b1a2+c1d2-d1c2)i +
    *  (a1c2-b1d2+c1a2+d1b2)j + (a1d2+b1c2-c1b2+d1a2)k
    * @param q factor
    * @return quaternion <code>this*q</code>
    */

   public Quaternion times (Quaternion q) {
      double r = a*q.getRpart() - b*q.getIpart() - c*q.getJpart() - d*q.getKpart();
      double i = a*q.getIpart() + b*q.getRpart() + c*q.getKpart() - d*q.getJpart();
      double j = a*q.getJpart() - b*q.getKpart() + c*q.getRpart() + d*q.getIpart();
      double k = a*q.getKpart() + b*q.getJpart() - c*q.getIpart() + d*q.getRpart();
      return new Quaternion(r,i,j,k);  // TODO!!!
   }
   /** Multiplication by a coefficient.
    * @param r coefficient
    * @return quaternion <code>this*r</code>
    */
   public Quaternion times (double r) {
      return new Quaternion(a*r, b*r, c*r, d*r); // TODO!!!
   }

//   source: https://introcs.cs.princeton.edu/java/32class/Quaternion.java.html

   /** Inverse of the quaternion. Expressed by the formula
    *     1/(a+bi+cj+dk) = a/(a*a+b*b+c*c+d*d) + 
    *     ((-b)/(a*a+b*b+c*c+d*d))i + ((-c)/(a*a+b*b+c*c+d*d))j + ((-d)/(a*a+b*b+c*c+d*d))k
    * @return quaternion <code>1/this</code>
    */
   public Quaternion inverse() {
      if(this.isZero()){
         throw new ArithmeticException("Zero quaternion cannot be inverted" + this.toString());
      }

      double inv = (a*a + b*b + c*c + d*d);

      double r = (a)/inv;
      double i = (-b)/inv;
      double j = (-c)/inv;
      double k = (-d)/inv;

      return new Quaternion(r,i,j,k);
   }

   /** Difference of quaternions. Expressed as addition to the opposite.
    * @param q subtrahend
    * @return quaternion <code>this-q</code>
    */
   public Quaternion minus (Quaternion q) {

      double r = a - q.getRpart();
      double i = b - q.getIpart();
      double j = c - q.getJpart();
      double k = d - q.getKpart();
      return new Quaternion(r, i, j, k);
   }

   /** Right quotient of quaternions. Expressed as multiplication to the inverse.
    * @param q (right) divisor
    * @return quaternion <code>this*inverse(q)</code>
    */
   public Quaternion divideByRight (Quaternion q) {
      if(q.isZero()){
         throw new ArithmeticException("Cannot construct inverse for zero quaternion " + q.toString());
      }
      else if(q.equals(this)){
         return new Quaternion(1,0,0,0);
      }
      return this.times(q.inverse());
   }

   /** Left quotient of quaternions.
    * @param q (left) divisor
    * @return quaternion <code>inverse(q)*this</code>
    */
   public Quaternion divideByLeft (Quaternion q) {
      if(q.isZero()){
         throw new ArithmeticException("Cannot construct an inverse for a zero quaternion: " + q.toString());
      }
      else if(q.equals(this)){
         return new Quaternion(1,0,0,0);
      }
      return (q.inverse()).times(this);
   }
   
   /** Equality test of quaternions. Difference of equal numbers
    *     is (close to) zero.
    * @param qo second quaternion
    * @return logical value of the expression <code>this.equals(qo)</code>
    */
   @Override
   public boolean equals (Object qo) {
      if (qo == null || qo.getClass() != this.getClass()) {
         return false;
      }

      if(((Quaternion) qo).isZero() && this.isZero()){

         return true;
      }
      return this.toString().equals(qo.toString());
   }

   /** Dot product of quaternions. (p*conjugate(q) + q*conjugate(p))/2
    * @param q factor
    * @return dot product of this and q
    */
   public Quaternion dotMult (Quaternion q) {
      return (this.times(q.conjugate())).plus(q.times(this.conjugate())).times(0.5);
   }

   /** Integer hashCode has to be the same for equal objects.
    * @return hashcode
    */
   @Override
   public int hashCode() {

      return Objects.hash(a, b, c, d); // TODO!!!
   }

   /** Norm of the quaternion. Expressed by the formula 
    *     norm(a+bi+cj+dk) = Math.sqrt(a*a+b*b+c*c+d*d)
    * @return norm of <code>this</code> (norm is a real number)
    */
   public double norm() {
      return round(Math.sqrt(a*a + b*b + c*c + d*d));  // TODO!!!
   }

   /** Main method for testing purposes. 
    * @param arg command line parameters
    */
   public static void main (String[] arg) {
      Quaternion arv1 = new Quaternion (-1., 1, 2., -2.);
      if (arg.length > 0)
         arv1 = valueOf (arg[0]);
      System.out.println ("first: " + arv1.toString());
      System.out.println ("real: " + arv1.getRpart());
      System.out.println ("imagi: " + arv1.getIpart());
      System.out.println ("imagj: " + arv1.getJpart());
      System.out.println ("imagk: " + arv1.getKpart());
      System.out.println ("isZero: " + arv1.isZero());
      System.out.println ("conjugate: " + arv1.conjugate());
      System.out.println ("opposite: " + arv1.opposite());
      System.out.println ("hashCode: " + arv1.hashCode());
      Quaternion res = null;
      try {
         res = (Quaternion)arv1.clone();
      } catch (CloneNotSupportedException e) {};
      System.out.println ("clone equals to original: " + res.equals (arv1));
      System.out.println ("clone is not the same object: " + (res!=arv1));
      System.out.println ("hashCode: " + res.hashCode());
      res = valueOf (arv1.toString());
      System.out.println ("string conversion equals to original: " 
         + res.equals (arv1));
      Quaternion arv2 = new Quaternion (1., -2.,  -1., 2.);
      if (arg.length > 1)
         arv2 = valueOf (arg[1]);
      System.out.println ("second: " + arv2.toString());
      System.out.println ("hashCode: " + arv2.hashCode());
      System.out.println ("equals: " + arv1.equals (arv2));
      res = arv1.plus (arv2);
      System.out.println ("plus: " + res);
      System.out.println ("times: " + arv1.times (arv2));
      System.out.println ("minus: " + arv1.minus (arv2));
      double mm = arv1.norm();
      System.out.println ("norm: " + mm);
      System.out.println ("inverse: " + arv1.inverse());
      System.out.println ("divideByRight: " + arv1.divideByRight (arv2));
      System.out.println ("divideByLeft: " + arv1.divideByLeft (arv2));
      System.out.println ("dotMult: " + arv1.dotMult (arv2));
   }
}
// end of file
