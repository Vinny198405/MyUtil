package Set;

import java.util.*;
import java.util.function.Predicate;

public class TreeSet<T> implements Set<T> {
    private static class Node<T> {
        T obj;
        Node<T> parent;
        Node<T> left; //reference to a less (relative to comparator)
        Node<T> right; // reference to a greater

        public Node(T obj) {
            this.obj = obj;
        }
    }

    public Node returnRoot() {
        return root;
    }

    Comparator<T> comparator;
    Node<T> root;
    int size;

    public TreeSet(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    public TreeSet() {
        this((Comparator<T>) Comparator.naturalOrder());
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeSetIterator();
    }

    @Override
    public boolean add(T obj) {
        if (root == null) {
            addRoot(obj); //addRoot creates new Node that will be root
            return true;
        }
        Node<T> parent = getParent(obj);
        //if obj already exists (compare return 0) -> returns false

        if (parent == null) {
            return false;
        }
        Node<T> newNode = new Node<>(obj);
        if (comparator.compare(obj, parent.obj) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
        newNode.parent = parent;
        return true;
    }

    private void addRoot(T obj) {
        size = 1;
        root = new Node<>(obj);
    }

    private Node<T> getParent(T obj) {
        Node<T> current = root;
        Node<T> parent = null;

        while (current != null) {
            parent = current;
            int cmp = comparator.compare(obj, current.obj);
            if (cmp == 0) return null;

            current = cmp < 0 ? current.left : current.right;
        }
        return parent;
    }

    @Override
    public Set<T> filter(Predicate<T> predicate) {
        TreeSet<T> res = new TreeSet<T>();
        for (T obj : this) {
            if (predicate.test(obj)) {
                res.add(obj);
            }
        }
        return res;
    }

    @Override
    public Object remove(Object pattern) {
        Object res = null;
        Node<T> node = findNode(pattern);
        if (node != null) {
            res = node.obj;
            removeNode(node);
        }
        return res;
    }

    public Node<T> findNode(Object pattern) {
        Node<T> current = root;
        while (current.obj != pattern) {
            if (comparator.compare(current.obj, (T) pattern) > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    @Override
    public boolean removeIf(Predicate<T> predicate) {
        Iterator<T> itr = iterator();
        int initSize = size;
        while (itr.hasNext()) {
            T obj = itr.next();
            if (predicate.test(obj)) {
                itr.remove();
            }
        }
        return initSize > size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T pattern) {
        return size > 0 && getParent(pattern) == null;
    }

    private class TreeSetIterator implements Iterator<T> {
        Node<T> current = getLeastNode(root);
        Node<T> remove;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            remove = current;
            T res = current.obj;
            current = current.right != null ?
                    getLeastNode(current.right) :
                    getParentFromLeft(current);

            return res;
        }

        @Override
        public void remove() {
            removeNode(remove);
        }
    }

    private Node<T> getLeastNode(Node<T> node) {
        Node<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node<T> getParentFromLeft(Node<T> node) {
        while (node.parent != null && node.parent.right == node) {
            node = node.parent;
        }
        return node.parent;
    }

    private void removeNode(Node<T> node) {
        if (isJunction(node)) {
            Node<T> substitute = getLeastNode(node.right);
            node.obj = substitute.obj;
            node = substitute;
        }
        removeNonJunctionNode(node);
        size--;
    }

    private void removeNonJunctionNode(Node<T> node) {
        if (node.left == null && node.right == null && node.parent == null) {
            root = null;
        }
        //remove tree leaf
        if (node.left == null && node.right == null && node.parent != null) {
            if (node == node.parent.left) node.parent.left = null;
            else node.parent.right = null;
        }
        //Removing a node that has a left subtree but does not have a right subtree
        if (node.left != null && node.right == null) {
            //Change parent
            node.left.parent = node.parent;
            if (node == node.parent.left) {
                node.parent.left = node.left;
            } else if (node == node.parent.right) {
                node.parent.right = node.left;
            }
        }
        //Removing a node that has a right subtree but does not have a left subtree
        if (node.left == null && node.right != null) {
            //Change parent
            node.right.parent = node.parent;
            if (node == root) {
                root = node.right;
            } else if (node == node.parent.left) {
                node.parent.left = node.right;
            } else if (node == node.parent.right) {
                node.parent.right = node.right;
            }
        }
    }

    private boolean isJunction(Node<T> node) {
        return node.left != null && node.right != null;
    }

    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int emptyLeaf = 32;
        boolean isRowEmpty = false;
        System.out.println("****......................................................****");
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;
            for (int j = 0; j < emptyLeaf; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.obj);
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    if (temp.left != null || temp.right != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < emptyLeaf * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            emptyLeaf /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        }
        System.out.println("****......................................................****");
    }

    public void preOrder(Node Root) {
        if (Root != null) {
            System.out.print(Root.obj + " ");
            preOrder(Root.left);
            preOrder(Root.right);
        }
    }

    public void inOrder(Node Root) {
        if (Root != null) {
            inOrder(Root.left);
            System.out.print(Root.obj + " ");
            inOrder(Root.right);
        }
    }

    public void postOrder(Node Root) {
        if (Root != null) {
            postOrder(Root.left);
            postOrder(Root.right);
            System.out.print(Root.obj + " ");
        }
    }

    public void byLevel(Node root) {
        Queue<Node> level = new LinkedList<>();
        level.add(root);

        while (!level.isEmpty()) {
            Node node = level.poll();
            System.out.print(node.obj + " ");
            if (node.left != null)
                level.add(node.left);
            if (node.right != null)
                level.add(node.right);
        }
    }

}
