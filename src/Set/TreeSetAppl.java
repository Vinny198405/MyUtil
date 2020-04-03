package Set;

import TreePresentation.TreePresentation;

public class TreeSetAppl {

    public static void main(String[] args) throws Exception {
        Integer[] numbers = {10, -8, 7, 9, 100, 13, 20, 11, 2, 70, 15, 21, 121, 500};
        Integer[] numbers1 = {2, 7, 10, 13, 20, 11, 100, 70, 15, 21, 121, -8, 9, 500};
        TreeSet theTree = new TreeSet<Integer>();
        for (Integer number : numbers) {
            theTree.add(number);
        }

        TreeSet tree = new TreeSet<Integer>();
        TreeSetRandom(tree, 0, 100, 30);
//        tree.rotatedTreeDisplay();
        System.out.printf("width = %d; height = %d\n",tree.width(), tree.height());
//        theTree.separationTreeToLevel();
        //    theTree.printTree();
        //    theTree.add(600);
        //   theTree.printTree();
        tree.balance();
        tree.printTree();
       // theTree.balance();
       // theTree.printTree();
        TreePresentation<Integer> treePresentation = tree.getTreePresentation();
        System.out.println(treePresentation);
    }

    private static void TreeSetRandom(TreeSet<Integer> tree, int min, int max, int count) {
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

