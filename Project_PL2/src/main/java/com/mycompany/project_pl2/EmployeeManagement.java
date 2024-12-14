package com.mycompany.project_pl2;

public class EmployeeManagement {
    private String id;
    private String name;

    // Constructor to initialize Employee
    public EmployeeManagement(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    @Override
    public String toString() {
        return id + ": " + name;
    }
}
