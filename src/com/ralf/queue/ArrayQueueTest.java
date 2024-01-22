package com.ralf.queue;

import org.junit.Assert;
import org.junit.Test;

public class ArrayQueueTest {

    @Test
    public void testSize() {

        Queue<Integer> queue = new ArrayQueue<>(10);
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
            Assert.assertFalse(queue.isEmpty());
            Assert.assertEquals(i + 1, queue.size());
        }
        Assert.assertEquals(10, queue.size());
    }

    @Test
    public void testPoll() {
        ArrayQueue<Integer> queue = new ArrayQueue<>(16);
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

        ArrayQueue<Integer> queue = new ArrayQueue<>(10);
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        Assert.assertFalse(queue.enQueue(10));

        Assert.assertEquals(0,queue.deQueue().intValue());
        queue.enQueue(10);
    }

}
