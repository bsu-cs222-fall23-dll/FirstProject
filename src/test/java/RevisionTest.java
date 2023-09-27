import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevisionTest {
    private Revision revision;

    @BeforeEach
    public void setUp() {
        revision = new Revision();
    }

    @Test
    public void testSetAndGetUser() {
        revision.setUser("User1");
        assertEquals("User1", revision.getUser());
    }

    @Test
    public void testSetAndGetTimestamp() {
        revision.setTimestamp("2023-09-15T12:00:00Z");
        assertEquals("2023-09-15T12:00:00Z", revision.getTimestamp());
    }
}
