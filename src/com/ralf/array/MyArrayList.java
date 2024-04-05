package com.ralf.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

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
            res = res * 10 + digit;
        }
        return res;
    }

    /**
     * 9. 回文数
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * 例如，121 是回文，而 123 不是。
     * <a href="https://leetcode.cn/problems/palindrome-number/description/">Leet Code</a>
     */
    static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int origin = x;
        int num = 0;
        while (x != 0) {
            int temp = x % 10;
            x = x / 10;
            num = num * 10 + temp;
        }
        return num == origin;
    }

    /**
     * 8. 字符串转换整数 (atoi)
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     * 函数 myAtoi(string s) 的算法如下：
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     * <a href="https://leetcode.cn/problems/string-to-integer-atoi/description/">Leet Code</a>
     */
    static int myAtoi(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] chs = s.toCharArray();
        int index = 0;
        while (index < chs.length && chs[index] == ' ') {
            index++;
        }
        if (index == chs.length) {
            return 0;
        }
        int signs = 1;
        if (chs[index] == '-') {
            signs = -1;
        }
        if (chs[index] == '-' || chs[index] == '+') {
            index++;
        }
        int res = 0;
        while (index < chs.length) {
            if (chs[index] < '0' || chs[index] > '9') {
                break;
            }
            int temp = chs[index] - '0';
            if (signs > 0 && res > (Integer.MAX_VALUE - temp) / 10) {
                return Integer.MAX_VALUE;
            }
            if (signs < 0 && res < (Integer.MIN_VALUE + temp) / 10) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + temp * signs;
            index++;
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
        while (i >= 0 && nums[i] >= nums[i + 1]) {
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

    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     * <a href="https://leetcode.cn/problems/two-sum/description/">Leet Code</a>
     */
    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int val = target - nums[i];
            if (map.containsKey(val)) {
                res[0] = map.get(val);
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    /**
     * 15. 三数之和
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * 你返回所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * <a href="https://leetcode.cn/problems/3sum/description/">Leet Code</a>
     */
    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num >= 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int val = -num;
                int a = nums[start];
                int b = nums[end];
                if (a + b == val) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(num);
                    temp.add(a);
                    temp.add(b);
                    result.add(temp);
                    while (start < end && nums[start] == nums[start + 1]) start++;
                    while (start < end && nums[end] == nums[end - 1]) end--;
                    start++;
                    end--;
                } else if (a + b > val) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return result;
    }

    /**
     * 16. 最接近的三数之和
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
     * 返回这三个数的和。
     * 假定每组输入只存在恰好一个解。
     * <a href="https://leetcode.cn/problems/3sum-closest/description/">Leet Code</a>
     */
    static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int second = nums[start];
                int third = nums[end];
                int temp = first + second + third;
                if (temp == target) {
                    return target;
                }
                if (Math.abs(temp - target) < Math.abs(sum - target)) {
                    sum = temp;
                }
                if (temp > target) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return sum;
    }

    /**
     * 18. 四数之和
     * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
     * 0 <= a, b, c, d < n
     * a、b、c 和 d 互不相同
     * nums[a] + nums[b] + nums[c] + nums[d] == target
     * 你可以按 任意顺序 返回答案 。
     * <a href="https://leetcode.cn/problems/4sum/">Leet Code</a>
     */
    static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            int first = nums[i];
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                if ((long) first + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                int start = j + 1;
                int end = nums.length - 1;
                int second = nums[j];
                while (start < end) {
                    long sum = (long) first + second + nums[start] + nums[end];
                    if (sum == target) {
                        List<Integer> list = Arrays.asList(first, second, nums[start], nums[end]);
                        result.add(list);
                        while (start < end && nums[start] == nums[start + 1]) start++;
                        while (start < end && nums[end] == nums[end - 1]) end--;
                        start++;
                        end--;
                    } else if (sum > target) {
                        end--;
                    } else {
                        start++;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 26. 删除有序数组中的重复项
     * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
     * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
     * 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。
     * 返回 k 。
     * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/">Leet Code</a>
     */
    static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    /**
     * 27. 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * <a href="https://leetcode.cn/problems/remove-element/">Leet Code</a>
     */
    static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int i = 0;
        int j = 0;
        int len = nums.length;
        while (i < len) {
            if (nums[i] != val) {
                nums[j++] = nums[i];
            }
            i++;
        }
        return j;
    }

    /**
     * 80. 删除有序数组中的重复项 II
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/">Leet Code</a>
     */
    static int removeDuplicatesII(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length < 3) {
            return nums.length;
        }
        int j = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            if (count < 3) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    /**
     * 217. 存在重复元素
     * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
     * <a href="https://leetcode.cn/problems/contains-duplicate/description/">Leet Code</a>
     */
    static boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    /**
     * 287. 寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
     * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
     * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
     * <a href="https://leetcode.cn/problems/find-the-duplicate-number/description/">Leet Code</a>
     */
    static int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        int fast = 0;
        int slow = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        fast = 0;
        while (fast != slow) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }

    static int findDuplicate2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        int left = 0, right = nums.length - 1, res = -1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int count = 0;
            for (int i : nums) {
                if (i <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
                res = mid;
            }
        }
        return res;
    }

    /**
     * 88. 合并两个有序数组
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     * <a href="https://leetcode.cn/problems/merge-sorted-array/description/">Leet Code</a>
     */
    static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums1.length < 1 || nums2 == null || nums2.length < 1) {
            return;
        }
        int k = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        while (i >= 0) {
            nums1[k--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * <a href="https://leetcode.cn/problems/majority-element/description/">Leet Code</a>
     */
    static int majorityElement(int[] nums) {
        int count = 1;
        int majority = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == majority) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                majority = nums[i];
                count = 1;
            }
        }
        return majority;
    }

    static int majorityElement2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        Integer majority = null;
        int result = nums[0];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (majority == null || entry.getValue() > majority) {
                majority = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }

    /**
     * 229. 多数元素 II
     * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
     * <a href="https://leetcode.cn/problems/majority-element-ii/description/">Leet Code</a>
     */
    static List<Integer> majorityElementII(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return result;
        }
        int fre = nums.length / 3;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > fre) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    static List<Integer> majorityElementII2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return result;
        }
        int res1 = nums[0], res2 = nums[0];
        int count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == res1) {
                count1++;
                continue;
            }
            if (num == res2) {
                count2++;
                continue;
            }
            if (count1 == 0) {
                res1 = num;
                count1 = 1;
                continue;
            }
            if (count2 == 0) {
                res2 = num;
                count2 = 1;
                continue;
            }
            count1--;
            count2--;
        }
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == res1) count1++;
            else if (num == res2) count2++;
        }
        if (count1 > nums.length / 3) {
            result.add(res1);
        }
        if (count2 > nums.length / 3) {
            result.add(res2);
        }
        return result;
    }

    /**
     * LCR 170. 交易逆序对的总数
     * 在股票交易中，如果前一天的股价高于后一天的股价，则可以认为存在一个「交易逆序对」。请设计一个程序，输入一段时间内的股票交易记录 record，返回其中存在的「交易逆序对」总数。
     * <a href="https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/description/?envType=list&envId=hU60vjRS">Leet Code</a>
     */
    static int reversePairs(int[] record) {
        if (record == null || record.length < 2) {
            return 0;
        }
        int[] temp = new int[record.length];
        return mergeSort(record, 0, record.length - 1, temp);
    }

    static int mergeSort(int[] nums, int left, int right, int[] temp) {
        if (left >= right) {
            return 0;
        }
        int mid = (left + right) >> 1;
        int leftCount = mergeSort(nums, left, mid, temp);
        int rightCount = mergeSort(nums, mid + 1, right, temp);
        // 如果整个数组已经有序，则无需合并，注意这里使用小于等于
        if (nums[mid] <= nums[mid + 1]) {
            return leftCount + rightCount;
        }
        int mergeCount = merge(nums, left, mid, right, temp);
        return leftCount + rightCount + mergeCount;
    }

    private static int merge(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }
        int i = left;
        int j = mid + 1;
        int count = 0;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                nums[k] = temp[j++];
            } else if (j > right) {
                nums[k] = temp[i++];
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i++];
            } else {
                nums[k] = temp[j++];
                count += mid - i + 1;
            }
        }
        return count;
    }

    /**
     * 315. 计算右侧小于当前元素的个数
     * 给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
     * <a href="https://leetcode.cn/problems/count-of-smaller-numbers-after-self/?envType=list&envId=hU60vjRS">Leet Code</a>
     */
    static List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return list;
        }
        int length = nums.length;
        int[] temp = new int[length];
        int[] indexes = new int[length];
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            indexes[i] = i;
        }
        mergeSort(nums, 0, length - 1, temp, indexes, res);
        for (int i : res) {
            list.add(i);
        }
        return list;
    }

    static void mergeSort(int[] nums, int left, int right, int[] temp, int[] indexes, int[] res) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) >> 1;
        mergeSort(nums, left, mid, temp, indexes, res);
        mergeSort(nums, mid + 1, right, temp, indexes, res);
        // 如果整个数组已经有序，则无需合并，注意这里使用小于等于
        if (nums[indexes[mid]] <= nums[indexes[mid + 1]]) {
            return;
        }
        merge(nums, left, mid, right, temp, indexes, res);
    }

    static void merge(int[] nums, int left, int mid, int right, int[] temp, int[] indexes, int[] res) {
        for (int i = left; i <= right; i++) {
            temp[i] = indexes[i];
        }
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                indexes[k] = temp[j++];
            } else if (j > right) {
                indexes[k] = temp[i++];
                res[indexes[k]] += (right - mid);
            } else if (nums[temp[i]] <= nums[temp[j]]) {
                indexes[k] = temp[i++];
                res[indexes[k]] += (j - mid - 1);
            } else {
                indexes[k] = temp[j++];
            }
        }
    }

    /**
     * 215. 数组中的第K个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * <a href="https://leetcode.cn/problems/kth-largest-element-in-an-array/description/?envType=list&envId=hU60vjRS">Leet Code</a>
     * 方法一：使用堆
     */
    public static int findKthLargestElement(int[] nums, int k) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > queue.peek()) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.peek();
    }

    public static int findKthLargestElement2(int[] nums, int k) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int target = nums.length - k;
        while (start < end) {
            int pivot = partition(nums, start, end);
            if (pivot > target) {
                end = pivot - 1;
            } else if (pivot < target) {
                start = pivot + 1;
            } else {
                return nums[pivot];
            }
        }
        return nums[start];
    }

    private static int partition(int[] nums, int start, int end) {
        int i = start;
        int j = end;
        int pivot = nums[start];
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
        nums[start] = nums[i];
        nums[i] = pivot;
        return i;
    }

    /**
     * 347. 前 K 个高频元素
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     * <a href="https://leetcode.cn/problems/top-k-frequent-elements/description/?envType=list&envId=hU60vjRS">Leet Code</a>
     * 方法一：使用堆
     */
    static int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (Integer key : map.keySet()) {
            if (queue.size() < k) {
                queue.offer(key);
            } else if (map.get(queue.peek()) < map.get(key)) {
                queue.poll();
                queue.offer(key);
            }
        }
        int[] result = new int[k];
        int i = 0;
        while (!queue.isEmpty() && i < k) {
            result[i++] = queue.poll();
        }
        return result;
    }

    /**
     * 75. 颜色分类
     * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
     * <a href="https://leetcode.cn/problems/sort-colors/description/?envType=list&envId=hU60vjRS">Leet Code</a>
     */
    static void sortColors(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int zero = 0;
        int two = nums.length;
        int i = 0;
        while (i < two) {
            if (nums[i] == 0) {
                swap(nums, zero, i);
                zero++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                two--;
                swap(nums, two, i);
            }
        }
    }

    /**
     * 11. 盛最多水的容器
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。
     * 说明：你不能倾斜容器。
     * <a href="https://leetcode.cn/problems/container-with-most-water/description/?company_slug=amazon">Leet Code</a>
     */
    static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int i = 0;
        int j = height.length - 1;
        int result = 0;
        while (i < j) {
            int w = j - i;
            int h = height[i] < height[j] ? height[i++] : height[j--];
            result = Math.max(result, w * h);
        }
        return result;
    }

    /**
     * 56. 合并区间
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * <a href="https://leetcode.cn/problems/merge-intervals/description/?envType=list&envId=hU60vjRS">LeetCode</a>
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 1) {
            return null;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];
            int size = result.size();
            if (size == 0 || result.get(size - 1)[1] < left) {
                result.add(new int[]{left, right});
            } else {
                result.get(size - 1)[1] = Math.max(result.get(size - 1)[1], right);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    /**
     * 128. 最长连续序列
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * <a href="https://leetcode.cn/problems/longest-consecutive-sequence/description/?envType=list&envId=hU60vjRS">LeetCode</a>
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int num : nums) {
            int cur = num;
            if (set.contains(cur - 1)) {
                continue;
            }
            int len = 1;
            while (set.contains(cur + 1)) {
                cur++;
                len++;
            }
            max = Math.max(len, max);
        }
        return max;
    }

    /**
     * 167. 两数之和 II - 输入有序数组
     * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     * 你所设计的解决方案必须只使用常量级的额外空间。
     * <a href="https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/description/?envType=study-plan-v2&envId=top-interview-150">LeetCode</a>
     */
    public int[] twoSumII(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        int[] res = new int[2];
        while (i < j) {
            int temp = numbers[i] + numbers[j];
            if (temp == target) {
                res[0] = i;
                res[1] = j;
                break;
            }
            if (temp > target) {
                j--;
            } else {
                i++;
            }
        }
        res[0] += 1;
        res[1] += 1;
        return res;
    }


    /**
     * 74. 搜索二维矩阵
     * 给你一个满足下述两条属性的 m x n 整数矩阵：
     * 每行中的整数从左到右按非严格递增顺序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
     * <a href="https://leetcode.cn/problems/search-a-2d-matrix/description/">LeetCode</a>
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0, j = m * n - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (matrix[mid / n][mid % n] == target) {
                return true;
            }
            if (matrix[mid / n][mid % n] > target) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return false;
    }

    public static boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int m = matrix.length;
        int i = 0, j = m - 1;
        int index = 0;
        // row search
        while (i <= j) {
            int mid = (i + j) / 2;
            if (matrix[mid][0] > target) {
                j = mid - 1;
            } else {
                if (mid == m - 1 || matrix[mid + 1][0] > target) {
                    index = mid;
                    break;
                } else {
                    i = mid + 1;
                }
            }
        }
        int n = matrix[0].length;
        int[] num = matrix[index];
        i = 0;
        j = n - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (num[mid] == target) {
                return true;
            }
            if (num[mid] > target) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return false;
    }

    /**
     * 240. 搜索二维矩阵 II
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * <a href="https://leetcode.cn/problems/search-a-2d-matrix-ii/description/">LeetCode</a>
     */
    public static boolean searchMatrixII(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    public static boolean searchMatrixII2(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        for (int[] ints : matrix) {
            if (searchMatrixInternal(ints, target)) {
                return true;
            }
        }
        return false;
    }

    private static boolean searchMatrixInternal(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    /**
     * 189. 轮转数组
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * <a href="https://leetcode.cn/problems/rotate-array/description/?envType=study-plan-v2&envId=top-interview-150">LeetCode</a>
     */
    public void rotate(int[] nums, int k) {
        final int len = nums.length;
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            int index = (i + k) % len;
            temp[index] = nums[i];
        }
        System.arraycopy(temp, 0, nums, 0, len);
    }

    /**
     * 392. 判断子序列
     */
    public void rotate1(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public boolean isSubsequence(String s, String t) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        if (t == null || t.isEmpty()) {
            return false;
        }
        if (s.length() > t.length()) {
            return false;
        }
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }


    /**
     * 263. 丑数
     * 丑数 就是只包含质因数 2、3 和 5 的正整数。
     */
    public boolean isUgly(int n) {
        if (n < 1) {
            return false;
        }
        while (n % 2 == 0) n /= 2;
        while (n % 3 == 0) n /= 3;
        while (n % 5 == 0)
            n /= 5;
        return n == 1;
    }

    /**
     * NC41 最长无重复子数组
     */
    public int maxLength(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                left = Math.max(left, map.get(arr[i]) + 1);
            }
            max = Math.max(max, i - left + 1);
            map.put(arr[i], i);
        }
        return max;
    }

    /**
     * 349. 两个数组的交集
     * 给定两个数组 nums1 和 nums2 ，返回 它们的交集,输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
     * <a href="https://leetcode.cn/problems/intersection-of-two-arrays/">Leet Code</a>
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int i = 0;
        for (int num : set1) {
            if (set2.contains(num)) {
                res[i++] = num;
            }
        }
        return Arrays.copyOfRange(res, 0, i);
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length < 1 || nums2 == null || nums2.length < 1) {
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, k = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] res = new int[Math.min(len1, len2)];
        while (i < len1 && j < len2) {
            if (nums1[i] == nums2[j]) {
                if (k == 0 || res[k - 1] != nums1[i]) {
                    res[k++] = nums1[i];
                }
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }
        return Arrays.copyOfRange(res, 0, i);
    }

    /**
     * 350. 两个数组的交集II
     * 给定两个数组 nums1 和 nums2 ，返回 它们的交集,输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
     * <a href="https://leetcode.cn/problems/intersection-of-two-arrays/">Leet Code</a>
     */
    public int[] intersectionII(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length < 1 || nums2 == null || nums2.length < 1) {
            return new int[0];
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 > len2) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int num : nums1) {
            map1.put(num, map1.getOrDefault(num, 0) + 1);
        }
        int[] res = new int[Math.min(len1, len2)];
        int i = 0;
        for (int num : nums2) {
            int count = map1.getOrDefault(num, 0);
            if (count > 0) {
                res[i++] = num;
                count--;
            }
            if (count > 0) {
                map1.put(num, count);
            } else {
                map1.remove(num);
            }
        }
        return Arrays.copyOfRange(res, 0, i);
    }
}














