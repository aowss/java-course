package course.ch28.examples;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClassLoadingOrderTest {

    @Test
    void staticInitializersRunBeforeConstructors() {
        List<String> log = ClassLoadingOrder.loadAndInstantiate();
        int parentStaticField = log.indexOf("static field: Parent");
        int parentStaticBlock = log.indexOf("static block: Parent");
        int childStaticField = log.indexOf("static field: Child");
        int childStaticBlock = log.indexOf("static block: Child");
        int parentConstructor = log.indexOf("constructor: Parent");
        int childConstructor = log.indexOf("constructor: Child");

        assertTrue(parentStaticField >= 0);
        assertTrue(parentStaticBlock > parentStaticField);
        assertTrue(childStaticField > parentStaticBlock);
        assertTrue(childStaticBlock > childStaticField);
        assertTrue(parentConstructor > childStaticBlock);
        assertTrue(childConstructor > parentConstructor);
    }

    @Test
    void instanceBlocksRunBeforeConstructors() {
        List<String> log = ClassLoadingOrder.loadAndInstantiate();
        int parentInstanceBlock = log.indexOf("instance block: Parent");
        int parentConstructor = log.indexOf("constructor: Parent");
        int childInstanceBlock = log.indexOf("instance block: Child");
        int childConstructor = log.indexOf("constructor: Child");

        assertTrue(parentInstanceBlock < parentConstructor);
        assertTrue(childInstanceBlock < childConstructor);
        assertTrue(parentConstructor < childInstanceBlock);
    }

    @Test
    void createsNamedInstance() {
        List<String> log = ClassLoadingOrder.loadAndInstantiate();
        assertEquals("instance created: Ada", log.getLast());
    }
}
