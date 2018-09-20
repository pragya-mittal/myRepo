package threading;

public class NaturalNumbersInSequence extends Thread {
    int maxval;
    static int num = 1;
    int threadno;
    int maxthread;
    Num number;

    public static class Num {
        public  int x =0;

        public void incr() {
            x++;
        }
        public int getX() {
            return x;
        }
    }

    public NaturalNumbersInSequence(Num number, int maxval, int threadno, int maxthread) {
        this.number = number;
      this.maxval = maxval;
      this.threadno = threadno;
      this.maxthread = maxthread;
      super.setName("Thread" + threadno);
    }

    public void run() {
        while (true) {
            if ((number.getX()%maxthread == threadno)) {
                int tmp = number.getX();
                number.incr();
                System.out.println(this.getName() + ":" + tmp);
            }
        }
    }

    public static void main(String[] args) {
        int noThread = 5;
        int max = 10;

        Num num = new NaturalNumbersInSequence.Num();

        NaturalNumbersInSequence[] threads = new NaturalNumbersInSequence[noThread];
        for (int i=0;i<noThread;i++) {
            threads[i] = new NaturalNumbersInSequence(num,1, i, noThread);
            threads[i].start();
        }
    }

}
