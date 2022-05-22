/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scproject2;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Maega
 */

//this class is the UI for a customer user
//it extends AbstractFile which contains the read and save to file functions
public class CustomerUi extends AbstractFile {

    Customer cust;
    Scanner scan;
    SystemFunction sys;
    HashMap<String, Restaurant> stores;
    HashMap<String, Order> orders;
    Restaurant rest;

    //constructor
    public CustomerUi(Customer cust, Scanner scan, SystemFunction sys, HashMap<String, Restaurant> stores, HashMap<String, Order> orders) {
        this.cust = cust;
        this.scan = scan;
        this.sys = sys;
        this.stores = stores;
        this.orders = orders;
    }

    //this function creates a new order for the customer
    public void createOrder() {
        Order order = new Order(rest, cust);
        String input = "";
        String choice;
        int option = 0;
        boolean run = true;
        boolean done = false;

        while (run == true) {

            //prints menu
            System.out.println("Menu:");
            if (rest.menu.isEmpty()) {
                System.out.println("Sorry this store currently does not have a menu");
            } else {
                for (String i : rest.menu.keySet()) {
                    System.out.println("$:" + rest.menu.get(i) + " " + i);
                }
            }
            //lets user type name of item to be added to order
            System.out.println("Enter the name of the item you want to add:");
            System.out.println("Enter 0 when you are finished");
            //when 0 is entered user cannot add any more items
            while (done == false) {
                input = scan.nextLine();
                choice = sys.checkInput(input);
                //if user enters 0 to finish or x it exits the order creator
                if ((choice == null) | (choice.equals("0"))) {
                    done = true;
                    break;
                } else if (rest.menu.containsKey(choice)) {
                    order.add(choice);
                    order.addPrice(rest.menu.get(choice));
                    System.out.println((choice) + " Added");
                } else {
                    System.out.println("Sorry this item is unavailable");
                }
            }
            //displays the order
            System.out.println("Your Order:");
            System.out.println(order);
            System.out.println("1.Submit Order");
            System.out.println("2.Try again");
            System.out.println("3.Cancel");
            input = scan.nextLine();
            option = sys.checkNum(input);
            switch (option) {
                //sets order and sends to restaurant
                case 1:
                    cust.setOrder(order);
                    cust.countUp();
                    rest.addOrder(order);
                    System.out.println("Order submitted!");
                    run = false;
                    //updates the store in list
                    stores.remove(rest.getName());
                    stores.put(rest.getName(), rest);
                case 2:
                    break;
                case 3:
                case -1:
                    run = false;
                    break;
            }
        }

    }

    //this function loads the current orders that belong to this customer from file
    public void loadOrders() {
        orders = (HashMap<String, Order>) this.Load("orders.txt");
        if (orders == null) {
            this.orders = new HashMap<String, Order>();
        }
        if (!(orders.isEmpty())) {
            for (String i : orders.keySet()) {
                //removes orders that belong to other users
                if (!(i.contains(cust.getUserName()))) {
                    orders.remove(i);
                }
            }
            int num = cust.getCount() - 1;
            String value = String.valueOf(num);
            //if the current order is in the past orders list
            //order has been completed thus can be removed from customers current order
            for (String i : orders.keySet()) {
                if (i.contains(value + " ")) {
                    cust.setOrder(null);
                }
            }
        }
    }

    //this function prints the past orders of customer
    public void printOrders() {
        if (!(orders == null)) {
            if (!(orders.isEmpty())) {
                for (String i : orders.keySet()) {
                    if (i.contains(cust.getUserName())) {
                        System.out.println(orders.get(i));
                    }
                }
            } else {
                System.out.println("No orders yet");
            }
        } else {
            System.out.println("No orders yet");
        }
    }

    public Customer CUI() {
        String input = "";
        String choice;
        int option = 0;
        boolean run = true;
        loadOrders();
        while (run == true) {
            System.out.println("Enter the number of the option you choose:");
            System.out.println("1.Place an order");
            System.out.println("2.View current orders");
            System.out.println("3.View past orders");
            System.out.println("4.Log out");

            input = scan.nextLine();
            option = sys.checkNum(input);
            switch (option) {
                case 1:
                    if (cust.hasOrder()) {
                        System.out.println("Sorry you already have an order being made");
                        System.out.println("You can only have 1 order at a time");
                        System.out.println("Please try again after your order is complete");
                    } else {
                        boolean open = false;
                        //display stores from list of stores which are open
                        System.out.println("Restaurants available: ");
                        if (!(stores.isEmpty())) {
                            for (Restaurant i : stores.values()) {
                                if (i.open) {
                                    open = true;
                                    System.out.println(i.toString());
                                }
                            }
                            if (open == true) {
                                System.out.println("Enter the store you choose:");
                                input = scan.nextLine();
                                choice = sys.checkInput(input);
                                //if user enters 'x' to exit
                                if (choice == null) {
                                    cust = null;
                                    System.out.println("Loggin out...");
                                    run = false;
                                    break;
                                } else if (stores.containsKey(choice)) {
                                    this.rest = stores.get(choice);
                                    if (rest.open) {
                                        createOrder();
                                    }
                                } else {
                                    System.out.println("You entered the store incorrectly please try again");
                                }
                            } else {
                                System.out.println("No restaurants available");
                            }
                        } else {
                            System.out.println("No restaurants available");
                        }
                    }

                    break;
                case 2:
                    System.out.println("Current Order: ");
                    cust.viewOrder();
                    break;
                case 3:
                    printOrders();
                    break;
                case 4:
                case -1:
                    //saves the list of stores to file
                    this.Save("stores.txt", stores);
                    System.out.println("Loggin out...");
                    run = false;
                    break;
            }
        }
        return cust;
    }

}
