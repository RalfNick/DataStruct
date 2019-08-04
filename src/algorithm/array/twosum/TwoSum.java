package algorithm.array.twosum;

import algorithm.search.BinarySearch;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-04-17 上午8:45
 **/
public class TwoSum {

    /**
     * 找到数组中两个元素之和为 target 的元素下标
     * 方法一：遍历相加   时间复杂度 O(n^2)
     *
     * @param arr    数组
     * @param target 目标值
     * @return
     */
    public static int[] findTwoSumIndex1(int[] arr, int target) {

        if (arr == null || arr.length < 1) {
            throw new NullPointerException("arr is null");
        }
        int[] result = {-1, -1};
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] >= target) {
                break;
            }
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] >= target) {
                    break;
                }
                if (arr[j] + arr[i] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    /**
     * 找到数组中两个元素之和为 target 的元素下标
     * 方法一：利用二分查找 时间复杂度 O(nlogn)
     *
     * @param arr    数组
     * @param target 目标值
     * @return
     */
    public static int[] findTwoSumIndex2(int[] arr, int target) {
        if (arr == null || arr.length < 1) {
            throw new NullPointerException("arr is null");
        }
        int[] result = {-1, -1};
        // 查找最后一个小于等于给定值的元素
        int lastIndex = BinarySearch.binarySearch4(arr, arr.length, target);
        for (int i = 0; i <= lastIndex; i++) {
            int num = target - arr[i];
            int resultIndex = BinarySearch.binarySearch(arr, lastIndex + 1, num);
            if (resultIndex != -1) {
                result[0] = i;
                result[1] = resultIndex;
                break;
            }
        }
        return result;
    }
}
