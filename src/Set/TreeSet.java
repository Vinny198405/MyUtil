package Set;

import java.util.*;
import java.util.function.Predicate;

import SortedSet.SortedSet;
import TreePresentation.TreePresentation;

public class TreeSet<T> implements SortedSet<T> {
    @Override
    public T getMin() {
        Node<T> min = root;
        min = getLeastNode(root);
        return min.obj;
    }

    @Override
    public T getMax() {
        Node<T> max = root;
        while (max.right != null) {
            max = max.right;
        }
        return max.obj;
    }

    @Override
    public SortedSet<T> subset(T from, boolean isIncludedFrom, T to, boolean isIncludedTo) {
        Node<T> current = getFrom(from);
        SortedSet<T> res = new TreeSet<T>(comparator);
        int compRes;
        compRes = comparator.compare(current.obj, from);
        if (!isIncludedFrom && compRes == 0) current = getCurrent(current);
        else if (compRes < 0) current = getCurrent(current);
        while (current != null) {
            compRes = comparator.compare(current.obj, to);
            if ((!isIncludedTo && compRes == 0) || compRes > 0) return res;
            res.add(current.obj);
            current = getCurrent(current);
        }
        return res;
    }

    private Node<T> getCurrent(Node<T> node) {
        return node.right != null ? getLeastNode(node.right) : getParentFromLeft(node);
    }

    private Node<T> getFrom(T obj) {
        Node<T> current = root;
        Node<T> from = null;

        while (current != null) {
            from = current;
            int cmp = comparator.compare(obj, current.obj);
            if (cmp == 0) return from;
            current = cmp < 0 ? current.left : current.right;
        }
        return from;
    }

    private static class Node<T> {
        T obj;
        Node<T> parent;
        Node<T> left; //reference to a less (relative to comparator)
        Node<T> right; // reference to a greater

        Node(T obj) {
            this.obj = obj;
        }
    }

    private Comparator<T> comparator;
    private Node<T> root;
    private int size;

    public TreeSet(Comparator<T> comparator) {
        this.comparator = comparator;
    }

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
        int iterations = 0;

        while (current != null) {
            parent = current;
            int cmp = comparator.compare(obj, current.obj);
            if (cmp == 0) return null;
            current = cmp < 0 ? current.left : current.right;
            iterations++;
        }

