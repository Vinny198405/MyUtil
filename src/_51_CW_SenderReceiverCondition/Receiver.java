package _51_CW_SenderReceiverCondition;


import _51_CW_MessageBoxCondition.MessageBox;

public class Receiver extends Thread {
    private MessageBox messageBox;
    private volatile boolean isFinished = false;

    Receiver(MessageBox messageBox) {
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
        String message = messageBox.getMessage();
        if (message != null) print(message);
    }

    private void print(String message) {
        System.out.printf("Thread with ID: %d - %s\n", getId(), message);
    }

    void setFinished() {
        isFinished = true;
    }

    public void setMessageBox(MessageBox messageBox) {
        this.messageBox = messageBox;
    }
}
