package _51_CW_SenderReceiverCondition;


import _51_CW_MessageBoxCondition.MessageBox;

public class Sender extends Thread {
    private MessageBox messageBoxEven;
    private MessageBox messageBoxOdd;

    private int nMessage;

    public Sender(MessageBox messageBox, int nMessage) {
        this.messageBoxEven = messageBox;
        this.messageBoxOdd = messageBox;
        this.nMessage = nMessage;
    }

    public Sender(MessageBox messageBoxEven, MessageBox messageBoxOdd, int nMessage) {
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
