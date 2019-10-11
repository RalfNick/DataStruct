package algorithm.heap;

import java.util.Arrays;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-08-30 下午8:42
 **/
public class MinHeap<T extends Comparable<T>> {

    private int count;
    private Object arr[];

    public MinHeap(int capacity) {
        arr = new Object[capacity];
    }

    public void insert(T value) {
        if (count == arr.length) {
            throw new IndexOutOfBoundsException("the min heap is full!");
        }
        arr[count] = value;
        upJust(arr, value, count);
        count++;
    }

    @SuppressWarnings("unchecked")
    public T remove() {
        if (count == 0) {
            return null;
        }
        Object result = arr[0];
        arr[0] = arr[--count];
        arr[count] = null;
        downJust(arr, count, 0);
        return (T) result;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (count == 0) {
            return null;
        }
        return (T) arr[0];
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void downJust(Object[] arr, int length, int parentIndex) {
        int childIndex = (parentIndex << 1) + 1;
        Object temp = arr[parentIndex];
        while (childIndex < length) {
            if (childIndex + 1 < length && isSmaller((T) arr[childIndex + 1], (T) arr[childIndex])) {
                childIndex++;
            }
            if (isSmaller((T) temp, (T) arr[childIndex])) {
                break;
            }
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = (childIndex << 1) + 1;
        }
        arr[parentIndex] = temp;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void upJust(Object[] arr, T value, int childIndex) {
        if (arr == null || childIndex == 0) {
            return;
        }
        int parentIndex = (childIndex - 1) >> 1;
        while (childIndex > 0 && isSmaller(value, (T) arr[parentIndex])) {
            arr[childIndex] = arr[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) >> 1;
        }
        arr[childIndex] = value;
    }

    public static <T extends Comparable<T>> void buildHeap(T[] arr, int length) {
        if (arr == null || length < 1) {
            return;
        }
        for (int i = (length - 2) / 2; i >= 0; i--) {
            downJust(arr, length, i);
        }
    }

    public static <T extends Comparable<T>> void sort(T[] arr, int length) {
        if (arr == null || length < 1) {
            return;
        }
        buildHeap(arr, length);
        int k = length;
        while (k > 0) {
            swap(arr, 0, --k);
            downJust(arr, k, 0);
        }
        reverse(arr, length);
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    @SuppressWarnings("unchecked")
    public T[] getArray(T[] newArr) {
        System.arraycopy(arr, 0, newArr, 0, count);
        return newArr;
    }

    public int size() {
        return count;
    }

    private static void reverse(Object[] arr, int length) {
        if (arr == null || length < 1) {
            return;
        }
        int time = length >> 1;
        for (int i = 0; i < time; i++) {
            swap(arr, i, length - i - 1);
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static <T extends Comparable<T>> boolean isSmaller(T t1, T t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null || t2 == null) {
            return false;
        }
        return t1.compareTo(t2) < 0;
    }
}
