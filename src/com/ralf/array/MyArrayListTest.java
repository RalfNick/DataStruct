package com.ralf.array;

import org.junit.Assert;
import org.junit.Test;

public class MyArrayListTest {

    @Test
    public void testMyAtoi() {
        // "4193 with words"
        int res = MyArrayList.myAtoi("4193 with words");
        Assert.assertEquals(res, 4193);
    }

    @Test
    public void testFindKthLargestElement() {
        int[] arr = {3, 2, 1, 5, 6, 4};
//        int result = MyArrayList.findKthLargestElement(arr, 2);
        int result = MyArrayList.findKthLargestElement2(arr, 2);
        Assert.assertEquals(5, result);

        int[] arr1 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
//        int result1 = MyArrayList.findKthLargestElement(arr1, 4);
        int result1 = MyArrayList.findKthLargestElement2(arr1, 4);
        Assert.assertEquals(4, result1);
    }
}
