package _50_CW_SenderReceiver;

import _49_CW_MessageBox.MessageBox;

public class Sender extends Thread {
    private MessageBox messageBox;
    private int nMessage;

    public Sender(MessageBox messageBox, int nMessage) {
        this.messageBox = messageBox;
        this.nMessage = nMessage;
    }

    @Override
    public void run() {
        for (int i = 1; i <= nMessage; i++) {
            try {
                messageBox.put("message" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            try {
//                sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }
    }
}
