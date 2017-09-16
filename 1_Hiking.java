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
  
 
