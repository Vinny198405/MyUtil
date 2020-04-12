package PremitivesAndStrings.util.performance;

public class JoinStringsPerformance extends PerformanceTests {
    private String[] strings;
    private JoinStringsInterface joinStrings;

    public JoinStringsPerformance(String testName, int nRuns, int nStrings, JoinStringsInterface joinStrings) {
        super(testName, nRuns);
        this.joinStrings = joinStrings;
        addArrayStrings(nStrings);
    }

    private void addArrayStrings(int nStrings) {
        strings = new String[nStrings];
        for (int i = 0; i < nStrings; i++) {
            strings[i] = "Hello";
        }
    }

    @Override
    protected void runTest() {
        joinStrings.join(strings, " ");
    }
}

