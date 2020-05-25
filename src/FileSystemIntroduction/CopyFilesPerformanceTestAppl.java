package FileSystemIntroduction;

import java.io.IOException;

public class CopyFilesPerformanceTestAppl {
    private static CopyFilesPerformance test2;

    public static void main(String[] args) throws IOException {
        String from = "c:/test.avi";
        String to = "f:/test.avi";

        CopyFilesPerformance test = new CopyFilesPerformance("Test1", from, to, new CopyingLargeFilesNIO());
        test.run();

        int[] bufferSize = {100, 100 * 1024, (int) Runtime.getRuntime().freeMemory()};
        for (int buff : bufferSize) {
            test2 = new CopyFilesPerformance("Test2", from, to, new CopyingLargeFilesIOS(buff));
            test2.run();
        }


    }

}
