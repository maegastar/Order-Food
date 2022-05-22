/*
 */
package scproject2;

import java.util.Scanner;

/**
 *
 * @author Maega
 */
//this class contains functions that can be used by the main ui
public class SystemFunction {

    //this function checks user input if its a number or not
    //if it is the number is returned
    //if user enters an x it returns -1
    //otherwise it asks user for correct input
    public int checkNum(String input) {
        boolean check = false;
        int num;
        while (check == false) {
            if (input.equalsIgnoreCase("x")) {
                return -1;
            } else if (!(input.matches("[0-9]+"))) {
                input = getNewInput();
                System.out.println("You must enter a number");
            } else {
                check = true;
            }
        }
        num = Integer.parseInt(input);
        return num;
    }

    //this function does the same as above but for doubles
    public double checkDouble(String input) {
        boolean check = false;
        Double num = 0.0;
        while (check == false) {
            if (input.equalsIgnoreCase("x")) {
                return -1.0;
            } else {
                try {
                    num = Double.parseDouble(input);
                    check = true;
                } catch (NumberFormatException e) {
                    input = getNewInput();
                    System.out.println("Number should be of 0.0 format");
                }
            }
        }
        return num;
    }

    //this function asks the user for another input if called
    //returns the new input
    public String getNewInput() {
        Scanner scan = new Scanner(System.in);
        String input = "";
        System.out.println("Please enter a valid option");
        input = scan.nextLine();
        return input;
    }

    //this function checks if input is x and returns null
    //otherwise it returns the string
    public String checkInput(String input) {
        if (input.equalsIgnoreCase("x")) {
            return null;
        } else {
            return input;
        }
    }

}
