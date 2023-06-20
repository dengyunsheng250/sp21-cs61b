package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> {
    private Comparator c;
    private int size;
    private T[] items;
    private int first;
    private int last;
    public MaxArrayDeque(Comparator<T> c){
        this.c = c;
    }
    public T max(){
        if(isEmpty()){
            return null;
        }

    }

    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
    }

    public int size(){
        return size;
    }
    public Boolean isEmpty(){
        return size() == 0;
    }

}
