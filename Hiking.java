/*Scenario:  1. Hiking
 
 1.1. Rent a ReachNow to drive to mountain rainier on weekends with friends
 1.2. On the way, fill the gas
 1.3. On the way, eat at restaurant for lunch
 1.4. Buy the ticket to get into the park
 1.5. Book a hotel or check in the hotel.  */
 
//Identify Objects and Behaviors://

 Object : Hiker
 Attribute : name, emailAddr, billingAddr, phoneNumber, username, password, signature
 Behaviors : loginToAccount(), pressCarBrake(), pressCarAccelerate(), rotateSteerWheel(), makePayment()
 
 Object : CarRentalSite
 Attribute : url, carColor, carMaker, priceRange, rentTimeRange, 
 Behaviors : searchCar(), sort(), compare() , placeTheOrder() 
 
 Object : CarRentalSiteLoginPage
 Attribute : welcomeMessage, accountInfo, accountSetting, 
 Behaviors : displayUserInfo()
 
 Object : Car
 Attribute : carMaker, carColor, plateNumber
 Behaviors : driveWheel()
  
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
 Attribute : gasType, gasPrice, gasAmount
 Behaviors : 
 
 Object : GasStation
 Attribute : gasStationName, gasStationLocation, gasPumpNumber, 
 Behaviors : outputGas()
 
 Object : Restaurant
 Attribute : resName, resLocation
 Behaviors : makeMeal

 Object : Menu
 Attribute : mealName, mealPrice
 Behaviors :  

 Object : Park
 Attribute : ticketPrice, pointOfInterest, openTime
 Behaviors : 
 
 Object : HotelBookingSite
 Attribute : url, hotelList, checkInDate, checkOutDate, hotelPrice, 
 Behaviors : 


//Sequence of Flow - Invoke Objects with Behaviors//
 
 Hiker yiteng
 CarRentalSite reachnow
 CarRentalSiteLoginPage reachnowloginpage
 Car bmw
 CreditCard visacard
 CreditCardCompany visa
 MapNavigator googlemaps
 GasStation chevron
 Restaurant kfc
 Park rainierPark
 HotelBookingSite expedia
 
 //1.1. Rent a ReachNow BMW car using VISA credit card//
 yiteng.loginToAccount() -> username, password -> reachnow : authorize & return LoginConfirmation object
 reachnow.searchCar() -> priceRange, carColor, carMaker,rentTimeRange -> reachnow : return Car object collection
 bmw = Car // choose BMW car (initialize Car object)
 yiteng.rentCar() -> bmw, CreditCard -> visa.authorizeCard()
 reachnow : return CarRentConfirmation object
 
 //1.2. On the way, use Google Maps to find route to Chevron gas station, make payment using VISA card, then fill the gas//
 googlemaps.getNearbyPlaceOfInterest() -> gasStationName -> googlemaps.getRoute() -> googlemaps: return Route object
 yiteng.pressCarAccelerate()
 yiteng.rotateSteerWheel()
 yiteng.pressCarBrake()
 yiteng.makePayment() -> CreditCard, gasType, gasPrice, gasAmount -> visa.authorizeCard()
 chevron: return GasPurchaseConfirmation object
 chevron.outputGas()
  
 //1.3. On the way, use Google Maps to find route to KFC restaurant, make payment using VISA card, then have//
 googlemaps.getNearbyPlaceOfInterest() -> resName -> googlemaps.getRoute() -> googlemaps: return Route object
 yiteng.pressCarAccelerate()
 yiteng.rotateSteerWheel()
 yiteng.pressCarBrake()
 yiteng.creatOrder() -> Menu -> kfc: return OrderConfirmation object 
 yiteng.makePayment() -> CreditCard-> visa.authorizeCard()
 kfc: return OrderPaidConfirmation object
 kfc.makeMeal()
  
  
  
 
 
 




