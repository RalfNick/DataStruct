package algorithm.array.singlenumber;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-05-14 上午10:14
 **/
public class SingleNumberTest {

    @Test
    public void test1() {
        int[] arr = {2, 2, 1};
        Assert.assertEquals(1, SingleNumber.findSingleNumber1(arr));

        int[] arr1 = {4, 1, 2, 1, 2};
        Assert.assertEquals(4, SingleNumber.findSingleNumber1(arr1));
    }

    @Test
    public void test2() {
        int[] arr = {2, 2, 3, 2};
        Assert.assertEquals(3, SingleNumber.findSingleNumber2(arr));

        int[] arr1 = {0, 1, 0, 1, 0, 1, 99};
        Assert.assertEquals(99, SingleNumber.findSingleNumber2(arr1));
    }

    @Test
    public void test3() {
        int[] arr = {2, 2, 3, 2};
        Assert.assertEquals(3, SingleNumber.findSingleNumber21(arr));

        int[] arr1 = {0, 1, 0, 1, 0, 1, 99};
        Assert.assertEquals(99, SingleNumber.findSingleNumber21(arr1));
    }
}
