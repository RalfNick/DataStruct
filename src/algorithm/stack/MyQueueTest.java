package algorithm.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-07-17 下午11:47
 **/
public class MyQueueTest {

    @Test
    public void testSize() {

        MyQueue<Integer> queue = new MyQueue<>();
        Assert.assertTrue(queue.empty());
        Assert.assertEquals(0, queue.size());
        for (int i = 0; i < 10; i++) {
            queue.push(i);
            Assert.assertFalse(queue.empty());
            Assert.assertEquals(i + 1, queue.size());
        }
        Assert.assertEquals(10, queue.size());
    }

    @Test
    public void testPoll() {
        MyQueue<Integer> queue = new MyQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.push(i);
        }

        for (int i = 0; i < 10; i++) {
            int peek = queue.peek();
            Assert.assertEquals(i, peek);
            int poll = queue.pop();
            Assert.assertEquals(i, poll);
        }
    }

    @Test
    public void testEnqueueAndDequeue() {

        MyQueue<Integer> queue = new MyQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.push(i);
        }
        Assert.assertEquals(0, queue.pop().intValue());
    }
}
