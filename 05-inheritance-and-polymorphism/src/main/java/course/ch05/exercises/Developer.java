package course.ch05.exercises;

/**
 * Exercise 2 (Practice): A developer with a primary programming language.
 *
 * <p>Extends {@link Employee} with a {@code language} field.
 * Override {@link #equals(Object)}, {@link #hashCode()}, and
 * {@link #toString()} to include the language.
 */
public class Developer extends Employee {

    private String language;

    public Developer(String name, double salary, String language) {
        super(name, salary);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    /**
     * Two developers are equal if their employee fields and language match.
     */
    @Override
    public boolean equals(Object o) {
        // TODO: call super.equals, then compare language
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int hashCode() {
        // TODO: combine super.hashCode() with language
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns {@code "Developer{name='...', salary=..., language='...'}"}.
     */
    @Override
    public String toString() {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
