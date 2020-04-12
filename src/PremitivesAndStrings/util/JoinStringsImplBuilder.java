package PremitivesAndStrings.util;

import PremitivesAndStrings.util.performance.JoinStringsInterface;

public class JoinStringsImplBuilder implements JoinStringsInterface {
    @Override
    public String join(String[] strings, String delimiter) {
        StringBuilder res = new StringBuilder(strings[0]);
        int size = strings.length;

        for (int i = 1; i < size; i++) {
            res.append(delimiter).append(strings[i]);
        }

        return res.toString();
    }
}
