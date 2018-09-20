package threading;

public class NaturalNumbers extends Thread {
    volatile int counter = 0;
    int max;
    String name;

    public NaturalNumbers(String name, int max) {
        this.max = max;
        this.name=name;
        super.setName(name);
    }

    public void run() {
       synchronized (this) {
           if (counter<max) {
               System.out.println(name + ":" + (counter++));
               try {
                   wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           notify();
       }
    }

    public static void main(String[] args) {
        int noThreads=3;
        int maxNatural = 10;

        NaturalNumbers[] naturalNumbers = new NaturalNumbers[noThreads];

        for (int i=0;i<noThreads;i++) {
            naturalNumbers[i] = new NaturalNumbers("Thread"+(i+1), maxNatural);
            naturalNumbers[i].start();
        }
    }
}
