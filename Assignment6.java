import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Assignment6 {
	
/*---------------------Question 1: Define MyIndexOutOfBoundException---------------------------------*/
	
	public static void main(String[] args) {
		int[] integerArray = {0, 1, 2};
		try {
			for (int index=0; index<=integerArray.length; index++) {
				
				if(index==3) {
					// throw MyIndexOutOfBoundException
					throw new MyIndexOutOfBoundException(index, integerArray[0], integerArray[integerArray.length-1]);
				}
				else {
					System.out.println(integerArray[index]); 
				}
 				
			}
		} catch (MyIndexOutOfBoundException indexOutOfBoundException) {
			// use System.out.println or printStackTrace to display the MyIndexOutOfBoundException error message
			System.out.println(indexOutOfBoundException);
			indexOutOfBoundException.printStackTrace();
		  }
	}
	
}	
	
	// define MyIndexOutOfBoundException Exception
	class MyIndexOutOfBoundException extends IndexOutOfBoundsException {
		
		int lowerBound;
		int upperBound;
		int index;
		
		public MyIndexOutOfBoundException() {
			super();
		}
		
		// constructor containing three parameters: current index, lowerbound, upperbound
		public MyIndexOutOfBoundException(int index, int lowerBound, int upperBound) {
			this.index = index;
			this.lowerBound = lowerBound;
			this.upperBound = upperBound;
			
		}
		
		// override the default toString() method to customize our own MyIndexOutOfBoundException error message
		@Override
		public String toString() {
			String message = "Error Message: Index: "+ this.index +", but Lower bound: "+ this.lowerBound +" , Upper bound: "+this.upperBound;
			return message;
		}
		
		public MyIndexOutOfBoundException(String s) {
			super(s);
		}
		
		
		
/*---------------------Question 2: Make the code compile---------------------------------*/
		/* Two ways to make this code compile:
		 * 1. add throws IOException to the parse(File file) method
		 * 2. add try/catch clause to in around RandomAccessFile input.readLine() and input.close() 
		 */
		public static void parse(File file) throws IOException {
		      RandomAccessFile input = null;
		      String line = null;
		      
		      try {
		          input = new RandomAccessFile(file, "r");
		          while ((line = input.readLine()) != null) {
		              System.out.println(line);
		          }
		          return;
		      } finally {
		            if (input != null) {
		              input.close();
		            }
		        }
		  }  	
	}
	
/*---------------------Question 3: ATM implementation ---------------------------------*/	
/* Please check the Atm.java file uploaded separately in the Assignment_Yiteng folder. Thank you!*/
