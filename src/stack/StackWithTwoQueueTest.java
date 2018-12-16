package stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-16 上午10:46
 **/
public class StackWithTwoQueueTest<T> {

    private StackWithTwoQueue<Integer> mStack = new StackWithTwoQueue<>();

    @Before
    public void init() {
        for (int i = 0; i < 10; i++) {
            mStack.push(i);
        }
    }

    @Test
    public void isEmpty() {
        StackWithTwoQueue<Integer> stack = new StackWithTwoQueue<>();
        Assert.assertTrue(stack.isEmpty());
    }

    @Test
    public void isFull() {
        StackWithTwoQueue<Integer> stack = new StackWithTwoQueue<>(10);
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        Assert.assertEquals(10, stack.size());
        Assert.assertTrue(stack.isFull());
    }

    @Test
    public void pop() {

        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(10 - i - 1, mStack.peek().intValue());
            Assert.assertEquals(10 - i - 1, mStack.pop().intValue());
        }
    }

    @Test
    public void pushAndPop() {

        Assert.assertFalse(mStack.push(10));

        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(10 - i - 1, mStack.pop().intValue());
        }
        Assert.assertEquals(5,mStack.size());

        for (int i = 5; i < 10; i++) {
            Assert.assertTrue(mStack.push(i));
        }

        Assert.assertEquals(10, mStack.size());
        Assert.assertTrue(mStack.isFull());

    }

    @Test
    public void size() {

        StackWithTwoQueue<Integer> stack = new StackWithTwoQueue<>();

        for (int i = 0; i < 10; i++) {
            Assert.assertTrue(stack.push(i));
            Assert.assertEquals(i + 1, stack.size());
        }

        Assert.assertFalse(stack.push(10));
    }

}
