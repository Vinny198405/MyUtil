package _38_CW_TcpJava.net;

import java.io.*;
import java.net.*;

public class TcpClientJava implements AutoCloseable {
    protected Socket socket;
    protected ObjectOutputStream outputNet;
    protected ObjectInputStream inputNet;
    public TcpClientJava(String hostname, int port)  {
        try {
            socket = new Socket(hostname, port);
            outputNet = new ObjectOutputStream(socket.getOutputStream());
            inputNet = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    protected <T>T sendRequest(String requestType,
                               Serializable requestData) {
        RequestJava request = new RequestJava(requestType, requestData);

        ResponseJava response = null;
        try {
            outputNet.writeObject(request);
            response = (ResponseJava) inputNet.readObject();

            if(response.code != TcpResponseCode.OK) {
                throw new RuntimeException(response.responseData.toString());
            }
            return (T)response.responseData;
        } catch (Exception e) {
            throw new RuntimeException(response.responseData.toString());
        }
    }


    @Override
    public void close() throws IOException {
        socket.close();

    }

}
