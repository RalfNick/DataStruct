package heap;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-03-21 下午5:47
 **/
public class PriorityQueueTest {

    @Test
    public void test() throws Exception {
        PriorityQueue priorityqueue = new PriorityQueue(10);
        for (int i = 0; i < 10; i++) {
            priorityqueue.enQueue(i);
        }
        System.out.println(priorityqueue.toString());

        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(9 - i,priorityqueue.deQueue());
        }
    }
}
