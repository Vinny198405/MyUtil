package FunctionalInterfacesIntroduction;

import java.util.Arrays;

public class FunctionalTestAppl {
    public static void main(String[] args) {
        String strings[] = {"p", "lmn", "abc", "l", "lmno"};
        //sorting using lambda expression sorting by string by length
        Arrays.sort(strings, (a, b) -> a.length() - b.length());
        System.out.println(Arrays.toString(strings));
        //sorting using lambda closure sorting by string by length and naturals
        Arrays.sort(strings, (a, b) -> {
            int res = a.length() - b.length();
            return res == 0 ? a.compareTo(b) : res;
        });
        System.out.println(Arrays.toString(strings));
        Arrays.sort(strings, FunctionalTestAppl::compareLengthNatural);
        System.out.println(Arrays.toString(strings));
    }

    private static Integer compareLengthNatural(String s1, String s2) {
        int res = Integer.compare(s1.length(), s2.length());
        return res == 0 ? s1.compareTo(s2) : res;
    }
}
