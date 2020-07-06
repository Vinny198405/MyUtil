package _51_CW_MessageBoxCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageBox {
    private String message;
    private Lock mutex;
    private Condition consumerWaitCondition;
    private Condition producerWaitCondition;

    public MessageBox() {
        mutex = new ReentrantLock();
        consumerWaitCondition = mutex.newCondition();
        producerWaitCondition = mutex.newCondition();
    }

    public void put(String message) throws InterruptedException {
        try {
            mutex.lock();
            while (this.message != null) {
                producerWaitCondition.await();
            }
            this.message = message;
            consumerWaitCondition.signal();
        } finally {
            mutex.unlock();
        }
    }

    public String take() throws InterruptedException {
        try {
            mutex.lock();
            while (message == null) {
                consumerWaitCondition.await();
            }
            String res = message;
            message = null;
            producerWaitCondition.signal();
            return res;
        } finally {
            mutex.unlock();
        }
    }

    public String getMessage() {
        try {
            mutex.lock();
            String res = message;
            message = null;
            return res;
        } finally {
            mutex.unlock();
        }
    }
}
