package algorithm.heap;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-09-10 下午10:49
 **/
public class HeapUtilTest {

    @Test
    public void testMergeKLists() {
        HeapUtil.ListNode node1 = new HeapUtil.ListNode(1);
        HeapUtil.ListNode node2 = new HeapUtil.ListNode(4);
        HeapUtil.ListNode node3 = new HeapUtil.ListNode(5);
        node1.next = node2;
        node2.next = node3;

        HeapUtil.ListNode node4 = new HeapUtil.ListNode(1);
        HeapUtil.ListNode node5 = new HeapUtil.ListNode(3);
        HeapUtil.ListNode node6 = new HeapUtil.ListNode(4);
        node4.next = node5;
        node5.next = node6;

        HeapUtil.ListNode node7 = new HeapUtil.ListNode(2);
        HeapUtil.ListNode node8 = new HeapUtil.ListNode(6);
        node7.next = node8;

        HeapUtil.ListNode[] listNodes = {node1, node4, node7};
        HeapUtil.ListNode listNode = HeapUtil.mergeKLists(listNodes);
        Assert.assertTrue(listNode != null);

        Assert.assertEquals(1, listNode.val);
        Assert.assertEquals(1, listNode.next.val);
        Assert.assertEquals(2, listNode.next.next.val);
        Assert.assertEquals(3, listNode.next.next.next.val);
        Assert.assertEquals(4, listNode.next.next.next.next.val);
        Assert.assertEquals(4, listNode.next.next.next.next.next.val);
        Assert.assertEquals(5, listNode.next.next.next.next.next.next.val);
        Assert.assertEquals(6, listNode.next.next.next.next.next.next.next.val);
    }

    @Test
    public void findKthLargest() {
        // 输入: [3,2,1,5,6,4] 和 k = 2
        // 输出: 5
        int[] arr1 = {3, 2, 1, 5, 6, 4};
        Assert.assertEquals(5, HeapUtil.findKthLargest(arr1, 2));

        // 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
        // 输出: 4
        int[] arr2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        Assert.assertEquals(4, HeapUtil.findKthLargest(arr2, 4));
    }

    @Test
    public void maxSlidingWindow() {
        // 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
        // 输出: [3,3,5,5,6,7]
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] result = {3, 3, 5, 5, 6, 7};
        Assert.assertArrayEquals(result, HeapUtil.maxSlidingWindow(nums, 3));
    }

    @Test
    public void findMedian() {
        HeapUtil.MedianFinder medianFinder = new HeapUtil.MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        Assert.assertEquals(1.5, medianFinder.findMedian(), 0.0001);
        medianFinder.addNum(3);
        Assert.assertEquals(2, medianFinder.findMedian(), 0.0001);
    }

    @Test
    public void testTopKFrequent() {
        // 输入: nums = [1,1,1,2,2,3], k = 2
        // 输出: [1,2]
        int[] nums = {1, 1, 1, 2, 2, 3};
        List<Integer> topKFrequent = HeapUtil.topKFrequent(nums, 2);
        Assert.assertEquals(2, topKFrequent.size());
        Assert.assertEquals(1, topKFrequent.get(0).intValue());
        Assert.assertEquals(2, topKFrequent.get(1).intValue());
    }

    @Test
    public void testFrequencySort() {
        // intput "tree" // output "eert"
        Assert.assertEquals("eert", HeapUtil.frequencySort("tree"));
        Assert.assertEquals("aaaccc", HeapUtil.frequencySort("cccaaa"));
        Assert.assertEquals("bbAa", HeapUtil.frequencySort("Aabb"));
    }

    @Test
    public void testTopKFrequentWords() {
        // 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
        // 输出: ["i", "love"]
        List<String> stringList = HeapUtil.topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2);
        Assert.assertArrayEquals(new String[]{"i", "love"}, stringList.toArray());

        // 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
        // 输出: ["the", "is", "sunny", "day"]
        List<String> stringList1 = HeapUtil.topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4);
        Assert.assertArrayEquals(new String[]{"the", "is", "sunny", "day"}, stringList1.toArray());
    }
}
