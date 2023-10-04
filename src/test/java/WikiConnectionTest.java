import edu.bsu.cs222.WikiConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WikiConnectionTest {

    private WikiConnection wikiConnection;
    private HttpURLConnection mockConnection;
    private BufferedReader mockReader;
    private InputStream mockInputStream;

    @BeforeEach
    public void setUp() {
        wikiConnection = new WikiConnection();
        mockConnection = mock(HttpURLConnection.class);
        mockReader = mock(BufferedReader.class);
        mockInputStream = mock(InputStream.class);
    }

    @Test
    public void testFetchArticleJsonData() throws IOException {
        String articleName = "Test_Article";
        String expectedJsonData = "{\"query\":{\"pages\":{\"1\":{\"revisions\":[{\"timestamp\":\"2023-10-03T10:15:30Z\",\"user\":\"User1\"}]}}}}";

        when(mockConnection.getInputStream()).thenReturn(mockInputStream);
        when(mockInputStream.read(any(byte[].class), anyInt(), anyInt())).thenReturn(-1);
        when(mockReader.readLine()).thenReturn(expectedJsonData, null);

        URL mockUrl = mock(URL.class);
        when(mockUrl.openConnection()).thenReturn(mockConnection);

        String jsonData = wikiConnection.fetchArticleJsonData(articleName);

        assertEquals(expectedJsonData, jsonData);

        verify(mockConnection).setRequestMethod("GET");
        verify(mockConnection).setRequestProperty("User-Agent", "WikipediaEditorTracker/1.0 (your-email@example.com)");
        verify(mockReader).close();
        verify(mockConnection).disconnect();
    }

    @Test
    public void testFormatArticleNameForURL() {
        String articleName = "Test Article";
        String expectedFormattedName = "Test_Article";

        String formattedName = wikiConnection.formatArticleNameForURL(articleName);

        assertEquals(expectedFormattedName, formattedName);
    }
}
