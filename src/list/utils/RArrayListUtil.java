package list.utils;

import list.RList;
import list.arraylist.RArrayList;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-11-26 下午1:43
 **/
public class RArrayListUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     */
    public static void reverseArray(int[] origin) {
        if (origin == null || origin.length < 1) {
            return;
        }
        for (int i = 0; i < origin.length / 2; i++) {
            swap(origin, i, origin.length - i - 1);
        }
    }

    private static void swap(int[] list, int indexA, int indexB) {

        if (list != null && indexA < list.length && indexB < list.length) {
            int temp = list[indexA];
            list[indexA] = list[indexB];
            list[indexB] = temp;
        }
    }

    /**
     * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     */
    public static int[] removeZero(int[] oldArray) {
        if (oldArray == null || oldArray.length < 1) return null;

        int count = 0;
        for (int i : oldArray) {
            if (i != 0) count++;
        }
        int[] newArray = new int[count];
        int i = 0;
        for (int val : oldArray) {
            if (val != 0) {
                newArray[i++] = val;
            }
        }
        return newArray;
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     */
    public static int[] merge(int[] array1, int[] array2) {

        if (array1 == null || array1.length < 1) {
            return array2;
        }
        if (array2 == null || array2.length < 1) {
            return array1;
        }
        int[] mergeArray = new int[array1.length + array2.length];

        int index1 = 0;
        int index2 = 0;
        int mergeLength = 0;
        for (int i = 0; i < mergeArray.length; i++) {

            if (index1 == array1.length) {
                mergeLength += array2.length - index2;
                System.arraycopy(array2, index2, mergeArray, i, array2.length - index2);
                break;
            } else if (index2 == array2.length) {
                mergeLength += array1.length - index1;
                System.arraycopy(array1, index1, mergeArray, i, array1.length - index1);
                break;

            } else {
                int num1 = array1[index1];
                int num2 = array2[index2];
                mergeLength++;
                if (num1 > num2) {
                    mergeArray[i] = num2;
                    index2++;
                } else if (num1 < num2) {
                    mergeArray[i] = num1;
                    index1++;
                } else {
                    mergeArray[i] = num1;
                    index1++;
                    index2++;
                }
            }

        }
        // 清除0部分
        int[] result = new int[mergeLength];
        System.arraycopy(mergeArray, 0, result, 0, result.length);
        return result;
    }

    /**
     * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
     * 注意，老数组的元素在新数组中需要保持
     * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
     * [2,3,6,0,0,0]
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] grow(T[] oldArray, int size) {

        if (oldArray == null || oldArray.length < 1) {
            return null;
        }
        T[] newArray = (T[]) new Object[oldArray.length + size];
        int i = 0;
        for (T item : oldArray) {
            newArray[i++] = item;
        }
        return newArray;
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     */
    public static int[] fibonacci(int max) {
        if (max < 2) {
            return null;
        }

        RList<Integer> list = new RArrayList<>(max);
        // 第一个数
        list.add(1);
        getFibonacci(1, 1, max, list);

        return listToArr(list);
    }

    private static void getFibonacci(int a, int b, int max, RList<Integer> list) {
        if (b > max) {
            return;
        }
        list.add(b);
        getFibonacci(b, a + b, max, list);
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     */
    public static int[] getPrimes(int max) {

        if (max < 2) {
            return null;
        }
        RList<Integer> list = new RArrayList<>();
        for (int i = 2; i < max; i++) {
            if (isPrime(i)) {
                list.add(i);
            }
        }
        return listToArr(list);
    }

    private static int[] listToArr(RList<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    private static boolean isPrime(int num) {

        if (num < 4) {
            return true;
        }
        boolean isPrime = true;
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     */
    public static int[] getPerfectNumbers(int max) {

        if (max < 2) {
            return null;
        }
        RList<Integer> list = new RArrayList<>();
        for (int i = 2; i <= max; i++) {
            if (isPerfectNumber(i)) {
                list.add(i);
            }
        }
        return listToArr(list);
    }

    private static boolean isPerfectNumber(int num) {

        if (num < 2) {
            return false;
        }
        int sum = 1;
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        if (num == sum) {
            return true;
        }
        return false;
    }


    /**
     * 用separator 把数组 array给连接起来
     * 例如array= [3,8,9], separator = "-"
     * 则返回值为"3-8-9"
     */
    public static String join(int[] array, String separator) {

        if (array == null || array.length < 1) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                builder.append(array[i]);
            } else {
                builder.append(array[i]).append(separator);
            }
        }
        return builder.toString();
    }
}
