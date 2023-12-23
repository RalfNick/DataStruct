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
public class QueueStack {

    private final LinkedList<Integer> queue = new LinkedList<>();

    public void push(int x) {
        queue.push(x);
    }

    public int pop() {
        if (queue.isEmpty()) {
            return -1;
        }
        int size = queue.size();
        if (size < 2) {
            return queue.pop();
        }
        for (int i = 0; i < size - 1; i++) {
            queue.push(queue.pop());
        }
        return queue.pop();
    }

    public int top() {
        if (queue.isEmpty()) {
            return -1;
        }
        int size = queue.size();
        if (size < 2) {
            return queue.peek();
        }
        for (int i = 0; i < size - 1; i++) {
            queue.push(queue.pop());
        }
        int val = queue.pop();
        queue.push(val);
        return val;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
