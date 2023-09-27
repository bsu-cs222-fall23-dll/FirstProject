import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WikiConnectionTest {
    private WikiConnection wikiConnection;

    @BeforeEach
    public void setUp() {
        wikiConnection = new WikiConnection();
    }

    @Test
    public void testGetJSONStringFromArticleName_ValidArticleName() throws IOException {
        String articleName = "Java_programming_language";
        String jsonString = wikiConnection.getJSONStringFromArticleName(articleName);

        assertNotNull(jsonString);
        assertFalse(jsonString.isEmpty());
    }

    @Test
    public void testGetJSONStringFromArticleName_InvalidArticleName() {
        String articleName = "InvalidArticleName";
        assertThrows(IOException.class, () -> wikiConnection.getJSONStringFromArticleName(articleName));
    }
}
