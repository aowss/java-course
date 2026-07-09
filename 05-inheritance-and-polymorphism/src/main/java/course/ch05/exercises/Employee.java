package course.ch05.exercises;

/**
 * Exercise 2 (Practice): Base class for an employee hierarchy.
 *
 * <p>Implement {@link #equals(Object)}, {@link #hashCode()}, and
 * {@link #toString()} following the contract defined in {@link Object}.
 * Two employees are equal if they have the same name, salary, and concrete type.
 */
public class Employee {

    private String name;
    private double salary;

    /**
     * Creates an employee with the given name and salary.
     *
     * @param name   the employee's name
     * @param salary the annual salary
     */
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    /**
     * Two employees are equal if they have the same runtime class,
     * same name, and same salary.
     */
    @Override
    public boolean equals(Object o) {
        // TODO: implement equals using getClass(), name, and salary
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int hashCode() {
        // TODO: implement hashCode consistent with equals
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns {@code "Employee{name='...', salary=...}"}.
     */
    @Override
    public String toString() {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
