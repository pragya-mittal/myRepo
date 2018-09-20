package threading;

public class NumberSeq {
    public static class Number {
        int num = 0;

        public void incrNum() {
            num++;
        }

        public int getNum() {
            return num;
        }
    }

    static Number number = new Number();

    public static class NumberThread extends Thread{
        int threadId;
        int maxThreads;
        String threadName;


        public NumberThread(int threadNum, int maxThreads) {
            this.threadId = threadNum;
            this.maxThreads = maxThreads;
            this.threadName = "Thread-"+ (threadNum + 1);
        }

        @Override
        public void run() {
            while (true) {
                if (number.getNum() % maxThreads ==  threadId) {
                    synchronized (number) {
                        number.incrNum();
                        System.out.println(threadName + " : " + number.getNum());
                    }
                }
            }
        }
    }

    public static void main(String args[]) throws Exception {
        NumberThread[] numberThreads = new NumberThread[3];
        for(int i = 0;  i < 3; i++) {
            numberThreads[i] = new NumberThread(i, 3);
            numberThreads[i].start();
        }
    }
}

//public class NumberSeq {
//    static int currentNum = 0;
//
//    public static class NumberThread extends Thread{
//        int threadId;
//        int maxThreads;
//        String threadName;
//
//        public NumberThread(int threadNum, int maxThreads) {
//            this.threadId = threadNum;
//            this.maxThreads = maxThreads;
//            this.threadName = "Thread-"+ (threadNum + 1);
//        }
//
//        @Override
//        public void run() {
//            while (true) {
//                if (currentNum % maxThreads ==  threadId) {
//                    System.out.println(threadName + " : " + ++currentNum);
//                    try {
//                        Thread.sleep(10);
//                    } catch (Exception e) {
//                    }
//                }
//            }
//        }
//    }
//
//    public static void main(String args[]) throws Exception {
//        NumberThread[] numberThreads = new NumberThread[5];
//
//        for(int i = 0;  i < 5; i++) {
//            numberThreads[i] = new NumberThread(i, 5);
//            numberThreads[i].start();
//        }
//
//    }
//}