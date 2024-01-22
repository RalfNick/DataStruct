package com.ralf.search;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTest {

    int arr[] = {1, 5, 6, 7, 11, 11, 56};

    @Test
    public void test() {

        int result;
        // 非递归查找
        result = BinarySearch.binarySearch(arr, 7, 1);
        Assert.assertEquals(0, result);
        result = BinarySearch.binarySearch(arr, 7, 5);
        Assert.assertEquals(1, result);
        result = BinarySearch.binarySearch(arr, 7, 6);
        Assert.assertEquals(2, result);
        result = BinarySearch.binarySearch(arr, 7, 7);
        Assert.assertEquals(3, result);
        result = BinarySearch.binarySearch(arr, 7, 11);
        Assert.assertEquals(5, result);
        result = BinarySearch.binarySearch(arr, 7, 56);
        Assert.assertEquals(6, result);

        // 递归查找
        result = BinarySearch.binarySearchRecursion(arr, 7, 1);
        Assert.assertEquals(0, result);
        result = BinarySearch.binarySearchRecursion(arr, 7, 5);
        Assert.assertEquals(1, result);
        result = BinarySearch.binarySearchRecursion(arr, 7, 6);
        Assert.assertEquals(2, result);
        result = BinarySearch.binarySearchRecursion(arr, 7, 7);
        Assert.assertEquals(3, result);
        result = BinarySearch.binarySearchRecursion(arr, 7, 11);
        Assert.assertEquals(5, result);
        result = BinarySearch.binarySearchRecursion(arr, 7, 56);
        Assert.assertEquals(6, result);

        // 变形一
        result = BinarySearch.binarySearch1(arr, 7, 11);
        Assert.assertEquals(4, result);

        // 变形二
        result = BinarySearch.binarySearch2(arr, 7, 11);
        Assert.assertEquals(5, result);

        // 变形三
        result = BinarySearch.binarySearch3(arr, 7, 10);
        Assert.assertEquals(4, result);

        // 变形四
        result = BinarySearch.binarySearch4(arr, 7, 12);
        Assert.assertEquals(5, result);
    }
}
