package Lista5.zad1;

import org.junit.Before;
import org.junit.Test;

public class HeapTest {
    PriorityQueue heap;

    @Before
    public void setUp(){
        heap = new PriorityQueue();
        heap.insert(4,7);
                heap.insert(1, 1);
                heap.insert(9,12);
                heap.insert(7,11);
                heap.insert(7,10);
                heap.insert(12,55);
                heap.insert(13,61);
                heap.insert(8,12);
                heap.insert(11,35);
                heap.insert(14,64);
                heap.insert(10,23);
                heap.insert(5,8);
                heap.insert(15,77);
                heap.insert(2,2);
                heap.insert(3,2);
                    }
    @Test
    public void Insert(){
        heap.priority(1,100);
        heap.pop();
                heap.pop();
                heap.pop();
        heap.print();heap.pop();
        heap.print();heap.pop();
                heap.pop();
        heap.print(); heap.pop();
        heap.print(); heap.pop();
        heap.print(); heap.pop();
        heap.print(); heap.pop();
        heap.print(); heap.pop();
                heap.pop();
        heap.print(); heap.pop();
        heap.print(); heap.pop();
        heap.print(); heap.pop();
            }
}
