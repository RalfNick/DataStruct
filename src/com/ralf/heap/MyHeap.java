package com.ralf.heap;

import java.util.Arrays;

class MyHeap {

    public void heapSort(int[] nums) {
        int len = nums.length;
        buildHeap(nums);
        for (int i = len - 1; i >= 1; ) {
            swap(nums, i, 0);
            i--;
            downAdjust(nums, 0, i);
        }
    }

    private void buildHeap(int[] nums) {
        int len = nums.length;
        for (int i = (len - 1) / 2; i >= 0; i--) {
            downAdjust(nums, i, len - 1);
        }
    }

    private void downAdjust(int[] nums, int i, int end) {
        int j = 2 * i + 1;
        while (j <= end) {
            if (j + 1 <= end && nums[j + 1] > nums[j]) {
                j++;
            }
            if (nums[j] <= nums[i]) {
                break;
            }
            swap(nums, i, j);
            i = j;
            j = 2 * i + 1;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {9, 8, 5, 6, 7, 1, 4, 0, 3, 2};
        MyHeap heap = new MyHeap();
        heap.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
