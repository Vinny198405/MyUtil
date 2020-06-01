package _37_CW_TcpServerAppl;

import java.io.*;
import java.net.*;

public class TcpServerAppl {
    private static final int PORT = 4000;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is listening on port " + PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            runClient(socket);
        }
    }

    private static void runClient(Socket socket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintStream writer = new PrintStream(socket.getOutputStream())) {
            while (true) {
                String request = reader.readLine();
                if (request == null) {
                    break;
                }
                String response = getResponse(request);
                writer.println(response);
            }
        } catch (IOException e) {
            System.out.println("illegal way of closing connection");
            return;
        }
        System.out.println("client closed connection");
    }

    private static String getResponse(String request) {
        String[] headersPayload = request.split("#");
        String headers = headersPayload[0];
        String payload = headersPayload[1];
        if (headersPayload.length != 2) return "Unknown Request";
        switch (headers) {
            case "reverse":
                return getReverse(payload);
            case "length":
                return getLength(payload);
            default:
                return "Unknown Request";
        }
    }

    private static String getLength(String payload) {
        return Integer.toString(payload.length());
    }

    private static String getReverse(String payload) {
        StringBuilder builder = new StringBuilder(payload);
        return builder.reverse().toString();
    }
}
