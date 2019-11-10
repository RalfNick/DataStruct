package algorithm.bit;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-11-10 上午9:34
 **/
public class BitTest {

    @Test
    public void testBinaryBit() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> list = Bit.binaryBit(nums);
        Assert.assertEquals(8, list.size());
        Assert.assertEquals(8, Bit.binaryBit1(nums).size());
        Assert.assertEquals(8, Bit.binaryBit2(nums).size());
    }

    @Test
    public void testSingleNumber() {
        Assert.assertEquals(1, Bit.singleNumber(new int[]{2, 2, 1}));
        Assert.assertEquals(4, Bit.singleNumber(new int[]{4, 1, 2, 1, 2}));

        Assert.assertEquals(1, Bit.singleNumber1(new int[]{2, 2, 1}));
        Assert.assertEquals(4, Bit.singleNumber1(new int[]{4, 1, 2, 1, 2}));
    }

    @Test
    public void testSingleNumber2() {
        Assert.assertEquals(3, Bit.singleNumber2(new int[]{2, 2, 3, 2}));
        Assert.assertEquals(99, Bit.singleNumber2(new int[]{0, 1, 0, 1, 0, 1, 99}));

        Assert.assertEquals(3, Bit.singleNumber21(new int[]{2, 2, 3, 2}));
        Assert.assertEquals(99, Bit.singleNumber21(new int[]{0, 1, 0, 1, 0, 1, 99}));

        Assert.assertEquals(3, Bit.singleNumber22(new int[]{2, 2, 3, 2}));
        Assert.assertEquals(99, Bit.singleNumber22(new int[]{0, 1, 0, 1, 0, 1, 99}));

        Assert.assertEquals(3, Bit.singleNumber23(new int[]{2, 2, 3, 2}));
        Assert.assertEquals(99, Bit.singleNumber23(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }

    @Test
    public void testIsPowerOfTwo() {
        Assert.assertTrue(Bit.isPowerOfTwo(1));
        Assert.assertTrue(Bit.isPowerOfTwo(16));
        Assert.assertFalse(Bit.isPowerOfTwo(218));

        Assert.assertTrue(Bit.isPowerOfTwo1(1));
        Assert.assertTrue(Bit.isPowerOfTwo1(16));
        Assert.assertFalse(Bit.isPowerOfTwo1(218));
    }

    @Test
    public void testIsPowerOfFour() {
        Assert.assertTrue(Bit.isPowerOfFour(1));
        Assert.assertTrue(Bit.isPowerOfFour(16));
        Assert.assertFalse(Bit.isPowerOfFour(5));
        Assert.assertFalse(Bit.isPowerOfFour(6));
    }

    @Test
    public void testSingleNumber3() {
        int[] arr = {1, 2, 1, 3, 2, 5};
        int[] ints = Bit.singleNumber3(arr);
        Assert.assertEquals(2, ints.length);
        Assert.assertEquals(5, ints[0]);
        Assert.assertEquals(3, ints[1]);
    }
}
