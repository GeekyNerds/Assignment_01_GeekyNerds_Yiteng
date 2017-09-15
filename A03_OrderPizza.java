/*Scenario:  3. Order Pizza from Pizza Hut */


//Identify Objects and Behaviors://

 Object : Customer
 Attribute : name, homeAddr, emailAddr, phoneNumber, username, password, signature
 Behaviors : loginToAccount(), createOrder(), makePayment()
   
 Object : PizzaWebsite
 Attribute : url
 Behaviors : authorize(), returnLoginPage(), makePizza()
   
 Object : Pizza
 Attribute : size, crustType, topping
 Behaviors : 
 
 Object : PizzaWebsiteLoginPage
 Attribute : welcomeMessage, accountInfo, accountSetting, 
 Behaviors : displayWelcomeMessage()
   
 Object : PizzaOrderConfirmation
 Attribute : pizzaName, pizzaOrderReference, orderTotalPrice
 Behaviors : 
   
 Object : PizzaOrderPaidConfirmation
 Attribute : orderPaidDate, orderPaidReference
 Behaviors : 
   
 Object : CreditCard
 Attribute : cardNumber, expireMonth, expireYear, securityCode
 Behaviors : 
  
 Object : CreditCardCompany
 Attribute : companyName
 Behaviors : authorizeCard()


//Sequence of Flow - Invoke Objects with Behaviors//
 Customer yiteng
 PizzaWebsite pizzahut
 PizzaWebsiteLoginPage pizzahutloginpage
 PizzaOrderConfirmation pizzaorderconfirmation
 PizzaOrderPaidConfirmation pizzaorderpaidconfirmation
 Pizza mypizza
 CreditCard visacard
 CreditCardCompany visa
 
 
 yiteng.loginToAccount() -> username, password -> pizzahut : authorize & return PizzaWebsiteLoginPage object
 pizzahutloginpage.displayWelcomeMessage()
 yiteng.createOrder() -> size, crustType, topping -> pizzahut: return PizzaOrderConfirmation object 
 yiteng.makePayment() -> CreditCard (input: cardNumber, expireMonth, expireYear, securityCode)-> visa.authorizeCard()
 pizzahut : return PizzaOrderPaidConfirmation object  
 pizzahut.makePizza()
   
   
   
   

public class Customer {

	// Data
	// <type> <name>;
	String name;
	String userName;
	String password;
	String address;
	String phone;
	CreditCard creditCard;

	// Behaviors - <output> name(<input1>, <input2>,);

	LoginPage login(Website website) {
		boolean response = website.validate(userName, password);
		if (response == true) {
			LoginPage lp = website.getLoginPage(userName);
			String message = lp.getMessage();
			System.out.println(message);
			return lp;
		} else {
			System.out.println(userName + " is unauthorized to log in website");
		}
		return null;
	}

}

class Website {
	boolean validate(String userName, String password) {
		// return true;
		// Logic to validate

		if (userName.startsWith("A")) {
			return true;
		}
		if (userName.startsWith("B")) {
			return true;
		}
		if (userName.startsWith("C")) {
			return true;
		}
		return false;

	}

	LoginPage getLoginPage(String userName) {
		// Create Object - new <name_of_the_class>();
		LoginPage lp = new LoginPage();
		lp.message = "Hi, " + userName;
		return lp;

	}
}

class LoginPage {
	String message;

	String getMessage() {
		return message;
	}

}

class CreditCard {
	String number;
	String nameOfCompany;
	int expiryMonth;
	int expiryYear;
	String securityCode;
}
