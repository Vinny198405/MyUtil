package _51_CW_SenderReceiverBlockingQueue;

import _51_CW_MessageBoxBlockingQueue.MessageBoxBlockingQueue;

public class Receiver extends Thread {
    private MessageBoxBlockingQueue messageBox;
    private volatile boolean isFinished = false;

    Receiver(MessageBoxBlockingQueue messageBox) {
        this.messageBox = messageBox;
    }

    Receiver() {
    }

    @Override
    public void run() {
        while (!isFinished) {
            try {
                String message = messageBox.take();
                print(message);
            } catch (InterruptedException e) {
            }
        }
        String message = null;
        try {
            message = messageBox.getMessage();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (message != null) print(message);
    }

    private void print(String message) {
        System.out.printf("Thread with ID: %d - %s\n", getId(), message);
    }

    void setFinished() {
        isFinished = true;
    }

    public void setMessageBox(MessageBoxBlockingQueue messageBox) {
        this.messageBox = messageBox;
    }
}
