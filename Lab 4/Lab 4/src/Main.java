import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {
    public static int[] readArray(String inputString) {
        inputString = inputString.substring(1, inputString.length() - 1);
        String[] values = inputString.split(",");
        int[] result = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            result[i] = Integer.parseInt(values[i].trim());
        }

        return result;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            PolynomialSolver solver = new PolynomialSolver();

            while (scanner.hasNextLine()) {
                String option = scanner.nextLine().trim();
                char poly1;
                char poly2;
                int[][] result;
                switch(option){
                    case "set":
                        poly1 = scanner.nextLine().charAt(0);
                        String termsStr = scanner.nextLine();
                        int[] coefficients = readArray(termsStr);
                        
                        int[][] terms = new int[2][coefficients.length];
                        for (int i = 0; i < coefficients.length; i++) {
                            terms[0][i] = coefficients[i];
                            terms[1][i] = coefficients.length - i - 1;
                        }
                    
                        solver.setPolynomial(poly1, terms);
                        break;
                    case "print":
                        poly1 = scanner.nextLine().charAt(0);
                        System.out.println(solver.print(poly1));
                        break;
                    case "add":
                        poly1 = scanner.nextLine().charAt(0);
                        poly2 = scanner.nextLine().charAt(0);
                        result = solver.add(poly1, poly2);
                        solver.setPolynomial('R', result);
                        System.out.println(solver.print('R'));
                        break;
                    case "sub":
                        poly1 = scanner.nextLine().charAt(0);
                        poly2 = scanner.nextLine().charAt(0);
                        result = solver.subtract(poly1, poly2);
                        solver.setPolynomial('R', result);
                        System.out.println(solver.print('R'));
                        break;
                    case "mult":
                        poly1 = scanner.nextLine().charAt(0);
                        poly2 = scanner.nextLine().charAt(0);
                        result = solver.multiply(poly1, poly2);
                        solver.setPolynomial('R', result);
                        System.out.println(solver.print('R'));
                        break;
                    case "clear":
                        poly1 = scanner.nextLine().charAt(0);
                        solver.clearPolynomial(poly1);
                        System.out.println("[]");
                        break;
                    case "eval":
                        poly1 = scanner.nextLine().charAt(0);
                        float value = Float.parseFloat(scanner.nextLine());
                        System.out.println((int)solver.evaluatePolynomial(poly1, value));
                        break;
                    default:
                        throw new Exception();
                }
            }
        } 
        catch (Exception e) {
            System.out.println("Error");
        }
    }
}