package TextOrientedStreams;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class OutputTextStreams {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream printStream = new PrintStream("ps_file");
        PrintWriter printWriter = new PrintWriter("pw_file");
        printStream.println("Hello word");
        printWriter.println("Hello word");
    }
}
