package linkedlist;

import java.util.*;

/**
 * 链表练习
 */
class LinkedList {

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    static String print(ListNode head) {
        ListNode node = head;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        while (node != null && node.next != null) {
            stringBuilder.append(node.val).append("-->");
            node = node.next;
        }
        if (node != null) {
            stringBuilder.append(node.val);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * 链表反转
     * 206
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * <a href="https://leetcode.cn/problems/reverse-linked-list/">描述</a>
     */
    static ListNode revert(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode node = head;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    /**
     * 链表反转递归
     */
    static ListNode revert1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = revert1(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 24 两两交换链表中的节点
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * * <a href="https://leetcode.cn/problems/swap-nodes-in-pairs/?envType=list&envId=kb1y1iQ5">Leet Code</a>
     */
    static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode second = head.next;
        ListNode third = second.next;
        second.next = head;
        head.next = swapPairs(third);
        return second;
    }

    static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = dummy;
        while (node.next != null && node.next.next != null) {
            ListNode start = node.next;
            ListNode end = start.next;
            start.next = end.next;
            end.next = start;
            node.next = end;
            node = start;
        }
        return dummy.next;
    }

    /**
     * 92 链表反转II
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * * <a href="https://leetcode.cn/problems/reverse-linked-list-ii/description/?envType=list&envId=0e81BPqb">Leet Code</a>
     */
    static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = dummy;
        for (int i = 1; i < left; i++) {
            node = node.next;
        }
        ListNode rNode = node.next;
        for (int i = 0; i < right - left; i++) {
            rNode = rNode.next;
        }
        ListNode next = rNode.next;
        rNode.next = null;
        ListNode lNode = node.next;
        ListNode cur = node.next;
        ListNode pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        node.next = rNode;
        lNode.next = next;
        return dummy.next;
    }

    static ListNode reverseBetween2(ListNode head, int left, int right) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }
        ListNode start = pre.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = start.next;
            start.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }

    /**
     * 是否是回文链表
     * 234
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false
     * <a href="https://leetcode.cn/problems/palindrome-linked-list/">描述</a>
     */
    static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode firstHalf = findMiddle(head);
        ListNode secondHalf = revert(firstHalf.next);

        ListNode node1 = head;
        ListNode node2 = secondHalf;
        boolean isPalindrome = true;
        while (node2 != null) {
            if (node1.val != node2.val) {
                isPalindrome = false;
                break;
            }
            node2 = node2.next;
            node1 = node1.next;
        }
        firstHalf.next = revert(secondHalf);
        return isPalindrome;
    }

    /**
     * 是否是回文链表
     * 递归
     */
    static ListNode palindromeHead = null;

    static boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        palindromeHead = head;
        return checkPalindrome(head);
    }

    static boolean checkPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        if (!checkPalindrome(head.next)) {
            return false;
        }
        if (head.val != palindromeHead.val) {
            return false;
        }
        palindromeHead = palindromeHead.next;
        return true;
    }

    /**
     * 找到链表的中点
     * 如果是偶数找到中间第一个节点
     */
    static ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 876 链表的中间节点：
     * <a href="https://leetcode.cn/problems/middle-of-the-linked-list/description/">描述</a>
     * 题解：<a href="https://leetcode.cn/problems/middle-of-the-linked-list/solutions/165152/kuai-man-zhi-zhen-zhu-yao-zai-yu-diao-shi-by-liwei/">题解</a>
     * 给你单链表的头结点 head ，请你找出并返回链表的中间结点，如果有两个中间结点，则返回第二个中间结点。
     */
    static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 141 环形链表：
     * <a href="https://leetcode.cn/problems/linked-list-cycle/"></a>
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     */
    static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 142 环形链表II
     * <a href="https://leetcode.cn/problems/linked-list-cycle-ii/description/">...</a>
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     */
    static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasCycle = true;
                break;
            }
        }
        if (hasCycle) {
            fast = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
        return null;
    }

    /**
     * 160 相交链表
     * <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists/description/">题目描述</a>
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     */
    static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headA : p1.next;
            p2 = p2 == null ? headB : p2.next;
        }
        return p1;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/">描述</a>
     */
    static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while (fast.next != null) {
            if (n <= 0) {
                slow = slow.next;
            }
            n--;
            fast = fast.next;
        }
        if (slow.next == null) {
            return null;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * 面试题 02.02. 返回倒数第 k 个节点
     * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
     * <a href="https://leetcode.cn/problems/kth-node-from-end-of-list-lcci/description//">描述</a>
     */
    static int kthToLast(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while (fast.next != null) {
            if (k <= 0) {
                slow = slow.next;
            }
            k--;
            fast = fast.next;
        }
        return slow.next.val;
    }

    /**
     * 2 两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <a href="https://leetcode.cn/problems/add-two-numbers/description/?envType=list&envId=0e81BPqb//">LeetCode</a>
     */
    static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int sum = num1 + num2 + carry;
            carry = sum / 10;
            sum = sum % 10;
            current.next = new ListNode(sum);
            current = current.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry != 0) {
            current.next = new ListNode(carry);
        }
        return dummy.next;
    }

    /**
     * 445. 两数相加 II
     * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * <a href="https://leetcode.cn/problems/add-two-numbers-ii/description/?envType=list&envId=0e81BPqb//">LeetCode</a>
     */
    static ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        ListNode next = null;
        ListNode head = null;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int num1 = stack1.isEmpty() ? 0 : stack1.pop();
            int num2 = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = num1 + num2 + carry;
            carry = sum / 10;
            sum = sum % 10;
            head = new ListNode(sum);
            head.next = next;
            next = head;
        }
        if (carry != 0) {
            head = new ListNode(carry);
            head.next = next;
        }
        return head;
    }

    /**
     * 2 03. 移除链表元素
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点
     * <a href="https://leetcode.cn/problems/remove-linked-list-elements/description/?envType=list&envId=kb1y1iQ5">LeetCode</a>
     */
    static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = dummy;
        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return dummy.next;
    }

    /**
     * 83. 删除排序链表中的重复元素
     * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表
     * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list/description/?envType=list&envId=0e81BPqb">LeetCode</a>
     */
    static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head;
        while (node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }

    /**
     * 82. 删除排序链表中的重复元素 II
     * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
     * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/description/?envType=list&envId=0e81BPqb">LeetCode</a>
     */
    static ListNode deleteDuplicatesII(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = dummy.next;
        ListNode pre = dummy;
        while (node != null) {
            ListNode cur = node;
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            if (pre.next == cur) {
                pre = pre.next;
            } else {
                pre.next = cur.next;
            }
            node = node.next;
        }
        return dummy.next;
    }

    static ListNode deleteDuplicatesIIRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val != head.next.val) {
            head.next = deleteDuplicatesIIRecursion(head.next);
        } else {
            ListNode cur = head.next;
            while (cur != null && cur.val == head.val) {
                cur = cur.next;
            }
            return deleteDuplicatesIIRecursion(cur);
        }
        return head;
    }

    /**
     * 328. 奇偶链表
     * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
     * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
     * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
     * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
     * <a href="https://leetcode.cn/problems/odd-even-linked-list/description/?envType=list&envId=kb1y1iQ5">LeetCode</a>
     */
    static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode evenHead = head.next;
        ListNode even = head.next;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    /**
     * 237. 删除链表中的节点
     * 有一个单链表的 head，我们想删除它其中的一个节点 node。
     * 给你一个需要删除的节点 node 。你将 无法访问 第一个节点  head。
     * 链表的所有值都是 唯一的，并且保证给定的节点 node 不是链表中的最后一个节点。
     * <a href="https://leetcode.cn/problems/delete-node-in-a-linked-list/description/?envType=list&envId=kb1y1iQ5">LeetCode</a>
     */
    static void deleteNode(ListNode node) {
        if (node == null || node.next == null) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 86. 分隔链表
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     * <a href="https://leetcode.cn/problems/partition-list/description/?envType=list&envId=kb1y1iQ5">LeetCode</a>
     */
    static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode smallHead = new ListNode(-1);
        ListNode small = smallHead;
        ListNode largeHead = new ListNode(-1);
        ListNode large = largeHead;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                small.next = cur;
                small = small.next;
            } else {
                large.next = cur;
                large = large.next;
            }
            cur = cur.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }

    /**
     * 147. 对链表进行插入排序
     * 给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。
     * 插入排序 算法的步骤:
     * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
     * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
     * 重复直到所有输入数据插入完为止。
     * <a href="https://leetcode.cn/problems/insertion-sort-list/description/?envType=list&envId=kb1y1iQ5">LeetCode</a>
     */
    static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = head.next;
        ListNode pre = head;
        while (node != null) {
            if (pre.val <= node.val) {
                pre = node;
            } else {
                ListNode cur = dummy;
                while (cur.next.val <= node.val) {
                    cur = cur.next;
                }
                pre.next = node.next;
                node.next = cur.next;
                cur.next = node;
            }
            node = pre.next;
        }
        return dummy.next;
    }

    /**
     * 148. 排序链表
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * <a href="https://leetcode.cn/problems/sort-list/description/">LeetCode</a>
     */
    static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode middle = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(middle);
        return mergeTwoLists(left, right);
    }

    /**
     * 21. 合并两个有序链表
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * <a href="https://leetcode.cn/problems/sort-list/description/">LeetCode</a>
     */
    static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 == null ? list2 : list1;
        return dummy.next;
    }

    /**
     * 143. 重排链表
     * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
     * L0 → L1 → … → Ln - 1 → Ln
     * 请将其重新排列后变为：
     * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * <a href="https://leetcode.cn/problems/reorder-list/description/?envType=list&envId=kb1y1iQ5">LeetCode</a>
     */
    static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        ListNode pre = null;
        while (right != null) {
            ListNode next = right.next;
            right.next = pre;
            pre = right;
            right = next;
        }
        ListNode first = head;
        ListNode second = pre;
        while (second != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;
            first.next = second;
            second.next = temp1;
            first = temp1;
            second = temp2;
        }
    }

    static void reorderList2(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

    /**
     * 1019. 链表中的下一个更大节点
     * 给定一个长度为 n 的链表 head
     * 对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。
     * 返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。
     * <a href="https://leetcode.cn/problems/next-greater-node-in-linked-list/description/?envType=list&envId=kb1y1iQ5">LeetCode</a>
     */
    static int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        if (head.next == null) {
            return new int[]{0};
        }
        ListNode node = head;
        ListNode pre = null;
        int length = 0;
        while (node != null) {
            length++;
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        int[] res = new int[length];
        int i = length - 1;
        Deque<Integer> stack = new ArrayDeque<>();
        while (pre != null) {
            while (!stack.isEmpty() && stack.peek() <= pre.val) {
                stack.pop();
            }
            res[i--] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(pre.val);
            pre = pre.next;
        }
        return res;
    }

    static int[] nextLargerNodes2(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        if (head.next == null) {
            return new int[]{0};
        }
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            cur = cur.next;
            length++;
        }
        Deque<int[]> stack = new ArrayDeque<>();
        int[] res = new int[length];
        int i = 0;
        for (ListNode node = head; node != null; node = node.next) {
            while (!stack.isEmpty() && stack.peek()[1] < node.val) {
                res[stack.pop()[0]] = stack.pop()[1];
            }
            stack.push(new int[]{i++, node.val});
        }
        return res;
    }

    /**
     * 61. 旋转链表
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     * <a href="https://leetcode.cn/problems/rotate-list/description/?envType=list&envId=kb1y1iQ5">LeetCode</a>
     */
    static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode node = head;
        ListNode end = null;
        int length = 0;
        while (node != null) {
            end = node;
            node = node.next;
            length++;
        }
        end.next = head;
        int n = length - k % length;
        for (int i = 0; i < n; i++) {
            end = end.next;
        }
        ListNode newHead = end.next;
        end.next = null;
        return newHead;
    }

    /**
     * 23. 合并 K 个升序链表
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * <a href="https://leetcode.cn/problems/merge-k-sorted-lists/description/?envType=list&envId=kb1y1iQ5">LeetCode</a>
     */
    static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int capacity = lists.length;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(capacity, Comparator.comparingInt(o -> o.val));
        for (ListNode node : lists) {
            queue.offer(node);
        }
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            head.next = node;
            head = head.next;
            if (node.next != null) {
                queue.offer(node.next);
            }
        }
        return dummy.next;
    }

    static ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode head = null;
        for (ListNode node : lists) {
            head = mergeTwoLists(head, node);
        }
        return head;
    }

    static ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        return mergeKLists3(lists, 0, lists.length - 1);
    }

    private static ListNode mergeKLists3(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        ListNode first = mergeKLists3(lists, l, mid);
        ListNode second = mergeKLists3(lists, mid + 1, r);
        return mergeTwoLists(first, second);
    }

    /**
     * 25. K 个一组翻转链表
     * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     * <a href="https://leetcode.cn/problems/reverse-nodes-in-k-group/?envType=list&envId=kb1y1iQ5">LeetCode</a>
     */
    static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        ListNode node = head;
        int length = 0;
        while (node != null) {
            node = node.next;
            length++;
        }
        int n = length / k;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < n; i++) {
            ListNode start = pre.next;
            ListNode then = start.next;
            for (int j = 1; j < k; j++) {
                start.next = then.next;
                then.next = pre.next;
                pre.next = then;
                then = start.next;
            }
            pre = start;
        }
        return dummy.next;
    }

    /**
     * K 个一组翻转链表II
     * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将前面剩余的节点保持原有顺序。
     */
    static ListNode reverseKGroupII(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        ListNode node = head;
        int length = 0;
        while (node != null) {
            node = node.next;
            length++;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < length % k; i++) {
            pre = pre.next;
        }
        int n = length / k;
        for (int i = 0; i < n; i++) {
            ListNode start = pre.next;
            ListNode then = start.next;
            for (int j = 1; j < k; j++) {
                start.next = then.next;
                then.next = pre.next;
                pre.next = then;
                then = start.next;
            }
            pre = start;
        }
        return dummy.next;
    }
}









