package com.ralf.search;

public class BinarySearch {

    static int binarySearch(int[] arr, int len, int value) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int mid = (high + low) >> 1;
            if (arr[mid] == value) {
                return mid;
            }
            if (arr[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    static int binarySearchRecursion(int[] arr, int len, int value) {
        return binarySearchRecursion(arr, 0, len - 1, value);
    }

    private static int binarySearchRecursion(int[] arr, int left, int right, int value) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) >> 1;
        if (arr[mid] == value) {
            return mid;
        }
        if (arr[mid] > value) {
            return binarySearchRecursion(arr, left, mid - 1, value);
        }
        return binarySearchRecursion(arr, mid + 1, right, value);
    }


    /**
     * 变体一：查找第一个值等于给定值的元素
     */
    static int binarySearch1(int[] arr, int len, int value) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] > value) {
                right = mid - 1;
            } else if (arr[mid] < value) {
                left = mid + 1;
            } else {
                if (mid < 1 || arr[mid - 1] != value) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 变体二：查找最后一个值等于给定值的元素
     */
    static int binarySearch2(int[] arr, int len, int value) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] > value) {
                right = mid - 1;
            } else if (arr[mid] < value) {
                left = mid + 1;
            } else {
                if (mid == len - 1 || arr[mid + 1] != value) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 变体三：查找第一个大于等于给定值的元素
     */
    static int binarySearch3(int[] arr, int len, int value) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] < value) {
                left = mid + 1;
            } else {
                if (mid < 1 || arr[mid - 1] < value) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 变体四：查找最后一个小于等于给定值的元素
     */
    static int binarySearch4(int[] arr, int len, int value) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] > value) {
                right = mid - 1;
            } else {
                if (mid == len - 1 || arr[mid + 1] > value) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 852. 山脉数组的峰顶索引
     * 符合下列属性的数组 arr 称为 山脉数组 ：
     * arr.length >= 3
     * 存在 i（0 < i < arr.length - 1）使得：
     * arr[0] < arr[1] < ... arr[i-1] < arr[i]
     * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
     * 给你由整数组成的山脉数组 arr ，返回满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
     * 你必须设计并实现时间复杂度为 O(log(n)) 的解决方案。
     * <a href="https://leetcode.cn/problems/peak-index-in-a-mountain-array/">Leet Code</a>
     */
    static int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 2;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 162. 寻找峰值
     * 峰值元素是指其值严格大于左右相邻值的元素。
     * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     * 你可以假设 nums[-1] = nums[n] = -∞ 。
     * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
     * <a href="https://leetcode.cn/problems/find-peak-element/description/?envType=list&envId=w4AWEFMb">Leet Code</a>
     */
    static int findPeakElement(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 153. 寻找旋转排序数组中的最小值
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     * <a href="https://leetcode.cn/problems/peak-index-in-a-mountain-array/">Leet Code</a>
     */
    static int findMin(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 154. 寻找旋转排序数组中的最小值 II
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
     * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     * 你必须尽可能减少整个过程的操作步骤。
     * <a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/description/?envType=list&envId=w4AWEFMb">Leet Code</a>
     */
    static int findMinII(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return nums[left];
    }

    /**
     * 33. 搜索旋转排序数组
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     * <a href="https://leetcode.cn/problems/search-in-rotated-sorted-array/description/">Leet Code</a>
     */
    static int search(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 81. 搜索旋转排序数组 II
     * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
     * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
     * 你必须尽可能减少整个操作步骤。
     * <a href="https://leetcode.cn/problems/search-in-rotated-sorted-array-ii/description/?envType=list&envId=w4AWEFMb">Leet Code</a>
     */
    static boolean searchII(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] == nums[left]) {
                left++;
                continue;
            }
            if (nums[mid] == nums[right]) {
                right--;
                continue;
            }
            if (nums[mid] > nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * 540. 有序数组中的单一元素
     * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
     * 请你找出并返回只出现一次的那个数。
     * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
     * <a href="https://leetcode.cn/problems/single-element-in-a-sorted-array/description/">Leet Code</a>
     */
    static int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (mid % 2 == 0) {
                if (mid + 1 < nums.length && nums[mid] == nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else {
                if (mid - 1 >= 0 && nums[mid] == nums[mid - 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return nums[right];
    }

    /**
     * 69. x 的平方根
     * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
     * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
     * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
     * <a href="https://leetcode.cn/problems/sqrtx/description/?envType=list&envId=w4AWEFMb">Leet Code</a>
     */
    static int mySqrt(int x) {
        int left = 0;
        int right = x;
        int res = -1;
        while (left < right) {
            int mid = (left + right) / 2;
            long temp = (long) mid * mid;
            if (temp == x) {
                return mid;
            } else if (temp < x) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // weights = [1,2,3,4,5,6,7,8,9,10], days = 5
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;
        int res = shipWithinDays(weights, days);
        System.out.println("shipWithinDays res=" + res);
//         weights = [3,2,2,4,1,4], days = 3
        int[] weights1 = {3, 2, 2, 4, 1, 4};
        int days1 = 3;
        int res1 = shipWithinDays(weights1, days1);
        System.out.println("shipWithinDays res1=" + res1);
    }

    public static int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 0;
        for (int weight : weights) {
            left = Math.max(weight, left);
            right += weight;
        }
        int capacity = 0;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int usedDays = getUsedDays(weights, mid);
            if (usedDays > days) {
                left = mid + 1;
            } else {
                right = mid - 1;
                capacity = mid;
            }
        }
        return capacity;
    }

    private static int getUsedDays(int[] weights, int capacity) {
        int day = 1;
        int sum = 0;
        for (int weight : weights) {
            sum += weight;
            if (sum > capacity) {
                sum = weight;
                day += 1;
            }
        }
        return day;
    }

}
