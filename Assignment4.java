package assignment4;

public class Assignment4 {
	
	public static void main(String[] args) {
		Assignment4 a4 = new Assignment4();
		
		//run Question 1 Format License Key
		System.out.println("------Question 1 Format License Key----");
		a4.formatLicenseKey("2-4A0r7-4k",3);
		
		//run Question 2 Rock Paper Scissors Game
		System.out.println("------Question 2 Rock Paper Scissors Game----");
		RockPaperScissorsGame.main();
		
		//run Question 3 IP Address
		System.out.println("------Question 3 IP Address----");
		Driver.main();
		
		//run Question 4 Course Registration
		System.out.println("------Question 4 Course Registration----");
		Test.main();
		
		//run Question 5 Integer to Roman Numeral
		System.out.println("------Question 5 Integer to Roman Numeral----");
		IntToRoman.main();
		
		//run Question 6 Median Number of Two Sorted Array
		System.out.println("------Question 6 Median Number of Two Sorted Array----");
		MedianNum.main();
		
	}
	
/*------------------Question 1: Format License Key------------------*/	
	public void formatLicenseKey(String s, int k) {
		String key = "";
		// firstly remove all the '-' inside the key
		for(int i=0;i<s.length();i++) {
			if (s.charAt(i)!='-') {
				key = key+s.charAt(i);
			}
			else {
				continue;
			}
				
		}
		// calculate group number and get how many '-' to be needed in the formatted key
		int mod = key.length() % k;
		int groupNum ;
		if (mod==0) {
			groupNum = key.length()/k;
		}	
		else {
			groupNum = key.length()/k+1;	
		}
	    // initialize formatted key char array based on the length we have calculated
		char[] keyFormatted = new char[key.length()+groupNum-1];
		// j is used as the index number to traverse each character of the input key 
		int j = key.length()-1;	
		for(int i=key.length()+groupNum-2; i>=0;i--) {
			// from back end to the front, put '-' in the formatted key char array we have initialize  
			if((i-(key.length()+groupNum-2)-1)%(k+1)==0) {
				keyFormatted[i] = '-';			
			}
			else {
				keyFormatted[i] = key.charAt(j);
				// get the ASCII code of each character, convert them into CAPITAL letter
				int asciiLetter =  (int) keyFormatted[i];
				if(97<=asciiLetter && asciiLetter<=122) {
					keyFormatted[i] = (char) (keyFormatted[i]-32);	
				}
				j--;	
			}
					
		}
		
		String result=new String(keyFormatted);
		System.out.printf("Formatted license key is '%s', original input is '%s'\n",result,s);		
				
	}
}	
	
/*------------------Question 2: Tool & Rock Paper Scissors Game------------------*/	
	
	class Tool {
		private int strength;
		private char type;
		
		void setStrength(int strength) {
			this.strength = strength; 			
		}
		void setType(char type) {
			this.type = type; 			
		}
		
		public boolean fight(Tool tool) {
			float compare = 0.0f;
			if(this.type=='r'&&tool.type=='s') {
				compare = this.strength*2.0f-tool.strength;
			}
			else if(this.type=='r'&&tool.type=='p') {
				compare = this.strength*0.5f-tool.strength;
			}
			else if(this.type=='s'&&tool.type=='p') {
				compare = this.strength*2.0f-tool.strength;
			}
			else if(this.type=='s'&&tool.type=='r') {
				compare = this.strength*0.5f-tool.strength;
			}
			else if(this.type=='p'&&tool.type=='r') {
				compare = this.strength*2.0f-tool.strength;
			}
			else if(this.type=='p'&&tool.type=='s') {
				compare = this.strength*0.5f-tool.strength;
			}
			
			if(compare>0) {
				return true;
			}
			else {
				return false;
			}	
		}
	}

	class Scissors extends Tool {
		Scissors(int strength){
			this.setStrength(strength);
			this.setType('s');	
		}	
	}

	class Paper extends Tool {
		Paper(int strength){
			this.setStrength(strength);
			this.setType('p');	
		}	
	}

	class Rock extends Tool {
		Rock(int strength){
			this.setStrength(strength);
			this.setType('r');		
		}	
	}
	
	
	class RockPaperScissorsGame{
		public static void main() {
			Scissors s = new Scissors(5);
			Paper p = new Paper(7);
			Rock r = new Rock(15);
		
			System.out.println(s.fight(p) + " , " + p.fight(s));
			System.out.println(p.fight(r) + " , " + r.fight(p));
			System.out.println(r.fight(s) + " , " + s.fight(r));
		}
	}
	
