package List.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-11-27 上午11:03
 **/
public class RArrayListUtilTest {

    int[] arr1;
    int[] arr2;

    @Before
    public void initArr() {
        arr1 = new int[]{7, 9, 30, 3};
        arr2 = new int[]{7, 9, 30, 3, 4};
    }

    @Test
    public void reverseArray() {
        RArrayListUtil.reverseArray(arr1);
        Assert.assertArrayEquals(new int[]{3, 30, 9, 7}, arr1);

        RArrayListUtil.reverseArray(arr2);
        Assert.assertArrayEquals(new int[]{4, 3, 30, 9, 7}, arr2);
    }

    @Test
    public void removeZero() {

        int[] arr = new int[]{0, 7, 0, 9, 0, 30, 3, 0};
        int[] result = RArrayListUtil.removeZero(arr);
        Assert.assertEquals(4, result.length);
        Assert.assertArrayEquals(new int[]{7, 9, 30, 3}, result);
    }

    @Test
    public void merge() {
        int[] a1 = {3, 5, 7, 8};
        int[] a2 = {4, 5, 6, 7};
        // a3 为[3,4,5,6,7,8]

        int[] result = RArrayListUtil.merge(a1, a2);
        Assert.assertEquals(6, result.length);
        Assert.assertArrayEquals(new int[]{3, 4, 5, 6, 7, 8}, result);
    }

    @Test
    public void grow() {

        String[] strings = new String[]{"Hello", "World", "!"};
        Object[] grow = RArrayListUtil.grow(strings, 5);
        Assert.assertEquals(8, grow.length);
        Assert.assertEquals("Hello", grow[0]);
        Assert.assertEquals("World", grow[1]);
        Assert.assertEquals("!", grow[2]);
        for (int i = 3; i < grow.length; i++) {
            Assert.assertNull(grow[i]);
        }
    }

    @Test
    public void fibonacci() {

        int[] fibonacci = RArrayListUtil.fibonacci(15);
        Assert.assertEquals(7, fibonacci.length);
        Assert.assertArrayEquals(new int[]{1, 1, 2, 3, 5, 8, 13}, fibonacci);
    }

    @Test
    public void getPrimes() {

        int[] primes = RArrayListUtil.getPrimes(23);
        Assert.assertEquals(8, primes.length);
        Assert.assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13, 17, 19}, primes);
    }

    @Test
    public void getPerfectNumbers() {

        int[] perfectNumbers = RArrayListUtil.getPerfectNumbers(10000);
        Assert.assertEquals(4, perfectNumbers.length);
        Assert.assertArrayEquals(new int[]{6, 28, 496, 8128}, perfectNumbers);
    }

    @Test
    public void join() {

        String join = RArrayListUtil.join(arr1, "-");

        Assert.assertEquals("7-9-30-3", join);

        join = RArrayListUtil.join(arr2, "-");
        Assert.assertEquals("7-9-30-3-4", join);
    }
}
