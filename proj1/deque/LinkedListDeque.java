package deque;

public class LinkedListDeque<T> {
    private class Node{
        private T item;
        private Node next;
        private Node prev;
        public Node(T item, Node next, Node prev){
            this.item=item;
            this.next=next;
            this.prev=prev;
        }
    }
    private Node sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel=new Node(null,null,null);
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
        size=0;
    }
    //get the ith item of the list
    public T get(int index){
        Node node=this.sentinel.next;
        if(node==sentinel){
            return null;
        }
        for(int i=0;i<index;i++){
            if(node==sentinel){
                return null;
            }
            node=node.next;
        }
        return node.item;
    }
    //add an item to the first location in the list
    public void addFirst(T item){
        Node next=sentinel.next;
        sentinel.next=new Node(item,next,sentinel);
        next.prev=sentinel.next;
        size++;
    }
    public void addLast(T item){
        Node prev=sentinel.prev;
        sentinel.prev=new Node(item,sentinel,prev);
        prev.next=sentinel.prev;
        size++;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size() {
        return size;
    }
    public void printDeque(){
        Node curr=sentinel.next;
        while(curr!=sentinel){
            System.out.print(curr.item+" ");
            curr=curr.next;
        }
        System.out.println();
    }
    public T removeFirst(){
        Node node=sentinel.next;
        if(node==sentinel){
            return null;
        }
        sentinel.next=node.next;
        node.next.prev=sentinel;
        T temp=node.item;
        node=null;
        size--;
        return temp;
    }
    public T removeLast(){
        Node node=sentinel.prev;
        if(node==sentinel){
            return null;
        }
        sentinel.prev=node.prev;
        node.prev.next=sentinel;
        T temp=node.item;
        node=null;
        size--;
        return temp;
    }
    public boolean equals(Object o){
        if(o instanceof LinkedListDeque){
            LinkedListDeque<?> lld=(LinkedListDeque<?>)o;
            if(size==lld.size){
                Node curr1=sentinel.next;
                Node curr2= (Node) lld.sentinel.next;
                for(int i=0;i<size;i++){
                    if(curr1.item!=curr2.item){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
