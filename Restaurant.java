/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scproject2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Maega
 */
//this class models a restaurant
public class Restaurant implements Serializable {

    //if store is open to take orders
    protected boolean open;
    private String name;
    private String address;
    private int phone;
    //the menu for the store
    protected HashMap<String, Double> menu = new HashMap<>();
    //the current orders the store needs to make
    protected ArrayList<Order> orders = new ArrayList<>();

    //constructor
    public Restaurant(String name) {
        this.name = name;
        open = false;
    }

    public void openStore() {
        open = true;
    }

    public void closeStore() {
        open = false;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void viewOrders() {
        System.out.println(orders);
    }

    //marking an order as complete
    public Order orderComplete(int num) {
        if ((num - 1) <= orders.size()) {
            //mark order as complete
            orders.get(num - 1).completed();
            Order order = orders.get(num - 1);
            //remove order from list of orders for restaurant
            orders.remove(num - 1);
            System.out.println("Order completed!");
            return order;
        } else {
            System.out.println("Wrong number entered please try again");
            return null;
        }
    }

    public void addToMenu(String name, double price) {
        menu.put(name, price);
        System.out.println("Item added.");
    }

    public void removeFromMenu(String name) {
        if (menu.containsKey(name)) {
            menu.remove(name);
            System.out.println("Item has been removed.");
        } else {
            System.out.println("Sorry this item is not in the menu.");
        }
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public int getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name + "\nAddress: " + getAddress();
    }
}
