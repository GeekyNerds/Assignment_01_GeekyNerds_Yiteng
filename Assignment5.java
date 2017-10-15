package Assignment5;

import java.util.Vector;
import java.util.ArrayList;



public class Assignment5 {

    public static void main(String[] args) {
 /*-----------------a test of Dessert Shoppe Check-out System---------------*/    
        Checkout checkout = new Checkout();

        checkout.enterItem(new Candy("Peanut Butter Fudge", 2.25, 399));
        checkout.enterItem(new IceCream("Vanilla Ice Cream", 105));
        checkout.enterItem(new Sundae("Choc. Chip Ice Cream", 145, "Hot Fudge", 50));
        checkout.enterItem(new Cookie("Oatmeal Raisin Cookies", 4, 399));

        System.out.println("\nNumber of items: " + checkout.numberOfItems() + "\n");
        System.out.println("\nTotal cost: " + checkout.totalCost() + "\n");
        System.out.println("\nTotal tax: " + checkout.totalTax() + "\n");
        System.out.println("\nCost + Tax: " + (checkout.totalCost() + checkout.totalTax()) + "\n");
        System.out.println(checkout);

        checkout.clear();

        checkout.enterItem(new IceCream("Strawberry Ice Cream", 145));
        checkout.enterItem(new Sundae("Vanilla Ice Cream", 105, "Caramel", 50));
        checkout.enterItem(new Candy("Gummy Worms", 1.33, 89));
        checkout.enterItem(new Cookie("Chocolate Chip Cookies", 4, 399));
        checkout.enterItem(new Candy("Salt Water Taffy", 1.5, 209));
        checkout.enterItem(new Candy("Candy Corn", 3.0, 109));

        System.out.println("\nNumber of items: " + checkout.numberOfItems() + "\n");
        System.out.println("\nTotal cost: " + checkout.totalCost() + "\n");
        System.out.println("\nTotal tax: " + checkout.totalTax() + "\n");
        System.out.println("\nCost + Tax: " + (checkout.totalCost() + checkout.totalTax()) + "\n");
        System.out.println(checkout);
        
/*---------------a test of Spiral Order of Matrix (Extra Credit question)----------------*/        
        System.out.println("-----Extra Credit Question: Spiral Order of Matrix-----");
        SpiralOrderOfMatrix spiralOrder = new SpiralOrderOfMatrix();
        int[][] matrix= {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(spiralOrder.spiralOrder(matrix));
           
    }
}


abstract class DessertItem {
	
	protected String name;
	
	public DessertItem() {
		
	}
	
	public DessertItem(String name) {
		this.name = name;
	}
	
	public final String getName() {
		return this.name;
	}
	
	public abstract int getCost();
	
}


class Candy extends DessertItem {
	double weight;
	int pricePerPound;
	
	public Candy(String name, double weight, int pricePerPound) {
		super(name);
		this.weight = weight;
		this.pricePerPound = pricePerPound;		
	}
	
	@Override
	public int getCost() {
		return (int) Math.round(this.weight*this.pricePerPound);	
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public int getPricePerPound() {
		return this.pricePerPound;
	}
	
}


class Cookie extends DessertItem {
	int number;
	int pricePerDozen;
	
	public Cookie(String name, int number, int pricePerDozen) {
		super(name);
		this.number = number;
		this.pricePerDozen = pricePerDozen;
		
	}
	
	@Override
	public int getCost() {
		return Math.round(this.number*this.pricePerDozen/12);	
	}
	
	public int getNumber() {
		return number;
	}
	
	public int getPricePerDozen() {
		return this.pricePerDozen;
	}
}


class IceCream extends DessertItem {
	protected int cost;
	
	public IceCream(String name, int cost) {
		super(name);
		this.cost=cost;
	}
	
	@Override
	public int getCost() {
		return this.cost;	
	}
	
}


class Sundae extends IceCream {
	String toppingName;
	int toppingCost;
	
	public Sundae(String name, int cost, String toppingName, int toppingCost) {
		super(name, cost);
		this.toppingName=toppingName;
		this.toppingCost=toppingCost;
		
	}
	
	@Override
	public int getCost() {
		// Sundae cost includes: ice cream cost + topping cost
		return this.cost+this.toppingCost;
	}
	
	public String getToppingName() {
		return this.toppingName;
	}
	
	
}


class Checkout {
	// use Vector to accept the DessertItem object
	Vector<DessertItem> itemVector = new Vector<DessertItem>();
	
	public Checkout() {	
		
	}
	
	public void enterItem(DessertItem item) {
		this.itemVector.add(item);	
	}
	
	public int numberOfItems() {
		return this.itemVector.size();
		
	}
	
	public int totalCost() {
		int totalCost = 0;;
		for(int i=0; i<this.itemVector.size(); i++) {
			totalCost += this.itemVector.get(i).getCost();
		}
		return totalCost;
		
	}
	public int totalTax() {
		return Math.round(this.totalCost()*DessertShoppe.getTaxRate());
		
	}
	public void clear() {
		this.itemVector.clear();
		
	}
	
