package List.linkedlist;

import List.RIterator;
import List.RList;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-23 下午3:14
 **/
public class SLinkedList<T> implements RList<T> {

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

    public SLinkedList() {
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

    /******************************************练习测试*****************************************/
    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {
        if (isEmpty() || size < 2) {
            return;
        }
        // 麻烦方法
//        Node<T> pre;
//        for (int i = size - 2; i >= 0; i--) {
//            pre = getNodeByIndex(i);
//            pre.next.next = pre;
//            pre.next = null;
//        }
//        Node<T> temp = first;
//        first = last;
//        last = temp;

        // 简单做法
        last = first;
        Node<T> pre = first;
        Node<T> node = first.next;
        Node<T> nextNode = first.next.next;
        while (nextNode != null) {
            node.next = pre;
            pre = node;
            node = nextNode;
            nextNode = nextNode.next;
        }
        node.next = pre;
        first = node;
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        if (isEmpty() || size < 2) {
            return;
        }
        Node<T> f = first;
        Node<T> node = getNodeByIndex(size / 2 - 1);
        first = node.next;
        node.next = null;
        size = size / 2 + size % 2;
        // 清理前半部分
        while (f != null) {
            Node<T> temp = f;
            f = f.next;
            temp.next = null;
            temp.item = null;
        }
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     */
    public void remove(int i, int length) {
        if (isEmpty() || length < 1 || length > size) {
            return;
        }
        length = Math.min(length, size - i);
        Node<T> endNode = getNodeByIndex(i + length - 1);
        // 尾节点
        if (endNode.next == null) {
            last = null;
        }
        Node<T> node;
        if (i == 0) {
            node = first;
            first = endNode.next;
        } else {
            Node<T> pre = getNodeByIndex(i - 1);
            node = pre.next;
            pre.next = endNode.next;
        }
        endNode.next = null;
        size -= length;
        // 销毁各个节点
        while (node != null) {
            Node<T> temp = node;
            node = node.next;
            temp.next = null;
            temp.item = null;
        }
    }

    /**
     * 假定当前链表和list均包含已升序排列的整数
     * 从当前链表中取出那些list所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     */
    @SuppressWarnings("unchecked")
    public T[] getElements(SLinkedList<Integer> list) {

        if (list == null || list.isEmpty()) {
            return null;
        }
        int count = Math.min(size, list.size());
        Object[] results = new Object[count];
        int i = 0;
        for (Node<Integer> node = list.first; node != null; node = node.next) {
            if (node.item >= size) {
                break;
            }
            results[i] = getNodeByIndex(node.item).item;
            i++;
        }
        if (i < count) {
            Object[] subResult = new Object[i];
            System.arraycopy(results, 0, subResult, 0, i);
            return (T[]) subResult;
        }
        return (T[]) results;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
     */
    public void subtract(SLinkedList<Integer> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        if (list.first.item > (Integer) this.last.item
                || list.last.item < (Integer) this.first.item) {
            return;
        }
        int index = 0;
        Node<T> node = first;
        while (node != null) {
            if (index == list.size) {
                break;
            }
            Node<T> temp = node;
            node = node.next;
            for (Node<Integer> listNode = list.getNodeByIndex(index);
                 listNode != null; listNode = listNode.next) {
                if (listNode.item == temp.item) {
                    remove(temp.item);
                    break;
                } else if (listNode.item < (Integer) temp.item) {
                    index++;
                }
            }
        }
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        if (isEmpty() || size < 2) {
            return;
        }
        T preVal = first.item;
        for (Node<T> node = first.next; node != null; node = node.next) {
            if (node.item.equals(preVal)) {
                remove(preVal);
            } else {
                preVal = node.item;
            }
        }
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     */
    public void removeRange(int min, int max) {

        if (max < min || isEmpty() || min > (Integer) last.item || max < (Integer) first.item) {
            return;
        }
        int minVal = Math.max(min, (Integer) first.item);
        int maxVal = Math.min(max, (Integer) last.item);
        Node<T> node = first;
        while (node != null) {
            Node<T> temp = node;
            node = node.next;
            if ((Integer) temp.item > maxVal) {
                break;
            } else if ((Integer) temp.item >= minVal && (Integer) temp.item <= maxVal) {
                remove(temp.item);
            }
        }
    }

    /**
     * 123456789876543
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     */
    @SuppressWarnings("unchecked")
    public SLinkedList<Integer> intersection(SLinkedList<Integer> list) {
        if (isEmpty()) {
            return list;
        }
        if (list == null || list.isEmpty()) {
            return (SLinkedList<Integer>) this;
        }
        SLinkedList<Integer> resultList = new SLinkedList<>();
        Node<Integer> node;
        if (list.first.item > (Integer) this.last.item) {
            for (node = (Node<Integer>) first; node != null; node = node.next) {
                resultList.add(node.item);
            }
            for (node = list.first; node != null; node = node.next) {
                resultList.add(node.item);
            }
        } else if (list.last.item < (Integer) this.first.item) {
            for (node = list.first; node != null; node = node.next) {
                resultList.add(node.item);
            }
            for (node = (Node<Integer>) first; node != null; node = node.next) {
                resultList.add(node.item);
            }
        } else {
            Node<Integer> node1 = (Node<Integer>) first;
            Node<Integer> node2 = list.first;
            int preVal = Integer.MIN_VALUE;
            for (int i = 0; i < size + list.size; i++) {
                if (node1 == null && node2 == null) {
                    break;
                } else if (node1 == null) {
                    resultList.add(node2.item);
                    node2 = node2.next;
                } else if (node2 == null) {
                    resultList.add(node1.item);
                    node1 = node1.next;
                } else {
                    int item1 = node1.item;
                    int item2 = node2.item;
                    if (item1 < item2) {
                        if (item1 != preVal) {
                            resultList.add(item1);
                            preVal = item1;
                        }
                        node1 = node1.next;
                    } else if (item1 > item2) {
                        if (item2 != preVal) {
                            resultList.add(item2);
                            preVal = item2;
                        }
                        node2 = node2.next;
                    } else {
                        if (node1.item != preVal) {
                            resultList.add(item1);
                            preVal = item1;
                        }
                        node1 = node1.next;
                        node2 = node2.next;
                    }
                }

            }
        }
        return resultList;
    }

    /**
     * 获取中间节点，假设不知道长度（快慢指针）
     *
     * @return
     */
    public T getMiddle() {
        if (isEmpty()) {
            return null;
        }
        Node<T> slowNode = first;
        Node<T> quickNode = first;
        while (quickNode != null && quickNode.next != null) {
            slowNode = slowNode.next;
            quickNode = quickNode.next.next;
        }
        return slowNode.item;
    }

    /**
     * 如何判断一个字符串是否是回文字符串的问题
     */
    public boolean isPalindrome() {
        if (isEmpty()) {
            return false;
        } else if (size == 1) {
            return true;
        } else if (size == 2) {
            if (first.item.equals(first.next.item)) {
                return true;
            }
            return false;
        }
        Node<T> slowNode = first;
        Node<T> quickNode = first;
        Node<T> pre = null;
        Node<T> nextNode = first.next;
        while (quickNode != null && quickNode.next != null) {
            quickNode = quickNode.next.next;
            slowNode.next = pre;
            pre = slowNode;
            slowNode = nextNode;
            nextNode = nextNode.next;
        }
        slowNode.next = nextNode;
        Node<T> temp = pre;
        // 偶数
        if (quickNode == null) {
            if (!pre.item.equals(slowNode.item)) {
                return false;
            }
            pre = pre.next;
            temp.next = slowNode;
            slowNode = temp;
            temp = pre;
        }
        boolean result = true;
        while (pre != null) {
            if (!pre.item.equals(nextNode.item)) {
                result = false;
            }
            nextNode = nextNode.next;
            pre = pre.next;
            temp.next = slowNode;
            slowNode = temp;
            temp = pre;
        }
        return result;
    }
}
