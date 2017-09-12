Scenario:  1. Hiking
 
 1.1. Rent a ReachNow to drive to mountain rainier on weekends with friends
 1.2. On the way, fill the gas
 1.3. On the way, eat at restaurant for lunch
 1.4. Buy the ticket to get into the park
 1.5. Book a hotel or check in the hotel.
 
 
 Object : Hiker
 Data : name, email, phone
 Behaviors : 
 
 Object : ReachNow
 Data : url, carList, rentTimeRange, rentPrice, 
 Behaviors : search, sort, display, compare , placeTheOrder 
 
 Object : Car
 Data : maker, color, plateNumber
 Behaviors : fillGas
 
 Object : GasStation
 Data : gasType, gasPrice, gasPumpNumber
 Behaviors : 
 
 Object : Restaurant
 Data : resName, resLocation, menu, mealPrice, tips,
 Behaviors : 

 Object : RainierPark
 Data : ticketPrice, pointOfInterest, openTime
 Behaviors : 
 
 Object : Expedia
 Data : url, hotelList, checkInDate, checkOutDate, hotelPrice, 
 Behaviors : 
