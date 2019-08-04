package algorithm.array.mergearray;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-05-04 下午8:22
 **/
public class MergeTwoArrayTest {

    @Test
    public void test() {
        int[] arr1 = {1, 2, 3, 0, 0, 0};
        int[] arr2 = {2, 5, 6};

        int[] result = {1, 2, 2, 3, 5, 6};
        MergeTwoArray.mergeTwoSortedArray1(arr1, 3, arr2, 3);
        Assert.assertArrayEquals(result, arr1);
    }
}
