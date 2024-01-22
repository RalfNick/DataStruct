package com.ralf.stack;

import java.util.LinkedList;

/**
 * 225. 用队列实现栈
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * 实现 MyStack 类：
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 * <a href="https://leetcode.cn/problems/implement-stack-using-queues/description/">Leet Code</a>
 */
public class TwoQueueStack {

    private LinkedList<Integer> queue1 = new LinkedList<>();
    private LinkedList<Integer> queue2 = new LinkedList<>();

    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        LinkedList<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public int pop() {
        if (queue1.isEmpty()) {
            return -1;
        }
        return queue1.pop();
    }

    public int top() {
        if (queue1.isEmpty()) {
            return -1;
        }
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}
