package _48_CW_MultiListProcessing;

import java.util.ArrayList;
import java.util.List;

public class ListProcessorAppl {

    private static final int N_NUMBERS = 1000000;
    private static final int N_PROCESSORS = 100;
    private static final int N_RUNS = 10000;
    private static final int PROB_UPDATE = 0;

    public static void main(String[] args) {
        List<Integer> list = getList();
        ListProcessor[] processors = new ListProcessor[N_PROCESSORS];
        startProcessors(processors, list);
        waitProcessors(processors);
        System.out.println("counter of blocking iterations: " + ListProcessor.getCountBlckIterations());
    }

    private static void waitProcessors(ListProcessor[] processors) {
        for(ListProcessor listProcessor: processors) {
            try {
                listProcessor.join();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
        }
    }

    private static void startProcessors(ListProcessor[] processors, List<Integer> list) {
        for (int i = 0; i < processors.length; i++) {
            processors[i] = new ListProcessor(list, N_RUNS, PROB_UPDATE);
            processors[i].start();
        }
    }

    private static List<Integer> getList() {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < N_NUMBERS; i++) {
            res.add(100);
        }
        return res;
    }
}

