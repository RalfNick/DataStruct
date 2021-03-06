package queue;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-10 下午7:34
 **/
public class CircleQueue<T> implements Queue<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int front = 0;
    private int rear = 0;

    public CircleQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CircleQueue(int capacity) {
        if (capacity < 1) {
            capacity = DEFAULT_CAPACITY;
        }
        elements = new Object[capacity + 1];
    }

    @Override
    public boolean enQueue(T t) {
        checkQueue();
        if (isFull()) {
            return false;
        }
        elements[rear] = t;
        rear = (rear + 1) % elements.length;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deQueue() {
        checkQueue();
        if (isEmpty()) {
            return null;
        }
        T t = (T) elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        return t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        checkQueue();
        if (isEmpty()) {
            return null;
        }
        return (T) elements[front];
    }

    @Override
    public int size() {
        checkQueue();
        return (rear - front + elements.length) % elements.length;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % elements.length == front;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        else {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            for (Object o : elements) {
                builder.append(o.toString()).append(",");
            }
            return builder.substring(0, builder.length() - 1) + "]";
        }
    }

    private void checkQueue() {
        if (elements == null || elements.length < 1) {
            throw new NullPointerException("Queue is null!");
        }
        if (front < 0 || front >= elements.length) {
            throw new IndexOutOfBoundsException("index id out of bounds!");
        }
        if (rear < 0 || rear >= elements.length) {
            throw new IndexOutOfBoundsException("index id out of bounds!");
        }
    }
}
