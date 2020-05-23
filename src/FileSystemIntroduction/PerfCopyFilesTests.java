package FileSystemIntroduction;

import java.io.IOException;

public abstract class PerfCopyFilesTests {
    private String testName;

    PerfCopyFilesTests(String testName) {
        this.testName = testName;
    }

    protected abstract void runTest() throws IOException;

    public void run() throws IOException {
        double startTime = System.currentTimeMillis();
        runTest();
        double endTime = System.currentTimeMillis();
        System.out.format("%s file downloaded for: %f ms \n", testName, endTime - startTime);
    }

}
