package _49_CW_MessageBox;

public class MessageBox {
    private String message;

    public void put(String message) throws InterruptedException {
        synchronized (this) {
            while (this.message != null) {
                this.wait();
            }
            this.message = message;
            this.notify();
        }
    }

    public String take() throws InterruptedException {
        synchronized (this) {
            while (message == null) {
                this.wait();
            }
            String res = message;
            message = null;
            this.notifyAll();
            return res;
        }
    }

    public String getMessage() {
        synchronized (this) {
            String res = message;
            message = null;
            return res;
        }
    }
}
