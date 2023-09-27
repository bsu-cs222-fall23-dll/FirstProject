import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JSONParserTest {
    private JSONParser jsonParser;

    @BeforeEach
    public void setUp() {
        jsonParser = new JSONParser();
    }

    @Test
    public void testParseValidJSONString() throws IOException {
        // Mock an input stream with sample JSON data
        String sampleJSON = "{\"revisions\":[{\"user\":\"User1\",\"timestamp\":\"2023-09-15T12:00:00Z\"}," +
                "{\"user\":\"User2\",\"timestamp\":\"2023-09-14T14:30:00Z\"}]}";
        InputStream inputStream = TestUtils.stringToInputStream(sampleJSON);

        // Parse the JSON data
        jsonParser.parse(inputStream);

        // Validate the parsed data
        assertEquals(2, jsonParser.getRevisions().size());
        assertEquals("User1", jsonParser.getRevisions().get(0).getUser());
        assertEquals("2023-09-15T12:00:00Z", jsonParser.getRevisions().get(0).getTimestamp());
        assertEquals("User2", jsonParser.getRevisions().get(1).getUser());
        assertEquals("2023-09-14T14:30:00Z", jsonParser.getRevisions().get(1).getTimestamp());
    }

    @Test
    public void testParseEmptyJSONString() throws IOException {
        // Mock an input stream with an empty JSON string
        String emptyJSON = "{}";
        InputStream inputStream = TestUtils.stringToInputStream(emptyJSON);

        // Parse the JSON data
        jsonParser.parse(inputStream);

        // Validate that no revisions are parsed
        assertEquals(0, jsonParser.getRevisions().size());
    }

    @Test
    public void testParseInvalidJSONString() throws IOException {
        // Mock an input stream with invalid JSON data
        String invalidJSON = "Invalid JSON Data";
        InputStream inputStream = TestUtils.stringToInputStream(invalidJSON);

        // Parse the JSON data
        jsonParser.parse(inputStream);

        // Validate that parsing returns null revisions
        assertNull(jsonParser.getRevisions());
    }
}
