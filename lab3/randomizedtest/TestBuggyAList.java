package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/*
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
         AListNoResizing<Integer> l1 = new AListNoResizing<>();
         BuggyAList<Integer> l2 = new BuggyAList<>();
         l1.addLast(4);
         l1.addLast(5);
         l1.addLast(6);
         l2.addLast(4);
         l2.addLast(5);
         l2.addLast(6);
         assertTrue(l1.removeLast() == l2.removeLast());
         assertTrue(l1.removeLast() == l2.removeLast());
         assertTrue(l1.removeLast() == l2.removeLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L  = new AListNoResizing<>();
        BuggyAList<Integer> R = new BuggyAList<>();
        int N = 5000;
        for(int i = 0;i < N;i++){
            int operationNumber = StdRandom.uniform(0,3);
            if(operationNumber == 0){
                int randVal = StdRandom.uniform(0,100);
                L.addLast(randVal);
                R.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            }else if(operationNumber == 1){
                int size1 = L.size();
                int size2 = R.size();
                assertEquals(size1,size2);
            }else if(operationNumber == 2){
                if(L.size() > 0){
                    assertEquals(L.getLast(),R.getLast());
                    Integer x = L.removeLast();
                    Integer y = R.removeLast();
                    assertEquals(x,y);
                }
            }
        }
    }
}
