package list.linkedlist;

import list.RIterator;
import list.RList;

import java.util.NoSuchElementException;

/**
 * DESCRIPTION
 * 双向链表
 *
 * @author lixin
 * @create 2019-01-03 下午11:21
 **/
public class DLinkedList<T> implements RList<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    private static class Node<T> {
        T item;
        Node<T> prev;
        Node<T> next;

        public Node(T t, Node<T> prev, Node<T> next) {
            this.item = t;
            this.prev = prev;
            this.next = next;
        }
    }

    public void addFirst(T e) {

        Node<T> f = first;
        Node<T> newNode = new Node<>(e, null, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    public void addLast(T e) {

        final Node<T> l = last;
        Node<T> newNode = new Node<>(e, l, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T element) {
        if (isEmpty()) {
            addLast(element);
        } else {
            checkElementIndex(index);
            if (index == 0) {
                addFirst(element);
            } else if (index == size) {
                addLast(element);
            } else {
                Node<T> node = getNode(index);
                Node<T> newNode = new Node<>(element, node.prev, node);
                node.prev.next = newNode;
                node.prev = newNode;
                size++;
            }
        }

    }

    @Override
    public boolean add(T o) {
        addLast(o);
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T o) {
        return indexOf(o) != -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
        if (isEmpty()) {
            return null;
        }
        Object[] arr = new Object[size];
        int i = 0;
        for (Node<T> node = first; node != null; node = node.next) {
            arr[i++] = node.item;
        }
        return (T[]) arr;
    }

    @Override
    public boolean remove(T o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        removeByIndex(index);
        return true;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        final Node<T> f = first;
        T result = f.item;
        first = f.next;
        if (first == null) {
            last = null;
        } else {
            first.prev = null;
            f.next = null;
            f.item = null;
        }
        size--;
        return result;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> l = last;
        T result = l.item;
        last = last.prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        l.prev = null;
        l.item = null;
        size--;
        return result;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }
        for (Node<T> node = first; node != null; ) {
            Node<T> nextNode = node.next;
            node.item = null;
            node.next = null;
            node.prev = null;
            node = nextNode;
        }
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return getNode(index).item;
    }

    public T getFirst() {
        final Node<T> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    public T getLast() {
        final Node<T> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }

    private Node<T> getNode(int index) {
        Node<T> node;
        if (index < size >> 2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    @Override
    public T set(int index, T element) {
        checkElementIndex(index);
        Node<T> oldNode = getNode(index);
        T oldVal = oldNode.item;
        oldNode.item = element;
        return oldVal;
    }

    @Override
    public T removeByIndex(int index) {
        checkElementIndex(index);
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<T> node = getNode(index);
            T result = node.item;
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
            node.item = null;
            size--;
            return result;
        }
    }

    @Override
    public int indexOf(T o) {
        int index = 0;
        if (o == null) {
            for (Node<T> node = first; node != null; node = node.next) {
                if (node.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<T> node = first; node != null; node = node.next) {
                if (node.item.equals(o)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("index is out of bounds");
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    @Override
    public RIterator<T> iterator() {
        return new DLinkedListIterator();
    }

    private class DLinkedListIterator implements RIterator<T> {

        private int index;
        private Node<T> node;

        public DLinkedListIterator() {
            node = first;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T result = node.item;
            node = node.next;
            index++;
            return result;
        }
    }
}
