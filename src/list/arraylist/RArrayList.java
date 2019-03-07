package list.arraylist;

import list.RIterator;
import list.RList;

import java.util.Objects;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-11-25 下午4:52
 **/
public class RArrayList<T> implements RList<T> {

    private static final int DEFAULT_CAPACITY = 20;

    private T[] items;
    private int capacity;
    private int count;

    public RArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public RArrayList(int capacity) {
        this.capacity = capacity;
        Object[] objects = new Object[capacity];
        items = (T[]) objects;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count < 1;
    }

    @Override
    public boolean contains(T o) {
        return indexOf(o) > -1;
    }

    @Override
    public T[] toArray() {
        if (isEmpty()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        T[] arrayItems = (T[]) new Object[count];
        System.arraycopy(items, 0, arrayItems, 0, count);
        return arrayItems;
    }


    @Override
    public boolean add(T o) {
        ensureCapacity();
        items[count++] = o;
        return true;
    }

    @Override
    public boolean remove(T o) {
        if (isEmpty()) {
            return false;
        }
        int index = indexOf(o);
        if (index < 0) {
            return false;
        }
        for (int i = index; i < count - 1; i++) {
            items[i] = items[i + 1];
            count--;
        }
        return true;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }
        for (int i = 0; i < count; i++) {
            items[i] = null;
        }
        count = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        if (isEmpty()) {
            return null;
        }
        return items[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T oldItem = items[index];
        items[index] = element;
        return oldItem;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);
        ensureCapacity();
        for (int i = count; i > index; i--) {
            items[i] = items[i-1];
        }
        items[index] = element;
        count++;
    }

    @Override
    public T removeByIndex(int index) {
        checkIndex(index);
        T oldItem = items[index];
        for (int i = index; i < count - 1; i++) {
            items[i] = items[i + 1];
        }
        items[count - 1] = null;
        count--;
        return oldItem;
    }

    @Override
    public int indexOf(T o) {
        if (isEmpty()) {
            return -1;
        }
        int index = -1;
        for (int i = 0; i < count; i++) {
            T item = items[i];
            if (Objects.equals(item, o)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (count == capacity) {
            capacity = capacity * 3 / 2;
            T[] newItems = (T[]) new Object[capacity];
            System.arraycopy(items, 0, newItems, 0, count);
            items = newItems;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= capacity) {
            throw new IndexOutOfBoundsException("index is invalid");
        }
    }

    @Override
    public RIterator<T> iterator() {
        return new RArrayListIterator();
    }

    private class RArrayListIterator implements RIterator<T> {

        private int index = 0;

        RArrayListIterator() {
        }

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public T next() {
            if (hasNext()) {
                return items[index++];
            }
            return null;
        }
    }
}
