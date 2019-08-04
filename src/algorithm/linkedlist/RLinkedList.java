package algorithm.linkedlist;

import java.util.*;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-05-17 上午8:18
 **/
public class RLinkedList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode random;
        ListNode pre;

        ListNode(int x) {
            val = x;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 两数相加   - 2
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultHead = new ListNode(-1);
        ListNode cur = resultHead;
        int a = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int result = (a + x + y) % 10;
            a = (x + y + a) / 10;
            ListNode node = new ListNode(result);
            cur.next = node;
            cur = node;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (a > 0) {
            cur.next = new ListNode(a);
        }
        return resultHead.next;
    }

    /**
     * 移除倒数第 n 个节点  - 19
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 n = 2
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     *
     * @param head 头节点
     * @param n    倒数第 n 个节点
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null) {
            if (n < 0) {
                slow = slow.next;
            }
            fast = fast.next;
            n--;
        }
        if (n > 0) {
            return head;
        } else if (n == 0) {
            head = head.next;
            return head;
        }
        ListNode temp = slow.next;
        slow.next = temp.next;
        temp.next = null;
        return head;
    }

    /**
     * 合并有序链表 - 21
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * @param l1 链表一
     * @param l2 链表二
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode newNode = new ListNode(-1);
        ListNode cur = newNode;
        while (l1 != null && l2 != null) {
            ListNode node = new ListNode(-1);
            if (l1.val < l2.val) {
                node.val = l1.val;
                l1 = l1.next;
            } else {
                node.val = l2.val;
                l2 = l2.next;
            }
            cur.next = node;
            cur = node;
        }
        while (l1 != null) {
            cur.next = new ListNode(l1.val);
            cur = cur.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            cur.next = new ListNode(l2.val);
            cur = cur.next;
            l2 = l2.next;
        }

        return newNode.next;
    }

    /**
     * 合并有序链表 - 21
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * @param l1 链表一
     * @param l2 链表二
     * @return
     */
    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists1(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists1(l1, l2.next);
            return l2;
        }
    }

    /**
     * 合并 K 个有序链表  - 23
     *
     * @param lists 链表集合
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length < 1) {
            return null;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode node : lists) {
            priorityQueue.offer(node);
        }
        ListNode sortedNode = new ListNode(-1);
        ListNode cur = sortedNode;
        while (!priorityQueue.isEmpty()) {
            ListNode node = priorityQueue.poll();
            ListNode newNode = new ListNode(node.val);
            cur.next = newNode;
            cur = newNode;
            if (node.next != null) {
                priorityQueue.offer(node);
            }
        }
        return sortedNode.next;
    }

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 - 24
     *
     * @param head 头指针
     * @return
     */
    public static ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode second = head.next;
        ListNode third = second.next;
        second.next = head;
        head.next = swapPairs1(third);
        return second;
    }

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * @param head 头指针
     * @return
     */
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode current = head;
        ListNode pre = null;

        while (current != null && current.next != null) {
            ListNode temp = current.next;
            current.next = temp.next;
            temp.next = current;
            if (pre != null) {
                pre.next = temp;
            } else {
                head = temp;
            }
            pre = current;
            current = current.next;
        }
        return head;
    }

    /**
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        return null;
    }

    /**
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int len = 1;
        ListNode cur = head;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }
        cur.next = head;
        ListNode temp = head;
        for (int i = 1; i < len - k % len; i++) {
            temp = temp.next;
        }
        head = temp.next;
        temp.next = null;
        return head;
    }

    /**
     * 移除相同元素 - 83
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     * 输入: 1->1->2
     * 输出: 1->2
     * 示例 2:
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     *
     * @param head 头指针
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val != pre.val) {
                pre = cur;
                cur = cur.next;
            } else {
                ListNode temp = cur;
                pre.next = cur.next;
                cur = cur.next;
                temp.next = null;
            }
        }
        return head;
    }

    /**
     * 移除相同元素 - 83
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     * 输入: 1->1->2
     * 输出: 1->2
     * 示例 2:
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     *
     * @param head 头指针
     * @return
     */
    public static ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * 移除相同元素 - 82
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     * 示例 1:
     * <p>
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     * 示例 2:
     * <p>
     * 输入: 1->1->1->2->3
     * 输出: 2->3
     *
     * @param head 头指针
     * @return
     */
    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newNode = new ListNode(-1);
        newNode.next = head;
        ListNode pre = newNode;
        while (head != null) {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            if (pre.next == head) {
                pre = head;
            } else {
                pre.next = head.next;
            }
            head = head.next;
        }
        return newNode.next;
    }

    /**
     * 分隔链表 - 86
     * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
     * 你应当保留两个分区中每个节点的初始相对位置。
     * 示例:
     * 输入: head = 1->4->3->2->5->2, x = 3
     * 输出: 1->2->2->4->3->5
     *
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode left = new ListNode(-1);
        ListNode leftTemp = left;
        ListNode right = new ListNode(-1);
        ListNode rightTemp = right;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                leftTemp.next = cur;
                leftTemp = cur;
            } else {
                rightTemp.next = cur;
                rightTemp = cur;
            }
            cur = cur.next;
        }
        rightTemp.next = null;
        leftTemp.next = right.next;
        return left.next;
    }

    /**
     * 有序链表转换二叉搜索树 - 109
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     *
     * @param head
     * @return
     */
    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        return sortedListToBST(head, null);
    }

    private static TreeNode sortedListToBST(ListNode head, ListNode end) {
        if (head == end) {
            return null;
        }
        ListNode fast = head;
        ListNode mid = head;
        while (fast != end && fast.next != end) {
            fast = fast.next.next;
            mid = mid.next;
        }
        TreeNode node = new TreeNode(mid.val);
        node.left = sortedListToBST(head, mid);
        node.right = sortedListToBST(mid.next, end);
        return node;
    }

    /**
     * 复制带随机指针的链表 - 138
     * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
     * 要求返回这个链表的深拷贝。
     *
     * @return
     */
    public static ListNode copyRandomListNode(ListNode head) {
        if (head == null) {
            return null;
        }
        Map<ListNode, ListNode> nodeMap = new HashMap<>();
        ListNode cur = head;
        while (cur != null) {
            ListNode node = new ListNode(cur.val);
            nodeMap.put(cur, node);
            cur = cur.next;
        }
        ListNode dummyNode = new ListNode(-1);
        ListNode newNode = dummyNode;
        ListNode temp = head;
        while (temp != null) {
            newNode.next = nodeMap.get(temp);
            newNode.random = nodeMap.get(temp.random);
            newNode = newNode.next;
            temp = temp.next;
        }
        return dummyNode.next;
    }


    /**
     * 检测是否有环形链表 - 141
     * 给定一个链表，判断链表中是否有环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
     * Input: head = [3,2,0,-4], pos = 1
     * Output: true
     *
     * @param head 头指针
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 环形链表 II - 142
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。
     * 说明：不允许修改给定的链表。
     *
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode node = head;
                while (node != null) {
                    if (slow == node) {
                        return node;
                    }
                    slow = slow.next;
                    node = node.next;
                }
            }
        }
        return null;
    }

    /**
     * 链表重排序 - 143
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * 示例 1:
     * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
     *
     * @param head
     * @return
     */
    public static ListNode reorderList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode slow = dummyNode;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode secondHalf = slow.next;
        slow.next = null;
        // reverse
        ListNode preNode = null;
        while (secondHalf != null) {
            ListNode temp = secondHalf.next;
            secondHalf.next = preNode;
            preNode = secondHalf;
            secondHalf = temp;
        }
        ListNode cur = head;
        while (cur != null) {
            ListNode firstTemp = cur.next;
            ListNode secondTemp = preNode.next;
            cur.next = preNode;
            preNode.next = firstTemp;
            cur = firstTemp;
            preNode = secondTemp;
        }
        return dummyNode.next;
    }

    /**
     * 对链表进行插入排序。 - 147
     *
     * @param head
     * @return
     */
    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyNode = new ListNode(-1);
        ListNode sortedNode;
        ListNode waitSortNode = head;
        while (waitSortNode != null) {
            ListNode temp = waitSortNode.next;
            sortedNode = dummyNode;
            while (sortedNode.next != null && sortedNode.next.val < waitSortNode.val) {
                sortedNode = sortedNode.next;
            }
            waitSortNode.next = sortedNode.next;
            sortedNode.next = waitSortNode;
            waitSortNode = temp;
        }
        return dummyNode.next;
    }

    /**
     * 排序链表 - 148
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。归并排序
     *
     * @param head 头指针
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = new ListNode(-1);
        slow.next = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode rHead = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(rHead);
        return merSortList(left, right);
    }

    private static ListNode merSortList(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;
        while (left != null && right != null) {
            if (left.val < right.val) {
                temp.next = left;
                left = left.next;
            } else {
                temp.next = right;
                right = right.next;
            }
            temp = temp.next;
        }
        if (left != null) {
            temp.next = left;
        }
        if (right != null) {
            temp.next = right;
        }
        return dummyNode.next;
    }

    /**
     * 快慢指针找到中间节点
     *
     * @param head 头指针
     * @return
     */
    public static ListNode findMidNode(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 找到交叉节点
     *
     * @param firstList  链表一
     * @param secondList 链表二
     * @return
     */
    public static ListNode findBeginNode(ListNode firstList, ListNode secondList) {
        if (firstList == null || secondList == null) {
            return null;
        }
        ListNode curNode = firstList;
        while (curNode != null) {
            if (isContainNode(secondList, curNode)) {
                return curNode;
            }
            curNode = curNode.next;
        }
        return null;
    }

    private static boolean isContainNode(ListNode list, ListNode node) {
        if (list == null) {
            return false;
        }
        while (list != null) {
            if (list == node) {
                return true;
            }
            list = list.next;
        }
        return false;
    }

    /**
     * 巧妙解法：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/tu-jie-xiang-jiao-lian-biao-by-user7208t/
     * 我们需要做的事情是，让两个链表从同距离末尾同等距离的位置开始遍历。这个位置只能是较短链表的头结点位置。 为此，我们必须消除两个链表的长度差
     * 指针pA指向A链表，指针pB指向B链表，依次往后遍历
     * 如果pA到了末尾，则pA = headB 继续遍历
     * 如果pB到了末尾，则pB = headA 继续遍历
     * （比较长的链表指针指向较短链表head时，长度差就消除了
     * 如此，只需要将最短链表遍历两次即可找到位置
     *
     * @param firstList
     * @param secondList
     * @return
     */
    public static ListNode findBeginNode1(ListNode firstList, ListNode secondList) {
        if (firstList == null || secondList == null) {
            return null;
        }
        ListNode nodeA = firstList;
        ListNode nodeB = secondList;
        while (nodeA != nodeB) {
            nodeA = nodeA == null ? secondList : nodeA.next;
            nodeB = nodeB == null ? firstList : nodeB.next;
        }
        return nodeA;
    }


    /**
     * 删除链表中等于给定值 val 的所有节点。 - 203
     * 示例:
     * 输入: 1->2->6->3->4->5->6, val = 6
     * 输出: 1->2->3->4->5
     *
     * @param head
     * @param val  需要移除的元素
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return dummyNode.next;
    }

    /**
     * 链表反转  - 206
     *
     * @param head 头指针
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode current = head;
        ListNode nextNode = head.next;
        while (nextNode != null) {
            ListNode temp = nextNode.next;
            nextNode.next = current;
            current = nextNode;
            nextNode = temp;
        }
        head.next = null;
        head = current;
        return head;
    }

    /**
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     * 示例:
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseList2(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        ListNode dummyNode = new ListNode(-1);
        ListNode pre = dummyNode;
        for (int i = 0; i < m - 1; i++) {
            pre.next = head;
            pre = head;
            head = head.next;
        }
        ListNode start = pre.next;
        ListNode then = start.next;

        for (int i = 0; i < n - m; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        return dummyNode.next;
    }

    public static ListNode reverseList1(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode current = head;
        while (current != null) {
            ListNode temp = current.next;
            current.next = pre;
            pre = current;
            current = temp;
        }
        return pre;
    }

    /**
     * 是否是回文链表 - 234
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        } else if (head.next == null) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = new ListNode(-1);
        slow.next = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode rHead;
        if (fast == null) {
            rHead = slow.next;
        } else {
            rHead = slow.next.next;
        }
        rHead = reverseList(rHead);
        while (rHead != null) {
            if (rHead.val != head.val) {
                return false;
            }
            rHead = rHead.next;
            head = head.next;
        }
        return true;
    }

    /**
     * 奇偶链表 - 328
     * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
     * 请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
     * 示例 1:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 1->3->5->2->4->NULL
     *
     * @param head
     * @return
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode evenHead = head.next;
        ListNode even = evenHead;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    /**
     * 两数相加 II - 445
     * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。
     * 它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                stack1.push(l1);
            }
            if (l2 != null) {
                stack2.push(l2);
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        int carry = 0;
        ListNode newHead = new ListNode(-1);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int val1 = stack1.isEmpty() ? 0 : stack1.peek().val;
            int val2 = stack2.isEmpty() ? 0 : stack2.peek().val;

            int result = (carry + val1 + val2) % 10;
            carry = (carry + val1 + val2) / 10;
            ListNode temp = newHead.next;
            newHead.next = new ListNode(result);
            newHead.next.next = temp;

            if (!stack1.isEmpty()) {
                stack1.pop();
            }
            if (!stack2.isEmpty()) {
                stack2.pop();
            }
        }
        if (carry > 0) {
            ListNode temp = newHead.next;
            newHead.next = new ListNode(carry);
            newHead.next.next = temp;
        }

        return newHead.next;
    }

    /**
     * 链表快速排序
     *
     * @param head
     * @return
     */
    public static ListNode quickSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        quickSortList(head, null);
        return head;
    }

    private static void quickSortList(ListNode head, ListNode end) {
        if (head != end) {
            ListNode node = partion(head, end);
            quickSortList(head, node);
            quickSortList(node.next, end);
        }
    }

    private static ListNode partion(ListNode head, ListNode end) {
        ListNode p1 = head;
        ListNode p2 = head.next;
        while (p2 != end) {
            if (p2.val < head.val) {
                p1 = p1.next;
                if (p1 != p2) {
                    int temp = p1.val;
                    p1.val = p2.val;
                    p2.val = temp;
                }
            }
            p2 = p2.next;
        }
        if (p1 != head) {
            int temp = p1.val;
            p1.val = head.val;
            head.val = temp;
        }
        return p1;
    }

    /**
     * 链表中的下一个更大节点 - 1019
     *
     * @param head
     * @return
     */
    public static int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new int[]{head.val};
        }
        List<Integer> result = new ArrayList<>();
        ListNode outer = head;
        while (outer != null) {
            ListNode cur = outer.next;
            int max = 0;
            while (cur != null) {
                if (cur.val > outer.val) {
                    max = cur.val;
                    break;
                }
                cur = cur.next;
            }
            result.add(max);
            outer = outer.next;
        }
        int[] arr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }
}
