package algorithm.bit;

import java.util.*;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-11-10 上午9:34
 **/
public class Bit {

    /**
     * 子集 - 78
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * @param nums 数组
     * @return
     */
    public static List<List<Integer>> binaryBit(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            list.add(new ArrayList<>());
            return list;
        }
        for (int i = 0; i < 1 << nums.length; i++) {
            List<Integer> subList = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1) {
                    subList.add(nums[j]);
                }
            }
            list.add(subList);
        }
        return list;
    }

    /**
     * 子集 - 78
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * @param nums 数组
     * @return
     */
    public static List<List<Integer>> binaryBit1(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            list.add(new ArrayList<>());
            return list;
        }
        list.add(new ArrayList<>());
        backtrack(nums, 0, new ArrayList<>(), list);
        return list;
    }

    private static void backtrack(int[] nums, int i, List<Integer> sub, List<List<Integer>> res) {
        for (int j = i; j < nums.length; j++) {
            if (j > i && nums[j] == nums[j - 1]) continue;
            sub.add(nums[j]);
            res.add(new ArrayList<>(sub));
            backtrack(nums, j + 1, sub, res);
            sub.remove(sub.size() - 1);
        }
    }

    /**
     * 子集 - 78
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * @param nums 数组
     * @return
     */
    public static List<List<Integer>> binaryBit2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            list.add(new ArrayList<>());
            return list;
        }
        list.add(new ArrayList<>());
        inOrder(nums, 0, new ArrayList<>(), list);
        return list;
    }

    private static void inOrder(int[] nums, int i, List<Integer> subList, List<List<Integer>> list) {
        if (i >= nums.length) {
            return;
        }
        subList = new ArrayList<>(subList);
        inOrder(nums, i + 1, subList, list);
        subList.add(nums[i]);
        list.add(subList);
        inOrder(nums, i + 1, subList, list);
    }

    /**
     * 只出现一次的数字 - 136
     *
     * @param nums 数组
     * @return
     */
    public static int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }

    public static int singleNumber1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                set.remove(i);
            } else {
                set.add(i);
            }
        }
        return set.iterator().next();
    }

    /**
     * 只出现一次的数字 II - 137
     *
     * @param nums 数组
     * @return
     */
    public static int singleNumber2(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int temp = 0;
            for (int j = 0; j < nums.length; j++) {
                temp |= (nums[j] >> i) & 1;
            }
            res |= (temp % 3) << i;
        }
        return res;
    }

    public static int singleNumber21(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                res = entry.getKey();
                break;
            }
        }
        return res;
    }

    public static int singleNumber22(int[] nums) {
        int ones = 0, twos = 0, threes;
        for (int i : nums) {
            twos |= ones & i;
            ones ^= i;
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }

    public static int singleNumber23(int[] nums) {
        int ones = 0, twos = 0;
        for (int i : nums) {
            ones = ones ^ i & ~twos;
            twos = twos ^ i & ~ones;
        }
        return ones;
    }

    /**
     * 只出现一次的数字 III - 260
     * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。
     * 找出只出现一次的那两个元素。
     *
     * @param nums 数组
     * @return
     */
    public static int[] singleNumber3(int[] nums) {
        int[] arr = new int[2];
        int xor = 0;
        for (int i : nums) {
            xor ^= i;
        }
        int mask = xor & (-xor);
        for (int i : nums) {
            if ((i & mask) == 0) {
                arr[0] ^= i;
            } else {
                arr[1] ^= i;
            }
        }
        return arr;
    }

    /**
     * 求众数 - 169
     *
     * @param nums 数组
     * @return
     */
    public int majorityElement(int[] nums) {
        int count = 1;
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                num = nums[i];
                count++;
            } else {
                if (num == nums[i]) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return num;
    }

    /**
     * 2 的幂
     *
     * @param n 判断一个数是否是2的幂运算
     * @return
     */
    public static boolean isPowerOfTwo(int n) {
        if (n < 1) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        while (n > 1 && (n % 2) == 0) {
            n /= 2;
        }
        return 1 == n;
    }

    public static boolean isPowerOfTwo1(int n) {
        return n >= 1 && (n & (n - 1)) == 0;
    }

    /**
     * 4 的幂运算 - 342
     *
     * @param n 判断一个数是否是4的幂运算
     * @returnn
     */
    public static boolean isPowerOfFour(int n) {
        if (n < 1) {
            return false;
        }
        if ((n & (n - 1)) != 0) {
            return false;
        }
        return (n & 0x55555555) == n;
    }
}
