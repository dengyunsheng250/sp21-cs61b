package deque;
import org.junit.Test;
import static org.junit.Assert.*;


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
}
