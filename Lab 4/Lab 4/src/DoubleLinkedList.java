public class DoubleLinkedList implements ILinkedList {

    public class DLnode {

        private Object element;
        private DLnode next;
        private DLnode prev;

        public DLnode() {
            element = null;
            next = null;
            prev = null;
        }

        public DLnode(Object e, DLnode n, DLnode p) {
            element = null;
            next = n;
            prev = p;
        }

        public void setElement(Object nodeContent) {
            this.element = nodeContent;
        }

        public Object getElement() {
            return this.element;
        }

        public void setnext(DLnode nodeNext) {
            this.next = nodeNext;
        }

        public DLnode getnext() {
            return this.next;
        }

        public void setprev(DLnode nodePrev) {
            this.prev = nodePrev;
        }

        public DLnode getprev() {
            return this.prev;
        }
    }

    private DLnode header;
    private DLnode trailer;

    // Constructor
    public DoubleLinkedList() {

        header = new DLnode();
        trailer = new DLnode();
        header.setnext(trailer);
        trailer.setprev(header);
    }

    void boundsCheck(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @ Override
    public boolean isEmpty() {
        if (header.getnext() == trailer && trailer.getprev() == header) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        header.setnext(trailer);
        trailer.setprev(header);
    }

    @Override
    public int size() {
        DLnode q = header.getnext();
        int size = 0;
        while (q != trailer) {
            size++;
            q = q.getnext();
        }
        return size;
    }

    @Override
    public boolean contains(Object o) {
        DLnode q = header.getnext();
        while (q != trailer) {
            if (q.getElement() == o) {
                return true;
            }
            q = q.getnext();
        }
        return false;
    }

    @Override
    public void add(Object element) {
        DLnode addedNode = new DLnode();
        addedNode.setElement(element);

        DLnode q = trailer.getprev();
        addedNode.setprev(q);
        q.setnext(addedNode);
        addedNode.setnext(trailer);
        trailer.setprev(addedNode);
    }

    @Override
    public void add(int index, Object element) {
        DLnode addedNode = new DLnode();
        addedNode.setElement(element);

        DLnode q = header.getnext();
        int i = 0;

        boundsCheck(index);

        while (q != trailer) {
            if (i != index) {
                q = q.getnext();
                i++;
            } else {
                break;
            }
        }

        DLnode w = q.getprev();
        w.setnext(addedNode);
        addedNode.setprev(w);

        addedNode.setnext(q);
        q.setprev(addedNode);
    }

    @Override
    public void remove(int index) {
        DLnode q = header.getnext();
        int i = 0;
        boundsCheck(index);

        while (q != trailer) {
            if (i != index) {
                q = q.getnext();
                i++;
            } else {
                break;
            }
        }
        DLnode n = q.getnext();
        DLnode p = q.getprev();

        p.setnext(n);
        n.setprev(p);
    }

    @Override
    public Object get(int index) {
        DLnode q = header.getnext();
        int i = 0;
        boundsCheck(index);

        while (q != trailer) {
            if (i != index) {
                q = q.getnext();
                i++;
            } else {
                break;
            }
        }

        return q.getElement();
    }

    @Override
    public void set(int index, Object element) {
        DLnode q = header.getnext();
        int i = 0;
        boundsCheck(index);

        while (q != trailer) {
            if (i != index) {
                q = q.getnext();
                i++;
            } else {
                break;
            }
        }

        q.setElement(element);
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex){
        DLnode q = header.getnext();
        DLnode w = header.getnext();
        int i = 0;

        if(fromIndex > toIndex){
            throw new IndexOutOfBoundsException();
        }

        boundsCheck(fromIndex);
        boundsCheck(toIndex);

        while (q != trailer) {
            if (i != fromIndex) {
                q = q.getnext();
                i++;
            }
            else{
                break;
            }
        }

        i = 0;

        while (w != trailer) {
            if (i != toIndex) {
                w = w.getnext();
                i++;
            }
            else{
                break;
            }
        }

        header.setnext(q);
        q.setprev(header);
        trailer.setprev(w);
        w.setnext(trailer);

        return this;
    }
}
