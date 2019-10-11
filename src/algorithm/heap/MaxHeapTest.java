package algorithm.heap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-09-05 下午8:33
 **/
public class MaxHeapTest {

    private MaxHeap<Integer> maxHeap;

    @Before
    public void init() {
        maxHeap = new MaxHeap<>(10);
    }

    @Test
    public void testInsert() {
        for (int i = 1; i < 11; i++) {
            maxHeap.insert(i);
        }
        System.out.println(maxHeap.toString());
    }

    @Test
    public void testRemove() {
        for (int i = 1; i < 11; i++) {
            maxHeap.insert(i);
        }
        for (int i = 10; i > 0; i--) {
            Assert.assertEquals(i, maxHeap.remove().intValue());
        }
    }

    @Test
    public void testSort() {
        for (int i = 1; i < 11; i++) {
            maxHeap.insert(i);
        }
        Integer[] arr = new Integer[maxHeap.getCount()];
        maxHeap.getArray(arr);
        MaxHeap.sort(arr, arr.length);
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(i + 1, arr[i].intValue());
        }
    }

    @Test
    public void testSort1() {
        Integer[] arr = new Integer[]{2, 1, 4, 6, 8, 9, 3, 7, 5, 10};
        MaxHeap.sort(arr, arr.length);
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(i + 1, arr[i].intValue());
        }
    }
}
