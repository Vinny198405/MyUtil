package _38_CW_TcpJava.net;

import java.io.Serializable;

public class RequestJava implements Serializable{
    private static final long serialVersionUID = 1L;
    public String requestType;
    public Serializable requestData;

    public RequestJava(String requestType, Serializable requestData) {
        this.requestType = requestType;
        this.requestData = requestData;
    }
}
