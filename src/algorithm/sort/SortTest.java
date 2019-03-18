package algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-03-12 下午11:08
 **/
public class SortTest {

    @Test
    public void bubble() {
        int a[] = {8, 6, 3, 2, 7, 4, 1, 5};
        Sort.bubbleSort(a);
        for (int i = 0; i < 8; i++) {
            Assert.assertEquals(i + 1, a[i]);
        }
    }

    @Test
    public void insert() {
        int a[] = {8, 6, 3, 2, 7, 4, 1, 5};
        Sort.insertSort(a);
        for (int i = 0; i < 8; i++) {
            Assert.assertEquals(i + 1, a[i]);
        }
    }

    @Test
    public void select() {
        int a[] = {8, 6, 3, 2, 7, 4, 1, 5};
        Sort.selectSort(a);
        for (int i = 0; i < 8; i++) {
            Assert.assertEquals(i + 1, a[i]);
        }
    }

    @Test
    public void quickSort() {
        int a[] = {8, 6, 3, 2, 7, 4, 1, 5};
        Sort.quickSort(a, 0, 7);
        for (int i = 0; i < 8; i++) {
            Assert.assertEquals(i + 1, a[i]);
        }
    }

    @Test
    public void bucketSort() {
        double[] array = new double[]{9.07, 7.09, 4.12, 1.345, 8.09, 2.96, 6.421, 0.0023, 4.09, 3.0,
                9.56, 2.123, 8.122, 7.83, 4.18, 9.09, 1.087, 2.56, 6.54, 5.74};
        Sort.bucketSort(array, 10);
        System.out.println(Arrays.toString(array));
    }
}
