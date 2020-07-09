package _52_CW_TcpJavaThreadsPool.server;

import _52_CW_TcpJavaThreadsPool.net.RequestJava;
import _52_CW_TcpJavaThreadsPool.net.ResponseJava;

public interface ProtocolJava {
    ResponseJava getResponse(RequestJava request);
}
