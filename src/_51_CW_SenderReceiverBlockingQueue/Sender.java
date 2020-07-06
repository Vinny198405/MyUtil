package _51_CW_SenderReceiverBlockingQueue;

import _51_CW_MessageBoxBlockingQueue.MessageBoxBlockingQueue;

public class Sender extends Thread {
    private MessageBoxBlockingQueue messageBoxEven;
    private MessageBoxBlockingQueue messageBoxOdd;

    private int nMessage;

    public Sender(MessageBoxBlockingQueue messageBox, int nMessage) {
        this.messageBoxEven = messageBox;
        this.messageBoxOdd = messageBox;
        this.nMessage = nMessage;
    }

    public Sender(MessageBoxBlockingQueue messageBoxEven, MessageBoxBlockingQueue messageBoxOdd, int nMessage) {
        this.messageBoxEven = messageBoxEven;
        this.messageBoxOdd = messageBoxOdd;
        this.nMessage = nMessage;
    }

    @Override
    public void run() {
        for (int i = 1; i <= nMessage; i++) {
            try {
                if (i % 2 == 0) messageBoxEven.put("message" + i);
                else messageBoxOdd.put("message" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
