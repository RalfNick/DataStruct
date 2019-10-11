package algorithm.heap;

import java.util.Arrays;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-08-30 下午8:42
 **/
public class MaxHeap<T extends Comparable<T>> {

    private int capacity;
    private Object[] arr;
    private int count;


    public MaxHeap(int capacity) {
        this.capacity = capacity;
        // 起始坐标从 1 开始
        arr = new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public void insert(T val) {
        if (count == arr.length) {
            throw new IndexOutOfBoundsException("the heap is full");
        }
        arr[count] = val;
        upJust(arr, val, count);
        count++;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void upJust(Object[] arr, T val, int child) {
        int parent = (child - 1) / 2;
        while (child > 0 && isBigger(val, (T) arr[parent])) {
            arr[child] = arr[parent];
            child = parent;
            parent = (parent - 1) / 2;
        }
        arr[child] = val;
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @SuppressWarnings("unchecked")
    public T remove() {
        if (count == 0) {
            return null;
        }
        Object result = arr[0];
        count--;
        arr[0] = arr[count];
        arr[count] = null;
        downAdjust(arr, count, 0);
        return (T) result;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void downAdjust(Object[] arr, int length, int parent) {
        int child = 2 * parent + 1;
        Object temp = arr[parent];
        while (child < length) {
            if (child + 1 < length && isBigger((T) arr[child + 1], (T) arr[child])) {
                child += 1;
            }
            if (isBigger((T) temp, (T) arr[child])) {
                break;
            }
            arr[parent] = arr[child];
            parent = child;
            child = 2 * child + 1;
        }
        arr[parent] = temp;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (count == 0) {
            return null;
        }
        return (T) arr[0];
    }

    private static <T extends Comparable<T>> boolean isBigger(T t1, T t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t2 == null) {
            return true;
        } else if (t1 == null) {
            return false;
        }
        return t1.compareTo(t2) > 0;
    }

    public static <T extends Comparable<T>> void buildHeap(T[] arr, int length) {
        if (arr == null || arr.length < 1) {
            return;
        }
        for (int i = (length - 2) / 2; i >= 0; i--) {
            downAdjust(arr, length, i);
        }
    }

    public static <T extends Comparable<T>> void sort(T[] arr, int length) {
        if (arr == null || arr.length < 1) {
            return;
        }
        buildHeap(arr, length);
        int k = length;
        while (k > 0) {
            swap(arr, 0, --k);
            downAdjust(arr, k, 0);
        }
    }

    public int getCount() {
        return count;
    }

    @SuppressWarnings("unchecked")
    public T[] getArray(T[] newArr) {
        System.arraycopy(arr, 0, newArr, 0, newArr.length);
        return newArr;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }
}
