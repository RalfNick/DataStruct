package List.linkedlist;

import List.RIterator;
import List.RList;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-23 下午3:14
 **/
public class RLinkedList<T> implements RList<T> {

    /**
     * 链表节点
     */
    private static class Node<T> {

        public T item;
        public Node<T> next;

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    public RLinkedList() {
        first = last = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(T o) {
        return getNode(o) != null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
        if (isEmpty()) {
            return null;
        }
        T[] arr = (T[]) new Object[size];
        Node<T> node = first;
        arr[0] = node.item;
        for (int i = 1; i < size; i++) {
            node = node.next;
            arr[i] = node.item;
        }

        return arr;
    }

    @Override
    public boolean add(T o) {

        final Node<T> l = last;
        Node<T> newNode = new Node<>(o, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(T o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }

        if (index == size - 1) {
            removeLast();
        } else if (index == 0) {
            Node<T> node = first;
            first = first.next;
            node.next = null;
            node.item = null;
            size--;
        } else {
            Node<T> preNode = getNodeByIndex(index - 1);
            Node<T> node = preNode.next;
            preNode.next = node.next;
            node.next = null;
            node.item = null;
            size--;
        }
        return true;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T result;
        if (size == 1) {
            result = last.item;
            first = last = null;
        } else {
            Node<T> preNode = getNodeByIndex(size - 2);
            result = preNode.next.item;
            preNode.next = null;
            last = preNode;
        }
        size--;
        return result;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }
        Node<T> node;
        while (first != null) {
            node = first;
            first = first.next;
            node.next = null;
            node.item = null;
        }
        size = 0;
    }

    @Override
    public T get(int index) {

        Node<T> result = getNodeByIndex(index);
        if (result != null) {
            return result.item;
        }
        return null;
    }

    @Override
    public T set(int index, T element) {
        if (isEmpty()) {
            return null;
        }
        Node<T> node = getNodeByIndex(index);
        T oldValue = node.item;
        node.item = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index is out of bounds!");
        }
        if (index == size) {
            add(element);
        } else if (index == 0) {
            Node<T> node = new Node<>(element, null);
            node.next = first;
            first = node;
            size++;
        } else {
            Node<T> preNode = getNodeByIndex(index - 1);
            Node<T> node = new Node<>(element, null);
            node.next = preNode.next;
            preNode.next = node;
            size++;
        }
    }

    @Override
    public T removeByIndex(int index) {

        if (isEmpty()) {
            return null;
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node<T> node;
        T result;
        if (index == 0) {
            result = first.item;
            node = first;
            first = first.next;
        } else {
            Node<T> preNode = getNodeByIndex(index - 1);
            node = preNode.next;
            result = node.item;
            preNode.next = node.next;
        }
        node.next = null;
        size--;
        return result;
    }

    @Override
    public int indexOf(T o) {
        if (isEmpty()) {
            return -1;
        }
        int index = 0;
        Node current = first;
        while (current != null) {
            if (current.item.equals(o)) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    private Node<T> getNode(T o) {

        if (isEmpty()) {
            return null;
        }
        Node<T> node = first;
        while (node != null) {
            if (node.item.equals(o)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    private Node<T> getNodeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index is out of bounds!");
        }
        if (index == size - 1) {
            return last;
        }
        Node<T> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

    @Override
    public RIterator<T> iterator() {
        return new RLinkListIterator();
    }

    private class RLinkListIterator implements RIterator<T> {

        private Node<T> node;

        public RLinkListIterator() {
            this.node = first;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            T result = node.item;
            node = node.next;
            return result;
        }
    }
}
