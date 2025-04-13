import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

    // function to print the results in SingleLinkedList : Task #1
    public static void printList(SingleLinkedList list) {
            // print the list
            SingleLinkedList.SllNode current = list.head.next;
    
            System.out.print("[");
            while (current != list.tail)
            {
                // print the element
                System.out.print(current.element);
    
                // check if it is the last element
                if (current.next != list.tail)
                {
                    System.out.print(", ");
                }
    
                // move to the next element
                current = current.next;
            }
            System.out.print("]");
            
    }
  
    // function to transform 1D array to integers 1D array Task #3
    public static int[] readArray(String inputString){

        // remove the brackets
        inputString = inputString.substring(1, inputString.length() - 1);

        // split with commas
        String[] values = inputString.split(",");

        // get all the values and put them in final
        int[] result = new int[values.length];

        int i = 0;
        for (String value : values) {
            
            // remove spaces
            value = value.trim();

            // check if the value is empty
            if (!value.isEmpty()){
                // put the values in final
                result[i] = Integer.parseInt(value);
            }
            i++;
        }

        return result;
        
    }
    
    public static void main(String[] args) {

        // 1 - SingleLinkedList part
        /* try (Scanner scanner = new Scanner(System.in)) {

            // 1. the inputList
            String inputList = scanner.nextLine().trim();

            // remove the brackets
            inputList = inputList.substring(1, inputList.length() - 1);

            // split each one with comma
            String[] values = inputList.split(",");

            // make the list made of the values SingleLinkedList
            SingleLinkedList list = new SingleLinkedList();
            for (String value : values) {
                // remove the spaces
                value = value.trim();
                // check if the value is empty
                if (!value.isEmpty()) {
                    // add the value to the list
                    list.add(Integer.parseInt(value));
                }
            }


            // 2. order
            String order = scanner.nextLine().trim();

            // 3. check which order

            // add to the end
            if (order.equals("add")){

                // get first element
                int firstElement = scanner.nextInt();

                // add to the end
                list.add(firstElement);

                // print the list in the form like the input
                printList(list);

            }

            // add to the index
            else if (order.equals("addToIndex")){
                // get the index
                int index = scanner.nextInt();

                // get the element
                int element = scanner.nextInt();

                // add to the index
                list.add(index, element);

                // print the list in the form like the input
                printList(list);

            }

            // get element in specific index
            else if (order.equals("get")){
                // get the index
                int index = scanner.nextInt();

                // get the element
                Object element = list.get(index);

                // print the element
                System.out.println(element);
            }

            // set element in specific index
            else if (order.equals("set")){

                // get the index
                int index = scanner.nextInt();

                // get the element
                int element = scanner.nextInt();

                // set the element
                list.set(index, element);

                // print the list in the form like the input
                printList(list);

            }

            // clear the inputList
            else if (order.equals("clear")){

                // clear the inputList
                list.clear();

                printList(list);

            }

            // check if the inputList is empty
            else if (order.equals("isEmpty")){

                // check if the inputList is empty
                boolean isEmpty = list.isEmpty();

                // print the result
                if (isEmpty)
                {
                    System.out.println("True");
                }
                else
                {
                    System.out.println("False");
                }

            }

            // remove element in specific index
            else if (order.equals("remove")){

                // get the index
                int index = scanner.nextInt();

                // remove the element
                list.remove(index);

                // print the list in the form like the input
                printList(list);

            }

            // get the size of the inputList
            else if (order.equals("size")){

                // get the size
                int size = list.size();

                // print the size
                System.out.println(size);

            }

            // get subList
            else if (order.equals("sublist")){

                // get the fromIndex
                int fromIndex = scanner.nextInt();

                // get the toIndex
                int toIndex = scanner.nextInt();

                // get the subinputList
                ILinkedList sublist = list.sublist(fromIndex, toIndex);

                // print the subinputList in the form like the input
                System.out.print("[");
                for (int i = 0; i < sublist.size(); i++)
                {
                    // get the element
                    Object val = sublist.get(i);

                    // print the element
                    System.out.print(val);

                    // check if it is the last element
                    if (i != sublist.size() - 1)
                    {
                        System.out.print(", ");
                    }
                }
                System.out.print("]");

            }

            // check if the inputList contains a specific element
            else if (order.equals("contains")){
                // get the element
                int element = scanner.nextInt();

                // print the result
                if (list.contains(element))
                {
                    System.out.println("True");
                }
                else
                {
                    System.out.println("False");
                }
            }

        }

        catch (Exception e) {
            System.out.println("Error");
        } */

        // 2 - DoubleLinkedList part


        // 3 - PolynomialSolver part

        /* 
         * Available Polynomial Variables : A, B, C
         * R : accumulator
         * Polynomial terms in descending order depending on its exponents
         * A Polynomial can have negative exponent
         */

        /*try (Scanner scanner = new Scanner(System.in)){

            PolynomialSolver polynomialSolver = new PolynomialSolver();

            // 1. input the polynomial

            // first thing the order
            String order = scanner.nextLine().trim();
            
            *//*
            the app should run until we get => add, clear, multiply, or subtract

            if it is print or set it should continue
            *//*
            int[] polysCheck = new int[3];
            for (int i = 0; i < 3; i++) {
                polysCheck[i] = 0;
            }

            // the setting loop tell other option
            while (order.equals("set") || order.equals("print")){
                // get which poly
                char poly = scanner.nextLine().charAt(0);

                // SET option
                if (order.equals("set")){
                    // get the coefficients
                    String termsString = scanner.nextLine();
                    int[] coefficients = readArray(termsString);

                    // check the polynomial
                    if (poly == 'A' && polysCheck[0] == 0){
                        int[][] termsInteger = new int[2][coefficients.length];
                        for (int i = 0; i < coefficients.length; i++) {
                            // add coefficients and exponents
                            termsInteger[0][i] = coefficients[i];
                            termsInteger[1][i] = coefficients.length - i - 1;
                        }
                        // set the polynomial
                        polynomialSolver.setPolynomial('A', termsInteger);
                        polysCheck[0] = 1;
                    }
                    else if (poly == 'B' && polysCheck[1] == 0){
                        int[][] termsInteger = new int[2][coefficients.length];
                        for (int i = 0; i < coefficients.length; i++) {
                            // add coefficients and exponents
                            termsInteger[0][i] = coefficients[i];
                            termsInteger[1][i] = coefficients.length - i - 1;
                        }
                        // set the polynomial
                        polynomialSolver.setPolynomial('B', termsInteger);
                        polysCheck[1] = 1;
                    }
                    else if (poly == 'C' && polysCheck[2] == 0){
                        int[][] termsInteger = new int[2][coefficients.length];
                        for (int i = 0; i < coefficients.length; i++) {
                            // add coefficients and exponents
                            termsInteger[0][i] = coefficients[i];
                            termsInteger[1][i] = coefficients.length - i - 1;
                        }
                        // set the polynomial
                        polynomialSolver.setPolynomial('C', termsInteger);
                        polysCheck[2] = 1;
                    }
                    else {
                        // check if the polynomial is already set
                        throw new Exception();
                    }
                }

                // PRINT option
                else if (order.equals("print")){

                    // check which polynomial
                    if (poly == 'A' && polysCheck[0] == 1){
                        System.out.println(polynomialSolver.print('A'));
                    }
                    else if (poly == 'B' && polysCheck[1] == 1){
                        System.out.println(polynomialSolver.print('B'));
                    }
                    else if (poly == 'C' && polysCheck[2] == 1){
                        System.out.println(polynomialSolver.print('C'));
                    }
                    else {
                        // check if the polynomial is already set
                        throw new Exception();
                    }
                }
                
                // get the next order
                order = scanner.nextLine().trim();

            }

            *//* now as the setting is done it is the time for the operations *//*

            // 2. input the operation

            // check which of the 5 operations

            // 1 - add two polynomials and put the result in R
            if (order.equals("add")){
                // you should read the two polynomials
                char poly1 = scanner.nextLine().charAt(0);
                char poly2 = scanner.nextLine().charAt(0);

                // check if the polynomials are valid
                if ((poly1 != 'A' && poly1 != 'B' && poly1 != 'C') || (poly2 != 'A' && poly2 != 'B' && poly2 != 'C')){   
                    throw new IllegalArgumentException("Invalid polynomial name");
                }
                
                // check if the polynomials are already set
                if (poly1 == 'A' && polysCheck[0] == 0){
                    throw new Exception();
                }
                else if (poly1 == 'B' && polysCheck[1] == 0){
                    throw new Exception();
                }
                else if (poly1 == 'C' && polysCheck[2] == 0){
                    throw new Exception();
                }
                else if (poly2 == 'A' && polysCheck[0] == 0){
                    throw new Exception();
                }
                else if (poly2 == 'B' && polysCheck[1] == 0){
                    throw new Exception();
                }
                else if (poly2 == 'C' && polysCheck[2] == 0){
                    throw new Exception();
                }

                // finally everything is okay so we can add the two polynomials by the internal method which have print inside

                // get the result of the addition
                int[][]result = polynomialSolver.add(poly1, poly2);

                // put the result in R
                polynomialSolver.setPolynomial('R', result);

                // print R
                System.out.println(polynomialSolver.print('R'));

            }

            // 2 - subtract two polynomials and put the result in R
            else if (order.equals("subtract")){
                // you should read the two polynomials
                char poly1 = scanner.nextLine().charAt(0);
                char poly2 = scanner.nextLine().charAt(0);

                // check if the polynomials are valid
                if ((poly1 != 'A' && poly1 != 'B' && poly1 != 'C') || (poly2 != 'A' && poly2 != 'B' && poly2 != 'C')){   
                    throw new IllegalArgumentException("Invalid polynomial name");
                }
                
                // check if the polynomials are already set
                if (poly1 == 'A' && polysCheck[0] == 0){
                    throw new Exception();
                }
                else if (poly1 == 'B' && polysCheck[1] == 0){
                    throw new Exception();
                }
                else if (poly1 == 'C' && polysCheck[2] == 0){
                    throw new Exception();
                }
                else if (poly2 == 'A' && polysCheck[0] == 0){
                    throw new Exception();
                }
                else if (poly2 == 'B' && polysCheck[1] == 0){
                    throw new Exception();
                }
                else if (poly2 == 'C' && polysCheck[2] == 0){
                    throw new Exception();
                }

                // finally everything is okay so we can add the two polynomials by the internal method which have print inside

                // get the result of the addition
                int[][]result = polynomialSolver.subtract(poly1, poly2);

                // put the result in R
                polynomialSolver.setPolynomial('R', result);

                // print R
                System.out.println(polynomialSolver.print('R'));

            }

            // 3 - multiply two polynomials and put the result in R
            else if (order.equals("multiply")){
                // you should read the two polynomials
                char poly1 = scanner.nextLine().charAt(0);
                char poly2 = scanner.nextLine().charAt(0);

                // check if the polynomials are valid
                if ((poly1 != 'A' && poly1 != 'B' && poly1 != 'C') || (poly2 != 'A' && poly2 != 'B' && poly2 != 'C')){   
                    throw new IllegalArgumentException("Invalid polynomial name");
                }
                
                // check if the polynomials are already set
                if (poly1 == 'A' && polysCheck[0] == 0){
                    throw new Exception();
                }
                else if (poly1 == 'B' && polysCheck[1] == 0){
                    throw new Exception();
                }
                else if (poly1 == 'C' && polysCheck[2] == 0){
                    throw new Exception();
                }
                else if (poly2 == 'A' && polysCheck[0] == 0){
                    throw new Exception();
                }
                else if (poly2 == 'B' && polysCheck[1] == 0){
                    throw new Exception();
                }
                else if (poly2 == 'C' && polysCheck[2] == 0){
                    throw new Exception();
                }

                // finally everything is okay so we can add the two polynomials by the internal method which have print inside

                // get the result of the addition
                int[][]result = polynomialSolver.multiply(poly1, poly2);

                // put the result in R
                polynomialSolver.setPolynomial('R', result);

                // print R
                System.out.println(polynomialSolver.print('R'));

            }

            // 4 - clear the polynomial
            else if (order.equals("clear")){
                // get which poly
                char poly = scanner.nextLine().charAt(0);

                // check if the polynomial is valid
                if (poly != 'A' && poly != 'B' && poly != 'C') {
                    throw new IllegalArgumentException("Invalid polynomial name");
                }

                // if it is either empty or not, it can be cleared

                // clear the polynomial
                polynomialSolver.clearPolynomial(poly);
            }

            // 5 - evaluate a polynomial equation
            else if (order.equals("evaluate")){
                // get which poly
                char poly = scanner.nextLine().charAt(0);

                // check if the polynomial is valid
                if (poly != 'A' && poly != 'B' && poly != 'C' && poly != 'R') {
                    throw new IllegalArgumentException("Invalid polynomial name");
                }

                // check if the polynomial is already set
                if (poly == 'A' && polysCheck[0] == 0){
                    throw new Exception();
                }
                else if (poly == 'B' && polysCheck[1] == 0){
                    throw new Exception();
                }
                else if (poly == 'C' && polysCheck[2] == 0){
                    throw new Exception();
                }

                // get the value
                float value = scanner.nextFloat();

                // evaluate the polynomial
                float result = polynomialSolver.evaluatePolynomial(poly, value);

                // print the result
                System.out.println(result);
            }

            // not valid operation
            else {
                // check if the operation is valid
                throw new Exception();
            }


        }
        catch (Exception e) {
            System.out.println("Error");
        }
        */

    }

}