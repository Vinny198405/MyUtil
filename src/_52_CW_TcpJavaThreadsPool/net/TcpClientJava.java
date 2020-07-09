package _52_CW_TcpJavaThreadsPool.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class TcpClientJava implements AutoCloseable {
    private Socket socket;
    private ObjectOutputStream outputNet;
    private ObjectInputStream inputNet;
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
            assert response != null;
            throw new RuntimeException(response.responseData.toString());
        }
    }


    @Override
    public void close() throws IOException {
        socket.close();

    }

}
