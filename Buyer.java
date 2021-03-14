package keele.learnprogramming;

// Anything already explained in the Seller Class will not be explained here

public class Buyer {


    private final String buyerName;

    //Constructor
    public Buyer(String buyerName) {
        this.buyerName = buyerName;
        System.out.println("Buyer Created called " + this.buyerName);

    }
    // Works the same as the addStock method in Sellers
    public void createOrder(String item, Double minValue, Double maxValue) {
        new Order(this.buyerName, item, minValue, maxValue);
    }
    /*
     This method returns a boolean value for whether the buyer accepts the offer or not. It is extremely simple as
     it simply checks whether the sellers price is within the min and max values that the buyer had specified
    */
    public static boolean processOffers(String buyer, Double price, Double min, Double max) {
        /*
         This if statement uses the && to mean and, so we are saying if the sellers price is between the min
         and max values specified then print that the buyer has accepted the offer (used for debugging and
         to make the output more readable)
        */
        if (price <= max && price >= min) {
            System.out.println(buyer + " accepted the offer.\n");
            return true;
        }
        //If the if statement fails then return false
        return false;
    }
}

