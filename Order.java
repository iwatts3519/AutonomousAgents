package keele.learnprogramming;

// Anything already explained in the Seller Class will not be explained here

//imported so that I can use an ArrayList, which does not have to of a fixed size
import java.util.ArrayList;

public class Order {
    /*
     This creates a list of type Order (This object) which will contain orders. It is public because it needs to
     be accessed outside of the class. I could have made it private and created a method to access it, which would
     possibly have been more correct, and more necessary when more than one developer is working on a piece of code.
     However, in this case there is no need to not declare the ordersList as public
    */
    public static ArrayList<Order> ordersList = new ArrayList<>();

    /*
     While I have declared variables as private before, this is the first time they have needed to be used outside of
     the Class - this means code outside of the class cannot access these variables, but has to use the get and set
     methods below. In this case it equates to the same thing, but the advantage of using getters and setters is that
     validation can be included, rather than allowing anyone to set the value to anything they like
    */
    private final String buyer;
    private final String product;
    private final Double minValue;
    private final Double maxValue;

    //Constructor
    public Order(String buyer, String product, Double minValue, Double maxValue) {
        this.buyer = buyer;
        this.product = product;
        this.minValue = minValue;
        this.maxValue = maxValue;
        /*
         Adds the current object (not the individual variables) to the ArrayList ordersList. 'this' is a keyword
         reference to the current object being created
        */
        ordersList.add(this);
        System.out.println(this.buyer + " has placed an order for item named " + this.product + " for between £" + this.minValue + " and £" + this.maxValue + "\n");
    }

    // Getters and Setters used by other Classes to access details about each Order
    public String getBuyer() {
        return this.buyer;
    }


    public String getProduct() {
        return this.product;
    }


    public Double getMinValue() {
        return this.minValue;
    }


    public Double getMaxValue() {
        return this.maxValue;
    }


}