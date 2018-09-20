package threading.blockingQueue;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


// https://javapapers.com/java/java-linkedblockingqueue/

public class LinkedBlockingQueueExample {
    public static void main(String[] args) {
        final BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>();

        LinkedBlockingQueueProducer queueProducer = new LinkedBlockingQueueProducer(
                linkedBlockingQueue);
        new Thread(queueProducer).start();

        LinkedBlockingQueueConsumer queueConsumer1 = new LinkedBlockingQueueConsumer(
                linkedBlockingQueue);
        new Thread(queueConsumer1).start();

        LinkedBlockingQueueConsumer queueConsumer2 = new LinkedBlockingQueueConsumer(
                linkedBlockingQueue);
        new Thread(queueConsumer2).start();

    }
}

class LinkedBlockingQueueConsumer implements Runnable {

    protected BlockingQueue<String> blockingQueue;

    public LinkedBlockingQueueConsumer(BlockingQueue<String> queue) {
        this.blockingQueue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = blockingQueue.take();
                System.out.println(Thread.currentThread().getName()
                        + " take(): " + data);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class LinkedBlockingQueueProducer implements Runnable {

    protected BlockingQueue<String> blockingQueue;
    final Random random = new Random();

    public LinkedBlockingQueueProducer(BlockingQueue<String> queue) {
        this.blockingQueue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = UUID.randomUUID().toString();
                System.out.println("Put: " + data);
                blockingQueue.put(data);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
