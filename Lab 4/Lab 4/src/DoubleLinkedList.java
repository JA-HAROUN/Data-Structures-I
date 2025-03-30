public abstract class DoubleLinkedList implements ILinkedList {

    @Override
    public abstract void add(int index, Object element);

    @Override
    public abstract void add(Object element);

    @Override
    public abstract Object get(int index);

    @Override
    public abstract void set(int index, Object element);

    @Override
    public abstract void clear();

    @Override
    public abstract boolean isEmpty();

    @Override
    public abstract void remove(int index);

    @Override
    public abstract int size();

    @Override
    public abstract ILinkedList sublist(int fromIndex, int toIndex);

    @Override
    public abstract boolean contains(Object o);

}
