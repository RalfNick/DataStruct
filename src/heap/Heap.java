package heap;

import java.util.Arrays;

/**
 * 大顶堆
 *
 * @author lixin
 * @create 2019-03-20 下午8:06
 **/
public class Heap {

    /**
     * 堆的数组
     */
    private int[] arr;
    /**
     * 堆的最大数据个数
     */
    private int max;
    /**
     * 堆中数据个数
     */
    private int size;

    public Heap(int capacity) {
        arr = new int[capacity];
        max = capacity;
        size = 0;
    }

    public void insert(int value) {
        if (size == max) {
            return;
        }
        arr[size] = value;
        int childIndex = size;
        int parentIndex = (childIndex - 1) / 2;
        while (childIndex > 0 && value > arr[parentIndex]) {
            arr[childIndex] = arr[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        arr[childIndex] = value;
        size++;
    }

    public int removeMax() {
        if (arr == null || size == 0) {
            return -1;
        }
        size--;
        int result = arr[0];
        arr[0] = arr[size];
        downAdjust(arr, size, 0);
        return result;
    }

    private static void downAdjust(int[] arr, int size, int i) {
        int temp = arr[i];
        int parentIndex = i;
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < size) {
            // 如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if (childIndex + 1 < size && arr[childIndex] < arr[childIndex + 1]) {
                childIndex++;
            }
            if (temp >= arr[childIndex]) {
                break;
            }
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        arr[parentIndex] = temp;
    }

    private static void buildHeap(int[] arr, int length) {
        for (int i = (length - 2) / 2; i >= 0; i--) {
            downAdjust(arr, length, i);
        }
    }

    public static void sort(int[] arr, int n) {
        if (arr == null) {
            return;
        }
        buildHeap(arr, n);
        int k = n - 1;
        while (k > 0) {
            // 交换堆顶元素和最后一个元素
            int temp = arr[0];
            arr[0] = arr[k];
            arr[k] = temp;
            k--;
            downAdjust(arr, k, 0);
        }
    }

    @Override
    public String toString() {
        return "Heap{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }
}
