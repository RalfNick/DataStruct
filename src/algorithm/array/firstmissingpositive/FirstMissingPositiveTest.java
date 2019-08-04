package algorithm.array.firstmissingpositive;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-04-25 下午9:31
 **/
public class FirstMissingPositiveTest {

    @Test
    public void test() {
        int[] arr = {3, 4, -1, 1};
        int firstPositive = FirstMissingPositive.findFirstPositive(arr);
        Assert.assertEquals(2, firstPositive);

        int[] arr1 = {7, 8, 9, 11, 12};
        int result = FirstMissingPositive.findFirstPositive(arr1);
        Assert.assertEquals(1, result);
    }
}
