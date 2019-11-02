package stack;


import queue.CircleQueue;
import queue.Queue;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-16 上午10:46
 **/
public class StackWithTwoQueue<T> {

    private static final int DEFAULT_SIZE = 10;

    private CircleQueue<T> queue1;
    private CircleQueue<T> queue2;
    private int top = -1;

    public StackWithTwoQueue() {
        this(DEFAULT_SIZE);
    }

    public StackWithTwoQueue(int capacity) {
        if (capacity < 1) {
            capacity = DEFAULT_SIZE;
        }
        queue1 = new CircleQueue<>(capacity);
        queue2 = new CircleQueue<>(capacity);
    }

    public boolean push(T t) {
        if (isFull()) {
            return false;
        }
        if (queue1.isEmpty() && queue2.isEmpty()) {
            return queue1.enQueue(t);
        } else if (queue1.isEmpty()) {
            return queue2.enQueue(t);
        } else {
            return queue1.enQueue(t);
        }
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        if (queue1.isEmpty()) {
            transQueue(queue1, queue2);
            return queue2.deQueue();
        } else {
            transQueue(queue2, queue1);
            return queue1.deQueue();
        }
    }

    private void transQueue(Queue<T> enQueue, Queue<T> deQueue) {
        int size = deQueue.size();
        for (int i = 0; i < size - 1; i++) {
            enQueue.enQueue(deQueue.deQueue());
        }
    }

    /**
     * 栈顶元素，有点麻烦，低效
     *
     * @return
     */
    public T peek() {

        if (isEmpty()) {
            return null;
        }
        T result = null;
        if (queue1.isEmpty()) {
            transQueue(queue1, queue2);
            result = queue2.deQueue();
            queue1.enQueue(result);
        } else {
            transQueue(queue2, queue1);
            result = queue1.deQueue();
            queue2.enQueue(result);
        }

        return result;
    }

    public boolean isEmpty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

    public boolean isFull() {
        return queue1.isFull() || queue2.isFull();
    }

    public int size() {
        if (queue1.isEmpty()) {
            return queue2.size();
        } else if (queue2.isEmpty()) {
            return queue1.size();
        } else {
            throw new RuntimeException("the data struct is error!");
        }
    }
}
