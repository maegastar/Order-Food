/*
 */
package scproject2;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Maega
 */
//this class is the UI for a staff user
public class StaffUI extends AbstractFile {

    Staff staff;
    Scanner scan;
    SystemFunction sys;
    HashMap<String, Restaurant> stores;
    Restaurant rest;
    HashMap<String, Order> orders;
    Order order;

    //constructor
    public StaffUI(Staff staff, Scanner scan, SystemFunction sys, HashMap<String, Restaurant> stores, HashMap<String, Order> orders) {
        this.staff = staff;
        this.scan = scan;
        this.sys = sys;
        this.stores = stores;
        this.orders = orders;
    }

    //this function is the order options ui
    public void orderOptions() {
        //prints current orders
        System.out.println("Current orders:");
        if (rest.orders.isEmpty()) {
            System.out.println("No orders at the moment");
        } else {
            for (int i = 1; i < rest.orders.size(); i++) {
                System.out.println(i + ". " + rest.orders.get(i));
            }
        }
        //displays options
        System.out.println("1. Mark an order as complete");
        System.out.println("2.Back");
        String input = "";
        int option = 0;
        input = scan.nextLine();
        option = sys.checkNum(input);
        if (option == 1) {
            System.out.println("Enter the number of the order to be completed");
            input = scan.nextLine();
            option = sys.checkNum(input);
            if (option == -1) {
                //do nothing to exit as user entered 'x'
            } else if (option <= rest.orders.size()) {
                //mark order as complete
                order = rest.orderComplete(option);
                //add order to orders list to be saved to file
                orders.put(order.getCount() + " " + order.getCust(), order);
            } else {
                System.out.println("Please enter the correct number");
            }
        }
    }

    //this function is the menu options ui
    public void menuOptions() {
        String input = "";
        int option = 0;
        //display the menu
        if (rest.menu.isEmpty()) {
            System.out.println("Menu is empty");
        } else {
            for (String s : rest.menu.keySet()) {
                System.out.println("$." + rest.menu.get(s) + " " + s);
            }
        }
        //display menu options
        System.out.println("1.Add to the menu");
        System.out.println("2.Remove from menu");
        System.out.println("3.Back");
        input = scan.nextLine();
        option = sys.checkNum(input);
        if (option == 1) {
            //adds item to menu
            double price;
            String value;
            System.out.println("Enter the name of the order");
            input = scan.nextLine();
            System.out.println("Enter the price");
            value = scan.nextLine();
            price = sys.checkDouble(value);
            if (price == -1.0) {
                //do nothing to exit
            } else {
                rest.addToMenu(input, price);
            }
        } else if (option == 2) {
            //removes item from menu
            System.out.println("Enter the name of the order");
            input = scan.nextLine();
            rest.removeFromMenu(input);
        }
    }

    //this function creates a new restaurant
    public void newStore() {
        String input;
        int num;
        System.out.println("Please enter the name of your store");
        Restaurant rest = new Restaurant(scan.nextLine());
        staff.setRestaurant(rest);
        System.out.println("Please enter the stores address");
        rest.setAddress(scan.nextLine());
        System.out.println("Please enter the stores phone number");
        input = scan.nextLine();
        num = sys.checkNum(input);
        rest.setPhone(num);
        //add restaurant to list of restaurants to be saved to file
        stores.put(rest.getName(), rest);
        //save the list to file
        this.Save("stores.txt", stores);
        System.out.println("You're store has been added to the system !");
        System.out.println("Please note it is closed! You need to open the store to recieve orders");
    }

    public Staff SUI() {
        String input = "";
        int option = 0;
        boolean run = true;
        //sets the restaurant for this staff in the ui
        this.rest = stores.get(staff.getRestaurant().getName());
        while (run == true) {
            System.out.println("Enter the number of the option you choose:");
            System.out.println("1.View Current orders");
            System.out.println("2.View Menu");
            System.out.println("3.Open/close store");
            System.out.println("4.Log out");

            input = scan.nextLine();
            option = sys.checkNum(input);

            switch (option) {
                case 1:
                    //opens order options ui
                    orderOptions();
                    break;
                case 2:
                    //opens menu options ui
                    menuOptions();
                    break;
                case 3:
                    System.out.println("1.Open store");
                    System.out.println("2.Close store");
                    input = scan.nextLine();
                    option = sys.checkNum(input);
                    switch (option) {
                        case 1:
                            //opens store
                            rest.openStore();
                            System.out.println("Store is open!");
                            break;
                        case 2:
                            //closes store
                            rest.closeStore();
                            System.out.println("Store is closed!");
                            break;
                        case -1:
                            break;
                    }
                    break;
                //if user chooses option 4 to exit or enters x to exit
                case 4:
                case -1:
                    //saves the list of orders to file
                    this.Save("orders.txt", orders);
                    //saves the list of stores to file
                    this.Save("stores.txt", stores);
                    run = false;
                    System.out.println("Logging out...");
                    break;
            }
        }
        //returns this staff member to the login Ui
        return staff;
    }
}
