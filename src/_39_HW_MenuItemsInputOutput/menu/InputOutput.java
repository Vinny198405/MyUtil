package _39_HW_MenuItemsInputOutput.menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Set;
import java.util.function.Function;

public interface InputOutput {
    String inputString(String prompt);

    void display(Object obj);

    default <T> T inputObject(String prompt, String errorMessage, Function<String, T> mapper) {
        T res;
        while (true) {
            String str = inputString(prompt);
            try {
                res = mapper.apply(str);
                if (res != null) {
                    break;
                }
            } catch (Exception e) {

            }
            displayLine(errorMessage);
        }
        return res;
    }

    default Integer inputInteger(String prompt, Integer min, Integer max) {
        return inputObject(prompt, String.format("No number in the range [%d-%d]", min, max),
                s -> {
                    Integer res = Integer.parseInt(s);
                    return res >= min && res <= max ? res : null;
                });
    }

    default Integer inputInteger(String prompt) {
        return inputObject(prompt, "It is not a number", Integer::parseInt);
    }

    default String inputEmail(String prompt) {
        return inputObject(prompt, "Wrong email format",
                s -> s.matches(email()) ? s + " is correct email" : null);
    }

    default String inputPhone(String prompt) {
        return inputObject(prompt, "Wrong mobile phone format",
                s -> s.matches(phone()) ? s + " is correct phone" : null);
    }

    default String inputIpV4(String prompt) {
        return inputObject(prompt, "Wrong IPv4 format",
                s -> s.matches(ipV4()) ? s + " is correct IpV4" : null);
    }

    default LocalDate inputDate(String prompt) {
        return inputObject(prompt, "Wrong Date, it should be in ISO format  YYYY-MM-DD",
                s -> isValidDate(s) ? LocalDate.parse(s) : null);
    }

    default String inputOptions(String prompt, Set<String> options) {
        return inputObject(prompt, "Wrong option", s -> options.contains(s) ? s : null);
    }

    default void displayLine(Object obj) {
        display(obj.toString() + "\n");
    }

    private String email() {
        return "[^\\p{Space},@]*@[\\p{Alpha}]+(-?[\\p{Alpha}])*(\\.[\\p{Alpha}](-?[\\p{Alpha}])*[\\p{Alpha}]?){1,3}";
    }

    private String phone() {
        return "(\\+972-?|0)5[0|2-8](-?\\d){7}";
    }

    private String ipV4() {
        return String.format("((%s)\\.){3}(%s)", numberLess256(), numberLess256());
    }

    private String numberLess256() {
        return "\\d|[01]\\d{2}|\\d{2}|2[0-4]\\d|25[0-5]";
    }

    private boolean isValidDate(String str) {
        if (str == null || !str.matches("\\d{4}-[01]\\d-[0-3]\\d"))
            return false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
