import java.util.ArrayList;

public class MidTermPartB { 
	
	// test driver: main method for testing MidTermPartB questions
	public static void main(String[] args) { 
		
		/*--------------------Q1: Reverse Even Index Element ------------------------*/	
		int[] nums = {9, 4, 8, 7, 5, 1, 3};
		nums = reverseEvenIndices(nums);
		System.out.print("Q1: even index reversed array = ");
		for(int i=0;i<nums.length;i++) {
			System.out.print(nums[i]+" ");
		}
		System.out.println();
		
		
		/*--------------------Q2: Staircase Coin: solution 1 & solution 2 ------------------------*/	
		System.out.println("Q2: solution1: staircase rows = "+arrangeCoins1(8));
		System.out.println("Q2: solution2: staircase rows = "+arrangeCoins2(8));
		
		
		/*--------------------Q3: Minimum Moves of Equal ELement --------------------*/
		int[] integerArray = {1, 2, 3};
		System.out.println("Q3: minimum move = "+minMoves(integerArray));
	
		
		/*--------------------Q4: Dice ---------------------------------------------*/
		System.out.println("Q4: number of possible ways = "+countNumberOfPossibleWays(4, 3, 5));
		
		
		/*--------------------Q5: Maze Path (Extra credit)---------------------------------------------*/
		Solution solution= new Solution();
		int[][] maze = { {1, 0, 0, 0},
                         {1, 1, 1, 1},
                         {0, 1, 0, 0},
                         {1, 1, 1, 1} };
		System.out.println("Q5: path found = "+solution.findPath(maze));

	}


/*--------------------Q1: Reverse Even Index Element------------------------*/	
	/*Solution Idea: define i starting from i=0 and increase by 2 each time
	 *define j starting from the last even index position decrease by 2 each time
	 *swap int[i] and int[j] in each iteration 
	 *once j<=i, then stop the iteration */
	
	public static int[] reverseEvenIndices(int[] nums) {
		int lastEvenIndex;
		if(nums.length % 2==0) {
			lastEvenIndex = nums.length-2;
		}
		else {
			lastEvenIndex = nums.length-1;	
		}
		
		for(int i=0,j=lastEvenIndex; j>i; i+=2,j-=2) {			
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}
		
		return nums;
	
	}
	
	
	

/*--------------------Q2: Staircase Coin: solution 1------------------------*/	
	/*Solution Idea: this question can be seen as finding out number x that 
	 * 1+2+3+4+.....+x <= N 
	 * we have 1+2+3+....+x = (1+x)*x/2 <= N
	 * solve this Quadratic equation we got x<= (square_root(8*n+1)-1)/2  (the other negative solution is eliminated)
	 * so x is the maximum integer solution of x<= (square_root(8*n+1)-1)/2 */
	
	public static int arrangeCoins1(int n) {
		return (int)((Math.sqrt(8.0*n+1)-1)/2);
	}
	
/*--------------------Q2: Staircase Coin: solution 2------------------------*/	
	/*Solution Idea: define k=1+2+3+4+....., 
	 * keep adding, 
	 * stop adding when the value of (n-k) is not big enough to form the next staircase*/
	
	public static int arrangeCoins2(int n) {
		int i,k;
		for(i=1,k=0; k<n; i++) {
			k+=i;
			if((n-k)<i+1) {
				break;
			}
		}
		return i;
	
	}
	



/*--------------------Q3: Minimum Moves of Equal ELement----------------------*/
	/*Solution Idea: n-1 elements increased by 1 is identical to that the single largest element decreased by 1, 
	 * because the difference between each element remains the same. 
	 * so this question can be simplified into that find the difference between the minimum element and other elements, 
	 * and sum all the difference together.
	 * the sum result is the minimum number of moves to get equal element*/
	
