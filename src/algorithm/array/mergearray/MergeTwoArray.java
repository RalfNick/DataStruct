package algorithm.array.mergearray;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-05-04 下午8:21
 **/
public class MergeTwoArray {

    /**
     * 方法一：利用插入排序
     * 给定两个有序整数数组 arr1 和 arr2，将 arr2 合并到 arr1 中，使得 num1 成为一个有序数组。
     * arr1 空间大小大于或等于 m + n
     *
     * @param arr1 数组1
     * @param m    数组1元素个数
     * @param arr2 数组2
     * @param n    数组2元素个数
     * @return
     */
    public static void mergeTwoSortedArray(int[] arr1, int m, int[] arr2, int n) {
        if (arr1 == null) {
            throw new NullPointerException("the arr can not be null");
        } else {
            if (arr2 == null) {
                return;
            }
        }
        if (m + n > arr1.length) {
            throw new IndexOutOfBoundsException("the arr1 is too small");
        }
        for (int i = m; i < m + n; i++) {
            int j = i - 1;
            int temp = arr2[i - m];
            while (j > 0 && arr1[j] > temp) {
                arr1[j + 1] = arr1[j];
                j--;
            }
            arr1[j + 1] = temp;
        }

    }

    /**
     * 方法二：利用双指针
     * 给定两个有序整数数组 arr1 和 arr2，将 arr2 合并到 arr1 中，使得 num1 成为一个有序数组。
     * arr1 空间大小大于或等于 m + n
     *
     * @param arr1 数组1
     * @param m    数组1元素个数
     * @param arr2 数组2
     * @param n    数组2元素个数
     * @return
     */
    public static void mergeTwoSortedArray1(int[] arr1, int m, int[] arr2, int n) {
        if (arr1 == null) {
            throw new NullPointerException("the arr can not be null");
        } else {
            if (arr2 == null) {
                return;
            }
        }
        if (m + n > arr1.length) {
            throw new IndexOutOfBoundsException("the arr1 is too small");
        }

        int x = m - 1;
        int y = n - 1;

        for (int i = m + n - 1; i >= 0; i--) {
            if (x < 0) {
                arr1[i] = arr2[y];
                y--;
            } else if (y < 0) {
                arr1[i] = arr1[x];
                x--;
            } else {
                if (arr1[x] >= arr2[y]) {
                    arr1[i] = arr1[x];
                    x--;
                } else {
                    arr1[i] = arr2[y];
                    y--;
                }
            }
        }
    }
}
