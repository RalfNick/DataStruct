package algorithm.array.findklargestelement;

import heap.PriorityQueue;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-05-10 上午8:05
 **/
public class FindKthLargestElement {

    /**
     * 在未排序的数组中找到第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 方法一：使用堆
     *
     * @param arr 数组
     * @param k   第 k 大元素
     * @return
     */
    public static int findKthLargestElement(int[] arr, int k) {
        if (arr == null || arr.length < 1) {
            throw new NullPointerException("the arr is empty!");
        }
        PriorityQueue priorityQueue = new PriorityQueue(arr.length);
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.enQueue(arr[i]);
        }
        for (int i = 1; i < k; i++) {
            priorityQueue.deQueue();
        }
        return priorityQueue.deQueue();
    }

    /**
     * 在未排序的数组中找到第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 方法二：使用快速排序思想
     *
     * @param arr 数组
     * @param k   第 k 大元素
     * @return
     */
    public static int findKthLargestElement1(int[] arr, int k) {
        if (arr == null || arr.length < 1) {
            throw new NullPointerException("the arr is empty!");
        }
        int start = 0, end = arr.length - 1, index = arr.length - k;
        while (start < end) {
            int pivot = partion(arr, start, end);
            if (pivot > index) {
                end = pivot - 1;
            } else if (pivot < index) {
                start = pivot + 1;
            } else {
                return arr[pivot];
            }
        }
        return arr[start];
    }

    private static int partion(int[] nums, int start, int end) {
        int pivot = start;
        while (start <= end) {
            while (start <= end && nums[start] <= nums[pivot]) start++;
            while (start <= end && nums[end] > nums[pivot]) end--;
            if (start > end) break;
            swap(nums,start,end);
        }
        swap(nums,end,pivot);
        return end;
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
