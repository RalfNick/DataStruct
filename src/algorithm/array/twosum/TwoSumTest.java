package algorithm.array.twosum;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-04-17 上午8:46
 **/
public class TwoSumTest {

    @Test
    public void twoSumTest1() {
        int[] arr = {2, 7, 11, 15};
        int target = 9;
        int[] result = {0, 1};
        Assert.assertArrayEquals(result, TwoSum.findTwoSumIndex1(arr, target));
    }

    @Test
    public void twoSumTest2() {
        int[] arr = {2, 7, 11, 15};
        int target = 9;
        int[] result = {0, 1};
        Assert.assertArrayEquals(result, TwoSum.findTwoSumIndex2(arr, target));
    }
}
