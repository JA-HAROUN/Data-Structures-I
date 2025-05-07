import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ArrayQueue implements IQueue, IArrayBased {

    public int n;
    public Object[] queue;
    private int front;
    private int rear;
    private int size;

    // constructor
    public ArrayQueue(int n) {
        this.n = n;
        this.queue = new Object[n];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    @Override
    public void enqueue(Object item) {
        // move all the elements to the right and add the new element at the front

        if (size == n) {
            throw new IllegalStateException("Queue is full");
        }

        front = (front - 1 + queue.length) % queue.length;
        queue[front] = item;

        if (size == 0) {
            rear = front;
        }

        size++;

    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        // remove from the rear
        Object item = queue[rear];
        queue[rear] = null; // clear the reference
        rear = (rear - 1 + queue.length) % queue.length; // move the rear pointer
        size--;
        if (isEmpty()) {
            front = 0;
            rear = -1;
        }
        return item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }


    public void printQueue() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        // put the elements
        for (int i = 0; i < size; i++) {
            sb.append(queue[(front + i) % queue.length]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println(sb.toString());

    }

    public void readData(String elements) {
        if (elements.equals("[]")) {
            return;
        }

        elements = elements.substring(1, elements.length() - 1);


        String[] elementsArray = elements.split(", ");

        queue = new Object[elementsArray.length];
        for (int i = 0; i < elementsArray.length; i++) {
            queue[i] = elementsArray[i];
        }

        front = 0;
        rear = elementsArray.length - 1;
        size = elementsArray.length;


    }








    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */


        try {

            Scanner scanner = new Scanner(System.in);
            String elements = scanner.nextLine().trim();
            String order = scanner.nextLine().trim();

            // Read the elements from the input
            ArrayQueue arrayQueue = new ArrayQueue(1000);
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