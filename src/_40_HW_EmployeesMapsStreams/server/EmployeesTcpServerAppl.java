package _40_HW_EmployeesMapsStreams.server;

import _40_HW_EmployeesMapsStreams.api.EmployeesService;
import _40_HW_EmployeesMapsStreams.service.EmployeesServiceMapsImpl;
import _38_CW_TcpJava.net.server.ProtocolJava;
import _38_CW_TcpJava.net.server.ServerJava;
import _40_HW_EmployeesMapsStreams.net.EmployeesTcpProtocol;

public class EmployeesTcpServerAppl {
    private static final int PORT = 4000;

    public static void main(String[] args) {
        EmployeesService employees = new EmployeesServiceMapsImpl();
        ProtocolJava protocol = new EmployeesTcpProtocol(employees);
        ServerJava server = new ServerJava(protocol , PORT);
        server.run();

    }
}
