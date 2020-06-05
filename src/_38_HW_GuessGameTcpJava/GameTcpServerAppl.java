package _38_HW_GuessGameTcpJava;

import _38_CW_TcpJava.net.server.ProtocolJava;
import _38_CW_TcpJava.net.server.ServerJava;
import _38_HW_GuessGameTcpJava.interfece.GuessGameInterface;
import _38_HW_GuessGameTcpJava.protocol.GuessGameProtocol;
import _38_HW_GuessGameTcpJava.server.GameTcpServer;

public class GameTcpServerAppl {
    private static final int PORT = 4000;

    public static void main(String[] args) {
        GuessGameInterface game = new GameTcpServer();
        ProtocolJava protocol = new GuessGameProtocol(game);
        ServerJava server = new ServerJava(protocol , PORT);
        server.run();

    }
}
