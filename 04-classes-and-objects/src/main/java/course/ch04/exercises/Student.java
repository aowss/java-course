package course.ch04.exercises;

import java.util.List;

/**
 * Exercise 2 (Practice): A student with a name and a list of grades.
 *
 * <p>Implement the methods to track and analyze a student's grades.
 */
public class Student {

    // TODO: declare private fields 'name' (String) and 'grades' (List<Double>)

    /**
     * Creates a student with the given name and an empty grade list.
     *
     * @param name the student's name (must not be {@code null} or empty)
     * @throws IllegalArgumentException if name is {@code null} or empty
     */
    public Student(String name) {
        // TODO: validate and initialize fields
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the student's name.
     *
     * @return the name
     */
    public String getName() {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Adds a grade to the student's record.
     *
     * @param grade the grade to add (must be between 0 and 100 inclusive)
     * @throws IllegalArgumentException if the grade is out of range
     */
    public void addGrade(double grade) {
        // TODO: validate and add the grade
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the average of all grades.
     *
     * @return the arithmetic mean
     * @throws IllegalStateException if no grades have been added
     */
    public double getAverage() {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the highest grade.
     *
     * @return the maximum grade
     * @throws IllegalStateException if no grades have been added
     */
    public double getHighest() {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns an unmodifiable view of the grades.
     *
     * @return the list of grades
     */
    public List<Double> getGrades() {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
