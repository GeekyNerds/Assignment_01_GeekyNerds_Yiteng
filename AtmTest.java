import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

public class AtmTest {
	
	static Atm atm = new Atm(10000000.00, 1.00, null);
	static User loggedInUser;
	
	
	
     // In BeforeClass: create new user and login this user for remaining tests to use
	@BeforeClass
	static public void createNewUser_logIn_test() {
		
		// create two new users, the size of userData should be 2
		atm.createNewUser();
		atm.createNewUser();
		loggedInUser = atm.logIn();
		assertTrue(Atm.userData.size()==2);
		// after login, the user's login status should be true
		assertTrue(loggedInUser.isLoggedIn==true);	
			
	}
	
	
	@Test
	public void forgetAndResetPassword_test() {
		
		int oldPassword = Atm.userData.get(loggedInUser);
		atm.forgetAndResetPassword();
		int newPassword = Atm.userData.get(loggedInUser);
		//assertTrue(oldPassword!=newPassword);
		System.out.println("Old Password: "+oldPassword);
		System.out.println("New Password: "+newPassword);

			
	}
	
	
	@Test
	public void deposit_test() {
		
		double oldBalance = loggedInUser.getAvailableBalance();
		atm.deposit(loggedInUser);
		double newBalance = loggedInUser.getAvailableBalance();
		//assertTrue(oldBalance <= newBalance);
		System.out.println("Old Balance: "+oldBalance);
		System.out.println("New Balance: "+newBalance);
        		
	}
	
	
	@Test
	public void withDraw_test() {
		
		double oldBalance = loggedInUser.getAvailableBalance();
		atm.withDraw(loggedInUser);
		double newBalance = loggedInUser.getAvailableBalance();
		//assertTrue(oldBalance >= newBalance);	
		System.out.println("Old Balance: "+oldBalance);
		System.out.println("New Balance: "+newBalance);
			
	}
	
	
	@Test
	public void changePassword_test() {
		
		int oldPassword = Atm.userData.get(loggedInUser);
		atm.changePassword(loggedInUser);
		int newPassword = Atm.userData.get(loggedInUser);
		assertTrue(oldPassword!=newPassword);
		System.out.println("Old Password: "+oldPassword);
		System.out.println("New Password: "+newPassword);
					
	}
	
	
	@AfterClass
	static public void availableBalance_test() {

		atm.availableBalance(loggedInUser);
				
	}
	
	
	@AfterClass
	static public void recentTransactions_test() {
		
		atm.recentTransactions(loggedInUser);
				
	}
	
	
	@AfterClass
	static public void exit_test() {
		
		atm.exit(loggedInUser);
				
	}
	
		

}
