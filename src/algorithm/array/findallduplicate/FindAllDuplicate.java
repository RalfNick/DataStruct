package algorithm.array.findallduplicate;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-05-16 下午8:58
 **/
public class FindAllDuplicate {

    /**
     * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
     * 找到所有出现两次的元素。
     * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
     *
     * @param arr 数组
     * @return
     */
    public static List<Integer> findAllDuplicate(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int index = Math.abs(arr[i]) - 1;
            if (arr[index] < 0) {
                list.add(Math.abs(arr[index]));
            }
            arr[index] *= -1;
        }
        return list;
    }
}
