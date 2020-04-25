package Exceptions;

public class MemoryService {
    public static int getAvailableMemorySize() {
        int left = 0, right = Integer.MAX_VALUE;
        int middle = 0, size = 0;
        byte[]ar ;
        while (left <= right) {
            try {
                middle = (int) (((long)right + left)/2);
                ar = new byte[middle];
                ar = null;

                size = middle;
                left = middle + 1;
            } catch (OutOfMemoryError e) {
                right = middle - 1;
            }
        }
        return size;
    }
}
