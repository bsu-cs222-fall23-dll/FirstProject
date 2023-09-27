package edu.bsu.cs222

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
      JSONObject jsonObject = new JSONObject(jsonData);
      JSONObject queryObject = jsonObject.getJSONObject("query");
      JSONArray redirectsArray = queryObject.getJSONArray("redirects");

      if (redirectsArray.length() > 0)
      {
        JSONObject redirectObject = redirectsArray.getJSONObject(0);
        String redirectTo = redirectObject.getString("to");
        return "Redirected to " + redirectTo + "\n";
      }
    }

    catch (JSONException e)
    {
      // Handle JSON parsing errors if necessary
    }
    return "";
  }

  public ArrayList<Revision> constructRevisionArrayList()
  {
    ArrayList<Revision> revisions = new ArrayList<>();
  
    try
    {
      JSONObject jsonObject = new JSONObject(jsonData);
      JSONObject queryObject = jsonObject.getJSONObject("query");
      JSONObject pagesObject = queryObject.getJSONObject("pages");
      String pageId = pagesObject.keys().next(); //Get the first (and only) key
  
      JSONArray revisionsArray = pagesObject.getJSONObject(pageId).getJSONArray("revisions");
      for (int i = 0; i < revisionsArray.length(); i++)
      {
        JSONObject revisionObject = revisionsArray.getJSONObject(i);
        String timestamp = revisionObject.getString("timestamp");
        String username = revisionObject.getString("user");
        revisions.add(new Revision(timestamp, username));
      }
    }
  
    catch (JSONException e)
    {
      // Handle JSON parsing errors if necessary
    }
  
    return revisions;
  }
}
