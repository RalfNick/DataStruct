package tree;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-03-02 下午8:21
 **/
public class BinarySearchTree<T extends Comparable<? super T>> {

    private Node<T> root;

    private static class Node<T> {

        public T data;
        public Node<T> left;
        public Node<T> right;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T value) {
        return contains(value, root);
    }

    private boolean contains(T value, Node<T> root) {
        if (root == null) {
            return false;
        }
        int result = root.data.compareTo(value);
        if (result == 0) {
            return true;
        } else if (result < 0) {
            return contains(value, root.right);
        } else {
            return contains(value, root.left);
        }
    }

    public void insert(T value) {
        root = insert(root, value);
    }

    private Node<T> insert(Node<T> root, T value) {
        if (root == null) {
            return new Node<>(value);
        }
        Node<T> node = new Node<>();
        node.data = value;
        int result = root.data.compareTo(value);
        if (result > 0) {
            root.left = insert(root.left, value);
            return root;
        } else if (result < 0) {
            root.right = insert(root.right, value);
            return root;
        } else {
            return root;
        }

    }

    public boolean remove(T value) {
        return remove(root, value) != null;
    }

    private Node<T> remove(Node<T> root, T value) {

        if (root == null) {
            return null;
        }
        int result = root.data.compareTo(value);
        if (result == 0) {
            if (root.left != null && root.right != null) {
                Node<T> node = findMin(root.right);
                root.data = node.data;
                root.right = remove(root.right, node.data);
            } else {
                root = root.left == null ? root.right : root.left;
            }
        } else if (result > 0) {
            root.left = remove(root.left, value);
        } else {
            root.right = remove(root.right, value);
        }
        return root;
    }

    public T findMax() {
        Node<T> max = findMax(root);
        if (max == null) {
            return null;
        }
        return max.data;
    }

    private Node<T> findMax(Node<T> root) {
        if (root == null) {
            return null;
        }
        if (root.right != null) {
            return findMax(root.right);
        }
        return root;
    }

    public T findMin() {
        Node<T> minNode = findMin(root);
        if (minNode == null) {
            return null;
        }
        return minNode.data;
    }

    private Node<T> findMin(Node<T> root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            return findMin(root.left);
        }
        return root;
    }

    public boolean removeItertation(T value) {
        if (value == null) {
            return false;
        }
        // 删除节点的父节点
        Node<T> pp = null;
        // 删除节点
        Node<T> p = root;
        while (value.compareTo(p.data) != 0) {
            pp = p;
            p = value.compareTo(p.data) > 0 ? p.right : p.left;
            if (p == null) {
                return false;
            }
        }
        if (p.left != null && p.right != null) {
            // 右子树删除节点的父节点
            Node<T> minPP = p;
            // 右子树删除节点
            Node<T> minP = p.right;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data;
            pp = minPP;
            p = minP;
        }
        Node<T> child = p.left != null ? p.left : p.right;
        if (pp == null) {
            root = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
        return true;
    }

}
