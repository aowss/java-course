package course.ch05.exercises;

/**
 * Exercise 2 (Practice): A manager with a department.
 *
 * <p>Extends {@link Employee} with a {@code department} field.
 * Override {@link #equals(Object)}, {@link #hashCode()}, and
 * {@link #toString()} to include the department.
 */
public class Manager extends Employee {

    private String department;

    public Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    /**
     * Two managers are equal if their employee fields and department match.
     */
    @Override
    public boolean equals(Object o) {
        // TODO: call super.equals, then compare department
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int hashCode() {
        // TODO: combine super.hashCode() with department
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns {@code "Manager{name='...', salary=..., department='...'}"}.
     */
    @Override
    public String toString() {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
