package heap;

import java.util.Arrays;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-03-21 下午5:46
 **/
public class PriorityQueue {

    private int[] arr;
    private int size;
    private int max;

    public PriorityQueue(int max) {
        this.max = max;
        arr = new int[max];
    }

    public void enQueue(int value) {
        if (size >= max) {
            resize();
        }
        arr[size++] = value;
        upAdjust();
    }

    public int deQueue() throws Exception {
        if (size <= 0) {
            throw new Exception("the queue is empty!");
        }
        int result = arr[0];
        arr[0] = arr[--size];
        downAdjust();
        return result;
    }

    /**
     * 上浮操作
     */
    private void upAdjust() {
        int childIndex = size - 1;
        int parentIndex = (childIndex - 1) / 2;
        int temp = arr[childIndex];
        while (childIndex > 0 && temp > arr[parentIndex]) {
            arr[childIndex] = arr[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        arr[childIndex] = temp;
    }

    /**
     * 下浮操作
     */
    private void downAdjust() {
        int parentIndex = 0;
        int childIndex = 1;
        int temp = arr[parentIndex];
        while (childIndex < size) {
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

    private void resize() {
        max = max << 1;
        int[] newArr = new int[max];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        arr = newArr;
    }

    @Override
    public String toString() {
        return "PriorityQueue{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }
}
