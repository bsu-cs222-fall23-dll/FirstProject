package edu.bsu.cs222;

import java.util.ArrayList;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner userInputScanner = new Scanner(System.in);

        System.out.println("Welcome to the Wikipedia Editor Tracker!");
        System.out.print("Enter the name of the Wikipedia article: ");

        String articleName = userInputScanner.nextLine();
        WikiConnection wikiConnection = new WikiConnection();
        String articleJsonData = wikiConnection.fetchArticleJsonData(articleName);
        JSONParser jsonParser = new JSONParser(articleJsonData);

        System.out.println("Redirected to: " + jsonParser.getRedirectsAsString());

        ArrayList<Revision> recentRevisions = jsonParser.constructRevisionArrayList();

        System.out.println("Showing the last " + recentRevisions.size() + " edits:");

        displayRecentRevisions(recentRevisions);
    }

    public static void displayRecentRevisions(ArrayList<Revision> revisionList)
    {
        System.out.println("Recent revisions:");

        int totalRevisions = revisionList.size();
        int revisionsToDisplay = Math.min(totalRevisions, 13);

        for (int i = 0; i < revisionsToDisplay; i++)
        {
            Revision revision = revisionList.get(i);
            System.out.println(revision.getTimestamp() + " " + revision.getUsername());
        }
    }
}
