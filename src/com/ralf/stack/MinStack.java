package com.ralf.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 155 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 * <a href="https://leetcode.cn/problems/min-stack/description/?envType=list&envId=0XgVvKK3">Leet Code</a>
 */
public class MinStack {
    int size = 0;
    private Deque<Integer> stack = new ArrayDeque<>();
    private int min = Integer.MAX_VALUE;

    public MinStack() {

    }

    public void push(int val) {
        if (isEmpty()) {
            min = val;
        } else if (val <= min) {
            stack.push(min);
            min = val;
        }
        stack.push(val);
        size++;
    }

    public void pop() {
        if (isEmpty()) {
            return;
        }
        int val = stack.pop();
        if (val == min && !stack.isEmpty()) {
            min = stack.pop();
        }
        size--;
    }

    public int top() {
        if (isEmpty()) {
            return -1;
        }
        return stack.peek();
    }

    public int getMin() {
        if (isEmpty()) {
            return -1;
        }
        return min;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
