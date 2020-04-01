package Set;

public class TreeSetAppl {

    public static void main(String[] args) throws Exception {
        Integer numbers[] = {10, -8, 7, 9, 100, 13, 20, 11, 2, 70, 15, 21, 121, 500};
        Integer numbers1[] = {2, 7, 10, 13, 20, 11, 100, 70, 15, 21, 121, -8, 9, 500};
        TreeSet theTree = new TreeSet<Integer>();
        for (int i = 0; i < numbers.length; i++) {
            theTree.add(numbers[i]);
        }

//        for (int i = 0; i < 50; i++) {
//            theTree.add((int) (Math.random() * Integer.MAX_VALUE));
//        }
//        System.out.println("Displaying the tree");
//        theTree.displayTree();
//        Iterator itr = theTree.iterator();
//        while (itr.hasNext()) {
//            System.out.print(itr.next() + " ");
//        }
//        theTree.removeIf(new EvenNumbersPredicate());
//        System.out.println("Displaying the tree");
//        theTree.displayTree();
//
//        System.out.println("Inorder traversal");
//        theTree.inOrder(theTree.getRoot());
//        System.out.println(" ");
//
//        System.out.println("Preorder traversal");
//        theTree.preOrder(theTree.getRoot());
//        System.out.println(" ");
//
//        System.out.println("Postorder traversal");
//        theTree.postOrder(theTree.getRoot());
//        System.out.println(" ");

//        System.out.println("By Level");
//        theTree.byLevel(theTree.getRoot());
//        System.out.println(" ");

        TreeSet tree = new TreeSet<Integer>();
        TreeSetRandom(tree, 0, 10, 10);
//        tree.rotatedTreeDisplay();
//        System.out.printf("width = %d; height = %d\n",tree.width(), tree.height());
//        theTree.separationTreeToLevel();
        theTree.separationTreeToLevel();
        tree.separationTreeToLevel();

    }

    static void TreeSetRandom(TreeSet<Integer> tree, int min, int max, int count) {

        if (max - min + 1 < count) {
            System.out.println("Wrong input data");
            return;
        }
        int number = 0;
        boolean res = false;
        for (int i = 0; i < count; i++) {
            do {
                number = getRandomNumber(min, max);
                if (!tree.contains(number)) {
                    tree.add(number);
                    res = true;
                }
            } while (!res);
            res = false;
        }
    }

    private static int getRandomNumber(int min, int max) {
        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }

}

