package Set;

import java.util.Iterator;

public class TreeSetAppl {

    public static void main(String[] args) {
        Integer numbers[] = {10, -8, 7, 9, 100, 13, 20, 11, 2, 70, 15, 21, 121, 500};
        Integer numbers1[] = {2, 7, 10, 13, 20, 11, 100, 70, 15, 21, 121, 500,-8,9};
        TreeSet theTree = new TreeSet<Integer>();
        for (int i = 0; i < numbers.length; i++) {
            theTree.add(numbers[i]);
        }
//        for (int i = 0; i < numbers.length; i++){
//            theTree.remove(numbers[i]);
//            theTree.displayTree();
//        }
        for (int i = numbers1.length-1; i > 0 ; i--){
            theTree.remove(numbers1[i]);
            theTree.displayTree();
        }
        System.out.println("Displaying the tree");
        theTree.displayTree();
        Iterator itr = theTree.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }

//        System.out.println("Inorder traversal");
//        theTree.inOrder(theTree.returnRoot());
//        System.out.println(" ");
//
//        System.out.println("Preorder traversal");
//        theTree.preOrder(theTree.returnRoot());
//        System.out.println(" ");
//
//        System.out.println("Postorder traversal");
//        theTree.postOrder(theTree.returnRoot());
//        System.out.println(" ");
//
//        System.out.println("By Level");
//        theTree.byLevel(theTree.returnRoot());
//        System.out.println(" ");
    }

}

