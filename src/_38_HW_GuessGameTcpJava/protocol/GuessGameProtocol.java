package _38_HW_GuessGameTcpJava.protocol;

import static _38_HW_GuessGameTcpJava.api.GuessGameApi.*;

import _38_CW_TcpJava.net.RequestJava;
import _38_CW_TcpJava.net.ResponseJava;
import _38_CW_TcpJava.net.TcpResponseCode;
import _38_CW_TcpJava.net.server.ProtocolJava;
import _38_HW_GuessGameTcpJava.interfece.GuessGameInterface;

import java.io.Serializable;
import java.util.HashMap;
import java.util.function.Function;

public class GuessGameProtocol implements ProtocolJava {
    private GuessGameInterface game;
    private HashMap<String, Function<Serializable, ResponseJava>> mapFunctions;

    public GuessGameProtocol(GuessGameInterface game) {
        this.game = game;
        fillMapFunctions();
    }

    private void fillMapFunctions() {
        mapFunctions = new HashMap<>();
        mapFunctions.put(START, this::start);
        mapFunctions.put(MOVE, this::move);
        mapFunctions.put(PROMPT, this::prompt);
        mapFunctions.put(IS_FINISHED, this::isFinished);
    }

    @Override
    public ResponseJava getResponse(RequestJava request) {
        Function<Serializable, ResponseJava> fn =
                mapFunctions.getOrDefault(request.requestType, this::wrongRequest);

        return fn.apply(request.requestData);
    }

    private ResponseJava start(Serializable requestData) {
        try {
            String res = game.startGame();
            return new ResponseJava(TcpResponseCode.OK, res);
        } catch (Exception e) {
            return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
        }
    }

    private ResponseJava prompt(Serializable requestData) {
        try {
            String res = game.prompt();
            return new ResponseJava(TcpResponseCode.OK, res);
        } catch (Exception e) {
            return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
        }
    }

    private ResponseJava move(Serializable requestData) {
        try {
            String res = game.move((String) requestData);
            return new ResponseJava(TcpResponseCode.OK, res);
        } catch (Exception e) {
            return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
        }
    }

    private ResponseJava isFinished(Serializable requestData) {
        try {
            boolean res = game.isFinished();
            return new ResponseJava(TcpResponseCode.OK, res);
        } catch (Exception e) {
            return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
        }
    }

    private ResponseJava wrongRequest(Serializable requestData) {
        return new ResponseJava(TcpResponseCode.WRONG_REQUEST, "Type of request not found");
    }
}
