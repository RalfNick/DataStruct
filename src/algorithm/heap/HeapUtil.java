package algorithm.heap;

import java.util.*;
import java.util.PriorityQueue;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-09-10 下午10:29
 **/
public class HeapUtil {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 合并K个排序链表 - 23
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     *
     * @param lists 链表
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length < 1) {
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (int i = 0; i < lists.length; i++) {
            queue.offer(lists[i]);
        }
        ListNode dummy = new ListNode(-1);
        ListNode curNode = dummy;

        while (!queue.isEmpty()) {
            ListNode listNode = queue.poll();
            curNode.next = new ListNode(listNode.val);
            curNode = curNode.next;
            if (listNode.next != null) {
                queue.offer(listNode.next);
            }
        }
        return dummy.next;
    }

    /**
     * 数组中的第K个最大元素 - 215
     * 在未排序的数组中找到第 k 个最大的元素。
     * 请注意，
     * k 个最大的元素，而不是第 k 个不同的元素。
     *
     * @param nums 数组
     * @param k    第 k 大
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        if (nums == null) {
            throw new NullPointerException("the arr is null");
        } else if (nums.length < k) {
            throw new IndexOutOfBoundsException("the k is out of  range");
        }
        MaxHeap<Integer> maxHeap = new MaxHeap<>(nums.length);
        for (int i : nums) {
            maxHeap.insert(i);
        }
        for (int i = 1; i < k; i++) {
            maxHeap.remove();
        }
        return maxHeap.peek();
    }

    /**
     * 滑动窗口最大值 - 239
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
     * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *
     * @param nums 数组
     * @param k    滑动窗口的长度
     * @return 滑动窗口返回最大值的数组
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 1) {
            return null;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                queue.offer(nums[i]);
                if (i == k - 1) {
                    result[i - k + 1] = queue.peek();
                }
            } else {
                queue.remove(nums[i - k]);
                queue.offer(nums[i]);
                result[i - k + 1] = queue.peek();
            }
        }
        return result;
    }

    /**
     * 数据流的中位数 - 295
     * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
     * <p>
     * [2,3,4] 的中位数是 3
     * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
     * 设计一个支持以下两种操作的数据结构：
     * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
     * double findMedian() - 返回目前所有元素的中位数。
     */
    static class MedianFinder {

        private PriorityQueue<Integer> maxQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        private PriorityQueue<Integer> minQueue = new PriorityQueue<>();

        public MedianFinder() {

        }

        public void addNum(int num) {
            minQueue.offer(num);
            if (minQueue.size() > maxQueue.size()) {
                maxQueue.offer(minQueue.poll());
            }
        }

        public double findMedian() {
            if (maxQueue.size() > minQueue.size()) {
                return maxQueue.peek();
            }
            return (maxQueue.peek() + minQueue.peek()) / 2.0;
        }
    }

    /**
     * 前 K 个高频元素 - 347
     *
     * @param nums 数组
     * @param k    前 k 个
     * @return
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return list;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }
        for (int i = 0; i < k; i++) {
            list.add(queue.poll().getKey());
        }
        return list;
    }

    /**
     * 根据字符出现频率排序 - 451
     *
     * @param s 字符串
     * @return
     */
    public static String frequencySort(String s) {
        if (s == null || s.equals("")) {
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(((o1, o2) -> o2.getValue() - o1.getValue()));
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            Map.Entry<Character, Integer> entry = queue.poll();
            for (int i = 0; i < entry.getValue(); i++) {
                builder.append(entry.getKey());
            }
        }
        return builder.toString();
    }

    /**
     * 前K个高频单词 - 692
     *
     * @param words 单词数组
     * @param k     前 k 个
     * @return
     */
    public static List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length < 1) {
            return new ArrayList<>();
        }
        Map<String, Integer> map = new TreeMap<>();
        for (String str : words) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(queue.poll().getKey());
        }
        return list;
    }
}
