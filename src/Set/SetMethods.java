package Set;

import hashSet.hashSet;

import java.util.Arrays;

public class SetMethods {
    /**
     * Assumption: no repeated numbers in each array, but
     * numbers in first array may be repeated in the second
     *
     * @param ar1 - first array
     * @param ar2 - second array
     * @return array containing numbers of first and second arrays
     * with no repetitions
     */
    public static int[] union(int ar1[], int ar2[]) {
        int res[] = Arrays.copyOf(ar1, ar1.length + ar2.length);
        int resInd = ar1.length;
        return getInts(ar1, ar2, res, resInd);
    }

    private static int[] getInts(int[] ar1, int[] ar2, int[] res, int resInd) {
        hashSet<Integer> setFirst = getFirst(ar1);
        for (int i = 0; i < ar2.length; i++) {
            if (!setFirst.contains(ar2[i])) {
                res[resInd++] = ar2[i];
            }
        }
        return Arrays.copyOf(res, resInd);
    }

    /**
     * Assumption: no repeated numbers in each array, but
     * numbers in first array may be repeated in the second
     *
     * @param ar1 - first array
     * @param ar2 - second array
     * @return array containing common numbers between first and second arrays
     * with no repetitions
     */
    public static int[] intersection(int ar1[], int ar2[]) {
        int res[] = new int[Math.min(ar1.length, ar2.length)];
        int resInd = 0;
        hashSet<Integer> setFirst = getFirst(ar2);
        for (int i = 0; i < ar1.length; i++) {
            if (setFirst.contains(ar1[i])) {
                res[resInd++] = ar1[i];
            }
        }
        return Arrays.copyOf(res, resInd);
    }


    /**
     * Assumption: no repeated numbers in each array, but
     * numbers in first array may be repeated in the second
     *
     * @param ar1 - first array
     * @param ar2 - second array
     * @return array containing numbers of first array that are not repeated
     * in the second
     */
    public static int[] difference(int ar1[], int ar2[]) {
        int[] res = new int[ar1.length];
        int resInd = 0;
        return getInts(ar2, ar1, res, resInd);
    }

    private static hashSet<Integer> getFirst(int[] ar1) {
        hashSet<Integer> setFirst = new hashSet<Integer>();
        for (int i = 0; i < ar1.length; i++) {
            setFirst.add(ar1[i]);
        }
        return setFirst;
    }
}

