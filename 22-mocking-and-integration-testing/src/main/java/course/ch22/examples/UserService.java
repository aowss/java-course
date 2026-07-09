package course.ch22.examples;

import java.util.Optional;

/**
 * Business service that depends on {@link UserRepository} and {@link EmailService}.
 *
 * <p>Used to demonstrate mocking dependencies in tests.
 */
public class UserService {

    private final UserRepository repository;
    private final EmailService emailService;

    public UserService(UserRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    /**
     * Finds a user by ID.
     *
     * @param id the user ID
     * @return an optional containing the user
     */
    public Optional<User> findUser(long id) {
        return repository.findById(id);
    }

    /**
     * Registers a new user: saves to repository and sends a welcome email.
     *
     * @param name  the user's name
     * @param email the user's email
     * @return the saved user
     */
    public User registerUser(String name, String email) {
        User user = repository.save(new User(0, name, email));
        emailService.send(email, "Welcome!", "Welcome to our platform, " + name + "!");
        return user;
    }
}
