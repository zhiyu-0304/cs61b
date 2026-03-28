package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Iterable<T>,Deque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size=0;
    }
    private void resize(int s){
        T[] temp = (T[]) new Object[s];
        for(int i=0;i<size;i++){
            temp[i]=get(i);
        }
        nextFirst=s-1;
        nextLast=size;
        items=temp;
    }
    private boolean isFull(){
        return size==items.length;
    }
    @Override
    public void addFirst(T item){
        if(isFull()){
            resize(items.length*2);
        }
        items[nextFirst]=item;
        nextFirst=(nextFirst-1+items.length)%items.length;
        size++;
    }
    @Override
    public void addLast(T item){
        if (isFull()) {
            resize(items.length*2);
        }
        items[nextLast]=item;
        nextLast=(nextLast+1)%items.length;
        size++;
    }
    @Override
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        int first=(nextFirst+1+items.length)%items.length;
        T item=items[first];
        items[first]=null;
        nextFirst=first;
        size--;
        if(size>=4&&size*4<=items.length){
            resize(items.length/2);
        }
        return item;
    }
    @Override
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        int last=(nextLast-1+items.length)%items.length;
        T item=items[last];
        items[last]=null;
        nextLast=last;
        size--;
        if(size>=4&&size*4<=items.length){
            resize(items.length/2);
        }
        return item;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public T get(int index){
        if(index<0 || index>=size){
            return null;
        }
        return items[(index+nextFirst+1+items.length)%items.length];
    }
    @Override
    public boolean equals(Object o){
        if(o==this)
            return true;
        if(o instanceof ArrayDeque){
            ArrayDeque<?> d=(ArrayDeque<?>) o;
            if(size==d.size){
                for(int i=0;i<size;i++){
                    if(!get(i).equals(d.get(i))){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    private class ArrayDequeIterator implements Iterator<T>{
        private int position;
        public ArrayDequeIterator(){
            position=0;
        }
        @Override
        public boolean hasNext() {
            return position<size;
        }
        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T item=get(position);
            position++;
            return item;
        }
    }
    @Override
    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }
    @Override
    public void printDeque() {
        for(int i=0;i<size;i++){
            System.out.print(items[i].toString()+" ");
        }
        System.out.println();
    }
}
