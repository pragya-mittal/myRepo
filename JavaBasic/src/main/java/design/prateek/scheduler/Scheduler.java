package design.prateek.scheduler;

public interface Scheduler {
    void runJob(Job job);

    void killJob(Job job);
}
