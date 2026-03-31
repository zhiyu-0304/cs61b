package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void randomTestArrayDeque() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();

        int N=5000;
        for (int i = 0; i < N; i += 1) {
            Integer choiceNum = StdRandom.uniform(0,4);

            if (choiceNum == 0) {
                Integer temp = StdRandom.uniform(0,100);
                System.out.println("addLast("+temp+")");
                sad1.addLast(temp);
                sad2.addLast(temp);
            } else if (choiceNum == 1) {
                Integer temp = StdRandom.uniform(0,100);
                System.out.println("addFirst("+temp+")");
                sad1.addFirst(temp);
                sad2.addFirst(temp);
            }else if (choiceNum == 2) {
                assertEquals(sad2.size(), sad1.size());
                if(sad1.size() == 0){
                    continue;
                }
                else{
                    System.out.println("removeFirst()");
                    assertEquals(sad2.removeFirst(), sad1.removeFirst());
                }
            }
            else {
                assertEquals(sad2.size(), sad1.size());
                if(sad1.size() == 0){
                    continue;
                }
                else{
                    System.out.println("removeLast()");
                    assertEquals(sad2.removeLast(), sad1.removeLast());
                }
            }
        }

    }
}
