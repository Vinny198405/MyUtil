package FileSystemIntroduction;

import java.io.IOException;

public class CopyFilesPerformance extends PerfCopyFilesTests {
    private CopyFilesInterface copyFiles;
    private String from;
    private String to;

    CopyFilesPerformance(String testName, String from, String to, CopyFilesInterface copyFiles) {
        super(testName);
        this.copyFiles = copyFiles;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void runTest() throws IOException {
        copyFiles.copyFiles(from, to);
    }
}
