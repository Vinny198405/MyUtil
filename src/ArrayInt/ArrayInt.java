package ArrayInt;

import java.util.Arrays;

public class ArrayInt {

    public static int[] insert1(int ar[], int index, int number) {
        int res[] = ar;
        res = new int[ar.length + 1];
        if (index == 0) {
            res[index] = number;
            for (int i = 0; i < ar.length; i++) {
                res[i + 1] = ar[i];
            }
        }
        if (index > 0 & index < res.length) {
            for (int i = 0, j = 0; i < res.length; i++, j++) {
                if (i == index) {
                    res[i] = number;
                    j--;
                } else {
                    res[i] = ar[j];
                }
            }
        }
        if (index < 0 | index > res.length - 1) {
            res = ar;
        }

        return res;
    }

    /**
     * searches for index of a given number in a given array
     *
     * @param ar     - array of number
     * @param number - number for search
     * @return - index value or -1
     */
    public static int search(int[] ar, int number) {

        for (int i = 0; i < (ar.length); i++) {
            if (number == ar[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * sorting of numbers
     *
     * @param ar - array of number
     */
    public static void sort(int[] ar) {
        boolean flSort = false;
        int length = ar.length;
        do {
            flSort = true;
            length--;
            for (int i = 0; i < length; i++) {
                if (ar[i] > ar[i + 1]) {
                    int tmp = ar[i];
                    ar[i] = ar[i + 1];
                    ar[i + 1] = tmp;
                    flSort = false;
                }
            }

        } while (!flSort);

    }

    /**
     * searches for index of a given number in a given sorted array
     *
     * @param ar     - array of number
     * @param number - number for search
     * @return - index value or -1
     */
    public static int binarySearch(int[] ar, int number) {
        int left = 0;
        int right = ar.length - 1;
        int middle = (left + right) / 2;
        while (left <= right && ar[middle] != number) {
            if (number < ar[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
            middle = (left + right) / 2;
        }
		/*int res = -(left+1);  //home work
		if (ar[middle] == number) {
			res = middle;
		}*/
        int res = left > right ? -(left + 1) : middle;
        if (res >= 0) {
            //searching for first index of the equaled values
            while (res >= 0 && ar[res] == number) {
                res--;
            }
            res++;
        }
        return res;
    }

    /**
     * @param ar
     * @param number
     * @return amount of elements equaled to number
     */
    public static int countSorted(int ar[], int number) {
        int ind = binarySearch(ar, number);
        int count = 0;
        if (ind >= 0) {
            while (ind < ar.length && ar[ind] == number) {
                count++;
                ind++;
            }
        }
        return count;
    }


    /**
     * @param ar
     * @param index
     * @param number
     * @return insert number of array
     */
    public static int[] insert(int ar[], int index, int number) {
        int res[] = ar;

        if (index >= 0 && index <= ar.length) {
            res = new int[ar.length + 1];
            System.arraycopy(ar, 0, res, 0, index);
            System.arraycopy(ar, index, res, index + 1, ar.length - index);
            res[index] = number;
        }

        return res;
    }

    /**
     * @param ar
     * @param index
     * @return remove index of array
     */
    public static int[] remove(int ar[], int index) {
        int res[] = ar;
        if (index >= 0 && index < ar.length) {
            res = new int[ar.length - 1];
            System.arraycopy(ar, 0, res, 0, index);
            System.arraycopy(ar, index + 1, res, index, res.length - index);
        }
        return res;
    }

    /**
     * @param ar
     * @param number
     * @return insert number of sorted array
     */
    public static int[] insertSorted(int ar[], int number) {
        //int res[]= ar;
        int i = 0;
        int index = 0;

        while (ar[i] < number && i < ar.length - 1) {
            i++;
            if (ar[i] < number) {
                index = i + 1;
            } else {
                index = i;
            }
        }

        int res[] = insert(ar, index, number);

        return res;
    }

    //**************HOME WORK***************
    /* methods of the HW #3 */

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
        for (int i = 0; i < ar1.length; i++) {
            int index = ArrayInt.search(ar2, ar1[i]);
            if (index > 0) {
                ar2 = ArrayInt.remove(ar2, index);
            }
        }
        int res[] = new int[ar1.length + ar2.length];
        System.arraycopy(ar1, 0, res, 0, ar1.length);
        System.arraycopy(ar2, 0, res, ar1.length, res.length - ar1.length);
        return res;
    }
	/*public static int[] union (int ar1[], int ar2[]) {
		int res[] = Arrays.copyOf(ar1, ar1.length + ar2.length);
		int resInd = ar1.length;
		Arrays.sort(ar1);
		for (int i = 0; i < ar2.length; i++) {
			if (Arrays.binarySearch(ar1, ar2[i]) < 0) {
				res[resInd++] = ar2[i];
			}
		}
		return Arrays.copyOf(res, resInd);
	}*/

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
        for (int i = 0; i < ar1.length; i++) {
            if (search(ar2, ar1[i]) >= 0) {
                res[resInd++] = ar1[i];
            }
        }
        return Arrays.copyOf(res, resInd);
    }
	/*public static int[] intersection (int ar1[], int ar2[]) {
		int res[] = {};
		for (int i = 0; i < ar1.length;i++) {
			int index = Arrayint.search(ar2, ar1[i]);
			if (index > 0) {
				res = Arrayint.insert(res, res.length, ar1[i]);
			}
		}
		return res;
	}*/

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
        for (int i = 0; i < ar1.length; i++) {
            if (search(ar2, ar1[i]) < 0) {
                res[resInd++] = ar1[i];
            }

        }
        return Arrays.copyOf(res, resInd);
    }
	/*public static int[] difference (int ar1[], int ar2[]) {
		int res[] = ar1;
		for (int i = 0; i < ar1.length;i++) {
			int index = Arrayint.search(res, ar2[i]);
			if (index >= 0) {
				res = Arrayint.remove(res, index);
			}
		}
		return res;
	}*/
    //--------------------------------------

}

