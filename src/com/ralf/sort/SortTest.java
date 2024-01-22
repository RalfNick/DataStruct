package com.ralf.sort;

import org.junit.Assert;
import org.junit.Test;

public class SortTest {

    @Test
    public void bubble() {
        int[] a = {8, 6, 3, 2, 7, 4, 1, 5};
//        Sort.bubbleSort(a);
        Sort.bubbleSort1(a);
        for (int i = 0; i < 8; i++) {
            Assert.assertEquals(i + 1, a[i]);
        }
    }

    @Test
    public void insert() {
        int[] a = {8, 6, 3, 2, 7, 4, 1, 5};
        Sort.insertSort(a);
        for (int i = 0; i < 8; i++) {
            Assert.assertEquals(i + 1, a[i]);
        }
    }

    @Test
    public void select() {
        int[] a = {8, 6, 3, 2, 7, 4, 1, 5};
        Sort.selectSort(a);
        for (int i = 0; i < 8; i++) {
            Assert.assertEquals(i + 1, a[i]);
        }
    }

    @Test
    public void quickSort() {
        int[] a = {8, 6, 3, 2, 7, 4, 1, 5};
        Sort.quickSort(a, 0, 7);
        for (int i = 0; i < 8; i++) {
            Assert.assertEquals(i + 1, a[i]);
        }
    }

    @Test
    public void countSort() {
        int[] array = new int[]{95, 94, 91, 98, 99, 90, 99, 93, 91, 92};
        int[] result = new int[]{90, 91, 91, 92, 93, 94, 95, 98, 99, 99};
        int[] countSort = Sort.countSort(array);
        Assert.assertArrayEquals(result, countSort);
    }

    @Test
    public void mergeSort() {
        int[] a = {8, 6, 3, 2, 7, 4, 1, 5};
        Sort.mergeSort(a);
        for (int i = 0; i < 8; i++) {
            Assert.assertEquals(i + 1, a[i]);
        }
    }
}
