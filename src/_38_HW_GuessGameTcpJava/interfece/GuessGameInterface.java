package _38_HW_GuessGameTcpJava.interfece;

import java.io.IOException;

public interface GuessGameInterface {
    String startGame() throws IOException;
    String prompt() throws IOException;
    String move(String userInput) throws IOException;
    Boolean isFinished() throws IOException;
}
