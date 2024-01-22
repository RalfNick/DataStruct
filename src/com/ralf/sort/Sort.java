package com.ralf.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/sort-an-array/solutions/179489/fu-xi-ji-chu-pai-xu-suan-fa-java-by-liweiwei1419/?envType=list&envId=hU60vjRS">Leet Code解析</a>
 */
class Sort {

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 冒泡
     */
    static void bubbleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    static void bubbleSort1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int border = nums.length - 1;
        for (int i = nums.length - 1; i > 0; i--) {
            int lastIndex = 0;
            boolean sorted = true;
            for (int j = 0; j < border; j++) {
                if (nums[j] > nums[j + 1]) {
                    sorted = false;
                    swap(nums, j, j + 1);
                    lastIndex = j;
                }
            }
            border = lastIndex;
            if (sorted) {
                break;
            }
        }
    }

    /**
     * 选择排序
     */
    static void selectSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(nums, min, i);
            }
        }
    }

    /**
     * 插入排序
     */
    static void insertSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        for (int i = 1; i < nums.length; i++) {
            int j = i;
            int temp = nums[j];
            while (j > 0 && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temp;
        }
    }

    /**
     * 归并排序
     */
    static void mergeSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temp);
    }

    static void mergeSort(int[] nums, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) >> 1;
            mergeSort(nums, left, mid, temp);
            mergeSort(nums, mid + 1, right, temp);
            if (nums[mid] < nums[mid + 1]) {
                return;
            }
            merge2(nums, left, mid, right, temp);
        }
    }

    private static void merge(int[] nums, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        k = 0;
        for (int s = left; s <= right; s++) {
            nums[s] = temp[k++];
        }
    }

    private static void merge2(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                nums[k] = temp[j++];
            } else if (j > right) {
                nums[k] = temp[i++];
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i++];
            } else {
                nums[k] = temp[j++];
            }
        }
    }

    static void quickSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }

    static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(nums, left, right);
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        int i = left;
        int j = right;
        int pivot = nums[left];
        while (i != j) {
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        nums[left] = nums[i];
        nums[i] = pivot;
        return i;
    }

    /**
     * 桶排序
     */
    static void bucketSort(int[] nums, int bucketSize) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        int bucketCount = (max - min) / bucketSize + 1;
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>(bucketSize));
        }
        for (int num : nums) {
            int index = (num - min) / bucketSize;
            buckets.get(index).add(num);
        }
        int k = 0;
        for (List<Integer> list : buckets) {
            if (!list.isEmpty()) {
                Collections.sort(list);
                for (int item : list) {
                    nums[k++] = item;
                }
            }
        }
    }

    /**
     * 计数排序
     */
    static int[] countSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        int max = nums[0];
        int min = nums[0];
        for (int i : nums) {
            if (i > max) max = i;
            if (i < min) min = i;
        }
        int d = max - min;
        int[] countArr = new int[d + 1];
        for (int num : nums) {
            countArr[num - min]++;
        }
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1];
        }
        int[] sortedArr = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = countArr[nums[i] - min] - 1;
            sortedArr[index] = nums[i];
            countArr[nums[i] - min]--;
        }
        return sortedArr;
    }
}
