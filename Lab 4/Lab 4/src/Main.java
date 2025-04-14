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
         /*
          * try (Scanner scanner = new Scanner(System.in)) {

            // 1. the inputList
            String inputList = scanner.nextLine().trim();

            // remove the brackets
            inputList = inputList.substring(1, inputList.length() - 1);

            // split each one with comma
            String[] values = inputList.split(",");

            // make the list made of the values DoubleLinkedList
            DoubleLinkedList list = new DoubleLinkedList();
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
            int index;
            int element;
            Object elem;

            switch(order){
                case "add":
                    // get first element
                    int firstElement = scanner.nextInt();

                    // add to the end
                    list.add(firstElement);

                    // print the list in the form like the input
                    list.printList();
                    
                    break;

                case "addToIndex":
                    // get the index
                    index = scanner.nextInt();

                    // get the element
                    element = scanner.nextInt();

                    // add to the index
                    list.addToIndex(index, element);

                    // print the list in the form like the input
                    list.printList();

                    break;

                case "get":
                    // get the index
                    index = scanner.nextInt();

                    // get the element
                    elem = list.get(index);

                    // print the element
                    System.out.println(elem);

                    break;

                case "set":
                    // get the index
                    index = scanner.nextInt();

                    // get the element
                    element = scanner.nextInt();

                    // set the element
                    list.set(index, element);

                    // print the list in the form like the input
                    list.printList();

                    break;

                case "clear":
                    // clear the inputList
                    list.clear();

                    list.printList();

                    break;

                case "isEmpty":
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
                    break;

                case "remove":
                    // get the index
                    index = scanner.nextInt();

                    // remove the element
                    list.remove(index);

                    // print the list in the form like the input
                    list.printList();
                    break;

                case "size":
                    // get the size
                    int size = list.size();

                    // print the size
                    System.out.println(size);
                    break;

                case "sublist":
                    // get the fromIndex
                    int fromIndex = scanner.nextInt();

                    // get the toIndex
                    int toIndex = scanner.nextInt();

                    // get the subinputList
                    ILinkedList sublist = list.sublist(fromIndex, toIndex);

                    sublist.printList();
                    break;

                case "contains":
                    // get the element
                    element = scanner.nextInt();

                    // print the result
                    if (list.contains(element))
                    {
                        System.out.println("True");
                    }
                    else
                    {
                        System.out.println("False");
                    }
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("Error");
        }
          */

        // the polynomial solver
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