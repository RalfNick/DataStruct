package algorithm.array.removeduplicate;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-04-30 下午8:26
 **/
public class RemoveDuplicateTest {

    @Test
    public void test() {
        int[] arr = {3, 2, 2, 3};
        int result = RemoveDuplicate.removeDuplicate(arr, 3);
        Assert.assertArrayEquals(arr, new int[]{2, 2, 2, 3});
        Assert.assertEquals(2, result);

        int[] arr1 = {0, 1, 2, 2, 3, 0, 4, 2};
        int result1 = RemoveDuplicate.removeDuplicate(arr1, 2);
        Assert.assertArrayEquals(arr1, new int[]{0, 1, 3, 0, 4, 0, 4, 2});
        Assert.assertEquals(5, result1);
    }

    @Test
    public void test1() {
        int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int result = RemoveDuplicate.removeDuplicateOfSort(arr);
        Assert.assertArrayEquals(arr, new int[]{0, 1, 2, 3, 4, 2, 2, 3, 3, 4});
        Assert.assertEquals(5, result);

        int[] arr1 = {1, 1, 2};
        int result1 = RemoveDuplicate.removeDuplicateOfSort(arr1);
        Assert.assertArrayEquals(arr1, new int[]{1, 2, 2});
        Assert.assertEquals(2, result1);
    }
}
