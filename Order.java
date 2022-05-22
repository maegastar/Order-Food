
package scproject1;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Maega
 */
//This class models an order
public class Order implements Serializable{

    //name of restaurant
    private String rest;
    //name of customer
    private String cust;
    //items in the order
    private ArrayList<String> items = new ArrayList<>();
    //whether the order is complete or not
    private Boolean complete;
    //the price of the order
    private double total;
    //the number of the order in relation to the customer
    int num;

    //constructor for the order
    public Order(Restaurant rest, Customer cust) {
        this.rest = rest.getName();
        this.cust = cust.getUserName();
        this.num = cust.getCount();
        complete = false;
        total = 0;
    }

    public void add(String item) {
            items.add(item);
    }

    public void completed() {
        complete = true;
    }
    
    public void NotComplete() {
        complete = false;
    }
    
    public void addPrice(double price){
        this.total += price;
    }
    
    public String getCust(){
        return this.cust;
    }
    
    public boolean getStatus(){
        return this.complete;
    }
    
    public int getCount(){
        return this.num;
    }
    
    @Override
    public String toString(){
        return items + "\n Complete: " + complete +"\n Total: $"+this.total;
    }
    

}
