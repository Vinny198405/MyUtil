package FileSystemIntroduction;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class CopyingLargeFilesNIO implements CopyFilesInterface {

    @Override
    public void copyFiles(String from, String to) throws IOException {
        FileChannel in = new RandomAccessFile(from, "r").getChannel();
        FileChannel out = new RandomAccessFile(to, "rw").getChannel();
        out.transferFrom(in, 0, Long.MAX_VALUE);
        in.close();
        System.out.println("read counter: " + out.size() + " bytes");
        out.close();
    }
}
