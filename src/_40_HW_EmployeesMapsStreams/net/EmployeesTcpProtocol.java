package _40_HW_EmployeesMapsStreams.net;

import EmployeesMaps.*;
import _38_CW_TcpJava.net.*;
import _38_CW_TcpJava.net.server.ProtocolJava;
import static _40_HW_EmployeesMapsStreams.api.EmployeesApi.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.function.Function;

public class EmployeesTcpProtocol implements ProtocolJava {
    private EmployeesService employees;
    private HashMap<String, Function<Serializable, ResponseJava>> mapFunctions;

    public EmployeesTcpProtocol(EmployeesService employees) {
        this.employees = employees;
        fillMapFunctions();
    }

    private void fillMapFunctions() {
        mapFunctions = new HashMap<>();
        mapFunctions.put(ADDEMPL, this::addEmployee);
    }
    @Override
    public ResponseJava getResponse(RequestJava request) {
        Function<Serializable, ResponseJava> fn =
                mapFunctions.getOrDefault(request.requestType, this::wrongRequest);

        return fn.apply(request.requestData);
    }

    private ResponseJava addEmployee(Serializable requestData) {
        try {
            EmployeesReturnCodes res = employees.addEmployee((Employee) requestData);
            return new ResponseJava(TcpResponseCode.OK, res);
        } catch (Exception e) {
            return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
        }
    }

    private ResponseJava wrongRequest(Serializable requestData) {
        return new ResponseJava(TcpResponseCode.WRONG_REQUEST, "Type of request not found");
    }
}
