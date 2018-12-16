package queue;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-15 下午9:00
 **/
public class RArrayQueue<T> implements Queue<T> {

    private static final int DEFAULT_SIZE = 10;
    private Object[] elements;
    private int head;
    private int tail;

    public RArrayQueue() {
        this(DEFAULT_SIZE);
    }

    public RArrayQueue(int size) {
        if (size < 1) {
            size = DEFAULT_SIZE;
        }
        elements = new Object[size + 1];
    }

    @Override
    public boolean enQueque(T t) {
        if (isFull()) {
            return false;
        }
        if (tail == elements.length) {
            if (isEmpty()) {
                head = 0;
                tail = 0;
            } else {
                Object[] newElements = new Object[elements.length];
                System.arraycopy(elements, head, newElements, 0, size());
                elements = newElements;
                tail = size();
                head = 0;
            }
        }
        elements[tail++] = t;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deQueue() {
        if (isEmpty()) {
            return null;
        }
        return (T) elements[head++];
    }

    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return (T) elements[head];
    }

    @Override
    public int size() {
        return tail - head;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return size() == elements.length  - 1;
    }

}
