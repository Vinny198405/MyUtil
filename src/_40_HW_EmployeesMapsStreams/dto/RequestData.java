package _40_HW_EmployeesMapsStreams.dto;

import java.io.Serializable;

public class RequestData implements Serializable {
    public long id;
    public int num;
    public int num2;
    public String str;


    public RequestData(long id, int num) {
        this.id = id;
        this.num = num;
    }

    public RequestData(int num1, int num2) {
        this.num = num1;
        this.num2 = num2;
    }

    public RequestData(long id, String str) {
        this.id = id;
        this.str = str;
    }
}
