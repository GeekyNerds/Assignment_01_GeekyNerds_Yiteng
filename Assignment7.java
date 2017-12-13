import java.util.ArrayList;

public class Assignment7 {
	
	public static void main(String[] args) {
		
		// Run Q1: Find Max Value Using Threads
		int[] intArray = new int[20];
		
		for(int i=0;i<intArray.length;i++) {
			intArray[i] = (int) (Math.random()*100);
		}
		
		System.out.println("The Max Value is "+ MaxValue.findMaxValue(intArray));
		
		
		// Run Q2: Reversed Hello
		MyThread myThread = new MyThread(1);
		myThread.start();
		try {
			myThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		// Run Q3: Sensor Controller
		Device device = new Device();
        Sensor heat = new Sensor(device);
        Sensor pressure = new Sensor(device);

        Controller controller = new Controller(device,heat,pressure);

        controller.start();
        heat.start();
        pressure.start();
        
        
        try {
			controller.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        try {
			heat.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        try {
			pressure.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        
        
        
        // Run Q4: Pascal Triangle
        PascalTriangle.printPascalTriangle(7);
      
        // Run Q5: Equal Sub Array
        int[] intArray0 = {-4,9,-18,-5};
		int[] intArray1 = {1,3,5,7,2,4,6,8};
		int[] intArray2 = {-80,-20,-70,-30};
		int[] intArray3 = {-1,-2,-3,-4,-100};
		
		System.out.println(FindEqualSubArray.isEqualSumSubArray(intArray0));
		System.out.println(FindEqualSubArray.isEqualSumSubArray(intArray1));
		System.out.println(FindEqualSubArray.isEqualSumSubArray(intArray2));
		System.out.println(FindEqualSubArray.isEqualSumSubArray(intArray3));

        
		
		
	}
}

	
/*----------------------Q1: Find Max Value Using Threads-----------------------*/	
class MaxValue {
	public static void main(String[] args) {
		int[] intArray = new int[20];
		
		for(int i=0;i<intArray.length;i++) {
			intArray[i] = (int) (Math.random()*100);
	
		}
		
		System.out.println("The Max Value is "+findMaxValue(intArray));
		
		
	}
	
	
	public static int findMaxValue(int[] intArray) {
		FindMaxThread[] findMaxThread = new FindMaxThread[4];
		
		for(int i=0; i<4; i++) {
			
			findMaxThread[i] = new FindMaxThread(intArray, i*intArray.length/4, (i+1)*intArray.length/4);			
			findMaxThread[i].start();
		}
		
		for(int i=0; i<4; i++) {
			try {
				findMaxThread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		int[] nums = new int[4];
		for(int i=0; i<4;i++) {
			nums[i] = findMaxThread[i].max;
			
		}
		
		return findMax(nums,0,4);
		
	}
		
			
	
	public static int findMax(int[] intArray, int indexLow, int indexHigh) {
		int max = -1;
		for(int i= indexLow; i<indexHigh; i++) {
			if(intArray[i]>max) {
				max = intArray[i];
			}
		}
		
		return max;
	}
	

}


class FindMaxThread extends Thread {
	int[] intArray;
	int indexLow;
	int indexHigh;
	int max;
	public FindMaxThread(int[] intArray, int indexLow, int indexHigh) {
		this.intArray = intArray;
		this.indexLow = indexLow;
		this.indexHigh =indexHigh;
	}
	
	@Override
	public void run() {
		
		this.max = MaxValue.findMax(intArray, indexLow, indexHigh);
		
	}
	
}



/*----------------------Q2: Reverse Hello---------------------------------*/	
class MyThread extends Thread {
	int threadNum;
	public MyThread(int threadNum) {
		this.threadNum = threadNum;
	}
	
	@Override
	public void run() {
		
		if(this.threadNum<51) {
			
			MyThread myThread = new MyThread (this.threadNum + 1);
			myThread.start();
			
			try {
				myThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Hello from Thread "+this.threadNum);
			
		}
		
	}
}





/*-------------------------Q3: Sensor Controller------------------------------*/
class Controller extends Thread {
	Device device;
	Sensor heat;
	Sensor pressure;
	
	public Controller(Device device, Sensor heat, Sensor pressure) {
		this.device = device;
		this.heat = heat;
		this.pressure = pressure;
	}
	
	@Override
	public void run() {
		this.device.startup();
		this.device.isStartUp =true;
		
		while(this.device.isStartUp) {
			
			synchronized(this.device) {
				try {
					this.device.wait(); // Controller thread blocks and waits for notification from Sensor thread
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
			}
			

			if(this.heat.getValue()>70 || this.pressure.getValue()>100) {
				System.out.printf("Warning: Heat -> %.2f !!! , Pressure -> %.2f\n", this.heat.getValue(),this.pressure.getValue());
				this.device.shutdown();
				this.device.isStartUp = false;
				break;
		
			}
			else {
				System.out.printf("heat -> %.2f , pressure -> %.2f\n", this.heat.getValue(), this.pressure.getValue());

			}
		}	

	}
}



class Sensor extends Thread{
	private final Device device;
	private double value;
	public Sensor(Device device){
		this.device = device;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public void updateValue() {
		this.value += Math.random()*20; // in real-world, sensor samples random data values
	} 
	
	@Override
	public void run() {
		while(this.device.isStartUp) {
			
			try {
				sleep(1000); // in real-world, sensor samples data every few seconds to save power
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.updateValue(); // sensor samples data value
			
			synchronized(this.device) {
				this.device.notify();   // notify and wake up the Controller thread
			}
			
			if(!this.device.isStartUp) {
				break; // if device is shut down, sensor stops sampling data
			}
			
		}
		
	}
	
	
}



class Device {
	boolean isStartUp = true;
	void startup() {
		System.out.println("Device started");
	}
	
	void shutdown() {
		System.out.println("Device shutting down due to maintenance");
	}
}



/*-------------------------Q4: Pascal Triangle------------------------------*/
class PascalTriangle {
	public static void printPascalTriangle(int n) {
		ArrayList<ArrayList<Integer>> rows = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i< n;i++) {
			ArrayList<Integer> eachRow = new ArrayList<Integer>();
			for(int j=0;j<i+1;j++) {
				if(j==0||j==i) {
					eachRow.add(1);
		
				}
				else {
					eachRow.add(rows.get(i-1).get(j)+rows.get(i-1).get(j-1));
				}
				
			}
			rows.add(eachRow);
		}
		System.out.println(rows);
	}
}


/*-------------------------Q5: Equal Sum Sub Array------------------------------*/
class FindEqualSubArray {

	public static boolean isEqualSumSubArray(int[] intArray) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int sum = 0;
		int sumMax = 0;
		int sumMin = 0;
		for(int i=0;i<intArray.length;i++) {
			if(intArray[i]>max) {
				max=intArray[i];
			}
			if(intArray[i]<min) {
				min=intArray[i];
			}
			
			if(intArray[i]>0) {
				sumMax+=intArray[i];
			}
			
			if(intArray[i]<0) {
				sumMin+=intArray[i];
			}
			sum+=intArray[i];
		}
		
		if(sum % 2 !=0) {
			//sum is odd, so this integer array does not have equal sum sub-arrays
			return false;
		}
		
		// define a table matrix to hold all the possible sum
		boolean[][] table =new boolean[sumMax-sumMin+1][intArray.length+1];
		
		for(int i=0; i<intArray.length+1;i++) {
			table[-sumMin][i] = true;
		}
		
		for(int i=1;i<intArray.length+1;i++) {
			for(int j=0;j<sumMax-sumMin+1;j++) {
				
				if(j==-sumMin) {
					continue;
				}
				
				// the sum should not go beyond the max sum or minimum sum
				if(sumMin+j-intArray[i-1]>sumMax || sumMin+j-intArray[i-1]<sumMin) {
					table[j][i]=false;
				}
				else {
					table[j][i]=table[j][i-1] || table[j-intArray[i-1]][i-1];	
				}
			}
		}
		return table[sum/2-sumMin][intArray.length];
		
	} 
	
}

