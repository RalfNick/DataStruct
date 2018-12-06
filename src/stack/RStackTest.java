package stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-05 下午9:53
 **/
public class RStackTest {

    RStack<Integer> rStack;

    @Before
    public void init() {
        rStack = new RStack<>(10);

        for (int i = 0; i < 10; i++) {
            rStack.push(i);
        }
    }

    @Test
    public void isEmpty(){

        Assert.assertFalse(rStack.isEmpty());

        RStack<Integer> stack = new RStack<>();
        Assert.assertTrue(stack.isEmpty());
    }

    @Test
    public void isFull(){
        Assert.assertTrue(rStack.isFull());

        RStack<Integer> stack = new RStack<>();
        Assert.assertFalse(stack.isFull());
    }

    @Test
    public void pop(){

        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(10 - i - 1,rStack.peek().intValue());
            Assert.assertEquals(10 - i - 1,rStack.pop().intValue());
        }
    }

    @Test
    public void size(){

        RStack<Integer> stack = new RStack<>();

        for (int i = 0; i < 10; i++) {
            Assert.assertTrue(stack.push(i));
            Assert.assertEquals(i+1,stack.size());
        }

        Assert.assertFalse(stack.push(10));
        System.out.println(stack.toString());
    }

    @Test
    public void pushExt(){

        for (int i = 10; i < 20; i++) {
            Assert.assertTrue(rStack.pushExt(i));
            Assert.assertEquals(i+1,rStack.size());
        }
        System.out.println(rStack.toString());
    }

}
