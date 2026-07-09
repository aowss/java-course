package course.ch05.exercises;

import java.util.Objects;

public class Manager extends Employee {

    private String department;

    public Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        var manager = (Manager) o;
        return Objects.equals(department, manager.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department);
    }

    @Override
    public String toString() {
        return "Manager{name='" + getName() + "', salary=" + getSalary()
                + ", department='" + department + "'}";
    }
}
