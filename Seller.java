package keele.learnprogramming;

public class Seller {

    /*
     Initialise the variable name that will be used to hold the name of the seller. As it is only created during
     the constructor it is declared as Final. It is private as it is only used within this Class in the printStock
      and the addStock method
    */
    private final String name;

    // Constructor - called every time a new Seller is created
    public Seller(String name) {
        //The 'this' keyword is referring the the current object being created
        this.name = name;

        //This line is optional but it is nice to get feedback that the Seller has been created
        System.out.println("Seller Created called " + this.name);
    }

    /*
     This method creates a new object of the items class by calling the constructor of the Items Class which embeds
     the name of the seller, the item and the price of the item
    */
    public void addStock(String item, Double price) {
        new Items(this.name, item, price);

    }
    /*
     This method prints out a list of stock for whichever seller calls it. If it is called by Sainsburys then
     it will only print out Sainsburys' stock etc
    */
    public void printStock() {
        /*
         This is an extended For loop in Java and works more like a Python For loop in that it will iterate over
         an iterator (which could be a list of objects as in this case) as opposed to the normal for loop which
         iterates over an integer which is incremented each time
        */
        for (Items obj : Items.itemsList) {
            /*
             This line tests whether the current object in the itemsList seller property is equal to the current
             object which is calling the method - strings in java are tested for equality using .equals() as opposed
             to == because a string is an object in Java. The if statement only runs the code if the condition#
             in the brackets is met.
            */
            if (obj.getSeller().equals(this.name)) {
                System.out.println(obj.getSeller() + " " + obj.getItem() + " " + obj.getPrice());
            }

        }
    }

}