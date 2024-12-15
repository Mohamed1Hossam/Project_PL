package tms;

import java.util.*;
class TaskCalendar extends Task {
    private int calendarId;
    private List<Task> taskList;
    private int employeeId;

    public TaskCalendar(int id, String userType, int calendarId, int employeeId) {
        this.calendarId = calendarId;
        this.employeeId = employeeId;
        this.taskList = new ArrayList<>();
    }

    public void showTasksByDate(Date date) {
        writeLog("Showing tasks for date: " + date);
        for (Task task : taskList) {
            if (super.getStartDate().equals(date) || getEndDate().equals(date)) {
                System.out.println(task);
            }
        }
    }

    public void filterTasksByPhase(String phase) {
        writeLog("Filtering tasks by phase: " + phase);
        for (Task task : taskList) {
            if (getPhase().equals(phase)) {
                System.out.println(task);
            }
        }
    }

    public void viewEmployeeTasks() {
        writeLog("Viewing tasks for EmployeeID: " + employeeId);
        for (Task task : taskList) {
            if (getAssignedEmployee().equals(String.valueOf(employeeId))) {
                System.out.println(task);
            }
        }
    }

    @Override
    public String toString() {
        return "CalendarID: " + calendarId + ", EmployeeID: " + employeeId;
    }
}


