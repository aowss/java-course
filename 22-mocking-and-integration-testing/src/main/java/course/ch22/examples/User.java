package course.ch22.examples;

/**
 * A simple user domain object.
 *
 * @param id    the user ID
 * @param name  the user's name
 * @param email the user's email address
 */
public record User(long id, String name, String email) {}
