/**
 * Interface defining basic stack operations.
 */
interface IStack {
    Object pop();
    Object peek();
    void push(Object element);
    boolean isEmpty();
    int size();
}

/**
 * A custom implementation of a stack using a singly linked list.
 */
public class MyStack implements IStack {

    /**
     * A node in the singly linked list, containing an element and a reference to the next node.
     */
    public class SLLNode {

        private Object element;
        private SLLNode next;

        /**
         * Constructs a new node with the given element.
         * @param element the data to store in the node
         */
        public SLLNode(Object element) {
            this.element = element;
            this.next = null;
        }

        public Object getElement() {
            return element;
        }

        public void setElement(Object element) {
            this.element = element;
        }

        public SLLNode getNext() {
            return next;
        }

        public void setNext(SLLNode next) {
            this.next = next;
        }
    }

    /**
     * A singly linked list used as the underlying data structure for the stack.
     */
    public class SLL {

        private SLLNode head;
        private int size;

        /** Constructs an empty singly linked list. */
        public SLL() {
            this.head = null;
        }

        public SLLNode getHead() {
            return head;
        }

        public void setHead(SLLNode head) {
            this.head = head;
        }

        /**
         * Adds a new element to the beginning of the list.
         * @param element the element to add
         */
        public void addFirst(Object element) {
            SLLNode newNode = new SLLNode(element);
            newNode.setNext(head);
            head = newNode;
            size++;
        }

        /**
         * Removes and returns the first element of the list.
         * @return the removed element
         */
        public Object removeFirst() {
            if (head == null) {
                throw new IllegalStateException("List is empty");
            }
            Object element = head.getElement();
            head = head.getNext();
            size--;
            return element;
        }

        /**
         * @return the size of the list
         */
        public int getSize() {
            return size;
        }

        /**
         * @return a string representation of the list
         */
        public String toString() {
            SLLNode curr = head;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            while (curr != null) {
                sb.append(curr.getElement());
                if (curr.getNext() != null) sb.append(", ");
                curr = curr.getNext();
            }
            sb.append("]");
            return sb.toString();
        }
    }

    private SLL stackList;

    /** Constructs an empty stack. */
    public MyStack() {
        this.stackList = new SLL();
    }

    /**
     * Removes and returns the top element of the stack.
     * @return the popped element
     */
    @Override
    public Object pop() {
        if (stackList.getSize() == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return stackList.removeFirst();
    }

    /**
     * Returns the top element of the stack without removing it.
     * @return the top element
     */
    @Override
    public Object peek() {
        if (stackList.getSize() == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return stackList.getHead().getElement();
    }

    /**
     * Pushes an element onto the top of the stack.
     * @param element the element to push
     */
    @Override
    public void push(Object element) {
        if (element == null) {
            throw new NullPointerException("Element is null");
        }
        stackList.addFirst(element);
    }

    /**
     * @return true if the stack is empty
     */
    @Override
    public boolean isEmpty() {
        return stackList.getSize() == 0;
    }

    /**
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return stackList.getSize();
    }

    /**
     * @return a string representation of the stack
     */
    public String toString() {
        return stackList.toString();
    }

    /**
     * Converts a formatted string into a stack object.
     * @param stackString the string representation of the stack (e.g. [3, 2, 1])
     * @return a new MyStack object with the elements parsed from the string
     */
    public static MyStack stringToStack(String stackString) {
        MyStack stack = new MyStack();
        String[] elements = stackString.substring(1, stackString.length() - 1).split(",");
        for (int i = elements.length - 1; i >= 0; i--) {
            String element = elements[i].trim();
            if (!element.equals("")) {
                stack.push(Integer.parseInt(element));
            }
        }
        return stack;
    }

    /**
     * Main method to interact with the stack using input from the user.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String stackString = scanner.nextLine().trim();
            MyStack stack = MyStack.stringToStack(stackString);
            String order = scanner.nextLine().trim();

            switch (order) {
                case "push":
                    int element = scanner.nextInt();
                    stack.push(element);
                    System.out.println(stack.toString());
                    break;
                case "pop":
                    stack.pop();
                    System.out.println(stack.toString());
                    break;
                case "peek":
                    System.out.println(stack.peek());
                    break;
                case "isEmpty":
                    System.out.println(stack.isEmpty() ? "True" : "False");
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                default:
                    System.out.println("Error");
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
