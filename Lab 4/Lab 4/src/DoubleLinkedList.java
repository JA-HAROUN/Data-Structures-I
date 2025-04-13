class DoubleLinkedList implements ILinkedList {
    class DLnode {
        private Object element;
        private DLnode next;
        private DLnode prev;

        public DLnode() {
            element = null;
            next = null;
            prev = null;
        }

        public DLnode(Object e, DLnode n, DLnode p) {
            element = e;
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

    @Override
    public boolean isEmpty() {
        return header.getnext() == trailer && trailer.getprev() == header;
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
            if (q.getElement().equals(o)) {
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

        boundsCheck(index);

        DLnode q = header.getnext();
        int i = 0;
        while (i < index) {
            q = q.getnext();
            i++;
        }

        DLnode w = q.getprev();
        w.setnext(addedNode);
        addedNode.setprev(w);
        addedNode.setnext(q);
        q.setprev(addedNode);
    }

    @Override
    public void remove(int index) {
        boundsCheck(index);

        DLnode q = header.getnext();
        int i = 0;
        while (i < index) {
            q = q.getnext();
            i++;
        }

        DLnode n = q.getnext();
        DLnode p = q.getprev();
        p.setnext(n);
        n.setprev(p);
    }

    @Override
    public Object get(int index) {
        boundsCheck(index);

        DLnode q = header.getnext();
        int i = 0;
        while (i < index) {
            q = q.getnext();
            i++;
        }

        return q.getElement();
    }

    @Override
    public void set(int index, Object element) {
        boundsCheck(index);

        DLnode q = header.getnext();
        int i = 0;
        while (i < index) {
            q = q.getnext();
            i++;
        }

        q.setElement(element);
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        boundsCheck(fromIndex);
        boundsCheck(toIndex);

        DLnode q = header.getnext();
        int i = 0;
        while (i < fromIndex) {
            q = q.getnext();
            i++;
        }

        DLnode w = header.getnext();
        i = 0;
        while (i < toIndex) {
            w = w.getnext();
            i++;
        }

        header.setnext(q);
        q.setprev(header);
        trailer.setprev(w);
        w.setnext(trailer);

        return this;
    }
}