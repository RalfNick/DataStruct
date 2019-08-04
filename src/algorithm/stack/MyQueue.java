package algorithm.stack;

import java.util.Stack;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-07-17 下午11:47
 **/
public class MyQueue<T> {

    private Stack<T> enterStack = new Stack<>();
    private Stack<T> exitStack = new Stack<>();

    public MyQueue() {

    }

    public void push(T item) {
        enterStack.push(item);
    }

    public T pop() {
        if (empty()) {
            return null;
        }
        if (!exitStack.isEmpty()) {
            return exitStack.pop();
        }
        while (!enterStack.isEmpty()) {
            exitStack.push(enterStack.pop());
        }
        return exitStack.pop();
    }

    public T peek() {
        if (empty()) {
            return null;
        }
        if (!exitStack.isEmpty()) {
            return exitStack.peek();
        }
        while (!enterStack.isEmpty()) {
            exitStack.push(enterStack.pop());
        }
        return exitStack.peek();
    }

    public int size() {
        return enterStack.size() + exitStack.size();
    }

    public boolean empty() {
        return exitStack.isEmpty() && enterStack.isEmpty();
    }
}
