
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Scanner;

public class Atm {
	private double availableAmountInMachine;
  private double transactionFee;
	static int bankAccountNumber = 100001;
	static Integer password = new Integer(100001);
	static HashMap<User, Integer> userData = new HashMap<User, Integer>();
	boolean isRunning = false;
	
	//Create a Atm class with attributes availableAmountInMachine, transactionFee and userData.
	//userData should store USER, PASSWORD, and other account details internally in a dataStructure of your choice.
	//The constructor should initialize all the attributes.
	public Atm(double availableAmountInMachine, double transactionFee, HashMap<User, Integer> userData) {
	  this.availableAmountInMachine = availableAmountInMachine;
		this.transactionFee = transactionFee;
		this.isRunning = true;		
	}
	
	public static void main(String[] args) {
		
		Atm atm = new Atm(10000000.00, 1.00, null);
		atm.runATM();
	}
	
	//Create a NEW USER with a unique bankAccountNumber and password.
		void createNewUser() {
			System.out.print("Please enter your name: ");
			Scanner scanner = new Scanner(System.in);
			String name = scanner.nextLine();
			System.out.print("Please enter your age: ");
			String age = scanner.nextLine();
			System.out.print("Please enter your address: ");
			String address = scanner.nextLine();
			System.out.print("Please enter your phone number: ");
			String phoneNumber = scanner.nextLine();
			int bankAccountNumber = Atm.bankAccountNumber;// assign account number to new user
			Atm.bankAccountNumber++;// ensure account number is unique for every user
			Integer password = Atm.password; // pre-assign an initial password to new user
			Atm.password++;// ensure initial password is unique for every user
			User user = new User (name, age, address, phoneNumber, bankAccountNumber);
			// put User object and password into HashMap data structure
			userData.put(user,password);
			System.out.printf("Your account: %d has been created successfully, your initial password: %d  You can log in now !\n", bankAccountNumber, password);
		}
		
		// CURRENT USER should be able to login using bank account number and password
		User logIn() {
			System.out.print("Please enter your account number: ");
			Scanner scanner = new Scanner(System.in);
			int accountNumber = scanner.nextInt();
			System.out.print("Please enter your password: ");
			Integer password = scanner.nextInt();
			User loggedInUser = null;
			int traverseNumber = 0;
			for(Entry<User, Integer> entry : userData.entrySet()) {
				
				if(accountNumber==entry.getKey().bankAccountNumber && password.equals(entry.getValue())) {
					entry.getKey().isLoggedIn = true;
					System.out.printf("%s, you have logged in successfully !\n",entry.getKey().name);
					loggedInUser = entry.getKey();
					break;
				}
				else {
					traverseNumber++;
					if(traverseNumber == userData.size()) {
						System.out.println("Sorry, log in failed, account number or password cannot be verified....");
						return null;
					}
					else {
						continue;
					}
				}
			}
			
			return loggedInUser;
		 }
		
			

		
		//CURRENT USER should be able to use FORGOT PASSWORD.
		//PASSWORD can be resetted by validating the name, age and phoneNumber of the user.
		void forgetAndResetPassword() {
			System.out.print("To reset password, please enter your name: ");
			Scanner scanner = new Scanner(System.in);
			String name = scanner.nextLine();
			System.out.print("Please continue to enter your age: ");
			String age = scanner.nextLine();
			System.out.print("Please continue to enter your phone number: ");
			String phoneNumber = scanner.nextLine();
			int traverseNumber = 0;
			for(User user : userData.keySet()) {
				if(name.equals(user.name) && age.equals(user.age) && phoneNumber.equals(user.phoneNumber)) {
					System.out.print("User validate success, please enter your new password: ");
					int password = scanner.nextInt();
					userData.remove(user, userData.get(user));
					userData.put(user, password);
					System.out.println("Your password has been resetted successfully !");
					break;
				}
				else {
					traverseNumber++;
					System.out.println("taverseNum"+traverseNumber+userData.size());
					if(traverseNumber == userData.size()) {
						System.out.println("Sorry, reset password failed, your information cannot be verified....");
					}
					else {
						continue;
					}
				}
			}
		 }
			
		
		
		void availableBalance(User user) {
			System.out.printf("Available Balance: $ %.2f\n", user.getAvailableBalance());
		}
		
		void withDraw(User user) {
			System.out.print("Please enter withdraw amount: ");
			Scanner scanner = new Scanner(System.in);
			double amount = scanner.nextDouble();
			double amount_transactionFee = amount+transactionFee;
			if(amount_transactionFee > user.getAvailableBalance()) {
				System.out.printf("Sorry, insufficienty balance! Total deduct amount is $ %.2f (transaction fee included). Your available balance is $ %.2f\n",amount_transactionFee, user.getAvailableBalance());
			}
			else {
				if(amount > this.availableAmountInMachine) {
					System.out.printf("Sorry, your withdraw cash $ %.2f is beyond the available cash in this ATM !\n",amount);

				}
				else {
					user.setAvailableBalance(-amount_transactionFee);
					updateAvailableAmountInMachine(-amount);
					user.transactionHistory.add("withdraw - $ "+amount_transactionFee);
					System.out.printf("Withdraw success (transaction fee included) ! Now your available balance is: $ %.2f\n", user.getAvailableBalance());
				}	
			}
		 }
		
