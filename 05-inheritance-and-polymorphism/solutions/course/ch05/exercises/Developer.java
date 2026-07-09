package course.ch05.exercises;

import java.util.Objects;

public class Developer extends Employee {

    private String language;

    public Developer(String name, double salary, String language) {
        super(name, salary);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        var developer = (Developer) o;
        return Objects.equals(language, developer.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), language);
    }

    @Override
    public String toString() {
        return "Developer{name='" + getName() + "', salary=" + getSalary()
                + ", language='" + language + "'}";
    }
}
