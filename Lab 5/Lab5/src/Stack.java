public class Stack implements IStack {


    // Single Linked List Node
    public class SLLNode {

        private Object element;
        private SLLNode next;
    
        // Constructor
        public SLLNode(Object element) {
            this.element = element;
            this.next = null;
        }
    
        // Getters and Setters
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

    // Single Linked List
    public class SLL {

        private SLLNode head;
        private int size;
    
        // Constructor
        public SLL() {
            this.head = null;
        }
    
        // Getters
        public SLLNode getHead() {
            return head.getNext();
        }
    
        // Setters
        public void setHead(SLLNode head) {
            this.head = head;
        }
    
        // Add a new node at the head of the list
        public void addFirst(Object element) {
            SLLNode newNode = new SLLNode(element);
            newNode.setNext(head);
            head = newNode;
            size++;
        }
    
        // remove the first node
        public Object removeFirst() {
            if (head == null) {
                throw new IllegalStateException("List is empty");
            }
            Object element = head.getElement();
            head = head.getNext();
            size--;
            return element;
        }
        
    }
    


    private SLL stackList;
    private int size = 0;

    // Constructor
    public Stack() {
        this.stackList = new SLL();
        this.size = 0;
    }

    @Override
    public Object pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        Object element = stackList.removeFirst();
        size--;
        return element;
    }

    @Override
    public Object peek() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }

        return stackList.getHead().getNext();
    }

    @Override
    public void push(Object element) {
        if (element == null) {
            throw new NullPointerException("Element is null");
        }
        stackList.addFirst(element);
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
    
}
