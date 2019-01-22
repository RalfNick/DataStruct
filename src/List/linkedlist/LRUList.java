package List.linkedlist;

/**
 * DESCRIPTION
 * LRU 模拟
 *
 * @author lixin
 * @create 2019-01-21 下午7:52
 **/
public class LRUList<T> {

    private static final int DEFAULT_SIZE = 10;
    private int capacity;
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LRUList() {
        this(DEFAULT_SIZE);
    }

    public LRUList(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 访问元素 t
     * -查询数据
     * --存在 - 在头部则返回  - 不在头部，移动到头部（是否是结尾）
     * --不存在 添加数据-添加到头部
     * ---是否达到capacity -是 移除尾部数据 -否 ¬不移除
     *
     * @param t 元素
     */
    public void access(T t) {
        int index = indexOfElement(t);
        if (index != -1) {
            if (index == 0) {
                return;
            } else {
                moveToHead(index);
            }
        } else {
            addElement(t);
        }
    }

    /**
     * 添加元素到头部
     *
     * @param t 元素
     */
    private void addElement(T t) {
        Node<T> node = new Node<>(t);
        if (size == capacity) {
            removeLast();
        }
        Node<T> f = head;
        node.prev = null;
        node.next = head;
        head = node;
        if (f == null) {
            tail = node;
        } else {
            f.prev = node;
        }
        size++;
    }

    /**
     * 移除最后一个节点
     */
    private void removeLast() {
        if (isEmpty()) {
            return;
        }
        Node<T> l = tail;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        l.prev = null;
        l.item = null;
        size--;
    }

    /**
     * 将元素移动到头部
     *
     * @param index 索引
     */
    private void moveToHead(int index) {
        Node<T> node;
        if (index == size - 1) {
            node = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            node = getNodeByIndex(index);
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        node.prev = null;
        node.next = head;
        head.prev = node;
        head = node;
    }

    /**
     * 根据索引获取节点
     *
     * @param index 索引
     * @return 节点
     */
    private Node<T> getNodeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * 查找节点索引
     *
     * @param t 元素
     * @return 不考虑为空的元素
     */
    private int indexOfElement(T t) {
        if (isEmpty()) {
            return -1;
        }
        int index = 0;
        for (Node node = head; node != null; node = node.next) {
            if (node.item.equals(t)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        for (Node<T> node = head; node != null; node = node.next) {
            builder.append(node.item.toString()).append(",");
        }
        String result = builder.toString();
        return result.substring(0, result.length() - 1);
    }

    private static class Node<T> {
        private Node<T> prev;
        private Node<T> next;
        private T item;

        public Node(T item) {
            this.item = item;
        }
    }
}