	public static int minMoves(int[] nums) {
		int min = nums[0];
		int sum = 0;
        for(int i = 0; i < nums.length; i++) {
        		if(nums[i] < min) {
        			min = nums[i];	
        		}
        		sum += nums[i];
        }
        return sum - min*nums.length;

    }
	

	
	
/*--------------------Q4: Dice ---------------------------------------------*/
	/*Solution Idea: take when face is 4, number of Dice is 3 and Sum is 5 for example, to get [3 (dice), 5 (sum)] is all the possible ways to get to [2, 4], [2, 3], [2, 2].
	 * hence, [3,5] = [2,4]+[2,3]+[2,2] = ([1,3]+[1,2]+[1,1]) + ([1,2]+[1,1]) + ([1,1]) = 6 possible ways
	 * so, we have the equation as: [diceNum, sum] = [d-1, s-1] + [d-1, s-2] +....+ [d-1, s-m] where m is the value of each face in the dice.*/
	
	public static int countNumberOfPossibleWays(int faceNum, int diceNum, int sum) {
	    
		// initialize a matrix for directly looking up the possible ways of dice rolling
		int[][] lookUpMatrix = new int[diceNum + 1][sum + 1];
	 
	    // for all the one dice situation, at most it has only one possible way
	    for (int s = 1; s <= faceNum && s <= sum; s++) {
	        lookUpMatrix[1][s] = 1;
	    }    
	 
	    // assign value to each element according to the equation we get
	    for (int d = 2; d <= diceNum; d++) {
	        for (int s = 1; s <= sum; s++) {
	            for (int m = 1; m <= faceNum && m < s; m++) {
	                lookUpMatrix[d][s] += lookUpMatrix[d-1][s-m];
	            }    
	        }    
	    }
	    return lookUpMatrix[diceNum][sum];
	}	

}	


/*--------------------Q5: Maze Path (Extra Credit)----------------------------------------------*/
	/*Solution Idea: recursive findPathRecursive() is used here to find each valid path position.
	 * It returns true when valid path is found
	 * it returns false and go back search again when no valid path is available */

class Cell{
	int x, y;
	
	Cell(int x, int y){
		this.x = x;
		this.y = y;	
	}
	
	@Override
	public String toString() {
		return "["+this.x+ ", "+this.y+ "]";
	}
}
    
class Solution {    
	 
    public ArrayList<Cell> findPath(int[][] maze) {
        ArrayList<Cell> cellPath = new ArrayList<Cell>();
     	int[][] path = new int[maze.length][maze[0].length];
 
        if (findPathRecursive(maze, 0, 0, path) == false) {
            System.out.print("No path is found...");
            return null;
        }
        else {
        		for(int i=0; i<maze.length;i++) {
        			for(int j=0; j<maze[0].length; j++) {
        				if(path[i][j]==1) {
        					cellPath.add(new Cell(i,j));
        				}
        			}
        		}
        	}
        
        return cellPath;
    }
 
    // use recursive method to find out the valid position of the path
    boolean findPathRecursive(int[][] maze, int x, int y, int[][] path) {
    		int xLength = maze.length;
    		int yLength = maze[0].length;
    	
        // if x, y is the final destination position, return true
        if (x == xLength-1 && y == yLength-1) {
            path[x][y] = 1;
            return true;
        }
 
        
        // Check whether maze[x][y] is a valid position to move at
        if (x >= 0 && x < xLength && y >= 0 && y < yLength && maze[x][y] == 1) {
            
        	    // mark path[x][y] = 1, indicating this position is a valid path position
            path[x][y] = 1;
 
            // firstly move forward
            if (findPathRecursive(maze, x + 1, y, path)) {
                return true;
            }    
 
            // if move forward along the x direction is not valid then move down along the y direction
            else if (findPathRecursive(maze, x, y + 1, path)) {
                return true;
            }    
 
            // if all the moves are not valid then go back: mark path[x][y] = 0, indicating this position is not a valid path position
            else {
            		path[x][y] = 0;
            		return false;
            	}
        }
 
        return false;
    }
       
  }

