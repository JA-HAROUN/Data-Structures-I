import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Main {

    public static void main(String[] args) {

        try(Scanner scanner = new Scanner(System.in)) {

            String infix = scanner.nextLine().trim();

            String strA = scanner.nextLine().trim();
            String strB = scanner.nextLine().trim();
            String strC = scanner.nextLine().trim();
            int A = Integer.parseInt(strA.substring(strA.indexOf('=') + 1));
            int B = Integer.parseInt(strB.substring(strB.indexOf('=') + 1));
            int C = Integer.parseInt(strC.substring(strC.indexOf('=') + 1));

            String variables = String.format("%s,%s,%s", A, B, C);

            // Use the Evaluator class
            // to postfix
            Evaluator evaluator = new Evaluator();
            String postfix = evaluator.infixToPostfix(infix);
            System.out.println(postfix);

            // to evaluate
            int result = evaluator.evaluate(postfix);
            System.out.println(result);

        }
        catch(Exception e) {
            System.out.println("Error");
        }

    }
}