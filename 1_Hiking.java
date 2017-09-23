/*Scenario:  1. Hiking
 
 1.1 rent a ReachNow BMW car using VISA credit card
 1.2 on the way, use Google Maps to find route to Chevron gas station, make payment using VISA card, then fill the gas
 1.3 on the way, use Google Maps to find route to KFC restaurant, make payment using VISA card
 1.4 buy the ticket using VISA card at Mountain Rainier Park
 1.5 book Holiday Inn via Expedia website 
 
*/
 

//Identify Objects and Behaviors:

 Object : Hiker
 Attribute : name, emailAddr, billingAddr, phoneNumber, username, password, signature
 Behaviors : loginToAccount(), pressCarBrake(), pressCarAccelerate(), rotateSteerWheel(), createOrder(), makePayment()
 
 Object : CarRentalSite
 Attribute : url, carColor, carMaker, priceRange, rentTimeRange, 
 Behaviors : searchCar(), sort(), compare(), placeTheOrder() 
 
 Object : CarRentalSiteLoginPage
 Attribute : welcomeMessage, accountInfo, accountSetting, 
 Behaviors : displayUserInfo()
 
 Object : Car
 Attribute : carMaker, carColor, plateNumber
 Behaviors :

 Object : CarRentOrderConfirmation
 Attribute : orderConfirmMessage
 Behaviors :

 Object : CarRentPaidConfirmation
 Attribute : paidConfirmMessage
 Behaviors :
  
 Object : CreditCard
 Attribute : cardNumber, expireMonth, expireYear, securityCode
 Behaviors : 
  
 Object : CreditCardCompany
 Attribute : companyName
 Behaviors : authorizeCard()
  
 Object : MapNavigator
 Attribute : navigatorName
 Behaviors : getRoute(), getNearbyPlaceOfInterest()
  
 Object : Route
 Attribute : routeName
 Behaviors : 
 
 Object : GasStation
 Attribute : gasStationName, gasStationLocation, gasPumpNumber, 
 Behaviors : outputGas()
  
 Object : Gas
 Attribute : gasType, gasPrice, gasAmount
 Behaviors : 

 Object : GasOrderConfirmation
 Attribute : orderConfirmMessage
 Behaviors : 

 Object : GasPaidConfirmation
 Attribute : paidConfirmMessage
 Behaviors : 
 
 Object : Restaurant
 Attribute : resName, resLocation
 Behaviors : makeMeal()

 Object : Menu
 Attribute : mealName, mealPrice, mealAmount
 Behaviors :  

 Object : MealOrderConfirmation
 Attribute : orderConfirmMessage
 Behaviors : 

 Object : MealPaidConfirmation
 Attribute : paidConfirmMessage
 Behaviors : 

 Object : Park
 Attribute : parkName, pointOfInterest, openTime
 Behaviors : issueTicket()

 Object : Ticket
 Attribute : ticketPrice, ticketAmount
 Behaviors : 

 Object : TicketOrderConfirmation
 Attribute : orderConfirmMessage
 Behaviors : 

 Object : TicketPaidConfirmation
 Attribute : paidConfirmMessage
 Behaviors : 
 
 Object : HotelBookingSite
 Attribute : url, hotelList, checkInDate, checkOutDate, hotelPrice, 
 Behaviors : authorize(), searchHotel() 
 
 Object : Hotel
 Attribute : name, hotelLocation
 Behaviors : 

 Object : HotelBookOrderConfirmation
 Attribute : orderConfirmMessage
 Behaviors : 

 Object : HotelBookPaidConfirmation
 Attribute : paidConfirmMessage
 Behaviors : 
 

