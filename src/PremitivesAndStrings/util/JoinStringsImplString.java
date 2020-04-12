package PremitivesAndStrings.util;

import PremitivesAndStrings.util.performance.JoinStringsInterface;

public class JoinStringsImplString implements JoinStringsInterface {
    @Override
    public String join(String[] strings, String delimiter) {
        String res = strings[0];
        int size = strings.length;

        for (int i = 1; i < size; i++) {
            res += delimiter + strings[i];
        }

        return res;
    }
}

