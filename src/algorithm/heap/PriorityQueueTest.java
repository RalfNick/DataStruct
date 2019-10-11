package algorithm.heap;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-09-05 下午8:33
 **/
public class PriorityQueueTest {

    @Test
    public void testInsert() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(10);
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
            Assert.assertEquals(i, queue.peek().intValue());
        }
        Assert.assertEquals(10, queue.size());

        for (int i = 9; i >= 0; i--) {
            Assert.assertEquals(i, queue.deQueue().intValue());
        }

        Assert.assertEquals(0, queue.size());
    }
}
