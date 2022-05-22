/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scproject1;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Maega
 */

//this class is the login UI for users
public class LoginUI extends AbstractFile {

    HashMap<String, Restaurant> stores;
    HashMap<String, User> users;
    HashMap<String, Order> orders;
    Customer cust = null;
    Staff staff = null;
    User usr;

    //this function loads the past orders from file to the hashmap orders
    public void loadOrders() {
        this.orders = (HashMap<String, Order>) this.Load("orders.txt");
        if (orders == null) {
            this.orders = new HashMap<String, Order>();
        }
    }

    //this function loads the users from file to the hashmap users
    public void loadUsers() {
        this.users = (HashMap<String, User>) this.Load("users.txt");
        if (users == null) {
            this.users = new HashMap<String, User>();
        }
    }

    //this function loads the stores from file to the hashmap stores
    public void loadStores() {
        stores = (HashMap<String, Restaurant>) this.Load("stores.txt");
        if (stores == null) {
            this.stores = new HashMap<String, Restaurant>();
        }
    }

    //this function logins a user, returns 1 if user is customer
    //returns 2 if user is staff
    //returns 0 if login failed
    public int login(String user, String pass) {
        if (user.isEmpty()) {
            System.out.println("There are no users in the system");
            System.out.println("Please create a new login");
            return 0;
        } else {
            //if user is in list
            if (users.containsKey(user)) {
                usr = users.get(user);
                //check password
                if (usr.checkPass(pass)) {
                    //check whether user is customer or staff
                    if (usr instanceof Customer) {
                        this.cust = (Customer) usr;
                        return 1;
                    } else {
                        this.staff = (Staff) usr;
                        return 2;
                    }
                } else {
                    System.out.println("Incorrect password");
                    return 0;
                }
            } else {
                System.out.println("Incorrect username or user does not exist");
                return 0;
            }
        }
    }

    //this function is the main UI 
    public void loginUI(Scanner scan, SystemFunction sys) {

        String input = "";
        int option = 0;
        boolean run = true;
        int isCust = 0;
        this.loadUsers();
        while (run == true) {
            loadOrders();
            loadStores();
            System.out.println("Enter the number of the option you choose:");
            System.out.println("(Enter 'x' to exit program)");
            System.out.println("1. Login");
            System.out.println("2. Create new Login");
            System.out.println("3. Exit");

            input = scan.nextLine();
            option = sys.checkNum(input);

            switch (option) {
                case 1:
                    //attempts to login user
                    System.out.println("Please enter your username: ");
                    input = scan.nextLine();
                    System.out.println("Please enter your password: ");
                    String pass = scan.nextLine();
                    isCust = login(input, pass);
                    switch (isCust) {
                        //is user is customer, opens customer ui
                        case 1:
                            CustomerUi cui = new CustomerUi(cust, scan, sys, stores, orders);
                            cust = cui.CUI();
                            //updates user in list
                            users.remove(cust.getUserName());
                            users.put(cust.getUserName(), cust);
                            break;
                        //if user is staff opens staff Ui
                        case 2:
                            StaffUI sui = new StaffUI(staff, scan, sys, stores, orders);
                            staff = sui.SUI();
                            //updates user in list
                            users.remove(staff.getUserName());
                            users.put(staff.getUserName(), staff);
                            break;
                        //if login fails ask user to try again
                        default:
                            System.out.println("Please try again or create new user");
                            break;
                    }
                    break;

                case 2:
                    //creates new user
                    System.out.println("Are you :");
                    System.out.println("1. Customer");
                    System.out.println("2. Staff");
                    input = scan.nextLine();
                    option = sys.checkNum(input);

                    newUser:
                    switch (option){
                        //creates new customer
                        case 1:
                            System.out.println("Please enter username:");
                            cust = new Customer(scan.nextLine());
                            staff = null;
                            System.out.println("User has been succesfully created!");
                            //opens customer ui
                            CustomerUi cui = new CustomerUi(cust, scan, sys, stores, orders);
                            //adds user to list
                            users.put(cust.getUserName(), cust);
                            cust = cui.CUI();
                            //updates user in list of users
                            users.remove(cust.getUserName());
                            users.put(cust.getUserName(), cust);
                            break;
                        //creates new staff
                        case 2:
                            System.out.println("Please enter username:");
                            staff = new Staff(scan.nextLine());
                            cust = null;
                            System.out.println("User has been succesfully created!");
                            StaffUI sui = new StaffUI(staff, scan, sys, stores, orders);
                            //adds user to list
                            users.put(staff.getUserName(), staff);
                            boolean done = false;

                            while (done == false) {
                                //setting the restaurant for the staff user
                                System.out.println("Is your store:");
                                System.out.println("1.In system already");
                                System.out.println("2.New");
                                input = scan.nextLine();
                                option = sys.checkNum(input);
                                switch (option) {
                                    case 1:
                                        //checks if store is in list of stores
                                        System.out.println("Please enter the name of your store");
                                        input = scan.nextLine();
                                        //sets staff users store 
                                        if (stores.containsKey(input)) {
                                            done = true;
                                            staff.setRestaurant(stores.get(input));
                                        //asks user to try again or create store
                                        } else {
                                            System.out.println("Sorry this store is not in the system or you have entered incorrectly");
                                            System.out.println("Please try again");
                                        }
                                        break;
                                    case 2:
                                        //creates new store
                                        done = true;
                                        sui.newStore();
                                        break;
                                    case -1:
                                        //exits system
                                        System.out.println("Exiting...");
                                        //removes staff from list as exited
                                        users.remove(staff.getUserName());
                                        //saves users to file
                                        this.Save("users.txt", users);
                                        run = false;
                                        cust = null;
                                        staff = null;
                                        break newUser;
                                }
                            }
                            //opens staff UI
                            staff = sui.SUI();
                            //updates staff in list
                            users.remove(staff.getUserName());
                            users.put(staff.getUserName(), staff);
                            break;
                        case -1:
                            //exits
                            System.out.println("Exiting...");
                            //saves users to file
                            this.Save("users.txt", users);
                            run = false;
                            cust = null;
                            staff = null;
                            break;
                    }

                    break;
                case 3:
                case -1:
                    //exits
                    System.out.println("Exiting...");
                    //saves user to file
                    this.Save("users.txt", users);
                    run = false;
                    cust = null;
                    staff = null;
                    break;

            }
        }
    }

}
