/*Scenario:  5. Design a soft-drink/snacks vending machine

5.1 be able to validate if a user input a valid product number, if not re-enter product number
5.2 be able to show user order price in total
5.3 be able to validate the money that user insert, if money is not enough or invalid money inserted, ask for re-insert 
5.4 be able to output the product that user orders
5.5 be able to update product inventory information
5.6 be able to alert admin to refill if certain product is in a low inventory

*/

//Identify Object and Behaviors:

Object : VendingMachine
Attribute : machineName, machineColor, machineMaker, location, Product[][] products
Behaviors : validateUserOrderInput(), showOrderPrice(), validateMoneyInput(), outputProduct(), updateInventory(),
            alertLowInventory()

           
public class VendingMachine{
     string location;
     string[][] product; // two-dimension array e.g. {{"001","coke"},{"002","cookie"},....}
     string machineName
     string machineColor
     string machineMaker
     
     //validate if a user input a valid product number, if not re-enter product number
     float validateUserOrderInput(){
     
     }
     
     //show user order price in total
     float showOrderPrice(){
     
     }
     
     //validate the money that user insert
     boolean validateMoneyInput(){
     
     }
     
     //output the product that user orders
     string outputProduct(){
     
     }
     
     //update product inventory information
     string updateInventory(){
     
     }
     
     //if certain product is in a low inventory, alert admin to refill
     string alertLowInventory(){
     
     }

}
