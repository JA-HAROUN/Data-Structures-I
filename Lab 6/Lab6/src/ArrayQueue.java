import java.util.Arrays;

public class ArrayQueue implements IQueue{

    public Object[] queue;
    private int front;
    private int rear;
    private int size;

    // constructor
    public ArrayQueue() {
        this.queue = new Object [1000];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    @Override
    public void enqueue(Object item) {
        // move all the elements to the right and add the new element at the front

        if (size == queue.length) {
            // resize the array if it's full
            queue = Arrays.copyOf(queue, queue.length * 2);
        }

        // add to the front
        front = (front - 1 + queue.length) % queue.length;
        queue[front] = item;
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

    

}
