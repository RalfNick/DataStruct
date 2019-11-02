package queue;

import stack.RStack;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-13 下午9:38
 **/
public class QueueWithTwoStacks<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private int MAX_LENGTH;

    private RStack<E> stack1;
    private RStack<E> stack2;

    public QueueWithTwoStacks() {
        this(DEFAULT_CAPACITY);
    }

    public QueueWithTwoStacks(int capacity) {
        if (capacity < 1) {
            capacity = DEFAULT_CAPACITY;
        }
        MAX_LENGTH = capacity;
        stack1 = new RStack<>(capacity);
        stack2 = new RStack<>(capacity);
    }

    @Override
    public boolean enQueue(E e) {
        if (size() >= MAX_LENGTH || stack1.isFull()) {
            return false;
        }
        stack1.push(e);
        return true;
    }

    @Override
    public E deQueue() {
        if (size() < 1) {
            return null;
        }
        if (!stack2.isEmpty()){
            return stack2.pop();
        }
        transStacks();
        return stack2.pop();
    }

    @Override
    public E peek() {
        if (size() < 1) {
            return null;
        }
        if (!stack2.isEmpty()){
            return stack2.peek();
        }
        transStacks();
        return stack2.peek();
    }

    public boolean isEmpty() {
        return size() < 1;
    }

    public boolean isFull(){
        return size() == MAX_LENGTH;
    }

    /**
     * 转移两个栈的元素
     */
    private void transStacks() {

        if (!stack2.isEmpty()) {
            return;
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }

    @Override
    public int size() {
        return stack1.size() + stack2.size();
    }
}
