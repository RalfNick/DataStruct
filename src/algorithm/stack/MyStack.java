package algorithm.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈 - 225
 *
 * @author lixin
 * @create 2019-07-17 下午11:31
 **/
public class MyStack<T> {

    private Queue<T> mQueue = new LinkedList<>();

    public MyStack() {

    }

    public void push(T data) {
        mQueue.offer(data);
    }

    public T pop() {
        if (empty()) {
            return null;
        }
        int size = mQueue.size();
        if (size < 2) {
            return mQueue.poll();
        } else {
            for (int i = 0; i < size - 1; i++) {
                mQueue.offer(mQueue.poll());
            }
            return mQueue.poll();
        }
    }

    public T top() {
        if (empty()) {
            return null;
        }
        int size = mQueue.size();
        if (size < 2) {
            return mQueue.peek();
        } else {
            for (int i = 0; i < size - 1; i++) {
                mQueue.offer(mQueue.poll());
            }
            T result = mQueue.poll();
            mQueue.offer(result);
            return result;
        }
    }

    public int size() {
        return mQueue.size();
    }

    public boolean empty() {
        return mQueue.isEmpty();
    }
}
