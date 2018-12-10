package queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-10 下午7:34
 **/
public class RArrayQueueTest {

    @Test
    public void testSize() {

        RArrayQueue<Integer> queue = new RArrayQueue<>();
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());
        for (int i = 0; i < 10; i++) {
            queue.push(i);
            Assert.assertFalse(queue.isEmpty());
            Assert.assertEquals(i + 1, queue.size());
        }
        Assert.assertTrue(queue.isFull());
    }

    @Test
    public void testPoll() {
        RArrayQueue<Integer> queue = new RArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.push(i);
        }

        for (int i = 0; i < 10; i++) {
            int peek = queue.peek();
            Assert.assertEquals(i,peek);
            int poll = queue.poll();
            Assert.assertEquals(i,poll);
        }
    }
}
