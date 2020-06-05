package _38_CW_TcpJava.net.server;

import _38_CW_TcpJava.net.RequestJava;
import _38_CW_TcpJava.net.ResponseJava;

import java.io.*;
import java.net.*;

public class ServerClientJava implements Runnable {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ProtocolJava protocol;
    private Socket socket;

    public ServerClientJava(Socket socket, ProtocolJava protocol) {
        this.protocol = protocol;
        this.socket = socket;
        try {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                RequestJava request = (RequestJava) input.readObject();
                ResponseJava response = protocol.getResponse(request);
                output.writeObject(response);
            }
        } catch (EOFException e){
            System.out.println("client closed connection");
        }
        catch (Exception e){
            System.out.println("illegal closing exception");
        }
    }
}
