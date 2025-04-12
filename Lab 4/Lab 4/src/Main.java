import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

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
    
        public static void main(String[] args) {

            try (Scanner scanner = new Scanner(System.in)) {

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
            }

        }
}