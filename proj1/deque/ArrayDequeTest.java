package deque;
import org.junit.Test;

import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;

public class ArrayDequeTest {
    @Test
    public void testadd(){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.addFirst(1);
        q.addFirst(2);
        q.addFirst(3);
        q.removeFirst();
        q.removeLast();
        q.removeLast();
        assertTrue(q.size() == 0);
    }
    @Test
    public void testArrayList(){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i = 0;i < 10;i++){
            int op = StdRandom.uniform(0,3);
            if (op == 0){
                q.addFirst(i);
            }else if(op == 1){
                q.removeFirst();
            }else{
                System.out.println(q.get(0));
            }
        }
    }
}
