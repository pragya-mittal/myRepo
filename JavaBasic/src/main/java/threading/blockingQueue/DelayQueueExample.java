package threading.blockingQueue;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// https://javapapers.com/java/java-delayqueue/

public class DelayQueueExample {
    public static void main(String[] args) {
        final BlockingQueue<DelayElement> queue = new DelayQueue<DelayElement>();

        DelayQueueProducer queueProducer = new DelayQueueProducer(queue);
        new Thread(queueProducer).start();

        DelayQueueConsumer queueConsumer1 = new DelayQueueConsumer(queue);
        new Thread(queueConsumer1).start();

        DelayQueueConsumer queueConsumer2 = new DelayQueueConsumer(queue);
        new Thread(queueConsumer2).start();

    }
}

class DelayQueueConsumer implements Runnable {

    protected BlockingQueue<DelayElement> blockingQueue;

    public DelayQueueConsumer(BlockingQueue<DelayElement> queue) {
        this.blockingQueue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                DelayElement delayElement = blockingQueue.take();
                System.out.println(Thread.currentThread().getName()
                        + " take(): " + delayElement);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class DelayQueueProducer implements Runnable {

    protected BlockingQueue<DelayElement> blockingQueue;
    final Random random = new Random();

    public DelayQueueProducer(BlockingQueue<DelayElement> queue) {
        this.blockingQueue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int delay = random.nextInt(10000);
                DelayElement delayElement = new DelayElement(UUID.randomUUID()
                        .toString(), delay);
                System.out.println("Put: "+ delayElement);
                blockingQueue.put(delayElement);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class DelayElement implements Delayed {
    private String element;
    private long expiryTime;

    public DelayElement(String element, long delay) {
        this.element = element;
        this.expiryTime = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit timeUnit) {
        long diff = expiryTime - System.currentTimeMillis();
        return timeUnit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.expiryTime < ((DelayElement) o).expiryTime) {
            return -1;
        }
        if (this.expiryTime > ((DelayElement) o).expiryTime) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return element + ": " + expiryTime;
    }
}