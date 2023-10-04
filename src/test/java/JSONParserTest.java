import edu.bsu.cs222.JSONParser;
import edu.bsu.cs222.Revision;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONParserTest {

    @Test
    public void testGetRedirectsAsString() {
        String jsonData = "{\"query\":{\"redirects\":[{\"to\":\"NewArticle\"}]}}";
        JSONParser parser = new JSONParser(jsonData);

        String expected = "Redirected to NewArticle\n";
        String actual = parser.getRedirectsAsString();

        assertEquals(expected, actual);
    }

    @Test
    public void testConstructRevisionArrayList() {
        String jsonData = "{\"query\":{\"pages\":{\"1\":{\"revisions\":[{\"timestamp\":\"2023-10-03T10:15:30Z\",\"user\":\"User1\"}]}}}}";
        JSONParser parser = new JSONParser(jsonData);

        ArrayList<Revision> revisions = parser.constructRevisionArrayList();

        assertEquals(1, revisions.size());
        assertEquals("2023-10-03T10:15:30Z", revisions.get(0).getTimestamp());
        assertEquals("User1", revisions.get(0).getUsername());
    }
}
