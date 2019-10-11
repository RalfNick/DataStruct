package algorithm.linkedlist;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-05-17 上午8:18
 **/
public class RLinkedListTest {

    private RLinkedList.ListNode initLinkedList() {
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(4);
        RLinkedList.ListNode node5 = new RLinkedList.ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        return node1;
    }

    @Test
    public void swapPairs1() {
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        RLinkedList.ListNode node = RLinkedList.swapPairs1(node1);
        Assert.assertEquals(2, node.val);
        Assert.assertEquals(1, node.next.val);
        Assert.assertEquals(4, node.next.next.val);
        Assert.assertEquals(3, node.next.next.next.val);
    }

    @Test
    public void swapPairs2() {
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        RLinkedList.ListNode node = RLinkedList.swapPairs2(node1);
        Assert.assertEquals(2, node.val);
        Assert.assertEquals(1, node.next.val);
        Assert.assertEquals(4, node.next.next.val);
        Assert.assertEquals(3, node.next.next.next.val);
    }

    @Test
    public void testReverse() {
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        RLinkedList.ListNode node = RLinkedList.reverseList1(node1);
        Assert.assertEquals(4, node.val);
        Assert.assertEquals(3, node.next.val);
        Assert.assertEquals(2, node.next.next.val);
        Assert.assertEquals(1, node.next.next.next.val);
    }

    @Test
    public void testReverseKGroup() {
        RLinkedList.ListNode node1 = initLinkedList();

        RLinkedList.ListNode node = RLinkedList.reverseKGroup(node1, 2);
        Assert.assertEquals(2, node.val);
        Assert.assertEquals(1, node.next.val);
        Assert.assertEquals(4, node.next.next.val);
        Assert.assertEquals(3, node.next.next.next.val);
        Assert.assertEquals(5, node.next.next.next.next.val);

        node1 = initLinkedList();
        node = RLinkedList.reverseKGroup(node1, 3);
        Assert.assertEquals(3, node.val);
        Assert.assertEquals(2, node.next.val);
        Assert.assertEquals(1, node.next.next.val);
        Assert.assertEquals(4, node.next.next.next.val);
        Assert.assertEquals(5, node.next.next.next.next.val);
    }

    @Test
    public void testReverseKGroupII() {
        RLinkedList.ListNode node1 = initLinkedList();
        RLinkedList.ListNode node = RLinkedList.reverseKGroupII(node1, 2);
        Assert.assertEquals(1, node.val);
        Assert.assertEquals(3, node.next.val);
        Assert.assertEquals(2, node.next.next.val);
        Assert.assertEquals(5, node.next.next.next.val);
        Assert.assertEquals(4, node.next.next.next.next.val);

        node1 = initLinkedList();
        node = RLinkedList.reverseKGroupII(node1, 3);
        Assert.assertEquals(1, node.val);
        Assert.assertEquals(2, node.next.val);
        Assert.assertEquals(5, node.next.next.val);
        Assert.assertEquals(4, node.next.next.next.val);
        Assert.assertEquals(3, node.next.next.next.next.val);
    }

    @Test
    public void testRemoveNthFromEnd() {
        RLinkedList.ListNode node1 = initLinkedList();

//        RLinkedList.ListNode newNode = RLinkedList.removeNthFromEnd(node1,2);

        RLinkedList.ListNode newNode1 = RLinkedList.removeNthFromEnd(node1, 5);

        RLinkedList.ListNode newNode2 = RLinkedList.removeNthFromEnd(node1, 6);
    }

    @Test
    public void testMergeTwoSortedList() {

        RLinkedList.ListNode node1 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(4);
        node1.next = node2;
        node2.next = node3;

        RLinkedList.ListNode node4 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node5 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node6 = new RLinkedList.ListNode(4);
        node4.next = node5;
        node5.next = node6;

//        RLinkedList.ListNode node = RLinkedList.mergeTwoLists(node1, node4);
        RLinkedList.ListNode node = RLinkedList.mergeTwoLists1(node1, node4);

        Assert.assertEquals(1, node.val);
        Assert.assertEquals(1, node.next.val);
        Assert.assertEquals(2, node.next.next.val);
        Assert.assertEquals(3, node.next.next.next.val);
        Assert.assertEquals(4, node.next.next.next.next.val);
        Assert.assertEquals(4, node.next.next.next.next.next.val);
    }

