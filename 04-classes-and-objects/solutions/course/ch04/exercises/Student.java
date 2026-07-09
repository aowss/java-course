package course.ch04.exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {

    private String name;
    private List<Double> grades;

    public Student(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addGrade(double grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
        grades.add(grade);
    }

    public double getAverage() {
        if (grades.isEmpty()) {
            throw new IllegalStateException("No grades added");
        }
        double sum = 0;
        for (double g : grades) {
            sum += g;
        }
        return sum / grades.size();
    }

    public double getHighest() {
        if (grades.isEmpty()) {
            throw new IllegalStateException("No grades added");
        }
        double max = grades.getFirst();
        for (double g : grades) {
            if (g > max) {
                max = g;
            }
        }
        return max;
    }

    public List<Double> getGrades() {
        return Collections.unmodifiableList(grades);
    }
}
