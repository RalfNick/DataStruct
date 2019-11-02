package queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-15 下午9:01
 **/
public class RArrayQueueTest {


    @Test
    public void testSize() {

        RArrayQueue<Integer> queue = new RArrayQueue<>();
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
        RArrayQueue<Integer> queue = new RArrayQueue<>();
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

    @Test
    public void testEnqueueAndDequeue(){

        RArrayQueue<Integer> queue = new RArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        Assert.assertFalse(queue.enQueue(10));

        Assert.assertEquals(0,queue.deQueue().intValue());
        queue.enQueue(10);
        Assert.assertTrue(queue.isFull());

    }

}
