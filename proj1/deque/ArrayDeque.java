package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>{
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
    public void addFirst(T item){
        items[first] = item;
        first = (first - 1 + items.length) % items.length;
        size++;
        if(size * 2 >= items.length){
            resize((int)(size * 1.2));
        }
    }
    public void addLast(T item){
        items[last] = item;
        last = (last + 1) % items.length;
        size++;
        if(size * 2 >= items.length){
            resize((int)(size * 1.2));
        }
    }
    public void printDeque(){
        if(isEmpty()) return;
        if(first > last){
            for(int i = first + 1;i < items.length;i++){
                System.out.print(items[i] + " ");
            }
            for(int i = 0;i < last;i++){
                System.out.print(items[i] + " ");
            }
        }
        else{
            for(int i = first + 1;i < last;i++){
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
        if(size != 0 && size * 4 <= items.length) resize((int)(size * 1.5));
        return item;
    }
    @Override
    public T removeLast(){
        if(isEmpty()) return null;
        T item = items[(last - 1 + items.length) % items.length];
        items[((last - 1) % items.length + items.length) % items.length] = null;
        last = ((last - 1) % items.length + items.length) % items.length;
        size--;
        if(size != 0 && size * 4 <= items.length) resize((int)(size * 1.5));
        return item;
    }
    public T get(int index){
        return items[(first + index + 1) % items.length];
    }
    public Iterator<T> iterator(){
        return new Iterator<T>() {
            private int f = first;
            private int l = last;
            @Override
            public boolean hasNext() {
                return (f + 1) % items.length !=  l;
            }

            @Override
            public T next() {
                f = (f + 1) % items.length;
                return items[f];
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
        int j = 0;
        for(int i = first + 1;i < items.length;i++){
            if(items[i] != null){
                news[j] = items[i];
                items[i] = null;
                j++;
            }
        }
        for(int i = 0;i < last;i++){
            if(items[i] != null){
                news[j] = items[i];
                items[i] = null;
                j++;
            }
        }
        first = news.length - 1;
        last = j;
        items = news;
    }
}
