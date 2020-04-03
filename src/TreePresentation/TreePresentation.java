package TreePresentation;

import java.util.ArrayList;

public class TreePresentation<T> {
    public static class Node<T> {
        public int seqNumber;
        public T obj;

        @Override
        public String toString() {
            return String.format("seqNumber:%d ; obj: %s", seqNumber, obj);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (ArrayList<Node<T>> list : levelsNodes) {
            int seqNumber = 0;
            int i = 0;
            for (Node<T> node : list) {
                res.append(printSpace((node.seqNumber * 2) - seqNumber));
                seqNumber = ((node.seqNumber * 2) + 1);
                res.append(node.obj);
            }
            res.append("\n");
        }
        return res.toString();
    }

    private String printSpace(int max) {
        String res = "";
        for (int i = 0; i < max; i++) {
            // System.out.print(" ");
            res += " ";
        }
        return res;
    }

    public ArrayList<ArrayList<Node<T>>> levelsNodes;
}
