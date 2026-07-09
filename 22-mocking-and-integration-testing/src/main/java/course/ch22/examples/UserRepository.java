package course.ch22.examples;

import java.util.Optional;

/**
 * Repository interface for user persistence.
 */
public interface UserRepository {

    /**
     * Finds a user by their ID.
     *
     * @param id the user ID
     * @return an optional containing the user, or empty if not found
     */
    Optional<User> findById(long id);

    /**
     * Saves a user.
     *
     * @param user the user to save
     * @return the saved user (potentially with generated ID)
     */
    User save(User user);
}
