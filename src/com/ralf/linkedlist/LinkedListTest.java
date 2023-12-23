package com.ralf.linkedlist;

import org.junit.Assert;
import org.junit.Test;

/**
 * 链表测试
 */
public class LinkedListTest {

    private LinkedList.ListNode createLinkedList() {
        LinkedList.ListNode node1 = new LinkedList.ListNode(1);
        LinkedList.ListNode node2 = new LinkedList.ListNode(2);
        LinkedList.ListNode node3 = new LinkedList.ListNode(3);
        LinkedList.ListNode node4 = new LinkedList.ListNode(4);
        LinkedList.ListNode node5 = new LinkedList.ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        return node1;
    }

    private LinkedList.ListNode createLinkedList1() {
        LinkedList.ListNode node1 = new LinkedList.ListNode(1);
        LinkedList.ListNode node2 = new LinkedList.ListNode(2);
        LinkedList.ListNode node3 = new LinkedList.ListNode(3);
        LinkedList.ListNode node4 = new LinkedList.ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        return node1;
    }

    @Test
    public void revert() {
        LinkedList.ListNode head = createLinkedList();

        String originString = LinkedList.print(head);
        System.out.println("原始列表：" + originString);

        // 迭代
//        LinkedList.ListNode newHead = LinkedList.revert(head);
        // 递归
        LinkedList.ListNode newHead = LinkedList.revert1(head);

        Assert.assertEquals(5, newHead.val);
        Assert.assertEquals(4, newHead.next.val);
        Assert.assertEquals(3, newHead.next.next.val);
        Assert.assertEquals(2, newHead.next.next.next.val);
        Assert.assertEquals(1, newHead.next.next.next.next.val);

        String newHeadString = LinkedList.print(newHead);
        System.out.println("新的列表：" + newHeadString);
    }

    /**
     * 如果是偶数找到中间第一个节点
     */
    @Test
    public void findMiddle() {
        LinkedList.ListNode head1 = createLinkedList();
        String originString1 = LinkedList.print(head1);
        System.out.println("原始列表1：" + originString1);

        LinkedList.ListNode middle1 = LinkedList.findMiddle(head1);
        Assert.assertEquals(3, middle1.val);

        LinkedList.ListNode head2 = createLinkedList1();
        String originString2 = LinkedList.print(head2);
        System.out.println("原始列表2：" + originString2);

        LinkedList.ListNode middle2 = LinkedList.findMiddle(head2);
        Assert.assertEquals(2, middle2.val);
    }

    /**
     * 如果是偶数找到中间第二个节点
     */
    @Test
    public void findMiddle1() {
        LinkedList.ListNode head1 = createLinkedList();
        String originString1 = LinkedList.print(head1);
        System.out.println("原始列表1：" + originString1);

        LinkedList.ListNode middle1 = LinkedList.middleNode(head1);
        Assert.assertEquals(3, middle1.val);

        LinkedList.ListNode head2 = createLinkedList1();
        String originString2 = LinkedList.print(head2);
        System.out.println("原始列表2：" + originString2);

        LinkedList.ListNode middle2 = LinkedList.middleNode(head2);
        Assert.assertEquals(3, middle2.val);
    }

    @Test
    public void testPalindrome() {
        LinkedList.ListNode node1 = new LinkedList.ListNode(1);
        LinkedList.ListNode node2 = new LinkedList.ListNode(2);
        LinkedList.ListNode node3 = new LinkedList.ListNode(2);
        LinkedList.ListNode node4 = new LinkedList.ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        String originString1 = LinkedList.print(node1);
        System.out.println("原始列表1：" + originString1);

        boolean result1 = LinkedList.isPalindrome1(node1);
        Assert.assertTrue(result1);

        LinkedList.ListNode node5 = new LinkedList.ListNode(2);
        LinkedList.ListNode node6 = new LinkedList.ListNode(3);
        LinkedList.ListNode node7 = new LinkedList.ListNode(4);
        LinkedList.ListNode node8 = new LinkedList.ListNode(3);
        LinkedList.ListNode node9 = new LinkedList.ListNode(2);
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;

        String originString2 = LinkedList.print(node5);
        System.out.println("原始列表2：" + originString2);

        boolean result2 = LinkedList.isPalindrome1(node5);
        Assert.assertTrue(result2);
    }

