package course.ch22.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Spy Demo")
class SpyDemoTest {

    @Spy
    private AuditLog auditLog = new AuditLog();

    @Test
    @DisplayName("spy uses real implementation but allows verification")
    void spyUsesRealImplementation() {
        auditLog.record("User logged in");
        auditLog.record("User viewed dashboard");

        assertEquals(2, auditLog.size());
        assertEquals("User logged in", auditLog.getEntries().get(0));

        verify(auditLog, times(2)).record(org.mockito.ArgumentMatchers.anyString());
    }

    @Test
    @DisplayName("spy tracks specific method calls")
    void spyTracksSpecificCalls() {
        auditLog.record("Created order #123");

        verify(auditLog).record("Created order #123");
        assertEquals(1, auditLog.size());
    }
}
