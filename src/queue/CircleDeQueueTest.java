package queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-08-05 下午12:35
 **/
public class CircleDeQueueTest {

    private CircleDeQueue<Integer> deQueue;

    @Before
    public void initQueue() {
        deQueue = new CircleDeQueue<>(5);
        Assert.assertTrue(deQueue.enQueque(1));
        Assert.assertTrue(deQueue.enQueque(2));
        Assert.assertTrue(deQueue.enQueque(3));
        Assert.assertTrue(deQueue.enQueque(4));
        Assert.assertTrue(deQueue.enQueque(5));
    }

    @Test
    public void testEnqueue() {
        CircleDeQueue<Integer> deQueue = new CircleDeQueue<>(5);
        Assert.assertEquals(0, deQueue.size());
        Assert.assertTrue(deQueue.enQueque(1));
        Assert.assertEquals(1, deQueue.size());
        Assert.assertTrue(deQueue.enQueque(2));
        Assert.assertEquals(2, deQueue.size());
        Assert.assertTrue(deQueue.enQueque(3));
        Assert.assertEquals(3, deQueue.size());
        Assert.assertTrue(deQueue.enQueque(4));
        Assert.assertEquals(4, deQueue.size());
        Assert.assertTrue(deQueue.enQueque(5));
        Assert.assertEquals(5, deQueue.size());
        Assert.assertFalse(deQueue.enQueque(6));
        Assert.assertEquals(5, deQueue.size());
    }

    @Test
    public void testInsertFront() {
        CircleDeQueue<Integer> deQueue = new CircleDeQueue<>(5);
        Assert.assertEquals(0, deQueue.size());
        Assert.assertTrue(deQueue.insertFront(5));
        Assert.assertEquals(1, deQueue.size());
        Assert.assertTrue(deQueue.insertFront(4));
        Assert.assertEquals(2, deQueue.size());
        Assert.assertTrue(deQueue.insertFront(3));
        Assert.assertEquals(3, deQueue.size());
        Assert.assertTrue(deQueue.insertFront(2));
        Assert.assertEquals(4, deQueue.size());
        Assert.assertTrue(deQueue.insertFront(1));
        Assert.assertEquals(5, deQueue.size());
        Assert.assertFalse(deQueue.insertFront(6));
        Assert.assertEquals(5, deQueue.size());
    }

    @Test
    public void testInsertLast() {
        CircleDeQueue<Integer> deQueue = new CircleDeQueue<>(5);
        Assert.assertEquals(0, deQueue.size());
        Assert.assertTrue(deQueue.insertLast(1));
        Assert.assertEquals(1, deQueue.size());
        Assert.assertTrue(deQueue.insertLast(2));
        Assert.assertEquals(2, deQueue.size());
        Assert.assertTrue(deQueue.insertLast(3));
        Assert.assertEquals(3, deQueue.size());
        Assert.assertTrue(deQueue.insertLast(4));
        Assert.assertEquals(4, deQueue.size());
        Assert.assertTrue(deQueue.insertLast(5));
        Assert.assertEquals(5, deQueue.size());
        Assert.assertFalse(deQueue.insertLast(6));
        Assert.assertEquals(5, deQueue.size());
    }

    @Test
    public void testDequeue() {
        Assert.assertTrue(deQueue.isFull());
        Assert.assertEquals(5, deQueue.size());
        Assert.assertEquals(1, deQueue.getFront().intValue());
        Assert.assertEquals(1, deQueue.deQueue().intValue());
        Assert.assertEquals(4, deQueue.size());
        Assert.assertEquals(2, deQueue.getFront().intValue());
        Assert.assertEquals(2, deQueue.deQueue().intValue());
        Assert.assertEquals(3, deQueue.size());
        Assert.assertEquals(3, deQueue.getFront().intValue());
        Assert.assertEquals(3, deQueue.deQueue().intValue());
        Assert.assertEquals(2, deQueue.size());
        Assert.assertEquals(4, deQueue.getFront().intValue());
        Assert.assertEquals(4, deQueue.deQueue().intValue());
        Assert.assertEquals(1, deQueue.size());
        Assert.assertEquals(5, deQueue.getFront().intValue());
        Assert.assertEquals(5, deQueue.deQueue().intValue());
        Assert.assertEquals(0, deQueue.size());
        Assert.assertTrue(deQueue.isEmpty());
    }

    @Test
    public void testDeleteFront() {
        Assert.assertTrue(deQueue.isFull());
        Assert.assertEquals(5, deQueue.size());
        Assert.assertEquals(1, deQueue.getFront().intValue());
        Assert.assertEquals(1, deQueue.deleteFront().intValue());
        Assert.assertEquals(4, deQueue.size());
        Assert.assertEquals(2, deQueue.getFront().intValue());
        Assert.assertEquals(2, deQueue.deleteFront().intValue());
        Assert.assertEquals(3, deQueue.size());
        Assert.assertEquals(3, deQueue.getFront().intValue());
        Assert.assertEquals(3, deQueue.deleteFront().intValue());
        Assert.assertEquals(2, deQueue.size());
        Assert.assertEquals(4, deQueue.getFront().intValue());
        Assert.assertEquals(4, deQueue.deleteFront().intValue());
        Assert.assertEquals(1, deQueue.size());
        Assert.assertEquals(5, deQueue.getFront().intValue());
        Assert.assertEquals(5, deQueue.deleteFront().intValue());
        Assert.assertEquals(0, deQueue.size());
        Assert.assertTrue(deQueue.isEmpty());
    }

    @Test
    public void testDeleteLast() {
        Assert.assertTrue(deQueue.isFull());
        Assert.assertEquals(5, deQueue.size());
        Assert.assertEquals(5, deQueue.getRear().intValue());
        Assert.assertEquals(5, deQueue.deleteLast().intValue());
        Assert.assertEquals(4, deQueue.size());
        Assert.assertEquals(4, deQueue.getRear().intValue());
        Assert.assertEquals(4, deQueue.deleteLast().intValue());
        Assert.assertEquals(3, deQueue.size());
        Assert.assertEquals(3, deQueue.getRear().intValue());
        Assert.assertEquals(3, deQueue.deleteLast().intValue());
        Assert.assertEquals(2, deQueue.size());
        Assert.assertEquals(2, deQueue.getRear().intValue());
        Assert.assertEquals(2, deQueue.deleteLast().intValue());
        Assert.assertEquals(1, deQueue.size());
        Assert.assertEquals(1, deQueue.getRear().intValue());
        Assert.assertEquals(1, deQueue.deleteLast().intValue());
        Assert.assertEquals(0, deQueue.size());
        Assert.assertTrue(deQueue.isEmpty());
    }
}