    private LinkedList.ListNode createCycleList() {
        LinkedList.ListNode node1 = new LinkedList.ListNode(3);
        LinkedList.ListNode node2 = new LinkedList.ListNode(2);
        LinkedList.ListNode node3 = new LinkedList.ListNode(0);
        LinkedList.ListNode node4 = new LinkedList.ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        return node1;
    }

    @Test
    public void testHasCycle() {
        LinkedList.ListNode node1 = createCycleList();
        boolean hasCycle = LinkedList.hasCycle(node1);
        Assert.assertTrue(hasCycle);

        LinkedList.ListNode node5 = new LinkedList.ListNode(1);
        boolean hasCycle1 = LinkedList.hasCycle(node5);
        Assert.assertFalse(hasCycle1);

        LinkedList.ListNode node6 = new LinkedList.ListNode(1);
        node6.next = new LinkedList.ListNode(2);

        boolean hasCycle3 = LinkedList.hasCycle(node6);
        Assert.assertFalse(hasCycle3);
    }

    @Test
    public void testDetectCycle() {
        LinkedList.ListNode node1 = createCycleList();
        LinkedList.ListNode resultNode1 = LinkedList.detectCycle(node1);
        Assert.assertEquals(resultNode1.val, 2);

        LinkedList.ListNode node5 = new LinkedList.ListNode(1);
        LinkedList.ListNode resultNode2 = LinkedList.detectCycle(node5);
        Assert.assertNull(resultNode2);

        LinkedList.ListNode node6 = new LinkedList.ListNode(1);
        node6.next = new LinkedList.ListNode(2);
        LinkedList.ListNode resultNode3 = LinkedList.detectCycle(node6);
        Assert.assertNull(resultNode3);
    }

    @Test
    public void testIntersectionNode() {
        // 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
        LinkedList.ListNode node1 = new LinkedList.ListNode(4);
        LinkedList.ListNode node2 = new LinkedList.ListNode(1);
        LinkedList.ListNode node3 = new LinkedList.ListNode(8);
        LinkedList.ListNode node4 = new LinkedList.ListNode(4);
        LinkedList.ListNode node5 = new LinkedList.ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        System.out.println(LinkedList.print(node1));

        LinkedList.ListNode node6 = new LinkedList.ListNode(5);
        LinkedList.ListNode node7 = new LinkedList.ListNode(6);
        LinkedList.ListNode node8 = new LinkedList.ListNode(1);

        node6.next = node7;
        node7.next = node8;
        node8.next = node3;

        System.out.println(LinkedList.print(node6));

        LinkedList.ListNode node = LinkedList.getIntersectionNode(node1, node6);
        Assert.assertEquals(node.val, 8);
    }

    @Test
    public void testRemoveNthFromEnd() {
        // 输入：head = [1,2,3,4,5], n = 2
        LinkedList.ListNode node1 = createLinkedList();
        System.out.println(LinkedList.print(node1));
        LinkedList.ListNode node = LinkedList.removeNthFromEnd(node1, 5);
        System.out.println(LinkedList.print(node));

        LinkedList.ListNode node2 = new LinkedList.ListNode(1);
        LinkedList.ListNode node3 = LinkedList.removeNthFromEnd(node2, 1);
        System.out.println(LinkedList.print(node3));

        LinkedList.ListNode node4 = new LinkedList.ListNode(1);
        node4.next = new LinkedList.ListNode(2);
        System.out.println(LinkedList.print(node4));
        LinkedList.ListNode node5 = LinkedList.removeNthFromEnd(node4, 2);
        System.out.println(LinkedList.print(node5));
    }

    @Test
    public void testKthToLast() {
        // 输入： 1->2->3->4->5 和 k = 2
        LinkedList.ListNode node1 = createLinkedList();
        System.out.println(LinkedList.print(node1));
        int result = LinkedList.kthToLast(node1, 2);
        Assert.assertEquals(result, 4);

        LinkedList.ListNode node4 = new LinkedList.ListNode(1);
        node4.next = new LinkedList.ListNode(2);
        System.out.println(LinkedList.print(node4));
        int result2 = LinkedList.kthToLast(node4, 1);
        Assert.assertEquals(result2, 2);

    }

