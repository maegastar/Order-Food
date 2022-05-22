/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scproject1;

import java.io.Serializable;

/**
 *
 * @author Maega
 */
//this class models a customer and its information and extends the user class
public class Customer extends User implements Serializable {

    //the number of orders customer has
    private int count;
    //the current order for customer
    private Order order;

    //constructor
    public Customer(String name) {
        super(name);
        count = 0;
    }

    public void viewOrder() {
        System.out.println(order);
    }

    //if customer currently has an order waiting
    public boolean hasOrder() {
        if (order == null) {
            return false;
        } else {
            return true;
        }
    }

    //sets an order to the customer
    public void setOrder(Order order) {
        this.order = order;
    }

    public void countUp() {
        count++;
    }

    //returns completion status of order
    public boolean orderStatus() {
        return this.order.getStatus();
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return ("Customer: " + super.getUserName() + "\nPhone: " + super.getPhone());
    }
}
