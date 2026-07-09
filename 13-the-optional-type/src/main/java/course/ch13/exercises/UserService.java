package course.ch13.exercises;

import java.util.Map;
import java.util.Optional;

/**
 * Exercise 2 (Practice): User service with Optional flatMap chains.
 */
public class UserService {

    /**
     * A user profile.
     *
     * @param id    the user id
     * @param name  the display name
     * @param email the email address (may be null)
     */
    public record User(String id, String name, String email) {
    }

    private final Map<String, User> users;

    /**
     * Creates a user service with the given user database.
     *
     * @param users map of user id to user
     */
    public UserService(Map<String, User> users) {
        this.users = users;
    }

    /**
     * Finds a user by id.
     *
     * @param id the user id
     * @return the user, or empty if not found
     */
    public Optional<User> findById(String id) {
        // TODO: implement — return Optional.ofNullable(users.get(id))
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the user's email if present and non-blank.
     *
     * @param id the user id
     * @return the email, or empty
     */
    public Optional<String> findEmail(String id) {
        // TODO: implement — findById → map email → filter non-blank
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the email domain (part after {@code @}).
     *
     * @param id the user id
     * @return the domain, or empty
     */
    public Optional<String> findEmailDomain(String id) {
        // TODO: implement — findEmail → flatMap extract domain
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns a greeting for the user, or a default greeting if not found.
     *
     * @param id the user id
     * @return the greeting string
     */
    public String greet(String id) {
        // TODO: implement — findById → map name → format greeting, orElse default
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        var service = new UserService(Map.of(
                "u1", new User("u1", "Alice", "alice@example.com"),
                "u2", new User("u2", "Bob", null)
        ));
        System.out.println("Email:  " + service.findEmail("u1"));
        System.out.println("Domain: " + service.findEmailDomain("u1"));
        System.out.println("Greet:  " + service.greet("u1"));
    }
}
