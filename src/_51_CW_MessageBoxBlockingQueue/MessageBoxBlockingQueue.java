package _51_CW_MessageBoxBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageBoxBlockingQueue {

    private BlockingQueue<String> blockingQueue;

    public MessageBoxBlockingQueue() {
        blockingQueue = new ArrayBlockingQueue<>(1);
    }

    public void put(String message) throws InterruptedException {
        while (!blockingQueue.isEmpty()){
        }
        blockingQueue.put(message);
    }

    public String take() throws InterruptedException {
        return blockingQueue.take();
    }

    public String getMessage() throws InterruptedException {
        return blockingQueue.poll();
    }
}