    @Test
    public void testAddTwoNumbers() {
        // 输入：l1 = [2,4,3], l2 = [5,6,4]
        // 输出：[7,0,8]
        LinkedList.ListNode node1 = new LinkedList.ListNode(2);
        node1.next = new LinkedList.ListNode(4);
        node1.next.next = new LinkedList.ListNode(3);
        System.out.println(LinkedList.print(node1));

        LinkedList.ListNode node2 = new LinkedList.ListNode(5);
        node2.next = new LinkedList.ListNode(6);
        node2.next.next = new LinkedList.ListNode(4);
        System.out.println(LinkedList.print(node2));

        LinkedList.ListNode result = LinkedList.addTwoNumbers(node1, node2);
        System.out.println(LinkedList.print(result));
    }

    @Test
    public void testAddTwoNumbersII() {
        // 输入：l1 = [7,2,4,3], l2 = [5,6,4]
        // 输出：[7,8,0,7]
        LinkedList.ListNode node1 = new LinkedList.ListNode(7);
        node1.next = new LinkedList.ListNode(2);
        node1.next.next = new LinkedList.ListNode(4);
        node1.next.next.next = new LinkedList.ListNode(3);
        System.out.println(LinkedList.print(node1));

        LinkedList.ListNode node2 = new LinkedList.ListNode(5);
        node2.next = new LinkedList.ListNode(6);
        node2.next.next = new LinkedList.ListNode(4);
        System.out.println(LinkedList.print(node2));

        LinkedList.ListNode result = LinkedList.addTwoNumbersII(node1, node2);
        System.out.println(LinkedList.print(result));
    }

    @Test
    public void testRemoveElements() {
        // 输入：head = [1,2,6,3,4,5,6], val = 6
        // 输出：[1,2,3,4,5]
        LinkedList.ListNode node1 = new LinkedList.ListNode(1);
        node1.next = new LinkedList.ListNode(2);
        node1.next.next = new LinkedList.ListNode(6);
        node1.next.next.next = new LinkedList.ListNode(3);
        node1.next.next.next.next = new LinkedList.ListNode(4);
        node1.next.next.next.next.next = new LinkedList.ListNode(5);
        node1.next.next.next.next.next.next = new LinkedList.ListNode(6);
        System.out.println(LinkedList.print(node1));

        LinkedList.ListNode resultNode = LinkedList.removeElements(node1, 6);
        System.out.println(LinkedList.print(resultNode));

        LinkedList.ListNode node2 = new LinkedList.ListNode(7);
        node2.next = new LinkedList.ListNode(7);
        node2.next.next = new LinkedList.ListNode(7);
        node2.next.next.next = new LinkedList.ListNode(7);
        System.out.println(LinkedList.print(node2));

        LinkedList.ListNode resultNode1 = LinkedList.removeElements(node2, 7);
        System.out.println(LinkedList.print(resultNode1));
    }

    @Test
    public void testDeleteDuplicates() {
        // 输入：head = [1,1,2,3,3]
        // 输出：[1,2,3]
        LinkedList.ListNode node1 = new LinkedList.ListNode(1);
        node1.next = new LinkedList.ListNode(1);
        node1.next.next = new LinkedList.ListNode(2);
        node1.next.next.next = new LinkedList.ListNode(3);
        node1.next.next.next.next = new LinkedList.ListNode(3);
        System.out.println(LinkedList.print(node1));

        LinkedList.ListNode resultNode = LinkedList.deleteDuplicates(node1);
        System.out.println(LinkedList.print(resultNode));

        // 输入：head = [1,1,2]
        // 输出：[1,2]
        LinkedList.ListNode node2 = new LinkedList.ListNode(1);
        node2.next = new LinkedList.ListNode(1);
        node2.next.next = new LinkedList.ListNode(2);
        System.out.println(LinkedList.print(node2));

        LinkedList.ListNode resultNode1 = LinkedList.deleteDuplicates(node2);
        System.out.println(LinkedList.print(resultNode1));
    }

