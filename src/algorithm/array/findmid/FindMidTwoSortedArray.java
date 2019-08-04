package algorithm.array.findmid;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-04-30 下午8:50
 **/
public class FindMidTwoSortedArray {

    /**
     * 两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n))
     *
     * @param arrM 数组
     * @param arrN 数组
     * @return
     */
    public static double findMidOfTwoSortedArray(int[] arrM, int[] arrN) {
        if (arrM.length > arrN.length) {
            int[] tempArr = arrM;
            arrM = arrN;
            arrN = tempArr;
        }
        int m = arrM.length;
        int n = arrN.length;
        int half = (m + n + 1) / 2;
        int iMin = 0;
        int iMax = m;

        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = half - i;
            if (i < iMax && arrM[i] < arrN[j - 1]) {
                iMin++;
            } else if (i > iMin && arrM[i - 1] > arrM[j]) {
                iMax--;
            } else {
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = arrN[j - 1];
                } else if (j == 0) {
                    maxLeft = arrM[i - 1];
                } else {
                    maxLeft = Math.max(arrM[i - 1], arrN[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                } else {
                    int minRight = 0;
                    if (i == m) {
                        minRight = arrN[j];
                    } else if (j == n) {
                        minRight = arrM[i];
                    } else {
                        minRight = Math.min(arrM[i], arrN[j]);
                    }
                    return (maxLeft + minRight) / 2.0;
                }
            }
        }
        return 0.0;
    }
}
