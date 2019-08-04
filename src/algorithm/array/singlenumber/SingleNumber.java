package algorithm.array.singlenumber;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-05-14 上午10:14
 **/
public class SingleNumber {

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。
     * 找出那个只出现了一次的元素。算法应该具有线性时间复杂度。 不使用额外空间来实现
     *
     * @param arr 数组（假设不为空）
     * @return
     */
    public static int findSingleNumber1(int[] arr) {
        int result = 0;
        for (int i : arr) {
            result ^= i;
        }
        return result;
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。
     * 找出那个只出现了一次的元素。算法应该具有线性时间复杂度。 不使用额外空间来实现
     *
     * @param arr 数组
     * @return
     */
    public static int findSingleNumber2(int[] arr) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < arr.length; i++) {
            b = (b ^ arr[i]) & ~a;
            a = (a ^ arr[i]) & ~b;
        }
        return b;
    }

    public static int findSingleNumber21(int[] arr) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int j = 0; j < arr.length; j++) {
                sum += (arr[j] >> i) & 1;
            }
            res |= (sum % 3) << i;
        }
        return res;
    }
}