//Sequence of Flow - Invoke Objects with Behaviors
 
 Hiker yiteng
 CarRentalSite reachnow
 CarRentalSiteLoginPage reachnowloginpage
 Car bmw
 CarRentOrderConfirmation carorderconfirmmessage
 CarRentPaidConfirmation carpaidconfirmmessage
 CreditCard visacard
 CreditCardCompany visa
 MapNavigator googlemaps
 Route route
 GasStation chevron
 Gas gas
 GasOrderConfirmation gasorderconfirmmessage
 GasPaidConfirmation gaspaidconfirmmessage
 Restaurant kfc
 Menu menu
 MealOrderConfirmation mealorderconfirmmessage
 MealPaidConfirmation mealpaidconfirmmessage
 Park rainierPark
 Ticket ticket
 TicketOrderConfirmation ticketorderconfirmmessage
 TicketPaidConfirmation ticketpaidconfirmmessage
 HotelBookingSite expedia
 Hotel hotel
 HotelBookOrderConfirmation hotelbookorderconfirmmessage
 HotelBookPaidConfirmation hotelbookpaidconfirmmessage
 
 //1.1. Rent a ReachNow BMW car using VISA credit card
 yiteng.loginToAccount() -> username, password -> reachnow : authorize & return CarRentalSiteLoginPage object
 reachnow.searchCar() -> priceRange, carColor, carMaker, rentTimeRange -> reachnow : return Car object collection
 yiteng.createOrder() -> Car(input: bmw) -> reachnow: return CarRentOrderConfirmation object 
 yiteng.makePayment() -> CreditCard (input: cardNumber, expireMonth, expireYear)-> visa.authorizeCard()
 reachnow : return CarRentPaidConfirmation object
 
 //1.2. On the way, use Google Maps to find route to Chevron gas station, make payment using VISA card, then fill the gas
 googlemaps.getNearbyPlaceOfInterest() -> gasStationName -> googlemaps.getRoute() -> googlemaps: return Route object
 yiteng.pressCarAccelerate()
 yiteng.rotateSteerWheel()
 yiteng.pressCarBrake()
 yiteng.createOrder() -> Gas(input: gasType, gasPrice, gasAmount) -> chevron: return GasOrderConfirmation object 
 yiteng.makePayment() -> CreditCard -> visa.authorizeCard()
 chevron: return GasPaidConfirmation object
 chevron.outputGas()
  
 //1.3. On the way, use Google Maps to find route to KFC restaurant, make payment using VISA card
 googlemaps.getNearbyPlaceOfInterest() -> resName -> googlemaps.getRoute() -> googlemaps: return Route object
 yiteng.pressCarAccelerate()
 yiteng.rotateSteerWheel()
 yiteng.pressCarBrake()
 yiteng.createOrder() -> Menu(input: mealName, mealPrice, mealAmount)-> kfc: return MealOrderConfirmation object 
 yiteng.makePayment() -> CreditCard-> visa.authorizeCard()
 kfc: return MealPaidConfirmation object
 kfc.makeMeal()
  
 //1.4. Buy the ticket using VISA card at Mountain Rainier Park
 yiteng.createOrder() -> Ticket -> rainierPark: return TicketOrderConfirmation object 
 yiteng.makePayment() -> CreditCard-> visa.authorizeCard()
 rainierPark: return TicketPaidConfirmation object
 rainierPark.issueTicket()
  
 //1.5. Book Holiday Inn via Expedia website
 yiteng.loginToAccount() -> username, password -> expedia : authorize & return LoginConfirmation object
 expedia.searchHotel() -> priceRange, hotelLocation, roomType, bookTimeRange -> expedia : return Hotel object collection
 yiteng.createOrder() -> Hotel(input: holidayinn) -> expedia: return HotelBookOrderConfirmation object 
 yiteng.makePayment() -> CreditCard -> visa.authorizeCard()
 expedia : return HotelBookPaidConfirmation object
 
 
 /*---------------------------Scenario:  2. Organise a career fair(Suppose you are the organiser)--------------------------------

2.1 set up a questionaire website to collect student's preferred companies, career fair open dates, how many students willing to come  
2.2 according to the questionaire result, invite companies, decide career fair open dates
2.3 according to the invitation result, reserve room that meets company & student's need
2.4 set up a career fair website for student to see career fair information detail, register check-in ticket and receive update
2.5 according to the registration result, decide whether further action is needed, such as "if a larger room is needed or not?"
2.6 collect students and companies feedbacks about this career fair.

*/

//Identify Objects and Behaviors://

 Object : Organisor
 Attribute : name, emailAddr, phoneNumber, username, password
 Behaviors : loginAccount(), setUpWebsite(), sendEmail(), reserveRoom()
  
 Object : Student
 Attribute : studentName, studentEmailAddr, phoneNumber, username, password
 Behaviors : loginAccount(), answerQuestionaire(), registerCareerFair(), giveFeedback(), sendEmail()
  
 Object : Company
 Attribute : companyName, location, companyEmailAddr, jobTitle
 Behaviors : respondInvitation(), giveFeedback(), sendEmail()
  
 Object : QuestionaireWebsite
 Attribute : url
 Behaviors : authorize()
  
 Object : Questionaire
 Attribute : Question[] question
 Behaviors : 
   
 Object : CareerFairWebsite
 Attribute : url
 Behaviors : authorize()
  
 Object : LoginConfirmation
 Attribute : welcomeMessage
 Behaviors : 
  
 Object : QuestionaireResult
 Attribute : answeredMessage
 Behaviors : 
  
 Object : InvitationResult
 Attribute : boolean 
 Behaviors : 
  
 Object : RegistrationResult
 Attribute : registrationAmount
 Behaviors : 
 

//Sequence of Flow - Invoke Objects with Behaviors//
 
 Organisor yiteng
 Student student
 Company company
 QuestionaireWebsite questionwebsite
 Questionaire questionaire
 CareerFairWebsite careerwebsite
 LoginConfirmation loginconfirmation
 QuestionaireResult questionaireresult
 InvitationResult invitationresult
 RegistrationResult registrationresult
 
 //2.1 Set up a questionaire website to collect student's preferred companies, career fair open dates, how many students willing to come
 yiteng.setUpWebsite() -> Questionaire (input: question[0], question[1], ...)
 yiteng.sendEmail() -> studentEmailAddr
 student.loginAccount() -> username, password -> questionairewebsite: authorize & return LoginConfirmation object
 student.answerQuestionaire() -> companyName, preferredOpenDate, jobTitle -> questionwebsite: return QuestionaireResult object
 
 //2.2 According to the questionaire result, invite companies, decide career fair open dates
 yiteng.sendEmail() -> Invitation (input: companyEmailAddr, time, location, message) -> company: return InvitationResult object
 
 //2.3 According to the invitation result, reserve room that meets company & student's need
 yiteng.reserveRoom()
  
 //2.4 Set up a career fair website for student to see career fair information detail, register check-in ticket and receive update
 yiteng.setUpWebsite() -> openTime, fairLocation, companyName, jobTitle
 student.registerCareerFair() -> studentName, studentEmailAddr, phoneNumber -> careerwebsite: return RegistrationResult object
 
 //2.5 According to the registration result, decide whether further action is needed, such as "if a larger room is needed or not?"
 if(registration amount > room capacity)
 yiteng.reserveRoom()
 end
 
 //2.6 Collect students and companies feedbacks about this career fair
 student.giveFeedback()
 company.giveFeedback()
  
 
/*---------------------------------Scenario:  3. Order Pizza from Pizza Hut ---------------------------------------------

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
   

  
  

  
 
