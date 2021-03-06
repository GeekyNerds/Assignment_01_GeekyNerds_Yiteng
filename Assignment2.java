
public class Assignment2 {
	
	public static void main(String[] args) {
		
		Assignment2 a2 = new Assignment2();
		// Run Question 1
		System.out.println("------Question 1:------");
		a2.employeeSalary(49);
		
		// Run Question 2
		System.out.println("------Question 2:------");
		a2.addDigits(21);
		
		// Run Question 3
		System.out.println("------Question 3:------");
		a2.printPerfectNumber(9000);
		
		// Run Question 4 and 5
		System.out.println("------Question 4 and 5:------");
		
			// create 3 types of pizza for customer to choose
		Pizza pizza1 = a2.new Pizza("seafood pizza",10.0f, 2, 50.0 );
		Pizza pizza2 = a2.new Pizza("fruit pizza",8.0f, 3, 40.0);
		Pizza pizza3 = a2.new Pizza("cheese pizza", 5.0f, 1, 25.0);
        		// customers choose pizza
		Customer customer1 = a2.new Customer("Serena", pizza1);
                Customer customer2 = a2.new Customer("Harsha",pizza3);
        		// calculate the order total price of customers 
                double sumCustomer1 = customer1.pizza.pizzaPrice*customer1.pizza.pizzaAmount;
                double sumCustomer2 = customer2.pizza.pizzaPrice*customer2.pizza.pizzaAmount;
        
                System.out.println(""+customer1.customerName+" ordered "+customer1.pizza.pizzaType+", and the total price is "+sumCustomer1);
                System.out.println(""+customer2.customerName+" ordered "+customer2.pizza.pizzaType+", and the total price is "+sumCustomer2);
		
		
		// Run Question 6, here I put two ways to do this task.
		System.out.println("------Question 6: method A------");
		a2.GenerateIsoscelesTriangle_Method_A (5);
		
		System.out.println("------Question 6: method B------");
		a2.GenerateIsoscelesTriangle_Method_B (9);	
		
	}
	
	
/*-------Question 1: calculate salary-------*/	
	public double employeeSalary(double hours) {
		double salary = 0;
		double hourTier1 = 36.0;
		double hourTier2 = 41.0;
		double hourTier3 = 48.0;
		double rateTier1 = 15.0;
		double rateTier2 = 15.5;
		double rateTier3 = 30.0;
			
		if( hours>=hourTier3 ){
			salary=hourTier1*rateTier1+(hourTier2-hourTier1)*rateTier2+(hourTier3-rateTier2)*rateTier3;
			System.out.println("Your salary is: "+salary+" for "+hours+" hours, you have reached max 48 working hours");
			return salary;
		}
		
		else if( hours>=hourTier2 && hours<hourTier3) {
			salary=hourTier1*rateTier1+(hourTier2-hourTier1)*rateTier2+(hours-hourTier2)*rateTier3;
			System.out.println("Your salary is: "+salary+" for "+hours+" hours");
			return salary;
		}
			
		else if( hours>hourTier1 && hours<hourTier2) {
			salary=hourTier1*rateTier1+(hours-hourTier1)*rateTier2;
			System.out.println("Your salary is: "+salary+" for "+hours+" hours");
			return salary;
		}
			
		else if( hours>=0 && hours<=hourTier1) {
			salary=hours*rateTier1;
			System.out.println("Your salary is: "+salary+" for "+hours+" hours");	
			return salary;
		}
			
		else{
			System.out.println("Please input a valid working hour!");
			return salary;
		}		
			
	}
	
	
	
/*-------Question 2: input a number, add its digits-------*/
	/* Solution idea: All the adding results are from 0,1,2,3,4,5,6,7,8,9, which is a periodic pattern repeating every 9 numbers, 
           thus based on the every 9 numbers repeating pattern, 
	   1. firstly find out the input number's nearest last multiples of 9: [(num-1)/9]*9;  e.g, number 21's nearest last multiples of 9 is 18
	   2. secondly find out the distance between this input number and its nearest last multiples of 9: num-[(num-1)/9]*9; e.g, 21 - 18 = 3;
	   3. the distance is the adding results. e.g. 21-18=3, 2+1=3 (number 21's the nearest last multiple of 9 is 18 )
	 */
	public int addDigits(int num) {
		int result = num-((num-1)/9)*9; 
	    System.out.println("Digits adding result is "+result+" (input number "+num+")");
		return result;
	}
	
	
	
/*-------Question 3: print perfect numbers-------*/	
	public void printPerfectNumber(int n) {
		int i,j,k;
		for(i=2;i<n;i++) {
			int[] divider = new int[i-1];
			for(j=1;j<i;j++) {
				if(i%j==0) {
					divider[j-1]=j;
				}
				else {
					divider[j-1]=0;
				}	
					
			}
			
			int sum=0;
			for(k=0;k<i-1;k++) {
				sum=divider[k]+sum;
			}
			
			if(i==sum) {
				System.out.println(i);	
			}	
		
		}
			
	}
		

	
/*-------Question 4: create Pizza class with constructors-------*/	
	class Pizza {
		String pizzaType;
		float pizzaPrice;
		int pizzaAmount;
		double loyaltyPoints;
		boolean isTakeOut;
		Receipt receipt; // define one attribute of Pizza as a Receipt object class
		
