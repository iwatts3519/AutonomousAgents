package keele.learnprogramming;

//As this is the main 'logic' Class I will explain it as fully as possible

//imported so that I can use an ArrayList, which does not have to of a fixed size
import java.util.ArrayList;

public class BrokerAgent {
    /*
     This creates a list of type String which will contain rejected offers by the processOffers method. It is public
     because it needs to be accessed outside of the class. I could have made it private and created a method to access
     it, which would possibly have been more correct, and more necessary when more than one developer is working on
     a piece of code.However, in this case there is no need to not declare the rejectedOffers as public. This will be
     used later to record the reason why the order is unfulfilled (Whether it is a stick issue or a price issue)
    */
    public static ArrayList<String> rejectedOffers = new ArrayList<>();

    /*
    Constructor - in the end there is nothing to initialise within the constructor, as the Broker does not need
     a name or anything. If the system used multiple brokers (in a large distributed, multi-threaded system) then
     more detail may be required. In it's current form it simply prints a line to confirm the Broker creation
    */
    public BrokerAgent() {
        System.out.println("Broker Created");
    }

    /*
     This is a private method that returns an integer of the number of a particular item in stock. It is used by#
     the processOrder method in this class to check that an ite is in stock before proceeding with the processing
    */
    private static int checkStock(String product) {
        /*
         Declare the inStock Variable as an integer and initialise it as 0. This means if no match is found
         the method will return 0
        */
        int inStock = 0;
        // Another example of the extended For Loop described earlier in the assignment
        for (Items item : Items.itemsList) {
            // if the product passed to the method equals an item in the itemsList then increment inStock by 1
            if (product.equals(item.getItem())) {
                inStock++;
            }
        }
        /*
         If no product is found then this will return 0 - otherwise it will return the number of items it hs
         been found
        */
        return inStock;
    }

    /*
     This is the main method of the BrokerAgent that contains all of the logic to process Orders, delete orders
     that have been successful from the orders list, but keep the ones that have not been successful for a further
     iteration. At it's heart it is a for loop which loops over the ordersList from the Class Order,
     processing them one at a time.
    */

    /*
     This is a public method that is accesses by the Main method - it only gets called once per iteration of orders
     and stock updates
    */
    public Integer processOrder() {
        /*
        If the ordersList has a size of 0 then the method ends with a return of -1 which is dealt with in the
         Main method
        */
        if (Order.ordersList.size() == 0) {
            return -1;
        }
        /*
         This creates a list of ordersToDelete which is basically fulfilled orders to make sure they do not get
         processed twice. It is used at the end of the method to remove anything in this list from the main ordersList
        */
        ArrayList<Object> ordersToDelete = new ArrayList<>();

        /*
         The start of the for loop which will loop over all of the orders in the order list (which it has already
         been established with the if statement above that the list does contain orders)
        */
        for (Order order : Order.ordersList) {
            /*
             This if statement checks to see if the item is in stock anywhere and if it isn't it does not get added
             to ordersToDelete (meaning it won't get deleted from the ordersList).
            */
            if (checkStock(order.getProduct()) == 0) {
                System.out.println("Not processing order for " + order.getBuyer() + ". Item " + order.getProduct() + " is not in stock\n");
                /*
                 Three strings are added to rejected offers - these wil always be added in threes, meaning that we
                 will be able to loop over the list using a standard for loop and indexing
                */
                rejectedOffers.add(order.getBuyer());
                rejectedOffers.add(order.getProduct());
                rejectedOffers.add("Not In Stock");

            } else {
                // If the item is in stock, then the order is processed
                System.out.println("Processing order for " + order.getBuyer() + ". Item " + order.getProduct() + " is in stock");
                /*
                 This for loop finds the cheapest place that the item is in stock - it sets Cheapest to a million,
                 as no items will cost that much, then so long as the item is cheaper than that, then cheapest is
                 set to be equal to the current price.
                */
                double cheapest = 1000000;
                for (Items item : Items.itemsList) {
                    if (item.getItem().equals(order.getProduct()) && item.getPrice() < cheapest) {
                        cheapest = item.getPrice();
                    }

                }
                /*
                 This line asks if the return from processOffers is True, and if so the order is added to
                 ordersToDelete so that they do not get processed again during the next iteration
                */
                if (Buyer.processOffers(order.getBuyer(), cheapest, order.getMinValue(), order.getMaxValue())){
                    ordersToDelete.add(order);
                    /*
                     If the return is false, the order stays in the orders list but gets added to the rejectedOffers
                     list this time with a reason of Incorrect Price
                    */
                } else {
                    System.out.println("Offer Rejected\n");
                    rejectedOffers.add(order.getBuyer());
                    rejectedOffers.add(order.getProduct());
                    rejectedOffers.add("Incorrect Price");
                }
            }

        // This is the end of the for loop
        }
        /*
         All fulfilled offers (that have been added to the ordersToDelete list) are now removed from the ordersList
         to ensure they do not get processed again
        */
        Order.ordersList.removeAll(ordersToDelete);
        /*
         Finally the updated size of the ordersList is returned to Main so that it knows how many offers are
         unfulfilled
        */
        return Order.ordersList.size();
    }

}

