package queue;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-08-05 下午12:34
 **/
public class CircleDeQueue<T> implements Queue<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int front = 0;
    private int rear = 0;

    public CircleDeQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CircleDeQueue(int capacity) {
        if (capacity < 1) {
            capacity = DEFAULT_CAPACITY;
        }
        elements = new Object[capacity + 1];
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(T value) {
        if (isFull()) {
            return false;
        }
        front = (front - 1 + elements.length) % elements.length;
        elements[front] = value;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(T value) {
        return enQueque(value);
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public T deleteFront() {
        return deQueue();
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    @SuppressWarnings("unchecked")
    public T deleteLast() {
        if (isEmpty()) {
            return null;
        }
        rear = (rear - 1 + elements.length) % elements.length;
        T result = (T) elements[rear];
        elements[rear] = null;
        return result;
    }

    /**
     * Get the front item from the deque.
     */
    public T getFront() {
        return peek();
    }

    /**
     * Get the last item from the deque.
     */
    @SuppressWarnings("unchecked")
    public T getRear() {
        if (isEmpty()) {
            return null;
        }
        int index = (rear - 1 + elements.length) % elements.length;
        return (T) elements[index];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return (rear + 1) % elements.length == front;
    }

    @Override
    public boolean enQueque(T t) {
        if (isFull()) {
            return false;
        }
        elements[rear] = t;
        rear = (rear + 1) % elements.length;
        return true;
    }

    @Override
    public T deQueue() {
        T result = peek();
        elements[front] = null;
        front = (front + 1) % elements.length;
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return (T) elements[front];
    }

    @Override
    public int size() {
        return (rear - front + elements.length) % elements.length;
    }
}
