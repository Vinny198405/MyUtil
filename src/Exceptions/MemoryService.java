package Exceptions;

public class MemoryService {
    public static int getAvailableMemorySize() {
        int left = 0;
        int right = Integer.MAX_VALUE;
        int middle = right / 2;
        byte[] ar;

        while (left < right) {
            try {
                ar = new byte[middle];
                left = middle + 1;
            } catch (OutOfMemoryError e) {
                right = middle - 1;
            }
            middle = (left + right) / 2;
            ar = null;
        }

        return middle;
    }
}
