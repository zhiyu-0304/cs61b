package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    public static void main(String[] args) {

    }
    @Test
    public void testThreeAddThreeRemove(){
        BuggyAList<Integer> list1=new BuggyAList<>();
        AListNoResizing<Integer> list2=new AListNoResizing<>();
        for(int i=4;i<=6;i++){
            list1.addLast(i);
            list2.addLast(i);
        }
        assertEquals(list1.size(),list2.size());
        for(int i=0;i<3;i++){
            assertEquals(list1.removeLast(),list2.removeLast());
        }
    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L1=new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L1.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size1 = L.size();
                int size2 = L1.size();
                assertEquals(size1, size2);
            } else if (operationNumber == 2) {
                if(L.size()<=0)
                    continue;
                assertEquals(L1.getLast(),L1.getLast());
            } else if (operationNumber == 3) {
                if(L.size()<=0)
                    continue;
                int result1=L.removeLast();
                int result2=L1.removeLast();
                assertEquals(result1,result2);
            }
        }
    }
}
