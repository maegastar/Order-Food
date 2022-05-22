/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scproject2;

import java.io.Serializable;

/**
 *
 * @author Maega
 */
//this class models a staff user and extends the user class
public class Staff extends User implements Serializable {

    //the restaurant the staff user belongs to
    private Restaurant restaurant;

    //constructor
    public Staff(String name) {
        super(name);
    }

    /**
     * @return the restaurant
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * @param restaurant
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return ("Staff: " + super.getUserName() + "\nRestaurant: " + restaurant + "\nPhone: " + super.getPhone());
    }

}
