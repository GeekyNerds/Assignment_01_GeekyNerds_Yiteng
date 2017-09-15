/*Scenario:  1. Hiking
 
 1.1. Rent a ReachNow to drive to mountain rainier on weekends with friends
 1.2. On the way, fill the gas
 1.3. On the way, eat at restaurant for lunch
 1.4. Buy the ticket to get into the park
 1.5. Book a hotel or check in the hotel.  */
 
//Identify Objects and Behaviors://

 Object : Hiker
 Attribute : name, emailAddr, phoneNumber, username, password
 Behaviors : loginToAccount(), pressCarBrake(), pressCarAccelerate(), makePayment()
 
 Object : CarRentalSite
 Attribute : url, carColor, carMaker, priceRange, rentTimeRange, 
 Behaviors : searchCar(), sort(), compare() , placeTheOrder() 
 
 Object : CarRentalSiteLoginPage
 Attribute : welcomeMessage, accountInfo, accountSetting, 
 Behaviors : displayUserInfo()
 
 Object : Car
 Attribute : carMaker, carColor, plateNumber
 Behaviors : fillGas()
  
 Object : CreditCard
 Attribute : cardNumber, expireMonth, expireYear, securityNumber
 Behaviors : 
  
 Object : CreditCardCompany
 Attribute : companyName
 Behaviors : authorizeCard()
  
 Object : MapNavigator
 Attribute : navigatorName
 Behaviors : getRoute(), getNearbyPlaceOfInterest()
 
 Object : Gas
 Attribute : gasType, gasPrice
 Behaviors : 
 
 Object : GasStation
 Attribute : gasStationName, gasStationLocation, gasPumpNumber, 
 Behaviors : 
 
 Object : Restaurant
 Attribute : resName, resLocation, menu, mealPrice, tips,
 Behaviors : 

 Object : Park
 Attribute : ticketPrice, pointOfInterest, openTime
 Behaviors : 
 
 Object : hotelBookingSite
 Attribute : url, hotelList, checkInDate, checkOutDate, hotelPrice, 
 Behaviors : 


//Sequence of Flow - Invoke Objects with Behaviors//
 
 Hiker yiteng
 CarRentalSite reachnow
 CarRentalSiteLoginPage reachnowloginpage
 Car bmw
 CreditCardCompany visa
 MapNavigator googlemaps
 GasStation chevron
 Restaurant kfc
 Park rainierPark
 HotelBookingSite expedia
 
 //1.1. Rent a ReachNow BMW car using VISA credit card//
 yiteng.loginToAccount() -> username, password -> reachnow : authorize & return reachnowloginpage
 rechnowloginpage.displayUserInfo()
 reachnow.searchCar() -> priceRange, carColor, carMaker,rentTimeRange -> reachnow : return collection of car
 bmw = car
 yiteng.rentCar() -> bmw, cardNumber, expireMonth, expireYear, securityNumber, address -> visa.authorizeCard() -> reachnow : return rentConfirmation
 
 //1.2. On the way, use Google Maps to find route to Chevron gas station, make payment using VISA card, then fill the gas//
 googlemaps.getNearbyPlaceOfInterest() -> gasStationName -> googlemaps.getRoute()
 yiteng.pressCarAccelerate()
 yiteng.pressCarBrake()
 yiteng.makePayment() -> cardNumber, expireMonth, expireYear, securityNumber, gasType, gasPrice -> visa.authorizeCard()
 bmw.fillGas()
 
 
 




