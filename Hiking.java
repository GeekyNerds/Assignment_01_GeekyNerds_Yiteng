Scenario:  1. Hiking
 
 1.1. Rent a ReachNow to drive to mountain rainier on weekends with friends
 1.2. On the way, fill the gas
 1.3. On the way, eat at restaurant for lunch
 1.4. Buy the ticket to get into the park
 1.5. Book a hotel or check in the hotel.
 
Identify Objects and Behaviors:

 Object : Hiker
 Data : name, email, phone, username, password
 Behaviors : loginToAccount
 
 Object : CarRentalSite
 Data : url, carColor, carMaker, priceRange, rentTimeRange, 
 Behaviors : searchCar, sort, display, compare , placeTheOrder 
 
 Object : Car
 Data : maker, color, plateNumber
 Behaviors : fillGas
 
 Object : GasStation
 Data : gasType, gasPrice, gasPumpNumber
 Behaviors : 
 
 Object : Restaurant
 Data : resName, resLocation, menu, mealPrice, tips,
 Behaviors : 

 Object : Park
 Data : ticketPrice, pointOfInterest, openTime
 Behaviors : 
 
 Object : hotelBookingSite
 Data : url, hotelList, checkInDate, checkOutDate, hotelPrice, 
 Behaviors : 

Sequence of Flow - Invoke Objects with Behaviors
 
 Hiker yiteng
 CarRentalSite reachnow
 Car bmw
 GasStation chevron
 Restaurant kfc
 Park rainierPark
 HotelBookingSite expedia
 
 yiteng.loginToAccount -> reachnow : authorize
 reachnow.searchCar -> priceRange, carColor, carMaker,rentTimeRange: collection of car
 bmw = car
 yiteng.rentCar -> bmw, creditCard, address, reachnow : rentConfirmation
 response = rentConfirmation
 yiteng.searchGasStation
 car.fillGas
 
 




