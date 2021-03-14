package keele.learnprogramming;

public class Main {
/*
The Main Class controls the programme, creating the datA and the agents and setting the Broker working with a single line
that deploys the logic of the broker which interacts with Buyer and Seller Agents. Broker, Buyer and Seller agents are
separate as they have separate logic though I am sure the same could possibly be achieved with a single Agent Class that
has a value Type (fOR Broker, Seller AND Buyer). However, in this simulation there seemed little to be gained by this.
 */

    public static void main(String[] args) {

        // Throughout I have used lines like the following to separate the output into an easy to read and logical form
        System.out.println("\n*********************");
        System.out.println("*** Create Seller ***");
        System.out.println("*********************\n");

        /*
         This line creates our first Agent - a seller agent called sainsburys which will then be used to add stock
         which will be used to call the Seller.addStock method.
        */

        Seller sainsburys = new Seller("Sainsbury's");

        System.out.println("\n****************************");
        System.out.println("*** Seller Creates Stock ***");
        System.out.println("****************************\n");

        /*
         Once a seller is created, it can be used to add stock and price using the addStock method. This will be explained
         once we get to the Seller class
        */

        sainsburys.addStock("Apple", 0.19);
        sainsburys.addStock("Orange", 0.29);
        sainsburys.addStock("Grapes", 1.49);


        // A repeat of the above but for Tesco

        System.out.println("\n*********************");
        System.out.println("*** Create Seller ***");
        System.out.println("*********************\n");

        Seller tesco = new Seller("Tesco");

        System.out.println("\n****************************");
        System.out.println("*** Seller Creates Stock ***");
        System.out.println("****************************\n");

        tesco.addStock("Apple", 0.21);
        tesco.addStock("Orange", 0.28);
        tesco.addStock("Pans", 29.99);
        tesco.addStock("Vacuum Cleaner", 79.99);

        // We create a Broker agent that will be called upon to negotiate orders and offers between Buyers and Sellers.
        // More detail about this process will be given in the separate Agent classes

        System.out.println("\n*********************");
        System.out.println("*** Create Broker ***");
        System.out.println("*********************\n");
        BrokerAgent broker1 = new BrokerAgent();

        /*
        Using the printStock method of the Seller we can at any time check the stock levels of the various sellers - this
        has been used to demonstrate how we can use the Main function to use simple Methods to perform tasks.
         */
        System.out.println("\n*******************");
        System.out.println("*** Print Stock ***");
        System.out.println("*******************\n");
        sainsburys.printStock();
        System.out.println("-----");
        tesco.printStock();

        /*
        The following code creates buyers with names that will then be used to create orders (a method of the Buyer
        Class that the Broker can interact with).
         */
        System.out.println("\n*********************");
        System.out.println("*** Create Buyers ***");
        System.out.println("*********************\n");

        Buyer buyer1 = new Buyer("Stuart");
        Buyer buyer2 = new Buyer("Gabi");
        Buyer buyer3 = new Buyer("Anne");
        Buyer buyer4 = new Buyer("David");

        /*
        Seven orders are created. One of the orders should be rejected because none of the Sellers stock the item
        and two of the orders should be rejected because it isn't sold at the right price. Each order has a minimum
        and maximum value that the order can be searched for within - the broker will choose the cheapest offer from
        whichever seller stocks it the cheapest
         */
        System.out.println("\n*********************");
        System.out.println("*** Create Orders ***");
        System.out.println("*********************\n");

        buyer4.createOrder("Speakers", 29.99, 49.99);
        buyer1.createOrder("Apple", .19, .25);
        buyer2.createOrder("Orange", 0.14, 0.25);
        buyer2.createOrder("Vacuum Cleaner", 69.99, 79.98);
        buyer3.createOrder("Grapes", 0.99, 1.50);
        buyer3.createOrder("Pans", 14.99, 35.99);
        buyer4.createOrder("Grapes", 1.29, 1.69);

        /*
        This line implements all of the logic of the Agents and the Buying and Selling process - it is all contained within
        the various classes and will be explained in full there, but as a general overview, the Broker will check the
        orders and will check if they are stocked and if they are finds the cheapest. Offers are then made to the buyers
        who will either accept if the price is right or reject if not. The process returns an integer which is either -1
        if there are no orders to fulfill or the number of unfulfilled orders. Fulfilled orders will have been deleted
        from the ordersList
         */
        System.out.println("\n*************************");
        System.out.println("*** Processing Orders ***");
        System.out.println("*************************\n");
        int processedOrders = broker1.processOrder();

        /*
        Finally any rejected offers are listed and display the reason why they have been rejected. They
        stay in the list so that if stock gets updated in a further iteration or if prices change then sales could
        happen. Only successful sales get deleted fom the orders list.
         */
        System.out.println("\n*******************************");
        System.out.println("*** Unfilled or zero Orders ***");
        System.out.println("*******************************\n");
        unfulfilledOffers(processedOrders);

        /*
         Iteration 2 - while it is recognised that this would be done in some sort of iterative process, this is
         simply to demonstrate that the unfulfilled orders can get placed as stock and prices change and the
         rejectedOffers list is cleared every time
        */
        System.out.println("\n*******************");
        System.out.println("*** Iteration 2 ***");
        System.out.println("*******************\n");

        System.out.println("\n****************************");
        System.out.println("*** Seller Creates Stock ***");
        System.out.println("****************************\n");
        tesco.addStock("Orange", 0.24);

        System.out.println("\n*********************");
        System.out.println("*** Create Seller ***");
        System.out.println("*********************\n");

        Seller aldi = new Seller("Aldi");

        System.out.println("\n****************************");
        System.out.println("*** Seller Creates Stock ***");
        System.out.println("****************************\n");

        aldi.addStock("Speakers", 39.99);

        System.out.println("\n*************************");
        System.out.println("*** Processing Orders ***");
        System.out.println("*************************\n");
        processedOrders = broker1.processOrder();

        System.out.println("\n*******************************");
        System.out.println("*** Unfilled or zero Orders ***");
        System.out.println("*******************************\n");
        unfulfilledOffers(processedOrders);
        /*
         Iteration three proves that even though rejectedOffers gets cleared every time, any remaining unfulfilled
         offers get added back in each time
        */
        System.out.println("\n*******************");
        System.out.println("*** Iteration 3 ***");
        System.out.println("*******************\n");

        sainsburys.addStock("Vacuum Cleaner", 69.99);

        System.out.println("\n*************************");
        System.out.println("*** Processing Orders ***");
        System.out.println("*************************\n");
        processedOrders = broker1.processOrder();

        System.out.println("\n*******************************");
        System.out.println("*** Unfilled or zero Orders ***");
        System.out.println("*******************************\n");
        unfulfilledOffers(processedOrders);

        // Demonstrates how the code handles all orders being fulfilled
        System.out.println("\n*******************");
        System.out.println("*** Iteration 4 ***");
        System.out.println("*******************\n");

        System.out.println("\n*************************");
        System.out.println("*** Processing Orders ***");
        System.out.println("*************************\n");
        processedOrders = broker1.processOrder();

        System.out.println("\n*******************************");
        System.out.println("*** Unfilled or zero Orders ***");
        System.out.println("*******************************\n");
        unfulfilledOffers(processedOrders);
    }
    /*
     This was simply to get rid of duplicate code withing main and create a method for outputting the results of
     processing orders and offers. If Main ran as an iterative process this would be unnecessary. As the BrokerAgent
     handles fulfilled offers this basically either handles no offers or unfulfilled offers
    */
    private static void unfulfilledOffers(int processedOrders) {
        // If -1 is returned it means there are no orders to process
        if (processedOrders <= 0) {
            System.out.println("No Orders to Process");
        } else {
            // Output the unfulfilled orders to the console
            System.out.println("\nUnfulfilled Orders = " + processedOrders);
            for (int i = 0; i < BrokerAgent.rejectedOffers.size(); i += 3) {
                System.out.println("Order is as follows: " + BrokerAgent.rejectedOffers.get(i) + " tried to buy " + BrokerAgent.rejectedOffers.get(i + 1) + " but couldn't for the following reason: " + BrokerAgent.rejectedOffers.get(i + 2));
            }
            System.out.println();
            /*
             clear the rejectedOffers list so that it unfulfilled offers that have now been fulfilled do not remain.
             We do not need to worry about orders that remain unfulfilled as they will be added back into the list
             next time the Broker Agent logic is called
            */
            BrokerAgent.rejectedOffers.clear();
        }
    }
}
