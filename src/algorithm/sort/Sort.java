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
     * 冒泡优化，已经有序，就不结束排序
     *
     * @param arr 数组
     */
    public static void bubbleSort1(int[] arr) {
        if (arr == null) {
            return;
        }
        int border = arr.length - 1;
        int lastIndex = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            boolean sorted = true;
            for (int j = 0; j < border; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    sorted = false;
                    lastIndex = j;
                }
            }
            border = lastIndex;
            if (sorted) {
                break;
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
            if (min != i) {
                swap(arr, min, i);
            }
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
        for (double v : arr) {
            int index = (int) ((v - min) / d * (bucketNum - 1));
            bucketList.get(index).add(v);
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
     * 桶排序 - 整数
     *
     * @param arr 数组
     */
    public static void bucketSort(int[] arr, int bucketSize) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int bucketCount = (max - min) / bucketSize + 1;
        List<List<Integer>> bucketLists = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            bucketLists.add(new ArrayList<>());
        }
        for (int anArr : arr) {
            int index = (anArr - min) / bucketSize;
            bucketLists.get(index).add(anArr);
        }
        int k = 0;
        for (List<Integer> list : bucketLists) {
            if (list.size() > 0) {
                Collections.sort(list);
                for (int item : list) {
                    arr[k++] = item;
                }
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

    /**
     * 归并排序
     *
     * @param arr 数组
     */
    public static void mergeSort(int[] arr) {
        if (arr == null) {
            return;
        }
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) >> 1;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
//        while(i<=mid){//将左边剩余元素填充进temp中
//            temp[t++] = arr[i++];
//        }
//        while(j<=right){//将右序列剩余元素填充进temp中
//            temp[t++] = arr[j++];
//        }
//        t = 0;
//        //将temp中的元素全部拷贝到原数组中
//        while(left <= right){
//            arr[left++] = temp[t++];
//        }
        if (i <= mid) {
            System.arraycopy(arr, i, temp, t, mid - i + 1);
            t += mid - i;
        }
        if (j <= right) {
            System.arraycopy(arr, j, temp, t, right - j + 1);
            t += right - j;
        }
        if (left <= right) {
            System.arraycopy(temp, 0, arr, left, t + 1);
        }
    }

}
