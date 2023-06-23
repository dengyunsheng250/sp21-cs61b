package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> {
    private Comparator c;
    private int size;
    private T[] items;
    private int first;
    private int last;
    public MaxArrayDeque(Comparator<T> c){
        this.size = 0;
        this.items = (T[])new Object[8];
        this.first = 0;
        this.last = 1;
        this.c = c;
    }
    public T max(){
        if(isEmpty()){
            return null;
        }
        return (T) max(this.c);

    }
    public Boolean isEmpty(){
        return size() == 0;
    }
    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
        int f = this.first;
        int l = this.last;
        T res = items[f];
        while((f + 1) % items.length !=  l){
            T item = items[(f + 1) % items.length];
            if(c.compare(item,res) > 0){
                res = item;
            }
        }
        return res;
    }
    public int size(){
        return size;
    }
    public void addFirst(T item){
        items[first] = item;
        first = (first - 1 + items.length) % items.length;
        size++;
        if(size * 2 >= items.length){
            resize((int)(size * 2.2));
        }
    }
    public void addLast(T item){
        items[last] = item;
        last = (last + 1) % items.length;
        size++;
        if(size * 2 >= items.length){
            resize((int)(size * 2.2));
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
        if(size != 0 && size * 8 <= items.length) resize((int)(size * 3));
        return item;
    }
    public T removeLast(){
        if(isEmpty()) return null;
        T item = items[(last - 1 + items.length) % items.length];
        items[((last - 1) % items.length + items.length) % items.length] = null;
        last = ((last - 1) % items.length + items.length) % items.length;
        size--;
        if(size != 0 && size * 8 <= items.length) resize((int)(size * 3));
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
        if(!(o instanceof MaxArrayDeque<?>)){
            return false;
        }
        if(!(this.size() == ((MaxArrayDeque<?>) o).size())){
            return false;
        }
        for(int i = 0;i < items.length;i++){
            if(!(this.items[i] != ((MaxArrayDeque<?>) o).items[i])){
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