    @Test
    public void testRotateRight() {
        RLinkedList.ListNode node1 = initLinkedList();

        RLinkedList.ListNode node = RLinkedList.rotateRight(node1, 2);

        Assert.assertEquals(4, node.val);
        Assert.assertEquals(5, node.next.val);
        Assert.assertEquals(1, node.next.next.val);
        Assert.assertEquals(2, node.next.next.next.val);
        Assert.assertEquals(3, node.next.next.next.next.val);
    }

    @Test
    public void testDeleteDuplicates() {
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(2);
        node1.next = node2;
        node2.next = node3;

//        RLinkedList.ListNode node = RLinkedList.deleteDuplicates(node1);
        RLinkedList.ListNode node = RLinkedList.deleteDuplicates1(node1);
        Assert.assertEquals(1, node.val);
        Assert.assertEquals(2, node.next.val);

        RLinkedList.ListNode node4 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node5 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node6 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node7 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node8 = new RLinkedList.ListNode(3);
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;

        RLinkedList.ListNode newNode = RLinkedList.deleteDuplicates(node4);
        Assert.assertEquals(1, newNode.val);
        Assert.assertEquals(2, newNode.next.val);
        Assert.assertEquals(3, newNode.next.next.val);
    }

    @Test
    public void testDeleteDuplicates2() {
//        RLinkedList.ListNode node1 = new RLinkedList.ListNode(1);
//        RLinkedList.ListNode node2 = new RLinkedList.ListNode(2);
//        RLinkedList.ListNode node3 = new RLinkedList.ListNode(3);
//        RLinkedList.ListNode node4 = new RLinkedList.ListNode(3);
//        RLinkedList.ListNode node5 = new RLinkedList.ListNode(4);
//        RLinkedList.ListNode node6 = new RLinkedList.ListNode(4);
//        RLinkedList.ListNode node7 = new RLinkedList.ListNode(5);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
//        node6.next = node7;
//
//        RLinkedList.ListNode node = RLinkedList.deleteDuplicates2(node1);
//        Assert.assertEquals(1, node.val);
//        Assert.assertEquals(2, node.next.val);
//        Assert.assertEquals(5, node.next.next.val);

        RLinkedList.ListNode node1 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node5 = new RLinkedList.ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        RLinkedList.ListNode newNode = RLinkedList.deleteDuplicates2(node1);
        Assert.assertEquals(2, newNode.val);
        Assert.assertEquals(3, newNode.next.val);
    }

    @Test
    public void testPartition() {
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(4);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node5 = new RLinkedList.ListNode(5);
        RLinkedList.ListNode node6 = new RLinkedList.ListNode(2);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        RLinkedList.ListNode newNode = RLinkedList.partition(node1, 3);
        Assert.assertEquals(1, newNode.val);
        Assert.assertEquals(2, newNode.next.val);
        Assert.assertEquals(2, newNode.next.next.val);
        Assert.assertEquals(4, newNode.next.next.next.val);
        Assert.assertEquals(3, newNode.next.next.next.next.val);
        Assert.assertEquals(5, newNode.next.next.next.next.next.val);
    }

    @Test
    public void testReverseList2() {
        RLinkedList.ListNode node1 = initLinkedList();

        RLinkedList.ListNode node = RLinkedList.reverseList2(node1, 2, 4);
        Assert.assertEquals(1, node.val);
        Assert.assertEquals(4, node.next.val);
        Assert.assertEquals(3, node.next.next.val);
        Assert.assertEquals(2, node.next.next.next.val);
        Assert.assertEquals(5, node.next.next.next.next.val);
    }

