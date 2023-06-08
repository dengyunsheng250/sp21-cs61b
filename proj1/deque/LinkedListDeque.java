package deque;


import java.util.Iterator;

public class LinkedListDeque<T>{
    private class Node{
        public T item;
        public Node next;
        public Node prev;
        public Node(T item,Node next,Node prev){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    private Node head;
    private Node tail;
    private int size;
    public LinkedListDeque(){
        head = new Node(null,null,null);
        tail = new Node(null,null,null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
    public void addFirst(T item){
        Node node = new Node(item,null,null);
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
        size++;
    }
    public void addLast(T item){
        Node node = new Node(item,null,null);
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
        size++;
    }
    public Iterator<T> iterator(){
        return new Iterator<T>() {
            Node node = head;
            @Override
            public boolean hasNext() {
                return node.next != tail;
            }

            @Override
            public T next() {
                node = node.next;
                T item = node.item;
                return item;
            }
        };
    }
    public T getRecursive(int index){
        return getRecursiveNode(head.next,index).item;
    }
    public Node getRecursiveNode(Node node,int index){
        if(index == 0) return node;
        return getRecursiveNode(node.next,index - 1);
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T item = head.next.item;
        head.next.next.prev = head;
        head.next = head.next.next;
        size--;
        return item;
    }
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T item = tail.prev.item;
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
        size--;
        return item;
    }
    public T get(int index) {
        index += 1;
        Node cur = head;
        while(index-->0){
            if(cur == tail){
                return null;
            }
            cur = cur.next;
        }
        return cur.item;
    }
    public boolean equals(Object o){
        if(!(o instanceof LinkedListDeque<?>)){
            return false;
        }
        int size = this.size;
        if(((LinkedListDeque<?>) o).size() != this.size()){
            return false;
        }
        Node node1 = ((LinkedListDeque<T>) o).head.next;
        Node node2 = this.head.next;
        while(size-->0){
            if(node1.item != node2.item){
                return false;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        return true;
    }
    public void printDeque(){
        Node node = this.head.next;
        while(node != tail){
            System.out.println(node.item);
            if(node.next != tail) System.out.print(" ");
            node = node.next;
        }
        System.out.println();
    }
}