/*------------------Question 3: IP Address------------------*/		

	class IpAddress {
		String dottedDecimal;
		int firstOctet, secondOctet, thirdOctet, fourthOctet;
		
		IpAddress(String dottedDecimal) {
			this.dottedDecimal = dottedDecimal;
			
			//String[] octets = dottedDecimal.split("\\.");
			
			//divide each octet into String array without using split() method
			String[] octets = new String[4];
			String eachOctet ="";
			for(int i=0,j=0;i<dottedDecimal.length();i++) {
				if(dottedDecimal.charAt(i)!='.') {
					eachOctet = eachOctet + dottedDecimal.charAt(i);
					octets[j] = eachOctet;
				}
				else {
					eachOctet ="";
					j++;
				}	
			}
			
			this.firstOctet = Integer.parseInt(octets[0]);
			this.secondOctet = Integer.parseInt(octets[1]);
			this.thirdOctet = Integer.parseInt(octets[2]);
			this.fourthOctet = Integer.parseInt(octets[3]);
	
		}
		
		String getDottedDecimal() {
			return this.dottedDecimal;	
		} 
		
		int getOctet(int position) {
			if(position == 1) {
				return this.firstOctet;
			}
			else if(position == 2) {
				return this.secondOctet;
			}
			else if(position == 3) {
				return this.thirdOctet;
			}
			else if(position == 4) {
				return this.fourthOctet;
			}
			else {
				System.out.println("Please input integer between 1 - 4");
				return -1;
			}
			
		}
		
	}
	
	class Driver{
		public static void main() {
			IpAddress ip = new IpAddress("216.27.6.136");
			System.out.println(ip.getDottedDecimal());
			System.out.println(ip.getOctet(4));
			System.out.println(ip.getOctet(1));
			System.out.println(ip.getOctet(3));
			System.out.println(ip.getOctet(2));
			
		}
		
		
	}

	
/*------------------Question 4: Course Registration------------------*/	
	
	
	class Student {
		String name;
		int id;
		
		Student(String name, int id) {
			this.name=name;
			this.id=id;
		}
		
		String getName() {
			return this.name;
		}
		
		int getId() {
			return this.id;
		}
	
	}
	
	class Course {
		String classTitle;
		int numberOfStudent = 0;
		Student[] students = new Student[10];
		
		Course(String classTitle) {
			this.classTitle = classTitle;
		}
		
		Student[] getStudents() {
			return this.students;
			
		}
		
		boolean isFull() {
			if(this.getNumberOfStudent()<10) {
				return false;
			}
			else {
				return true;
			}
			
		}
		
		String getClassTitle() {
			return this.classTitle;
		}
		
		int getNumberOfStudent() {
			return this.numberOfStudent;
		}
		
		void registerStudent(Student student) {
			if(this.isFull()==false) {
				this.students[numberOfStudent]=student;
				this.numberOfStudent++;	
				System.out.printf("Hi, %s, register success, welcome to %s course, you "
						+ "are the %dth student\n",student.name,this.classTitle,this.getNumberOfStudent());
			}
			else {
				System.out.printf("Sorry, %s, %s course is full, register fail!\n", student.name,this.getClassTitle());
			}	
			
		}
		
	}
	
	class Test {
		public static void main() {
			Student studentYiteng1 = new Student("Yiteng1",1);
			Student studentYiteng2 = new Student("Yiteng2",2);
			Student studentYiteng3 = new Student("Yiteng3",3);
			Student studentYiteng4 = new Student("Yiteng4",4);
			Student studentYiteng5 = new Student("Yiteng5",5);
			Student studentYiteng6 = new Student("Yiteng6",6);
			Student studentYiteng7 = new Student("Yiteng7",7);
			Student studentYiteng8 = new Student("Yiteng8",8);
			Student studentYiteng9 = new Student("Yiteng9",9);
			Student studentYiteng10 = new Student("Yiteng10",10);
			Student studentYiteng11 = new Student("Yiteng11",11);
			Course courseJava = new Course("JAVA");
			courseJava.registerStudent(studentYiteng1);
			courseJava.registerStudent(studentYiteng2);
			courseJava.registerStudent(studentYiteng3);
			courseJava.registerStudent(studentYiteng4);
			courseJava.registerStudent(studentYiteng5);
			courseJava.registerStudent(studentYiteng6);
			courseJava.registerStudent(studentYiteng7);
			courseJava.registerStudent(studentYiteng8);
			courseJava.registerStudent(studentYiteng9);
			courseJava.registerStudent(studentYiteng10);
			courseJava.registerStudent(studentYiteng11);
			Course courseDatabase = new Course("Database");
			courseDatabase.registerStudent(studentYiteng11);	
		}
	}
	
	
