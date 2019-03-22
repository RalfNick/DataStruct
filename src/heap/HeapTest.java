package heap;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-03-21 下午5:47
 **/
public class HeapTest {

    @Test
    public void testInsert() {
        int[] arr = {9, 8, 5, 6, 7, 1, 4, 0, 3, 2};
        Heap heap = new Heap(10);
        for (int i = 0; i < 10; i++) {
            heap.insert(i);
        }
        System.out.println(heap.toString());

        Heap.sort(arr, 10);
        System.out.println(Arrays.toString(arr));

        int result = heap.removeMax();
        Assert.assertEquals(9,result);
        System.out.println(heap.toString());
    }
}