    @Test
    public void testSortedListToBST() {
        // 给定的有序链表： [-10, -3, 0, 5, 9],
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(-10);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(-3);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(0);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(5);
        RLinkedList.ListNode node5 = new RLinkedList.ListNode(9);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        RLinkedList.TreeNode treeNode = RLinkedList.sortedListToBST(node1);
        Assert.assertEquals(0, treeNode.val);
        Assert.assertEquals(-3, treeNode.left.val);
        Assert.assertEquals(-10, treeNode.left.left.val);
        Assert.assertEquals(9, treeNode.right.val);
        Assert.assertEquals(5, treeNode.right.left.val);
    }

    @Test
    public void testHasCircle() {
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(0);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;

        boolean result = RLinkedList.hasCycle(node1);
        Assert.assertTrue(result);
    }

    @Test
    public void detectCircle() {
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(0);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;

        RLinkedList.ListNode node = RLinkedList.detectCycle(node1);
        Assert.assertEquals(2, node.val);

        RLinkedList.ListNode node5 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node6 = new RLinkedList.ListNode(2);

        node5.next = node6;
        node6.next = node5;
        boolean result = RLinkedList.hasCycle(node5);
        Assert.assertTrue(result);

        RLinkedList.ListNode nod = RLinkedList.detectCycle(node5);
        Assert.assertEquals(1, nod.val);
    }

    @Test
    public void testReorderList() {
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        RLinkedList.ListNode node = RLinkedList.reorderList(node1);
        Assert.assertEquals(1, node.val);
        Assert.assertEquals(4, node.next.val);
        Assert.assertEquals(2, node.next.next.val);
        Assert.assertEquals(3, node.next.next.next.val);
    }

    @Test
    public void testSortList() {
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(4);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        RLinkedList.ListNode node = RLinkedList.sortList(node1);
        Assert.assertEquals(1, node.val);
        Assert.assertEquals(2, node.next.val);
        Assert.assertEquals(3, node.next.next.val);
        Assert.assertEquals(4, node.next.next.next.val);


        RLinkedList.ListNode node5 = new RLinkedList.ListNode(-1);
        RLinkedList.ListNode node6 = new RLinkedList.ListNode(5);
        RLinkedList.ListNode node7 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node8 = new RLinkedList.ListNode(4);
        RLinkedList.ListNode node9 = new RLinkedList.ListNode(0);

        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;

        RLinkedList.ListNode nod = RLinkedList.sortList(node5);
        Assert.assertEquals(-1, nod.val);
        Assert.assertEquals(0, nod.next.val);
        Assert.assertEquals(3, nod.next.next.val);
        Assert.assertEquals(4, nod.next.next.next.val);
        Assert.assertEquals(5, nod.next.next.next.next.val);
    }

    @Test
    public void testInsertionSortList() {
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(4);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        RLinkedList.ListNode node = RLinkedList.insertionSortList(node1);
        Assert.assertEquals(1, node.val);
        Assert.assertEquals(2, node.next.val);
        Assert.assertEquals(3, node.next.next.val);
        Assert.assertEquals(4, node.next.next.next.val);
    }

    @Test
    public void testFindBeginNode() {
        // list1
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(4);
        RLinkedList.ListNode node5 = new RLinkedList.ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        // list2
        RLinkedList.ListNode node6 = new RLinkedList.ListNode(6);
        RLinkedList.ListNode node7 = new RLinkedList.ListNode(7);
        node6.next = node7;
        node7.next = node4;

//        RLinkedList.ListNode resultNode = RLinkedList.findBeginNode(node1,node6);
//        Assert.assertNotNull(resultNode);

        RLinkedList.ListNode resultNode = RLinkedList.findBeginNode1(node1, node6);
        Assert.assertNotNull(resultNode);
    }

