package _37_CW_TcpServerAppl;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TcpClientAppl {
    private static final int PORT = 4000;
    private static final String HOST = "localhost";

    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket(HOST, PORT);
        Scanner scanner = new Scanner(System.in);
        try (PrintStream writer = new PrintStream(socket.getOutputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            while (true) {
                System.out.println("enter request in the format <type>#<string> or exit");
                String request = scanner.nextLine();
                if (request.equalsIgnoreCase("exit")) break;
                writer.println(request);
                String response = reader.readLine();
                if (response == null) break;
                System.out.println(response);
            }
        }
    }
}