	@Override
	public String toString() {
		String receipt = "\n"+DessertShoppe.getShoppeName()+"\n\n";
		
		for(int i=0; i<this.itemVector.size(); i++) {
			
			if(this.itemVector.get(i) instanceof Candy) {
				receipt+=((Candy) this.itemVector.get(i)).getWeight()+" lbs. @ "
						+DessertShoppe.cent2Dollar(((Candy) this.itemVector.get(i)).getPricePerPound())+" /lb.\n";
			}
			
			else if(this.itemVector.get(i) instanceof Cookie) {
				receipt+=((Cookie) this.itemVector.get(i)).getNumber()+" @ "
						+DessertShoppe.cent2Dollar(((Cookie) this.itemVector.get(i)).getPricePerDozen())+" /dz.\n";
			}
			
			else if(this.itemVector.get(i) instanceof Sundae) {
				receipt+=((Sundae) this.itemVector.get(i)).getToppingName()+" Sundae with\n";
			}
			
			// if the size of item name is larger than the pre-defined maximum size of item name printing on the receipt, cut the name size in order to fit the receipt
			if(this.itemVector.get(i).getName().length()>DessertShoppe.getMaximumSizeOfItemName()) {
				receipt+=String.format("%.27s", this.itemVector.get(i).getName());
			}
			else {
				receipt+=this.itemVector.get(i).getName();
			}
			
			// align cost of each item to the right most on the receipt according to the item name size and cost length
			for(int j=0; j<DessertShoppe.getWidthOfDisplayOnReceipt()
					-this.itemVector.get(i).getName().length()-DessertShoppe.cent2Dollar(this.itemVector.get(i).getCost()).length();j++) {	
				receipt+=" ";	
			}
			
			receipt+=DessertShoppe.cent2Dollar(this.itemVector.get(i).getCost())+"\n";
		
		}
		// according to the pre-defined display width of receipt = 37, %34s & %27s are calculated because "Tax" occupy 3 character (37-3=34) and "Total Cost" occupy 10 character (37-10=27)  
		receipt+="\nTax"+String.format("%34s", DessertShoppe.cent2Dollar(this.totalTax()))+"\n";
		receipt+="Total Cost"+String.format("%27s", DessertShoppe.cent2Dollar(this.totalCost()+this.totalTax()))+"\n\n";
		
		return receipt;
	}
		
	
}


class DessertShoppe {
	private static final String shoppeName = "        M & M Dessert Shoppe";
	private static final String underShoppeName = "\n        --------------------"; 
	private static final float taxRate = 0.065f;
	private static final int maximumSizeOfItemName = 27; // define maximum size of item name for printing receipt  
	private static final int widthOfDisplayOnReceipt = 37; // define maximum size of display width of receipt
	
	public static float getTaxRate() {
		return taxRate;
	}
	
	public static String getShoppeName() {
		return shoppeName + underShoppeName;
	}
	
	public static int getWidthOfDisplayOnReceipt() {
		return widthOfDisplayOnReceipt;
	}
	
	public static int getMaximumSizeOfItemName() {
		return maximumSizeOfItemName;
	}
	
	public static String cent2Dollar(int cents) {
		return String.valueOf(cents/100.0);
		
	}
}


/*---------------Extra credit question: Spiral Order of Matrix-------------------*/ 
/* Solving Idea:  the spiral order path has four directions: two horizontal and two vertical, 
 * we start from horizontal direction, in which the row number remain the same ++0 and column number ++1 
 * we change the path direction if the next element position go beyond the border or has been checked before
 */
class SpiralOrderOfMatrix {
	
	public ArrayList<Integer> spiralOrder(int[][] matrix){
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		int rowSize=matrix.length;
		int columnSize=matrix[0].length;
		// initialize each position in the matrix as unchecked status (i.e. false)
		boolean[][] isPositionChecked = new boolean[rowSize][columnSize];
		// define the increment number of row & column in each direction
		int[] directionRow = {0, 1, 0, -1};
		int[] directionColumn = {1, 0, -1, 0};
		
		int r = 0,c = 0;
		// start from the horizontal direction in which the row number remain the same ++0 and column number ++1 
		int direction = 0;
		
		for(int i=0; i<rowSize*columnSize; i++) {
			result.add(matrix[r][c]);
			// mark the position as true once checked
			isPositionChecked[r][c]=true;
			int nextR = r + directionRow[direction];
			int nextC = c + directionColumn[direction];
			
			// validate the next position (not checked and not go beyond the border) 
			if(nextR>=0 && nextR<rowSize && nextC >=0 && nextC<columnSize && isPositionChecked[nextR][nextC]==false) {
				r=nextR;
				c=nextC;
			}
			else {
				// change path direction
				direction = (direction + 1) % 4;
				r = r + directionRow[direction];
				c = c + directionColumn[direction];
			}		
			
			
		}
		return result;
	}
}
