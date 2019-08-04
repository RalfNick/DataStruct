package algorithm.array.findmid;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-05-04 下午8:11
 **/
public class FindMidTwoSortedArrayTest {

    @Test
    public void test1() {
        int arr1[] = {1, 3};
        int[] arr2 = {2};
        double result = FindMidTwoSortedArray.findMidOfTwoSortedArray(arr1, arr2);
        Assert.assertEquals(2.0, result, 0.1);
    }

    @Test
    public void test2() {
        int arr1[] = {1, 2};
        int[] arr2 = {3, 4};
        double result = FindMidTwoSortedArray.findMidOfTwoSortedArray(arr1, arr2);
        Assert.assertEquals(2.5, result, 0.0);
    }
}
