package com.ralf.arraylist;

public class MyArrayList {

    /**
     * 7. 整数反转
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     * <a href="https://leetcode.cn/problems/reverse-integer/description/">Leet Code</a>
     */
    static int reverse(int x) {
        int res = 0;
        while (x != 0) {
            if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            res = res + res * 10 + digit;
        }
        return res;
    }

    /**
     * 31. 下一个排列
     * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
     * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
     * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
     * 那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列
     * （即，其元素按升序排列）。
     * <a href="https://leetcode.cn/problems/next-permutation/description//">Leet Code</a>
     */
    static void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 1) {
            return;
        }
        // 从后面找到第一个升序的位置
        int i = nums.length - 2;
        while (i >= -1 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            // 从后面找到第一个大于 num[i] 的值
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        revert(nums, i + 1);
    }

    private static void revert(int[] arr, int begin) {
        int i = begin;
        int j = arr.length - 1;
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
