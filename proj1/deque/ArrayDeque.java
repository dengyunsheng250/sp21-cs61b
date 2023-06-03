package deque;

import java.util.Iterator;

public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int first;
    private int last;
    public ArrayDeque(){
        this.size = 0;
        this.items = (T[])new Object[8];
        this.first = 0;
        this.last = 1;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public void addFirst(T item){
        if(size * 2 >= items.length){
            resize((int)(size * 1.2));
        }
        items[first] = item;
        first = (first - 1 + items.length) % items.length;
        size++;
    }
    public void addLast(T item){
        if(size * 2 >= items.length){
            resize((int)(size * 1.2));
        }
        items[last] = item;
        last = (last + 1) % items.length;
        size++;
    }
    public void printDeque(){
        if(!(first == last)){
            for(int i = first + 1;i < items.length;i++){
                System.out.print(items[i] + " ");
            }
            for(int i = 0;i < last;i++){
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }
    public T removeFirst(){
        if(isEmpty()) return null;
        T item = items[(first + 1) % items.length];
        items[(first + 1) % items.length] = null;
        first = (first + 1) % items.length;
        size--;
        if(size * 3 <= items.length) resize((int)(size * 0.8));
        return item;
    }
    public T removeLast(){
        if(isEmpty()) return null;
        T item = items[(last - 1 + items.length) % items.length];
        items[(last - 1 + items.length) % items.length] = null;
        last = (last - 1 + items.length) % items.length;
        size--;
        if(size * 3 <= items.length) resize((int)(size * 0.8));
        return item;
    }
    public T get(int index){
        if(first + 1 == last){
            return null;
        }
        return items[(first + index + 1) % items.length];
    }
    public Iterator<T> iterator(){
        return new Iterator<T>() {
            private int f = first;
            private int l = last;
            @Override
            public boolean hasNext() {
                return first + 1 !=  last;
            }

            @Override
            public T next() {
                first = (first + 1) % items.length;
                return items[first];
            }
        };
    }
    public boolean equals(Object o){
        if(!(o instanceof ArrayDeque<?>)){
            return false;
        }
        if(!(this.size() == ((ArrayDeque<?>) o).size())){
            return false;
        }
        for(int i = 0;i < items.length;i++){
            if(!(this.items[i] != ((ArrayDeque<?>) o).items[i])){
                return false;
            }
        }
        return true;
    }
    private void resize(int n){
        T[] news = (T[])new Object[n];
        int j = 0, k = items.length - 1;
        for(int i = 0;items[i] != null;i++,j++) news[i] = items[i];
        for(int i = items.length - 1;items[i] != null;i--,k--) news[i] = items[i];
        size = n;
        items = news;
        last = j;
        first = k;
    }
}
