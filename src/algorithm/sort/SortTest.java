package algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

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
        Sort.quickSort(a,0,7);
        for (int i = 0; i < 8; i++) {
            Assert.assertEquals(i + 1, a[i]);
        }
    }
}
