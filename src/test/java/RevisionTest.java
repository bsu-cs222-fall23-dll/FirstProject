import edu.bsu.cs222.Revision;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevisionTest {

    @Test
    public void testGettersAndSetters() {
        String timestamp = "2023-10-03T10:15:30Z";
        String username = "User1";

        Revision revision = new Revision(timestamp, username);

        assertEquals(timestamp, revision.getTimestamp());
        assertEquals(username, revision.getUsername());

        String newTimestamp = "2023-10-04T09:30:45Z";
        String newUsername = "User2";

        revision.setTimestamp(newTimestamp);
        revision.setUsername(newUsername);

        assertEquals(newTimestamp, revision.getTimestamp());
        assertEquals(newUsername, revision.getUsername());
    }

    @Test
    public void testToString() {
        String timestamp = "2023-10-03T10:15:30Z";
        String username = "User1";

        Revision revision = new Revision(timestamp, username);

        String expectedString = "2023-10-03T10:15:30Z User1";
        String actualString = revision.toString();

        assertEquals(expectedString, actualString);
    }
}
