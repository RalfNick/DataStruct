package algorithm.stack;

import java.util.Stack;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-07-12 上午10:28
 **/
public class MiniStack<T extends Comparable<? super T>> {

    private Stack<T> miniStack = new Stack<>();
    private int size = 0;
    private T min;

    public MiniStack() {
        miniStack.push(null);
    }

    public void push(T item) {
        if (item == null) {
            return;
        }
        if (isEmpty()) {
            miniStack.push(item);
            min = item;
        } else {
            if (item.compareTo(min) > 0) {
                miniStack.push(item);
            } else {
                miniStack.push(min);
                miniStack.push(item);
                min = item;
            }
        }
        size++;
    }

    public T pop() {
        if (size < 1) {
            throw new NullPointerException("the stack is empty!");
        }
        T result = miniStack.pop();
        if (result.compareTo(min) == 0) {
            min = miniStack.pop();
        }
        size--;
        return result;
    }

    public T getTop() {
        return miniStack.peek();
    }

    public T getMin() {
        return min;
    }

    public boolean isEmpty() {
        return size < 1;
    }
}
