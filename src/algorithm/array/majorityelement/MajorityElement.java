package algorithm.array.majorityelement;

import java.util.NoSuchElementException;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-04-23 上午7:49
 **/
public class MajorityElement {

    /**
     * 找到一个数组中的众数
     *
     * @param arr 数组
     * @return 众数
     */
    public static int majorityElement(int[] arr) {
        if (arr == null || arr.length < 1) {
            throw new NullPointerException("the arr is null or empty");
        }
        int majority = arr[0];
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (majority == arr[i]) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                majority = arr[i + 1];
            }
        }
        return majority;
    }
}
