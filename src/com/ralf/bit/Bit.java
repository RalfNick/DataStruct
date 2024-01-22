package com.ralf.bit;

import java.util.HashMap;
import java.util.Map;

public class Bit {

    /**
     * 136. 只出现一次的数字
     * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
     * <a href="https://leetcode.cn/problems/single-number/description/?envType=list&envId=2vE9QdLy">Leet Code</a>
     */
    static int singleNumber(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }

    /**
     * 137. 只出现一次的数字 II
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
     * <a href="https://leetcode.cn/problems/single-number-ii/description/">Leet Code</a>
     */
    static int singleNumberII(int[] nums) {
        int a = 0;
        int b = 0;
        for (int num : nums) {
            b = (b ^ num) & ~a;
            a = (a ^ num) & ~b;
        }
        return a;
    }

    static int singleNumberII2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int num : nums) {
                count += (num >> i & 1);
            }
            if (count % 3 != 0) {
                res |= 1 << i;
            }
        }
        return res;
    }

    static int singleNumberII3(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i,map.getOrDefault(i, 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.get(key) == 1) {
                return key;
            }
        }
        return -1;
    }

    /**
     * 260. 只出现一次的数字 III  && LCR 177. 撞色搭配
     * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
     * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
     * <a href="https://leetcode.cn/problems/single-number-iii/description/">Leet Code</a>
     */
    static int[] singleNumberIII(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int lowBit = xor & -xor;
        int[] res = new int[2];
        for (int num : nums) {
            res[(lowBit & num) == 0 ? 0 : 1] ^= num;
        }
        return res;
    }
}
