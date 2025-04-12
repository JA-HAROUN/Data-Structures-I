public abstract class DoubleLinkedList implements ILinkedList {

    public class DllNode {

        Object element;
        DllNode next;
        DllNode prev;
    
        public DllNode(DllNode prev, Object element, DllNode next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
        
    }


    // Main Dummy Nodes & the size of the list
    DllNode head;
    DllNode tail;
    int numOfNodes;

    // Constructor
    public DoubleLinkedList() {
        head = new DllNode(null, null, null);
        tail = new DllNode(head, null, null);
        head.next = tail;
        numOfNodes = 0;
    }


    // interface methods

    
    @Override
    public void add(int index, Object element){return;}

    @Override
    public void add(Object element){return;}

    @Override
    public Object get(int index){return null;}

    @Override
    public void set(int index, Object element){return;}

    @Override
    public void clear(){return;}

    @Override
    public boolean isEmpty(){return false;}

    @Override
    public void remove(int index){return;}

    @Override
    public int size(){return 0;}

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex){
        return null;
    }

    @Override
    public boolean contains(Object o){return false;}

}
