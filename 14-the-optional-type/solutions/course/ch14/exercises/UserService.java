package course.ch14.exercises;

import java.util.Map;
import java.util.Optional;

public class UserService {

    public record User(String id, String name, String email) {
    }

    private final Map<String, User> users;

    public UserService(Map<String, User> users) {
        this.users = users;
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    public Optional<String> findEmail(String id) {
        return findById(id)
                .map(User::email)
                .filter(email -> email != null && !email.isBlank());
    }

    public Optional<String> findEmailDomain(String id) {
        return findEmail(id).flatMap(UserService::extractDomain);
    }

    public String greet(String id) {
        return findById(id)
                .map(User::name)
                .map(name -> "Hello, " + name + "!")
                .orElse("Hello, Guest!");
    }

    private static Optional<String> extractDomain(String email) {
        int at = email.indexOf('@');
        if (at < 0 || at == email.length() - 1) {
            return Optional.empty();
        }
        return Optional.of(email.substring(at + 1));
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
