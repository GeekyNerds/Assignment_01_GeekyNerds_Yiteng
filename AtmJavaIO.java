

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class AtmJavaIO {
	public static void main(String[] args) {
		Atm atm = new Atm(10000000.00, 1.00, null);
		atm.runATM();
	}
}





class Atm {
	String userDataFile = "src/userDataFile.txt";
	String userPasswordFile = "src/userPasswordFile.txt";
	BufferedReader bufferReader;
	PrintStream printStream;
	PrintStream out = System.out;
	FileWriter fileWriter;
	
	private double availableAmountInMachine;
    private double transactionFee;
	static int bankAccountNumber = 100001;
	static Integer password = new Integer(100001);
	static HashMap<Integer,User> accountMapUser = new HashMap<Integer,User>();
	boolean isRunning = false;
	
	
	
	
	//Create a Atm class with attributes availableAmountInMachine, transactionFee and userData.
	//userData should store USER, PASSWORD, and other account details internally in a dataStructure of your choice.
	//The constructor should initialize all the attributes.
	public Atm(double availableAmountInMachine, double transactionFee, HashMap<User, Integer> userData) {
	    this.availableAmountInMachine = availableAmountInMachine;
		this.transactionFee = transactionFee;
		this.isRunning = true;		
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
			User user = new User (name, age, address, phoneNumber, bankAccountNumber, password);
			// write user data into .txt file
			writeUserDataIntoFile(userDataFile,user);
			accountMapUser.put(bankAccountNumber,user);
			System.out.printf("Your account: %d has been created successfully, your initial password: %d  You can log in now !\n", bankAccountNumber, password);
		}
		
		// using Java IO PrintStream to write user data into .txt file
		void writeUserDataIntoFile(String userDataFile, User user) {
			try {
				printStream = new PrintStream(new FileOutputStream(userDataFile, true), true);
				System.setOut(printStream); // locate System's output to PrintStream
				System.out.println(user);
			}catch(IOException e) {
				System.out.println(e);
			}finally {
				System.setOut(out); // relocate System'output to System.out
				printStream.close();
				
			}
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
			String[] loggedInUserData = null;
			
			// read each line in the userDataFile.txt to verify user's input
			try {
				 bufferReader = new BufferedReader(new FileReader(userDataFile));
		         String line = null;
	        
		         while(true) {
		        	 	line = bufferReader.readLine();
			        if(line!=null) {
			               String[] userDataString = line.split(",");
			               // compare accountNumber & password inputs with the accountNumber & password in the userDataFile.txt file
			               if(accountNumber==Integer.parseInt(userDataString[0].substring(8, userDataString[0].length()))
			                	  && password == Integer.parseInt(userDataString[1].substring(10, userDataString[1].length()))){
			            	   		loggedInUserData = userDataString;
			    					Integer accountOfLoggedInUser = Integer.parseInt(loggedInUserData[0].substring(8, loggedInUserData[0].length()));
			    					loggedInUser = accountMapUser.get(accountOfLoggedInUser);
			    					loggedInUser.isLoggedIn=true;
								System.out.println("You have logged in successfully !\n");
							    break;
			                }
			                else {
			                		continue;
			                }
			        	}      
		            
			        else {
		                	System.out.println("Sorry, log in failed, account number or password cannot be verified....\n");
		                	return null;
		           }
		        	
		        }
	         } catch(IOException e) {
	        	   	 System.out.println(e);
	           } finally {
	        	   	 	try {
	        	   	 		bufferReader.close();
				    } catch (IOException e) {
				    		System.out.println(e);
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
			String total ="";
			String newline="";	
			
			try {
				 bufferReader = new BufferedReader(new FileReader(userDataFile));
		         String line = "";
		     
		         while(true) {
		        	 	line = bufferReader.readLine();
			        	if(line!=null) {
			        		 
			               String[] userDataString = line.split(",");
			               if(name.equals(userDataString[2].substring(6, userDataString[2].length()))
			                	 && age.equals(userDataString[3].substring(5, userDataString[3].length()))
			                	 && phoneNumber.equals(userDataString[4].substring(10,userDataString[4].length()))){
								
			              
			                		System.out.print("User validate success, please enter your new password: ");
			                	    String password = scanner.nextLine();
			                	    newline = line.replace(userDataString[1], " Password:"+password);
								total+=(newline+"\n");
								System.out.println("Your password has been resetted successfully !\n");
							 
			                }
			                else {
			                		total+= (line+"\n");
			        
			                }
			        	  }      
			          else {
			                	System.out.println("Sorry, reset password failed, your information cannot be verified....\n");
			                	break;
			          }
		        	 
		         }
      
		         
	        } catch(IOException e) {
	        	   	 System.out.println(e);
	          } finally {
	        	   	 	try {
	        	   	 		bufferReader.close();
	        	   	 	
				    } catch (IOException e) {
				    		System.out.println(e);
				      }
	            }
			
		    try {
				fileWriter = new FileWriter(userDataFile);
				fileWriter.write(total);
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
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
			String userName =user.name;
			String userAge = user.age;
			String userPhone = user.phoneNumber;
			
			System.out.print("Please enter your new password: ");
			Scanner scanner = new Scanner(System.in);
			String password = scanner.nextLine();
			
			// read the file and locate the line of current logged-in user's data
			String line = "";
			String total = "";
			try {
				bufferReader = new BufferedReader(new FileReader(userDataFile));
				while((line=bufferReader.readLine())!=null) {
					
					String[] userDataString = line.split(",");
					if(userName.equals(userDataString[2].substring(6, userDataString[2].length()))
	                	 && userAge.equals(userDataString[3].substring(5, userDataString[3].length()))
	                	 && userPhone.equals(userDataString[4].substring(10,userDataString[4].length()))) {
						// replace the old password with new password input, then update and re-write these information into the userDataFile.txt file
						String newline = line.replace(userDataString[1]," Password:"+password);
						total+=(newline+"\n");
						
					}
					else {
						total+=(line+"\n");
					}
				}	
			} catch(IOException e){
					e.printStackTrace();
			  } finally {
					try {
						bufferReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					  }
				}
			
			// after old password is replaced with new password, then re-write the whole information into the userDataFile.txt file
			try {
				fileWriter = new FileWriter(userDataFile);
				fileWriter.write(total);
				fileWriter.close();
				System.out.println("Your password has been changed successfully !");

				
			} catch (IOException e) {
				e.printStackTrace();
			 }
			
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
	int password;
	private double availableBalance = 0.00;
	ArrayList<String> transactionHistory = new ArrayList<String>();
	boolean isLoggedIn = false;
	
	public User() {
		
	}
	
	public User(String name, String age, String address, String phoneNumber, int bankAccountNumber, int password) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.bankAccountNumber = bankAccountNumber;
		this.password = password;
	}
	
	// override the User object's default toString() method for further Java IO output to other file 
	@Override
	public String toString() {
		// decide the user data format to be written into the userDataFile.txt   
		String userData = "Account:"+this.bankAccountNumber+", Password:"+this.password+", Name:"+this.name+", Age:"+this.age+", PhoneNum:"+this.phoneNumber+", Address:"+this.address;
		return userData;
	}
	
	double getAvailableBalance() {
		return this.availableBalance;
	}
	
	void setAvailableBalance(double amount) {
		this.availableBalance+=amount;
	}

	
	
}
