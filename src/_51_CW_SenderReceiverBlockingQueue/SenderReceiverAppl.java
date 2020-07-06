package _51_CW_SenderReceiverBlockingQueue;


import _51_CW_MessageBoxBlockingQueue.MessageBoxBlockingQueue;

public class SenderReceiverAppl {
    private static final int N_MESSAGES = 30;
    private static final int N_RECEIVERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBoxBlockingQueue messageBoxEven = new MessageBoxBlockingQueue();
        MessageBoxBlockingQueue messageBoxOdd = new MessageBoxBlockingQueue();
        Sender sender = new Sender(messageBoxEven, messageBoxOdd, N_MESSAGES);
        Receiver[] receivers = new Receiver[N_RECEIVERS];
        for (int i = 0; i < N_RECEIVERS; i++) {
            receivers[i] = new Receiver();
            receivers[i].setMessageBox(receivers[i].getId() % 2 == 0 ? messageBoxEven : messageBoxOdd);
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
