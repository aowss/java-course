package course.ch19.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PackageOrganizerTest {

    @Test
    void serviceClassGoesToService() {
        Map<String, String> result = PackageOrganizer.organize(List.of("UserService"));
        assertEquals("service", result.get("UserService"));
    }

    @Test
    void repositoryClassGoesToRepository() {
        Map<String, String> result = PackageOrganizer.organize(List.of("UserRepository"));
        assertEquals("repository", result.get("UserRepository"));
    }

    @Test
    void daoClassGoesToRepository() {
        Map<String, String> result = PackageOrganizer.organize(List.of("OrderDao"));
        assertEquals("repository", result.get("OrderDao"));
    }

    @Test
    void controllerClassGoesToController() {
        Map<String, String> result = PackageOrganizer.organize(List.of("ProductController"));
        assertEquals("controller", result.get("ProductController"));
    }

    @Test
    void utilClassGoesToUtil() {
        Map<String, String> result = PackageOrganizer.organize(List.of("StringUtils"));
        assertEquals("util", result.get("StringUtils"));
    }

    @Test
    void helperClassGoesToUtil() {
        Map<String, String> result = PackageOrganizer.organize(List.of("DateHelper"));
        assertEquals("util", result.get("DateHelper"));
    }

    @Test
    void plainClassDefaultsToModel() {
        Map<String, String> result = PackageOrganizer.organize(List.of("User"));
        assertEquals("model", result.get("User"));
    }

    @Test
    void multipleClassesAreOrganized() {
        List<String> classes = List.of("Order", "OrderService", "OrderRepository", "OrderController");
        Map<String, String> result = PackageOrganizer.organize(classes);
        assertEquals(4, result.size());
        assertEquals("model", result.get("Order"));
        assertEquals("service", result.get("OrderService"));
        assertEquals("repository", result.get("OrderRepository"));
        assertEquals("controller", result.get("OrderController"));
    }

    @Test
    void emptyListReturnsEmptyMap() {
        Map<String, String> result = PackageOrganizer.organize(List.of());
        assertTrue(result.isEmpty());
    }
}
