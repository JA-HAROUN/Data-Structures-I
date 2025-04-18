public class SingleLinkedList implements ILinkedList {

    public class SllNode {
    
        public Object element;
        public SllNode next;
    
        public SllNode(Object element, SllNode next) {
            this.element = element;
            this.next = next;
        }
    
    }
    
    // Main Dummy Nodes & the size of the list
    SllNode head;
    SllNode tail;
    int numOfNodes;

    // Constructor
    public SingleLinkedList() {
        head = new SllNode(null, null);
        tail = new SllNode(null, null);
        head.next = tail;
        numOfNodes = 0;
    }

    void boundsCheck(int index) {
        if (index < 0 || index >= numOfNodes) {
            throw new IndexOutOfBoundsException();
        }
    }

    // interface methods

    // Done
    @Override
    public void add(int index, Object element) {
        // check if out of boundaries
        if (index < 0 || index >= numOfNodes) {
            throw new IndexOutOfBoundsException();
        }

        // search
        SllNode current = head;
        for (int k = 0; k < index; k++){
            current = current.next;
        }
        SllNode newNode = new SllNode( element, current.next);
        current.next = newNode;
        numOfNodes++;
    }

    // Done
    @Override
    public void add(Object element) {
        // check if out of boundaries
        if (numOfNodes < 0)
        {
            return;
        }

        // search
        SllNode current = head;
        for (int k = 0; k < numOfNodes; k++){
            current = current.next;
        }
        SllNode newNode = new SllNode( element, tail);
        current.next = newNode;
        numOfNodes++;
    }

    @Override 
    public Object get(int index) {

        // check if out of boundaries
        boundsCheck(index);

        // search
        SllNode current = head.next;
        for (int k = 0; k < index; k++){
            current = current.next;
        }
        return current.element;

    }

    @Override 
    public void set(int index, Object element) {
        // check if out of boundaries
        boundsCheck(index);

        // search
        SllNode current = head.next;
        for (int k = 0; k < index; k++){
            current = current.next;
        }
        current.element =  element;
    }

    @Override 
    public void clear() {
        while (head.next != tail){
            head.next=head.next.next;
        }
        numOfNodes=0;
    }
    
    @Override 
    public boolean isEmpty() {
        return numOfNodes == 0;
    }

    @Override 
    public void remove(int index){
        boundsCheck(index);

        // search
        SllNode current = head;
        for (int k = 0; k < index; k++){
            current = current.next;
        }
        SllNode toRemove = current.next;
        current.next = toRemove.next;
        toRemove.next = null;
        toRemove.element = null;
        numOfNodes--;
    }

    @Override 
    public int size() {
        return numOfNodes;
    }

    @Override 
    public ILinkedList sublist(int fromIndex, int toIndex) {
        // check if out of boundaries
        if (fromIndex >= numOfNodes || fromIndex < 0 || toIndex >= numOfNodes || toIndex < 0 || fromIndex > toIndex)
        {
            throw new IndexOutOfBoundsException();
        }

        // search
        SllNode current = head.next;
        for (int k = 0; k < fromIndex; k++){
            current = current.next;
        }

        // we got the head of the sublist it is current

        // make the sublist
        SingleLinkedList sublist = new SingleLinkedList();

        // make sublist head and tail
        sublist.head = new SllNode(null, null);
        sublist.tail = new SllNode(null, null);
        SllNode sublistHead = sublist.head;
        sublistHead.next = sublist.tail;

        SllNode sublistPointer = sublistHead;

        // add the elements to the sublist
        for (int k = fromIndex; k <= toIndex; k++){
            SllNode newNode = new SllNode(current.element, null);
            sublistPointer.next = newNode;
            sublistPointer = newNode;
            current = current.next;
        }

        // make the tail of the sublist and add the size
        sublistPointer.next = sublist.tail;

        sublist.numOfNodes = toIndex - fromIndex + 1;

        // return the sublist
        return sublist;
    }

    @Override
    public boolean contains(Object o) {
        SllNode current = head.next;
        while (current != tail){
            if (current.element.equals(o)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

}