    @Test
    public void testRemoveElements() {
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(6);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node5 = new RLinkedList.ListNode(4);
        RLinkedList.ListNode node6 = new RLinkedList.ListNode(5);
        RLinkedList.ListNode node7 = new RLinkedList.ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        RLinkedList.ListNode node = RLinkedList.removeElements(node1, 6);
        Assert.assertEquals(1, node.val);
        Assert.assertEquals(2, node.next.val);
        Assert.assertEquals(3, node.next.next.val);
        Assert.assertEquals(4, node.next.next.next.val);
        Assert.assertEquals(5, node.next.next.next.next.val);
    }

    @Test
    public void testIsPalindrome() {
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node5 = new RLinkedList.ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        boolean result = RLinkedList.isPalindrome(node1);
        Assert.assertTrue(result);

        RLinkedList.ListNode node6 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node7 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node8 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node9 = new RLinkedList.ListNode(1);
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;

        boolean result1 = RLinkedList.isPalindrome(node6);
        Assert.assertTrue(result1);
    }

    @Test
    public void testOddEvenList() {
        // 1->2->3->4->5->NULL
        // 1->3->5->2->4->NULL
        RLinkedList.ListNode node1 = initLinkedList();

        RLinkedList.ListNode node = RLinkedList.oddEvenList(node1);
        Assert.assertEquals(1, node.val);
        Assert.assertEquals(3, node.next.val);
        Assert.assertEquals(5, node.next.next.val);
        Assert.assertEquals(2, node.next.next.next.val);
        Assert.assertEquals(4, node.next.next.next.next.val);

    }

    @Test
    public void testAddTwoNumbers2() {
        // 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
        // 输出: 7 -> 8 -> 0 -> 7

        RLinkedList.ListNode node1 = new RLinkedList.ListNode(7);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(4);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        RLinkedList.ListNode node6 = new RLinkedList.ListNode(5);
        RLinkedList.ListNode node7 = new RLinkedList.ListNode(6);
        RLinkedList.ListNode node8 = new RLinkedList.ListNode(4);
        node6.next = node7;
        node7.next = node8;

        RLinkedList.ListNode node = RLinkedList.addTwoNumbers2(node1, node6);
        Assert.assertEquals(7, node.val);
        Assert.assertEquals(8, node.next.val);
        Assert.assertEquals(0, node.next.next.val);
        Assert.assertEquals(7, node.next.next.next.val);
    }

    @Test
    public void testNextLargerNodes() {
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(7);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(4);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node5 = new RLinkedList.ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        int[] result = RLinkedList.nextLargerNodes(node1);
        Assert.assertArrayEquals(new int[]{7, 0, 5, 5, 0}, result);
    }

    @Test
    public void testQuickSortList() {
        RLinkedList.ListNode node1 = new RLinkedList.ListNode(8);
        RLinkedList.ListNode node2 = new RLinkedList.ListNode(6);
        RLinkedList.ListNode node3 = new RLinkedList.ListNode(9);
        RLinkedList.ListNode node4 = new RLinkedList.ListNode(2);
        RLinkedList.ListNode node5 = new RLinkedList.ListNode(1);
        RLinkedList.ListNode node6 = new RLinkedList.ListNode(4);
        RLinkedList.ListNode node7 = new RLinkedList.ListNode(7);
        RLinkedList.ListNode node8 = new RLinkedList.ListNode(3);
        RLinkedList.ListNode node9 = new RLinkedList.ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;

        RLinkedList.ListNode node = RLinkedList.quickSortList(node1);
        Assert.assertEquals(1, node.val);
        Assert.assertEquals(2, node.next.val);
        Assert.assertEquals(3, node.next.next.val);
        Assert.assertEquals(4, node.next.next.next.val);
        Assert.assertEquals(5, node.next.next.next.next.val);
        Assert.assertEquals(6, node.next.next.next.next.next.val);
        Assert.assertEquals(7, node.next.next.next.next.next.next.val);
        Assert.assertEquals(8, node.next.next.next.next.next.next.next.val);
        Assert.assertEquals(9, node.next.next.next.next.next.next.next.next.val);
    }
}
