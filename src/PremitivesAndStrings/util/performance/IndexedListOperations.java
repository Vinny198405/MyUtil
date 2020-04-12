package PremitivesAndStrings.util.performance;

import IndexedListInterfece.IndexedList;

public class IndexedListOperations extends PerformanceTests {
    private int probGet;
    private IndexedList<Integer> list;
    private int nNumber;

    public IndexedListOperations(String testName, Integer nRuns,
                                 IndexedList<Integer> list, int nNumber) {
        super(testName, nRuns);
        this.list = list;
        this.nNumber = nNumber;
        addList();
    }

    private void addList() {
        for (int i = 0; i < nNumber; i++) {
            list.add(100);
        }
    }

    @Override
    protected void runTest() {
        if (probGet > getRandomIndex(100)) {
            runGetAtIRandomIndex();
        } else {
            runRemoveAddFirst();
            runRemoveAddFirst();
        }
    }

    private void runRemoveAddFirst() {
        list.remove(0);
        list.add(0, 100);
    }

    private void runGetAtIRandomIndex() {
        list.get(getRandomIndex(nNumber));
    }

    private int getRandomIndex(int num) {
        return (int) (num * Math.random());
    }

    public void setProbGet(int probGet) {
        this.probGet = probGet;
    }
}

