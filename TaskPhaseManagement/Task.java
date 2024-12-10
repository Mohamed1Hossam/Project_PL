package com.mycompany.mavenproject2;

public class Task {
    private String taskId;
    private String taskName;
    private String phase;

    public Task(String taskId, String taskName, String phase) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.phase = phase;
    }


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatus() {
        return phase;
    }

    public void setStatus(String status) {
        this.phase = status;
    }

    
    @Override
    public String toString() {
        return taskId + ": " + taskName + " - " + phase;
    }
}
