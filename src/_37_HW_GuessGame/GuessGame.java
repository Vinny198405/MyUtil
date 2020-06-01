package _37_HW_GuessGame;

import java.io.IOException;

public interface GuessGame {
    String startGame() throws IOException;
    String prompt() throws IOException;
    String move(String userInput) throws IOException;
    Boolean isFinished() throws IOException;
}
