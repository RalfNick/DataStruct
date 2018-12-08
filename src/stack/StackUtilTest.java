package stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-06 下午8:37
 **/
public class StackUtilTest {

    @Test
    public void reverseStack() {

        RStack<Integer> stack = new RStack<>();
        for (int i = 1; i < 6; i++) {
            stack.push(i);
        }
        StackUtil.reverse(stack);

        Assert.assertTrue(!stack.isEmpty());
        System.out.println(stack);

        for (int i = 1; i < 6; i++) {
            Assert.assertEquals(i, stack.pop().intValue());
        }
        Assert.assertTrue(stack.isEmpty());
    }

    @Test
    public void removeStack() {
        RStack<Integer> stack = new RStack<>();
        for (int i = 1; i < 6; i++) {
            stack.push(i);
        }
        Assert.assertTrue(!stack.isEmpty());
        StackUtil.remove(stack, 2);
        Assert.assertTrue(!stack.isEmpty());
        Assert.assertEquals(4, stack.size());
        System.out.println(stack);

        StackUtil.remove(stack, 1);
        Assert.assertTrue(!stack.isEmpty());
        Assert.assertEquals(3, stack.size());
        System.out.println(stack);

        StackUtil.remove(stack, 6);
        Assert.assertTrue(!stack.isEmpty());
        System.out.println(stack);

        StackUtil.remove(stack, 5);
        Assert.assertTrue(!stack.isEmpty());
        Assert.assertEquals(2, stack.size());
        System.out.println(stack);
    }

    @Test
    public void getTopStack() {
        RStack<Integer> stack = new RStack<>();
        for (int i = 1; i < 6; i++) {
            stack.push(i);
        }

        Object[] integers = StackUtil.getTop(stack, 4);
        Assert.assertEquals(5, stack.size());

        for (int i = 0; i < integers.length; i++) {
            Assert.assertEquals(5-i, ((Integer) integers[i]).intValue());
        }
    }

    @Test
    public void isValidPairs(){
        String s1 = "([e{d}f])";
        String s2 = "([b{x]y})";

        Assert.assertTrue(StackUtil.isValidPairs(s1));
        Assert.assertFalse(StackUtil.isValidPairs(s2));

    }
}
