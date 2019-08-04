package algorithm.array.firstmissingpositive;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-04-25 下午9:31
 **/
public class FirstMissingPositive {

    public static int findFirstPositive(int[] arr) {
        if (arr == null || arr.length < 1) {
            throw new NullPointerException("the arr is null or empty");
        }
        int result = -1;
        int i = 0;
        while (i < arr.length) {
            if (arr[i] > 0 && arr[i] <= arr.length && arr[i] != i + 1) {
                swap(arr, i, arr[i] - 1);
            } else {
                i++;
            }
        }

        for (i = 0; i < arr.length; i++) {
            if (i + 1 != arr[i]) {
                result = i + 1;
                break;
            }
        }
        return result;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
