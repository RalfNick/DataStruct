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
public class MinHeapTest {

    private MinHeap<Integer> minHeap;

    @Before
    public void init() {
        minHeap = new MinHeap<>(10);
    }

    @Test
    public void testInsert() {
        for (int i = 10; i > 0; i--) {
            minHeap.insert(i);
        }
        System.out.println(minHeap.toString());
    }

    @Test
    public void testRemove() {
        for (int i = 10; i > 0; i--) {
            minHeap.insert(i);
        }
        for (int i = 1; i < 11; i++) {
            Assert.assertEquals(i, minHeap.remove().intValue());
        }
    }

    @Test
    public void testSort() {
        for (int i = 10; i > 0; i--) {
            minHeap.insert(i);
        }
        Integer[] arr = new Integer[minHeap.size()];
        minHeap.getArray(arr);
        MinHeap.sort(arr, minHeap.size());
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(i + 1, arr[i].intValue());
        }
    }

    @Test
    public void testSort1() {
        Integer[] arr = new Integer[]{2, 1, 4, 6, 8, 9, 3, 7, 5, 10};
        MinHeap.sort(arr, arr.length);
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(i + 1, arr[i].intValue());
        }
    }
}
