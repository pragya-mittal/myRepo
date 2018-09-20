package threading;

class MultiThreadDemo extends Thread {

    public void run() {
        System.out.println("Executing thread : " + Thread.currentThread().getId());
    }
}

class MultithreadingDemo implements Runnable
{
    public void run()
    {
        try
        {
            // Displaying the thread that is running
            System.out.println ("Thread " +
                    Thread.currentThread().getId() +
                    " is running");

        }
        catch (Exception e)
        {
            // Throwing an exception
            System.out.println ("Exception is caught");
        }
    }
}

public class MultiThread {

    public static void main(String[] args) {

        for (int i=0; i<8; i++) {
            MultiThreadDemo multiThreadDemo = new MultiThreadDemo();
            multiThreadDemo.start();
        }

        for (int i=0; i<8; i++) {
            Thread multiThreadDemo = new Thread(new MultithreadingDemo());
            multiThreadDemo.start();
        }
    }

}
