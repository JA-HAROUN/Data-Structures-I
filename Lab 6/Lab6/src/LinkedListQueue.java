public class LinkedListQueue  implements IQueue {

    public class Node {
        Object data;
        Node next;
        Node prev;

        public Node(Object data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        public Node (Node prev, Object data, Node next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
    
    public class DLL {
        Node header;
        Node trailer;
        int size;

        public DLL() {
            header = new Node(null);
            trailer = new Node(null);
            header.next = trailer;
            trailer.prev = header;
            size = 0;
        }

        public void addLast(Object item) {
            Node newNode = new Node(trailer.prev, item, trailer);
            trailer.prev.next = newNode;
            trailer.prev = newNode;
            size++;
        }

        public void addFirst(Object item) {
            Node newNode = new Node(header, item, header.next);
            header.next.prev = newNode;
            header.next = newNode;
            size++;
        }

        public Object removeFirst() {
            if (isEmpty()) {
                throw new IllegalStateException("List is empty");
            }
            Node firstNode = header.next;
            header.next = firstNode.next;
            firstNode.next.prev = header;
            size--;
            return firstNode.data;
        }

        public Object removeLast() {
            if (isEmpty()) {
                throw new IllegalStateException("List is empty");
            }
            Node lastNode = trailer.prev;
            trailer.prev = lastNode.prev;
            lastNode.prev.next = trailer;
            size--;
            return lastNode.data;
        }

        public Object getFirst () {
            if (isEmpty()) {
                throw new IllegalStateException("List is empty");
            }
            return header.next.data;
        }


        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

    }

    private DLL list;

    // constructor
    public LinkedListQueue() {
        list = new DLL();
    }

    @Override
    public void enqueue(Object item) {
        list.addFirst(item);
        if (list.isEmpty()) {
            list.header = list.trailer;
            list.trailer = list.header;
        } else {
            list.header = list.header.next;
            list.trailer = list.trailer.prev;
        }
        list.size++;
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        Object item = list.removeLast();
        list.size--;
        if (list.isEmpty()) {
            list.header = list.trailer;
            list.trailer = list.header;
        } else {
            list.header = list.header.next;
            list.trailer = list.trailer.prev;
        }
        return item;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    public void printQueue() {
        // print it as [1, 2, 3, 4, 5]
        Node current = list.header.next;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (current != list.trailer) {
            sb.append(current.data);
            current = current.next;
            if (current != list.trailer) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
        
    }

    public void reverseQueue() {
        Node temp = list.header;
        list.header = list.trailer;
        list.trailer = temp;
    }

    public void readData(String elements) {
        // This method should read the elements from the input and add them to the list [1, 2, 3, 4, 5]

        if (elements == "[]"){
            list.size = 0;
            list.header.next = list.trailer;
            list.trailer.prev = list.header;
            return;
        }

        String elementsArray = elements.substring(elements.indexOf("[") + 1, elements.indexOf("]"));

        String[] elementsList = elementsArray.split(", ");

        for (String element : elementsList) {
            list.addLast(element);
        }

    }

}
