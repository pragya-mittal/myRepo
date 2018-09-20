package threading.blockingQueue;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

// https://javapapers.com/java/java-linkedblockingdeque/
public class LinkedBlockingDequeExample {
    public static void main(String[] args) {
        final BlockingDeque<String> priorityBlockingQueue = new LinkedBlockingDeque<String>();

        LinkedBlockingDequeProducer queueProducer = new LinkedBlockingDequeProducer(
                priorityBlockingQueue);
        new Thread(queueProducer).start();

        LinkedBlockingDequeConsumer queueConsumer1 = new LinkedBlockingDequeConsumer(
                priorityBlockingQueue);
        new Thread(queueConsumer1).start();

        LinkedBlockingDequeConsumer queueConsumer2 = new LinkedBlockingDequeConsumer(
                priorityBlockingQueue);
        new Thread(queueConsumer2).start();
    }
}

class LinkedBlockingDequeConsumer implements Runnable {

    protected BlockingDeque<String> blockingDeque;

    public LinkedBlockingDequeConsumer(BlockingDeque<String> queue) {
        this.blockingDeque = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = blockingDeque.takeFirst(); // *
                System.out.println(Thread.currentThread().getName()
                        + " take(): " + data);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class LinkedBlockingDequeProducer implements Runnable {
    protected BlockingDeque<String> blockingDeque;
    final Random random = new Random();

    public LinkedBlockingDequeProducer(BlockingDeque<String> queue) {
        this.blockingDeque = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = UUID.randomUUID().toString();
                System.out.println("Put: " + data);
                blockingDeque.addFirst(data);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}