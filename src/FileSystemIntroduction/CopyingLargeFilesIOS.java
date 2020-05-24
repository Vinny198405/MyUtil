package FileSystemIntroduction;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyingLargeFilesIOS implements CopyFilesInterface {
    private int bufferSize;

    public CopyingLargeFilesIOS(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    @Override
    public void copyFiles(String from, String to) throws IOException {
        FileInputStream in = new FileInputStream(from);
        FileOutputStream out = new FileOutputStream(to);
        byte[] buf = new byte[bufferSize];
        int len;
        long count = 0;
        while ((len = in.read(buf)) >= 0) {
            count += len;
            out.write(buf, 0, len);

        }
        in.close();
        out.close();
        System.out.println("read counter: " + count + " bytes");
    }
}
