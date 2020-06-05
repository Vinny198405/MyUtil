package _38_HW_GuessGameTcpJava.client;

import static _38_HW_GuessGameTcpJava.api.GuessGameApi.*;

import _38_CW_TcpJava.net.TcpClientJava;
import _38_HW_GuessGameTcpJava.interfece.GuessGameInterface;

public class GameTcpClient extends TcpClientJava implements GuessGameInterface {

    public GameTcpClient(String hostname, int port) {
        super(hostname, port);
    }

    @Override
    public String startGame() {
        return sendRequest(START, null);
    }

    @Override
    public String prompt() {
        return sendRequest(PROMPT, null);
    }

    @Override
    public String move(String userInput) {
        return sendRequest(MOVE, userInput);
    }

    @Override
    public Boolean isFinished() {
        return sendRequest(IS_FINISHED, null);
    }

}
