package design.prateek.scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SJF  {

    ArrayList<Job> list;
    int threads;

    public SJF(ArrayList<Job> list, int threads) {
        this.list = list;
        this.threads = threads;
    }

    void run() {
        Collections.sort(list, new Comparator<Job>() {
            public int compare(Job o1, Job o2) {
                if (o1.duration > o2.duration) {
                    return 1;
                } else if (o1.duration < o2.duration) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        int time = 0;
        int thread_end[] = new int[threads];

        int i =0;
        int min = 0;
        while (i<list.size()) {
            for (int j=0; j<threads; j++) {
                if (min > thread_end[i]) {
                    min = thread_end[i];
                }
            }
            time += min;

            if (i!=0) {
                time += list.get(i-1).duration;
                System.out.println("finished Job " + list.get(i-1).jobId + " at " + time);
            }
            System.out.println("Running job " + list.get(i).jobId + " at " + time);
        }
        System.out.println("finished Job " + list.get(list.size() - 1).jobId + " at " + time);

    }
    public static void main(String[] args) {
        ArrayList<Job> jobList = new ArrayList<Job>();
        jobList.add(new Job.Builder().setDuration(4).setjobId("one").build());
        jobList.add(new Job.Builder().setDuration(3).setjobId("two").build());
        jobList.add(new Job.Builder().setDuration(5).setjobId("three").build());
        jobList.add(new Job.Builder().setDuration(2).setjobId("four").build());
        jobList.add(new Job.Builder().setDuration(3).setjobId("five").build());
        jobList.add(new Job.Builder().setDuration(2).setjobId("six").build());
        SJF sjf = new SJF(jobList, 1);
        sjf.run();

    }
}