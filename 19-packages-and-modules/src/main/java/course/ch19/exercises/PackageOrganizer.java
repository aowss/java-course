package course.ch19.exercises;

import java.util.List;
import java.util.Map;

/**
 * Exercise 1 (Guided): Package organization by convention.
 *
 * <p>Given a list of class names with hints about their purpose, assign each
 * class to the correct package following standard Java conventions:
 * <ul>
 *   <li>{@code "model"} — data/domain classes (e.g., User, Order, Product)</li>
 *   <li>{@code "service"} — business logic classes (names ending with "Service")</li>
 *   <li>{@code "repository"} — data access classes (names ending with "Repository" or "Dao")</li>
 *   <li>{@code "controller"} — request-handling classes (names ending with "Controller")</li>
 *   <li>{@code "util"} — utility/helper classes (names ending with "Util", "Utils", or "Helper")</li>
 * </ul>
 *
 * <p>Classes that don't match any suffix rule should be assigned to {@code "model"}.
 */
public class PackageOrganizer {

    /**
     * Assigns each class name to a package layer based on naming conventions.
     *
     * @param classNames the list of simple class names
     * @return a map from class name to layer name (e.g., "UserService" → "service")
     */
    public static Map<String, String> organize(List<String> classNames) {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
