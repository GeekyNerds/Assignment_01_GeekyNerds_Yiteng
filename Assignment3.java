import java.util.Arrays;

public class Assignment33 {
	
	public static void main(String[] args) {
	Assignment33 a3 = new Assignment33();
	
	//run Question 3 remove vowels from string input
	a3.removeVowelsFromString("aeiou & AEIOU");
	
	//run Question 4 check two strings are anagram or not
	a3.checkIfTwoStringsAreAnagrams("abc","b c A");
	
	//run Question 5 calculator
	Calculator calculator = new Calculator();
	calculator.addition(3, 3);
	calculator.subtraction(2, 3);
	calculator.multiplication(2,3);
	calculator.division(2,6);
	calculator.squareRoot(81);
	calculator.square(-1);
	calculator.cube(-1);
	calculator.convertFahrenheitToCelsius(100); 
	calculator.convertFeetToInches(1);
	calculator.solveQuadraticEquation(2,5,3);
			
	
		
	}	
	
	/*-------Question 1: find error-------
	 * There are two major mistakes in Question 1:
	 * 1. Two constructors have the same type & number of parameter, 
	 *    so Java cannot not distinguish which one to call.
	 * 2. Missing return type "String" before the method setName(String name).     
	 */
	
	
	
	/*-------Question 2: find error-------
	 * There are one major mistake in Question 2:
	 * 1. The void method type cannot return a value, 
	 *    correction: change the "void" to "String" before the method getTime(). 
	 */
	
	
	
	/*-------Question 3: remove vowels-------*/
	
	public void removeVowelsFromString(String input) {
		//use replaceAll() method to replace vowels with ""
		String result = input.replaceAll("[aeiouAEIOU]", "");
		System.out.printf("Remove vowels from '%s', result is: '%s'\n",input,result);		
	}
	
	
	
	/*-------Question 4: check if two strings are anagram-------*/
	public boolean checkIfTwoStringsAreAnagrams(String s1,String s2) {
		String str1 = s1.replaceAll(" ", ""); //remove spaces in s1
		String str2 = s2.replaceAll(" ", ""); //remove spaces in s2

		char[] charArrayS1 = str1.toLowerCase().toCharArray(); //convert to lower-case & char array
		char[] charArrayS2 = str2.toLowerCase().toCharArray();
		Arrays.sort(charArrayS1); // sort by order
		Arrays.sort(charArrayS2);
		boolean isAnagram;
		if(charArrayS1.length!=charArrayS2.length) { //if length do not equal, not anagram 
			isAnagram = false;
			System.out.printf("%s, '%s' and '%s' are not anagram\n",isAnagram,s1,s2);
			return isAnagram;
			
			
		}
		else {
			if(Arrays.equals(charArrayS1, charArrayS2)) { //use Arrays.equal() to compare the content of two array objects
				isAnagram = true;
				System.out.printf("%s, '%s' and '%s' are anagram\n",isAnagram,s1,s2);	
				return isAnagram;	
			}
			else {
				isAnagram = false;
				System.out.printf("%s, '%s' and '%s' are not anagram\n",isAnagram,s1,s2);
				return isAnagram;
			}
			
			
		}
		
			
	}


}
	
	
	/*-------Question 5: calculator-------*/
	class Calculator {
		String calculatorName = "calculator";
		String calculatorBrand = "CASIO";
		
		public void addition(double x, double y) {
			double result = x+y;
			System.out.printf("%.2f + %.2f = %.2f\n",x,y,result);	
		}
		
		public void subtraction(double x,double y) {
			double result = x-y;
			System.out.printf("%.2f - %.2f = %.2f\n",x,y,result);	
		}
		
		public void multiplication(double x,double y) {
			double result = x*y;
			System.out.printf("%.2f * %.2f = %.2f\n",x,y,result);
		}
		
		public void division(double x,double y) {
			double result = x/y;
			System.out.printf("%.2f / %.2f = %.2f\n",x,y,result);
		}
		
		public void squareRoot(double x) {
			double result = Math.sqrt(x);
			System.out.printf("The square root of %.2f is %.2f\n",x,result);	
		}
		
		public void square(double x) {
			double result = x*x;
			System.out.printf("The square of %.2f is %.2f\n",x,result);		
		}
		
		public void cube(double x) {
			double result = x*x*x;
			System.out.printf("The cube of %.2f is %.2f\n",x,result);		
		}
		
		public void convertFahrenheitToCelsius(double x) {
			double result = (x-32)*5/9;
			System.out.printf("%.2f Fahrenheit equals %.2f Celsius\n",x,result);	
			
		}
		
		public void convertFeetToInches(double x) {
			double result = x*12;
			System.out.printf("%.2f feet equals %.2f inches\n", x,result);
			
		}
		
		public void solveQuadraticEquation(double a,double b,double c) {
			if(a==0) {
				System.out.println("a=0, this is not a quadratic equation, Please input a which is non-zero");	
			}
			
			else {
				if((b*b-4*a*c)>0) {         //real number two solutions
					double result1 = (-b+Math.sqrt(b*b-4*a*c))/(2*a);
					double result2 = (-b-Math.sqrt(b*b-4*a*c))/(2*a);
					double[] result = new double[] {result1,result2};
					System.out.printf("For equation %.2fx*x+%.2fx+%.2f, the solutions are %.2f and %.2f\n",a,b,c,result1,result2);
				}
				else if((b*b-4*a*c)==0){    //real number one solution
					double result1 = (-b)/(2*a);
					double[] result = new double[] {result1};
					System.out.printf("For equation %.2fx*x+%.2fx+%.2f, the solution is %.2f\n",a,b,c,result1);
				    
				}	
				else if((b*b-4*a*c)<0) {    //complex number solutions
					double result1Real = (-b)/(2*a);
					double result1Imag = (Math.sqrt(4*a*c-b*b))/(2*a);
					double result2Real = (-b)/(2*a);
					double result2Imag = (-Math.sqrt(4*a*c-b*b))/(2*a);
					String[] result1 = new String[] {Double.toString(result1Real),result1Imag+"i"};
					String[] result2 = new String[] {Double.toString(result2Real),result2Imag+"i"};
					for(int i=0;i<result1.length;i++) {
						System.out.println(result1[i]);
						
				    }
					for(int i=0;i<result2.length;i++) {
						System.out.println(result2[i]);
						
				    }
				 }	
			  }	
		
		}	
}
