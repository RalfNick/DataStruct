package algorithm.heap;

import java.util.Arrays;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-09-05 下午8:34
 **/
public class PriorityQueue<T extends Comparable<? super T>> {

    private int capacity;
    private int size;
    private Object[] arr;

    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        arr = new Object[capacity];
    }

    public void enQueue(T value) {
        if (size >= capacity) {
            resize();
        }
        arr[size] = value;
        upJust(arr, value, size);
        size++;
    }

    private void resize() {
        capacity <<= 1;
        Object[] newArr = new Object[capacity];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    @SuppressWarnings("unchecked")
    public T deQueue() {
        if (size == 0) {
            return null;
        }
        Object result = arr[0];
        arr[0] = arr[--size];
        arr[size] = null;
        downJust(arr, size, 0);
        return (T) result;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (size == 0) {
            return null;
        }
        return (T) arr[0];
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<? super T>> void upJust(Object[] arr, T val, int childIndex) {
        int parentIndex = (childIndex - 1) >> 1;
        while (childIndex > 0 && isBigger(val, (T) arr[parentIndex])) {
            arr[childIndex] = arr[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) >> 1;
        }
        arr[childIndex] = val;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<? super T>> void downJust(Object[] arr, int length, int parentIndex) {
        int childIndex = (parentIndex << 1) + 1;
        Object temp = arr[parentIndex];
        while (childIndex < length) {
            if (childIndex + 1 < length && isBigger((T) arr[childIndex + 1], (T) arr[childIndex])) {
                childIndex++;
            }
            if (isBigger((T) temp, (T) arr[childIndex])) {
                break;
            }
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = (childIndex << 1) + 1;
        }
        arr[parentIndex] = temp;
    }

    private static <T extends Comparable<? super T>> boolean isBigger(T t1, T t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t2 == null) {
            return true;
        } else if (t1 == null) {
            return false;
        }
        return t1.compareTo(t2) > 0;
    }
}
