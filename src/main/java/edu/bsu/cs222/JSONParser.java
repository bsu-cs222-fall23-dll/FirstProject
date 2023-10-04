package edu.bsu.cs222;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JSONParser
{
  private final String jsonData;

  public JSONParser(String jsonData)
  {
    this.jsonData = jsonData;
  }

  public String getRedirectsAsString()
  {
    try
    {
      Map<String, Object> jsonObject = parseJSON(jsonData);
      Map<String, Object> queryObject = (Map<String, Object>) jsonObject.get("query");
      List<Object> redirectsArray = (List<Object>) queryObject.get("redirects");

      if (!redirectsArray.isEmpty())
      {
        Map<String, Object> redirectObject = (Map<String, Object>) redirectsArray.get(0);
        String redirectTo = (String) redirectObject.get("to");
        return "Redirected to " + redirectTo + "\n";
      }
    }
    catch (JSONException e)
    {
      handleJsonException(e);
    }
    return "";
  }

  public ArrayList<Revision> constructRevisionArrayList()
  {
    ArrayList<Revision> revisions = new ArrayList<>();

    try
    {
      Map<String, Object> jsonObject = parseJSON(jsonData);
      Map<String, Object> queryObject = (Map<String, Object>) jsonObject.get("query");
      Map<String, Object> pagesObject = (Map<String, Object>) queryObject.get("pages");
      Iterator<Map.Entry<String, Object>> pagesIterator = pagesObject.entrySet().iterator();
      String pageId = pagesIterator.hasNext() ? pagesIterator.next().getKey() : null; // Get the first (and only) key

      List<Object> revisionsArray = (List<Object>) ((Map<String, Object>) pagesObject.get(pageId)).get("revisions");
      for (Object revisionObject : revisionsArray)
      {
        Map<String, Object> revisionMap = (Map<String, Object>) revisionObject;
        String timestamp = (String) revisionMap.get("timestamp");
        String username = (String) revisionMap.get("user");
        revisions.add(new Revision(timestamp, username));
      }
    }
    catch (JSONException e)
    {
      handleJsonException(e);
    }

    return revisions;
  }

  // JSON parsing logic without external library
  private Map<String, Object> parseJSON(String jsonData) throws JSONException
  {
    try
    {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(jsonData, Map.class);
    }
    catch (IOException e)
    {
      throw new JSONException("JSON parsing error: " + e.getMessage());
    }
  }

  // Handle JSON parsing errors
  private void handleJsonException(JSONException e) {
    // Handle JSON parsing errors if necessary
    System.err.println("JSON parsing error: " + e.getMessage());
  }
  private static class JSONException extends Exception {
    public JSONException(String message) {
      super(message);
    }
  }
}
