package PremitivesAndStrings;

import PremitivesAndStrings.util.JoinStringsImplBuilder;
import PremitivesAndStrings.util.JoinStringsImplString;
import PremitivesAndStrings.util.performance.JoinStringsPerformance;

public class JoinStringsPerformanceTestAppl {
    public static void main(String[] args) {
        int runsAmount = 1000;
        int nStrings = 1000;

        JoinStringsPerformance stringTest = new JoinStringsPerformance("String Test", runsAmount, nStrings, new JoinStringsImplString());
        JoinStringsPerformance builderTest = new JoinStringsPerformance("Builder Test", runsAmount, nStrings, new JoinStringsImplBuilder());

        stringTest.run();
        builderTest.run();
    }
}
