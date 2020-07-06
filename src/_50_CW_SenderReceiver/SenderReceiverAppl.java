package _50_CW_SenderReceiver;

import _49_CW_MessageBox.MessageBox;

public class SenderReceiverAppl {
    private static final int N_MESSAGES = 20;
    private static final int N_RECEIVERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBox messageBox = new MessageBox();
        Sender sender = new Sender(messageBox, N_MESSAGES);
        Receiver[] receivers = new Receiver[N_RECEIVERS];
        for (int i = 0; i < N_RECEIVERS; i++) {
            receivers[i] = new Receiver(messageBox);
            //     receivers[i].setDaemon(true);
            receivers[i].start();
        }
        sender.start();
        sender.join();

        for (Receiver receiver : receivers) {
            receiver.setFinished();
            receiver.interrupt();
        }
    }
}