    @Test
    public void testDeleteDuplicatesII() {
        // 输入：head = [1,2,3,3,4,4,5]
        // 输出：[1,2,5]
        LinkedList.ListNode node1 = new LinkedList.ListNode(1);
        node1.next = new LinkedList.ListNode(2);
        node1.next.next = new LinkedList.ListNode(3);
        node1.next.next.next = new LinkedList.ListNode(3);
        node1.next.next.next.next = new LinkedList.ListNode(4);
        node1.next.next.next.next.next = new LinkedList.ListNode(4);
        node1.next.next.next.next.next.next = new LinkedList.ListNode(5);
        System.out.println(LinkedList.print(node1));

        LinkedList.ListNode resultNode = LinkedList.deleteDuplicatesII(node1);
        System.out.println(LinkedList.print(resultNode));

        // 输入：head = [1,1,1,2,3]
        // 输出：[2,3]
        LinkedList.ListNode node2 = new LinkedList.ListNode(1);
        node2.next = new LinkedList.ListNode(1);
        node2.next.next = new LinkedList.ListNode(1);
        node2.next.next.next = new LinkedList.ListNode(2);
        node2.next.next.next.next = new LinkedList.ListNode(3);
        System.out.println(LinkedList.print(node2));

        LinkedList.ListNode resultNode1 = LinkedList.deleteDuplicatesII(node2);
        System.out.println(LinkedList.print(resultNode1));
    }

    @Test
    public void testRotateRight() {
        // 输入：head = [1,2,3,4,5], k = 2
        // 输出：[4,5,1,2,3]
        LinkedList.ListNode node1 = new LinkedList.ListNode(1);
        node1.next = new LinkedList.ListNode(2);
        node1.next.next = new LinkedList.ListNode(3);
        node1.next.next.next = new LinkedList.ListNode(4);
        node1.next.next.next.next = new LinkedList.ListNode(5);
        System.out.println(LinkedList.print(node1));

        LinkedList.ListNode resultNode = LinkedList.rotateRight(node1, 2);
        System.out.println(LinkedList.print(resultNode));
    }

    @Test
    public void testMergeKLists() {
        // 输入：lists = [[1,4,5],[1,3,4],[2,6]]
        // 输出：[1,1,2,3,4,4,5,6]
        LinkedList.ListNode node1 = new LinkedList.ListNode(1);
        node1.next = new LinkedList.ListNode(4);
        node1.next.next = new LinkedList.ListNode(5);
        System.out.println(LinkedList.print(node1));

        LinkedList.ListNode node2 = new LinkedList.ListNode(1);
        node2.next = new LinkedList.ListNode(3);
        node2.next.next = new LinkedList.ListNode(4);
        System.out.println(LinkedList.print(node2));

        LinkedList.ListNode node3 = new LinkedList.ListNode(2);
        node3.next = new LinkedList.ListNode(6);
        System.out.println(LinkedList.print(node3));

        LinkedList.ListNode resultNode = LinkedList.mergeKLists3(new LinkedList.ListNode[]{node1, node2, node3});
        System.out.println(LinkedList.print(resultNode));
    }

    @Test
    public void testReverseKGroupII() {
        LinkedList.ListNode node1 = new LinkedList.ListNode(1);
        node1.next = new LinkedList.ListNode(2);
        node1.next.next = new LinkedList.ListNode(3);
        node1.next.next.next = new LinkedList.ListNode(4);
        node1.next.next.next.next = new LinkedList.ListNode(5);
        System.out.println(LinkedList.print(node1));

        LinkedList.ListNode node = LinkedList.reverseKGroupII(node1, 2);
        System.out.println(LinkedList.print(node));

        Assert.assertEquals(1, node.val);
        Assert.assertEquals(3, node.next.val);
        Assert.assertEquals(2, node.next.next.val);
        Assert.assertEquals(5, node.next.next.next.val);
        Assert.assertEquals(4, node.next.next.next.next.val);

        LinkedList.ListNode node2 = new LinkedList.ListNode(1);
        node2.next = new LinkedList.ListNode(2);
        node2.next.next = new LinkedList.ListNode(3);
        node2.next.next.next = new LinkedList.ListNode(4);
        node2.next.next.next.next = new LinkedList.ListNode(5);

        LinkedList.ListNode node3 = LinkedList.reverseKGroupII(node2, 3);
        System.out.println(LinkedList.print(node3));

        Assert.assertEquals(1, node3.val);
        Assert.assertEquals(2, node3.next.val);
        Assert.assertEquals(5, node3.next.next.val);
        Assert.assertEquals(4, node3.next.next.next.val);
        Assert.assertEquals(3, node3.next.next.next.next.val);
    }

}
