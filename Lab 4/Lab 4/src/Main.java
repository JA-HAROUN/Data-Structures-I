import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

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

        try (Scanner scanner = new Scanner(System.in)){

            PolynomialSolver polynomialSolver = new PolynomialSolver();

            // 1. input the polynomial

            // first thing the order
            String order = scanner.nextLine().trim();

            // the setting loop tell other option
            while (order.equals("set") || order.equals("print")){
                // get which poly
                char poly = scanner.nextLine().charAt(0);

                // SET option
                if (order.equals("set")){
                    // get the coefficients
                    String termsString = scanner.nextLine();
                    int[] coefficients = readArray(termsString);

                    int[][] termsInteger = new int[2][coefficients.length];
                    for (int i = 0; i < coefficients.length; i++) {
                        // add coefficients and exponents
                        termsInteger[0][i] = coefficients[i];
                        termsInteger[1][i] = coefficients.length - i - 1;
                    }
                    // set the polynomial
                    polynomialSolver.setPolynomial(poly, termsInteger);

                }

                // PRINT option
                else if (order.equals("print")){
                    
                    System.out.println(polynomialSolver.print(poly));
                    
                }
                
                // get the next order
                order = scanner.nextLine().trim();

            }

            // 1 - add two polynomials and put the result in R
            if (order.equals("add")){
                // you should read the two polynomials
                char poly1 = scanner.nextLine().charAt(0);
                char poly2 = scanner.nextLine().charAt(0);

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
                // if it is either empty or not, it can be cleared

                // clear the polynomial
                polynomialSolver.clearPolynomial(poly);
            }

            // 5 - evaluate a polynomial equation
            else if (order.equals("evaluate")){
                // get which poly
                char poly = scanner.nextLine().charAt(0);

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
       

    }

}