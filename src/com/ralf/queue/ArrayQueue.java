package com.ralf.queue;

class ArrayQueue<T> implements Queue<T> {

    private final Object[] elements;
    private int head;
    private int tail;
    private final int capacity;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        int length = 1;
        while (length < capacity) {
            length = length << 1;
        }
        elements = new Object[length];
    }

    @Override
    public boolean enQueue(T t) {
        if (isFull()) {
            return false;
        }
        elements[tail] = t;
        tail = (tail + 1) & (elements.length - 1);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deQueue() {
        if (isEmpty()) {
            return null;
        }
        Object t = elements[head];
        elements[head] = null;
        head = (head + 1) & (elements.length - 1);
        return (T) t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        return (T) elements[head];
    }

    @Override
    public int size() {
        return (tail - head) & (elements.length - 1);
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    private boolean isFull() {
        return size() == capacity;
    }
}
