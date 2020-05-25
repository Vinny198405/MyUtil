package TextOrientedStreams;

import java.nio.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.*;
public class InputTextStreams {

    public static void main(String[] args) throws IOException {
//		try(BufferedReader reader = Files.newBufferedReader
//				(Paths.get("test.txt"), Charset.forName("utf-8"));){
//			while(true) {
//				String line = reader.readLine();
//				if(line == null) {
//					break;
//				}
//				System.out.println(line);
//			}
//		}
        //Entering password
//		System.out.println("Enter password");
//		String password = getPassword();
//		System.out.println("Entered password (Make sure nobody sees it) " + password);
        //Finding sum of two numbers
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Enter two numbers separated by space or exit");
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            String strNumbers[] = line.split(" ");
            System.out.printf("sum of %s and %s equals %d\n", strNumbers[0], strNumbers[1],
                    Integer.parseInt(strNumbers[0]) + Integer.parseInt(strNumbers[1]));

        }


    }

    private static String getPassword() {
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();

        return console == null ? scanner.nextLine() : new String(console.readPassword());
    }

}
