package algorithm.array.findklargestelement;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-05-10 上午8:05
 **/
public class FindKthLargestElementTest {

    @Test
    public void test1() {
        int[] arr = {3,2,1,5,6,4};
        int result = FindKthLargestElement.findKthLargestElement(arr,2);
        Assert.assertEquals(5,result);

        int[] arr1 = {3,2,3,1,2,4,5,5,6};
        int result1 = FindKthLargestElement.findKthLargestElement(arr1,4);
        Assert.assertEquals(4,result1);
    }

    @Test
    public void test2() {
        int[] arr = {3,2,1,5,6,4};
        int result = FindKthLargestElement.findKthLargestElement1(arr,2);
        Assert.assertEquals(5,result);

        int[] arr1 = {3,2,3,1,2,4,5,5,6};
        int result1 = FindKthLargestElement.findKthLargestElement1(arr1,4);
        Assert.assertEquals(4,result1);
    }
}
