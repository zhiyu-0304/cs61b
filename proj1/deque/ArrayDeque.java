package deque;

public class ArrayDeque<T>{
    private T[] items;
    private int first;
    private int last;
    private int size;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        first = 0;
        last = 0;
        size=0;
    }
    private void resize(int s){
        T[] temp = (T[]) new Object[s];
        for(int i=0;i<size;i++){
            temp[i]=get(i);
        }
        first=0;
        last=size-1;
        items=temp;
    }
    private boolean isFull(){
        return size==items.length;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void addFirst(T item){
        if(isFull()){
            resize(items.length*2);
        }
        if (size!=0){
            first=(first-1+items.length)%items.length;
        }
        items[first]=item;
        size++;
    }
    public void addLast(T item){
        if (isFull()) {
            resize(items.length*2);
        }
        if(size!=0){
            last=(last+1)%items.length;
        }
        items[last]=item;
        size++;
    }
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T item=items[first];
        items[first]=null;
        first++;
        first=(first+ items.length)%items.length;
        size--;
        if(size>=4&&size*4<=items.length){
            resize(items.length/2);
        }
        return item;
    }
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T item=items[last];
        items[last]=null;
        last--;
        last=(last+ items.length)%items.length;
        size--;
        if(size>=4&&size*4<=items.length){
            resize(items.length/2);
        }
        return item;
    }
    public int size(){
        return size;
    }
    public T get(int index){
        if(index<0 || index>=size){
            return null;
        }
        return items[(index+first+items.length)%items.length];
    }
    public boolean equals(Object o){
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
}