        if (!checkBalanced(iterations)) {
            balance();
            getParent(obj);
        }
        return parent;
    }

    private boolean checkBalanced(int iterations){
        boolean res = true;
        double checkBal = 0;
        int log2 = (31 - Integer.numberOfLeadingZeros(size));
        if (log2 != 0) {
            checkBal = iterations / log2;
        }
        if (size > 3 && checkBal > 1.2) {
            res = false;
        }
        return res;
    }

    public boolean isBalanced() {
        return isBalanced(root) != -1;
    }

    private int isBalanced(Node<T> node) {
        if (node == null) return 0;
        int left = isBalanced(node.left);
        int right = isBalanced(node.right);
        if (Math.abs(left - right) > 1) {
            return -1;
        } else {
            return 1 + Math.max(left, right);
        }
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

    private Node<T> findNode(Object pattern) {
        Node<T> current = root;
        int compRes;
        while (current != null && (compRes =
                comparator.compare((T) pattern, current.obj)) != 0) {
            current = compRes < 0 ? current.left : current.right;
        }
        return current;
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
        Node<T> current = root != null ? getLeastNode(root) : null;
        Node<T> removeNode;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            removeNode = current;
            T res = current.obj;
            current = getCurrent(current);

            return res;
        }

        @Override
        public void remove() {
            if (isJunction(removeNode)) {
                current = removeNode;
            }
            removeNode(removeNode);
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
        Node<T> parent = node.parent;
        Node<T> child = node.left == null ? node.right :
                node.left;
        if (parent == null) {
            //removing root as non-junction node
            root = child;
        } else if (parent.left == node) { //removing tree leaf or removing a node that has a left subtree
            parent.left = child;
        } else parent.right = child;  //removing tree leaf or removing a node that has a right subtree
        if (child != null) {
            child.parent = parent; //change parent
        }
    }

    private boolean isJunction(Node<T> node) {
        return node.left != null && node.right != null;
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> root) {
        int res = 0;
        if (root != null) {
            int heightRight = height(root.right);
            int heightLeft = height(root.left);
            res = 1 + Math.max(heightLeft, heightRight);
        }
        return res;
    }

    public int width() {
        return width(root);
    }

    private int width(Node<T> root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return width(root.left) + width(root.right);
    }

    public void rotatedTreeDisplay() {
        rotatedDisplay(root, 1);
    }

    private void rotatedDisplay(Node<T> root, int level) {
        if (root != null) {
            rotatedDisplay(root.right, level + 1);
            displayRoot(root, level);
            rotatedDisplay(root.left, level + 1);
        }
    }

    private void displayRoot(Node<T> root, int level) {
        for (int i = 0; i < level * 2; i++) {
            System.out.print(" ");
        }
        System.out.println(root.obj);
    }

    //******* HW Print Tree ***************
    private ArrayList<LinkedList<Node>> listPrint;

    private void separationTreeToLevel() {
        listPrint = new ArrayList<>();
        int max = height();
        addArray(root, 0);
        separationTreeToLevel(listPrint.get(0), 1, max);
    }

    private void separationTreeToLevel(LinkedList<Node> list, int level, int max) {
        for (Node tempNode : list) {
            if (max > 1) {
                addArray(tempNode == null ? null : tempNode.left, level);
                addArray(tempNode == null ? null : tempNode.right, level);
            }
        }
        if (max > 1) {
            separationTreeToLevel(listPrint.get(level), level + 1, max - 1);
        }
    }

    private void addArray(Node<T> node, int level) {
        if (listPrint.size() <= level) listPrint.add(level, new LinkedList<Node>());
        if (node == null) {
            listPrint.get(level).add(null);
        } else listPrint.get(level).add(node);
    }

    public void printTree() {
        separationTreeToLevel();
        int max = listPrint.size();
        for (int i = 0; i < max; i++) {
            List<Node> list = listPrint.get(i);
            printSpaceBefore(max - i);
            for (Node nodeTemp : list) {
                System.out.print(nodeTemp == null ? "-" : nodeTemp.obj);
                printSpaceAfter(max - i);
            }
            System.out.println();
        }
    }

    private void printSpaceBefore(int max) {
        for (int i = 0; i < Math.pow(2, max - 1); i++) {
            System.out.print(" ");
        }
    }

    private void printSpaceAfter(int max) {
        for (int i = 0; i < Math.pow(2, max - 1) * 2 - 1; i++) {
            System.out.print(" ");
        }
    }

    // CW *********************
    private int seqNumber;

    public TreePresentation<T> getTreePresentation() {
        TreePresentation<T> res = new TreePresentation<T>();
        ArrayList<ArrayList<TreePresentation.Node<T>>> levels = new ArrayList<>();
        seqNumber = 0;
        int nLevels = height();
        for (int i = 0; i < nLevels; i++) {
            levels.add(new ArrayList<>());
        }
        fillLevelsPresentation(root, 0, levels);
        res.levelsNodes = levels;
        return res;
    }

    private void fillLevelsPresentation(Node<T> root, int level,
                                        ArrayList<ArrayList<TreePresentation.Node<T>>> levels) {
        if (root != null) {
            fillLevelsPresentation(root.left, level + 1, levels);
            TreePresentation.Node<T> node = new TreePresentation.Node<>();
            node.obj = root.obj;
            node.seqNumber = seqNumber++;
            levels.get(level).add(node);
            fillLevelsPresentation(root.right, level + 1, levels);
        }
    }

    // ***************** HW balance tree **********************
    public void balance() {
        ArrayList<Node<T>> arrayNodes = new ArrayList<>(size);
        fillArrayNodes(arrayNodes, root); //fills array of the nodes
        root = balance(arrayNodes, 0, size - 1, null);
    }

    private Node<T> balance(ArrayList<Node<T>> arrayNodes, int left, int right, Node<T> root) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        Node<T> node;
        node = arrayNodes.get(mid);
        node.parent = root;
        node.left = balance(arrayNodes, left, mid - 1, node);
        node.right = balance(arrayNodes, mid + 1, right, node);
        return node;
    }

    private void fillArrayNodes(ArrayList<Node<T>> arrayNodes, Node<T> root) {
        if (root != null) {
            fillArrayNodes(arrayNodes, root.left);
            arrayNodes.add(root);
            fillArrayNodes(arrayNodes, root.right);
        }
    }
}
