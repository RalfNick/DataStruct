package algorithm.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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

    /**
     * 一个长度为 n 的 double 类型数组，取值为 0 - 10，要求快速将这 20 个 double 元素从小到大排序
     *
     * @param arr       double 数组
     * @param bucketNum 桶的数量
     */
    public static void bucketSort(double[] arr, int bucketNum) {
        if (arr == null) {
            return;
        }
        double max = arr[0];
        double min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        // 间隔
        double d = max - min;

        // 创建桶
        List<LinkedList<Double>> bucketList = new ArrayList<>();
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<>());
        }

        // 将数据放入桶中
        for (int i = 0; i < arr.length; i++) {
            int index = (int) ((arr[i] - min) / d * (bucketNum - 1));
            bucketList.get(index).add(arr[i]);
        }

        // 对每个桶排序
        for (LinkedList<Double> linkedList : bucketList) {
            Collections.sort(linkedList);
        }

        // 输出数据
        int i = 0;
        for (LinkedList<Double> linkedList : bucketList) {
            for (Double number : linkedList) {
                arr[i++] = number;
            }
        }
    }

    /**
     * 计数排序
     *
     * @param arr 数组
     */
    public static int[] countSort(int[] arr) {
        if (arr == null) {
            return null;
        }
        int max = arr[0];
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        // 创建统计数组，并对每个数据进行计数
        int d = max - min;
        int[] countArr = new int[d + 1];
        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i] - min]++;
        }

        // 对统计数组累计求和
        int sum = 0;
        for (int i = 0; i < countArr.length; i++) {
            sum += countArr[i];
            countArr[i] = sum;
        }

        int[] sortArr = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            sortArr[countArr[arr[i] - min] - 1] = arr[i];
            countArr[arr[i] - min]--;
        }
        return sortArr;
    }

}
