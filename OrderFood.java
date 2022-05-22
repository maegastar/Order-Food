/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scproject2;


import java.util.Scanner;

/**
 *
 * @author Maega
 */

//this class is the starting point for the program
public class OrderFood{
    

    public static void main(String[] args) {
        //creates a scanner for user input
        Scanner scan = new Scanner(System.in);
        //creates the system functions to used
        SystemFunction sys = new SystemFunction();
        //welcome user
        System.out.println("Welcome to OrderFood");
        //creates and opens the login UI
        LoginUI login = new LoginUI();
        login.loginUI(scan, sys);
        
    }
}
