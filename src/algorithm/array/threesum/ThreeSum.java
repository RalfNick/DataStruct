package algorithm.array.threesum;

import algorithm.sort.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-04-23 上午7:47
 **/
public class ThreeSum {

    /**
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
     * 找出所有满足条件且不重复的三元组。注意：答案中不可以包含重复的三元组。
     *
     * @param arr 数组
     * @return
     */
    public static List<List<Integer>> threeSum(int[] arr) {
        if (arr == null || arr.length < 1) {
            throw new NullPointerException("the arr is null or empty");
        }
        List<List<Integer>> result = new ArrayList<>();
        Sort.quickSort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int start = i + 1;
            int end = arr.length - 1;
            if (num > 0) {
                break;
            }
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            while (start < end) {
                int x = arr[start];
                int y = arr[end];
                int val = 0 - num;
                if (x + y == val) {
                    result.add(Arrays.asList(num, x, y));
                    while (x == arr[start + 1]) start++;
                    while (y == arr[end - 1]) end--;
                    start++;
                    end--;
                } else if (x + y > val) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return result;
    }
}
