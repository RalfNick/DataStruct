package algorithm.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-07-17 下午11:41
 **/
public class MyStackTest {

    private MyStack<Integer> mStack = new MyStack<>();

    @Before
    public void init() {
        for (int i = 0; i < 10; i++) {
            mStack.push(i);
        }
    }

    @Test
    public void isEmpty() {
        MyStack<Integer> stack = new MyStack<>();
        Assert.assertTrue(stack.empty());
    }


    @Test
    public void pop() {
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(10 - i - 1, mStack.top().intValue());
            Assert.assertEquals(10 - i - 1, mStack.pop().intValue());
        }
    }

    @Test
    public void pushAndPop() {
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(10 - i - 1, mStack.pop().intValue());
        }
        Assert.assertEquals(5,mStack.size());
        for (int i = 5; i < 10; i++) {
            mStack.push(i);
        }
        Assert.assertEquals(10, mStack.size());
    }

    @Test
    public void size() {
        MyStack<Integer> stack = new MyStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            Assert.assertEquals(i + 1, stack.size());
        }
    }

}
