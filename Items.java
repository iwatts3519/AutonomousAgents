package keele.learnprogramming;

// Anything already explained in the Seller Class will not be explained here

//imported so that I can use an ArrayList, which does not have to of a fixed size
import java.util.ArrayList;

public class Items {
    /*
     This creates a list of type Items (This object) which will contain items (products). It is public because it
     needs to be accessed outside of the class. I could have made it private and created a method to access it,
     which would possibly have been more correct, and more necessary when more than one developer is working
     on a piece of code. However, in this case there is no need to not declare the itemsList as public
    */
    public static ArrayList<Items> itemsList = new ArrayList<>();
    private final String seller;
    private final String item;
    private final Double price;

    //Constructor - very similar to Constructor in Order Class
    public Items(String seller, String item, Double price) {
        this.seller = seller;
        this.item = item;
        this.price = price;
        itemsList.add(this);
        System.out.println("Item Created by " + seller + ". Item is " + item + ", and it costs £" + price + "\n");
    }

    // Getters and Setters
    public String getSeller() {
        return this.seller;
    }


    public String getItem() {
        return this.item;
    }


    public Double getPrice() {
        return this.price;
    }

}
