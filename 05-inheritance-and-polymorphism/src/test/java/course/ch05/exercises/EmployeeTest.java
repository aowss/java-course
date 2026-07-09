package course.ch05.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EmployeeTest {

    // --- equals ---

    @Test
    void employeeEqualsItself() {
        var e = new Employee("Alice", 75000);
        assertEquals(e, e);
    }

    @Test
    void equalEmployeesAreEqual() {
        var e1 = new Employee("Alice", 75000);
        var e2 = new Employee("Alice", 75000);
        assertEquals(e1, e2);
    }

    @Test
    void differentNameNotEqual() {
        var e1 = new Employee("Alice", 75000);
        var e2 = new Employee("Bob", 75000);
        assertNotEquals(e1, e2);
    }

    @Test
    void employeeNotEqualToNull() {
        var e = new Employee("Alice", 75000);
        assertNotEquals(null, e);
    }

    @Test
    void employeeNotEqualToManager() {
        var e = new Employee("Alice", 75000);
        var m = new Manager("Alice", 75000, "Engineering");
        assertNotEquals(e, m);
    }

    // --- Manager ---

    @Test
    void equalManagersAreEqual() {
        var m1 = new Manager("Alice", 95000, "Engineering");
        var m2 = new Manager("Alice", 95000, "Engineering");
        assertEquals(m1, m2);
    }

    @Test
    void managersWithDifferentDepartmentsNotEqual() {
        var m1 = new Manager("Alice", 95000, "Engineering");
        var m2 = new Manager("Alice", 95000, "Sales");
        assertNotEquals(m1, m2);
    }

    // --- Developer ---

    @Test
    void equalDevelopersAreEqual() {
        var d1 = new Developer("Bob", 85000, "Java");
        var d2 = new Developer("Bob", 85000, "Java");
        assertEquals(d1, d2);
    }

    @Test
    void developersWithDifferentLanguagesNotEqual() {
        var d1 = new Developer("Bob", 85000, "Java");
        var d2 = new Developer("Bob", 85000, "Python");
        assertNotEquals(d1, d2);
    }

    // --- hashCode ---

    @Test
    void equalObjectsHaveSameHashCode() {
        var e1 = new Employee("Alice", 75000);
        var e2 = new Employee("Alice", 75000);
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    void equalManagersHaveSameHashCode() {
        var m1 = new Manager("Alice", 95000, "Engineering");
        var m2 = new Manager("Alice", 95000, "Engineering");
        assertEquals(m1.hashCode(), m2.hashCode());
    }

    // --- toString ---

    @Test
    void employeeToString() {
        var e = new Employee("Alice", 75000);
        assertEquals("Employee{name='Alice', salary=75000.0}", e.toString());
    }

    @Test
    void managerToString() {
        var m = new Manager("Alice", 95000, "Engineering");
        assertEquals("Manager{name='Alice', salary=95000.0, department='Engineering'}", m.toString());
    }

    @Test
    void developerToString() {
        var d = new Developer("Bob", 85000, "Java");
        assertEquals("Developer{name='Bob', salary=85000.0, language='Java'}", d.toString());
    }
}
