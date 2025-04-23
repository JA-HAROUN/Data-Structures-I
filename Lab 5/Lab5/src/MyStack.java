public class MyStack implements IStack {

    // Double Linked List Node
    public class DLLNode {

        private DLLNode prev;
        private Object element;
        private DLLNode next;
    
        // Constructor
        public DLLNode(Object element) {
            this.element = element;
            this.prev = null;
            this.next = null;
        }
    
        // Getters and Setters
        public Object getElement() {
            return element;
        }
        
        public void setElement(Object element) {
            this.element = element;
        }
        
        public DLLNode getNext() {
            return next;
        }
    
        public void setNext(DLLNode next) {
            this.next = next;
        }

        public DLLNode getPrev() {
            return prev;
        }

        public void setPrev(DLLNode prev) {
            this.prev = prev;
        }
        
    }

    // Double Linked List
    public class DLL {

        private DLLNode header;
        private DLLNode sentinel;
        private int size;
    
        // Constructor
        public DLL() {
            this.header = new DLLNode(null);
            this.sentinel = new DLLNode(null);
            this.size = 0;
            this.header.setNext(sentinel);
            this.sentinel.setPrev(header);
        }
    
        // Getters
        public DLLNode getHead() {
            if (size == 0) {
                return null;
            }
            return header.getNext();
        }
    
        // Setters
        public void setHead(DLLNode head) {
            this.header.setNext(head);
        }
    
        // Add a new node at the head of the list
        public void addFirst(Object element) {
            DLLNode newNode = new DLLNode(element);
            newNode.setNext(header.getNext());
            header.getNext().setPrev(newNode);
            header.setNext(newNode);
            newNode.setPrev(header);
            size++;
        }
    
        // remove the first node
        public Object removeFirst() {
            if (size == 0) {
                throw new IllegalStateException("List is empty");
            }
            DLLNode firstNode = header.getNext();
            Object element = firstNode.getElement();
            header.setNext(firstNode.getNext());
            firstNode.getNext().setPrev(header);
            size--;
            return element;
        }

        // Get the size of the list
        public int getSize() {
            return size;
        }

        // print DLL like abcde
        public String toString() {
            StringBuilder sb = new StringBuilder();
            DLLNode current = header.getNext();
            while (current != sentinel) {
                sb.append(current.getElement());
                current = current.getNext();
            }
            return sb.toString();
        }
        
    }
    
    private DLL stackList;

    // Constructor
    public MyStack() {
        this.stackList = new DLL();
    }

    @Override
    public Object pop() {
        if (stackList.getSize() == 0) {
            return null;
        }

        Object element = stackList.getHead().getElement();
        stackList.removeFirst();
        return element;
    }

    @Override
    public Object peek() {
        if (stackList.getSize() == 0) {
            return null;
        }

        return stackList.getHead().getElement();
    }

    @Override
    public void push(Object element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        stackList.addFirst(element);
    }

    @Override
    public boolean isEmpty() {
        return stackList.getSize() == 0;
    }

    @Override
    public int size() {
        return stackList.getSize();
    }
    
    public String toString() {
        return stackList.toString();
    }

    // String to stack
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


}
