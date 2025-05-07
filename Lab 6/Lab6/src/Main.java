import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

    
        public static void main(String[] args) {
            
        try {
    
            Scanner scanner = new Scanner(System.in);
            String elements = scanner.nextLine().trim();
            String order = scanner.nextLine().trim();

            // Read the elements from the input
            ArrayQueue arrayQueue = new ArrayQueue();
            arrayQueue.readData(elements);
            

            if (order.equals("enqueue")){
                String element = scanner.nextLine().trim();
                arrayQueue.enqueue(element);
                arrayQueue.printQueue();
            }

            else if (order.equals("dequeue")) {
                arrayQueue.dequeue();
                arrayQueue.printQueue();
            }

            else if (order.equals("isEmpty")) {
                if (arrayQueue.isEmpty()) {
                    System.out.println("True");
                } else {
                    System.out.println("False");
                }
            }

            else if (order.equals("size")) {
                System.out.println(arrayQueue.size());
            }

            else {
                throw new IllegalArgumentException("Invalid order");
            }

        }
        catch (Exception e) {
            System.out.println("Error");
        }

    }

}