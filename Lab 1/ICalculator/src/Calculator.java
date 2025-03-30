import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ICalculator {
    int add(int x, int y);
    float divide(int x, int y) throws RuntimeException;
}


public class Calculator implements ICalculator{
    /* Implement your calculator class here*/

    int x, y;

    Calculator(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int add (int x, int y) {
        return x + y;
    }

    @Override
    public float divide (int x, int y) {
        return (float) x / (float) y;
    }

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in))
        {
            String input = scanner.nextLine();
            // addition
            if (input.contains("+"))
            {
                int num1 = Integer.parseInt(input.substring(0, input.indexOf("+")).trim());
                int num2 = Integer.parseInt(input.substring(input.indexOf("+") + 1, input.length()).trim());
                Calculator calculator = new Calculator(num1, num2);
                System.out.println(calculator.add(num1, num2));
            }
            // division
            else if (input.contains("/")) {
                int num1 = Integer.parseInt(input.substring(0, input.indexOf("/")).trim());
                int num2 = Integer.parseInt(input.substring(input.indexOf("/") + 1, input.length()).trim());
                if (num2 == 0) {
                    throw new ArithmeticException("Error");
                }
                Calculator calculator = new Calculator(num1, num2);
                System.out.println(calculator.divide(num1, num2));
            }
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }

    }

}

