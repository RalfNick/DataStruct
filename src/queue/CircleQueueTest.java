package queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-10 下午7:34
 **/
public class CircleQueueTest {

    @Test
    public void testSize() {

        CircleQueue<Integer> queue = new CircleQueue<>();
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
            Assert.assertFalse(queue.isEmpty());
            Assert.assertEquals(i + 1, queue.size());
        }
        Assert.assertEquals(10, queue.size());
        Assert.assertTrue(queue.isFull());
    }

    @Test
    public void testPoll() {
        CircleQueue<Integer> queue = new CircleQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }

        for (int i = 0; i < 10; i++) {
            int peek = queue.peek();
            Assert.assertEquals(i,peek);
            int poll = queue.deQueue();
            Assert.assertEquals(i,poll);
        }
    }
}
