import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 1. the list
        String list = scanner.nextLine();

        // 2. order
        String order = scanner.nextLine();

        // 3. check which order

        // add to the end
        if (order.equals("add")){
            String element = scanner.nextLine();
            
            // put the elements to the list (single linked list) [use regex]

        }

        // add to the index
        else if (order.equals("addToIndex")){}

        // get element in specific index
        else if (order.equals("get")){}

        // set element in specific index
        else if (order.equals("set")){}

        // clear the list
        else if (order.equals("clear")){}

        // check if the list is empty
        else if (order.equals("isEmpty")){}

        // remove element in specific index
        else if (order.equals("remove")){}

        // get the size of the list
        else if (order.equals("size")){}

        // get sublist
        else if (order.equals("sublist")){}

        // check if the list contains a specific element
        else if (order.equals("contains")){}
        
        scanner.close();

    }
}