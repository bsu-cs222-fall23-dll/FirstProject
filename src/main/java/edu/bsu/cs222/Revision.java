package edu.bsu.cs222

public class Revision
{
  private String timestamp;
  private String username;

  public Revision(String timestamp, String username)
  {
    this.timestamp = timestamp;
    this.username = username;
  }

  public String getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp(String timestamp)
  {
    this.timestamp = timestamp;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  @Override
  public String toString()
  {
    return timestamp + " " + username;
  }
}
