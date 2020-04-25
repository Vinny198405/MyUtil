package Exceptions;

import java.util.ArrayList;

public class MemoryTestAppl {
    static final int Mg = 1024 * 1024;
    static Runtime runtime = Runtime.getRuntime();
    public static void main(String[] args) {

//		long freeMem = runtime.freeMemory();//free heap memory of JVM
//		long totalMem = runtime.totalMemory();//common heap memory of JVM
//		long maxMem = runtime.maxMemory();//maximal memory that JVM can require from OS
//		int maxArrayMem = MemoryService.getAvailableMemoryBlockSize();
//		System.out.printf("free memory: %d; total memory: %d; maximal memory: %d,"
//				+ " maximal array memory: %d\n", freeMem / Mg, totalMem / Mg,
//				maxMem / Mg, maxArrayMem / Mg);
//		byte ar[] = new byte[(int)freeMem];
//		freeMem = runtime.freeMemory();//free heap memory of JVM
//		totalMem = runtime.totalMemory();//common heap memory of JVM
//		 maxMem = runtime.maxMemory();//maximal memory that JVM can require from OS
//		maxArrayMem = MemoryService.getAvailableMemoryBlockSize();
//		System.out.printf("free memory: %d; total memory: %d; maximal memory: %d,"
//				+ " maximal array memory: %d\n", freeMem / Mg, totalMem / Mg,
//				maxMem / Mg, maxArrayMem / Mg);
        ArrayList<byte[]> arrays = new ArrayList<>();
        System.out.println("maximal memory: " + runtime.maxMemory());
        printMemoryState();
        System.out.println("Before allocations \n -------------------");

        while(true) {
            long freeMem = runtime.freeMemory();
            int size = freeMem >= Integer.MAX_VALUE ? Integer.MAX_VALUE :
                    (int)freeMem;
            try {
                byte ar[] = new byte[size];
                arrays.add(ar);
                printMemoryState();
                arrays = null;
                arrays = new ArrayList<>();
            } catch (Throwable  e) {
                break;
            }
        }
        System.out.println("number of arrays: " + arrays.size());


    }
    private static void printMemoryState() {
        System.out.printf("free memory: %dMb; total memory: %dMb\n",
                runtime.freeMemory() / Mg,
                runtime.totalMemory() / Mg);

    }

}