		//default constructor with no parameter input
		Pizza() {
			
		}
		//constructor with three parameter inputs
		Pizza(String pizzaType, float pizzaPrice, int pizzaAmount) {
			this.pizzaType = pizzaType;
			this.pizzaPrice = pizzaPrice;
			this.pizzaAmount = pizzaAmount;	
		}
		
		//constructor with four parameter inputs
		Pizza(String pizzaType, float pizzaPrice, int pizzaAmount, double loyaltyPoints) {
			this.pizzaType = pizzaType;
			this.pizzaPrice = pizzaPrice;
			this.pizzaAmount = pizzaAmount;
			this.loyaltyPoints = loyaltyPoints;	
		}
		
		//constructor with six parameter inputs including one Receipt object class input
		Pizza(String pizzaType, float pizzaPrice, int pizzaAmount, double loyaltyPoints, boolean isTakeOut, Receipt receipt) {
			this.pizzaType = pizzaType;
			this.pizzaPrice = pizzaPrice;
			this.pizzaAmount = pizzaAmount;
			this.loyaltyPoints = loyaltyPoints;
			this.isTakeOut = true;
			this.receipt = receipt;		
		}
		

	}

	class Receipt {
		double receiptTotal;
		String receiptDate;		
	}
	
	
	
	
/*-------Question 5: create Customer class and calculate customer's pizza order total price-------*/	
        /* Map data structure" can be used for pairing "pizza type" and "pizza amount", 
	   the key and value pair in the Map data structure helps 
	   to achieve an high-efficiency searching and locating.*/
	
	class Customer {
		String customerName;
		Pizza pizza; // define one attribute of Customer as a Pizza object class
		
		Customer(String customerName, Pizza pizza) {
			this.customerName = customerName;
			this.pizza = pizza;		
		}		
			
	}
	
	
	
/*-------Question 6: generate isosceles right triangle-------*/	
	
	// Method 1: 
	public void GenerateIsoscelesTriangle_Method_A (int n) {
    		
		int i, j;
		for(i=1;i<n+1;i++) {
			for(j=1;j<i+1;j++) {
				
				if(i==n) {
					System.out.print("*");
				}
				else {
					if(j==1||j==i) {
						System.out.print("*");
					}
					else {
						System.out.print(" ");
					}
				}				
			}
			System.out.println();
			
		}
			
	}		

	// Method 2: 
	public void GenerateIsoscelesTriangle_Method_B (int n) {
	
		int i, j, k;
		for(i=1; i<n+1; i++) {
			String[] str = new String[i];
			str[0] = "*";
			str[i-1] = "*";
		
			for(j=1; j<i-1; j++) {
			
				if(i==n) {
					str[j] = "*";
				}
				else {
					str[j] = " ";
				}
			}
		
			for(k=0; k<i; k++) {
				System.out.print(str[k]);
			}
			System.out.println();			
		
		}	  
	}
	


}

	
	
	


