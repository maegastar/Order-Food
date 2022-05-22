/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scproject1;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author Maega
 */

//This is an abstract class for a user
//its subclasses are a staff user or customer user
public abstract class User implements Serializable{
    private String userName;
    private String passWord;
    private int phone;
    
    public User(String name){
        this.userName = name;
        this.setPassWord();
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    //checks if input matches the password
    public boolean checkPass(String input){
        return passWord.equals(input);
    }

    //sets the password for user within class to stay encapsulated
    public void setPassWord() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your password: ");
        this.passWord = scan.nextLine();
    }

    /**
     * @return the phone
     */
    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString(){
        return userName;
    }
    
}
