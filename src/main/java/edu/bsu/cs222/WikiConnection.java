package edu.bsu.cs222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WikiConnection
{
  private static final String WIKIPEDIA_API_URL = "https://en.wikipedia.org/w/api.php";
  private static final String USER_AGENT = "WikipediaEditorTracker/1.0 (your-email@example.com)";

  public String fetchArticleJsonData(String articleName)
  {
    String formattedArticleName = formatArticleNameForURL(articleName);
    String apiUrl = buildApiUrl(formattedArticleName);

    HttpURLConnection connection = null;
    BufferedReader reader = null;
    StringBuilder jsonContent = new StringBuilder();

    try
    {
      URL url = new URL(apiUrl);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("User-Agent", USER_AGENT);

      InputStream inputStream = connection.getInputStream();
      reader = new BufferedReader(new InputStreamReader(inputStream));

      String line;
      while ((line = reader.readLine()) != null)
      {
        jsonContent.append(line);
      }
    }

    catch (IOException e)
    {
      Logger.getLogger(WikiConnection.class.getName()).log(Level.SEVERE, "Connection failed :(", e);
    }

    finally
    {
      try
      {
        if (reader != null)
        {
          reader.close();
        }
        if (connection != null)
        {
          connection.disconnect();
        }
      }

      catch (IOException e)
      {
        Logger.getLogger(WikiConnection.class.getName()).log(Level.SEVERE, "Error closing resources", e);
      }
    }

    return jsonContent.toString();
  }

  public String formatArticleNameForURL(String articleName)
  {
    return articleName.replace(" ", "_");
  }

  private String buildApiUrl(String formattedArticleName)
  {
    return String.format("%s?action=query&format=json&prop=revisions&titles=%s&rvprop=timestamp|user&rvlimit=13&redirects",
            WIKIPEDIA_API_URL, formattedArticleName);
  }
}
  private String buildApiUrl(String formattedArticleName)
  {
    return String.format("%s?action=query&format=json&prop=revisions&titles=%s&rvprop=timestamp|user&rvlimit=13&redirects",
            WIKIPEDIA_API_URL, formattedArticleName);
  }
}
