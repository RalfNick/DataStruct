package algorithm.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-10-27 上午9:09
 **/
public class ArrayUtilTest {

    @Test
    public void testMaxArea() {
        Assert.assertEquals(49, ArrayUtil.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
