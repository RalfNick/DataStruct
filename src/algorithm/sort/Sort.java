package algorithm.sort;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-03-12 下午10:34
 **/
public class Sort {

    /**
     * 交换数组中两个位置的数值
     *
     * @param arr
     * @param i
     * @param j
     */

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 冒泡排序
     *
     * @param arr 数组
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 插入排序
     * 要点：像扑克牌一样，小牌放在左边，插入的牌比左边的某张牌大（左边的牌已经排好大小），
     * 该张牌后面的牌依次向右移动一个位置
     *
     * @param arr 数组
     */
    public static void insertSort(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    /**
     * 选择排序
     * 每次选择一个最小（或最大）的数，拿走，再从剩下的数中重复选择最小（或最大）的数
     *
     * @param arr 数组
     */
    public static void selectSort(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, min, i);
        }
    }

    /**
     * 快速排序算法
     *
     * @param arr   数组
     * @param left  左边索引
     * @param right 右边索引
     */
    public static void quickSort(int[] arr, int left, int right) {

        if (left > right) {
            return;
        }
        int index = pivot(arr, left, right);
        quickSort(arr, left, index - 1);
        quickSort(arr, index + 1, right);
    }

    /**
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int pivot(int[] arr, int left, int right) {
        int i = left;
        int j = right;
        int pivot = arr[left];

        while (i != j) {
            while (arr[j] >= pivot && i < j) {
                j--;
            }
            while (arr[i] <= pivot && i < j) {
                i++;
            }
            if (i < j) {
                swap(arr, i, j);
            }
        }
        arr[left] = arr[i];
        arr[i] = pivot;
        return i;
    }
}
