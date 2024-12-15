package tms;

public class EmployeeManagement extends Employee {
    private int id;
    private String name;
    

    
    // Constructor to initialize Employee
    public EmployeeManagement(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    
    @Override
    public String toString() {
        return id + ": " + name;
    }
}
