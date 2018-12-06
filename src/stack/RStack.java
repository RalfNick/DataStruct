package stack;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-05 下午9:24
 **/
public class RStack<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private T[] elements;

    public RStack() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public RStack(int capacity) {

        if (capacity < 1) {
            capacity = DEFAULT_CAPACITY;
        }
        Object[] objs = new Object[capacity];
        elements = (T[]) objs;
    }

    public T pop() {
        checkIsValid();
        if (isEmpty()) {
            return null;
        }
        T data = elements[size - 1];
        elements[size - 1] = null;
        size--;
        return data;
    }

    public boolean push(T t) {
        if (isFull()) {
            return false;
        }
        elements[size] = t;
        size++;
        return true;
    }

    public T peek() {
        checkIsValid();
        if (isEmpty()) {
            return null;
        }
        return elements[size - 1];
    }

    public boolean pushExt(T t) {
        checkIsValid();
        if (!isFull()){
           return push(t);
        }
        ensureCapacity(elements.length * 2);
        return push(t);
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int newLength) {

        T[] newItems = (T[]) new Object[newLength];
        System.arraycopy(elements,0,newItems,0,elements.length);
        elements = newItems;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return elements == null || size < 1;
    }

    public boolean isFull() {
        checkIsValid();
        return size == elements.length;
    }

    private void checkIsValid() {
        if (elements == null) {
            throw new NullPointerException("init first");
        }
        if (elements.length < size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        if (elements == null || size < 1) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < size; i++) {
            T t = elements[i];
            if (i == size - 1) {
                builder.append(t.toString());
            } else {
                builder.append(t.toString()).append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
