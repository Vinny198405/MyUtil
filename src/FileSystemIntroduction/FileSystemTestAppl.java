package FileSystemIntroduction;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.*;

public class FileSystemTestAppl {
    private static final int SPACES_PER_LEVEL = 2;

    public static void main(String[] args) throws IOException {
        FileSystem fs = FileSystems.getDefault();
        //fs.getRootDirectories().forEach(System.out::println);
        //Path current = fs.getPath("..");
//	System.out.printf("isAbsolute: %s; file name: %s; absolute path: %s "
//			,current.isAbsolute(),
//			current.getFileName(),
//			current.toAbsolutePath().normalize());
//	displayDirectoryContent(fs.getPath("C:/"), 2);
        Path filePathSrc = fs.getPath("src/FileSystemIntroduction/FileSystemTestAppl.java");
        //Path filePathDest = fs.getPath("CopyFileSysteTestAppl");
        InputStream input = Files.newInputStream(filePathSrc);
        OutputStream output = new FileOutputStream("CopyFileSysteTestAppl");
        byte[] buffer = new byte[input.available()];
        System.out.println(buffer.length);
        System.out.println(input.read(buffer));
        output.write(buffer);
        output.close();
    }

    private static void displayDirectoryContent(Path path, int depth) {
        // prints directory structure of the given path directory and depth
        //if depth equals 0 it prints all directory structure from the given start
        //output:
        // dir_name       level 1
        //     file_name
        //     ......
        //     dir_name
        //         file_name level 2
        //         ....
        //         dir_name
        //            ......    level 3
        if (!path.isAbsolute()) {
            path = path.toAbsolutePath().normalize();
        }
        if (Files.isDirectory(path)) {
            System.out.println(path);
            try {
                Files.list(path).forEach(p -> displayRecursion(p, 1, depth));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void displayRecursion(Path path, int level, int depth) {

        if (level > depth && depth != 0) return;
        System.out.print(" ".repeat(level * SPACES_PER_LEVEL));
        System.out.println(path.getFileName());
        if (Files.isDirectory(path)) {
            try {
                Files.list(path).forEach(p1 -> displayRecursion(p1, level + 1, depth));
            } catch (IOException e) {
            }
        }
    }
}

