package design.prateek.scheduler;

public class OS {
    Scheduler scheduler;
    int numThreads;

    public OS(int numThreads, Scheduler scheduler) {
        this.scheduler = scheduler;
        this.numThreads = numThreads;
    }

    void runJob(Job job) {
        scheduler.runJob(job);
    }

    void killJob(Job job) {
        scheduler.killJob(job);
    }

    public static void main(String[] args) {
        //  OS os = new OS(4, );
    }
}