/*------------------Question 5: Integer to Roman Numeral------------------*/
	
	class IntToRoman {
		public static void main() {
			IntToRoman iToR= new IntToRoman();
			System.out.println(iToR.intToRoman(3999));	
		}
		public String intToRoman(int num) {
			// put roman numerals' ones, tens, hundreds, thousands patterns in the corresponding array
			String[] ones= {"","I","II","III","IV","V","VI","VII","VIII","IX"};
			String[] tens= {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
			String[] hundreds= {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"}; 
			String[] thousands= {"","M","MM","MMM"};
			// convert into roman numeral
			String romanNum=thousands[num/1000]+hundreds[num%1000/100]+tens[num%100/10]+ones[num%10/1];
			System.out.printf("Convert %d to Roman Numeral is: \n",num);
			return romanNum;
		}
	}
	

	
	
/*---------Extra Credit :Question 6: Median Number of Two Sorted Array----------*/	
	/* Median number divides the integer array into two identical length parts, and left part of that median number is always smaller than the right part
	 * hence, the problem will be solved once we find a position where left-side numbers of that position is smaller than all the numbers of the right-side of that position
	 * ...a[i-2], a[i-1] | a[i], a[i+1]...   once a[i-1] < b[j] and b[j-1] < a[i+1], left-side length = right-side length
	 * ...b[j-2], b[j-1] | b[j], b[j+1]...   then '|' is the position we want to divide the array.
	 */
	class MedianNum {
		public static void main() {
			int[] a = {1,3,5,7,9};
			int[] b = {2,4,6,8,10,12,14};
			if (a.length>b.length) {
				int[] temp=a;
				a=b;
				b=temp;
			}
			
			// using binary search to locate the position
			int iLow = 0;
			int iHigh =a.length;
			while(iLow<=iHigh) {
				// initialize i as the index of a[] and start searching in the middle position of a[]
				int i=(iLow+iHigh)/2;
				// initialize j as the index of b[] and ensure left-side length is always equal to the right-side length
				int j = (a.length+b.length+1)/2-i;
			    // a is too big, set the high search range lower
				if(i>0 && a[i-1]>b[j]) {
					iHigh=i-1;
				}
				// a is too small, set the low search range higher
				else if(i<a.length && a[i]<b[j-1]) {			
					iLow=i+1;
				}
				else {
					float median;
					if((a.length+b.length)%2==1) {
						if(i==0) {
							median=b[j-1];
						}
						else if(j==0) {
							median=a[i-1];
						}
						else {			
							median = getMax(a[i-1],b[j-1]);	
						}
						System.out.println("The median number is "+median);
						System.out.println("This is an odd number length array");
						break;
						
					}
					else {
						
						if(i==a.length) {
							//rightMin=b[j];
							median = (getMax(a[i-1],b[j-1])+b[j])/2f;
						}
						else if(j==b.length) {
							//rightMin=a[i];
							median = (getMax(a[i-1],b[j-1])+a[i])/2f;
						}
						
						else {
						median = (getMax(a[i-1],b[j-1])+getMin(a[i],b[j]))/2f;
						}
						
						System.out.println("The median number is "+median);
						System.out.println("This is an even number length array");
						break;
					}	
				}
			}
		}
		public static int getMax(int x,int y) {
			if(x-y>0) {
				return x;
			}
			else {
				return y;
			}
		}
		public static int getMin(int x,int y) {
			if(x-y>0) {
				return y;
			}
			else {
				return x;
			}
		}
	}
