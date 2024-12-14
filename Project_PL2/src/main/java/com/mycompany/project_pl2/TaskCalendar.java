package com.mycompany.project_pl2;


import java.util.*;
class TaskCalendar extends TaskManagementSystem {
    private int calendarId;
    private List<Task> taskList;
    private int employeeId;

    public TaskCalendar(int id, String userType, int calendarId, int employeeId) {
        super(id, userType);
        this.calendarId = calendarId;
        this.employeeId = employeeId;
        this.taskList = new ArrayList<>();
    }

    public void showTasksByDate(Date date) {
        writeLog("Showing tasks for date: " + date);
        for (Task task : taskList) {
            if (task.startDate.equals(date) || task.endDate.equals(date)) {
                System.out.println(task);
            }
        }
    }

    public void filterTasksByPhase(String phase) {
        writeLog("Filtering tasks by phase: " + phase);
        for (Task task : taskList) {
            if (task.phase.equals(phase)) {
                System.out.println(task);
            }
        }
    }

    public void viewEmployeeTasks() {
        writeLog("Viewing tasks for EmployeeID: " + employeeId);
        for (Task task : taskList) {
            if (task.assignedEmployee.equals(String.valueOf(employeeId))) {
                System.out.println(task);
            }
        }
    }

    @Override
    public String toString() {
        return "CalendarID: " + calendarId + ", EmployeeID: " + employeeId;
    }
}