		void deposit(User user) {
			
			System.out.print("Please enter deposit amount: ");
			Scanner scanner = new Scanner(System.in);
			double amount = scanner.nextDouble();
			double amount_transactionFee = amount-transactionFee;
			user.setAvailableBalance(amount_transactionFee);
			updateAvailableAmountInMachine(amount);
			user.transactionHistory.add("deposit  - $ "+amount_transactionFee);
			System.out.printf("Deposit success (transaction fee included) ! Now your available balance is: $ %.2f\n", user.getAvailableBalance());	
		}
		
		
		/* recentTransaction should display the last 10 trasactions, 
		   in a format of transactionName - amount. 
		   (transactionName is withDrawal or deposit). 
		   e.g. withdrawal - $ 400.0
		*/
		void recentTransactions(User user) {
			
			int recordsNumber = 10;
			if(user.transactionHistory.size()==0) {
				System.out.println("There are no transaction records in your account: "+user.bankAccountNumber);
			
			}
			else {	
				System.out.println("Recent transactions of your account: "+user.bankAccountNumber);
				
				for(int i=user.transactionHistory.size()-1; i>=0; i--) {
					System.out.println(user.transactionHistory.get(i));
					if(i==user.transactionHistory.size()-recordsNumber) {
						break;
					}
					else {
						continue;
					}
				}
			}
			
		}
		
		void changePassword(User user) {
			System.out.print("Please enter your new password: ");
			Scanner scanner = new Scanner(System.in);
			int password = scanner.nextInt();
			userData.remove(user, userData.get(user));
			userData.put(user, password);
			System.out.println("Your password has been changed successfully !");
		}
		
		void exit(User user) {
			user.isLoggedIn = false;
			System.out.printf("%s, you have logged out your account. Thanks for using Bank of Java ATM !\n",user.name);
		}
		
		void displayWelcomePage() {
			System.out.println("-------------------------- Welcome to Bank of Java ATM ---------------------------");
			System.out.println(" 1. Create New Account (new user)   2. Log In (current user)   3. Reset Password");
			System.out.print(">>> Please select: ");
		}
		
		void displayLogInPage(User user) {
			System.out.printf("-------------- Hello, %s, You have logged into your account: %d ------------\n", user.name,user.bankAccountNumber);
			System.out.println(" 1. Available Balance         2. WithDraw                 3. Deposit");
			System.out.println(" 4. Recent Transactions       5. Change Password          6. Exit");
			System.out.print(">>> Please select: ");
		}
		
		void updateAvailableAmountInMachine(double amount) {
			this.availableAmountInMachine += amount;
		}
		
		void runATM() {
			while (this.isRunning = true) {
				displayWelcomePage();
				Scanner scanner = new Scanner(System.in);
				String choice = scanner.nextLine();
				
				if(choice.equals("1")) {
					createNewUser();	
				}
				
				else if(choice.equals("2")) {
					// run login() method to do user login process ,then get current logged-in user
					User logInUser = logIn();
					
					while(logInUser!=null && logInUser.isLoggedIn==true) {
						displayLogInPage(logInUser);
						
						String select = scanner.nextLine();
						if(select.equals("1")) {
							availableBalance(logInUser);	
						}
						else if(select.equals("2")) {
							withDraw(logInUser);
						}
						else if(select.equals("3")) {
							deposit(logInUser);
						}
						else if(select.equals("4")) {
							recentTransactions(logInUser);
						}
						else if(select.equals("5")) {
							changePassword(logInUser);
						}
						else if(select.equals("6")) {
							exit(logInUser);
						}
						else {
							System.out.println("invalid input!!! Please select again");
						}	
					}
				}
				else if(choice.equals("3")) {
					forgetAndResetPassword();
				}
				
				else {
					System.out.println("invalid input!!! Please select again");
					
				}
			}
		}
	
}



class User {
	String name;
	String age;
	String address;
	String phoneNumber;
	int bankAccountNumber;
	private double availableBalance = 0.00;
	ArrayList<String> transactionHistory = new ArrayList<String>();
	boolean isLoggedIn = false;
	
	public User() {
		
	}
	
	public User(String name, String age, String address, String phoneNumber, int bankAccountNumber) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.bankAccountNumber = bankAccountNumber;
	}
	
	double getAvailableBalance() {
		return this.availableBalance;
	}
	
	void setAvailableBalance(double amount) {
		this.availableBalance+=amount;
	}

	
	
}
