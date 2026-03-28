package deque;

public interface Deque<T>{
    public void addFirst(T e);
    public void addLast(T e);
    public T removeFirst();
    public T removeLast();
    public int size();

    default public boolean isEmpty() {
        return size()==0;
    }

    public T get(int index);
    public void printDeque();
}
