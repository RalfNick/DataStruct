package algorithm.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-07-12 上午10:52
 **/
public class MiniStackTest {

    @Test
    public void testMiniStack() {

        MiniStack<Integer> miniStack = new MiniStack<>();
        miniStack.push(-2);
        Assert.assertFalse(miniStack.isEmpty());
        Assert.assertEquals(-2, miniStack.getMin().intValue());
        Assert.assertEquals(-2, miniStack.getTop().intValue());

        miniStack.push(0);
        Assert.assertFalse(miniStack.isEmpty());
        Assert.assertEquals(-2, miniStack.getMin().intValue());
        Assert.assertEquals(0, miniStack.getTop().intValue());

        miniStack.push(-3);
        Assert.assertFalse(miniStack.isEmpty());
        Assert.assertEquals(-3, miniStack.getMin().intValue());
        Assert.assertEquals(-3, miniStack.getTop().intValue());

        int data = miniStack.pop();
        Assert.assertFalse(miniStack.isEmpty());
        Assert.assertEquals(-2, miniStack.getMin().intValue());
        Assert.assertEquals(0, miniStack.getTop().intValue());
        Assert.assertEquals(-3, data);

        data = miniStack.pop();
        Assert.assertFalse(miniStack.isEmpty());
        Assert.assertEquals(-2, miniStack.getMin().intValue());
        Assert.assertEquals(-2, miniStack.getTop().intValue());
        Assert.assertEquals(0, data);

        data = miniStack.pop();
        Assert.assertTrue(miniStack.isEmpty());
        Assert.assertEquals(null, miniStack.getMin());
        Assert.assertEquals(-2, data);
    }
}
