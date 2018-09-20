package design.prateek.scheduler;

public class Job {

    int duration;
    int deadline;
    Priority priority;
    UserLevel userLevel;
    String jobId;

    public int getDuration() {
        return duration;
    }

    public int getDeadline() {
        return deadline;
    }

    public Priority getPriority() {
        return priority;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public Job(Builder builder) {
        duration = builder.duration;
        deadline = builder.deadline;
        priority = builder.priority;
        userLevel = builder.userLevel;
        jobId = builder.jobId;
    }

    static class Builder {
        int duration = 0;
        int deadline = 0;
        Priority priority = Priority.LOW;
        UserLevel userLevel = UserLevel.USER;
        String jobId = "default";

        public Job build() {
            return new Job(this);
        }
        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder setDeadline(int deadline) {
            this.deadline = deadline;
            return this;
        }

        public Builder setPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder setUserLevel(UserLevel userLevel) {
            this.userLevel = userLevel;
            return this;
        }

        public Builder setjobId(String jobId) {
            this.jobId = jobId;
            return this;
        }
    }
}