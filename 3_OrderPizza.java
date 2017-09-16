/*Scenario:  3. Order Pizza from Pizza Hut 

3.1 log into PizzaHut website and get welcome messagae from PizzaHut website
3.2 choose pizza size, crust, topping, then PizzaHut returns pizza order confirmation
3.3 pay the pizza order online using VISA card, then PizzaHut returns order paid confirmation
3.4 PizzaHut makes pizza according to pizza order details

*/


//Identify Objects and Behaviors:

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


//Sequence of Flow - Invoke Objects with Behaviors
 Customer yiteng
 PizzaWebsite pizzahut
 PizzaWebsiteLoginPage pizzahutloginpage
 PizzaOrderConfirmation pizzaorderconfirmation
 PizzaOrderPaidConfirmation pizzaorderpaidconfirmation
 Pizza mypizza
 CreditCard visacard
 CreditCardCompany visa
 
 //3.1 Yiteng logs into PizzaHut website and get welcome messagae from PizzaHut website
 yiteng.loginToAccount() -> username, password -> pizzahut : authorize & return PizzaWebsiteLoginPage object
 pizzahutloginpage.displayWelcomeMessage()
 
 //3.2 Yiteng chooses pizza size, crust, topping, then PizzaHut returns pizza order confirmation
 yiteng.createOrder() -> size, crustType, topping -> pizzahut: return PizzaOrderConfirmation object 
 
 //3.3 Yiteng pays the pizza order online using VISA card, then PizzaHut returns order paid confirmation
 yiteng.makePayment() -> CreditCard (input: cardNumber, expireMonth, expireYear, securityCode)-> visa.authorizeCard()
 pizzahut : return PizzaOrderPaidConfirmation object  
 
 //3.4 PizzaHut makes pizza according to pizza order details
 pizzahut.makePizza() -> PizzaOrderConfirmation, PizzaOrderPaidConfirmation
   
