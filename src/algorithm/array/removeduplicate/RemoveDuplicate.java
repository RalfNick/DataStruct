package algorithm.array.removeduplicate;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-04-30 下午7:59
 **/
public class RemoveDuplicate {

    /**
     * 移除数组中重复元素 nums = [3,2,2,3], val = 3 result = [2,2,2,3] len = 2
     *
     * @param arr 数组
     * @return 返回剩余元素的长度
     */
    public static int removeDuplicate(int[] arr, int value) {
        if (arr == null || arr.length < 1) return 0;
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != value) {
                arr[i] = arr[j];
                i++;
            }
        }
        return i;
    }

    /**
     * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * nums = [0,0,1,1,1,2,2,3,3,4], result = [0,1,2,3,4,] len = 5
     *
     * @param arr 数组
     * @return
     */
    public static int removeDuplicateOfSort(int[] arr) {
        if (arr == null || arr.length < 1) return 0;
        int i = 0;
        for (int j = 1; j < arr.length; j++) {
            if (arr[i] != arr[j]) {
                i++;
                arr[i] = arr[j];
            }
        }
        return i + 1;
    }
}
