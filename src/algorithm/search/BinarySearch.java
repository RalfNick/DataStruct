package algorithm.search;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-03-18 下午7:06
 **/
public class BinarySearch {

    /**
     * 二分查找迭代算法
     *
     * @param arr   数组
     * @param len   长度
     * @param value 要找的值
     * @return
     */
    public static int binarySearch(int[] arr, int len, int value) {
        if (arr == null) {
            return -1;
        }
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找-递归
     *
     * @param arr   数组
     * @param len   长度
     * @param value 要找的值
     * @return
     */
    public static int binarySearchRecursion(int[] arr, int len, int value) {
        if (arr == null) {
            return -1;
        }
        return bsBinarySearch(arr, 0, len - 1, value);
    }

    public static int bsBinarySearch(int[] arr, int low, int high, int value) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) >> 1;
        if (arr[mid] > value) {
            return bsBinarySearch(arr, low, mid - 1, value);
        } else if (arr[mid] < value) {
            return bsBinarySearch(arr, mid + 1, high, value);
        } else {
            return mid;
        }
    }

    /**
     * 变体一：查找第一个值等于给定值的元素
     */
    public static int binarySearch1(int[] arr, int len, int value) {
        if (arr == null) {
            return -1;
        }
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || arr[mid - 1] != value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 变体二：查找最后一个值等于给定值的元素
     */
    public static int binarySearch2(int[] arr, int len, int value) {
        if (arr == null) {
            return -1;
        }
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == len - 1 || arr[mid + 1] != value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 变体三：查找第一个大于等于给定值的元素
     */
    public static int binarySearch3(int[] arr, int len, int value) {
        if (arr == null) {
            return -1;
        }
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= value) {
                if (mid == 0 || arr[mid - 1] < value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 变体四：查找最后一个小于等于给定值的元素
     */
    public static int binarySearch4(int[] arr, int len, int value) {
        if (arr == null) {
            return -1;
        }
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == len - 1 || arr[mid + 1] > value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
